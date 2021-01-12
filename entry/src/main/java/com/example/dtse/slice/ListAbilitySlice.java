package com.example.dtse.slice;

import com.chg.ultimateprovider.UltimateProvider;
import com.example.dtse.ResourceTable;
import com.example.dtse.slice.model.MenuItem;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.Component;
import ohos.agp.components.ListContainer;

import java.util.ArrayList;
import java.util.List;

public class ListAbilitySlice extends AbilitySlice {
    private ListContainer listContainer;
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_list);
        listContainer = (ListContainer) findComponentById(ResourceTable.Id_listContainer);
        listContainer.setItemProvider(new UltimateProvider(funcs(),getContext()));

        /*设置点击事件*/
        listContainer.setItemClickedListener(new ListContainer.ItemClickedListener() {
            @Override
            public void onItemClicked(ListContainer listContainer, Component component, int i, long l) {
                MenuItem menuItem = (MenuItem) listContainer.getItemProvider().getItem(i);
                Intent secondIntent = new Intent();
                // 指定待启动FA的bundleName和abilityName
                Operation operation = new Intent.OperationBuilder()
                        .withDeviceId("")
                        .withBundleName("com.example.dtse")
                        .withAbilityName(menuItem.getAbilityName())
                        .build();
                secondIntent.setOperation(operation);
                startAbility(secondIntent);
            }
        });
    }

    /*创建功能列表*/
    private List funcs() {
        List list = new ArrayList();
        list.add(new MenuItem("简单的显示（显示一种布局）","最基础使用","com.example.dtse.SongAbility"));
        list.add(new MenuItem("简单的显示（显示多种布局）","最基础使用","com.example.dtse.RecommendAbility"));
        list.add(new MenuItem("嵌套ListContainer","最基础使用","com.example.dtse.NestedListAbility"));
        list.add(new MenuItem("ItemView中的按钮点击、等事件","最基础使用","com.example.dtse.EventHanlderAbility"));
        list.add(new MenuItem("provider设置自定义数据","provider设置自定义数据，方便在ItemView之间以及Activity中共享数据","com.chg.ultimateproviderdemo.Menu.Ability.CustomDataUserAbility"));
        list.add(new MenuItem("多列的ListView","UltimateProvider","com.example.dtse.MulitSongAbility"));
        list.add(new MenuItem("多列的ListView","最基础使用","com.example.dtse.MultListAbility"));
        list.add(new MenuItem("多列嵌套ListContainer","最基础使用","com.example.dtse.NestedMulitListAbility"));
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
