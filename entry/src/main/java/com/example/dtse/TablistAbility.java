package com.example.dtse;

import com.example.dtse.slice.TablistAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class TablistAbility extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(TablistAbilitySlice.class.getName());
    }
}
