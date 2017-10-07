package com.jithin.android.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jithin.android.R;
import com.jithin.android.adapter.ArticleAdapter;
import com.jithin.core.model.Article;
import com.jithin.core.viewmodel.ArticleViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jithin on 07/10/17.
 */

public class ArticleFragment extends BaseFragment {
    ArticleAdapter adapter;
    List<Article> articles = new ArrayList<>();
    String source = "";
    String sort = "latest";

    public ArticleFragment() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            source = bundle.getString("source", "");
            sort = bundle.getString("sort", "latest");
        }
    }

    public void setArgs(String source, String sort) {
        this.source = source;
        this.sort = sort;
        Bundle bundle = new Bundle();
        bundle.putString("source", source);
        bundle.putString("sort", sort);
        setArguments(bundle);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_source, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        SwipeRefreshLayout refreshLayout = view.findViewById(R.id.refresher_list);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_productlist);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ArticleAdapter();
        recyclerView.setAdapter(adapter);

        ArticleViewModel viewModel = ViewModelProviders.of(getActivity()).get(ArticleViewModel.class);
        loadArticles(view, viewModel, refreshLayout, source, sort);
        refreshLayout.setOnRefreshListener(() -> loadArticles(view, viewModel, refreshLayout, source, sort));
    }

    private void loadArticles(View view, ArticleViewModel viewModel, SwipeRefreshLayout refreshLayout, String source, String sort) {
        refreshLayout.setRefreshing(true);
        viewModel.loadArticles(source, sort).observe(getActivity(), apiResponse -> {
            if (apiResponse != null && apiResponse.getArticles() != null) {
                articles.addAll(apiResponse.getArticles().getArticles());
                adapter.setArticles(articles);
                adapter.notifyDataSetChanged();
            } else {
//                Snackbar mySnackbar = Snackbar.make(view.findViewById(R.id.base_container),
//                        "Something went wrong!", Snackbar.LENGTH_INDEFINITE);
//                mySnackbar.setAction("Retry", view1 -> {
//                    loadArticles(view, viewModel, refreshLayout, source, sort);
//                });
//                mySnackbar.show();
            }
            refreshLayout.setRefreshing(false);
        });
    }

}
