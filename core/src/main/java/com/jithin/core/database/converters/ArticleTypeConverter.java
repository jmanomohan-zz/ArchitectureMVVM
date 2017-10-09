package com.jithin.core.database.converters;

import android.arch.persistence.room.TypeConverter;

import com.jithin.core.model.Article;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import java.io.IOException;
import java.util.List;

/**
 * Created by Jithin on 09/10/17.
 */

public class ArticleTypeConverter {

    @TypeConverter
    public static String toString(List<Article> articles) {
        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<List<Article>> jsonAdapter = moshi.adapter(Types.newParameterizedType(List.class, Article.class));
        return jsonAdapter.toJson(articles);
    }

    @TypeConverter
    public static List<Article> toArticles(String json) {
        try {
            Moshi moshi = new Moshi.Builder().build();
            JsonAdapter<List<Article>> jsonAdapter = moshi.adapter(Types.newParameterizedType(List.class, Article.class));
            return jsonAdapter.fromJson(json);
        }catch (IOException e){
            return null;
        }
    }

}
