package com.brainasaservice.feature.dashboard

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.brainasaservice.core.feature.capability.ViewCapability
import kotlinx.android.synthetic.main.view_dashboard.view.button

class DashboardViewCapability : ViewCapability {
    override fun inflate(context: Context, parent: ViewGroup?): View {
        return LayoutInflater.from(context)
                .inflate(R.layout.view_dashboard, parent, false).apply {
                    button.setOnClickListener {
                        val intent = Intent(context, DashboardActivity::class.java)
                        context.startActivity(intent)
                    }
                }
    }
}
