package com.omeryildizce.artbook.roomdb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.omeryildizce.artbook.model.Art;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface ArtDao {
    @Query("select name, id from Art")
    Flowable<List<Art>> getArtWithNameAndId();

    @Query("select * from Art where id = :id")
    Flowable<Art>getArtById(int id);

    @Insert
    Completable insert(Art art);

    @Delete
    Completable delete(Art art);
}
