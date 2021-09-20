package com.br.magazinehero.util

import android.widget.ImageView
import com.br.magazinehero.R
import com.bumptech.glide.Glide


fun ImageView.loadImage(path: String, extension: String) {

    if (!path.isNullOrBlank()) {
        Glide.with(this)
                .load("${path}.${extension}")
                .error(R.drawable.no_internet_24dp)
                .into(this)
    } else {
        this.setImageResource(R.drawable.ic_error)
    }



}





