package com.jithin.android.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
        return inflater.inflate(R.layout.fragment_source, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_productlist);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new SourceAdapter();


        SourcesViewModel viewModel = ViewModelProviders.of(getActivity()).get(SourcesViewModel.class);
        viewModel.loadSource().observe(getActivity(), apiResponse -> {
            if (apiResponse != null && apiResponse.getSources() != null) {
                sources.addAll(apiResponse.getSources().getSources());
                adapter.setProducts(sources);
                recyclerView.setAdapter(adapter);
            }
        });

    }
}
