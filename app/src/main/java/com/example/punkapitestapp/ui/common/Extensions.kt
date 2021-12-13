package com.example.punkapitestapp.ui.common

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.punkapitestapp.R


// Navigate to
    inline fun <reified T: Activity> Context.startActivity(vararg pairs: Pair<String, Any?>){

        val intent = Intent(this, T::class.java)
            .apply {
                putExtras(bundleOf(*pairs))
            }
        startActivity(intent)
    }


//  EditView detecter with lambdas
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }
    })
}


    // preference for adapter
    fun adapterPreference(act: Activity, list: RecyclerView){

        list.apply {
            val LayoutManager = LinearLayoutManager(act)
            layoutManager = LayoutManager
        }
    }

//  inflate views
fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = true): View =
    LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)

// load image with Glide
fun ImageView.loadImage(url: String?) {
    Glide.with(this)
        .asBitmap()
        .load("$url")
      //  .centerCrop()
        .error(R.drawable.beer_list_ic)
        .into(this)
}

