package com.example.beosztasapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.beosztasapp.R

class ErtesitesRecyclerActivity : AppCompatActivity() {
    lateinit var ertesitesek: ArrayList<Keresek>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ertesites_recycler)
    }

    class ErtesitesRecycleAdapter(private val dataSet: List<Keresek>) :
        RecyclerView.Adapter<ErtesitesRecycleAdapter.ViewHolder>() {

        /**
         * Provide a reference to the type of views that you are using
         * (custom ViewHolder).
         */
        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val nameTextView = itemView.findViewById<TextView>(R.id.textAllapot)
            val dateTextView = itemView.findViewById<TextView>(R.id.textDate)
            val tipusTextView = itemView.findViewById<TextView>(R.id.textTipus)

        }

        override fun onCreateViewHolder(
            viewGroup: ViewGroup,
            viewType: Int
        ): ErtesitesRecycleAdapter.ViewHolder {
            val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.activity_ertesites_recycler, viewGroup, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(
            viewHolder: ErtesitesRecycleAdapter.ViewHolder,
            position: Int
        ) {

            val tmpkereses: Keresek = dataSet[position]
            // Set item views based on your views and data model
            val textView = viewHolder.nameTextView
            textView.setText(tmpkereses.allapot.toString())
            val textView1 = viewHolder.dateTextView
            textView1.setText(tmpkereses.datum)
            val textView2 = viewHolder.tipusTextView
            textView2.setText(tmpkereses.statusz.toString())


        }


        override fun getItemCount(): Int {
            return dataSet.size
        }


    }
}