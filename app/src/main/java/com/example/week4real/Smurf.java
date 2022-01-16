package com.example.week4real;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceView;

import java.util.Random;

public class Smurf implements EntityBase , Collidable{
    private boolean isDone = false;
    private Bitmap bmp = null, scaledbmp = null;

    boolean IsGoingUp = false;

    int ScreenWidth, ScreenHeight;
    public float xPos, yPos, offset;
    public float defaultxPos;

    int spriteCounter = 0;

    private SurfaceView view = null;
    Matrix tfx = new Matrix();
    DisplayMetrics metrics;

    float lifetime;

    int Width, Height;

    float PrevXPos, PrevYPos;

    private Sprite spritesmurf = null;


    private boolean hasTouched = false; // New to Week 8

    //check if anything to do with entity (use for pause)
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
        defaultxPos = xPos;
        bmp = ResourceManager.Instance.GetBitmap( R.drawable.thesprite);

        metrics = _view.getResources().getDisplayMetrics();

        ScreenHeight = metrics.heightPixels;
        ScreenWidth = metrics.widthPixels;

        PrevXPos = xPos;
        PrevYPos = yPos;

        // New to Week 8
        // Using Sprite animation class to load our sprite sheet
        bmp = Bitmap.createScaledBitmap(bmp, (int)(ScreenWidth * 0.5), (int)(ScreenHeight * 0.25), false);
        spritesmurf = new Sprite((bmp),1,6, 5 );
        Width = spritesmurf.GetWidth();
        Height = spritesmurf.GetHeight();

        // Define how we want the player to react or if it is enemy or obstacles, how it is going to appear as.
        // You should have this part!

        xPos = 0.1f * ScreenWidth;

        yPos = 0.7f * ScreenHeight;

//        System.out.println("Player's x: " + GetPosX());
//        System.out.println("Player's y: " + GetPosY());
//        System.out.println("Player's right: " + GetRight());
//        System.out.println("Player's bottom: " + GetBottom());
    }

    @Override
    public void Update(float _dt) {
        // Week 8

        if (GameSystem.Instance.GetIsPaused())
            return;


        defaultxPos += _dt * 100;
        PrevXPos = xPos;
        PrevYPos = yPos;

        spritesmurf.Update(_dt);
        Log.i("PlayerHeight",Integer.toString(Height));
//        lifetime -= _dt;
//        if (lifetime < 0.0f) {
//            SetIsDone(true);   // <--- This part here or this code, meant when time is up, kill the items.
//        }
        if (TouchManager.Instance.IsDown())
        {   // Previous and it is for just a touch - useful for collision with a image (example button)
            IsGoingUp = true;
            spriteCounter = 1;
        }
        else
        {
            IsGoingUp = false;
            spriteCounter = 0;
        }

        if (IsGoingUp)
        {
            yPos -= 300 * _dt;
        }
        else
        {
            yPos += 300 * _dt;
        }

        if (yPos < spritesmurf.GetHeight() / 2)
        {
            yPos = spritesmurf.GetHeight() / 2;
        }

        if(yPos > 0.7f * ScreenHeight)
        {
            yPos = 0.7f * ScreenHeight;
        }

        if(xPos <= ScreenWidth * 0.50 && xPos > 0)
        {
            xPos += _dt * 10;
        }
        if(xPos >= ScreenWidth * 0.25)
            xPos = (float) (ScreenWidth * 0.25);

        if (xPos < -Width * 0.5)
        {
           isDone = true;

            Log.i(";", "YOU DEAD BRUH ");
        }


//        if (TouchManager.Instance.HasTouch())  // Touch and drag
//        {
//            // Check collision with the smurf sprite
//            float imgRadius1 = spritesmurf.GetWidth() * 0.5f;
//            if (Collision.SphereToSphere(TouchManager.Instance.GetPosX(), TouchManager.Instance.GetPosY(), 0.0f, xPos, yPos, imgRadius1) || hasTouched) {
//                hasTouched = true;
//                xPos = TouchManager.Instance.GetPosX();
//                yPos = TouchManager.Instance.GetPosY();
//
//            }
//        }
    }
    @Override
    public void Render(Canvas _canvas) {
            // New to Week 8
//            spritesmurf.Render(_canvas, (int)xPos, (int)yPos);  // Location can be changed!
            if (spriteCounter == 0)
            {
                spritesmurf.Render(_canvas, (int)xPos, (int)yPos);
            }
            else
            {
                spritesmurf.Render(_canvas, (int)xPos, (int)yPos);
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

    public static Smurf Create() {
        Smurf result = new Smurf();
        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_DEFAULT);
        return result;
    }

    @Override
    public String GetType() {
        return "SmurfEntity";
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
                && _other.GetType() ==  "Platform")
        {  // Another entity
            Log.i("debugging", "it touched the platform");
            if ((PrevYPos - Width * 0.5f) <= _other.GetBottom() && (PrevYPos + Width * 0.5f) >= _other.GetPosY())
            {
                xPos = _other.GetPosX() - Width * 0.5f;
                return;
            }
            if (IsGoingUp)
            {
                yPos = _other.GetBottom() + Height / 2;
            }
            else
            {
                yPos = _other.GetPosY() - Height / 2;
            }
        }


    }
}