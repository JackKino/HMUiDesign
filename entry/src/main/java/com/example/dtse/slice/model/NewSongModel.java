package com.example.dtse.slice.model;

import com.chg.ultimateprovider.Model;
import com.example.dtse.ResourceTable;
import com.example.dtse.slice.ViewHolder.NewSongViewHolder;
import com.example.dtse.slice.ViewHolder.SongViewHolder;

import java.util.List;

public class NewSongModel implements Model {
    private List<SongData> songModels;

    public NewSongModel(List<SongData> songModels) {
        this.songModels = songModels;
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
