package com.lavaira.checklistapp.view.fragment.landing

import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NavUtils
import androidx.lifecycle.Observer
import com.lavaira.checklistapp.BR
import com.lavaira.checklistapp.R
import com.lavaira.checklistapp.contract.SubscriptionContract
import com.lavaira.checklistapp.databinding.FragmentLandingBinding
import com.lavaira.checklistapp.utils.FragmentUtils
import com.lavaira.checklistapp.view.fragment.base.BaseFragment
import com.lavaira.checklistapp.view.fragment.registration.RegistrationFragment
import kotlinx.android.synthetic.main.fragment_landing.*


/****
 * Landing Screen
 * Author: Lajesh Dineshkumar
 * Created on: 2020-03-15
 * Modified on: 2020-03-15
 *****/
class LandingFragment : BaseFragment<LandingViewModel, FragmentLandingBinding>(), SubscriptionContract {

    override val layoutRes: Int
        get() = R.layout.fragment_landing
    override val bindingVariable: Int
        get() = BR.viewModel

    override val subscriptionContract: SubscriptionContract?
        get() = this

    override fun getViewModel(): Class<LandingViewModel> {
        return LandingViewModel::class.java
    }

    override fun getTitle(): String {
       return ""
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val content = SpannableString(getString(R.string.landing_screen_register))
        content.setSpan(UnderlineSpan(), 0, content.length, 0)
        tvRegister.text = content
    }

    override fun subscribeNavigationEvent() {
        super.subscribeNavigationEvent()
        viewModel.registrationEvent.observe(this, Observer{
            FragmentUtils.replaceFragment(activity as AppCompatActivity, RegistrationFragment(), R.id.fragmentContainer,
                true, FragmentUtils.FragmentAnimation.TRANSITION_SLIDE_LEFT_RIGHT)

        })
    }
}