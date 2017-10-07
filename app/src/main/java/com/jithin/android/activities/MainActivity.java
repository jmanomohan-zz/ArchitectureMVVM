package com.jithin.android.activities;

import android.os.Bundle;

import com.jithin.android.R;
import com.jithin.android.fragments.SourceFragment;
import com.jithin.android.util.ScreenUtil;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null)
            ScreenUtil.add(this, new SourceFragment());
        setTitle(getResources().getString(R.string.app_name));
        setSubTitle(getResources().getString(R.string.toolbar_subtext));
    }

    @Override
    public void onBackPressed() {

        if (getSupportFragmentManager().getBackStackEntryCount() == 2)
            setTitle(getResources().getString(R.string.app_name));

        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }
}
