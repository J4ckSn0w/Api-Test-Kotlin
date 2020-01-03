package com.example.samir.apiclientapp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button

class menu_activity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu)

        val btn_usuarios = findViewById(R.id.btn_usuarios) as Button
        val btn_posts = findViewById(R.id.btn_posts) as Button

        btn_usuarios.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v:View?){
                val intent = Intent(this@menu_activity, MainActivity::class.java).apply {
                    //console.log("Entre")
                    putExtra("opcion",0)
                }
                startActivity(intent)

        }
        })

        btn_posts.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v:View?){
                val intent = Intent(this@menu_activity, MainActivity::class.java).apply {
                    //console.log("Entre")
                    putExtra("opcion",1)
                }
                startActivity(intent)

            }
        })

    }

}
