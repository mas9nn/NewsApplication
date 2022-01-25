package com.example.newsapp.util

import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.newsapp.R
import com.google.android.material.textfield.TextInputLayout


@BindingAdapter("app:loadImage")
fun loadImage(view: ImageView?, url: String?) {
    if (!url.isNullOrEmpty()) {
        view?.let {
            val circularProgressDrawable = CircularProgressDrawable(it.context)
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()
            Glide.with(view.context).load(url)
                .error(R.drawable.icon_placeholder)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.icon_placeholder)
                .into(view)
        }
    }
}

@BindingAdapter(value = ["app:setError", "app:setErrorText"], requireAll = false)
fun setError(view: TextInputLayout, enable: Boolean, errorText: String?) {
    view.isErrorEnabled = enable
    if (enable) {
        view.error = errorText
        errorText?.let {
            Toast.makeText(view.context, errorText, Toast.LENGTH_SHORT).show()
        }
    } else {
        view.error = null
    }
    view.setErrorIconOnClickListener {
        Toast.makeText(view.context, errorText ?: "Error", Toast.LENGTH_SHORT).show()
    }
}

@BindingAdapter("app:changeVisibility")
fun changeVisibility(view: View, show: Boolean) {
    if (show) view.show()
    else view.hide()
}