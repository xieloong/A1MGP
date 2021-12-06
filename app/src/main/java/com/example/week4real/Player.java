package com.example.week4real;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceView;

import java.util.Random;
import java.util.Set;


public class Player implements EntityBase{

    private Bitmap bmp = null;
    private boolean isDone = false;
    private float xPos,yPos;
    private boolean isInit = false;
    private boolean hasTouched = false;
    private float lifetime;


    Player(float xPosition, float yPosition){
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
        bmp = ResourceManager.Instance.GetBitmap(R.drawable.spritemann);
        isInit = true;

        // Define how we want the player to react or if it is enemy or obstacles, how it is going to appear as
       // Random ranGem = new Random();
        //xPos = ranGem.nextFloat()* _view.getWidth();
        //yPos = ranGem.nextFloat()* _view.getHeight();

        lifetime = 30.0f;

    }
    @Override
    public void Update(float _dt) {

        if(TouchManager.Instance.IsDown()){
            // Check Collision
            float imgRadius = bmp.getHeight() * 0.5f;
            if (Collision.SphereToSphere(TouchManager.Instance.GetPosX(),TouchManager.Instance.GetPosY(),0.0f,xPos,yPos,imgRadius))
            {
                SetIsDone(true);
            }
        }


    }
    @Override
    public void Render(Canvas _canvas){
        // Basic Rendering
        _canvas.drawBitmap(bmp, xPos, yPos ,null);

    }
    @Override
    public boolean IsInit(){
        return isInit;
    }
    @Override
    public int GetRenderLayer(){
        return LayerConstants.SMURF_LAYER;
    }
    @Override
    public void SetRenderLayer(int _newLayer){
        return;
    }

    public static Player Create(float xPosition, float yPosition){
        Player object = new Player(xPosition,yPosition);
        EntityManager.Instance.AddEntity(object,ENTITY_TYPE.ENT_PLAYER);
        return object;
    }

    public static Player Create(int _layer,float xPosition, float yPosition){
        Player object = Create(xPosition,yPosition);
        object.SetRenderLayer(_layer);
        return object;
    }

    @Override
    public ENTITY_TYPE GetEntityType(){
        return ENTITY_TYPE.ENT_PLAYER;
    }

    @Override
    public float GetPositionX(){
        return xPos;
    }

    @Override
    public float GetPositionY(){
        return yPos;
    }
}
