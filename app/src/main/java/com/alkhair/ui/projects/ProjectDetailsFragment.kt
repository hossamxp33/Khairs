package com.alkhair.ui.projects

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alkhair.Adapters.ProjectDetailsAdapter
import com.alkhair.Models.ProjectDetailsResponseModel
import com.alkhair.R
import com.alkhair.ViewModels.ProjectsViewModel
import com.alkhair.databinding.FragmentProjectDetailsBinding
import com.alkhair.helper.BroadcastHelper
import com.alkhair.helper.PreferenceHelper
import com.alkhair.ui.MainActivity
import com.alkhair.ui.partners.MainViewModelFactory
import com.codesroots.mac.cards.presentaion.mainfragment.viewmodel.MainViewModel
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.alkhair.helper.Utility
import com.alkhair.helper.Utility.isConnectedToInternet
import com.alkhair.ui.partners.PartnersFragment
import java.util.*


class ProjectDetailsFragment : Fragment() {
     lateinit var helper: PreferenceHelper
     lateinit var binding: FragmentProjectDetailsBinding
     lateinit var projectsViewModel: ProjectsViewModel
     private  var data: ArrayList<ProjectDetailsResponseModel.ResultBean>? = null
     lateinit var viewModel: MainViewModel
    lateinit var MainAdapter: ProjectDetailsAdapter



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_project_details, container, false)

        data = ArrayList<ProjectDetailsResponseModel.ResultBean>()
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        (activity as MainActivity).setTittle(resources.getString(R.string.details))
        (activity as MainActivity).findViewById<View>(R.id.btnBack).visibility = View.VISIBLE

        Log.i("langInDetials", "onCreate: " + Locale.getDefault().displayLanguage)

        val projectId = arguments!!.getSerializable("project_id") as Int?
        viewModel.GetProjectsDetails(projectId!!)

         helper = PreferenceHelper(activity)
        (activity as MainActivity).findViewById<View>(R.id.btnBack).setOnClickListener {
            fragmentManager!!.popBackStack()
        }


        viewModel.ProjectsDetailsResponseLD!!.observe(this ,Observer {
            if (isConnectedToInternet(getActivity())) {
                if (it != null) {
                    data = ArrayList<ProjectDetailsResponseModel.ResultBean>()
                    data = it.result as ArrayList<ProjectDetailsResponseModel.ResultBean>?
                    binding.details.visibility = View.VISIBLE
                    MainAdapter = ProjectDetailsAdapter(this, data, context as FragmentActivity?)
                    binding.details.adapter = MainAdapter
                    binding.details.layoutManager = LinearLayoutManager(this@ProjectDetailsFragment.activity)
                    binding.progressBar.visibility = View.GONE
                }else {
                    binding.details.visibility = View.GONE
                    if (isAdded) {
                        binding.error.visibility = View.VISIBLE
                        binding.error.text = activity!!.resources.getString(R.string.no_result)
                    }
                }
            } else run {
                Utility.hideDialog()
                binding.error.visibility = View.VISIBLE
            }

        })


        return binding.root
    }
    private val viewModelFactory: MainViewModelFactory
        get() = MainViewModelFactory(this.activity!!.application)

    override fun onResume() {
        MainActivity.backFromProjectDetails = true

        super.onResume()
        if (view == null) {
            return
        }

        view!!.isFocusableInTouchMode = true
        view!!.requestFocus()
        view!!.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                (activity as MainActivity).supportActionBar!!.show()
            }
            //    BroadcastHelper.sendInform(activity as MainActivity, "go_to_home")
            false
        }
    }

}