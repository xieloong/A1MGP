package com.example.week4real;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import java.util.Set;

public class Platform implements EntityBase, Collidable{

    private Bitmap bmp,scaledbmp = null;
    private boolean isDone = false;
    private float xPos,yPos;
    private boolean isInit = false;
    private boolean hasTouched = false;
    public int ScreenWidth, ScreenHeight;

    int Height, Width;

    Platform()
    {

    }

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
        scaledbmp = Bitmap.createScaledBitmap(bmp,ScreenWidth/5,ScreenHeight/30,true);
        Width = scaledbmp.getWidth();
        Height = scaledbmp.getHeight();
        isInit = true;
    }
    @Override
     public void Update(float _dt) {

        if(GameSystem.Instance.GetIsPaused())
            return;

        xPos -= _dt * 100;
        Log.i("PlatformHeight", Integer.toString(Height));
        // Delete Platform outside of screen
        if(xPos < -ScreenWidth)
        {
            SetIsDone(true);
        }
    }
    @Override
    public void Render(Canvas _canvas){
        // Basic Rendering
        _canvas.drawBitmap(scaledbmp, xPos, yPos ,null);
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

    @NonNull
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
    public String GetType() {
        return "Platform";
    }

    @Override
    public float GetPosX() {
        return xPos;
    }

    @Override
    public float GetPosY() {
        return yPos;
    }

    @Override
    public float GetBottom() {
        return GetPosY() + Height;
    }

    @Override
    public float GetRight() {
        return GetPosX() + Width;
    }


    @Override
    public float GetRadius() {
        return scaledbmp.getWidth();
    }
    @Override
    public ENTITY_TYPE GetEntityType(){
        return ENTITY_TYPE.ENT_PLATFORM;
    }

    @Override
    public void OnHit(Collidable _other) {
//        if(_other.GetType() != this.GetType()
//                && _other.GetType() !=  "SmurfEntity") {  // Another entity
//            SetIsDone(true);
//        }
    }
}
