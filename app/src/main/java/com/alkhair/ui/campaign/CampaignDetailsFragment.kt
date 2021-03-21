package com.alkhair.ui.campaign

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.os.SystemClock
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.alkhair.Adapters.ProjectTypesAdapter
import com.alkhair.Models.CampaginResponseModel
import com.alkhair.Models.ProjecttypesResponseModel
import com.alkhair.R
import com.alkhair.ViewModels.ProjectsViewModel
import com.alkhair.databinding.CampaignDetailsFragmentBinding
import com.alkhair.databinding.FragmentProjectsBinding
import com.alkhair.helper.BroadcastHelper
import com.alkhair.helper.PreferenceHelper
import com.alkhair.helper.Utility
import com.alkhair.helper.interfaces.GetCallBack
import com.alkhair.ui.MainActivity
import com.alkhair.ui.partners.MainViewModelFactory
import com.alkhair.ui.projects.ProjectsFragment
import com.alkhair.ui.ui.home.HomeFragment
import com.codesroots.mac.cards.presentaion.mainfragment.viewmodel.MainViewModel
import com.squareup.picasso.Picasso
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.pedant.SweetAlert.SweetAlertDialog
import com.alkhair.Models.ProjectDetailsResponseModel

import com.alkhair.helper.Utility.isConnectedToInternet
import com.alkhair.ui.chairtydetails.donationfragment.InsideProjectDetailsAdapter
import com.alkhair.ui.payment.PaymentWaysFragment
import kotlinx.android.synthetic.main.fragment_projects.view.*
import java.util.ArrayList

class CampaignDetailsFragment : Fragment() {
    internal lateinit var binding: CampaignDetailsFragmentBinding
    internal lateinit  var helper: PreferenceHelper
    lateinit var MainAdapter: CampaignDetailsFragmentBinding
    private var mLastClickTime: Long = 0
    lateinit var pDialog: SweetAlertDialog

    var projectsViewModel: ProjectsViewModel? = null
     var result : ProjectDetailsResponseModel.ResultBean?  = null
     lateinit  var mFragmentManager: FragmentManager

    private val viewModelFactory: MainViewModelFactory
        get() = MainViewModelFactory(this.activity!!.application)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.campaign_details_fragment, container, false)

        (activity as MainActivity).setTittle(resources.getString(R.string.campaign_details))

        helper = PreferenceHelper(activity)

        val mydata = arguments!!.getParcelable<ProjectDetailsResponseModel.ResultBean>("data")


            result = mydata

        if (helper.lang == "ar") {
            if (mydata!!.getProjectName_Ar() != null) {
                binding.proName.text = mydata.charityName_Ar
                binding.name.text = mydata.projectDescription_Ar

            }else{
                binding.proName.text = mydata.charityName_En.toString()
                binding.name.text = mydata.projectDescription_En.toString()
            }


        }else{
            if (mydata!!.getProjectName_En() != null) {
                binding.proName.text = mydata.charityName_En.toString()
                binding.name.text = mydata.projectDescription_En.toString()

            }else{
                binding.proName.text = mydata.charityName_Ar
                binding.name.text = mydata.projectDescription_Ar
            }
        }

        binding.price.text = (mydata.targetedAmount).toString()
        binding.cashCollect.text = (mydata.collectedAmount).toString()

        val project_cost = mydata.targetedAmount
        val cash_Collect = mydata.collectedAmount
        val res = project_cost - cash_Collect
        binding.remainingValue.text =(res).toString();
        (activity as MainActivity).setTittle(resources.getString(R.string.campaign_details))

       binding.detailstext.setOnClickListener(View.OnClickListener {
           pDialog = SweetAlertDialog(activity, SweetAlertDialog.SUCCESS_TYPE)
           pDialog.setTitleText(R.string.campaign_details)
           pDialog.setContentText(result!!.getProjectDescription_Ar())
           pDialog.setConfirmText("OK")
           pDialog.show()
        })

        binding.donation.setOnClickListener(View.OnClickListener { view ->

              val input_amount = binding.amount.text.toString().trim({ it <= ' ' })
              if (input_amount == "0" || input_amount == "") {
                  Toast.makeText(context, R.string.entervalue, Toast.LENGTH_SHORT).show()


              }else {
                  val bundle = Bundle()
                  bundle.putSerializable("amount", input_amount)
                  bundle.putParcelable("data", result)
                  bundle.putInt("strDonationTypeID ", 6)

                  val fragment = PaymentWaysFragment()
                  fragment.arguments = bundle
                  val mFragmentTransaction = mFragmentManager.beginTransaction()
                  mFragmentTransaction.replace(R.id.fragment, fragment)
                  mFragmentTransaction.addToBackStack(null)
                  mFragmentTransaction.commit()
                  helper.donation_amount = result!!.getCollectedAmount().toString()
              }
            //    projectsFragment.selectPayment();
        })

        mFragmentManager = activity!!.supportFragmentManager

        (activity as MainActivity).findViewById<View>(R.id.btnBack).setOnClickListener {

            fragmentManager!!.popBackStack()

        }

        return binding.root

    }


    override fun onResume() {
        MainActivity.backFromProjectDetails = false

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
