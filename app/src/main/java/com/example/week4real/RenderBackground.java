package com.example.week4real;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceView;

public class RenderBackground implements EntityBase{

    private boolean isDone = false;
    private Bitmap bmp = null;
    private Bitmap Player = null;
    private Bitmap Scaledbmp = null; // I am going to scale the bmp based on the screen width and height
    private SurfaceView view = null;
    private float xPos, yPos;
    public int ScreenWidth, ScreenHeight;

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
        bmp = ResourceManager.Instance.GetBitmap(R.drawable.forest);


        // FInding the screen width & height to allow the images to scale according to it.
        DisplayMetrics metrics = _view.getResources().getDisplayMetrics();
        ScreenWidth = metrics.widthPixels;
        ScreenHeight = metrics.heightPixels;

        Scaledbmp = Bitmap.createScaledBitmap(bmp, ScreenWidth, ScreenHeight, true);


    }

    @Override
    public void Update(float _dt){

        if(GameSystem.Instance.GetIsPaused())
            return;

        xPos -= _dt * 100; // How fast you want to move the screen

        if (xPos <  -ScreenWidth){
            xPos = 0;
        }


        //Log.i("BACKGROUND", Float.toString(ScreenWidth));

    }

    @Override
    public void Render(Canvas _canvas){
        _canvas.drawBitmap(Scaledbmp, xPos, yPos, null); //1st image
        _canvas.drawBitmap(Scaledbmp, xPos + ScreenWidth, yPos, null); // 2nd image

    }


    @Override
    public boolean IsInit(){
        return bmp !=null;
    }

    @Override
    public int GetRenderLayer(){
        return LayerConstants.BACKGROUND_LAYER;
    }

    @Override
    public void SetRenderLayer(int _newLayer){
        return;
    }

    @Override
    public ENTITY_TYPE GetEntityType(){
        return ENTITY_TYPE.ENT_DEFAULT;
    }

    public static RenderBackground Create(){
        RenderBackground result = new RenderBackground();
        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_DEFAULT);
        return result;
    }
}
