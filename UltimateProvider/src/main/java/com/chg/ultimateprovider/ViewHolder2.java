package com.chg.ultimateprovider;

import ohos.agp.components.Component;
import ohos.app.Context;

import java.util.List;

/**
 * ViewHolder
 * @param <M> 对应的模型
 */
public abstract class ViewHolder2<M extends Model2> implements Notify{
    /*ViewHolder对应的数据*/
    private M model;
    private List<M> models;
    /*传递事件到外部（Slice）*/
    private EventTransmissionListener eventTransmissionListener;
    /*对应的布局视图*/
    private Component component;
    /*当前对应的provider*/
    private UltimateProvider2 provider;
    /*当前在列表中的位置*/
    private int position;

    /**
     *构造方法
     * @param eventTransmissionListener 事件传输对象
     * @param component component
     * @param provider provider
     */
    public ViewHolder2(EventTransmissionListener eventTransmissionListener, Component component, UltimateProvider2 provider) {
        this.eventTransmissionListener = eventTransmissionListener;
        this.component = component;
        this.provider = provider;
    }

    /**
     * 当数据已经绑定到ViewMolder。应该在这里给UI控件设置数据
     */
    public abstract void onDataBound();
    public abstract void setView();
    /**
     * 返回当前模型数据
     * @return 数据模型
     */
    public M getModel() {
        return model;
    }

    public void setModel(M model) {
        this.model = model;
    }

    public void setModels(List<M> models) {
        this.models = models;
    }

    public List<M> getModels() {
        return models;
    }

    public EventTransmissionListener getEventTransmissionListener() {
        return eventTransmissionListener;
    }

    public void setEventTransmissionListener(EventTransmissionListener eventTransmissionListener) {
        this.eventTransmissionListener = eventTransmissionListener;
    }

    public UltimateProvider2 getProvider() {
        return provider;
    }

    public void setProvider(UltimateProvider2 provider) {
        this.provider = provider;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    public Context getContext(){
        return component.getContext();
    }

    public Object getCustomData(){
        return getProvider().getCustomData();
    }

    public List getList(){
        return getProvider().getModels();
    }

    public Component findComponentById(int id){
        return getComponent().findComponentById(id);
    }

    /**
     * 更新当前ItemView
     */
    public void notifyCurrentDataSetItemChanged(){
        notifyDataSetItemChanged(getPosition());
    }

    /**
     * 简单封装一下事件传输，使用的时候省去了空的判断
     * @param target 事件发生的地方
     * @param params 传递的参数
     * @param eventId 用于区分多个事件的标志
     * @param callBack 回掉对象
     * @return 传输事件结束后返回的数据(根据需求返回)
     */
    public Object eventTransmission(Object target, Object params, int eventId, EventTransmissionListener.CallBack callBack) {
        if (eventTransmissionListener != null) {
            return eventTransmissionListener.onEventTransmission(target,params,eventId,callBack);
        }
        return null;
    }

    @Override
    public void notifyDataChanged() {
        getProvider().notifyDataChanged();
    }

    @Override
    public void notifyDataInvalidated(){
        getProvider().notifyDataInvalidated();
    }

    @Override
    public void notifyDataSetItemChanged(int position){
        getProvider().notifyDataSetItemChanged(position);
    }

    @Override
    public void notifyDataSetItemInserted(int position){
        getProvider().notifyDataSetItemInserted(position);
    }

    @Override
    public void notifyDataSetItemRemoved(int position){
        getProvider().notifyDataSetItemRemoved(position);
    }

    @Override
    public void notifyDataSetItemRangeChanged(int startPos, int countItems){
        getProvider().notifyDataSetItemRangeChanged(startPos,countItems);
    }

    @Override
    public void notifyDataSetItemRangeInserted(int startPos, int countItems){
        getProvider().notifyDataSetItemRangeInserted(startPos,countItems);
    }

    @Override
    public void notifyDataSetItemRangeRemoved(int startPos, int countItems){
        getProvider().notifyDataSetItemRangeRemoved(startPos,countItems);
    }
}
