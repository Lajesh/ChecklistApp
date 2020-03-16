package com.lavaira.checklistapp.view.activity

import android.os.Bundle
import com.lavaira.checklistapp.BR
import com.lavaira.checklistapp.R
import com.lavaira.checklistapp.databinding.ActivityMainBinding
import com.lavaira.checklistapp.utils.FragmentUtils
import com.lavaira.checklistapp.view.activity.base.BaseActivity
import com.lavaira.checklistapp.view.fragment.landing.LandingFragment
import com.lavaira.checklistapp.viewmodel.EmptyViewModel


/****
 * MainActivity class
 * Author: Lajesh Dineshkumar
 * Created on: 15/03/20
 * Modified on: 15/03/20
 *****/
class PreLoginActivity : BaseActivity<EmptyViewModel, ActivityMainBinding>() {
    override val layoutRes: Int
        get() = R.layout.activity_main

    override val bindingVariable: Int
        get() = BR.viewModel

    override fun getViewModel(): Class<EmptyViewModel> {
      return EmptyViewModel::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FragmentUtils.replaceFragment(this, LandingFragment(), R.id.fragmentContainer,
            false, FragmentUtils.FragmentAnimation.TRANSITION_NONE)
    }

}
