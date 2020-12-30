package com.example.dtse.slice;

import com.chg.ultimateprovider.UltimateProvider;
import com.example.dtse.ResourceTable;
import com.example.dtse.slice.model.SongModel;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.ListContainer;

import java.util.ArrayList;
import java.util.List;

public class SongAbilitySlice extends AbilitySlice {
    private ListContainer listContainer;
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_song);
        listContainer = (ListContainer) findComponentById(ResourceTable.Id_listContainer);
        listContainer.setItemProvider(new UltimateProvider(getSongs(),getContext()));


    }

    List getSongs(){
        List list = new ArrayList();
        for (int i=0; i<100; i++) {
            list.add(new SongModel("歌曲名称："+i,"歌手："+i));
        }
        return list;
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
