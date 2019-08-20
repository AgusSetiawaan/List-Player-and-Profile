package com.agus.project.dicodingtest.view;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.agus.project.dicodingtest.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class AboutActivity extends AppCompatActivity {

    @BindView(R.id.photo)
    CircleImageView photo;

    @BindView(R.id.nama)
    TextView nama;

    @BindView(R.id.email)
    TextView email;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);

        Drawable myDrawable = getResources().getDrawable(R.drawable.agus);
        photo.setImageDrawable(myDrawable);
        nama.setText("Agus Setiawan");
        email.setText("agus103setiawan@gmail.com");
    }
}
