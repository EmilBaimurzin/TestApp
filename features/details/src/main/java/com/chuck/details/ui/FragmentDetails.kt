package com.chuck.details.ui

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.chuck.core.R
import com.chuck.core.ViewBindingFragment
import com.chuck.details.databinding.FragmentDetailsBinding

class FragmentDetails: ViewBindingFragment<FragmentDetailsBinding>(FragmentDetailsBinding::inflate) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.joke.text = arguments?.getString("JOKE") ?: ""

        val circularProgressDrawable = CircularProgressDrawable(requireContext())
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        val errorDrawable = ResourcesCompat.getDrawable(resources, R.drawable.ic_placeholder, null)
        val newErrorDrawable = DrawableCompat.wrap(errorDrawable!!)
        newErrorDrawable.setTint(getThemeAccentColor(requireContext()))

        Glide.with(requireContext())
            .load(arguments?.getString("IMG") ?: "")
            .apply(
                RequestOptions()
                    .placeholder(circularProgressDrawable)
                    .error(errorDrawable)
                    .fitCenter()
            )
            .listener(object : RequestListener<Drawable?> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable?>,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable,
                    model: Any,
                    target: Target<Drawable?>?,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }


            })
            .into(binding.img)
    }

    private fun getThemeAccentColor(context: Context): Int {
        val value = TypedValue()
        context.theme.resolveAttribute(android.R.attr.colorAccent, value, true)
        return value.data
    }
}