package com.example.dtse.slice.ViewHolder;

import com.chg.ultimateprovider.EventTransmissionListener;
import com.chg.ultimateprovider.UltimateProvider;
import com.chg.ultimateprovider.ViewHolder;
import com.example.dtse.ResourceTable;
import com.example.dtse.slice.model.NestedAlbumModel;
import ohos.agp.components.Component;
import ohos.agp.components.DirectionalLayout;
import ohos.agp.components.Image;
import ohos.agp.components.Text;
import ohos.agp.window.dialog.ToastDialog;

public class NestedAlbumViewHolder extends ViewHolder<NestedAlbumModel> {

    private Image icon;
    private Text name;
    private Text songer;
    private DirectionalLayout album_item_layout;

    public NestedAlbumViewHolder(EventTransmissionListener eventTransmissionListener, Component component, UltimateProvider provider) {
        super(eventTransmissionListener, component,provider);
        icon = (Image) findComponentById(ResourceTable.Id_icon);
        name = (Text) findComponentById(ResourceTable.Id_name);
        songer = (Text) findComponentById(ResourceTable.Id_songer);

        icon.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                ToastDialog toastDialog=new ToastDialog(getContext());
                toastDialog.setText("NestedAlbumViewHolder").show();
                /*getEventTransmissionListener().onEventTransmission(NestedAlbumViewHolder.this, "NestedAlbumViewHolder", 1, new EventTransmissionListener.CallBack() {
                    @Override
                    public Object callBack(Object object) {
                        return null;
                    }
                });*/
            }
        });
    }

    @Override
    public void onDataBound() {
        name.setText(getModel().getName());
        songer.setText(getModel().getSonger());
    }
}