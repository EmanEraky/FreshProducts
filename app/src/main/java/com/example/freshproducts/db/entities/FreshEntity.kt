package com.example.freshproducts.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FreshEntity(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "plantId") val plantId: String?,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "imageUrl") val imageUrl: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "favorite") val favorite: Boolean?,

    )
