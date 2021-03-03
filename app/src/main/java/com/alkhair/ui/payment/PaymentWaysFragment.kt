package com.alkhair.ui.payment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import android.os.SystemClock
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.EditText
import android.widget.Toast


import com.alkhair.Adapters.PaymentWaysAdapter
import com.alkhair.Models.CampaginResponseModel
import com.alkhair.Models.CharityProjects
import com.alkhair.Models.PaymentWaysResponse
import com.alkhair.Models.ProjectDetailsResponseModel
import com.alkhair.Models.Result
import com.alkhair.R
import com.alkhair.ViewModels.DonationsViewModel
import com.alkhair.databinding.FragmentPaymentWaysBinding
import com.alkhair.helper.BroadcastHelper
import com.alkhair.helper.PreferenceHelper
import com.alkhair.helper.Utility
import com.alkhair.helper.interfaces.GetCallBack
import com.alkhair.ui.MainActivity
import com.alkhair.ui.login.LoginFragment
import com.alkhair.ui.partners.PartnersFragment
import com.alkhair.ui.projects.ProjectDetailsFragment
import com.alkhair.ui.projects.ProjectsFragment
import com.dalil.dalilcom.presentation.homepage.morefragment.contactus.aboutdalil.WebPaymentFragment

import java.util.ArrayList
import java.util.Locale

import com.alkhair.helper.Utility.isConnectedToInternet
import kotlinx.android.synthetic.main.fragment_home.*


class PaymentWaysFragment : Fragment() {

    lateinit var binding: FragmentPaymentWaysBinding
    lateinit var helper: PreferenceHelper
    lateinit var donationsViewModel: DonationsViewModel
    private val mFragmentTransaction: FragmentManager? = null
    private var mFragmentManager: FragmentManager? = null
    private var result: ProjectDetailsResponseModel.ResultBean? = null
    var radioIndex: Int? = null
    var inside_data: Result?=null
    lateinit var input_amount: String
    internal var actionBar: ActionBar? = null
    private val mLastClickTime: Long = 0


    //  private ArrayList<CharityProjects> inside_result;
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_payment_ways, container, false)
        (activity as MainActivity).setTittle(resources.getString(R.string.paymentWay))
        helper = PreferenceHelper(activity)

        Log.i("lang", "onCreate: " + Locale.getDefault().displayLanguage)


        (activity as MainActivity).findViewById<View>(R.id.btnBack).visibility = View.VISIBLE
        (activity as MainActivity).hideImageIcon()

        //helper = new PreferenceHelper(getActivity());

        donationsViewModel = ViewModelProviders.of(this).get(DonationsViewModel::class.java)

        mFragmentManager = activity!!.supportFragmentManager

        result = arguments!!.getSerializable("data") as ProjectDetailsResponseModel.ResultBean?

        inside_data = arguments!!.getSerializable("inside_data") as Result?
        input_amount = arguments!!.getSerializable("amount")!!.toString()

        val id = helper.getuser_id()
        (activity as MainActivity).findViewById<View>(R.id.btnBack).setOnClickListener {

            fragmentManager!!.popBackStack()

        }

        if (id == null) {
            binding.loginConstraint.visibility = View.VISIBLE
        } else {
            binding.loginConstraint.visibility = View.GONE
        }

        binding.donation.setOnClickListener {
            if (radioIndex == null) {
                Toast.makeText(activity, "Please chose Payment way", Toast.LENGTH_LONG).show()
            } else {
                StartActivity()
            }
        }


        getWays()
        return binding.root
    }


    private fun getWays() {
        Utility.showDialog(activity)
        donationsViewModel.getPayment { isOk, requestCode, o ->
            if (isOk) {
                Utility.hideDialog()
                if (isConnectedToInternet(activity)) {
                    if (o != null) {
                        val responseModel = o as PaymentWaysResponse
                        if (responseModel.result.size > 0) {
                            val adapter = PaymentWaysAdapter(activity, this@PaymentWaysFragment, responseModel.result)

                            binding.ways.adapter = adapter
                            binding.ways.layoutManager = LinearLayoutManager(this@PaymentWaysFragment.activity)
                        }
                    }
                }
            } else {
                Utility.hideDialog()
                Toast.makeText(activity, getString(R.string.connection_error), Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun StartActivity() {
        val intent = Intent(activity, WebPaymentFragment::class.java)
        intent.putExtra("data", result)
        intent.putExtra("radioIndex", radioIndex)
        intent.putExtra("amount", input_amount)

        startActivity(intent)
    }

    override fun onResume() {
        MainActivity.backFromProjectDetails = true

        super.onResume()
        if (view == null) {
            return
        }
        helper.getlanguage()
        if(helper.getLang() == "ar") {
         //   (activity as MainActivity).setTittle("alaa")

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