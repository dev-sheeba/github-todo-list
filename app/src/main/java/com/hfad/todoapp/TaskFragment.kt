package com.hfad.todoapp

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.hfad.todoapp.database.entities.Task
import com.hfad.todoapp.databinding.FragmentTaskBinding
import com.hfad.todoapp.ui.AddDialogListener
import com.hfad.todoapp.ui.TaskViewModel

class TaskFragment : Fragment(), AddDialogListener, SearchView.OnQueryTextListener,
    TaskAdapter.OnTaskActionListener, DeleteConfirmationDialogFragment.OnDeleteConfirmListener {

    private val viewModel: TaskViewModel by viewModels {
        TaskViewModel.TaskViewModelFactory((requireActivity().application as TasksApplication).repository)
    }

    private var allTasksLiveData: LiveData<List<Task>>? = null
    private var searchedTasksLiveData: LiveData<List<Task>>? = null

    private val myAdapter: TaskAdapter by lazy { TaskAdapter(this) }

    private lateinit var binding: FragmentTaskBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        binding = FragmentTaskBinding.inflate(layoutInflater, container, false)
        (activity as? AppCompatActivity)?.setSupportActionBar(binding.toolBar)
        val recyclerview = binding.homeRecyclerview
        recyclerview.adapter = myAdapter
        recyclerview.layoutManager = LinearLayoutManager(requireContext())

        observeAllTasks()
        onFabButtonClick()

        return binding.root

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.task_menu_fragment, menu)
        val search = menu.findItem(R.id.menu_search)
        val searchView = search.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_sort_by_name -> {
                true
            }
            R.id.action_sort_by_date_created -> {
                true
            }
            R.id.action_hide_completed_task -> {
                item.isChecked = !item.isChecked
                true
            }
            R.id.delete_all_completed_task -> {
                true
            }
            else -> super.onOptionsItemSelected(item)

        }
        return true
    }

    private fun onFabButtonClick() {
        binding.floatingActionButton.setOnClickListener {
            val dialog = TaskDialogFragment()
            dialog.show(childFragmentManager, "DialogFragment")
        }
    }

    override fun onDoneButtonClicked(newTask: Task, dialog: DialogFragment) {
        viewModel.insert(newTask)
        dialog.dismiss()
    }

    override fun onDeleteClicked(task: Task, dialog: DeleteConfirmationDialogFragment) {
        viewModel.delete(task)
    }


    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        if (query != null && query.isNotEmpty()) {
            observeSearchTask(query)
        } else {
            observeAllTasks()
        }
        return true
    }

    override fun onItemClick(task: Task) {
        val dialog = EditDialogFragment()
        dialog.show(childFragmentManager, "EditDialogFragment")
    }

    override fun onItemLongClick(task: Task) {
        val dialog = DeleteDialogFragment(this,task)
        dialog.show(childFragmentManager, "DeleteDialogFragment")
    }

//    override fun onItemClick(task: Task) {
//        viewModel.onTaskSelected(task)
//    }

    override fun onCheckBoxClick(task: Task, isChecked: Boolean) {
        viewModel.onTaskIsCheckedChanged(task, isChecked)
    }

    private fun observeAllTasks() {
        searchedTasksLiveData?.removeObservers(viewLifecycleOwner)
        allTasksLiveData = viewModel.allTasks()
        allTasksLiveData?.observe(viewLifecycleOwner, Observer { tasks ->
            tasks?.let {
                myAdapter.submitList(tasks)
            }
        })
    }

    private fun observeSearchTask(query: String?) {
        val searchQuery = "%$query%"
        allTasksLiveData?.removeObservers(viewLifecycleOwner)
        searchedTasksLiveData = viewModel.searchDatabase(searchQuery)
        searchedTasksLiveData?.observe(viewLifecycleOwner, { list ->
            list.let {
                myAdapter.submitList(it)
            }
        })
    }

    override fun onDeleteConfirm(task: Task, dialog: DeleteConfirmationDialogFragment) {
        viewModel.delete(task)
        dialog.dismiss()
    }

    override fun onCancel(dialog: DeleteConfirmationDialogFragment) {
        dialog.dismiss()
    }


}