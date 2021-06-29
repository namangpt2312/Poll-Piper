package com.example.pollpiper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.pollpiper.daos.PostDao
import kotlinx.android.synthetic.main.activity_create_post.*

class CreatePostActivity : AppCompatActivity() {

    private lateinit var postDao: PostDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)

        postDao = PostDao()

        postButton.setOnClickListener {
            val input = postInput.text.toString().trim()
            val option1 = option1text.text.toString().trim()
            val option2 = option2text.text.toString().trim()
            val option3 = option3text.text.toString().trim()
            val option4 = option4text.text.toString().trim()

            if(input.isEmpty())
                Toast.makeText(this, "Question cannot be empty!", Toast.LENGTH_SHORT).show()
            else if(option1.isEmpty() || option2.isEmpty() || option3.isEmpty() || option4.isEmpty())
                Toast.makeText(this, "Options cannot be empty!", Toast.LENGTH_SHORT).show()
            else {
                postDao.addPost(input, option1, option2, option3, option4)
                finish()
            }
        }
    }
}