package com.example.freshproducts.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.freshproducts.db.dao.FreshDao
import com.example.freshproducts.domain.model.Fresh

@Database(entities = [Fresh::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun freshDao(): FreshDao

    companion object {
        val DATABASE_NAME: String = "general_database"
    }
}