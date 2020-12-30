package com.example.dtse;

import com.example.dtse.slice.SurfaceProviderAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class SurfaceProviderAbility extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(SurfaceProviderAbilitySlice.class.getName());
    }
}
