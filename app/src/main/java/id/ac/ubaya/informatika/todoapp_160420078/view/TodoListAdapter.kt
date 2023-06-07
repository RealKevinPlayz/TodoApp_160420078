package id.ac.ubaya.informatika.todoapp_160420078.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.informatika.todoapp_160420078.R
import id.ac.ubaya.informatika.todoapp_160420078.model.Todo
import kotlinx.android.synthetic.main.todo_item_layout.view.*

class TodoListAdapter(val todoList:ArrayList<Todo>, val adapterOnClick : (Todo) -> Unit)
: RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>() {
    class TodoViewHolder(var view: View): RecyclerView.ViewHolder(view)

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.view.checkTask.setText(todoList[position].title.toString())
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
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.todo_item_layout, parent, false)

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


}
