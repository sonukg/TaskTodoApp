package com.sonukg.tasktodoapp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.sonukg.tasktodoapp.data.model.TaskTodo
import com.sonukg.tasktodoapp.databinding.AddTaskFragmentBinding
import com.sonukg.tasktodoapp.utils.DateChange
import com.sonukg.tasktodoapp.viewmodel.AddTaskViewModel
import com.sonukg.tasktodoapp.viewmodel.AddTaskViewModelFactory

class AddTask : Fragment(),AdapterView.OnItemSelectedListener {

    lateinit var binding: AddTaskFragmentBinding
    var taskTodo:TaskTodo?=null
    private var text:String?=null
    private val dateChange = DateChange()
    private lateinit var viewModel: AddTaskViewModel
    val REQUEST_CODE = 200
    var imageUri: Uri?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AddTaskFragmentBinding.inflate(inflater, container, false)
        val addTodoViewModelFactory= AddTaskViewModelFactory(requireActivity().application)
        viewModel= ViewModelProvider(requireActivity(),addTodoViewModelFactory).get(AddTaskViewModel::class.java)
        getSpinnerSetup()
        binding.taskDateText.setOnClickListener {
            binding.taskDateText.setText(dateChange.getToday())
        }
        binding.save.setOnClickListener {
            saveTodo()
        }
       /* binding.imgpick.setOnClickListener {
            openGalleryForImages()
        }*/
        return binding.root

    }

     fun saveTodo() {
        if (validateInput()){
            val id = if (taskTodo !=null) taskTodo?.id else null
            val taskTodos = TaskTodo(
                    id = id,
                    name = binding.taskNameText.text.toString(),
                    desc = binding.taskDescText.text.toString(),
                    todoDate = dateChange.getTime(),
                    category = text!!.toString(),
                   // image = imageUri!!
            )
                viewModel.saveTodo(taskTodos)
            requireActivity().supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace<TaskFrag>(R.id.fragment_container_view)
            }
            /*val intent= Intent(this,DisplayProducts::class.java)
            viewModel.saveProduct(products)
            startActivity(intent)
            finish()*/
        }
    }
    fun validateInput():Boolean{
        if (binding.taskNameText.text!!.isEmpty()){
            binding.taskNameText.setError("Enter the name of task")
            binding.taskNameText.requestFocus()
            return false
        }

        if (binding.taskDescText.text!!.isEmpty()){
            binding.taskDescText.setError("Enter the description of task")
            binding.taskDescText.requestFocus()
            return false
        }
        Toast.makeText(requireActivity(), "Todo is saved successfully.", Toast.LENGTH_SHORT).show()
        return true
    }

   /* override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //viewModel = ViewModelProvider(this).get(AddTaskViewModel::class.java)


    }*/

    private fun getSpinnerSetup() {
        val adapter = ArrayAdapter.createFromResource(
            this.requireActivity(),
            R.array.Category,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerCategory.adapter = adapter
        binding.spinnerCategory.onItemSelectedListener = this
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        text= parent?.getItemAtPosition(position).toString()
        //Toast.makeText(this,text, Toast.LENGTH_SHORT).show()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
    private fun openGalleryForImages() {

        if (Build.VERSION.SDK_INT < 19) {
            var intent = Intent()
            intent.type = "image/*"
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(
                    Intent.createChooser(intent, "Choose Pictures"), REQUEST_CODE
            )
        } else {
            var intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.type = "image/*"
            startActivityForResult(intent, REQUEST_CODE);
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE){
            if (data?.getClipData() != null) {
                var count = data.clipData!!.itemCount

                for (i in 0..count - 1) {
                    imageUri = data.clipData!!.getItemAt(i).uri
                    binding.imgpick.setImageURI(imageUri)
                    Toast.makeText(requireActivity(),""+imageUri,Toast.LENGTH_LONG).show()

                }

            } else if (data?.getData() != null) {
                imageUri = data.data
                binding.imgpick.setImageURI(imageUri)
                Toast.makeText(requireActivity(),""+imageUri,Toast.LENGTH_LONG).show()
            }
        }
    }
}
