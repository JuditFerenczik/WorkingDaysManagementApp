package com.example.beosztasapp.activities

import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

import androidx.recyclerview.widget.RecyclerView
import com.example.beosztasapp.R

lateinit var contacts: ArrayList<szemelyek>
private lateinit var sqliteHelper: SqlHelper


    class  UsersRecyclerAdapter(private val dataSet: List<szemelyek>) :  //ArrayList<szemelyek>
        RecyclerView.Adapter< UsersRecyclerAdapter.ViewHolder>() {

        private var onClickDeleteItem: ((szemelyek) -> Unit)? = null

        fun setOnClickDeleteItem(callback:(szemelyek) -> Unit){
            this.onClickDeleteItem = callback
  }


        // Create new views (invoked by the layout manager)
        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int):UsersRecyclerAdapter.ViewHolder {
            // Create a new view, which defines the UI of the list item
          //  val context = viewGroup.context
            val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_user_recycler, viewGroup, false)
               // .inflate(R.layout.item_user_recycler, viewGroup, false)
            return ViewHolder(view)
        }

        // Replace the contents of a view (invoked by the layout manager)
        override fun onBindViewHolder(viewHolder:UsersRecyclerAdapter.ViewHolder, position: Int) {

            // Get element from your dataset at this position and replace the
            // contents of the view with that element
          //  sqliteHelper = SqlHelper(this)

            val tmpSzemely: szemelyek = dataSet[position]
            // Set item views based on your views and data model
            val textView = viewHolder.nameTextView
            textView.setText(tmpSzemely.nev)
            val textView1 = viewHolder.emailTextView
            textView1.setText(tmpSzemely.email)
            val textView2 = viewHolder.belepesTextView
            textView2.setText(tmpSzemely.belepes)
           viewHolder.deleteButton.setOnClickListener { onClickDeleteItem?.invoke(tmpSzemely) }
           // val button = viewHolder.messageButton
           // button.text = "Delete"
         //   button.isEnabled = contact.isOnline






        /*  val tmpSzemely = dataSet[position]
           viewHolder.textViewName.setText(tmpSzemely.nev)
           viewHolder.textViewEmail.setText(tmpSzemely.email)
           viewHolder.textViewPassword.setText(tmpSzemely.belepes)

           viewHolder.textViewName.text = dataSet[position].nev
           viewHolder.textViewEmail.text = dataSet[position].email
           viewHolder.textViewPassword.text = dataSet[position].belepes
       */}


        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            //    val   textViewName = view.findViewById(R.id.textViewName) as AppCompatTextView
            //   val   textViewEmail = view.findViewById(R.id.textViewEmail) as AppCompatTextView
            //    val   textViewPassword = view.findViewById(R.id.textViewPassword) as AppCompatTextView
            var nameTextView = itemView.findViewById<TextView>(R.id.textViewName)
            var emailTextView = itemView.findViewById<TextView>(R.id.textViewEmail)
            var belepesTextView = itemView.findViewById<TextView>(R.id.textViewPassword)
            var deleteButton = itemView.findViewById<Button>(R.id.btn_delete)

        }
        // Return the size of your dataset (invoked by the layout manager)
        override fun getItemCount():Int{
            return dataSet.size
        }


    }
