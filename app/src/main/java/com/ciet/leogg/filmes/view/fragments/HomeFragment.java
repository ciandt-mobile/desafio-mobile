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
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.ciet.leogg.filmes.BR;
import com.ciet.leogg.filmes.R;
import com.ciet.leogg.filmes.databinding.FragmentHomeBinding;
import com.ciet.leogg.filmes.databinding.HomeStatusBinding;
import com.ciet.leogg.filmes.presenter.HomePresenter;
import com.ciet.leogg.filmes.presenter.MoviesContract;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class HomeFragment extends Fragment implements MoviesContract.HomeView {

    public HomeFragment() {}

    private HomePresenter presenter;

    private View clapperIcon;
    private ViewPager2 viewPager;
    private TabLayout tabLayout;
    private ObjectAnimator clapperAnimator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        presenter = new ViewModelProvider(this).get(HomePresenter.class);
        presenter.setHomeView(this);
        FragmentHomeBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        binding.setLifecycleOwner(this);
        clapperIcon = binding.getRoot().findViewById(R.id.clapper_icon);
        viewPager = binding.getRoot().findViewById(R.id.home_pager);
        tabLayout = binding.getRoot().findViewById(R.id.tab_layout);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(presenter);
        viewPager.setAdapter(myPagerAdapter);

        new TabLayoutMediator(tabLayout, viewPager,new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if(position == 1){
                    tab.setText(getString(R.string.tab_title_about));
                }else{
                    tab.setText(R.string.tab_title_status);
                }

            }
        }).attach();

        clapperAnimator = ObjectAnimator.ofFloat(clapperIcon,"translationX",0,170f,-170f,0f);
        clapperAnimator.setDuration(3000);
        clapperAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        clapperAnimator.setRepeatCount(ValueAnimator.INFINITE);
        clapperAnimator.setRepeatMode(ValueAnimator.REVERSE);
        clapperAnimator.start();
    }

    public static class StatusFragment extends Fragment{
        public StatusFragment(){}
        HomeStatusBinding binding;
        HomePresenter presenter;
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            binding = DataBindingUtil.inflate(inflater,R.layout.home_status,container,false);
            binding.setLifecycleOwner(this);
            presenter = new ViewModelProvider(this).get(HomePresenter.class);
            binding.setPresenter(presenter);
            return binding.getRoot();
        }
    }
    public static class AboutFragment extends Fragment {
        public AboutFragment(){
            super(R.layout.home_about);
        }
    }

    static class MyPagerAdapter extends RecyclerView.Adapter<PageViewHolder> {
        private static final int VIEW_TYPE_STATUS = 0;
        private static final int VIEW_TYPE_ABOUT = 1;
        private static HomePresenter presenter;

        protected MyPagerAdapter(HomePresenter presenter) {
            super();
            MyPagerAdapter.presenter = presenter;
        }

        @NonNull
        @Override
        public PageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            ViewDataBinding binding;
            switch(viewType){
                case VIEW_TYPE_STATUS:{
                    binding = DataBindingUtil.inflate(inflater,R.layout.home_status,parent,false);
                    break;
                }
                case VIEW_TYPE_ABOUT:{
                    binding = DataBindingUtil.inflate(inflater,R.layout.home_about,parent,false);
                    break;
                }
                default: binding = null;
            }
            return new PageViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull PageViewHolder holder, int position) {
            switch(position){
                case VIEW_TYPE_STATUS: {
                    holder.updateStatusView(presenter);
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

        @Override
        public int getItemCount() {
            return 2;
        }
    }
    static class PageViewHolder extends RecyclerView.ViewHolder{
        private final ViewDataBinding binding;
        public PageViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void updateStatusView(HomePresenter presenter){
            binding.setVariable(BR.presenter,presenter);
            binding.executePendingBindings();
        }

        public void updateAboutView(){
            WebView webView = binding.getRoot().findViewById(R.id.web_view);
            webView.setWebViewClient(new MyWebViewClient(binding.getRoot()));
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
