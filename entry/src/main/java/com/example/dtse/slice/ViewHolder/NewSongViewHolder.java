package com.example.dtse.slice.ViewHolder;

import com.chg.ultimateprovider.EventTransmissionListener;
import com.chg.ultimateprovider.UltimateProvider;
import com.chg.ultimateprovider.ViewHolder;
import com.example.dtse.ResourceTable;
import com.example.dtse.slice.model.NewSongModel;
import com.example.dtse.slice.model.SongData;
import com.example.dtse.slice.model.SongModel;
import ohos.agp.components.*;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

public class NewSongViewHolder extends ViewHolder<NewSongModel> {
    private static final HiLogLabel LABEL_LOG = new HiLogLabel(3, 0xD001100, "Demo");


    private DirectionalLayout mulit_mainlayout;

    public NewSongViewHolder(EventTransmissionListener eventTransmissionListener, Component component, UltimateProvider provider) {
        super(eventTransmissionListener, component,provider);

      //  ComponentContainer container= (ComponentContainer) component;
        mulit_mainlayout= (DirectionalLayout) findComponentById(ResourceTable.Id_mulit_mainlayout);

    }

    @Override
    public void onDataBound() {
       /* songName.setText(getModel().getName());
        songer.setText(getModel().getSinger());*/
        HiLog.error(LABEL_LOG,"size=="+getModel().getSongModels().size());
        for (int j = 0; j < getModel().getSongModels().size(); j++) {
            SongData songModel=getModel().getSongModels().get(j);
            DirectionalLayout directionalLayout= (DirectionalLayout) LayoutScatter.getInstance(getContext()).parse(ResourceTable.Layout_mulitsong_item,null,false);
            Text songName= (Text) directionalLayout.findComponentById(ResourceTable.Id_songName);
            Text songer= (Text) directionalLayout.findComponentById(ResourceTable.Id_songer);
            songName.setText(songModel.getName());
            songer.setText(songModel.getSinger());
            mulit_mainlayout.addComponent(directionalLayout);
        }
    }
}