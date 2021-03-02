package com.dalil.dalilcom.presentation.homepage.morefragment.contactus.aboutdalil

import android.os.Bundle
import android.os.PersistableBundle
import android.os.SystemClock
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
import com.alkhair.Models.CampaginResponseModel
import com.alkhair.Models.ProjectDetailsResponseModel
import com.alkhair.Models.Result
import com.alkhair.R
import com.alkhair.databinding.PaymentFragmentBinding
import com.alkhair.helper.BroadcastHelper
import com.alkhair.helper.PreferenceHelper
import com.alkhair.ui.MainActivity
import com.alkhair.ui.payment.PaymentWaysFragment
import com.alkhair.ui.projects.ProjectDetailsFragment
import com.alkhair.ui.projects.ProjectsFragment
import com.alkhair.ui.ui.home.HomeFragment
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_projects.*
import kotlinx.android.synthetic.main.payment_fragment.view.*
import kotlinx.android.synthetic.main.project_details_item.*


class WebPaymentFragment: AppCompatActivity() {
var webVieww : WebView ? = null
      var helper: PreferenceHelper ? = null
    var strUserTypeId : Int ? = null
    var lastamout : String ? = null
     var mFragmentManager: FragmentManager? = null
    private var mLastClickTime: Long = 0
    var data : ProjectDetailsResponseModel.ResultBean ? = null
    var inside_data: Result ? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<PaymentFragmentBinding>(this, R.layout.payment_fragment)

        webVieww = binding.webview
        helper = PreferenceHelper(this)
        val userid = helper!!.getuser_id()
        var extras = intent.extras

        var radioIndex   = extras!!.getInt("radioIndex")
        val input_amount   = extras!!.getSerializable("amount").toString()

        data = extras!!.getSerializable("data") as ProjectDetailsResponseModel.ResultBean?
        //   inside_data = arguments!!.getSerializable("inside_data") as Result

//        binding.b.setOnClickListener { v ->
//
//            if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {
//            }
//
//
//        }
        val id = helper!!.getuser_id()
        if (id == null){
            strUserTypeId = 0
        }else{
            strUserTypeId = 1
        }
        val userTypeId = strUserTypeId
        if (data != null) {

            lastamout = input_amount

            webVieww!!.loadUrl("http://test.e.net.kw/kuwaitalkhairwebsite/mobile?strDonationTypeID=2&strProjectID=${data!!.projectId}&strProjectTypeID=${data!!.projectTypeId}&strProjectCharityId=${data!!.charityId}&strPaymentTypeId=${radioIndex}&strUserTypeId=0&strDonationAmount=${lastamout}")
//                          http://test.e.net.kw/kuwaitalkhairwebsite/mobile?strDonationTypeID=1&strProjectID=116&strProjectTypeID=6&strProjectCharityId=26&strPaymentTypeId=1&strUserTypeId=0&strDonationAmount=12
        }



// Enable Javascript  //http://test.e.net.kw/kuwaitalkhairwebsite/mobile?strDonationTypeID=2&strProjectID=78&strProjectTypeID=14&strProjectCharityId=11&strPaymentTypeId=1&strUserTypeId=0&strDonationAmount=12
        val webSettings = webVieww!!.getSettings()
        webSettings.setJavaScriptEnabled(true)

        // Force links and redirects to open in the WebView instead of in a browser
        webVieww!!.setWebViewClient(WebViewClient())
    }



}
