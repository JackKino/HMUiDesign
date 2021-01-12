package com.example.dtse;

import com.example.dtse.slice.NestedMulitListAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class NestedMulitListAbility extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(NestedMulitListAbilitySlice.class.getName());
    }
}
