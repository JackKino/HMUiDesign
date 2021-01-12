package com.example.dtse;

import com.example.dtse.slice.MultListAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class MultListAbility extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(MultListAbilitySlice.class.getName());
    }
}
