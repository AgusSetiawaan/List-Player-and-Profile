package com.agus.project.dicodingtest.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
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
    private OnCLickAction onCLickAction;

    public PlayersAdapter(List<PlayerData> listPlayers, OnCLickAction onCLickAction) {
        setPlayerList(listPlayers);
        this.onCLickAction = onCLickAction;
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
        CardView cardPlayer;

        public PlayerViewHolder(@NonNull View itemView) {
            super(itemView);
            playerImage = itemView.findViewById(R.id.player_image);
            playerName = itemView.findViewById(R.id.player_name);
            playerClub = itemView.findViewById(R.id.player_club);
            playerPosition = itemView.findViewById(R.id.player_position);
            cardPlayer = itemView.findViewById(R.id.card_player);
        }

        void bind(final PlayerData playerData){
            if(playerData.getStrCutout()!=null){
                Glide.with(itemView).load(playerData.getStrCutout()).into(playerImage);
            }
            else{
                Glide.with(itemView).load(playerData.getStrThumb()).into(playerImage);
            }
            playerName.setText(playerData.getStrPlayer());
            playerClub.setText(playerData.getStrTeam());
            playerPosition.setText(playerData.getStrPosition());
            cardPlayer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onCLickAction.action(playerData);
                }
            });
        }
    }

    public interface OnCLickAction{
        void action(PlayerData playerData);
    }
}
