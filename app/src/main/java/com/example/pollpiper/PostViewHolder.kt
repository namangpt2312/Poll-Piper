package com.example.pollpiper

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PostViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val postText: TextView = itemView.findViewById(R.id.postTitle)
    val userText: TextView = itemView.findViewById(R.id.userName)
    val createdAt: TextView = itemView.findViewById(R.id.createdAt)
    val option1: LinearLayout = itemView.findViewById(R.id.option1)
    val option2: LinearLayout = itemView.findViewById(R.id.option2)
    val option3: LinearLayout = itemView.findViewById(R.id.option3)
    val option4: LinearLayout = itemView.findViewById(R.id.option4)
    val option1count: TextView = itemView.findViewById(R.id.option1count)
    val option2count: TextView = itemView.findViewById(R.id.option2count)
    val option3count: TextView = itemView.findViewById(R.id.option3count)
    val option4count: TextView = itemView.findViewById(R.id.option4count)
    val option1text: TextView = itemView.findViewById(R.id.option1text)
    val option2text: TextView = itemView.findViewById(R.id.option2text)
    val option3text: TextView = itemView.findViewById(R.id.option3text)
    val option4text: TextView = itemView.findViewById(R.id.option4text)
    val option1fill: View = itemView.findViewById(R.id.option1fill)
    val option2fill: View = itemView.findViewById(R.id.option2fill)
    val option3fill: View = itemView.findViewById(R.id.option3fill)
    val option4fill: View = itemView.findViewById(R.id.option4fill)
    val option1unfill: View = itemView.findViewById(R.id.option1unfill)
    val option2unfill: View = itemView.findViewById(R.id.option2unfill)
    val option3unfill: View = itemView.findViewById(R.id.option3unfill)
    val option4unfill: View = itemView.findViewById(R.id.option4unfill)
    val userImage: ImageView = itemView.findViewById(R.id.userImage)
}
