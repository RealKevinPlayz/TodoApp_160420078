package id.ac.ubaya.informatika.todoapp_160420078.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import id.ac.ubaya.informatika.todoapp_160420078.R
import id.ac.ubaya.informatika.todoapp_160420078.model.Todo
import id.ac.ubaya.informatika.todoapp_160420078.viewmodel.DetailTodoViewModel
import kotlinx.android.synthetic.main.fragment_create_todo.*
import kotlinx.android.synthetic.main.fragment_create_todo.view.*

class CreateTodoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_todo, container, false)
    }

    private lateinit var viewModel: DetailTodoViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            ViewModelProvider(this).get(DetailTodoViewModel::class.java)
            val btnAdd = view.findViewById<Button>(R.id.btnAdd)
        btnAdd.setOnClickListener {
            var radio = view.findViewById<RadioGroup>(R.id.radioGroupPriority)
            val selectedRadioButton = view.findViewById<RadioButton>(radio.checkedRadioButtonId)

            val txtTitle = view.findViewById<EditText>(R.id.txtTitle)
            val txtNotes = view.findViewById<EditText>(R.id.txtNotes)
            var todo = Todo(txtTitle.text.toString(), txtNotes.text.toString(), selectedRadioButton.tag.toString().toInt())
            val list = listOf(todo)
            viewModel.addTodo(todo)
            Toast.makeText(view.context, "Data added", Toast.LENGTH_LONG).show()
            Navigation.findNavController(it).popBackStack()
        }

    }

}