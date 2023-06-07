package id.ac.ubaya.informatika.todoapp_160420078.view

import android.view.View
import android.widget.CompoundButton
import id.ac.ubaya.informatika.todoapp_160420078.model.Todo

interface TodoItemLayoutInterface{
    fun onCheckedChanged(cb : CompoundButton, isChecked : Boolean, obj: Todo)
    fun onTodoEditClick(v: View)
}