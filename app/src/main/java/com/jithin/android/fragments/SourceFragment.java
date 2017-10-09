package com.jithin.android.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jithin.android.R;
import com.jithin.android.adapter.SourceAdapter;
import com.jithin.core.model.Source;
import com.jithin.core.viewmodel.SourcesViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jithin on 05/10/17.
 */

public class SourceFragment extends BaseFragment {

    SourceAdapter adapter;
    List<Source> sources = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recycler, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        SwipeRefreshLayout refreshLayout = view.findViewById(R.id.refresher_list);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_productlist);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new SourceAdapter();
        recyclerView.setAdapter(adapter);

        SourcesViewModel viewModel = ViewModelProviders.of(getActivity()).get(SourcesViewModel.class);
        loadArticles(view, viewModel, refreshLayout);
        refreshLayout.setOnRefreshListener(() -> loadArticles(view, viewModel, refreshLayout));

    }

    private void loadArticles(View view, SourcesViewModel viewModel, SwipeRefreshLayout refreshLayout) {
        refreshLayout.setRefreshing(true);
        viewModel.loadSource().observe(getActivity(), apiResponse -> {
            if (apiResponse != null && apiResponse.getSources() != null) {
                sources = apiResponse.getSources();
                adapter.setSources(sources);
                adapter.notifyDataSetChanged();
            } else {
                Snackbar mySnackbar = Snackbar.make(view.findViewById(R.id.fragment_c),
                        "Something went wrong!", Snackbar.LENGTH_INDEFINITE);
                mySnackbar.setActionTextColor(ContextCompat.getColor(getContext(), R.color.white));
                mySnackbar.setAction("Retry", view1 -> {
                    loadArticles(view, viewModel, refreshLayout);
                });
                mySnackbar.show();
            }
            refreshLayout.setRefreshing(false);
        });
    }
}
