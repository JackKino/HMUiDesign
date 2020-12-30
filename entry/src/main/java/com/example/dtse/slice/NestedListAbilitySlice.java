package com.example.dtse.slice;

import com.chg.ultimateprovider.EventTransmissionListener;
import com.chg.ultimateprovider.Model;
import com.chg.ultimateprovider.UltimateProvider;
import com.example.dtse.ResourceTable;
import com.example.dtse.slice.ViewHolder.NestedAlbumViewHolder;
import com.example.dtse.slice.ViewHolder.NestedSongViewHolder;
import com.example.dtse.slice.model.*;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.ListContainer;
import ohos.agp.window.dialog.ToastDialog;

import java.util.ArrayList;
import java.util.List;

public class NestedListAbilitySlice extends AbilitySlice {
    private ListContainer listContainer;

    @Override
    protected void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_nested_list);
        listContainer = (ListContainer) findComponentById(ResourceTable.Id_listContainer);
        UltimateProvider ultimateProvider = new UltimateProvider<Model>(getData(), getContext());

        /*这里使用匿名内部类实现，也可以让Slice实现接口EventTransmissionListener*/
        ultimateProvider.setEventTransmissionListener(new EventTransmissionListener() {
            @Override
            public Object onEventTransmission(Object target, Object params, int eventId, CallBack callBack) {
                if (target instanceof NestedAlbumViewHolder){
                    if (eventId == 1) {//播放列表中播放被点击，演示同步返回数据
                        ToastDialog toastDialog=new ToastDialog(getContext());
                        toastDialog.setText((String) params).show();
                        return 1;
                    }
                }else    if (target instanceof NestedSongViewHolder){
                    if (eventId == 1) {//播放列表中播放被点击，演示同步返回数据
                        ToastDialog toastDialog=new ToastDialog(getContext());
                        toastDialog.setText((String) params).show();
                        return 1;
                    }
                }
                return null;
            }
        });
        listContainer.setItemProvider(ultimateProvider);
    }

    List getData() {
        List list = new ArrayList();
        for (int i=0; i<100; i++) {
            if (i%5 == 0) {
                list.add(new SongModel("歌曲名称："+i,"歌手："+i));
            } else if (i%5 == 1) {
                list.add(new AlbumModel("推荐专辑："+i,"歌手："+i));
            } else if (i%5 == 2) {
                list.add(creageMusicData(i,i%2));
            } else {
                list.add(creageHybridData());
            }
        }
        return list;
    }

    /*创建推荐内容*/
    MusicModel creageMusicData(int position, int type){
        List list = new ArrayList();
        String title = type == 0 ? "推荐歌曲":"推荐专辑";
        for (int i=0; i< 100; i++) {
            if (type == 0) {
                list.add(new NestedSongModel("推荐歌曲："+i,"歌手："+i));
            } else if(type == 1){
                list.add(new NestedAlbumModel("推荐专辑："+i,"歌手："+i));
            }
        }
        return new MusicModel(title + position,list);
    }

    /*创建推荐内容*/
    MusicModel creageHybridData(){
        List list = new ArrayList();

        for (int i=0; i< 100; i++) {
            if (i %2 == 0) {
                list.add(new NestedSongModel("推荐歌曲："+i,"歌手："+i));
            } else {
                list.add(new NestedAlbumModel("推荐专辑："+i,"歌手："+i));
            }
        }
        return new MusicModel("推荐的歌曲+专辑",list);
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
