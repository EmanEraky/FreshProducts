package com.example.freshproducts.utils


import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy


@BindingAdapter("android:freshImage")
fun setImageUrl(view: AppCompatImageView, path: String?) {
    if (path != null)
        if (!path.equals("")) {
            Glide.with(view.context).asBitmap().load(path)
                .diskCacheStrategy(DiskCacheStrategy.ALL).dontAnimate().into(view)
        }
}


