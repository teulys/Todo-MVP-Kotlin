package com.tjsoft.todomvp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.tjsoft.todomvp.R
import com.tjsoft.todomvp.TodoMVP
import com.tjsoft.todomvp.databinding.ActivityMainBinding
import com.tjsoft.todomvp.model.entities.Todo
import com.tjsoft.todomvp.presenter.TodoPresenter
import com.tjsoft.todomvp.view.adapters.TodoAdapter

class MainActivity : AppCompatActivity(), TodoMVP.View, View.OnClickListener {

    private lateinit var presenter: TodoMVP.Presenter
    private lateinit var adapter: TodoAdapter
    private var todoList: MutableList<Todo> = mutableListOf()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        presenter = TodoPresenter(this, this)

        binding.ivButtomAdd.setOnClickListener(this)

        adapter = TodoAdapter(todoList, this, presenter)
        presenter.getAllTodo()

        binding.rvTodoList.layoutManager = LinearLayoutManager(this)
        binding.rvTodoList.adapter = adapter
    }

    override fun showTodo(todoList: List<Todo>) {
        runOnUiThread {
            this.todoList.clear()
            this.todoList.addAll(todoList)
            adapter.notifyDataSetChanged()
        }
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun notifyTodoAdded(todo: Todo) {
        runOnUiThread {
            this.todoList.add(todo)
            adapter.notifyDataSetChanged()
        }
    }

    override fun notifyTodoUpdated(todo: Todo) {
        runOnUiThread {
            for (i in 0..this.todoList.size) {
                if (this.todoList[i].id == todo.id)
                {
                    this.todoList[i] = todo
                }
            }
            adapter.notifyDataSetChanged()
        }
    }

    override fun notifyTodoDeleted(todo: Todo) {
        runOnUiThread {
            for (i in 0..this.todoList.size) {
                if (this.todoList[i].id == todo.id) {
                    this.todoList.removeAt(i)
                }
            }
            adapter.notifyDataSetChanged()
        }
    }

    override fun onClick(view: View?) {
        if (view == binding.ivButtomAdd) {

            val text : String? = binding.etTodoName.text.toString()

            if (text.isNullOrEmpty()) {
                Toast.makeText(this, "Write some to add", Toast.LENGTH_LONG).show()
            } else {
                presenter.addTodo(Todo(name = text))
            }
        }
    }
}