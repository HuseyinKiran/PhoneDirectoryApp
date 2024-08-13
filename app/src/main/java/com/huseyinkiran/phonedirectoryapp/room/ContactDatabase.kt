package com.huseyinkiran.phonedirectoryapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.huseyinkiran.phonedirectoryapp.model.Contact

@Database(
    entities = [Contact::class],
    version = 1
)
abstract class ContactDatabase : RoomDatabase() {

    abstract val dao: ContactDao

}