package com.example.dtse;

import com.example.dtse.slice.EventHanlderAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class EventHanlderAbility extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(EventHanlderAbilitySlice.class.getName());
    }
}
