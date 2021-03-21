package com.alkhair.Adapters

import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView

import com.alkhair.Models.ProjecttypesResponseModel
import com.alkhair.R
import com.alkhair.databinding.TypeNewItemBinding
import com.alkhair.helper.PreferenceHelper
import com.alkhair.ui.projects.ProjectDetailsFragment
import com.codesroots.tourismgroup.presentation.screens.details.offers.InsideDonationsFragment
import com.squareup.picasso.Picasso

class TypesAdapter(internal var activity: FragmentActivity, internal var projectsFragment: InsideDonationsFragment, internal var result: List<ProjecttypesResponseModel.ResultBean>?,val listener: ContentListener) : RecyclerView.Adapter<TypesAdapter.ProjectiewHolder>() {
    internal lateinit var helper: PreferenceHelper
    private var mLastClickTime: Long = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypesAdapter.ProjectiewHolder {
        val binding = DataBindingUtil.inflate<TypeNewItemBinding>(LayoutInflater.from(parent.context), R.layout.type_new_item, parent, false)
        return ProjectiewHolder(binding)
    }

    override fun onBindViewHolder(holder: TypesAdapter.ProjectiewHolder, position: Int) {
        helper = PreferenceHelper(activity)
        if (result!![position].logo != null) {
            holder.binding.progressBar.visibility = View.GONE
            Picasso.with(activity).load(result!![position].logo).into(holder.binding.proImage)
        }
        if (helper.lang == "ar") {
            if (result!![position].name_ar != null) {
                holder.binding.name.text = result!![position].name_ar.replace("\\n".toRegex(), "")
            }
        } else {
            if (result!![position].name_en != null) {
                holder.binding.name.text = result!![position].name_en.replace("\\n".toRegex(), "")
            } else {
                holder.binding.name.text = result!![position].name_ar.replace("\\n".toRegex(), "")

            }
        }
        holder.binding.proImage.setOnClickListener(View.OnClickListener {
            if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {
                return@OnClickListener
            }
              listener.onItemClicked(result!![position])

              mLastClickTime = SystemClock.elapsedRealtime()
//            helper.addData("projectID", result!![position].id.toString())
//            helper.addData("campaignDetailsEN", result!![position].name_en)
//            helper.addData("campaignDetailsAR", result!![position].name_ar)
//            val bundle = Bundle()
//            val fragment = ProjectDetailsFragment()
//            bundle.putSerializable("project_id", result!![position].id)
//            fragment.arguments = bundle
//            val mFragmentTransaction = activity.supportFragmentManager.beginTransaction()
//            mFragmentTransaction.replace(R.id.fragment, fragment)
//            mFragmentTransaction.addToBackStack(null)
//            mFragmentTransaction.commit()
        })


    }

    override fun getItemCount(): Int {
        return if (result == null) 0 else result!!.size
    }

    inner class ProjectiewHolder internal constructor(internal val binding: TypeNewItemBinding) : RecyclerView.ViewHolder(binding.root)
}

public interface ContentListener {
    fun onItemClicked(item: ProjecttypesResponseModel.ResultBean)
}