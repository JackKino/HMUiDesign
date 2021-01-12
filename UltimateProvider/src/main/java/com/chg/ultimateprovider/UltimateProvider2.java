package com.chg.ultimateprovider;

import ohos.agp.components.Component;
import ohos.agp.components.ComponentContainer;
import ohos.agp.components.LayoutScatter;
import ohos.agp.components.RecycleItemProvider;
import ohos.app.Context;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * 主要把数据
 * @param <M>  模型
 */
public class UltimateProvider2<M extends Model2> extends RecycleItemProvider {
    private static final HiLogLabel LABEL_LOG = new HiLogLabel(3, 0xD001100, "Demo");
    /*当前Provider需要的数据*/
    private List<M> models;
    private Context context;
    /*根据自己的需求存放的自定义数据，方便在ItemView之间以及Activity中进行数据交换*/
    private Object customData;
    /*ItemView中的事件传递到Ability,Slice中的回掉*/
    private EventTransmissionListener eventTransmissionListener;
    public boolean isShow;

    /**
     * 获取自定义数据
     * @return 自定义数据
     */
    public Object getCustomData() {
        return customData;
    }

    /**
     * 设置自定义数据
     * @param customData 自定义数据
     */
    public void setCustomData(Object customData) {
        this.customData = customData;
    }

    /**
     * 获取所有需要显示的数组
     * @return 模型列表
     */
    public List<M> getModels() {
        return models;
    }

    /**
     * 模型数据
     * @param models 模型列表
     */
    public void setModels(List<M> models) {
        this.models = models;
    }

    /**
     * 获取Context
     * @return context
     */
    public Context getContext() {
        return context;
    }

    /**
     * 设置 context
     * @param context context
     */
    public void setContext(Context context) {
        this.context = context;
    }

    /**
     * 获取事件传输对象
     * @return 事件传输对象
     */
    public EventTransmissionListener getEventTransmissionListener() {
        return eventTransmissionListener;
    }

    /**
     * 设置事件传输对象
     * @param eventTransmissionListener 事件传输对象
     */
    public void setEventTransmissionListener(EventTransmissionListener eventTransmissionListener) {
        this.eventTransmissionListener = eventTransmissionListener;
    }

    /**
     * 构造方法
     * @param models 模型数据
     * @param context context
     */
    public UltimateProvider2(List<M> models, Context context) {
        this.models = models;
        this.context = context;
        customData=models;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public Object getItem(int i) {
        return models.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public void onItemMoved(int from, int to) {
        super.onItemMoved(from, to);
    }



    @Override
    public Component getComponent(int i, Component component, ComponentContainer componentContainer) {
       /* M model = models.get(position);
        component = LayoutScatter.getInstance(context).parse(model.getResource(position),null,false);
        if (!(component instanceof ComponentContainer)){
            return null;
        }*/

        ViewHolder2 viewHolder = null;
        M model = models.get(i);
      /*  if (component == null) {
            HiLog.error(LABEL_LOG,"component == null");
            viewHolder = createViewHolder(i);
        }else {
            viewHolder = (ViewHolder2) component.getTag();
            //  HiLog.error(LABEL_LOG,"component.getTag()=="+ component.getTag());
            if (!viewHolder.getClass().equals(model.getHolderClass(i))) {
                viewHolder = createViewHolder(i);
            }
        }*/
        viewHolder = createViewHolder(i);
        if (viewHolder == null) {
            HiLog.error(LABEL_LOG,"viewHolder == null");
        }

        viewHolder.setPosition(i);
        viewHolder.setModel(model);
        viewHolder.setModels(models);
        viewHolder.onDataBound();
        viewHolder.setView();
        return viewHolder.getComponent();
    }

    /**
     * 创建ViewHolder
     * @param position 当前位置
     * @return 返回创建的ViewHolder
     */
    private ViewHolder2 createViewHolder(int position){
        M model = models.get(position);
        ViewHolder2 viewHolder = null;

        Component component = LayoutScatter.getInstance(this.context).parse(model.getResource(position),null,false);
            try {
                Constructor constructor = model.getHolderClass(position).getDeclaredConstructor( EventTransmissionListener.class, Component.class, UltimateProvider2.class);
                viewHolder = (ViewHolder2) constructor.newInstance(getEventTransmissionListener(), component, this);
               // component.setTag(viewHolder);
            } catch (NoSuchMethodException e) {
                HiLog.error(LABEL_LOG,"NoSuchMethodException");
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                HiLog.error(LABEL_LOG,"IllegalAccessException");
                e.printStackTrace();
            } catch (InstantiationException e) {
                HiLog.error(LABEL_LOG,"InstantiationException");
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                HiLog.error(LABEL_LOG,"InvocationTargetException");
                e.printStackTrace();
            }

        return viewHolder;
    }

    public void setShowChecked(boolean isShow){
        this.isShow=isShow;
        notifyDataChanged();
    }



}
