package com.example.dtse.slice.model;

import com.example.dtse.ResourceTable;
import com.example.dtse.slice.ViewHolder.NestedSongViewHolder;

/**
 * 这里完全可以在SongModel里写。这里为了看起来清晰所有创建一个新的，以免影响阅读
 */
public class NestedSongModel extends SongModel {
    public NestedSongModel(String name, String singer) {
        super(name, singer);
    }

    @Override
    public Class getHolderClass(int position) {
        return NestedSongViewHolder.class;
    }

    @Override
    public int getResource(int position) {
        return ResourceTable.Layout_song_item_nested;
    }
}
