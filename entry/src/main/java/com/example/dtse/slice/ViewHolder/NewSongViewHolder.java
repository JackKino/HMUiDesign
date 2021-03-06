package com.example.dtse.slice.ViewHolder;

import com.chg.ultimateprovider.*;
import com.example.dtse.ResourceTable;
import com.example.dtse.slice.model.NewSongModel;
import com.example.dtse.slice.model.SongData;
import com.example.dtse.slice.model.SongModel;
import com.example.dtse.slice.model.UserModel;
import com.google.gson.Gson;
import ohos.agp.components.*;
import ohos.agp.components.element.PixelMapElement;
import ohos.agp.components.element.StateElement;
import ohos.global.resource.NotExistException;
import ohos.global.resource.Resource;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NewSongViewHolder extends ViewHolder2<NewSongModel> {
    private static final HiLogLabel LABEL_LOG = new HiLogLabel(3, 0xD001100, "Demo");
    private EventTransmissionListener eventTransmissionListener;



    public NewSongViewHolder(EventTransmissionListener eventTransmissionListener, Component component, UltimateProvider2 provider) {
        super(eventTransmissionListener, component,provider);
        this.eventTransmissionListener=eventTransmissionListener;

    }

    @Override
    public void setView(){
        DirectionalLayout songitem_layout= (DirectionalLayout) getComponent().findComponentById(ResourceTable.Id_songitem_layout);
        ComponentContainer container= (ComponentContainer) songitem_layout;
        Text songitem_name= (Text) getComponent().findComponentById(ResourceTable.Id_songitem_name);

        //component相当于Android中的view，其他的和Android中ListView的适配器adapter差不多。
        // 名字区别也不大，不过Android中ListView基本被淘汰了。
        NewSongModel usersModel=getModel();
        songitem_name.setText(usersModel.getName());
        for (int j = 0; j < usersModel.getSongModels().size(); j++) {
            SongData songModel=usersModel.getSongModels().get(j);
            DirectionalLayout directionalLayout= (DirectionalLayout) LayoutScatter.getInstance(getContext()).parse(ResourceTable.Layout_mulitsong_item,null,false);
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
            if(songModel.isChecked()){
                checkbox.setChecked(true);
            }else{
                checkbox.setChecked(false);
            }
            checkbox.setCheckedStateChangedListener(new AbsButton.CheckedStateChangedListener() {
                @Override
                public void onCheckedChanged(AbsButton absButton, boolean b) {
                    songModel.setChecked(b);
                    HiLog.error(LABEL_LOG,"checkdatas size=="+getCheckedDatas().size()+" checkdatas=="+new Gson().toJson(getCheckedDatas()));

                }
            });

            setCheckBox(checkbox);
            container.addComponent(directionalLayout);
            directionalLayout.setLongClickedListener(new Component.LongClickedListener() {
                @Override
                public void onLongClicked(Component component) {
                    HiLog.error(LABEL_LOG,"setLongClickedListener=="+getPosition());
                   if(eventTransmissionListener==null){
                       HiLog.error(LABEL_LOG,"eventTransmissionListener==null");
                   }
                    eventTransmissionListener.onEventTransmission(NewSongViewHolder.this, songModel, 1, new EventTransmissionListener.CallBack() {
                        @Override
                        public Object callBack(Object object) {

                            return null;
                        }
                    });
                }
            });

        }
    }

    /*** 设置CheckBox样式 */
    private void setCheckBox(Checkbox checkbox){

        try {
            Resource resource=getContext().getResourceManager().getResource(ResourceTable.Media_checked_icon);
            PixelMapElement elementButtonOn=new PixelMapElement(resource);

            Resource resource2=getContext().getResourceManager().getResource(ResourceTable.Media_unchecked_icon);
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
        for(NewSongModel newSongModel:getModels()){
            for (int j = 0; j < newSongModel.getSongModels().size(); j++) {
                SongData songData=newSongModel.getSongModels().get(j);
                if(songData.isChecked()){
                    checkdatas.add(songData);
                }
            }
        }
        return checkdatas;
    }

    @Override
    public void onDataBound() {
       /* songName.setText(getModel().getName());
        songer.setText(getModel().getSinger());*/
      //  HiLog.error(LABEL_LOG,"size=="+getModel().getSongModels().size());

    }


}