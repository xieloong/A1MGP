package com.example.week4real;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.SurfaceView;

public class RenderTextEntity implements EntityBase{
    private boolean isDone = false;
// Paint takes Red, Green, Blue and also there is an alpha

    // Paint
    Paint paint = new Paint();
    private int red = 0,green = 0,blue = 0; // 0 - 255

    // Use for loading FPS so need da more parameters!
    int frameCount; // Framecount
    long lastTime = 0; // Time
    long lastFPSTime = 0; // Last time frame
    float fps; // Used to store FPS
    Typeface myfont; // Use for loading font

    @Override
    public boolean IsDone(){
        return isDone;
    }

    @Override
    public void SetIsDone(boolean _isDone){
        isDone = _isDone;
    }

    @Override
    // For us to intialize or load resource eg: images
    public void Init(SurfaceView _view){
        myfont = Typeface.createFromAsset(_view.getContext().getAssets(),"fonts/Gemcut.otf");
    }

    @Override
    public void Update(float _dt){
        // Load a Text FPS: the real FPS
        // Get actual FPS
        frameCount++;
        long currentTime = System.currentTimeMillis(); // Get the current time from system

        lastTime = currentTime; // Last time = current time

        if (currentTime - lastFPSTime > 1000)
        {
            fps = (frameCount * 1000.f) / (currentTime - lastFPSTime);
            lastFPSTime = currentTime;
            frameCount = 0;
        }
    }

    @Override
    public void Render(Canvas _canvas){
        // We using Paint which is part of graphic library
        paint.setARGB(255,0,0,0); // alpha = 255 , which meant it is not transparent and opacity is 100%
        paint.setStrokeWidth(200); // How Thick the font is
        paint.setTypeface(myfont); // Use the font type loaded
        paint.setTextSize(70); // Font Size
        _canvas.drawText("FPS: " + fps,30,80,paint); // For now, default number but u can use _view.getWidth/ ?
    }

    @Override
    public boolean IsInit(){
        //return bmp !=null;
        return false;
    }

    @Override
    public int GetRenderLayer(){
        return LayerConstants.RENDERTEXT_LAYER; // Check from Layerconstants
    }

    @Override
    public void SetRenderLayer(int _newLayer){
        return;
    }

    @Override
    public ENTITY_TYPE GetEntityType(){
        return ENTITY_TYPE.ENT_DEFAULT;
    }

    public static RenderTextEntity Create(){
        RenderTextEntity result = new RenderTextEntity();
        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_DEFAULT);
        return result;
    }

    @Override
    public float GetPositionX(){
        return 0;
    }

    @Override
    public float GetPositionY(){
        return 0;
    }
}
