package com.example.week4real;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class Leaderboard extends Activity implements OnClickListener, StateBase{

    private Button btn_back;
    private Button btn_share;

    private TextView playerText;
    private TextView scoreText;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //To make fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE); // Hide titlebar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);  // Hide topbar

        setContentView(R.layout.leaderboard);

        btn_back = (Button)findViewById(R.id.btn_back);
        btn_back.setOnClickListener(this);

        btn_share = (Button)findViewById(R.id.btn_sharescore);
        btn_share.setOnClickListener(this);

        playerText = findViewById(R.id.text_playername);
        scoreText = findViewById(R.id.text_score);

        for(int index = 0; index < GameSystem.Instance.playerNames.size(); index++)
        {
            String PlayerName = GameSystem.Instance.playerNames.get(index);
            String playername = "PlayerName:  ";
            String playerscore = "Score:  ";
            String text_playernames = " ";
            String text_scores = " ";
            text_playernames = playername + PlayerName + System.lineSeparator();
            text_scores = playerscore + GameSystem.Instance.GetIntFromSave(PlayerName) + System.lineSeparator();
            playerText.append(text_playernames);
            scoreText.append(text_scores);
        }
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent();

        if (v == btn_back)
        {
            intent.setClass(this, Mainmenu.class);
        }
        startActivity(intent);
    }

    @Override
    public String GetName() {
        return "Leaderboard";
    }

    @Override
    public void OnEnter(SurfaceView _view) {

    }

    @Override
    public void OnExit() {

    }

    @Override
    public void Render(Canvas _canvas) {
    }

    @Override
    public void Update(float _dt) {


    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
