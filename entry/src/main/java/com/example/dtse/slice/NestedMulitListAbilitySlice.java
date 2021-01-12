package com.example.dtse.slice;

import com.chg.ultimateprovider.*;
import com.example.dtse.ResourceTable;
import com.example.dtse.provider.NestedMulitListProvider;
import com.example.dtse.slice.ViewHolder.*;
import com.example.dtse.slice.model.MulitListModel;
import com.example.dtse.slice.model.NewSongModel;
import com.example.dtse.slice.model.SongData;
import com.google.gson.Gson;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.agp.components.ListContainer;
import ohos.agp.components.Text;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

import java.util.ArrayList;
import java.util.List;

public class NestedMulitListAbilitySlice extends AbilitySlice {
    private ListContainer listContainer;
    private static final HiLogLabel LABEL_LOG = new HiLogLabel(3, 0xD001100, "Demo");
    private NestedMulitListProvider<MulitListModel> ultimateProvider;
    private Button customdata;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_nested_mulit_list);
        listContainer = (ListContainer) findComponentById(ResourceTable.Id_listContainer);
        customdata= (Button) findComponentById(ResourceTable.Id_customdata);
        ultimateProvider = new NestedMulitListProvider<MulitListModel>(getData(), getContext());
        listContainer.enableScrollBar(Component.AXIS_Y, true);
        /*这里使用匿名内部类实现，也可以让Slice实现接口EventTransmissionListener*/
      //  ultimateProvider.setCustomData(getData());
        ultimateProvider.setEventTransmissionListener(new EventTransmissionListener() {
            @Override
            public Object onEventTransmission(Object target, Object params, int eventId, CallBack callBack) {
                if (target instanceof MulitListHolder){
                    if (eventId == 1) {//播放列表中播放被点击，演示同步返回数据
                        NewSongModel songModel= (NewSongModel) params;
                      //  HiLog.error(LABEL_LOG,"name=="+songModel.getName());

                    }
                }else  if (target instanceof MultListSongProvider) {
                    if (eventId == 1) {//播放列表中播放被点击，演示同步返回数据
                        SongData songModel = (SongData) params;
                        HiLog.error(LABEL_LOG, "name==" + songModel.getName() + " isChecked==" + songModel.isChecked());
                    }else  if (eventId == 2) {//播放列表中播放被点击，演示同步返回数据
                        boolean isShow = (boolean) params;
                        HiLog.error(LABEL_LOG, "isShow=="+!isShow);
                        ultimateProvider.setShowChecked(!isShow);
                    }
                }
                return null;
            }
        });
        listContainer.setItemProvider(ultimateProvider);

        customdata.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                HiLog.error(LABEL_LOG,"checkdatas size=="+getCheckedDatas().size()+" checkdatas=="+new Gson().toJson(getCheckedDatas()));

            }
        });

    }

    List getData(){
        List<MulitListModel> mulitListModels=new ArrayList<>();
        for(int x=0;x<20;x++) {
            MulitListModel mulitListModel=new MulitListModel();
            List list = new ArrayList();
            List<SongData> list2 = new ArrayList();
            List<NewSongModel> list3 = new ArrayList();
            for (int i = 0; i < x+3; i++) {
                list.add(new SongData("歌曲名称：" + x+""+i, "歌手：" + x+""+i));
            }

            int i = 0;
            int j = 0;
            for (Object data : list) {
                if (i == 3) {
                    i = 0;
                    list3.add(new NewSongModel(list2, "歌曲" + x+" "+j));
                    j++;
                    list2 = new ArrayList<>();

                }
                list2.add((SongData) data);
                i++;
            }
            list3.add(new NewSongModel(list2, "歌曲" + j));
            mulitListModel.setData(list3);

            if(x+7<10) {
                mulitListModel.setTitle("2021-01-0" +(x+7));
            }else{
                mulitListModel.setTitle("2021-01-" +(x+7));
            }
            mulitListModels.add(mulitListModel);

        }
        HiLog.error(LABEL_LOG,"mulitListModels=="+mulitListModels.size());
        return mulitListModels;
    }
    List getData2(){
        List<MulitListModel> mulitListModels=new ArrayList<>();
        for(int x=0;x<20;x++) {
            MulitListModel mulitListModel=new MulitListModel();
            List<NewSongModel> list3 = new ArrayList();
            for(int j=0;j<1+x;j++) {
                list3.add(new NewSongModel("歌曲" + x+" "+j));
            }
            mulitListModel.setData(list3);
            if(x+7<10) {
                mulitListModel.setTitle("2021-01-0" +(x+7));
            }else{
                mulitListModel.setTitle("2021-01-" +(x+7));
            }
            mulitListModels.add(mulitListModel);

        }
        HiLog.error(LABEL_LOG,"mulitListModels=="+mulitListModels.size());
        return mulitListModels;
    }
    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }
    public List<SongData> getCheckedDatas(){
        List<SongData> checkdatas=new ArrayList<>();
        List<MulitListModel> mulitListModels= (List<MulitListModel>) ultimateProvider.getModels();
        HiLog.error(LABEL_LOG,"getCheckedDatas=="+mulitListModels.size());
        for(MulitListModel mulitListModel:mulitListModels){
            for (int j = 0; j <mulitListModel.getData().size(); j++) {
                NewSongModel newSongModel= (NewSongModel) mulitListModel.getData().get(j);
                for (int i = 0; i <newSongModel.getSongModels().size(); i++) {
                    SongData songData=newSongModel.getSongModels().get(i);
                    if(songData.isChecked()){
                        checkdatas.add(songData);
                    }
                }
            }
        }
        return checkdatas;
    }

}
