package com.jithin.core.database;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.jithin.core.database.converters.ArticleTypeConverter;
import com.jithin.core.database.converters.SourceTypeConverter;
import com.jithin.core.database.dao.ArticleDao;
import com.jithin.core.database.dao.SourceDao;
import com.jithin.core.model.Articles;
import com.jithin.core.model.Sources;

/**
 * Created by Jithin on 08/10/17.
 */
@Database(entities = {Articles.class, Sources.class}, version = 1, exportSchema = false)
@TypeConverters({ArticleTypeConverter.class, SourceTypeConverter.class})
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract SourceDao sourceDao();

    public abstract ArticleDao articleDao();

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, AppDatabase.class, "app_database")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

    public static AppDatabase getDatabase() {
        return INSTANCE;
    }

    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
