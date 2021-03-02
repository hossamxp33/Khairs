package com.alkhair.ui.projects

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager

import android.os.SystemClock
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast

import com.alkhair.Adapters.CampaignAdapter
import com.alkhair.Adapters.ProjectTypesAdapter
import com.alkhair.Models.CampaginResponseModel
import com.alkhair.Models.ProjecttypesResponseModel
import com.alkhair.R
import com.alkhair.ViewModels.CampaignViewModel
import com.alkhair.ViewModels.ProjectsViewModel
import com.alkhair.databinding.FragmentProjectsBinding
import com.alkhair.helper.BroadcastHelper
import com.alkhair.helper.PreferenceHelper
import com.alkhair.helper.Utility
import com.alkhair.helper.interfaces.GetCallBack
import com.alkhair.ui.MainActivity
import com.alkhair.ui.campaign.CampaignFragment
import com.alkhair.ui.ui.home.HomeFragment

import com.alkhair.helper.Utility.isConnectedToInternet
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_projects.*


class ProjectsFragment : Fragment() {
   lateinit var binding: FragmentProjectsBinding
    lateinit var helper: PreferenceHelper
    lateinit var projectsViewModel: ProjectsViewModel
     var activity: MainActivity? = null
    private var mLastClickTime: Long = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_projects, container, false)
        (getActivity() as MainActivity).hideImageIcon()
        (getActivity() as MainActivity).setTittle(resources.getString(R.string.projects))
        (getActivity() as MainActivity).findViewById<View>(R.id.btnBack).visibility = View.VISIBLE
        (getActivity() as MainActivity).findViewById<View>(R.id.btnBack).setOnClickListener {
            if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {

            }
            mLastClickTime = SystemClock.elapsedRealtime()
            val homeFragment = HomeFragment()
            (getActivity() as MainActivity).showFragment(homeFragment)
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
            }else
            {
                Utility.hideDialog()
                Toast.makeText(getActivity(), getString(R.string.connection_error), Toast.LENGTH_LONG).show()
            }

        }

    }







}