package com.alkhair.ui.ui.home

import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.alkhair.R
import com.alkhair.databinding.FragmentHomeBinding
import com.alkhair.ui.MainActivity
import com.alkhair.ui.partners.MainViewModelFactory
import com.alkhair.ui.partners.PartnersFragment
import com.alkhair.ui.projects.ProjectsFragment
import com.codesroots.mac.cards.presentaion.mainfragment.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*


class HomeFragment : Fragment(), View.OnClickListener {
    lateinit var binding: FragmentHomeBinding
    internal var mLastClickTime: Long = 0
    private val toolbar: Toolbar? = null
    internal var actionBar: ActionBar? = null
    lateinit var viewModel: MainViewModel
    var pagers: ViewPager? = null
    var NUM_PAGES = 0
    var currentPage = 0
    var index: Int = 0
    lateinit var mFragmentManager: FragmentManager
    private val mFragmentTransaction: FragmentManager? = null
    private fun getViewModelFactory(): MainViewModelFactory {
        return MainViewModelFactory(this.activity!!.application)
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        actionBar = (activity as MainActivity).supportActionBar
        (activity as MainActivity).findViewById<View>(R.id.btnBack).visibility = View.GONE


        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        (activity as MainActivity).setTittle(resources.getString(R.string.menu_home))
        viewModel = ViewModelProviders.of(this, getViewModelFactory()).get(MainViewModel::class.java)
        viewModel.GetSlider()
        binding.shimmerViewContainer2.startShimmerAnimation()

        viewModel.SliderResponseLD!!.observe(this, Observer {
            slider.adapter = it?.let { it1 -> SliderPagerAdapter(activity!!, it1.result) }
            it.result.let { it1 -> init(it1.size) }
            slider.setPadding(50, 0, 50, 0)
            slider.offscreenPageLimit = 3
            slider.pageMargin = 20
            slider.clipToPadding = false
            slider.clipChildren = false
            indicator!!.setViewPager(slider)
            stoploading()

        })
        binding.project.setOnClickListener {
            if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {
                return@setOnClickListener
            }
            mLastClickTime = SystemClock.elapsedRealtime()
            val projectsFragment = ProjectsFragment()
            showFragment(projectsFragment)
        }


        binding.charity.setOnClickListener {
            if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {
                return@setOnClickListener
            }
            mLastClickTime = SystemClock.elapsedRealtime()
            val partnersFragment = PartnersFragment()
            showFragment(partnersFragment)
        }
        mFragmentManager = activity!!.supportFragmentManager
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        binding.donation.setOnClickListener(this)
        val inflater = LayoutInflater.from(shimmer_view_container2.context)
        inflater.inflate(R.layout.shimmer, shimmer_view_container2)
        (activity as MainActivity).findViewById<View>(R.id.btnBack).visibility = View.GONE
        (activity as MainActivity).showIcon()

    }


    override fun onClick(view: View) {

        when (view.id) {
            R.id.donation -> {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {
                    return
                }
                mLastClickTime = SystemClock.elapsedRealtime()
                if (binding.donationContainer.visibility == View.VISIBLE) {
                    binding.donationContainer.visibility = View.GONE
                    binding.donationDown.visibility = View.GONE
                    binding.donationUp.visibility = View.VISIBLE

                } else {
                    binding.donationContainer.visibility = View.VISIBLE
                    binding.donationDown.visibility = View.VISIBLE
                    binding.donationUp.visibility = View.GONE
                }
            }
        }
    }

    private fun showFragment(fragment: Fragment) {
        val mFragmentTransaction = mFragmentManager.beginTransaction()
        mFragmentTransaction.setCustomAnimations(R.anim.slide_in, R.anim.slide_out, R.anim.pop_out, R.anim.pop_in)
        mFragmentTransaction.replace(R.id.fragment, fragment)
        mFragmentTransaction.addToBackStack(null)
        mFragmentTransaction.commit()
    }

    override fun onResume() {
        super.onResume()
        MainActivity.backFromProjectDetails = false
        if (view == null) {
            return
        }
        view!!.isFocusableInTouchMode = true
        view!!.requestFocus()
        view!!.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                (activity as AppCompatActivity).supportActionBar!!.show()
            }

            false
        }
    }

    private fun init(size: Int) {
        val density = getResources().getDisplayMetrics().density
        indicator!!.setRadius(4 * density)
        NUM_PAGES = size
        val handler = Handler()
        val Update: Runnable = Runnable {
            if (currentPage == NUM_PAGES) {
                currentPage = 0
            }
            slider?.setCurrentItem(currentPage++, true)
        }

        val swipeTimer = Timer()
        swipeTimer.schedule(object : TimerTask() {
            override fun run() {
                handler.post(Update)
            }
        }, 4000, 10000)
        indicator!!.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageSelected(position: Int) {
                currentPage = position
            }

            override fun onPageScrolled(pos: Int, arg1: Float, arg2: Int) {}
            override fun onPageScrollStateChanged(pos: Int) {}
        })
    }


    override fun onPause() {
        shimmer_view_container2?.stopShimmerAnimation()
        shimmer_view_container2?.setVisibility(View.GONE)
        super.onPause()
    }

    fun stoploading() {
        shimmer_view_container2?.setVisibility(View.GONE)
        shimmer_view_container2?.stopShimmerAnimation()

    }
}