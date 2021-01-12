package com.example.dtse.slice;

import com.example.dtse.ResourceTable;
import com.example.dtse.slice.ViewHolder.ListUserProvider;
import com.example.dtse.slice.model.AlbumModel;
import com.example.dtse.slice.model.SongModel;
import com.example.dtse.slice.model.UserModel;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.ListContainer;

import java.util.ArrayList;
import java.util.List;

public class MultListAbilitySlice extends AbilitySlice {
    private ListContainer lc_container;
    private ListUserProvider listUserProvider;
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_mult_list);
        lc_container= (ListContainer) findComponentById(ResourceTable.Id_lc_container);
        listUserProvider=new ListUserProvider(this,this);
        lc_container.setItemProvider(listUserProvider);
        listUserProvider.setDataMos(getData());
    }

    List getData() {
        List list = new ArrayList();
        for (int i=0; i<100; i++) {
           UserModel userModel= new UserModel();
           userModel.setId(i);
            userModel.setAge(10+i);
            userModel.setName("Jack"+i);
          //  userModel.setPhoneNumbers("10086+"+i);
             list.add(userModel);
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
