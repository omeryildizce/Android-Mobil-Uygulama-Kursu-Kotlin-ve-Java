package com.omeryildizce.kotlinmaps.roomdb

import androidx.room.*
import com.omeryildizce.kotlinmaps.model.Place
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

@Dao
interface PlaceDao {
    @Query("select * from Place")
    fun getAll() : Flowable<List<Place>>

    @Insert
    fun insert(place: Place) : Completable

    @Update
    fun update(place: Place) : Completable

    @Delete
    fun delete(place: Place) :Completable


}