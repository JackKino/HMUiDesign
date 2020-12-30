package com.example.dtse.slice.model;

import com.chg.ultimateprovider.Model;

import com.example.dtse.ResourceTable;
import com.example.dtse.slice.ViewHolder.MusicViewHolder;

import java.util.List;

public class MusicModel implements Model {

    private String title;
    private List data;

    public MusicModel(String title, List data) {
        this.title = title;
        this.data = data;
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
        return ResourceTable.Layout_music_item;
    }

    @Override
    public Class getHolderClass(int position) {
        return MusicViewHolder.class;
    }
}
