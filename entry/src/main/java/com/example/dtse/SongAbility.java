package com.example.dtse;

import com.example.dtse.slice.SongAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class SongAbility extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(SongAbilitySlice.class.getName());
    }
}
