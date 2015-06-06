package com.example.win.omar;

import android.graphics.Bitmap;

/**
 * Created by WIN on 05/03/2015.
 */
 public class Item {
    Bitmap image;

    public Item(Bitmap image) {
        super();
        this.image = image;
    }
    public Bitmap getImage() {
        return image;
    }
    public void setImage(Bitmap image) {
        this.image = image;
    }



}