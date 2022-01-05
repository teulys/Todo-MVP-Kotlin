package com.tjsoft.todomvp.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.tjsoft.todomvp.R
import com.tjsoft.todomvp.TodoMVP
import com.tjsoft.todomvp.databinding.ItemTodoBinding
import com.tjsoft.todomvp.model.entities.Todo

class TodoAdapter(private val todoList: List<Todo>,
                  private val context: Context,
                  private val presenter: TodoMVP.Presenter) : RecyclerView.Adapter<TodoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        return TodoViewHolder(inflate, context, presenter)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(todoList[position])
    }

    override fun getItemCount(): Int {
        return todoList.size
    }
}

class TodoViewHolder(itemView: View, private val context: Context, private val presenter: TodoMVP.Presenter) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemTodoBinding.bind(itemView)

    fun bind(todo: Todo){
        binding.cbTodoStatus.isChecked = todo.status
        binding.tvTodoName.text = todo.name

        setCheckLisener(binding.cbTodoStatus, todo, presenter)
        setDeleteButton(binding.ivButtomDelete, todo, presenter)
    }

    private fun setCheckLisener(checkBox: CheckBox, todo: Todo, presenter: TodoMVP.Presenter) {

        checkBox.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { compoundButton, b ->
            todo.status = b
            presenter.updateTodo(todo)
        })
    }

    private fun setDeleteButton(iv: ImageView, todo: Todo, presenter: TodoMVP.Presenter) {
        iv.setOnClickListener {
            presenter.deleteTodo(todo)
        }
    }
}
