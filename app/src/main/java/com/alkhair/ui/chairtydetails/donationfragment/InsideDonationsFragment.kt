package com.codesroots.tourismgroup.presentation.screens.details.offers

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.alkhair.Adapters.ProjectTypesAdapter
import com.alkhair.Adapters.TypesAdapter
import com.alkhair.Models.ProjecttypesResponseModel
import com.alkhair.Models.Result
import com.alkhair.R
import com.alkhair.ViewModels.ProjectsViewModel
import com.alkhair.databinding.FragmentProjectsBinding
import com.alkhair.helper.Utility
import com.alkhair.helper.Utility.isConnectedToInternet
import com.alkhair.helper.interfaces.GetCallBack
import com.alkhair.ui.chairtydetails.donationfragment.InsideProjectDetailsAdapter
import com.alkhair.ui.partners.MainViewModelFactory
import com.alkhair.ui.projects.ProjectsFragment
import com.codesroots.mac.cards.presentaion.mainfragment.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_projects.*
import kotlinx.android.synthetic.main.fragment_projects.view.*


class InsideDonationsFragment : Fragment() {
    private var companydetails: Result? = null
    private var data: ProjecttypesResponseModel? = null

    lateinit var MainAdapter: InsideProjectDetailsAdapter
    lateinit var typesAdapter: TypesAdapter

    lateinit  var viewModel: MainViewModel
    private val viewModelFactory: MainViewModelFactory
        get() = MainViewModelFactory(this.activity!!.application)


    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,  savedInstanceState: Bundle? ): View? {

        val view= inflater.inflate(R.layout.fragment_projects, container, false)

        companydetails = arguments?.getSerializable("companydetails") as Result?

        MainAdapter = InsideProjectDetailsAdapter(this, companydetails!!.CharityProjectsList, activity)
        view.projects.layoutManager = LinearLayoutManager(context)
        view.projects.adapter = MainAdapter;

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)

        viewModel.GetProjectsTypes()
        viewModel.ProjectsTypesResponseLD!!.observe(this ,Observer {
            typesAdapter = TypesAdapter(activity,this,it.result)
            data = it
            view.projects_cell.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
            view.projects_cell.adapter = typesAdapter;


        })




        return view
    }




}