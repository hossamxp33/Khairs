package com.alkhair.ui.projects

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.alkhair.Adapters.ProjectTypesAdapter
import com.alkhair.Models.ProjecttypesResponseModel
import com.alkhair.R
import com.alkhair.ViewModels.ProjectsViewModel
import com.alkhair.databinding.FragmentProjectsBinding
import com.alkhair.helper.BroadcastHelper
import com.alkhair.helper.PreferenceHelper
import com.alkhair.helper.Utility
import com.alkhair.helper.Utility.isConnectedToInternet
import com.alkhair.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_projects.view.*


class ProjectsFragment : Fragment() {
    lateinit var binding: FragmentProjectsBinding
    lateinit var helper: PreferenceHelper
    lateinit var projectsViewModel: ProjectsViewModel
    private var mLastClickTime: Long = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_projects, container, false)
        (getActivity() as MainActivity).hideImageIcon()
        (getActivity() as MainActivity).setTittle(resources.getString(R.string.projects))
        (getActivity() as MainActivity).findViewById<View>(R.id.btnBack).visibility = View.VISIBLE
        binding.progressBar.visibility = View.GONE

        (getActivity() as MainActivity).findViewById<View>(R.id.btnBack).setOnClickListener {
            BroadcastHelper.sendInform(activity, "go_to_home")

            fragmentManager!!.popBackStack()


        }

        helper = PreferenceHelper(getActivity())
        val mLayoutManager = GridLayoutManager(getActivity(), 3)
        binding.projects.layoutManager = mLayoutManager
        projectsViewModel = ViewModelProviders.of(this).get(ProjectsViewModel::class.java)
        getProjects()
        return binding.root
    }


    private fun getProjects() {

        Utility.showDialog(getActivity())
        projectsViewModel.getProjectstypes { isOk, requestCode, o ->
            if (isOk) {
                Utility.hideDialog()
                if (isConnectedToInternet(getActivity())) {
                    if (o != null) {
                        val responseModel = o as ProjecttypesResponseModel
                        if (responseModel.result.size > 0) {
                            val adapter = ProjectTypesAdapter(getActivity(), this@ProjectsFragment, responseModel.result)
                            binding.projects.adapter = adapter
                        }

                    }
                }
            } else {
                Utility.hideDialog()
                Toast.makeText(getActivity(), getString(R.string.connection_error), Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onResume() {
        MainActivity.backFromProjectDetails = false
        super.onResume()
        if (view == null) {
            return
        }
        helper.getlanguage()
        if (helper.getLang() == "ar") {
            (activity as MainActivity).setTittle("المشاريع")
        }
        view!!.isFocusableInTouchMode = true
        view!!.requestFocus()
        view!!.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                (activity as MainActivity).supportActionBar!!.show()
            }
            BroadcastHelper.sendInform(activity, "go_to_home")

            false
        }
    }
}