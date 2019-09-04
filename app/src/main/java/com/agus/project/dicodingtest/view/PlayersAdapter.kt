package com.agus.project.dicodingtest.view

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.agus.project.dicodingtest.R
import com.agus.project.dicodingtest.model.PlayerData
import com.bumptech.glide.Glide

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Date

class PlayersAdapter(listPlayers: List<PlayerData>?, private val onCLickAction: OnCLickAction) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var listPlayers: List<PlayerData>? = null

    init {
        setPlayerList(listPlayers)
    }

    internal fun setPlayerList(listPlayers: List<PlayerData>?) {
        if (listPlayers == null) {
            this.listPlayers = ArrayList()
        } else {
            this.listPlayers = listPlayers
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(viewGroup.context)

        val view = layoutInflater.inflate(R.layout.players_content, viewGroup, false)
        return PlayerViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, pos: Int) {
        val playerViewHolder = viewHolder as PlayerViewHolder
        val playerData = listPlayers!![pos]
        playerViewHolder.bind(playerData)
    }

    override fun getItemCount(): Int {
        return if (listPlayers != null) listPlayers!!.size else 0
    }

    internal inner class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var playerImage: ImageView
        var playerName: TextView
        var playerClub: TextView
        var playerPosition: TextView
        var cardPlayer: CardView

        init {
            playerImage = itemView.findViewById(R.id.player_image)
            playerName = itemView.findViewById(R.id.player_name)
            playerClub = itemView.findViewById(R.id.player_club)
            playerPosition = itemView.findViewById(R.id.player_position)
            cardPlayer = itemView.findViewById(R.id.card_player)
        }

        fun bind(playerData: PlayerData) {
            if (playerData.strCutout != null) {
                Glide.with(itemView).load(playerData.strCutout).into(playerImage)
            } else {
                Glide.with(itemView).load(playerData.strThumb).into(playerImage)
            }
            playerName.text = playerData.strPlayer
            playerClub.text = playerData.strTeam
            playerPosition.text = playerData.strPosition
            cardPlayer.setOnClickListener { onCLickAction.action(playerData) }
        }
    }

    interface OnCLickAction {
        fun action(playerData: PlayerData)
    }


}
