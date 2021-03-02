package com.alkhair.ui.campaign

import android.os.Bundle
import android.os.SystemClock
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.alkhair.Adapters.CampaignAdapter
import com.alkhair.Models.CampaginResponseModel
import com.alkhair.Models.ProjectDetailsResponseModel
import com.alkhair.R
import com.alkhair.ViewModels.CampaignViewModel
import com.alkhair.databinding.FragmentCampaignBinding
import com.alkhair.helper.BroadcastHelper
import com.alkhair.helper.PreferenceHelper
import com.alkhair.helper.Utility
import com.alkhair.helper.interfaces.GetCallBack
import com.alkhair.ui.MainActivity
import com.alkhair.ui.projects.ProjectsFragment
import com.alkhair.ui.ui.home.HomeFragment
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.alkhair.helper.Utility.isConnectedToInternet


class CampaignFragment : Fragment() {
    lateinit var binding: FragmentCampaignBinding
    lateinit var helper: PreferenceHelper
    lateinit var campaignViewModel: CampaignViewModel
    lateinit var mFragmentManager: FragmentManager
    private var mLastClickTime: Long = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_campaign, container, false)
        mFragmentManager = activity!!.supportFragmentManager

        (activity as MainActivity).setTittle(resources.getString(R.string.campaign))
        (activity as MainActivity).findViewById<View>(R.id.btnBack).visibility = View.VISIBLE

        (activity as MainActivity).findViewById<View>(R.id.btnBack).setOnClickListener { v ->
            BroadcastHelper.sendInform(activity, "go_to_home")
            fragmentManager!!.popBackStack()
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        helper = PreferenceHelper(activity)
        (activity as MainActivity).setTittle(resources.getString(R.string.campaign))

        val mLayoutManager = GridLayoutManager(activity, 2)
        binding.campagin.layoutManager = mLayoutManager
        campaignViewModel = ViewModelProviders.of(this).get(CampaignViewModel::class.java)
        getCampaign()
    }


    private fun getCampaign() {
        Utility.showDialog(activity)
        campaignViewModel.getCampaign { isOk, requestCode, o ->
            if (isOk) {
                Utility.hideDialog()
                if (isConnectedToInternet(activity)) {
                    if (o != null) {
                        val responseModel = o as ProjectDetailsResponseModel

                        if (responseModel.result.size > 0) {
                            val adapter = CampaignAdapter(activity, this@CampaignFragment, responseModel.result)
                            binding.campagin.adapter = adapter
                        }

                    }
                }
            } else {
                Utility.hideDialog()
                Toast.makeText(activity, getString(R.string.connection_error), Toast.LENGTH_LONG).show()
            }
        }
    }


    override fun onResume() {
        super.onResume()
        if (view == null) {
            return
        }
        view!!.isFocusableInTouchMode = true
        view!!.requestFocus()
        view!!.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                (activity as AppCompatActivity).supportActionBar!!.show()
                BroadcastHelper.sendInform(activity, "go_to_home")
            }

            false
        }
    }

}