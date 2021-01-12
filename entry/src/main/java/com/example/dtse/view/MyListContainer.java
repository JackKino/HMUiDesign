package com.example.dtse.view;

import ohos.agp.components.AttrSet;
import ohos.agp.components.Component;
import ohos.agp.components.DragEvent;
import ohos.agp.components.ListContainer;
import ohos.app.Context;

public class MyListContainer extends ListContainer {
    public MyListContainer(Context context) {
        super(context);
    }

    public MyListContainer(Context context, AttrSet attrSet) {
        super(context, attrSet);
    }

    public MyListContainer(Context context, AttrSet attrSet, String styleName) {
        super(context, attrSet, styleName);
    }

    @Override
    public void postLayout() {
        super.postLayout();
    }


    /**
     * 设置listView不可滑动
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
   /* @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }*/


    @Override
    public boolean onDrag(Component component, DragEvent event) {
        return super.onDrag(component, event);
    }

}
