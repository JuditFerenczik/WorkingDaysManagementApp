package com.example.beosztasapp.utils

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class MSPEditText(context: Context, attrs: AttributeSet) :AppCompatEditText(context, attrs){
    init {
        applyFont()
    }
    private fun applyFont() {
        val typeface: Typeface =
            Typeface.createFromAsset(context.assets,"zai_HongKongGraffiti.ttf" )
        setTypeface(typeface)
    }


}