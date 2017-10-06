package com.jithin.android.util;

import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by Jithin on 05/10/17.
 */

public class ImageUtil {
    public static void load(ImageView view, String url) {
        if (view != null && view.getContext() != null && url != null)
            Glide.with(view.getContext()).load(url).into(view);
    }
}
