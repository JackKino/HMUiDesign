package com.example.dtse.slice.ViewHolder;

import com.chg.ultimateprovider.EventTransmissionListener;
import com.chg.ultimateprovider.UltimateProvider;
import com.chg.ultimateprovider.ViewHolder;

import com.example.dtse.ResourceTable;
import com.example.dtse.slice.model.MusicModel;
import ohos.agp.components.Component;
import ohos.agp.components.ListContainer;
import ohos.agp.components.Text;

public class MusicViewHolder extends ViewHolder<MusicModel> {

    private Text title;
    private ListContainer listContainer;
    private UltimateProvider ultimateProvider;

    public MusicViewHolder(EventTransmissionListener eventTransmissionListener, Component component, UltimateProvider provider) {
        super(eventTransmissionListener, component,provider);
        title = (Text) findComponentById(ResourceTable.Id_title);
        listContainer = (ListContainer) findComponentById(ResourceTable.Id_listContainer);
        ultimateProvider = new UltimateProvider(null,getContext());
        ultimateProvider.setEventTransmissionListener(getEventTransmissionListener());
    }

    @Override
    public void onDataBound() {
        title.setText(getModel().getTitle());
        ultimateProvider.setModels(getModel().getData());
        listContainer.setItemProvider(ultimateProvider);
    }
}
