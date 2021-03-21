package com.codesroots.tourismgroup.presentation.screens.details.offers

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.alkhair.Adapters.ContentListener
import com.alkhair.Adapters.ProjectDetailsAdapter
import com.alkhair.Adapters.ProjectTypesAdapter
import com.alkhair.Adapters.TypesAdapter
import com.alkhair.Models.ProjectDetailsResponseModel
import com.alkhair.Models.ProjecttypesResponseModel
import com.alkhair.Models.Result
import com.alkhair.R
import com.alkhair.ViewModels.ProjectsViewModel
import com.alkhair.databinding.AboutChairtyBinding
import com.alkhair.databinding.FragmentProjectsBinding
import com.alkhair.helper.BroadcastHelper
import com.alkhair.helper.Utility
import com.alkhair.helper.Utility.isConnectedToInternet
import com.alkhair.helper.interfaces.GetCallBack
import com.alkhair.ui.MainActivity
import com.alkhair.ui.chairtydetails.donationfragment.InsideProjectDetailsAdapter
import com.alkhair.ui.partners.MainViewModelFactory
import com.alkhair.ui.projects.ProjectsFragment
import com.codesroots.mac.cards.presentaion.mainfragment.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_projects.*
import kotlinx.android.synthetic.main.fragment_projects.view.*
import java.util.ArrayList


class InsideDonationsFragment : Fragment() , ContentListener {

    private var companydetails: ArrayList<ProjectDetailsResponseModel.ResultBean>? = null
    private var data: ProjecttypesResponseModel? = null
    private var dataa: ArrayList<ProjectDetailsResponseModel.ResultBean>? = null
    private var view: FragmentProjectsBinding? = null

    var Project_id : Int? = null
    lateinit var MainAdapter: InsideProjectDetailsAdapter
    lateinit var typesAdapter: TypesAdapter

    lateinit  var viewModel: MainViewModel
    private val viewModelFactory: MainViewModelFactory
        get() = MainViewModelFactory(this.activity!!.application)


    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,  savedInstanceState: Bundle? ): View? {

        view = DataBindingUtil.inflate(inflater, R.layout.fragment_projects, container, false)

        companydetails = arguments?.getParcelableArrayList<ProjectDetailsResponseModel.ResultBean>("companydetails")
        dataa = arguments?.getParcelableArrayList<ProjectDetailsResponseModel.ResultBean>("companydetails")

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        if (companydetails != null) {
        if (companydetails!!.isNotEmpty()){

                MainAdapter = InsideProjectDetailsAdapter(this, companydetails!!, activity)
                view!!.projects.layoutManager = LinearLayoutManager(context)
                view!!.projects.adapter = MainAdapter;
                view!!.progressBar.visibility = View.GONE

            }
        }else{

            view!!.projects.visibility = View.GONE
            view!!.error.visibility = View.VISIBLE
            view!!.error.text = activity!!.resources.getString(R.string.no_result)

        }
//



        viewModel.ProjectsDetailsResponseLD!!.observe(this, Observer {
            companydetails = ArrayList<ProjectDetailsResponseModel.ResultBean>()
            companydetails!!.addAll(it.result)

            if (isConnectedToInternet(getActivity())) {
                if (it != null) {
                    if (companydetails?.isEmpty()!!) {
                        view!!.projects.visibility = View.GONE
                        view!!.error.visibility = View.VISIBLE
                        view!!.error.text = activity!!.resources.getString(R.string.no_result)
                    } else {

                        view!!.projects.visibility = View.VISIBLE
                    MainAdapter = InsideProjectDetailsAdapter(this, companydetails, activity)
                        view!!.projects.adapter = MainAdapter
                        view!!.projects.layoutManager = LinearLayoutManager(context)
                        view!!.progressBar.visibility = View.GONE
                        MainAdapter.notifyDataSetChanged()
                        view!!.error.visibility = View.GONE

                }
            }

            }

        })

        viewModel.GetProjectsTypes()
        viewModel.ProjectsTypesResponseLD!!.observe(this ,Observer {
            typesAdapter = TypesAdapter(activity!!,this , it.result,this)
            data = it
            view!!.projectsCell.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
            view!!.projectsCell.adapter = typesAdapter;
        })

        return view!!.root
    }


    override fun onItemClicked(item: ProjecttypesResponseModel.ResultBean) {
     //   companydetails!!.clear()
        Project_id = item.id
        progressBar.visibility = View.VISIBLE
        viewModel.GetProjectsDetails(Project_id!!)

    }



}