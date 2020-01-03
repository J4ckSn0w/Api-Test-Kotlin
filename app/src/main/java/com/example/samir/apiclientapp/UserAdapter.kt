package com.example.samir.apiclientapp
import android.content.Intent
import android.support.v4.content.ContextCompat.createDeviceProtectedStorageContext
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import kotlinx.android.synthetic.main.card_layout.view.address
import kotlinx.android.synthetic.main.user_layout.view.*
import kotlinx.android.synthetic.main.user_layout.view.name
import kotlinx.android.synthetic.main.user_layout.view.*

class UserAdapter (
        private val userList: ArrayList<User>,
        private val listener: (User) -> Unit
    ): RecyclerView.Adapter<UserAdapter.ArticleHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ArticleHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.user_layout, parent, false))

    override fun onBindViewHolder(holder: ArticleHolder, position: Int) = holder.bind(userList[position], listener)

    override fun getItemCount() = userList.size

    class ArticleHolder(articleView: View): RecyclerView.ViewHolder(articleView) {

        fun bind(user: User, listener: (User) -> Unit) = with(itemView) {
                name.text = user.name
                username.text = user.username
                email.text = user.email
                address.text = user.address.street
                /*var btn_posts = findViewById(R.id.btn_posts) as Button
                btn_posts.setOnClickListener(object: View.OnClickListener{
                    override fun onClick(v:View?){
                        val intent = Intent(context, MainActivity::class.java).apply {
                            putExtra("opcion",1)
                        }
                        context.startActivity(intent)
                    }
                })*/
            setOnClickListener { listener(user)
                val intent = Intent(context, MainActivity::class.java).apply {
                    putExtra("opcion",1)
                    putExtra("usuario",user.id)
                }
                context.startActivity(intent)
            }

        }
    }
}
