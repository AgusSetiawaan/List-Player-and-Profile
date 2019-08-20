package com.agus.project.dicodingtest.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.agus.project.dicodingtest.R;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlayersDetailActivity extends AppCompatActivity {

    public static final String _NAME = "NAME";
    public static final String _TEAM = "TEAM";
    public static final String _DATE = "DATE";
    public static final String _WAGE = "WAGE";
    public static final String _POS = "POS";
    public static final String _DESC = "DESC";
    public static final String _IMAGE= "IMAGE";


    @BindView(R.id.player_name)
    TextView playerName;

    @BindView(R.id.player_club)
    TextView playerTeam;

    @BindView(R.id.player_date_born_)
    TextView dateBorn;

    @BindView(R.id.player_wage)
    TextView playerWage;

    @BindView(R.id.player_position)
    TextView playerPosition;

    @BindView(R.id.player_description)
    TextView playerDesc;

    @BindView(R.id.player_image)
    ImageView playerImage;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_detail);
        ButterKnife.bind(this);

        if(getIntent().getExtras()!=null){
            setData(getIntent().getExtras());
        }

    }

    private void setData(Bundle bundle){
        playerName.setText(bundle.getString(_NAME));
        playerTeam.setText(bundle.getString(_TEAM));
        dateBorn.setText(bundle.getString(_DATE));
        playerWage.setText(bundle.getString(_WAGE));
        playerPosition.setText(bundle.getString(_POS));
        playerDesc.setText(bundle.getString(_DESC));
        Glide.with(this).load(bundle.getString(_IMAGE)).into(playerImage);
    }
}
