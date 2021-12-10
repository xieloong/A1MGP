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
    private Paint borderPaint,healthPaint;
    public Healthbar( EarthEntity earthEntity){
        this.earthEntity = earthEntity;
        this.width = 400;
        this.height = 120;
        this.margin = 2;

        this.borderPaint = new Paint();
        borderPaint.setColor(Color.GRAY);
        borderPaint.setStyle(Paint.Style.FILL);

        this.healthPaint = new Paint();
        healthPaint.setColor(Color.GREEN);
        healthPaint.setStyle(Paint.Style.FILL);
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
            float distanceToEarth = 190.f;
            float healthPointPercentage = earthEntity.GetHealthPoints() / earthEntity.MAX_HEALTH_POINTS;


            // Draw Border
            float borderLeft,borderTop,borderRight,borderBottom;
            borderLeft = x + distanceToEarth;
            borderRight = borderLeft + width;
            borderTop = 40; // 40 + 120 for FAT , // 60 + 100 for SMALL
            borderBottom = height;


            _canvas.drawRect(borderLeft,borderTop,borderRight,borderBottom,borderPaint);

            float healthLeft, healthTop, healthRight, healthBottom, healthWidth,healthHeight;
            healthWidth = width - 2*margin;
            healthHeight = height - 2*margin;
            healthLeft = borderLeft + 2*margin;
            healthRight = healthLeft + healthWidth*healthPointPercentage;
            healthBottom = borderBottom - margin;
            healthTop = borderTop + margin;
            _canvas.drawRect(healthLeft,healthTop,healthRight,healthBottom,healthPaint);

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
