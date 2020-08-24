package com.ciet.leogg.filmes.view.fragments;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.ciet.leogg.filmes.R;
import com.ciet.leogg.filmes.model.Movie;
import com.ciet.leogg.filmes.presenter.HomePresenter;
import com.ciet.leogg.filmes.presenter.MoviesContract;
import com.ciet.leogg.filmes.view.customviews.MovieItemView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeFragment extends Fragment implements MoviesContract.HomeView {

    public HomeFragment() {}

    private MoviesContract.HomeInteraction homeInteraction;
    private View homeView;
    private View clapperIcon;
    private ViewPager2 viewPager;
    private TabLayout tabLayout;
    private MyPagerAdapter myPagerAdapter;
    private ObjectAnimator clapperAnimator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        homeInteraction = new ViewModelProvider(this).get(HomePresenter.class);
        homeInteraction.setHomeView(this);
        homeView = inflater.inflate(R.layout.fragment_home, container, false);
        clapperIcon = homeView.findViewById(R.id.clapper_icon);
        viewPager = homeView.findViewById(R.id.home_pager);
        tabLayout = homeView.findViewById(R.id.tab_layout);
        return homeView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        myPagerAdapter = new MyPagerAdapter();
        viewPager.setAdapter(myPagerAdapter);

        new TabLayoutMediator(tabLayout, viewPager,new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if(position == 1){
                    tab.setText("About");
                }else{
                    tab.setText("Status");
                }

            }
        }).attach();

        clapperAnimator = ObjectAnimator.ofFloat(clapperIcon,"translationX",0,170f,-170f,0f);
        clapperAnimator.setDuration(3000);
        clapperAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        clapperAnimator.setRepeatCount(ValueAnimator.INFINITE);
        clapperAnimator.setRepeatMode(ValueAnimator.REVERSE);
        clapperAnimator.start();
        homeInteraction.loadPageAndLastMovie();
    }

    @Override
    public void showPageAndLastMovie(Integer page, Movie movie) {
        List<Object> list = new ArrayList<>();
        list.add(page.toString());
        list.add(movie);
        myPagerAdapter.submitList(list);
    }

    public static class StatusFragment extends Fragment{
        public StatusFragment(){
            super(R.layout.home_status);
        }
    }
    public static class AboutFragment extends Fragment {
        public AboutFragment(){
            super(R.layout.home_about);
        }
    }

    static class MyPagerAdapter extends ListAdapter<Object,PageViewHolder> {
        private static final int VIEW_TYPE_STATUS = 0;
        private static final int VIEW_TYPE_ABOUT = 1;

        protected MyPagerAdapter() {
            super(new DiffUtil.ItemCallback<Object>() {
                @Override
                public boolean areItemsTheSame(@NonNull Object oldItem, @NonNull Object newItem) {
                    return oldItem == newItem;
                }

                @Override
                public boolean areContentsTheSame(@NonNull Object oldItem, @NonNull Object newItem) {
                    return Objects.equals(oldItem,newItem);
                }
            });
        }

        @NonNull
        @Override
        public PageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view;
            switch(viewType){
                case VIEW_TYPE_STATUS:{
                    view = inflater.inflate(R.layout.home_status, parent, false);
                    break;
                }
                case VIEW_TYPE_ABOUT:{
                    view = inflater.inflate(R.layout.home_about, parent, false);
                    break;
                }
                default: view = null;
            }
            return new PageViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull PageViewHolder holder, int position) {
            switch(position){
                case VIEW_TYPE_STATUS: {
                    holder.updateStatusView((String) getCurrentList().get(0), (Movie) getCurrentList().get(1));
                    break;
                }
                case VIEW_TYPE_ABOUT:{
                    holder.updateAboutView();
                    break;
                }
            }
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }
    }
    static class PageViewHolder extends RecyclerView.ViewHolder{
        private final View myView;
        public PageViewHolder(@NonNull View itemView) {
            super(itemView);
            myView = itemView;
        }

        public void updateStatusView(String page, Movie movie){
           View view = ((NestedScrollView)myView).getChildAt(0);
            ((TextView)view.findViewById(R.id.current_page_number)).setText(page);
            if(movie == null){
                movie = Movie.createDefault();
            }
            ((MovieItemView)view.findViewById(R.id.home_last_movie)).updateMovie(movie);
        }

        public void updateAboutView(){
            WebView webView = myView.findViewById(R.id.web_view);
            webView.setWebViewClient(new MyWebViewClient(myView));
            webView.loadUrl("https://github.com/leoggoes/desafio-mobile/tree/dev");
        }
    }

    public static class MyWebViewClient extends WebViewClient {
        private final String path = "github.com/leoggoes/";
        private final ProgressBar loadingBar;
        public MyWebViewClient(View parentView){
            loadingBar = parentView.findViewById(R.id.web_view_progress);
        }
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if(Uri.parse(url).getPath().contains(path)) {
                return false;
            }else{
                view.goBack();
                return true;
            }
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            if(request.getUrl().getPath().contains(path)){
                return false;
            }else{
                view.goBack();
                return true;
            }
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            view.setVisibility(View.GONE);
            loadingBar.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            loadingBar.setVisibility(View.GONE);
            view.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        clapperAnimator.cancel();
    }
}
