//package com.codesroots.tourismgroup.presentation.screens.details.main
//
//import android.os.Build
//import android.os.Bundle
//import android.text.Html
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//
//import android.provider.ContactsContract
//import android.content.Intent
//import androidx.lifecycle.Observer
//import androidx.lifecycle.ViewModelProviders
//import com.alkhair.Models.ProjectDetailsResponseModel
//import com.alkhair.Models.Result
//import com.alkhair.R
//import com.alkhair.helper.PreferenceHelper
//import com.codesroots.mac.cards.presentaion.mainfragment.viewmodel.MainViewModel
//import com.google.android.material.snackbar.Snackbar
//import kotlinx.android.synthetic.main.about_chairty.view.*
//import kotlinx.android.synthetic.main.chairty_details.view.*
//import kotlinx.android.synthetic.main.chairty_details.view.progressBar
//
//
//class AboutDonationFragment : Fragment()  {
//
//
//
//     var companydetails: Result? = null
//      var helper: PreferenceHelper? = null
//
//    val viewModel: MainViewModel by lazy {
//        ViewModelProviders.of(this).get(MainViewModel::class.java)
//    }
//    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,  savedInstanceState: Bundle? ): View? {
//
//        val view= inflater.inflate(R.layout.about_chairty, container, false)
//         helper = PreferenceHelper(context)
//        companydetails = arguments?.getSerializable("companydetails") as Result?
//
//        if (helper!!.lang.equals("ar"))
//        {
//
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                view.details.setText(Html.fromHtml(companydetails!!.CharityMission_Ar, Html.FROM_HTML_MODE_COMPACT));
//            } else {
//                view.details.setText(Html.fromHtml(companydetails!!.CharityMission_Ar));
//            }
//
//        }else {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                view.details.setText(Html.fromHtml(companydetails!!.CharityMission_En, Html.FROM_HTML_MODE_COMPACT));
//            } else {
//                view.details.setText(Html.fromHtml(companydetails!!.CharityMission_En));
//            }
//        }
//        view.progressBar.setVisibility(View.GONE)
//
// //       data = arguments!!.getSerializable("data") as ProjectDetailsResponseModel.ResultBean?
//
////         view.mail.text = companydetails!!.email
//
//
//
//
//
//
//
//        return view
//    }
//
//
//
//}
//
