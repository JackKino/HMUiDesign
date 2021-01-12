package com.example.dtse.view;

import com.example.dtse.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.agp.components.DirectionalLayout;
import ohos.agp.components.LayoutScatter;
import ohos.agp.components.surfaceprovider.SurfaceProvider;
import ohos.agp.utils.LayoutAlignment;
import ohos.agp.window.dialog.ListDialog;

public class GalleryView extends DirectionalLayout implements Component.ClickedListener{
    private AbilitySlice slice;
    private Button text_helloworld;
    private SurfaceProvider videoprovider;
    private Button tablist_btn;
    private Button list_btn;
    private Button listdialog_btn;
    private static DirectionalLayout root;
    public GalleryView(AbilitySlice slice) {
        super(slice);
        this.slice = slice;
        initView();

    }

    private void initView() {
        root = (DirectionalLayout) LayoutScatter.getInstance(slice).parse(ResourceTable.Layout_gallery_layout, null, false);

    }
    @Override
    public void onClick(Component component) {
        switch (component.getId()){

        }
    }




    public DirectionalLayout getRootView() {
        return root;
    }
}
