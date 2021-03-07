package com.uc3m.searchyourrecipe.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
        @PrimaryKey
        val email: String,
        val name: String,
        val image: String
)
