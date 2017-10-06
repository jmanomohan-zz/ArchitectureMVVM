package com.jithin.android.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.jithin.android.R;
import com.jithin.android.fragments.SourceFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SourceFragment fragment = new SourceFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.base_container, fragment, null).commit();
    }
}
