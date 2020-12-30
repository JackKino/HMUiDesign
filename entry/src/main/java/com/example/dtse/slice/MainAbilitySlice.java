package com.example.dtse.slice;

import com.example.dtse.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.agp.components.surfaceprovider.SurfaceProvider;
import ohos.media.common.Source;
import ohos.media.player.Player;

import java.io.*;

public class MainAbilitySlice extends AbilitySlice implements Component.ClickedListener{
    private Button text_helloworld;
    private SurfaceProvider videoprovider;
    private Button tablist_btn;
    private Button list_btn;
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);
        text_helloworld= (Button) findComponentById(ResourceTable.Id_text_helloworld);
        tablist_btn= (Button) findComponentById(ResourceTable.Id_tablist_btn);
        list_btn= (Button) findComponentById(ResourceTable.Id_list_btn);
        text_helloworld.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                Intent intent=new Intent();
                Operation operation = new Intent.OperationBuilder()
                        .withBundleName("com.example.dtse")
                        .withAbilityName("com.example.dtse.SurfaceProviderAbility")
                        .build();
                intent.setOperation(operation);
                startAbility(intent);
            }
        });
        tablist_btn.setClickedListener(this);
        list_btn.setClickedListener(this);

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
        switch (component.getId()){
            case ResourceTable.Id_tablist_btn:
                Intent intent=new Intent();
                Operation operation = new Intent.OperationBuilder()
                        .withBundleName("com.example.dtse")
                        .withAbilityName("com.example.dtse.TablistAbility")
                        .build();
                intent.setOperation(operation);
                startAbility(intent);

                break;
            case ResourceTable.Id_list_btn:
                Intent intent2=new Intent();
                Operation operation2 = new Intent.OperationBuilder()
                        .withBundleName("com.example.dtse")
                        .withAbilityName("com.example.dtse.ListAbility")
                        .build();
                intent2.setOperation(operation2);
                startAbility(intent2);
                break;
        }
    }
}
