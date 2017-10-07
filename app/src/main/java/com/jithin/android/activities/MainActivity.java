package com.jithin.android.activities;

import android.os.Bundle;

import com.jithin.android.fragments.SourceFragment;
import com.jithin.android.util.ScreenUtil;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScreenUtil.add(this, new SourceFragment());

        setTitle("News");
        setSubTitle("Powered By NewsApi.org");
    }

    @Override
    public void onBackPressed() {

        if (getSupportFragmentManager().getBackStackEntryCount() == 2)
            setTitle("News");

        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }
}
