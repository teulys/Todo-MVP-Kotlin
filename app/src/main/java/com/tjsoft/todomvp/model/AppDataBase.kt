package com.tjsoft.todomvp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tjsoft.todomvp.model.dao.TodoDao
import com.tjsoft.todomvp.model.entities.Todo

@Database(entities = [Todo::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun todoDao(): TodoDao

    companion object {

        private var INSTANCE : AppDataBase? = null

        fun getDB(context: Context) :AppDataBase {

            if (INSTANCE != null)
            {
                return INSTANCE!!
            }

            synchronized(this){
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "todo-db"
                ).build()

                return INSTANCE!!
            }
        }


    }

}