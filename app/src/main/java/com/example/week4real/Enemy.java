package com.example.week4real;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

import java.util.Random;


public class Enemy implements EntityBase , Collidable{

    private boolean isDone = false;
    private Bitmap bmp = null, scaledbmp = null;

    boolean IsGoingUp = false;

    int ScreenWidth, ScreenHeight;
    private float xPos, yPos, offset;
    int Height, Width;

    int enemyCounter = 0;

    private SurfaceView view = null;
    Matrix tfx = new Matrix();
    DisplayMetrics metrics;

    float lifetime;
    EarthEntity earthEntity = null;
    private Sprite enemysmurf = null;

    private boolean hasTouched = false; // New to Week 8

    //check if anything to do with entity (use for pause)

    Enemy(float xPosition, float yPosition,EarthEntity earthEntity)
    {
        xPos = xPosition;
        yPos = yPosition;
        this.earthEntity = earthEntity;
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
        bmp = ResourceManager.Instance.GetBitmap( R.drawable.enemysprite);

        metrics = _view.getResources().getDisplayMetrics();

        ScreenHeight = metrics.heightPixels;
        ScreenWidth = metrics.widthPixels;

        // New to Week 8
        // Using Sprite animation class to load our sprite sheet
        bmp = Bitmap.createScaledBitmap(bmp, (int)(ScreenWidth * 0.5), (int)(ScreenHeight * 0.2), false);
        enemysmurf = new Sprite((bmp),1,6, 5 );
        Width = enemysmurf.GetWidth();
        Height = enemysmurf.GetHeight();

        // Define how we want the player to react or if it is enemy or obstacles, how it is going to appear as.
        // You should have this part!
        if(xPos < -ScreenWidth)
        {
            SetIsDone(true);
        }

    }

    @Override
    public void Update(float _dt) {
        // Week 8

        if (GameSystem.Instance.GetIsPaused())
            return;

        enemysmurf.Update(_dt);

        xPos -= _dt * 100; // How fast you want to move the screen

//        if (xPos <  -ScreenWidth){
//            xPos = 0;
//        }
//
//        if(xPos<-(int)(Width * 0.8)) //wrapping
//        {
//            xPos = ScreenWidth;
//        }

//        lifetime -= _dt;
//        if (lifetime < 0.0f) {
//            SetIsDone(true);   // <--- This part here or this code, meant when time is up, kill the items.
//        }

        if(xPos < -ScreenWidth)
        {
            SetIsDone(true);
        }
    }
    @Override
    public void Render(Canvas _canvas) {
        // New to Week 8
//            spritesmurf.Render(_canvas, (int)xPos, (int)yPos);  // Location can be changed!
        if (enemyCounter == 0)
        {
            enemysmurf.Render(_canvas, (int)xPos, (int)yPos);
        }
        else
        {
            enemysmurf.Render(_canvas, (int)xPos, (int)yPos);
        }


    }

    @Override
    public boolean IsInit() {
        return bmp != null;
    }

    @Override
    public int GetRenderLayer() {
        return LayerConstants.PLAYER_LAYER;
    }

    @Override
    public void SetRenderLayer(int _newLayer) {

    }



    @Override
    public ENTITY_TYPE GetEntityType() {
        return ENTITY_TYPE.ENT_DEFAULT;
    }

    public static Enemy Create(float xPosition, float yPosition, EarthEntity earthEntity) {
        Enemy result = new Enemy(xPosition,yPosition, earthEntity);
        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_DEFAULT);
        return result;
    }

    @Override
    public String GetType() {
        return "EnemyEntity";
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
        if(_other.GetType() != this.GetType()
                && _other.GetType() ==  "SmurfEntity")
        {  // Another entity

            earthEntity.SetHealthPoints(earthEntity.GetHealthPoints() - 20);
            System.out.println("Enemy's x: " + GetPosX());
            System.out.println("Enemy's y: " + GetPosY());
            System.out.println("Enemy's right: " + GetRight());
            System.out.println("Enemy's bottom: " + GetBottom());
            SetIsDone(true);
        }
    }
}
