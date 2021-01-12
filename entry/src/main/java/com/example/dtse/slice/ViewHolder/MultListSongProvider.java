package com.example.dtse.slice.ViewHolder;


import com.chg.ultimateprovider.EventTransmissionListener;
import com.chg.ultimateprovider.UltimateProvider;
import com.example.dtse.ResourceTable;
import com.example.dtse.slice.model.NewSongModel;
import com.example.dtse.slice.model.SongData;
import com.example.dtse.slice.model.UserModel;
import com.google.gson.Gson;
import ohos.aafwk.ability.AbilitySlice;
import ohos.agp.components.*;
import ohos.agp.components.element.PixelMapElement;
import ohos.agp.components.element.StateElement;
import ohos.app.Context;
import ohos.global.resource.NotExistException;
import ohos.global.resource.Resource;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MultListSongProvider extends RecycleItemProvider {
    private static final HiLogLabel LABEL_LOG = new HiLogLabel(3, 0xD001100, "Demo");

    private List<NewSongModel> datas=new ArrayList<>();
    private Context mSlice;
    LayoutScatter layoutScatter;
    private int width=0;
    private boolean isShow=false;
    private EventTransmissionListener listener;


    public MultListSongProvider(Context mSlice, EventTransmissionListener listener) {
        this.mSlice = mSlice;
        this.layoutScatter = LayoutScatter.getInstance(mSlice);
        this.listener=listener;
       /* Point point= new Point();
        Optional<Display> optional= DisplayManager.getInstance().getDefaultDisplay(mSlice);
        optional.get().getRealSize(point);
        width=point.getPointXToInt();*/
    }

    public MultListSongProvider(Context mSlice, EventTransmissionListener listener, boolean isShow) {
        this.mSlice = mSlice;
        this.layoutScatter = LayoutScatter.getInstance(mSlice);
        this.isShow = isShow;
        this.listener = listener;
    }

    public void setDataMos(List<NewSongModel> cityMos){
        this.datas.clear();
        this.datas.addAll(cityMos);
     //   HiLog.error(LABEL_LOG,"datas=="+datas.size());
        notifyDataChanged();

    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int i) {
        return datas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private void onItemClick(UserModel data,int position){
    }

    public interface onItemLongClickListener{
        void onItemClick(SongData songModel, int position);
    }

    @Override
    public Component getComponent(int pos, Component component, ComponentContainer componentContainer) {
        DirectionalLayout component1= (DirectionalLayout) LayoutScatter.getInstance(mSlice).parse(ResourceTable.Layout_songitem_layout,null,false);

        if (!(component1 instanceof ComponentContainer)){
            return null;
        }

        DirectionalLayout songitem_layout= (DirectionalLayout) component1.findComponentById(ResourceTable.Id_songitem_layout);
        songitem_layout.setTotalWeight(2);
        ComponentContainer container= (ComponentContainer) songitem_layout;
        Text songitem_name= (Text) component1.findComponentById(ResourceTable.Id_songitem_name);

        //component相当于Android中的view，其他的和Android中ListView的适配器adapter差不多。
        // 名字区别也不大，不过Android中ListView基本被淘汰了。
        NewSongModel usersModel=datas.get(pos);
        songitem_name.setText(usersModel.getName());
        for (int j = 0; j < usersModel.getSongModels().size(); j++) {
            SongData songModel=usersModel.getSongModels().get(j);
           // HiLog.error(LABEL_LOG,"  component.getWidth()/2=="+  component1.getWidth() );
           // HiLog.error(LABEL_LOG,"componentContainer=="+componentContainer.getWidth());
            DirectionalLayout directionalLayout= (DirectionalLayout) LayoutScatter.getInstance(mSlice).parse(ResourceTable.Layout_mulitsong_item,null,false);
            Text songName= (Text) directionalLayout.findComponentById(ResourceTable.Id_songName);
            Text songer= (Text) directionalLayout.findComponentById(ResourceTable.Id_songer);
            Image icon= (Image) directionalLayout.findComponentById(ResourceTable.Id_icon);
            Checkbox checkbox= (Checkbox) directionalLayout.findComponentById(ResourceTable.Id_check_box_1);
            songName.setText(songModel.getName());
            songer.setText(songModel.getSinger());
            icon.setImageAndDecodeBounds(ResourceTable.Media_music);
            icon.setScaleMode(Image.ScaleMode.ZOOM_CENTER);
            DirectionalLayout.LayoutConfig buttonLayoutConfig = new DirectionalLayout.LayoutConfig(ComponentContainer.LayoutConfig.MATCH_CONTENT, ComponentContainer.LayoutConfig.MATCH_CONTENT, DirectionalLayout.HORIZONTAL, 1);
            directionalLayout.setPaddingBottom(20);
            directionalLayout.setPaddingTop(20);
            directionalLayout.setLayoutConfig(buttonLayoutConfig);
            if(isShow){
                checkbox.setVisibility(Component.VISIBLE);
            }else{
                checkbox.setVisibility(Component.HIDE);
            }
            if(songModel.isChecked()){
                checkbox.setChecked(true);
            }else{
                checkbox.setChecked(false);
            }
            checkbox.setCheckedStateChangedListener(new AbsButton.CheckedStateChangedListener() {
                @Override
                public void onCheckedChanged(AbsButton absButton, boolean b) {
                          songModel.setChecked(b);
                  //  HiLog.error(LABEL_LOG,"checkdatas size=="+getCheckedDatas().size()+" checkdatas=="+new Gson().toJson(getCheckedDatas()));
                }
            });

            setCheckBox(checkbox);
           // main.addComponent(directionalLayout,ComponentContainer.LayoutConfig.MATCH_CONTENT);
         //   container.setAutoLayout(true,4);
            container.addComponent(directionalLayout);
            directionalLayout.setClickedListener(new Component.ClickedListener() {

                @Override
                public void onClick(Component component) {
                    listener.onEventTransmission(MultListSongProvider.this, songModel, 1, new EventTransmissionListener.CallBack() {
                        @Override
                        public Object callBack(Object object) {
                          //  HiLog.error(LABEL_LOG,"songModel=="+new Gson().toJson(songModel));
                            return null;
                        }
                    });
                }
            });
            directionalLayout.setLongClickedListener(new Component.LongClickedListener() {
                @Override
                public void onLongClicked(Component component) {
                    listener.onEventTransmission(MultListSongProvider.this, isShow, 2, new EventTransmissionListener.CallBack() {
                        @Override
                        public Object callBack(Object object) {
                            //  HiLog.error(LABEL_LOG,"songModel=="+new Gson().toJson(songModel));
                            return null;
                        }
                    });
                    // isShow = (boolean) listener.onEventTransmission(MultListSongProvider.this, isShow,2,null);

                }
            });

        }
        //container.addComponent(main);
        return component1;
    }


    /*** 设置CheckBox样式 */
    private void setCheckBox(Checkbox checkbox){
        try {
            Resource resource=mSlice.getResourceManager().getResource(ResourceTable.Media_checked_icon);
            PixelMapElement  elementButtonOn=new PixelMapElement(resource);
            Resource resource2=mSlice.getResourceManager().getResource(ResourceTable.Media_unchecked_icon);
            PixelMapElement  elementButtonOff=new PixelMapElement(resource2);
            StateElement checkElement = new StateElement();
            checkElement.addState(new int[]{ComponentState.COMPONENT_STATE_CHECKED}, elementButtonOn);
            checkElement.addState(new int[]{ComponentState.COMPONENT_STATE_EMPTY}, elementButtonOff);
            checkbox.setButtonElement(checkElement);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NotExistException e) {
            e.printStackTrace();
        }
    }

    public List<SongData> getCheckedDatas(){
        List<SongData> checkdatas=new ArrayList<>();
        for(NewSongModel newSongModel:datas){
            for (int j = 0; j < newSongModel.getSongModels().size(); j++) {
                SongData songData=newSongModel.getSongModels().get(j);
                if(songData.isChecked()){
                    checkdatas.add(songData);
                }
            }
        }
        return checkdatas;
    }

    public void setShowChecked(boolean isShow){
        this.isShow=isShow;
        notifyDataChanged();
    }



}
