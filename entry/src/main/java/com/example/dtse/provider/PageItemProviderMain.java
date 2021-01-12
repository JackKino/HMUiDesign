package com.example.dtse.provider;

import ohos.aafwk.ability.AbilitySlice;
import ohos.agp.components.Component;
import ohos.agp.components.ComponentContainer;
import ohos.agp.components.DirectionalLayout;
import ohos.agp.components.PageSliderProvider;

import java.util.ArrayList;
import java.util.Optional;

public class PageItemProviderMain extends PageSliderProvider {

    private AbilitySlice context;

    public PageItemProviderMain(ArrayList<DirectionalLayout> slices, AbilitySlice context) {
        this.slices = slices;
        this.context=context;
    }

    ArrayList<DirectionalLayout> slices=new ArrayList<>();

    @Override
    public int getCount() {
        return slices.size();
    }


    @Override
    public Object createPageInContainer(ComponentContainer componentContainer, int i) {
        if (i >= slices.size() || componentContainer == null) {
            return Optional.empty();
        }
        DirectionalLayout item = slices.get(i);
        componentContainer.addComponent(item);
        return item;
    }



    @Override
    public void destroyPageFromContainer(ComponentContainer componentContainer, int i, Object o) {
        if (i >= slices.size() || componentContainer == null) {
            return;
        }
        componentContainer.removeComponent((DirectionalLayout)o);
        return;
    }

    @Override
    public boolean isPageMatchToObject(Component component, Object o) {
        return component==o;
    }
}
