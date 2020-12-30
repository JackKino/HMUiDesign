package com.example.dtse.slice;

import com.chg.ultimateprovider.UltimateProvider;
import com.example.dtse.ResourceTable;
import com.example.dtse.slice.model.NewSongModel;
import com.example.dtse.slice.model.SongData;
import com.example.dtse.slice.model.SongModel;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.ListContainer;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

import java.util.ArrayList;
import java.util.List;

public class MulitSongAbilitySlice extends AbilitySlice {
    private static final HiLogLabel LABEL_LOG = new HiLogLabel(3, 0xD001100, "Demo");

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_mulit_song);
        listContainer = (ListContainer) findComponentById(ResourceTable.Id_listContainer);
        getSongs();
        listContainer.setItemProvider(new UltimateProvider(getSongs(),getContext()));
    }

    private ListContainer listContainer;


    List getSongs(){
        HiLog.error(LABEL_LOG,"getSongs==");
        List list = new ArrayList();
        List<SongData> list2 = new ArrayList();
        List<NewSongModel> list3 = new ArrayList();
        for (int i=0; i<100; i++) {
            list.add(new SongData("歌曲名称："+i,"歌手："+i));
        }

        int i=0;
        for (Object data:list) {
            if (i==3){
                i=0;
                list3.add(new NewSongModel(list2));
                list2=new ArrayList<>();

            }
            list2.add((SongData) data);
            i++;
        }
        list3.add(new NewSongModel(list2));
        HiLog.error(LABEL_LOG,"list3=="+list3.size());
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
}
