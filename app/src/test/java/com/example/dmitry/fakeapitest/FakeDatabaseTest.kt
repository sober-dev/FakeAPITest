package com.example.dmitry.fakeapitest

import com.example.dmitry.fakeapitest.db.CommentDao
import com.example.dmitry.fakeapitest.db.FakeDatabaseHelper
import com.example.dmitry.fakeapitest.db.PostDao
import com.example.dmitry.fakeapitest.db.UserDao
import com.example.dmitry.fakeapitest.models.*
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test

class FakeDatabaseTest {
    private val fakeDatabaseHelper = FakeDatabaseHelper(PostDao(), CommentDao(), UserDao())
    private val postDao = fakeDatabaseHelper.postDao
    private val commentDao = fakeDatabaseHelper.commentDao
    private val userDao = fakeDatabaseHelper.userDao

    @Test
    fun postsRWTest() {
        val post = Post(10, 100, "post title", "post text")
        postDao.create(arrayListOf(post))
        assertThat(postDao.query()[0], equalTo(post))
    }

    @Test
    fun commentsRWTest() {
        val comment = Comment(100, 1, "Bob", "t1@test.tt", "comment text")
        commentDao.create(arrayListOf(comment))
        assertThat(commentDao.query()[0], equalTo(comment))
    }

    @Test
    fun userRWTest() {
        val user = User(10,
                "Kate Brown",
                "Kate",
                "kate@test.tt",
                Address("Main Street", "123", "NYK", "123",
                        Geo("000", "111")),
                "333",
                "www.test.tt",
                Company("Apple", "abc", "qwerty")
        )
        userDao.create(arrayListOf(user))
        assertThat(userDao.query()[0], equalTo(user))
    }
}