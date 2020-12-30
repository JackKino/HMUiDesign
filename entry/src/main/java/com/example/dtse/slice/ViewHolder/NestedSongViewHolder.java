package com.example.dtse.slice.ViewHolder;

import com.chg.ultimateprovider.EventTransmissionListener;
import com.chg.ultimateprovider.UltimateProvider;
import com.chg.ultimateprovider.ViewHolder;
import com.example.dtse.ResourceTable;
import com.example.dtse.slice.model.NestedSongModel;
import ohos.agp.components.Component;
import ohos.agp.components.DirectionalLayout;
import ohos.agp.components.Image;
import ohos.agp.components.Text;
import ohos.agp.window.dialog.ToastDialog;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

public class NestedSongViewHolder extends ViewHolder<NestedSongModel> {
    private static final HiLogLabel LABEL_LOG = new HiLogLabel(3, 0xD001100, "Demo");

    private Image icon;
    private Text songName;
    private Text songer;
    private DirectionalLayout song_item_layout;

    public NestedSongViewHolder(EventTransmissionListener eventTransmissionListener, Component component, UltimateProvider provider) {
        super(eventTransmissionListener, component,provider);
        HiLog.error(LABEL_LOG,"viewHolder.getComponent()=="+component.getId());
        icon = (Image) findComponentById(ResourceTable.Id_icon);
        songName = (Text) findComponentById(ResourceTable.Id_songName);
        songer = (Text) findComponentById(ResourceTable.Id_songer);
   //     song_item_layout= (DirectionalLayout) findComponentById(ResourceTable.Id_album_item_layout);
        icon.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
               /* ToastDialog toastDialog=new ToastDialog(getContext());
                toastDialog.setText("NestedSongViewHolder").show();*/
               if(getEventTransmissionListener()==null){
                   HiLog.error(LABEL_LOG,"getEventTransmissionListener==null");
               }else {
                   getEventTransmissionListener().onEventTransmission(NestedSongViewHolder.this, "NestedSongViewHolder", 1, new EventTransmissionListener.CallBack() {
                       @Override
                       public Object callBack(Object object) {
                           return null;
                       }
                   });
               }
            }
        });
    }

    @Override
    public void onDataBound() {
        songName.setText(getModel().getName());
        songer.setText(getModel().getSinger());
    }
}
