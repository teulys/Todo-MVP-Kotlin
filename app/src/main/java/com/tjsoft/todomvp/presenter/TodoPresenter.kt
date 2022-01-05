package com.tjsoft.todomvp.presenter

import android.content.Context
import com.tjsoft.todomvp.TodoMVP
import com.tjsoft.todomvp.model.entities.Todo
import com.tjsoft.todomvp.model.service.TodoService

class TodoPresenter(private val context: Context, private val view: TodoMVP.View) : TodoMVP.Presenter {

    private val model: TodoMVP.Model = TodoService(context, this)

    override fun showTodo(todoList: List<Todo>) {
        view.showTodo(todoList)
    }

    override fun showError(message: String) {
        view.showError(message)
    }

    override fun notifyTodoAdded(todo: Todo) {
        view.notifyTodoAdded(todo)
    }

    override fun notifyTodoUpdated(todo: Todo) {
        view.notifyTodoUpdated(todo)
    }

    override fun notifyTodoDeleted(todo: Todo) {
        view.notifyTodoDeleted(todo)
    }

    override fun getAllTodo() {
        model.getAllTodo()
    }

    override fun addTodo(todo: Todo) {
        model.addTodo(todo)
    }

    override fun updateTodo(todo: Todo) {
        model.updateTodo(todo)
    }

    override fun deleteTodo(todo: Todo) {
        model.deleteTodo(todo)
    }
}