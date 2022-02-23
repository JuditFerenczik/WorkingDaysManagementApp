package com.example.beosztasapp.utils

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class MSPTextView(context: Context, attrs: AttributeSet ):AppCompatTextView(context, attrs) {
init {
    applyFont()
}

    private fun applyFont() {
      val typeface: Typeface =
          Typeface.createFromAsset(context.assets,"zai_I♥Covid-19.ttf" )
        setTypeface(typeface)
    }


}