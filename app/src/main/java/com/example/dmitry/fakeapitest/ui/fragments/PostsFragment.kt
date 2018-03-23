package com.example.dmitry.fakeapitest.ui.fragments

import android.app.Fragment
import android.app.FragmentTransaction
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.dmitry.fakeapitest.MyApp
import com.example.dmitry.fakeapitest.R
import com.example.dmitry.fakeapitest.models.Post
import com.example.dmitry.fakeapitest.network.FakeApiService
import com.example.dmitry.fakeapitest.ui.adapters.PostsAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_posts.*

class PostsFragment : Fragment() {
    private val posts: ArrayList<Post> = ArrayList()
    private val postsAdapter = PostsAdapter(posts, { postItem: Post -> postItemClicked(postItem) })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true

        if (MyApp.fakeDatabaseHelper.postDao.query().isEmpty())
            updatePosts()
        else
            showPosts()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_posts, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        sr_layout_fragment_post.setOnRefreshListener {
            updatePosts()
        }
        rv_posts.layoutManager = LinearLayoutManager(activity)
        rv_posts.adapter = postsAdapter
    }

    private fun updatePosts() {
        FakeApiService.create().getPosts()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ result ->
                    MyApp.fakeDatabaseHelper.postDao.create(result)
                    showPosts()
                    sr_layout_fragment_post.isRefreshing = false
                }, { error ->
                    Toast.makeText(activity, "Error: " + error.message, Toast.LENGTH_SHORT).show()
                    error.printStackTrace()
                    sr_layout_fragment_post.isRefreshing = false
                })
    }

    private fun showPosts() {
        posts.clear()
        posts.addAll(MyApp.fakeDatabaseHelper.postDao.query())
        postsAdapter.notifyDataSetChanged()
    }

    private fun postItemClicked(postItem: Post) {
        navigateToCommentsFragment(postItem.id, postItem.userId)
    }

    private fun navigateToCommentsFragment(postId: Int, userId: Int) {
        val commentsFragment = CommentsFragment()
        val bundle = Bundle()
        bundle.putInt("postId", postId)
        bundle.putInt("userId", userId)
        commentsFragment.arguments = bundle

        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, commentsFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
                .commit()
    }
}
