package com.example.mymodule;

import com.example.mymodule.slice.MatAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class MatAbility extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(MatAbilitySlice.class.getName());
    }
}
