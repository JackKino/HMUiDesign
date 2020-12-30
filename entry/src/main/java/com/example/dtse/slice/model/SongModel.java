package com.example.dtse.slice.model;

import com.chg.ultimateprovider.Model;
import com.example.dtse.ResourceTable;
import com.example.dtse.slice.ViewHolder.SongViewHolder;

public class SongModel implements Model {
    private String name;
    private String singer;

    public SongModel(String s, String s1) {
        this.name=s;
        this.singer=s1;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    @Override
    public int getResource(int position) {
        return  ResourceTable.Layout_song_item;
    }

    @Override
    public Class getHolderClass(int position) {
        return SongViewHolder.class;
    }
}
