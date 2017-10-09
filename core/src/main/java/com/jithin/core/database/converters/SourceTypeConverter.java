package com.jithin.core.database.converters;

import android.arch.persistence.room.TypeConverter;

import com.jithin.core.model.Source;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import java.io.IOException;
import java.util.List;

/**
 * Created by Jithin on 09/10/17.
 */

public class SourceTypeConverter {

    @TypeConverter
    public static String toString(List<Source> sources) {
        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<List<Source>> jsonAdapter = moshi.adapter(Types.newParameterizedType(List.class, Source.class));
        return jsonAdapter.toJson(sources);
    }

    @TypeConverter
    public static List<Source> toSources(String json) {
        try {
            Moshi moshi = new Moshi.Builder().build();
            JsonAdapter<List<Source>> jsonAdapter = moshi.adapter(Types.newParameterizedType(List.class, Source.class));
            return jsonAdapter.fromJson(json);
        }catch (IOException e){
            return null;
        }
    }

}
