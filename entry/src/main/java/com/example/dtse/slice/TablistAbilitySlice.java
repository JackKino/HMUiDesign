package com.example.dtse.slice;

import com.example.dtse.ResourceTable;
import com.example.dtse.TablistAbility;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Component;
import ohos.agp.components.ComponentContainer;
import ohos.agp.components.TabList;
import ohos.agp.utils.LayoutAlignment;
import ohos.agp.window.dialog.ToastDialog;

public class TablistAbilitySlice extends AbilitySlice {
    private  String[] str={"Image","Video","Audio","HuaweiShare","News","NBA","Documentary"};
    private TabList tabList;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_tablist);
        tabList= (TabList) findComponentById(ResourceTable.Id_tab_list);
        initData();

    /*    // 本示例中在"图片"和"视频"之间的页签中新增"新闻"页签
        TabList.Tab tab = createTab("News");
        tabList.addTab(tab, 1); // 1表示位置*/


        tabList.addTabSelectedListener(new TabList.TabSelectedListener() {
            @Override
            public void onSelected(TabList.Tab tab) {
                // 当某个Tab从未选中状态变为选中状态时的回调

                new ToastDialog(getContext())
                        .setText("从未选中状态变为选中状态时的回调")
                        .setAlignment(LayoutAlignment.CENTER)
                        .show();
            }

            @Override
            public void onUnselected(TabList.Tab tab) {
                // 当某个Tab从选中状态变为未选中状态时的回调
                new ToastDialog(getContext())
                        .setText("从选中状态变为未选中状态时的回调")
                        .setAlignment(LayoutAlignment.CENTER)
                        .show();
            }

            @Override
            public void onReselected(TabList.Tab tab) {
                // 当某个Tab已处于选中状态，再次被点击时的状态回调
                new ToastDialog(getContext())
                        .setText("已处于选中状态，再次被点击时的状态回调")
                        .setAlignment(LayoutAlignment.CENTER)
                        .show();
            }
        });
    }

    public  void initData(){
        for (int i = 0; i < str.length; i++) {
            TabList.Tab tab = tabList.new Tab(getContext());
            tab.setText(str[i]);
            tabList.addTab(tab);
        }
        tabList.setTabLength(ComponentContainer.LayoutConfig.MATCH_CONTENT); // 设置Tab的宽度
        tabList.setTabMargin(30); // 设置两个Tab之间的间距
        tabList.setFixedMode(false);
    }

    // 创建Tab并设置Tab
    private TabList.Tab createTab(String name) {
        TabList.Tab tab = tabList.new Tab(this);
        tab.setText(name);
        tab.setName(name);
        tab.setMinWidth(64);
        tab.setPadding(12, 0, 12, 0);
        return tab;
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
