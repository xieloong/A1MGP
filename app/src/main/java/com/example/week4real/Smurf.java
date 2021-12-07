package com.example.week4real;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

import java.util.Random;

public class Smurf implements EntityBase {
    private boolean isDone = false;
    private Bitmap bmp = null, scaledbmp = null;

    boolean IsGoingUp = false;

    int ScreenWidth, ScreenHeight;
    private float xPos, yPos, offset;

    int spriteCounter = 0;

    private SurfaceView view = null;
    Matrix tfx = new Matrix();
    DisplayMetrics metrics;

    float lifetime;

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
        bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.ship2_1);
        bmp = ResourceManager.Instance.GetBitmap( R.drawable.ship2_1);

        metrics = _view.getResources().getDisplayMetrics();

        ScreenHeight = metrics.heightPixels;
        ScreenWidth = metrics.widthPixels;

        // New to Week 8
        // Using Sprite animation class to load our sprite sheet
        spritesmurf = new Sprite(ResourceManager.Instance.GetBitmap(R.drawable.mainsprite),1,6, 5 );


        // Define how we want the player to react or if it is enemy or obstacles, how it is going to appear as.
        // You should have this part!

        xPos = 0.1f * ScreenWidth;
        yPos = 0.7f * ScreenHeight;
    }

    @Override
    public void Update(float _dt) {
        // Week 8
        spritesmurf.Update(_dt);

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
        return LayerConstants.BACKGROUND_LAYER;
    }

    @Override
    public void SetRenderLayer(int _newLayer) {

    }

    @Override
    public float GetPositionX() {
        return xPos;
    }

    @Override
    public float GetPositionY() {
        return yPos;
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
}