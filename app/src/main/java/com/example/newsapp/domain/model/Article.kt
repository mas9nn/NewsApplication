package com.example.newsapp.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

@Entity(
    tableName = "articles"
)
@Parcelize
data class Article(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source?,
    val title: String,
    val url: String,
    val urlToImage: String
) : Parcelable {
    fun convertDate(): String {
        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val date: Date =
            dateFormat.parse(publishedAt)
        val formatter: DateFormat =
            SimpleDateFormat("yyyy-MM-dd HH:mm")
        return formatter.format(date)
    }
}