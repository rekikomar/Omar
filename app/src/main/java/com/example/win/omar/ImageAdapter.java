package com.example.win.omar;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by WIN on 05/03/2015.
 */
public class ImageAdapter extends BaseAdapter {
    private ArrayList<String> listCountry;
    private ArrayList<Integer> listFlag;
    private Activity activity;
    ArrayList<Item> data = new ArrayList<Item>();

    public ImageAdapter(Activity activity,ArrayList<String> listCountry, ArrayList<Integer> listFlag,ArrayList<Item> data) {
        super();
        this.listCountry = listCountry;
        this.listFlag = listFlag;
        this.activity = activity;
        this.data = data;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return listCountry.size();
    }

    @Override
    public String getItem(int position) {
        // TODO Auto-generated method stub
        return listCountry.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    public static class ViewHolder
    {
        public ImageView imgViewFlag;
        public TextView txtViewTitle;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder view;
        LayoutInflater inflator = activity.getLayoutInflater();

        if(convertView==null)
        {
            view = new ViewHolder();
            convertView = inflator.inflate(R.layout.full_image, null);

            view.imgViewFlag = (ImageView) convertView.findViewById(R.id.imageView1);

            view.txtViewTitle = (TextView) convertView.findViewById(R.id.textView1);

            convertView.setTag(view);
        }
        else
        {
            view = (ViewHolder) convertView.getTag();
        }

        view.imgViewFlag.setBackgroundResource(listFlag.get(position));
        view.txtViewTitle.setText(listCountry.get(position));
        Item item = data.get(position);
        view.imgViewFlag.setImageBitmap(item.getImage());
        return convertView;
    }
}