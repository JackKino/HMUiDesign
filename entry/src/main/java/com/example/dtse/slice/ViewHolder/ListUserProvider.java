package com.example.dtse.slice.ViewHolder;


import com.example.dtse.ResourceTable;
import com.example.dtse.slice.model.UserModel;
import com.example.dtse.slice.model.UsersModel;

import ohos.aafwk.ability.AbilitySlice;
import ohos.agp.components.*;
import ohos.app.Context;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

import java.util.ArrayList;
import java.util.List;


public class ListUserProvider extends RecycleItemProvider {
    private static final HiLogLabel LABEL_LOG = new HiLogLabel(3, 0xD001100, "Demo");

    private List<UsersModel> datas=new ArrayList<>();
    private AbilitySlice mSlice;
    LayoutScatter layoutScatter;
    public ListUserProvider(AbilitySlice mSlice, Context context) {
        this.mSlice = mSlice;
        this.layoutScatter = LayoutScatter.getInstance(context);
    }

    public void setDataMos(List<UserModel> cityMos){
        this.datas.clear();
        int i=0;
        ArrayList<UserModel> cityMosItem=new ArrayList<>();
        for (UserModel cityMo:cityMos) {
            if (i==2){
                i=0;
                datas.add(new UsersModel(cityMosItem));
                cityMosItem=new ArrayList<>();
            }
            cityMosItem.add(cityMo);
            i++;
        }
        datas.add(new UsersModel(cityMosItem));
        HiLog.error(LABEL_LOG,"datas=="+datas.size());
        notifyDataChanged();
     /*   if (datas.isEmpty()){
            notifyDataChanged();
            return;
        }*/
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int i) {
        return datas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private void onItemClick(UserModel cityMo,int position){
    }

    @Override
    public Component getComponent(int pos, Component component, ComponentContainer componentContainer) {
       /* Component component1= LayoutScatter.getInstance(mSlice).parse(ResourceTable.Layout_layout_user_item,null,false);
        User mo=dataMos.get(i);
        Text text= (Text) component1.findComponentById(ResourceTable.Id_txt_user_item);
        text.setText("姓名："+mo.getName()+"  年龄："+mo.getAge()+"  phone:"+mo.getPhone());
        return component1;*/
        Component component1= LayoutScatter.getInstance(mSlice).parse(ResourceTable.Layout_layout_items,null,false);
        if (!(component1 instanceof ComponentContainer)){
            return null;
        }
        ComponentContainer container= (ComponentContainer) component1;

        //component相当于Android中的view，其他的和Android中ListView的适配器adapter差不多。
        // 名字区别也不大，不过Android中ListView基本被淘汰了。
        UsersModel usersModel=datas.get(pos);
        for (int j = 0; j < usersModel.getUserModels().size(); j++) {
            UserModel user=usersModel.getUserModels().get(j);
            DirectionalLayout directionalLayout= (DirectionalLayout) LayoutScatter.getInstance(mSlice).parse(ResourceTable.Layout_layout_user_item,null,false);
            Text id = (Text) directionalLayout.findComponentById(ResourceTable.Id_user_id);
            Text name = (Text) directionalLayout.findComponentById(ResourceTable.Id_user_name);
            Text lastName = (Text) directionalLayout.findComponentById(ResourceTable.Id_user_last_name);
            Text age = (Text) directionalLayout.findComponentById(ResourceTable.Id_user_age);
            Text numbers = (Text) directionalLayout.findComponentById(ResourceTable.Id_user_numbers);
           // id.setText("Id : " + user.getId());
            name.setText("Name : " + user.getName());
         //   lastName.setText("LastName : " + user.getLastName());
            age.setText("Age : " + user.getAge());
          //  numbers.setText("PhoneNumbers : " + numbers);
            container.addComponent(directionalLayout);

        }
        return component1;
    }
    public interface onDeleteListener{
        void onItemClick(UserModel userBean, int position);
    }

    /**
     * 类似于Android中的listView缓存。
     * 将已经显示在屏幕上的item缓存在ViewHolder中，下次再次出现直接从缓存中读取
     */
    static class ViewHolder {
        Text id;
        Text name;
        Text lastName;
        Text age;
        Text numbers;
        public ViewHolder(ComponentContainer componentContainer) {
            id = (Text) componentContainer.findComponentById(ResourceTable.Id_user_id);
            name = (Text) componentContainer.findComponentById(ResourceTable.Id_user_name);
            lastName = (Text) componentContainer.findComponentById(ResourceTable.Id_user_last_name);
            age = (Text) componentContainer.findComponentById(ResourceTable.Id_user_age);
            numbers = (Text) componentContainer.findComponentById(ResourceTable.Id_user_numbers);
        }
    }
}
