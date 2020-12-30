package com.example.dtse;

import com.example.dtse.slice.RecommendAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class RecommendAbility extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(RecommendAbilitySlice.class.getName());
    }
}
