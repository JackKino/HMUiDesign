package com.example.dtse.provider;

import com.chg.ultimateprovider.UltimateProvider2;
import com.example.dtse.slice.model.MulitListModel;
import ohos.app.Context;

import java.util.List;

public class NestedMulitListProvider<MulitListModel>  extends UltimateProvider2 {
    /**
     * 构造方法
     *
     * @param models  模型数据
     * @param context context
     */
    public NestedMulitListProvider(List<MulitListModel> models, Context context) {
        super(models, context);
    }

    @Override
    public List<MulitListModel> getModels() {
        return super.getModels();
    }
}
