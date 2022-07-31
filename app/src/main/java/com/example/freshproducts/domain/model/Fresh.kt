package com.example.freshproducts.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.squareup.moshi.Json


@Entity(tableName = "Fresh", indices = [Index(value = ["plantId"], unique = true)])
data class Fresh(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "plantId")
    @Json(name = "plantId")
    var plantId: String = "",

    @ColumnInfo(name = "name")
    @Json(name = "name")
    var name: String = "",

    @ColumnInfo(name = "imageUrl")
    @Json(name = "imageUrl")
    val imageUrl: String = "",

    @ColumnInfo(name = "description")
    @Json(name = "description")
    val description: String = "",

    @ColumnInfo(name = "favorite")
    @Json(name = "favorite")
    var favorite: Boolean =false,
    )

