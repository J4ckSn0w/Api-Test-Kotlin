package com.example.samir.apiclientapp
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.card_layout.view.address
import kotlinx.android.synthetic.main.comment_layout.view.*

class CommentAdapter(
        private val commentList: ArrayList<Comment>,
        private val listener: (Comment) -> Unit
    ): RecyclerView.Adapter<CommentAdapter.ArticleHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ArticleHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.comment_layout, parent, false))

    override fun onBindViewHolder(holder: ArticleHolder, position: Int) = holder.bind(commentList[position], listener)

    override fun getItemCount() = commentList.size

    class ArticleHolder(articleView: View): RecyclerView.ViewHolder(articleView) {

        fun bind(comment: Comment, listener: (Comment) -> Unit) = with(itemView) {
                name.text = comment.name
                email.text = comment.email
                address.text = comment.body
            setOnClickListener { listener(comment) }
        }
    }
}
