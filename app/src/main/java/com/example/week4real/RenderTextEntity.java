package com.example.week4real;

// Created by TanSiewLan2020

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

public class RenderTextEntity implements EntityBase{

        // Paint object
        Paint paint = new Paint();
        private int red = 0, green = 0, blue = 0;

        private boolean isDone = false;
        private boolean isInit = false;

        int frameCount;
        long lastTime = 0;
        long lastFPSTime = 0;
        float fps;

        float points = 0;
        int ScreenWidth,ScreenHeight;
        int displayScore;
        Typeface myfont;

        @Override
        public boolean IsDone() {
            return isDone;
        }

        @Override
        public void SetIsDone(boolean _isDone) {
            isDone = _isDone;
        }

        @Override
        public void Init(SurfaceView _view) {

            // Week 8 Use my own fonts
            myfont = Typeface.createFromAsset(_view.getContext().getAssets(), "fonts/Gemcut.otf");
           // myfont = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL);

            DisplayMetrics metrics= _view.getResources().getDisplayMetrics();
            ScreenHeight = metrics.heightPixels;
            ScreenWidth = metrics.widthPixels;


            isInit = true;


        }

        @Override
        public void Update(float _dt) {

            // get actual fps

            frameCount++;

            long currentTime = System.currentTimeMillis();

            lastTime = currentTime;

            if(currentTime - lastFPSTime > 1000)
            {
                fps = (frameCount * 1000.f) / (currentTime - lastFPSTime);
                lastFPSTime = currentTime;
                frameCount = 0;
            }


            // Points According to Time
            points += 0.5f * _dt;

        }

        @Override
        public void Render(Canvas _canvas)
        {

            Paint paint = new Paint();
            paint.setARGB(255, 0,0,0);
            //paint.setStrokeWidth(200);
            paint.setTypeface(myfont);
            paint.setTextSize(70);
            _canvas.drawText("FPS: " + fps, 30, 80, paint);

            Paint score = new Paint();
            score.setARGB(255, 0,0,0);
            //paint.setStrokeWidth(200);
            score.setTypeface(myfont);
            score.setTextSize(70);
            displayScore = Math.round(points);
            _canvas.drawText("Score: " + displayScore, 1400, 80, score);

        }

        @Override
        public boolean IsInit() {
            return true;
        }

        @Override
        public int GetRenderLayer() {
            return LayerConstants.RENDERTEXT_LAYER;
        }

        @Override
        public void SetRenderLayer(int _newLayer) {
            return;
        }



    @Override
        public ENTITY_TYPE GetEntityType(){ return ENTITY_TYPE.ENT_TEXT;}

        public static RenderTextEntity Create()
        {
            RenderTextEntity result = new RenderTextEntity();
            EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_TEXT);
            return result;
        }

        public int GetPoints()
        {
            return displayScore;
        }

}

