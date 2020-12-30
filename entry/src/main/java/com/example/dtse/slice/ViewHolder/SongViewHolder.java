package com.example.dtse.slice.ViewHolder;

import com.chg.ultimateprovider.EventTransmissionListener;
import com.chg.ultimateprovider.UltimateProvider;
import com.chg.ultimateprovider.ViewHolder;
import com.example.dtse.ResourceTable;
import com.example.dtse.slice.model.SongModel;
import ohos.agp.components.Component;
import ohos.agp.components.Image;
import ohos.agp.components.Text;

public class SongViewHolder extends ViewHolder<SongModel> {

    private Image icon;
    private Text songName;
    private Text songer;

    public SongViewHolder(EventTransmissionListener eventTransmissionListener, Component component, UltimateProvider provider) {
        super(eventTransmissionListener, component,provider);

        icon = (Image) findComponentById(ResourceTable.Id_icon);
        songName = (Text) findComponentById(ResourceTable.Id_songName);
        songer = (Text) findComponentById(ResourceTable.Id_songer);
    }

    @Override
    public void onDataBound() {
        songName.setText(getModel().getName());
        songer.setText(getModel().getSinger());
    }
}