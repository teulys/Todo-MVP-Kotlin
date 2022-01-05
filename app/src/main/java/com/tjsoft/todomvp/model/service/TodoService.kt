package com.tjsoft.todomvp.model.service

import android.content.Context
import com.tjsoft.todomvp.TodoMVP
import com.tjsoft.todomvp.model.AppDataBase
import com.tjsoft.todomvp.model.entities.Todo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoService(private val context: Context, private val presenter: TodoMVP.Presenter) : TodoMVP.Model {

    private val db: AppDataBase = AppDataBase.getDB(context)

    override fun getAllTodo() {
        CoroutineScope(Dispatchers.IO).launch {
            val todoList = db.todoDao().getAll()
            presenter.showTodo(todoList)
        }
    }

    override fun addTodo(todo: Todo) {
        CoroutineScope(Dispatchers.IO).launch {
            db.todoDao().insert(todo)
            presenter.notifyTodoAdded(todo)
        }
    }

    override fun updateTodo(todo: Todo) {
        CoroutineScope(Dispatchers.IO).launch {
            db.todoDao().update(todo)
            presenter.notifyTodoAdded(todo)
        }
    }

    override fun deleteTodo(todo: Todo) {
        CoroutineScope(Dispatchers.IO).launch {
            db.todoDao().delete(todo)
            presenter.notifyTodoAdded(todo)
        }
    }
}