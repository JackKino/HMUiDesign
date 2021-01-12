package com.example.dtse.slice.model;

import com.chg.ultimateprovider.Model;
import com.example.dtse.ResourceTable;
import com.example.dtse.slice.ViewHolder.SongViewHolder;

public class SongData  {
    private String name;
    private String singer;
    private boolean isChecked;


    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public SongData(String s, String s1) {
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

}
