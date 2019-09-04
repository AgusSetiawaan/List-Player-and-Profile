package com.agus.project.dicodingtest.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView

import com.agus.project.dicodingtest.R
import com.bumptech.glide.Glide

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date

import butterknife.BindView
import butterknife.ButterKnife
import kotlinx.android.synthetic.main.activity_player_detail.*
import kotlinx.android.synthetic.main.players_content.*
import kotlinx.android.synthetic.main.players_content.player_club
import kotlinx.android.synthetic.main.players_content.player_name

class PlayersDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_detail)
        ButterKnife.bind(this)

        if (intent.extras != null) {
            setData(intent.extras!!)
        }

        back_btn.setOnClickListener { v ->
            startActivity(Intent(this,MainActivity::class.java))
        }

    }

    private fun setData(bundle: Bundle) {
        player_name!!.text = bundle.getString(_NAME)
        player_club!!.text = bundle.getString(_TEAM)
        player_date_born_!!.text = newFormatDate(bundle.getString(_DATE))
        player_wage!!.text = bundle.getString(_WAGE)
        player_pos!!.text = bundle.getString(_POS)
        player_description!!.text = bundle.getString(_DESC)
        Glide.with(this).load(bundle.getString(_IMAGE)).into(player_images!!)
    }

    private fun newFormatDate(oldDate: String?): String {
        val df = SimpleDateFormat("yyyy-MM-dd")
        val dfNew = SimpleDateFormat("dd MMMM yyyy")

        var date: Date? = null
        try {
            date = df.parse(oldDate)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return dfNew.format(date)
    }

    companion object {

        val _NAME = "NAME"
        val _TEAM = "TEAM"
        val _DATE = "DATE"
        val _WAGE = "WAGE"
        val _POS = "POS"
        val _DESC = "DESC"
        val _IMAGE = "IMAGE"
    }

    override fun onBackPressed() {
        startActivity(Intent(this,MainActivity::class.java))
    }
}
