package com.jithin.core.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.jithin.core.model.Sources;

/**
 * Created by Jithin on 08/10/17.
 */
@Dao
public interface SourceDao {

    @Query("SELECT * FROM Sources WHERE id = :id LIMIT 1")
    Sources get(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void add(Sources sources);

    @Delete
    void remove(Sources sources);

}
