package id.ac.ubaya.informatika.todoapp_160420078.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import id.ac.ubaya.informatika.todoapp_160420078.R
import id.ac.ubaya.informatika.todoapp_160420078.viewmodel.DetailTodoViewModel
import kotlinx.android.synthetic.main.fragment_create_todo.*
import kotlinx.android.synthetic.main.fragment_create_todo.view.*

class EditTodoFragment : Fragment() {
    private lateinit var viewModel: DetailTodoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_todo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)
        val uuid = EditTodoFragmentArgs.fromBundle(requireArguments()).uuid
        viewModel.fetch(uuid)
        observeViewModel()

        val lblJudulToDo = view.findViewById<TextView>(R.id.lblJudulToDo)
        val btnAdd = view.findViewById<Button>(R.id.btnAdd)

        lblJudulToDo.text = "Edit Todo"
        btnAdd.text = "Save"

        btnAdd.setOnClickListener {
            val radio =
                view.findViewById<RadioButton>(radioGroupPriority.checkedRadioButtonId)
            viewModel.update(txtTitle.text.toString(), txtNotes.text.toString(),
                radio.tag.toString().toInt(), uuid)
            Toast.makeText(view.context, "Todo updated", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(it).popBackStack()
        }

    }

    fun observeViewModel() {
        viewModel.todoLD.observe(viewLifecycleOwner, Observer {
            val txtTitle = view?.findViewById<EditText>(R.id.txtTitle)
            val txtNotes = view?.findViewById<EditText>(R.id.txtNotes)
            txtTitle?.setText(it.title)
            txtNotes?.setText(it.notes)

            val high = view?.findViewById<RadioButton>(R.id.radioHigh)
            val med = view?.findViewById<RadioButton>(R.id.radioMedium)
            val low = view?.findViewById<RadioButton>(R.id.radioLow)

            when (it.priority) {
                1 -> low?.isChecked = true
                2 -> med?.isChecked = true
                else -> high?.isChecked = true
            }

        })
    }

}