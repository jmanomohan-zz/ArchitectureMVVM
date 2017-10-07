package com.jithin.android.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jithin.android.R;
import com.jithin.core.util.AppConfig;

public class NavigationFragment extends BaseFragment {

    public NavigationFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_navigation, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        AppConfig.getInstance().init(getContext());
        String built = "Built version "+AppConfig.getInstance().getVersionName() + "/" + AppConfig.getInstance().getVersionCode();
        ((TextView) view.findViewById(R.id.text_built_no)).setText(built);
    }
}
