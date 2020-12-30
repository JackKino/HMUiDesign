package com.example.dtse;

import com.example.dtse.slice.MulitSongAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class MulitSongAbility extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(MulitSongAbilitySlice.class.getName());
    }
}
