package com.example.freshproducts.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.freshproducts.domain.model.Fresh

@Dao
interface FreshDao {

    @Query("SELECT * From Fresh")
    suspend fun getAll(): List<Fresh>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product: Fresh): Long

    @Query("DELETE FROM Fresh WHERE plantId = :plantId")
    suspend fun deleteByPlantId(plantId: String): Int
}