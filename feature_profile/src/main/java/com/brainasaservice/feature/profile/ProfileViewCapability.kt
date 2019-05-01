package com.brainasaservice.feature.profile

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import com.brainasaservice.core.feature.capability.ViewCapability
import kotlinx.android.synthetic.main.view_profile.view.button

class ProfileViewCapability : ViewCapability {
    override fun inflate(context: Context, parent: ViewGroup?): View {
        return LayoutInflater.from(context)
                .inflate(R.layout.view_profile, parent, false).apply {
                    button.setOnClickListener {
                        val intent = Intent(context, ProfileActivity::class.java)
                        context.startActivity(intent)
                    }

                    addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
                        var animation: ScaleAnimation? = null

                        override fun onViewDetachedFromWindow(view: View?) {
                            animation?.cancel()
                        }

                        override fun onViewAttachedToWindow(view: View?) {
                            animation = ScaleAnimation(1f, 0.8f, 1f, 0.8f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f).apply {
                                repeatCount = Animation.INFINITE
                                repeatMode = Animation.REVERSE
                                duration = 800

                                view?.startAnimation(this)
                            }
                        }
                    })
                }
    }
}
