package com.example.dtse.slice.model;

import com.chg.ultimateprovider.Model;
import com.chg.ultimateprovider.Model2;
import com.example.dtse.ResourceTable;
import com.example.dtse.slice.ViewHolder.NewSongViewHolder;


import java.util.List;

public class NewSongModel implements Model2 {
    private List<SongData> songModels;
    private String name;

    public NewSongModel(List<SongData> songModels) {
        this.songModels = songModels;
    }

    public NewSongModel(List<SongData> songModels, String name) {
        this.songModels = songModels;
        this.name = name;
    }

    public NewSongModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SongData> getSongModels() {
        return songModels;
    }

    public void setSongModels(List<SongData> songModels) {
        this.songModels = songModels;
    }

    @Override
    public int getResource(int position) {
        return  ResourceTable.Layout_songitem_layout;
    }

    @Override
    public Class getHolderClass(int position) {
        return NewSongViewHolder.class;
    }
}
