package com.agus.project.dicodingtest.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast

import com.agus.project.dicodingtest.R
import com.agus.project.dicodingtest.model.PlayerData
import com.agus.project.dicodingtest.model.PlayerResult
import com.agus.project.dicodingtest.network.PlayersUrl
import com.agus.project.dicodingtest.presenter.DataPresenter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.facebook.shimmer.ShimmerFrameLayout

import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.afollestad.materialdialogs.MaterialDialog
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), DataPresenter.View, PlayersAdapter.OnCLickAction {

    private var playersAdapter: PlayersAdapter? = null
    private var mShimmerViewContainer: ShimmerFrameLayout? = null

    var doubleBackToExitPressedOnce:Boolean = false




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        playersAdapter = PlayersAdapter(null, this)
        val linearLayoutManager = LinearLayoutManager(this)

        mShimmerViewContainer = findViewById(R.id.shimmer_view_container)
        val recyclerView = findViewById<RecyclerView>(R.id.list_player)
        val dataPresenter = DataPresenter(this,applicationContext)

        recyclerView.adapter = playersAdapter
        recyclerView.layoutManager = linearLayoutManager

        dataPresenter.getEverything(133612L)
        //        setPhotoAccount();
        
        image_account.setOnClickListener { v ->
            Log.e("masuk sini", "tes")
            startActivity(Intent(this, AboutActivity::class.java))
        }



    }

    override fun onSuccessGetPlayers(playerResult: PlayerResult) {
        playersAdapter!!.setPlayerList(playerResult.player)
        playersAdapter!!.notifyDataSetChanged()

        mShimmerViewContainer!!.stopShimmer()
        mShimmerViewContainer!!.visibility = View.GONE
    }

    override fun onErrorGetPlayers(e: Throwable) {
        Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()

        mShimmerViewContainer!!.stopShimmer()
        mShimmerViewContainer!!.visibility = View.GONE
    }

    override fun onErrorConnection() {
        Toast.makeText(applicationContext,"Cek koneksi internet anda",Toast.LENGTH_LONG).show()
    }

    override fun action(playerData: PlayerData) {
        val intent = Intent(this, PlayersDetailActivity::class.java)
        intent.putExtra(PlayersDetailActivity._NAME, playerData.strPlayer)
        intent.putExtra(PlayersDetailActivity._TEAM, playerData.strTeam)
        intent.putExtra(PlayersDetailActivity._DATE, playerData.dateBorn)
        intent.putExtra(PlayersDetailActivity._WAGE, playerData.strWage)
        intent.putExtra(PlayersDetailActivity._POS, playerData.strPosition)
        intent.putExtra(PlayersDetailActivity._DESC, playerData.strDescriptionEN)
        if (playerData.strCutout != null) {
            intent.putExtra(PlayersDetailActivity._IMAGE, playerData.strCutout)
        } else {
            intent.putExtra(PlayersDetailActivity._IMAGE, playerData.strThumb)
        }
        startActivity(intent)
    }

    override fun onBackPressed() {
        if(doubleBackToExitPressedOnce){
            super.onBackPressed()
            return
        }

        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this,"Please click BACK again to exit",Toast.LENGTH_SHORT).show()

        Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false },2000)
    }

}
