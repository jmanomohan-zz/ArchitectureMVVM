package com.jithin.android.util;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.jithin.android.R;

/**
 * Created by Jithin on 07/10/17.
 */

public class ScreenUtil {
    protected enum Show {Source, Article}

    public static void show(Activity activity, Fragment fragment) {
        ((AppCompatActivity) activity).getSupportFragmentManager().beginTransaction()
                .replace(R.id.base_container, fragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();

    }

    public static void openChromeTab(Context context, String url) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent customTabsIntent = builder.build();
        builder.setToolbarColor(ContextCompat.getColor(context, R.color.white));
        customTabsIntent.launchUrl(context, Uri.parse(url));
    }
}
