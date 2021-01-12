package com.example.dtse.slice;

import com.example.dtse.ResourceTable;
import com.example.dtse.provider.PageItemProviderMain;
import com.example.dtse.slice.model.SongData;
import com.example.dtse.view.GalleryView;
import com.example.dtse.view.MainView;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.*;
import ohos.agp.components.element.VectorElement;
import ohos.agp.components.surfaceprovider.SurfaceProvider;
import ohos.agp.utils.LayoutAlignment;
import ohos.agp.window.dialog.ListDialog;
import ohos.agp.window.dialog.ToastDialog;
import ohos.media.common.Source;
import ohos.media.player.Player;

import java.io.*;
import java.util.ArrayList;

public class MainAbilitySlice extends AbilitySlice implements Component.ClickedListener{
    private  String[] str={"MainView","Gallery"};
    private TabList tabList;
    private PageSlider pageSlider;
    private MainView mainView;
    private GalleryView galleryView;
    private ArrayList<DirectionalLayout> layouts=new ArrayList<>();
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);
        tabList= (TabList) findComponentById(ResourceTable.Id_tab_list);
        pageSlider = (PageSlider) findComponentById(ResourceTable.Id_ps_main);
        initData();
        tabList.addTabSelectedListener(new TabList.TabSelectedListener() {
            @Override
            public void onSelected(TabList.Tab tab) {
                // 当某个Tab从未选中状态变为选中状态时的回调
                if(tab.getPosition()==0) {
                    pageSlider.setCurrentPage(0);
                    tab.setAroundElements(null, new VectorElement(getContext(), ResourceTable.Graphic_find), null, null);
                }else if(tab.getPosition()==1){
                    pageSlider.setCurrentPage(1);
                    tab.setAroundElements(null, new VectorElement(getContext(), ResourceTable.Graphic_xiangce), null, null);

                }

            }

            @Override
            public void onUnselected(TabList.Tab tab) {
                // 当某个Tab从选中状态变为未选中状态时的回调
                if(tab.getPosition()==0) {
                    tab.setAroundElements(null, new VectorElement(getContext(), ResourceTable.Graphic_find_hui), null, null);
                }else if(tab.getPosition()==1){
                    tab.setAroundElements(null, new VectorElement(getContext(), ResourceTable.Graphic_xiangce_hui), null, null);

                }

            }

            @Override
            public void onReselected(TabList.Tab tab) {
                // 当某个Tab已处于选中状态，再次被点击时的状态回调

            }
        });

        mainView=new MainView(this);
        galleryView=new GalleryView(this);
        layouts.add(mainView.getRootView());
        layouts.add(galleryView.getRootView());
        pageSlider.setProvider(new PageItemProviderMain(layouts,this));
        pageSlider.setSlidingPossible(false);//设置pageslider不可滑动
       /* pageSlider.addPageChangedListener(new PageSlider.PageChangedListener() {
            @Override
            public void onPageSliding(int i, float v, int i1) {
            }

            @Override
            public void onPageSlideStateChanged(int i) {
            }

            @Override
            public void onPageChosen(int i) {

                switch (i){

                }
            }
        });*/
    }

    public  void initData() {
        for (int i = 0; i < str.length; i++) {
            TabList.Tab tab = tabList.new Tab(getContext());
            tab.setText(str[i]);
            tab.setHeight(ComponentContainer.LayoutConfig.MATCH_CONTENT);
            if (i == 0) {
                tab.setAroundElements(null, new VectorElement(this, ResourceTable.Graphic_find), null, null);
            } else {
                tab.setAroundElements(null, new VectorElement(this, ResourceTable.Graphic_xiangce_hui), null, null);

            }
            tabList.addTab(tab);
        }
        tabList.setTabLength(ComponentContainer.LayoutConfig.MATCH_CONTENT); // 设置Tab的宽度
        tabList.setTabMargin(30); // 设置两个Tab之间的间距
        tabList.setFixedMode(true);
        tabList.selectTabAt(0);
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
    public void onClick(Component component) {
        switch (component.getId()) {
        }
    }

    String[] getStr(){
        String[] strings=new String[10];
        for (int i=0; i<10; i++) {
            strings[i]="Jack"+i;
        }
        return strings;

    }

    boolean[] getboolean(){
        boolean[] booleans=new boolean[10];
        for (int i=0; i<10; i++) {
            if(i%2==0){
                booleans[i]=false;
            }else{
                booleans[i]=true;
            }

        }
        return booleans;

    }
}
