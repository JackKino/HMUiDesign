package com.example.dtse.slice;

import com.chg.ultimateprovider.EventTransmissionListener;
import com.chg.ultimateprovider.UltimateProvider2;
import com.example.dtse.ResourceTable;
import com.example.dtse.slice.ViewHolder.MultListSongProvider;
import com.example.dtse.slice.ViewHolder.MultSongProvider;
import com.example.dtse.slice.ViewHolder.NewSongViewHolder;
import com.example.dtse.slice.model.NewSongModel;
import com.example.dtse.slice.model.SongData;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Component;
import ohos.agp.components.ComponentTreeObserver;
import ohos.agp.components.ListContainer;
import ohos.agp.components.ScrollHelper;
import ohos.agp.utils.Color;
import ohos.agp.utils.Point;
import ohos.agp.window.service.Display;
import ohos.agp.window.service.DisplayManager;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MulitSongAbilitySlice extends AbilitySlice implements MultSongProvider.onItemLongClickListener {
    private static final HiLogLabel LABEL_LOG = new HiLogLabel(3, 0xD001100, "Demo");
    private MultSongProvider multSongProvider;
    private UltimateProvider2 ultimateProvider2;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_mulit_song);
        listContainer = (ListContainer) findComponentById(ResourceTable.Id_listContainer);
        listContainer.enableScrollBar(Component.AXIS_Y, true);

        ultimateProvider2 = new UltimateProvider2(getSongs(), this);
        ultimateProvider2.setEventTransmissionListener(new EventTransmissionListener() {
            @Override
            public Object onEventTransmission(Object target, Object params, int eventId, CallBack callBack) {
                if (target instanceof NewSongViewHolder) {
                    if (eventId == 1) {//播放列表中播放被点击，演示同步返回数据
                        SongData songModel = (SongData) params;
                        HiLog.error(LABEL_LOG, "name==" + songModel.getName() + " isChecked==" + songModel.isChecked());

                    }
                }
                return null;
            }
        });
        listContainer.setItemProvider(ultimateProvider2);

      /*
        multSongProvider=new MultSongProvider(this,this,this);
      listContainer.setItemProvider(multSongProvider);
        multSongProvider.setDataMos(getSongs());
        listContainer.setScrolledListener(new Component.ScrolledListener() {
            @Override
            public void onContentScrolled(Component component, int i, int i1, int i2, int i3) {
               // HiLog.error(LABEL_LOG,"i=="+ i+"   i1=="+ i1+"   i2=="+ i2+"   i3=="+ i3);
            }
        });
        listContainer.getComponentTreeObserver().addTreeLayoutChangedListener(new ComponentTreeObserver.GlobalLayoutListener() {
            @Override
            public void onGlobalLayoutUpdated() {
                HiLog.error(LABEL_LOG,"onGlobalLayoutUpdated");
                listContainer.getComponentTreeObserver().removeTreeLayoutChangedListener(this);
                listContainer.getWidth(); // 获取宽度
                HiLog.error(LABEL_LOG,"   listContainer.getWidth222()=="+ listContainer.getWidth());
            }

        });
        listContainer.getComponentTreeObserver().addGlobalFocusUpdatedListener(new ComponentTreeObserver.GlobalFocusUpdatedListener() {
            @Override
            public void onGlobalFocusUpdated(Component component, Component component1) {
                HiLog.error(LABEL_LOG,"   onGlobalFocusUpdated");
            }
        });*/

    }


    private ListContainer listContainer;


    List getSongs() {
        HiLog.error(LABEL_LOG, "getSongs==");
        List list = new ArrayList();
        List<SongData> list2 = new ArrayList();
        List<NewSongModel> list3 = new ArrayList();
        for (int i = 0; i < 100; i++) {
            list.add(new SongData("歌曲名称：" + i, "歌手：" + i));
        }
        int i = 0;
        int j = 0;
        for (Object data : list) {
            if (i == 2) {
                i = 0;
                list3.add(new NewSongModel(list2, "歌曲" + j));
                j++;
                list2 = new ArrayList<>();

            }
            list2.add((SongData) data);
            i++;
        }
        list3.add(new NewSongModel(list2, "歌曲" + j));
        HiLog.error(LABEL_LOG, "list3==" + list3.size());
        return list3;
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }

    @Override
    public void onItemClick(SongData songModel, int position) {
        HiLog.error(LABEL_LOG, "name==" + songModel.getName() + " isChecked==" + songModel.isChecked() + "  position==" + position);
    }
}
