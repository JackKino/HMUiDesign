package com.example.dtse.slice.model;

import com.chg.ultimateprovider.Model;
import com.chg.ultimateprovider.Model2;
import com.example.dtse.ResourceTable;
import com.example.dtse.slice.ViewHolder.MulitListHolder;
import com.example.dtse.slice.ViewHolder.MusicViewHolder;

import java.util.List;

public class MulitListModel implements Model2 {

    private String title;
    private List<NewSongModel> data;

    public MulitListModel(String title, List data) {
        this.title = title;
        this.data = data;
    }

    public MulitListModel() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    @Override
    public int getResource(int position) {
        return ResourceTable.Layout_mulitlist_item;
    }

    @Override
    public Class getHolderClass(int position) {
        return MulitListHolder.class;
    }
}
