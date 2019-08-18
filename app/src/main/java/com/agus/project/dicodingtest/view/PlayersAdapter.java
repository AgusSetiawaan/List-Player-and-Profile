package com.agus.project.dicodingtest.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.agus.project.dicodingtest.R;
import com.agus.project.dicodingtest.model.PlayerData;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class PlayersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<PlayerData> listPlayers;

    public PlayersAdapter(List<PlayerData> listPlayers) {
        setPlayerList(listPlayers);
    }

    void setPlayerList(List<PlayerData> listPlayers){
        if(listPlayers==null){
            this.listPlayers = new ArrayList<>();
        }
        else{
            this.listPlayers = listPlayers;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater =LayoutInflater.from(viewGroup.getContext());

        View view =layoutInflater.inflate(R.layout.players_content, viewGroup,false);
        return new PlayerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int pos) {
        PlayerViewHolder playerViewHolder =(PlayerViewHolder) viewHolder;
        PlayerData playerData = listPlayers.get(pos);
        playerViewHolder.bind(playerData);
    }

    @Override
    public int getItemCount() {
        return listPlayers!=null?listPlayers.size():0;
    }

    class PlayerViewHolder extends RecyclerView.ViewHolder{

        ImageView playerImage;
        TextView playerName;
        TextView playerClub;
        TextView playerPosition;

        public PlayerViewHolder(@NonNull View itemView) {
            super(itemView);
            playerImage = itemView.findViewById(R.id.player_image);
            playerName = itemView.findViewById(R.id.player_name);
            playerClub = itemView.findViewById(R.id.player_club);
            playerPosition = itemView.findViewById(R.id.player_position);
        }

        void bind(PlayerData playerData){
            Glide.with(itemView).load(playerData.getStrCutout()).into(playerImage);
            playerName.setText(playerData.getStrPlayer());
            playerClub.setText(playerData.getStrTeam());
            playerPosition.setText(playerData.getStrPosition());
        }
    }
}
