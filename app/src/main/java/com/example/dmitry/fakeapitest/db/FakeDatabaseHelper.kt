package com.example.dmitry.fakeapitest.db

import com.example.dmitry.fakeapitest.models.Comment
import com.example.dmitry.fakeapitest.models.Post
import com.example.dmitry.fakeapitest.models.User
import java.util.*

class FakeDatabaseHelper(
        val postDao: PostDao,
        val commentDao: CommentDao,
        val userDao: UserDao
)

class PostDao {
    private val posts: ArrayList<Post> = ArrayList()

    fun create(data: List<Post>) {
        posts.clear()
        posts.addAll(data)
    }

    fun query() = posts
}

class CommentDao {
    private val comments: ArrayList<Comment> = ArrayList()

    fun create(data: List<Comment>) {
        comments.clear()
        comments.addAll(data)
    }

    fun query() = comments
}

class UserDao {
    private val users: ArrayList<User> = ArrayList()

    fun create(data: List<User>) {
        users.clear()
        users.addAll(data)
    }

    fun query() = users
}