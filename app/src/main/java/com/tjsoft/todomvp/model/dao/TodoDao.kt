package com.tjsoft.todomvp.model.dao

import androidx.room.*
import com.tjsoft.todomvp.model.entities.Todo

@Dao
interface TodoDao {

    @Query("SELECT * FROM todo")
    fun getAll() : List<Todo>

    @Query("SELECT * FROM todo WHERE id = :id")
    fun getById(id: Int) : Todo

    @Insert
    fun insert(vararg todo: Todo)

    @Update
    fun update(todo: Todo)

    @Delete
    fun delete(todo: Todo)
}