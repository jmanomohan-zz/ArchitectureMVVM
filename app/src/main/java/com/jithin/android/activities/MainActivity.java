package com.jithin.android.activities;

import android.os.Bundle;

import com.jithin.android.fragments.SourceFragment;
import com.jithin.android.util.ScreenUtil;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null)
            ScreenUtil.show(this, new SourceFragment());
    }
}
