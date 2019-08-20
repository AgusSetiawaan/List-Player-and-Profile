package com.agus.project.dicodingtest.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.agus.project.dicodingtest.R;
import com.agus.project.dicodingtest.model.PlayerData;
import com.agus.project.dicodingtest.model.PlayerResult;
import com.agus.project.dicodingtest.presenter.DataPresenter;
import com.facebook.shimmer.ShimmerFrameLayout;

public class MainActivity extends AppCompatActivity implements DataPresenter.View, PlayersAdapter.OnCLickAction {

    private PlayersAdapter playersAdapter;
    private ShimmerFrameLayout mShimmerViewContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playersAdapter = new PlayersAdapter(null,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        mShimmerViewContainer = findViewById(R.id.shimmer_view_container);
        RecyclerView recyclerView = findViewById(R.id.list_player);
        DataPresenter dataPresenter = new DataPresenter(this);

        recyclerView.setAdapter(playersAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        dataPresenter.getEverything(133612L);


    }

    @Override
    public void onSuccessGetPlayers(PlayerResult playerResult) {
        playersAdapter.setPlayerList(playerResult.getPlayer());
        playersAdapter.notifyDataSetChanged();

        mShimmerViewContainer.stopShimmer();
        mShimmerViewContainer.setVisibility(View.GONE);
    }

    @Override
    public void onErrorGetPlayers(Throwable e) {
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();

        mShimmerViewContainer.stopShimmer();
        mShimmerViewContainer.setVisibility(View.GONE);
    }

    @Override
    public void action(PlayerData playerData) {
        Intent intent = new Intent(this,PlayersDetailActivity.class);
        intent.putExtra(PlayersDetailActivity._NAME,playerData.getStrPlayer());
        intent.putExtra(PlayersDetailActivity._TEAM,playerData.getStrTeam());
        intent.putExtra(PlayersDetailActivity._DATE,playerData.getDateBorn());
        intent.putExtra(PlayersDetailActivity._WAGE,playerData.getStrWage());
        intent.putExtra(PlayersDetailActivity._POS,playerData.getStrPosition());
        intent.putExtra(PlayersDetailActivity._DESC,playerData.getStrDescriptionEN());
        if(playerData.getStrCutout()!=null){
            intent.putExtra(PlayersDetailActivity._IMAGE,playerData.getStrCutout());
        }
        else{
            intent.putExtra(PlayersDetailActivity._IMAGE,playerData.getStrThumb());
        }
        startActivity(intent);

    }
}
