package com.example.pollpiper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pollpiper.daos.PostDao
import com.example.pollpiper.daos.UserDao
import com.example.pollpiper.models.Post
import com.example.pollpiper.models.User
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_my_posts.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

//const val UID = "uid"
//const val NAME = "name"
//const val IMAGE = "imageUrl"

class MyPostsActivity : AppCompatActivity() {

    private lateinit var postDao: PostDao
    private lateinit var adapter: MyPostAdapter
    val auth = Firebase.auth

//    private val uid : String by lazy {
//        intent.getStringExtra(UID)!!
//    }
//    private val name : String by lazy {
//        intent.getStringExtra(NAME)!!
//    }
//    private val imageUrl : String by lazy {
//        intent.getStringExtra(IMAGE)!!
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_posts)

        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
//        val user = User(name, imageUrl, uid)
        postDao = PostDao()
        val postsCollections = postDao.postCollections
        val query = postsCollections.whereEqualTo("createdBy", auth.uid!!).orderBy("createdAt", Query.Direction.DESCENDING)
        val recyclerViewOptions = FirestoreRecyclerOptions.Builder<Post>().setQuery(query, Post::class.java).build()

        adapter = MyPostAdapter(recyclerViewOptions)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }
}