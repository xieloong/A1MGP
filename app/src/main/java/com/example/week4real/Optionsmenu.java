package com.example.week4real;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Optionsmenu extends Activity implements OnClickListener, StateBase  {

    TextView textView;
    TextView textView2;

    Switch aSwitch;
    Switch aSwitch2;

    private Button btn_back;

    @Override
    protected  void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //To make fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE); // Hide titlebar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);  // Hide topbar
        setContentView(R.layout.optionmenu);

        btn_back = (Button)findViewById(R.id.btn_back);
        btn_back.setOnClickListener(this);

        textView = findViewById(R.id.text);
        textView2 = findViewById(R.id.text2);

        aSwitch = findViewById(R.id.switch1);
        aSwitch2 = findViewById(R.id.switch2);

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(aSwitch.isChecked()) {
                    textView.setText("Toggle BGM: on");

                }
                else
                {
                    textView.setText("Toggle BGM: off");
                }
            }
        });

        aSwitch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(aSwitch2.isChecked()) {
                    textView2.setText("Toggle SFX: on");

                }
                else
                {
                    textView2.setText("Toggle SFX: off");
                }
            }
        });
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
        return null;
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
}
