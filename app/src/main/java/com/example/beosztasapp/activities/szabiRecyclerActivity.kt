package com.example.beosztasapp.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.beosztasapp.R


class szabiRecyclerActivity : AppCompatActivity() {
    lateinit var keres: ArrayList<Keresek>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ertesites_recycler)
    }

    class szabiRecyclerActivity(private val dataSet: List<Keresek>) :
        RecyclerView.Adapter<szabiRecyclerActivity.ViewHolder>() {

        /**
         * Provide a reference to the type of views that you are using
         * (custom ViewHolder).
         */
        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

            val dateTextView = itemView.findViewById<TextView>(R.id.textSDate)
            val tipusTextView = itemView.findViewById<TextView>(R.id.textSTipus)

        }

        override fun onCreateViewHolder(
            viewGroup: ViewGroup,
            viewType: Int
        ): szabiRecyclerActivity.ViewHolder {
            val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.activity_szaby_recycler, viewGroup, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(
            viewHolder: szabiRecyclerActivity.ViewHolder,
            position: Int
        ) {

            val tmpkereses: Keresek = dataSet[position]
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