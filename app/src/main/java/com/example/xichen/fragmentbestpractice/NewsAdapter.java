package com.example.xichen.fragmentbestpractice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Xi Chen on 2016/8/12.
 */
public class NewsAdapter extends ArrayAdapter<News> {
    private int resourceId;
    public NewsAdapter(Context context, int resource, List<News> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        News news = getItem(position);
        View view;
        TextView newsTitleText;
        if (convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            newsTitleText = (TextView) view.findViewById(R.id.news_title);
            view.setTag(newsTitleText);
        }
        else{
            view = convertView;
            newsTitleText = (TextView) view.getTag();
        }
        newsTitleText.setText(news.getNewsTitle());
        return view;
    }
}
