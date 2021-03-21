package com.codesroots.tourismgroup.presentation.screens.details

import android.content.Context
import android.os.Bundle
import android.os.SystemClock
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.alkhair.Models.Result
import com.alkhair.R
import com.alkhair.helper.PreferenceHelper
import com.alkhair.ui.MainActivity
import com.alkhair.ui.chairtydetails.AboutDonationFragment
import com.alkhair.ui.partners.MainViewModelFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.codesroots.mac.cards.presentaion.mainfragment.viewmodel.MainViewModel
import com.codesroots.tourismgroup.presentation.screens.details.offers.InsideDonationsFragment
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.chairty_details.view.*

class ChairtyDetailsFragment : Fragment() {

    lateinit var viewModel: MainViewModel
    private var fragmentOne: AboutDonationFragment? = null
    private var fragmentTwo: InsideDonationsFragment? = null
    var chairty_id: Int? = null
    var tabLayout: TabLayout? = null
    var companydetails: Result? = null
    var helper: PreferenceHelper? = null
    private var toolbar: Toolbar? = null
    private var mLastClickTime: Long = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        val view = inflater.inflate(R.layout.chairty_details, container, false)
        (activity as MainActivity).hideImageIcon()
//        (activity as MainActivity).setTittle(getResources().getString(R.string.charity_details))
        (activity as MainActivity).findViewById<View>(R.id.btnBack).visibility = View.VISIBLE
        (activity as MainActivity).findViewById<View>(R.id.btnBack).setOnClickListener {
            fragmentManager!!.popBackStack()
        }        //  activity!!.actionBar!!.hide()
        chairty_id = arguments!!.getSerializable("chairty_id") as Int
        viewModel = ViewModelProviders.of(this, getViewModelFactory()).get(MainViewModel::class.java)
        viewModel.get_ChairtyDetails(chairty_id!!)

        tabLayout = view.tab as TabLayout?
        helper = PreferenceHelper(context)

        if (helper!!.lang.equals("ar")) {
            tabLayout?.newTab()?.setText("عن الجمعية")?.let { tabLayout?.addTab(it, true) }
            tabLayout?.newTab()?.setText("مشاريع التبرع")?.let { tabLayout?.addTab(it) }

            //arguments?.getString("companyid")?.let { viewModel.getCompany(it.toInt()) }
        } else {
            tabLayout?.newTab()?.setText("About Charity")?.let { tabLayout?.addTab(it, true) }
            tabLayout?.newTab()?.setText("Donation Projects")?.let { tabLayout?.addTab(it) }
        }
        viewModel.ChairtyDetailsResponseLD!!.observe(this, Observer {
            companydetails = it
            setupTabLayout()
            bindWidgetsWithAnEvent()
            setCurrentTabFragment(0)

            context?.let { it1 -> loudImage(it1, view.company_img, it?.CharityLogo) }

            if (helper!!.lang.equals("ar")) {
                if (it?.CharityName_Ar != null) {
                    (activity as MainActivity).setTittle(it?.CharityName_Ar)
                } else {
                    (activity as MainActivity).setTittle(getResources().getString(R.string.charity_details))
                }
            } else {
                if (it?.CharityName_En != null) {
                    (activity as MainActivity).setTittle(it?.CharityName_En)
                } else if (it?.CharityName_Ar != null) {
                    (activity as MainActivity).setTittle(it?.CharityName_Ar)
                } else {
                    (activity as MainActivity).setTittle(getResources().getString(R.string.charity_details))
                }
            }
            view.progressBar.setVisibility(View.GONE)
        })
        viewModel.loadingLivedat.observe(
                this,
                Observer { loading -> view.progressBar.setVisibility(if (loading!!) View.VISIBLE else View.GONE) })

        return view
    }

    private fun setupTabLayout() {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {
        }
        mLastClickTime = SystemClock.elapsedRealtime()

        fragmentOne = AboutDonationFragment()
        fragmentTwo = InsideDonationsFragment()
    }

    private fun bindWidgetsWithAnEvent() {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {

        }
        mLastClickTime = SystemClock.elapsedRealtime()

        tabLayout?.setOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab) {
                setCurrentTabFragment(tab.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    private fun setCurrentTabFragment(tabPosition: Int) {
        when (tabPosition) {
            0 -> fragmentOne?.let { replaceFragment(it) }
            1 -> fragmentTwo?.let { replaceFragment(it) }

        }
    }

    fun replaceFragment(fragment: Fragment) {
        val fm = activity?.getSupportFragmentManager()
        val ft = fm?.beginTransaction()
        val args = Bundle()
        if (fragment is AboutDonationFragment) {
            if (companydetails != null) {
                args.putParcelable("companydetails", companydetails)
            }
        } else {
            try {
                companydetails?.let { args.putParcelableArrayList("companydetails", companydetails!!.CharityProjectsList as ArrayList) }

            } catch (iOException: Exception) {


            }

        }

        fragment.setArguments(args)
        ft?.replace(R.id.frame_container, fragment)
//        ft!!.addToBackStack("home");
        ft?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        ft?.commit()
    }


    fun loudImage(context: Context, imag: ImageView, url: String?) {
        Glide.with(context).applyDefaultRequestOptions(RequestOptions()
                .placeholder(R.drawable.no_image)
                .error(R.drawable.no_image)).load(url).into(imag)
    }


    private fun getViewModelFactory(): MainViewModelFactory {
        return MainViewModelFactory(this.activity!!.application)
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

