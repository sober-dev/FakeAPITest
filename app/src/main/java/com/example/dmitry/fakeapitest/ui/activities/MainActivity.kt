package com.example.dmitry.fakeapitest.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.dmitry.fakeapitest.R
import com.example.dmitry.fakeapitest.ui.fragments.PostsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            return
        }

        fragmentManager.beginTransaction()
                .add(R.id.fragment_container, PostsFragment())
                .commit()
    }
}
