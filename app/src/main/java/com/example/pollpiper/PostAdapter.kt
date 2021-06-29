package com.example.pollpiper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pollpiper.daos.UserDao
import com.example.pollpiper.models.Post
import com.example.pollpiper.models.User
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class PostAdapter(options: FirestoreRecyclerOptions<Post>, val listener1: IPostAdapter1, val listener2: IPostAdapter2, val listener3: IPostAdapter3, val listener4: IPostAdapter4) : FirestoreRecyclerAdapter<Post, PostViewHolder>(
    options
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val viewHolder =  PostViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false))
        viewHolder.option1.setOnClickListener{
            listener1.onOption1Clicked(snapshots.getSnapshot(viewHolder.adapterPosition).id)
        }
        viewHolder.option2.setOnClickListener{
            listener2.onOption2Clicked(snapshots.getSnapshot(viewHolder.adapterPosition).id)
        }
        viewHolder.option3.setOnClickListener{
            listener3.onOption3Clicked(snapshots.getSnapshot(viewHolder.adapterPosition).id)
        }
        viewHolder.option4.setOnClickListener{
            listener4.onOption4Clicked(snapshots.getSnapshot(viewHolder.adapterPosition).id)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int, model: Post) {
//        GlobalScope.launch {
//            val userDao = UserDao()
//            val user = userDao.getUserById(model.createdBy.uid).await().toObject(User::class.java)!!
//            withContext(Dispatchers.Main) {
//                holder.userText.text = user.name
//                Glide.with(holder.userImage.context).load(user.imageUrl).circleCrop().into(holder.userImage)
//            }
//        }

        holder.userImage.setImageResource(R.drawable.defaultavatar)
        holder.userText.text = ""

        val userDao = UserDao()
        userDao.getUserById(model.createdBy)
            .addOnSuccessListener {
                val user = it.toObject(User::class.java)!!
                holder.userText.text = user.name
                Glide.with(holder.userImage.context).load(user.imageUrl)
                    .placeholder(R.drawable.defaultavatar)
                    .error(R.drawable.defaultavatar)
                    .circleCrop()
                    .into(holder.userImage)
            }

//        holder.userText.text = model.createdBy.name
//        Glide.with(holder.userImage.context).load(model.createdBy.imageUrl).circleCrop().into(holder.userImage)
        holder.postText.text = model.text

        holder.option1text.text = model.option1
        holder.option2text.text = model.option2
        holder.option3text.text = model.option3
        holder.option4text.text = model.option4
        holder.createdAt.text = Utils.getTimeAgo(model.createdAt)

        val auth = Firebase.auth
        val currentUserId = auth.uid!!
        val isOption1 = model.option1count.contains(currentUserId)
        val isOption2 = model.option2count.contains(currentUserId)
        val isOption3 = model.option3count.contains(currentUserId)
        val isOption4 = model.option4count.contains(currentUserId)

        if(isOption1 || isOption2 || isOption3 || isOption4) {
            val total = model.option1count.size + model.option2count.size + model.option3count.size + model.option4count.size
            val option1per = (model.option1count.size * 100f) / (total * 1.0f)
            val option2per = (model.option2count.size * 100f) / (total * 1.0f)
            val option3per = (model.option3count.size * 100f) / (total * 1.0f)
            val option4per = (model.option4count.size * 100f) / (total * 1.0f)

            holder.option1count.text = "${option1per.toInt()}%"
            holder.option2count.text = "${option2per.toInt()}%"
            holder.option3count.text = "${option3per.toInt()}%"
            holder.option4count.text = "${option4per.toInt()}%"

            holder.option1fill.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, option1per)
            holder.option2fill.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, option2per)
            holder.option3fill.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, option3per)
            holder.option4fill.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, option4per)

            holder.option1unfill.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 100f - option1per)
            holder.option2unfill.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 100f - option2per)
            holder.option3unfill.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 100f - option3per)
            holder.option4unfill.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 100f - option4per)
        }
        else {
            holder.option1count.text = ""
            holder.option2count.text = ""
            holder.option3count.text = ""
            holder.option4count.text = ""

            holder.option1fill.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 0f)
            holder.option2fill.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 0f)
            holder.option3fill.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 0f)
            holder.option4fill.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 0f)

            holder.option1unfill.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1f)
            holder.option2unfill.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1f)
            holder.option3unfill.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1f)
            holder.option4unfill.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1f)
        }
    }
}

interface IPostAdapter1 {
    fun onOption1Clicked(postId: String)
}

interface IPostAdapter2 {
    fun onOption2Clicked(postId: String)
}

interface IPostAdapter3 {
    fun onOption3Clicked(postId: String)
}

interface IPostAdapter4 {
    fun onOption4Clicked(postId: String)
}