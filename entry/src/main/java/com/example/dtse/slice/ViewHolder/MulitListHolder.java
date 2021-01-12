package com.example.dtse.slice.ViewHolder;

import com.chg.ultimateprovider.*;
import com.example.dtse.ResourceTable;
import com.example.dtse.slice.model.MulitListModel;
import com.example.dtse.slice.model.MusicModel;
import com.example.dtse.slice.model.NewSongModel;
import com.example.dtse.slice.model.SongData;
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

public class MulitListHolder extends ViewHolder2<MulitListModel> {
    private static final HiLogLabel LABEL_LOG = new HiLogLabel(3, 0xD001100, "Demo");
    private Text title;
    private ListContainer listContainer;
    private UltimateProvider ultimateProvider;
    private MultListSongProvider multSongProvider;
    public boolean isShow;

    //  private TableLayout mulit_tablayout;
    public MulitListHolder(EventTransmissionListener eventTransmissionListener, Component component, UltimateProvider2 provider) {
        super(eventTransmissionListener, component,provider);
        title = (Text) findComponentById(ResourceTable.Id_title);

        listContainer = (ListContainer) findComponentById(ResourceTable.Id_listContainer);
        multSongProvider=new MultListSongProvider(getContext(),eventTransmissionListener,provider.isShow);
        DirectionalLayout.LayoutConfig buttonLayoutConfig = new DirectionalLayout.LayoutConfig(ComponentContainer.LayoutConfig.MATCH_PARENT, ComponentContainer.LayoutConfig.MATCH_CONTENT);
        listContainer.setLayoutConfig(buttonLayoutConfig);
        title.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                HiLog.error(LABEL_LOG,"checkdatas size=="+getCheckedDatas().size()+" checkdatas=="+new Gson().toJson(getCheckedDatas()));

            }
        });

     /*   listContainer.setFooterBoundarySwitch(true);
        listContainer.setAutoLayout(true);*/
     //  ultimateProvider.setEventTransmissionListener(getEventTransmissionListener());
    }

    @Override
    public void onDataBound() {
       // HiLog.error(LABEL_LOG,"title=="+getModel().getTitle());
        title.setText(getModel().getTitle());
        multSongProvider.setDataMos(getModel().getData());
        listContainer.setItemProvider(multSongProvider);
    }

    @Override
    public void setView() {
   /*     TableLayout mulit_tablayout= (TableLayout) findComponentById(ResourceTable.Id_mulit_tablayout);
        mulit_tablayout.setColumnCount(2);
        MulitListModel mulitListModel= (MulitListModel) getModel();
        for (int j = 0; j < mulitListModel.getData().size(); j++) {
            NewSongModel songModel= (NewSongModel) mulitListModel.getData().get(j);
            // HiLog.error(LABEL_LOG,"  component.getWidth()/2=="+  component1.getWidth() );
            // HiLog.error(LABEL_LOG,"componentContainer=="+componentContainer.getWidth());
            DirectionalLayout directionalLayout= (DirectionalLayout) LayoutScatter.getInstance(getContext()).parse(ResourceTable.Layout_mulitsong_item,null,false);
            Text songName= (Text) directionalLayout.findComponentById(ResourceTable.Id_songName);
            Text songer= (Text) directionalLayout.findComponentById(ResourceTable.Id_songer);
            Image icon= (Image) directionalLayout.findComponentById(ResourceTable.Id_icon);
            Checkbox checkbox= (Checkbox) directionalLayout.findComponentById(ResourceTable.Id_check_box_1);
            songName.setText(songModel.getName());
          //  songer.setText(songModel.getSinger());
            icon.setImageAndDecodeBounds(ResourceTable.Media_music);
            icon.setScaleMode(Image.ScaleMode.ZOOM_CENTER);
            DirectionalLayout.LayoutConfig buttonLayoutConfig = new DirectionalLayout.LayoutConfig(ComponentContainer.LayoutConfig.MATCH_CONTENT, ComponentContainer.LayoutConfig.MATCH_CONTENT, DirectionalLayout.HORIZONTAL, 1);
            directionalLayout.setPaddingBottom(20);
            directionalLayout.setPaddingTop(20);
            directionalLayout.setLayoutConfig(buttonLayoutConfig);
           *//* if(songModel.isChecked()){
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

            setCheckBox(checkbox);*//*

            mulit_tablayout.addComponent(directionalLayout);
            directionalLayout.setLongClickedListener(new Component.LongClickedListener() {
                @Override
                public void onLongClicked(Component component) {
                    getEventTransmissionListener().onEventTransmission(MulitListHolder.this, songModel, 1, new EventTransmissionListener.CallBack() {
                        @Override
                        public Object callBack(Object object) {

                            return null;
                        }
                    });

                }
            });

        }*/
        //container.addComponent(main);

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


    public void setShowChecked(boolean isShow){
        this.isShow=isShow;
        multSongProvider.setShowChecked(isShow);
        notifyDataChanged();
    }

    public List<SongData> getCheckedDatas(){
        List<SongData> checkdatas=new ArrayList<>();
        for(MulitListModel mulitListModel:getModels()){
            for (int j = 0; j <mulitListModel.getData().size(); j++) {
                NewSongModel newSongModel= (NewSongModel) mulitListModel.getData().get(j);
                for (int i = 0; i <newSongModel.getSongModels().size(); i++) {
                    SongData songData=newSongModel.getSongModels().get(i);
                    if(songData.isChecked()){
                        checkdatas.add(songData);
                    }
                }
            }
        }
        return checkdatas;
    }

}
