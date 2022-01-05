package com.tjsoft.todomvp

import com.tjsoft.todomvp.model.entities.Todo

interface TodoMVP {

    interface View {
        fun showTodo(todoList: List<Todo>)
        fun showError(message: String)
        fun notifyTodoAdded(todo: Todo)
        fun notifyTodoUpdated(todo: Todo)
        fun notifyTodoDeleted(todo: Todo)
    }

    interface Model {
        fun getAllTodo()
        fun addTodo(todo: Todo)
        fun updateTodo(todo: Todo)
        fun deleteTodo(todo: Todo)
    }

    interface  Presenter {
        fun showTodo(todoList: List<Todo>)
        fun showError(message: String)
        fun notifyTodoAdded(todo: Todo)
        fun notifyTodoUpdated(todo: Todo)
        fun notifyTodoDeleted(todo: Todo)
        fun getAllTodo()
        fun addTodo(todo: Todo)
        fun updateTodo(todo: Todo)
        fun deleteTodo(todo: Todo)
    }

}