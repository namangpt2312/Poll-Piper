package com.example.pollpiper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pollpiper.auth.LoginActivity
import com.example.pollpiper.auth.SignUpActivity
import com.example.pollpiper.daos.PostDao
import com.example.pollpiper.models.Post
import com.example.pollpiper.models.User
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), IPostAdapter1, IPostAdapter2, IPostAdapter3, IPostAdapter4 {

    private lateinit var postDao: PostDao
    private lateinit var adapter: PostAdapter
//    val auth = Firebase.auth
//    lateinit var user : User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        FirebaseFirestore.getInstance().collection("users").document(auth.uid!!).get()
//            .addOnSuccessListener {
//                user = it.toObject(User :: class.java)!!
//            }

        fab.setOnClickListener{
            val intent = Intent(this, CreatePostActivity::class.java)
            startActivity(intent)
        }

        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        postDao = PostDao()
        val postsCollections = postDao.postCollections
        val query = postsCollections.orderBy("createdAt", Query.Direction.DESCENDING)
        val recyclerViewOptions = FirestoreRecyclerOptions.Builder<Post>().setQuery(query, Post::class.java).build()

        adapter = PostAdapter(recyclerViewOptions, this, this, this, this)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.myPolls -> {
                val intent = Intent(this, MyPostsActivity :: class.java)
//                intent.putExtra(NAME, user.name)
//                intent.putExtra(IMAGE, user.imageUrl)
//                intent.putExtra(UID, user.uid)
                startActivity(intent)
            }
            R.id.profile -> {
                startActivity(Intent(this, SignUpActivity :: class.java))
            }
            R.id.signOut -> {
                Firebase.auth.signOut()
                startActivity(
                    Intent(this, SplashActivity:: class.java)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK))
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }

    override fun onOption1Clicked(postId: String) {
        postDao.updateOption1(postId)
    }

    override fun onOption2Clicked(postId: String) {
        postDao.updateOption2(postId)
    }

    override fun onOption3Clicked(postId: String) {
        postDao.updateOption3(postId)
    }

    override fun onOption4Clicked(postId: String) {
        postDao.updateOption4(postId)
    }
}