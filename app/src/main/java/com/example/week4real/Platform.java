package com.example.week4real;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

public class Platform implements EntityBase{

    private Bitmap bmp = null;
    private boolean isDone = false;
    private float xPos,yPos;
    private boolean isInit = false;
    private boolean hasTouched = false;
    public int ScreenWidth, ScreenHeight;
    public float EndPositionX,EndPositionY;
    Platform(float xPosition, float yPosition){
        xPos = xPosition;
        yPos = yPosition;
    }

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
        bmp = ResourceManager.Instance.GetBitmap(R.drawable.platform);
        DisplayMetrics metrics = _view.getResources().getDisplayMetrics();
        ScreenWidth = metrics.widthPixels;
        ScreenHeight = metrics.heightPixels;
        EndPositionX = xPos;
        EndPositionY = yPos;
        isInit = true;
    }
    @Override
     public void Update(float _dt) {
        xPos -= _dt * 100;


    }
    @Override
    public void Render(Canvas _canvas){
        // Basic Rendering
        _canvas.drawBitmap(bmp, xPos, yPos ,null);
        return;
    }
    @Override
    public boolean IsInit(){
        return isInit;
    }
    @Override
    public int GetRenderLayer(){
        return LayerConstants.GAMEOBJECTS_LAYER;
    }
    @Override
    public void SetRenderLayer(int _newLayer){
        return;
    }

    public static Platform Create(float xPosition, float yPosition){
        Platform object = new Platform(xPosition,yPosition);
        EntityManager.Instance.AddEntity(object,ENTITY_TYPE.ENT_PLATFORM);
        return object;
    }
/*
    public static Platform Create(int _layer,float xPosition, float yPosition){
        Platform object = Create(xPosition,yPosition);
        object.SetRenderLayer(_layer);
        return object;
    }
*/


    @Override
    public ENTITY_TYPE GetEntityType(){
        return ENTITY_TYPE.ENT_PLATFORM;
    }
}
