package com.example.pollpiper.daos

import com.example.pollpiper.models.Post
import com.example.pollpiper.models.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class PostDao {

    val db = FirebaseFirestore.getInstance()
    val postCollections = db.collection("posts")
    val auth = Firebase.auth

    fun addPost(text: String, option1: String, option2: String, option3: String, option4: String) {
        GlobalScope.launch {
            val currentUserId = auth.uid!!
//            val userDao = UserDao()
//            val user = userDao.getUserById(currentUserId).await().toObject(User::class.java)!!

            val currentTime = System.currentTimeMillis()
//            val post = Post(text, user, currentTime, option1, option2, option3, option4)
            val post = Post(text, currentUserId, currentTime, option1, option2, option3, option4)
            postCollections.document().set(post)
        }
    }

    fun getPostById(postId: String): Task<DocumentSnapshot> {
        return postCollections.document(postId).get()
    }

    fun updateOption1(postId: String) {
        GlobalScope.launch {
            val currentUserId = auth.uid!!
            val post = getPostById(postId).await().toObject(Post::class.java)!!
            val isOption1 = post.option1count.contains(currentUserId)
            val isOption2 = post.option2count.contains(currentUserId)
            val isOption3 = post.option3count.contains(currentUserId)
            val isOption4 = post.option4count.contains(currentUserId)

            if(isOption1) {
                post.option1count.remove(currentUserId)
            } else {
                post.option1count.add(currentUserId)
            }

            if(isOption2) {
                post.option2count.remove(currentUserId)
            }

            if(isOption3) {
                post.option3count.remove(currentUserId)
            }

            if(isOption4) {
                post.option4count.remove(currentUserId)
            }

            postCollections.document(postId).set(post)
        }
    }

    fun updateOption2(postId: String) {
        GlobalScope.launch {
            val currentUserId = auth.uid!!
            val post = getPostById(postId).await().toObject(Post::class.java)!!
            val isOption1 = post.option1count.contains(currentUserId)
            val isOption2 = post.option2count.contains(currentUserId)
            val isOption3 = post.option3count.contains(currentUserId)
            val isOption4 = post.option4count.contains(currentUserId)

            if(isOption2) {
                post.option2count.remove(currentUserId)
            } else {
                post.option2count.add(currentUserId)
            }

            if(isOption1) {
                post.option1count.remove(currentUserId)
            }

            if(isOption3) {
                post.option3count.remove(currentUserId)
            }

            if(isOption4) {
                post.option4count.remove(currentUserId)
            }

            postCollections.document(postId).set(post)
        }
    }

    fun updateOption3(postId: String) {
        GlobalScope.launch {
            val currentUserId = auth.uid!!
            val post = getPostById(postId).await().toObject(Post::class.java)!!
            val isOption1 = post.option1count.contains(currentUserId)
            val isOption2 = post.option2count.contains(currentUserId)
            val isOption3 = post.option3count.contains(currentUserId)
            val isOption4 = post.option4count.contains(currentUserId)

            if(isOption3) {
                post.option3count.remove(currentUserId)
            } else {
                post.option3count.add(currentUserId)
            }

            if(isOption2) {
                post.option2count.remove(currentUserId)
            }

            if(isOption1) {
                post.option1count.remove(currentUserId)
            }

            if(isOption4) {
                post.option4count.remove(currentUserId)
            }

            postCollections.document(postId).set(post)
        }
    }

    fun updateOption4(postId: String) {
        GlobalScope.launch {
            val currentUserId = auth.uid!!
            val post = getPostById(postId).await().toObject(Post::class.java)!!
            val isOption1 = post.option1count.contains(currentUserId)
            val isOption2 = post.option2count.contains(currentUserId)
            val isOption3 = post.option3count.contains(currentUserId)
            val isOption4 = post.option4count.contains(currentUserId)

            if(isOption4) {
                post.option4count.remove(currentUserId)
            } else {
                post.option4count.add(currentUserId)
            }

            if(isOption2) {
                post.option2count.remove(currentUserId)
            }

            if(isOption3) {
                post.option3count.remove(currentUserId)
            }

            if(isOption1) {
                post.option1count.remove(currentUserId)
            }

            postCollections.document(postId).set(post)
        }
    }
}