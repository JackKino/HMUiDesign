package com.example.dtse.slice;

import com.chg.ultimateprovider.Model;
import com.chg.ultimateprovider.UltimateProvider;
import com.example.dtse.ResourceTable;
import com.example.dtse.slice.model.AlbumModel;
import com.example.dtse.slice.model.SongModel;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.ListContainer;

import java.util.ArrayList;
import java.util.List;

public class RecommendAbilitySlice extends AbilitySlice {
    private ListContainer listContainer;

    @Override
    protected void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_recommend);
        listContainer = (ListContainer) findComponentById(ResourceTable.Id_listContainer);
        listContainer.setItemProvider(new UltimateProvider<Model>(getData(),getContext()));
    }

    List getData(){
        List list = new ArrayList();
        for (int i=0; i<100; i++) {
            if (i %2 == 0) {
                list.add(new SongModel("歌曲名称："+i,"歌手名称:"+i));
            } else {
                list.add(new AlbumModel("专辑名称："+i,"歌手"));
            }
        }
        return list;
    }

}
