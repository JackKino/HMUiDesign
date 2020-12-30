package com.example.dtse;

import com.example.dtse.slice.NestedListAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class NestedListAbility extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(NestedListAbilitySlice.class.getName());
    }
}
