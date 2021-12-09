package com.example.week4real;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceView;

import androidx.core.content.ContextCompat;

public class Healthbar implements EntityBase{

    private Bitmap bmp = null;
    private boolean isDone = false;
    private float xPos,yPos;
    private boolean isInit = false;
    private boolean hasTouched = false;
    private EarthEntity earthEntity;
    private int width,height,margin;
    private Paint borderPaint;
    public Healthbar( EarthEntity earthEntity){
        this.earthEntity = earthEntity;
        this.width = 100;
        this.height = 150;
        this.margin = 2;
        this.borderPaint = new Paint();
        borderPaint.setColor(Color.GRAY);
        borderPaint.setStyle(Paint.Style.FILL);

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

    }
    @Override
    public void Update(float _dt) {




    }
    @Override
    public void Render(Canvas _canvas){
        if(earthEntity.IsInit())
        {
            float x = earthEntity.xPos;
            float y = earthEntity.yPos;
            float distanceToEarth = 200.f;
            float healthPointPercentage = (float)earthEntity.GetHealthPoints() / earthEntity.MAX_HEALTH_POINTS;


            // Draw Border
            float borderleft,borderTop,borderRight,borderBottom;
            borderleft = x + distanceToEarth;
            borderRight = borderleft + width;
//            borderBottom =
//            borderTop =

//            _canvas.drawRect(borderleft,borderTop,borderRight,borderBottom,borderPaint);
        }
        // 1/12/2021  Fix HealthBar Position and Continues HealthBar


         //Draw Health_canvas.drawRect(healthLeft,healthTop,healthRight,healthBottom,healthPaint);




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

    public static Healthbar Create(EarthEntity earthEntity){
        Healthbar object = new Healthbar(earthEntity);
        EntityManager.Instance.AddEntity(object,ENTITY_TYPE.ENT_HEALTHBAR);
        return object;
    }


    @Override
    public ENTITY_TYPE GetEntityType(){
        return ENTITY_TYPE.ENT_HEALTHBAR;
    }


}
