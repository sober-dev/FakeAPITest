package com.example.dmitry.fakeapitest

import android.app.Application
import com.example.dmitry.fakeapitest.db.CommentDao
import com.example.dmitry.fakeapitest.db.FakeDatabaseHelper
import com.example.dmitry.fakeapitest.db.PostDao
import com.example.dmitry.fakeapitest.db.UserDao

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        fakeDatabaseHelper = FakeDatabaseHelper(PostDao(), CommentDao(), UserDao())
    }

    companion object {
        lateinit var fakeDatabaseHelper: FakeDatabaseHelper
    }
}