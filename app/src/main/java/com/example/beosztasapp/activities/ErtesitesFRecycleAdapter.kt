package com.example.beosztasapp.activities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.beosztasapp.R


//ateinit var ertesitesekF: ArrayList<ErtesitesF>


class ErtesitesFRecycleAdapter() :
    RecyclerView.Adapter<ErtesitesFRecycleAdapter.ViewHolder>() {
   private var dataSet:ArrayList<ErtesitesF> = ArrayList()
    fun addItems(items: ArrayList<ErtesitesF>){
        this.dataSet= items

}

    private var onClickElfogadItem: ((ErtesitesF) -> Unit)? = null
    private var onClickElutasitItem: ((ErtesitesF) -> Unit)? = null

    fun setOnClickElfogadItem(callback: (ErtesitesF) -> Unit) {
        this.onClickElfogadItem = callback
    }


    fun setOnClickElutasitItem(callback: (ErtesitesF) -> Unit) {
        this.onClickElutasitItem = callback
    }


    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): ErtesitesFRecycleAdapter.ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_contact, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ErtesitesFRecycleAdapter.ViewHolder, position: Int) {

        val tmpErtesites: ErtesitesF = dataSet[position]
        val textView = viewHolder.nameTextView
        textView.setText(tmpErtesites.nev)
        val textView1 = viewHolder.dateTextView
        textView1.setText(tmpErtesites.mikor)
        val textView2 = viewHolder.tipusTextView
        textView2.setText(tmpErtesites.tipus)
        viewHolder.elfogadButton.setOnClickListener { onClickElfogadItem?.invoke(tmpErtesites) }
        viewHolder.elutasitButton.setOnClickListener { onClickElutasitItem?.invoke(tmpErtesites) }

    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView = itemView.findViewById<TextView>(R.id.textViewName)
        val dateTextView = itemView.findViewById<TextView>(R.id.textViewDate)
        val tipusTextView = itemView.findViewById<TextView>(R.id.textViewTipus)
        val elfogadButton = itemView.findViewById<Button>(R.id.btn_elfogad)
        val elutasitButton = itemView.findViewById<Button>(R.id.btn_elutasit)

    }

    override fun getItemCount(): Int {
        return dataSet.size
    }


}