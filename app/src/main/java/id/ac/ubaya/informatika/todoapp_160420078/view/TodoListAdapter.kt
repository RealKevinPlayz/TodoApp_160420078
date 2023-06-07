package id.ac.ubaya.informatika.todoapp_160420078.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.informatika.todoapp_160420078.R
import id.ac.ubaya.informatika.todoapp_160420078.databinding.TodoItemLayoutBinding
import id.ac.ubaya.informatika.todoapp_160420078.model.Todo
import kotlinx.android.synthetic.main.todo_item_layout.view.*

class TodoListAdapter(val todoList:ArrayList<Todo>, val adapterOnClick : (Todo) -> Unit) : RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>() , TodoItemLayoutInterface {
    class TodoViewHolder(var view: TodoItemLayoutBinding): RecyclerView.ViewHolder(view.root)

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.view.todo = todoList[position]
        holder.view.checkListener = this
        holder.view.editListener = this

        holder.view.checkTask.isChecked = false
        /*holder.view.checkTask.setText(todoList[position].title.toString())
        holder.view.checkTask.isChecked = false
        holder.view.checkTask.setOnCheckedChangeListener { compoundButton, b ->
            if(holder.view.checkTask.isChecked ){
                adapterOnClick(todoList[position])
            }

        }

        holder.view.imgEdit.setOnClickListener {
            val action =
                TodoListFragmentDirections.actionEditTodoFragment(todoList[position].uuid)

            Navigation.findNavController(it).navigate(action)
        }*/

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = TodoItemLayoutBinding.inflate(inflater, parent, false)

        return TodoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    fun updateTodoList(newTodoList: List<Todo>) {
        todoList.clear()
        todoList.addAll(newTodoList)
        notifyDataSetChanged()
    }

    override fun onCheckedChanged(cb: CompoundButton, isChecked: Boolean, obj: Todo) {
        if(isChecked) {
            adapterOnClick(obj)
        }
    }

    override fun onTodoEditClick(v: View) {
        val uuid = v.tag.toString().toInt()
        val action = TodoListFragmentDirections.actionEditTodoFragment(uuid)
        Navigation.findNavController(v).navigate(action)
    }
}
