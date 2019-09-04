package com.agus.project.dicodingtest.view

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView

import com.agus.project.dicodingtest.R

import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_about.*

class AboutActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        ButterKnife.bind(this)

        val myDrawable = resources.getDrawable(R.drawable.agus)
        photo.setImageDrawable(myDrawable)
        nama.text = "Agus Setiawan"
        email.text = "agus103setiawan@gmail.com"
        note.text = "API from https://www.thesportsdb.com/"

        btn_back.setOnClickListener { v ->
            startActivity(Intent(this,MainActivity::class.java))
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this,MainActivity::class.java))
    }

//    @OnClick(R.id.btn_back)
//    fun clickBack(){
//        startActivity(Intent(this,MainActivity::class.java))
//    }
}
