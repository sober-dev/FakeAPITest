package com.example.dmitry.fakeapitest.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dmitry.fakeapitest.R
import com.example.dmitry.fakeapitest.models.Post
import kotlinx.android.synthetic.main.post_item.view.*

class PostsAdapter(private val posts: List<Post>, private val clickListener: (Post) -> Unit) :
        RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            PostsViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.post_item, parent, false))

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) =
            holder.bind(posts, position, clickListener)

    override fun getItemCount() = posts.size

    class PostsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(result: List<Post>, position: Int, clickListener: (Post) -> Unit) {
            val post: Post = result.get(position)
            itemView.tv_title.text = post.title
            itemView.tv_body.text = post.body
            itemView.setOnClickListener { clickListener(post) }
        }
    }
}