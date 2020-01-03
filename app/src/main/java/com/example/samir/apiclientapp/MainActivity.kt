package com.example.samir.apiclientapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    public var userglobal:Int = 1
    public var idpost:Int = 2

    val client by lazy {
        ArticlesApiClient.create()
    }

    var disposable: Disposable? = null

    val comment by lazy {
        CommentsApiClient.create()
    }

    var disposable2: Disposable? = null

    val user by lazy {
        UserApiClient.create()
    }

    var disposable3: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var n = intent.getIntExtra("opcion",0)
        var userid = intent.getIntExtra("usuario",100)
        var postid = intent.getIntExtra("post",1000)
        idpost = postid
        if(n==0)
            showUsers()
        if(n==1) {
            showArticles(userid)
        }
        if(n==2) {
            showComments(idpost)
        }

    }

    /* get list of articles */
    private fun showArticles(userid:Int) {

        disposable = client.getArticles()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> setupRecycler(result,userid) },
                        { error -> Log.e("ERROR", error.message) }
                        )

    }

    private fun showComments(idpost:Int) {

        disposable = comment.getComments()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> setupRecyclerComment(result) },
                        { error -> Log.e("ERROR", error.message) }
                )

    }

    private fun showUsers() {

        disposable = user.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> setupRecyclerUser(result) },
                        { error -> Log.e("ERROR", error.message) }
                )

    }

    fun setupRecycler(articleList: List<Article>,userid: Int) {

        var newList:ArrayList<Article> = ArrayList<Article>()
        newList

        userglobal = userid

        for(i in 0 .. articleList.size-1)
        {
            if(userglobal!=100)
            {
                if(articleList[i].userId == userglobal)
                {
                    newList.add(articleList[i])
                }
            }
            else
            {
                newList.add(articleList[i])
            }
        }

        articles_recycler.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        articles_recycler.layoutManager = layoutManager
        articles_recycler.adapter = ArticleAdapter(newList){
            Log.v("Article", it.id.toString())
        }
    }

    fun setupRecyclerComment(articleList: List<Comment>) {

        var newList:ArrayList<Comment> = ArrayList<Comment>()
        newList

        for(i in 0 .. articleList.size-1)
        {
            if(articleList[i].postId == idpost)
            {
                newList.add(articleList[i])
            }
        }

        //articleList = newList

        articles_recycler.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        articles_recycler.layoutManager = layoutManager
        articles_recycler.adapter = CommentAdapter(newList){
            Log.v("Comment", it.id.toString())
            Log.v("size de comentarios", articleList.size.toString())
            Log.v("size de Nuevo", newList.size.toString())
            Log.v("name", newList[0].name.toString())
        }
    }

    fun setupRecyclerUser(articleList: List<User>) {

        var newList:ArrayList<User> = ArrayList<User>()
        newList

        for(i in 0 .. articleList.size-1)
        {
            newList.add(articleList[i])
        }

        //articleList = newList

        articles_recycler.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        articles_recycler.layoutManager = layoutManager
        articles_recycler.adapter = UserAdapter(newList){
            Log.v("User", it.id.toString())
        }

    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }

}
