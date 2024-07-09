package com.example.appwallpaper;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;
public class ImageAdapter extends BaseAdapter {

    private Context context;
    private List<Integer> images;
    private LayoutInflater inflater;

    public ImageAdapter(Context context, List<Integer> images) {
        this.context = context;
        this.images = images;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object getItem(int position) {
        return images.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.adapterimg, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.imageView);
        imageView.setImageResource(images.get(position));

        return convertView;
    }
}
