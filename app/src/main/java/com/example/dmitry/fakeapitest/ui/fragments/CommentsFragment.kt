package com.example.dmitry.fakeapitest.ui.fragments

import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.dmitry.fakeapitest.MyApp
import com.example.dmitry.fakeapitest.R
import com.example.dmitry.fakeapitest.models.Comment
import com.example.dmitry.fakeapitest.models.User
import com.example.dmitry.fakeapitest.network.FakeApiService
import com.example.dmitry.fakeapitest.ui.adapters.CommentsAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_comments.*

class CommentsFragment : Fragment() {
    private var postId: Int = 0
    private var userId: Int = 0
    private val users: ArrayList<User> = ArrayList()
    private val comments: ArrayList<Comment> = ArrayList()
    private val commentsAdapter = CommentsAdapter(comments)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true

        val bundle = arguments
        if (bundle != null) {
            postId = bundle.getInt("postId")
            userId = bundle.getInt("userId")

            updateUserInfo(userId)
            updateComments(postId)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_comments, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        sr_layout_fragment_comments.setOnRefreshListener {
            updateUserInfo(userId)
            updateComments(postId)
        }
        rv_comments.layoutManager = LinearLayoutManager(activity)
        rv_comments.adapter = commentsAdapter

        if (users.isNotEmpty())
            tv_user_info.text = users[0].toString()
    }

    private fun updateComments(postId: Int) {
        FakeApiService.create().getComments(postId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ result ->
                    MyApp.fakeDatabaseHelper.commentDao.create(result)
                    showComments()
                    sr_layout_fragment_comments.isRefreshing = false
                }, { error ->
                    Toast.makeText(activity, "Error: " + error.message, Toast.LENGTH_SHORT).show()
                    error.printStackTrace()
                    sr_layout_fragment_comments.isRefreshing = false
                })
    }

    private fun updateUserInfo(userId: Int) {
        FakeApiService.create().getUser(userId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ result ->
                    MyApp.fakeDatabaseHelper.userDao.create(result)
                    showUserInfo()
                }, { error ->
                    Toast.makeText(activity, "Error: " + error.message, Toast.LENGTH_SHORT).show()
                    error.printStackTrace()
                })
    }

    private fun showUserInfo() {
        users.clear()
        users.addAll(MyApp.fakeDatabaseHelper.userDao.query())
        if (users.isNotEmpty())
            tv_user_info.text = users[0].toString()
    }

    private fun showComments() {
        comments.clear()
        comments.addAll(MyApp.fakeDatabaseHelper.commentDao.query())
        commentsAdapter.notifyDataSetChanged()
    }
}
