package com.example.week4real;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

public class PausebuttonEntity implements EntityBase{
    private boolean isDone = false;

    private Bitmap bmpP = null;
    private Bitmap scaledbmpP =null;

    private Bitmap bmpUP = null;
    private Bitmap scaledbmpUP =null;

    private boolean isInit = false;
    private boolean Paused = false;

    int ScreenWidth,ScreenHeight;
    private float xPos,yPos;

    private float buttonDelay = 0;


    @Override
    public boolean IsDone(){
        return isDone;
    }

    @Override
    public void SetIsDone(boolean _isDone){
        isDone = _isDone;
    }

    @Override
    public void Init(SurfaceView _view){
        bmpP = ResourceManager.Instance.GetBitmap(R.drawable.pause);
        bmpUP = ResourceManager.Instance.GetBitmap((R.drawable.pause1));

        DisplayMetrics metrics = _view.getResources().getDisplayMetrics();
        ScreenHeight = metrics.heightPixels;
        ScreenWidth = metrics.widthPixels;

        scaledbmpP = Bitmap.createScaledBitmap(bmpP,ScreenWidth/10,ScreenHeight/10,true);
        scaledbmpUP = Bitmap.createScaledBitmap(bmpUP,ScreenWidth/10,ScreenHeight/10,true);

        xPos = ScreenWidth - 100;
        yPos = 150;
        isInit = true;
    }

    @Override
    public void Update(float _dt){
        buttonDelay += _dt;

        if(TouchManager.Instance.HasTouch())
        {
            // Check for Collision
            if(TouchManager.Instance.IsDown() && !Paused){
                float imgRadius = scaledbmpP.getHeight();
                if(Collision.SphereToSphere(TouchManager.Instance.GetPosX(), TouchManager.Instance.GetPosY(), 0.0f,xPos,yPos,imgRadius))
                {
                    Paused = true;
                }
                buttonDelay = 0;
                GameSystem.Instance.SetIsPaused(!GameSystem.Instance.GetIsPaused());
            }
        }
        else
        {
            Paused = false;
        }

    }
    @Override
    public void Render(Canvas _canvas){
        // Basic Rendering
        if(Paused == false)
            _canvas.drawBitmap(scaledbmpP, xPos - scaledbmpP.getWidth() * 0.5f, yPos - scaledbmpP.getHeight() * 0.5f ,null);
        else
            _canvas.drawBitmap(scaledbmpUP, xPos - scaledbmpUP.getWidth() * 0.5f, yPos - scaledbmpUP.getHeight() * 0.5f ,null);
    }

    @Override
    public boolean IsInit(){
        return isInit;
    }

    @Override
    public int GetRenderLayer(){
        return LayerConstants.UI_LAYER;
    }
    @Override
    public void SetRenderLayer(int _newLayer){
        return;
    }

    @Override
    public EntityBase.ENTITY_TYPE GetEntityType(){
        return EntityBase.ENTITY_TYPE.ENT_PAUSEBUTTON;
    }

    public static PausebuttonEntity Create(){
        PausebuttonEntity object = new PausebuttonEntity();
        EntityManager.Instance.AddEntity(object, EntityBase.ENTITY_TYPE.ENT_JUMPBUTTON);
        return object;
    }
}
