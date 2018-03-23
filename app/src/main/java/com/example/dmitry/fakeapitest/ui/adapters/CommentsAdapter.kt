package com.example.dmitry.fakeapitest.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dmitry.fakeapitest.R
import com.example.dmitry.fakeapitest.models.Comment
import kotlinx.android.synthetic.main.comment_item.view.*

class CommentsAdapter(private val comments: List<Comment>) :
        RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            CommentsViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.comment_item, parent, false))

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) =
            holder.bind(comments, position)

    override fun getItemCount() = comments.size

    class CommentsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(result: List<Comment>, position: Int) {
            val comment: Comment = result[position]
            itemView.tv_name.text = "name: " + comment.name
            itemView.tv_email.text = "email: " + comment.email
            itemView.tv_body.text = comment.body
        }
    }
}