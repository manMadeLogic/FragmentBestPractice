package com.example.xichen.fragmentbestpractice;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xi Chen on 2016/8/12.
 */
public class NewsTitleFragment extends Fragment {

    private List<News> newsList = new ArrayList<News>();
    private NewsAdapter adapter;
    private boolean isTwoPane;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        initNews();
        adapter = new NewsAdapter(activity, R.layout.news_item, newsList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ListView newsTitleListView;
        View view = inflater.inflate(R.layout.news_title, container, false);
        newsTitleListView = (ListView) view.findViewById(R.id.news_title_list_view);
        newsTitleListView.setAdapter(adapter);
        newsTitleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                News news = newsList.get(position);
                if(isTwoPane) {
                    NewsContentFragment newsContentFragment = (NewsContentFragment)
                            getFragmentManager().findFragmentById(R.id.news_content_fragment);
                    newsContentFragment.refresh(news.getNewsTitle(), news.getNewsContent());
                }
                else{
                    NewsContentActivity.actionStart(getActivity(),news.getNewsTitle(), news.getNewsContent() );
                }
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isTwoPane = getActivity().findViewById(R.id.news_content_layout) != null;
    }

    void initNews() {
        News news = new News();
        news.setNewsTitle("The Internet of (insecure) Things and other inside observations from the Black Hat hackers conference");
        news.setNewsContent("It doesn't feel like we're quite ready for all this stuff to be connected yet. By James Plouffe Aug 11, 2016, 7:22p. tweet · share · Linkedin.");
        newsList.add(news);
        news = new News();
        news.setNewsTitle("Facebook rolls out code to nullify Adblock Plus' workaround");
        news.setNewsContent("Adblock Plus launched a workaround to Facebook's ad block bypass today that ham-handedly removes posts from friends and Pages, not just ads, according to a statement provided by Facebook to TechCrunch.");
        newsList.add(news);
    }
}
