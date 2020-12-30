package com.example.dtse.slice.model;

import com.chg.ultimateprovider.Model;
import com.example.dtse.ResourceTable;
import com.example.dtse.slice.ViewHolder.AlbumViewHolder;

public class AlbumModel implements Model {

    private String name;
    private String songer;

    public AlbumModel(String name, String songer) {
        this.name = name;
        this.songer = songer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSonger() {
        return songer;
    }

    public void setSonger(String songer) {
        this.songer = songer;
    }

    @Override
    public int getResource(int position) {
        return ResourceTable.Layout_album_item;
    }

    @Override
    public Class getHolderClass(int position) {
        return AlbumViewHolder.class;
    }
}
