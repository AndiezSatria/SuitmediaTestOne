package com.andiez.suitmediatestone.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.andiez.suitmediatestone.R
import com.bumptech.glide.Glide

@BindingAdapter("setImageUrl")
fun setImageUrl(imageView: ImageView, url: String?) {
    url?.let {
        val uri = it.toUri().buildUpon().scheme("http").build()
        Glide.with(imageView)
            .load(uri)
            .placeholder(R.drawable.loading_animation)
            .error(R.drawable.ic_broken_image)
            .into(imageView)
    }
}

@BindingAdapter("setDateWithNewFormat")
fun setDateWithNewFormat(tv: TextView, oldDate: String) {
    tv.text = convertOldFormatDate(oldDate)
}

@BindingAdapter("setDate")
fun setDate(tv: TextView, oldDate: String) {
    tv.text = convertFormatDate(oldDate)
}