package com.example.week4real;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceView;

import java.util.Random;
import java.util.Set;


public class Player implements EntityBase, Collidable{

    private Bitmap bmp = null;
    private boolean isDone = false;
    private float xPos,yPos;
    private boolean isInit = false;
    private boolean hasTouched = false; // To detect
    private float lifetime; // Life of the Player

    int Height, Width;

    private Sprite playerSprite = null;
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
        bmp = ResourceManager.Instance.GetBitmap(R.drawable.smurf_sprite);

        // Row , Col , NoOfAnimationsFrame = Row x Col
        playerSprite = new Sprite(bmp,4,4,16);

        isInit = true;

        // Define how we want the player to react or if it is enemy or obstacles, how it is going to appear as
       // Random ranGem = new Random();
        //xPos = ranGem.nextFloat()* _view.getWidth();
        //yPos = ranGem.nextFloat()* _view.getHeight();

        lifetime = 30.0f;

    }
    @Override
    public void Update(float _dt) {

        if(GameSystem.Instance.GetIsPaused())
            return;
        // Sprite Animation Update
        playerSprite.Update(_dt);

        if (TouchManager.Instance.HasTouch())  // Touch and drag
        {
            // Check collision with the smurf sprite
            float imgRadius1 = playerSprite.GetWidth() * 0.5f;
            if (Collision.SphereToSphere(TouchManager.Instance.GetPosX(), TouchManager.Instance.GetPosY(), 0.0f, xPos,yPos, imgRadius1) || hasTouched)
            {
                hasTouched = true;
                xPos = TouchManager.Instance.GetPosX();
                yPos = TouchManager.Instance.GetPosY();

            }
        }
//        if(TouchManager.Instance.HasTouch()){
//            // Check Collision
//            float imgRadius = bmp.getHeight() * 0.5f;
//            if (Collision.SphereToSphere(TouchManager.Instance.GetPosX(),TouchManager.Instance.GetPosY(),0.0f,xPos,yPos,imgRadius))
//            {
//                xPos =  (int)TouchManager.Instance.GetPosX();
//                yPos =  (int)TouchManager.Instance.GetPosY();
//            }
//        }

    }
    @Override
    public void Render(Canvas _canvas){
        // Basic Rendering
       // _canvas.drawBitmap(bmp, xPos, yPos ,null);
        playerSprite.Render(_canvas,(int)xPos,(int)yPos);

    }
    @Override
    public boolean IsInit(){
        return isInit;
    }
    @Override
    public int GetRenderLayer(){
        return LayerConstants.PLAYER_LAYER;
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
    public String GetType() {
        return "PlayerEntity";
    }

    @Override
    public float GetPosX() {
        return xPos - (Width * 0.5f);
    }

    @Override
    public float GetPosY() {
        return yPos - (Height * 0.5f);
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
        return bmp.getWidth();
    }

    @Override
    public void OnHit(Collidable _other) {
//        if(_other.GetType() != this.GetType()
//                && _other.GetType() !=  "SmurfEntity") {  // Another entity
//            SetIsDone(true);
//        }
    }


}
