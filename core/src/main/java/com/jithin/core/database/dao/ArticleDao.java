package com.jithin.core.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.jithin.core.model.Articles;

/**
 * Created by Jithin on 08/10/17.
 */
@Dao
public interface ArticleDao {
    @Query("SELECT * FROM Articles WHERE source = :source LIMIT 1")
    Articles get(String source);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void add(Articles articles);

    @Delete
    void remove(Articles articles);
}
