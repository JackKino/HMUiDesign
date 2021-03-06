package com.example.dtse.slice.model;


import com.example.dtse.ResourceTable;
import com.example.dtse.slice.ViewHolder.NestedAlbumViewHolder;

/**
 * 这里完全可以在AlbumModel里写。这里为了看起来清晰所有创建一个新的，以免影响阅读
 */
public class NestedAlbumModel extends AlbumModel {

    public NestedAlbumModel(String name, String songer) {
        super(name, songer);
    }

    @Override
    public int getResource(int position) {
        return ResourceTable.Layout_album_item_nested;
    }

    @Override
    public Class getHolderClass(int position) {
        return NestedAlbumViewHolder.class;
    }
}
