package com.example.dtse.view;

import com.example.dtse.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.*;
import ohos.agp.components.surfaceprovider.SurfaceProvider;
import ohos.agp.utils.LayoutAlignment;
import ohos.agp.window.dialog.ListDialog;
import ohos.app.Context;

public class MainView   extends DirectionalLayout implements Component.ClickedListener{
    private AbilitySlice slice;
    private Button text_helloworld;
    private SurfaceProvider videoprovider;
    private Button tablist_btn;
    private Button list_btn;
    private Button listdialog_btn;
    private static DirectionalLayout root;
    public MainView(AbilitySlice slice) {
        super(slice);
        this.slice = slice;
        initView();

    }

    private void initView() {
        root = (DirectionalLayout) LayoutScatter.getInstance(slice).parse(ResourceTable.Layout_home_layout01, null, false);
        text_helloworld= (Button)root.findComponentById(ResourceTable.Id_text_helloworld);
        tablist_btn= (Button) root.findComponentById(ResourceTable.Id_tablist_btn);
        list_btn= (Button) root.findComponentById(ResourceTable.Id_list_btn);
        listdialog_btn= (Button) root.findComponentById(ResourceTable.Id_listdialog_btn);

        text_helloworld.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                Intent intent=new Intent();
                Operation operation = new Intent.OperationBuilder()
                        .withBundleName("com.example.dtse")
                        .withAbilityName("com.example.dtse.SurfaceProviderAbility")
                        .build();
                intent.setOperation(operation);
                slice.startAbility(intent);
            }
        });
        tablist_btn.setClickedListener(this);
        list_btn.setClickedListener(this);
        listdialog_btn.setClickedListener(this);
    }
    @Override
    public void onClick(Component component) {
        switch (component.getId()){
            case ResourceTable.Id_tablist_btn:
                Intent intent=new Intent();
                Operation operation = new Intent.OperationBuilder()
                        .withBundleName("com.example.dtse")
                        .withAbilityName("com.example.dtse.TablistAbility")
                        .build();
                intent.setOperation(operation);
                slice.startAbility(intent);

                break;
            case ResourceTable.Id_list_btn:
                Intent intent2=new Intent();
                Operation operation2 = new Intent.OperationBuilder()
                        .withBundleName("com.example.dtse")
                        .withAbilityName("com.example.dtse.ListAbility")
                        .build();
                intent2.setOperation(operation2);
                slice.startAbility(intent2);
                break;
            case ResourceTable.Id_listdialog_btn:
                ListDialog listDialog=new ListDialog(getContext(),ListDialog.MULTI);
                // listDialog.setItems(getStr());
                listDialog.setMultiSelectItems(getStr(),getboolean());
                listDialog.setSwipeToDismiss(false);
                listDialog.setAlignment(LayoutAlignment.HORIZONTAL_CENTER);
                listDialog.setMovable(true);
                listDialog.setAutoClosable(true);
                listDialog.setTitleText("ListDialog");
                listDialog.show();
                break;
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
    public DirectionalLayout getRootView() {
        return root;
    }
}
