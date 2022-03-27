package com.example.beosztasapp.activities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.beosztasapp.R

lateinit var contacts: ArrayList<szemelyek>


class UsersRecyclerAdapter(private val dataSet: List<szemelyek>) :
    RecyclerView.Adapter<UsersRecyclerAdapter.ViewHolder>() {

    private var onClickDeleteItem: ((szemelyek) -> Unit)? = null

    fun setOnClickDeleteItem(callback: (szemelyek) -> Unit) {
        this.onClickDeleteItem = callback
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): UsersRecyclerAdapter.ViewHolder {

        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_user_recycler, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: UsersRecyclerAdapter.ViewHolder, position: Int) {

        val tmpSzemely: szemelyek = dataSet[position]
        val textView = viewHolder.nameTextView
        textView.setText(tmpSzemely.nev)
        val textView1 = viewHolder.emailTextView
        textView1.setText(tmpSzemely.email)
        val textView2 = viewHolder.belepesTextView
        textView2.setText(tmpSzemely.belepes)
        viewHolder.deleteButton.setOnClickListener { onClickDeleteItem?.invoke(tmpSzemely) }
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var nameTextView = itemView.findViewById<TextView>(R.id.textViewName)
        var emailTextView = itemView.findViewById<TextView>(R.id.textViewEmail)
        var belepesTextView = itemView.findViewById<TextView>(R.id.textViewPassword)
        var deleteButton = itemView.findViewById<Button>(R.id.btn_delete)

    }

    override fun getItemCount(): Int {
        return dataSet.size
    }


}
