package com.example.dtse.slice.ViewHolder;


import com.example.dtse.ResourceTable;
import com.example.dtse.slice.model.*;
import com.google.gson.Gson;
import ohos.aafwk.ability.AbilitySlice;
import ohos.agp.colors.RgbPalette;
import ohos.agp.components.*;
import ohos.agp.components.element.PixelMapElement;
import ohos.agp.components.element.ShapeElement;
import ohos.agp.components.element.StateElement;
import ohos.agp.components.element.VectorElement;
import ohos.agp.render.Arc;
import ohos.agp.utils.Point;
import ohos.agp.window.dialog.ListDialog;
import ohos.agp.window.service.Display;
import ohos.agp.window.service.DisplayManager;
import ohos.app.Context;
import ohos.app.IAbilityManager;
import ohos.global.resource.NotExistException;
import ohos.global.resource.Resource;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;
import ohos.workscheduler.WorkInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class MultSongProvider extends RecycleItemProvider {
    private static final HiLogLabel LABEL_LOG = new HiLogLabel(3, 0xD001100, "Demo");

    private List<NewSongModel> datas=new ArrayList<>();
    private AbilitySlice mSlice;
    LayoutScatter layoutScatter;
    private int width=0;
    private onItemLongClickListener listener;


    public MultSongProvider(AbilitySlice mSlice, Context context,onItemLongClickListener listener) {
        this.mSlice = mSlice;
        this.layoutScatter = LayoutScatter.getInstance(context);
        this.listener=listener;
       /* Point point= new Point();
        Optional<Display> optional= DisplayManager.getInstance().getDefaultDisplay(mSlice);
        optional.get().getRealSize(point);
        width=point.getPointXToInt();*/
    }

    public void setDataMos(List<NewSongModel> cityMos){
        this.datas.clear();
        this.datas.addAll(cityMos);
        HiLog.error(LABEL_LOG,"datas=="+datas.size());
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
        //    directionalLayout.setWidth(width/2);
        //    HiLog.error(LABEL_LOG,"width=="+width/2);
         //   directionalLayout.setHeight(ComponentContainer.LayoutConfig.MATCH_CONTENT);
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
           // main.addComponent(directionalLayout,ComponentContainer.LayoutConfig.MATCH_CONTENT);
         //   container.setAutoLayout(true,4);
            container.addComponent(directionalLayout);
            directionalLayout.setLongClickedListener(new Component.LongClickedListener() {
                @Override
                public void onLongClicked(Component component) {
                    listener.onItemClick(songModel,pos);
                }
            });

        }
        //container.addComponent(main);
        return component1;
    }
    public interface onDeleteListener{
        void onItemClick(UserModel userBean, int position);
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

    /**
     * 类似于Android中的listView缓存。
     * 将已经显示在屏幕上的item缓存在ViewHolder中，下次再次出现直接从缓存中读取
     */
    static class ViewHolder {
        Text id;
        Text name;
        Text lastName;
        Text age;
        Text numbers;
        public ViewHolder(ComponentContainer componentContainer) {
            id = (Text) componentContainer.findComponentById(ResourceTable.Id_user_id);
            name = (Text) componentContainer.findComponentById(ResourceTable.Id_user_name);
            lastName = (Text) componentContainer.findComponentById(ResourceTable.Id_user_last_name);
            age = (Text) componentContainer.findComponentById(ResourceTable.Id_user_age);
            numbers = (Text) componentContainer.findComponentById(ResourceTable.Id_user_numbers);
        }
    }

    public void setWidth(int width){
        this.width=width;
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

}
