package com.eveexite.coffeemaker_android.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.eveexite.coffeemaker_android.ui.utils.TitleUtils;

/**
 * Created by Ivan on 9/07/2017.
 */

public class WaterLevelView extends SurfaceView implements SurfaceHolder.Callback{

    private Paint recipientPaint;
    private Paint waterPaint;
    private Paint textPaint;

    private RectF rectContainer;

    private int width;
    private int height;
    private int textSize;
    private int waterLevel;

    private float offsetStart;

    private SurfaceHolder holder;

    public WaterLevelView(Context context) {
        super(context);
        init();
    }

    public WaterLevelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WaterLevelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {

        offsetStart = 150;
        textSize = 30;

        recipientPaint = new Paint();
        recipientPaint.setColor(Color.WHITE);
        recipientPaint.setStyle(Paint.Style.STROKE);
        recipientPaint.setAntiAlias(true);
        recipientPaint.setStrokeWidth(5);

        textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setFakeBoldText(true);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(textSize);

        waterPaint = new Paint();
        waterPaint.setColor(Color.parseColor("#9900ffff"));
        waterPaint.setStyle(Paint.Style.FILL);
        waterPaint.setAntiAlias(true);

        rectContainer = new RectF();

        setZOrderOnTop(true);
        holder = getHolder();
        holder.setFormat(PixelFormat.TRANSLUCENT);
        holder.addCallback(this);

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Canvas c = holder.lockCanvas();
        if (c != null) {
            onDraw(c);
            holder.unlockCanvasAndPost(c);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    public int getWaterLevel() {
        return waterLevel;
    }

    public void setWaterLevel(int waterLevel) {
        this.waterLevel = waterLevel;
        
        Canvas c = holder.lockCanvas();
        if (c != null) {
            onDraw(c);
            holder.unlockCanvasAndPost(c);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //Para limpiar el canvas anterior
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

        width = getMeasuredWidth();
        height = getMeasuredHeight();
        rectContainer.set(0, 0, width, height);

        drawRecipient(canvas);
        drawWater(canvas);
    }

    private void drawRecipient(Canvas canvas) {

        //Horizontal arriba izq
        /*canvas.drawLine(offsetStart - 25, 0,
                offsetStart, 0, recipientPaint);*/

        //Superior

        //Fore

        canvas.drawPath(drawCurveLine(offsetStart, 25, rectContainer.width() - 5,
                25, - 45, new Path()), recipientPaint);

        //Back

        canvas.drawPath(drawCurveLine(offsetStart, 25, rectContainer.width() - 5,
                25, 45, new Path()), recipientPaint);

        //Vertical izq
        canvas.drawLine(offsetStart, 25,
                offsetStart, rectContainer.height() - 25, recipientPaint);

        //Vertical der
        canvas.drawLine(rectContainer.width() - 5, 25,
                rectContainer.width() - 5, rectContainer.height() - 25, recipientPaint);

        //Indicadores

        //Nivel 0 tazas = 0 mL

        canvas.drawText("0 tazas",
                offsetStart - 75,
                rectContainer.height() - 25,
                textPaint);

        //Fore

        canvas.drawPath(drawCurveLine(offsetStart, rectContainer.height() - 25, rectContainer.width() - 5,
                rectContainer.height() - 25, - 45, new Path()), recipientPaint);

        //Nivel 1 taza, no hay sensor

        /*canvas.drawText("1 taza",
                offsetStart - 75,
                25 + (rectContainer.height() - 50) / 5 * 4 - TitleUtils.textHeight(textPaint) / 2,
                textPaint);

        canvas.drawPath(drawHalfCurveLine(offsetStart, (rectContainer.height() - 50) / 5 * 4,
                rectContainer.width() - 5, (rectContainer.height() - 50) / 5 * 4, - 45, new Path()), recipientPaint);*/

        //Nivel 2 tazas = 300 mL

        canvas.drawText("2-5 tazas",
                offsetStart - 75,
                25 + (rectContainer.height() - 50) / 5 * 3 - TitleUtils.textHeight(textPaint) / 2,
                textPaint);

        canvas.drawPath(drawHalfCurveLine(offsetStart, (rectContainer.height() - 50) / 5 * 3,
                rectContainer.width() - 5, (rectContainer.height() - 50) / 5 * 3, - 45, new Path()), recipientPaint);

    }

    private void drawWater(Canvas canvas) {
        Path path = new Path();
        switch(waterLevel) {
            case 2:
                //Base
                path = drawCurveLine(offsetStart + 1, rectContainer.height() - 25, rectContainer.width() - 6,
                rectContainer.height() - 25, - 45, path);
                //Vertical der
                path.lineTo(rectContainer.width() - 6, 25 + (rectContainer.height() - 50) / 5 * 3);
                //Superior curva
                path = drawCurveLine(rectContainer.width() - 6, 25 + (rectContainer.height() - 50) / 5 * 3,
                        offsetStart + 1, 25 + (rectContainer.height() - 50) / 5 * 3, - 30, path);
                //Vertical izq
                path.lineTo(offsetStart + 1, rectContainer.height() - 25);
                //Dibujar
                canvas.drawPath(path, waterPaint);
                break;
            /*case 1:
                //Base
                path = drawCurveLine(offsetStart + 1, rectContainer.height() - 25, rectContainer.width() - 6,
                        rectContainer.height() - 25, - 45, path);
                //Vertical der
                path.lineTo(rectContainer.width() - 6, 25 + (rectContainer.height() - 50) / 5 * 4);
                //Superior curva
                path = drawCurveLine(rectContainer.width() - 6, 25 + (rectContainer.height() - 50) / 5 * 4,
                        offsetStart + 1, 25 + (rectContainer.height() - 50) / 5 * 4, - 30, path);
                //Vertical izq
                path.lineTo(offsetStart + 1, rectContainer.height() - 25);
                //Dibujar
                canvas.drawPath(path, waterPaint);
                break;*/
            case 0:
                break;
        }
    }

    private Path drawCurveLine(float x1, float y1, float x2, float y2, int curveRadius, Path path) {

        float midX            = x1 + ((x2 - x1) / 2);
        float midY            = y1 + ((y2 - y1) / 2);
        float xDiff         = midX - x1;
        float yDiff         = midY - y1;
        double angle        = (Math.atan2(yDiff, xDiff) * (180 / Math.PI)) - 90;
        double angleRadians = Math.toRadians(angle);
        float pointX        = (float) (midX + curveRadius * Math.cos(angleRadians));
        float pointY        = (float) (midY + curveRadius * Math.sin(angleRadians));

        path.moveTo(x1, y1);
        path.cubicTo(x1,y1,pointX, pointY, x2, y2);

        return path;

    }

    private Path drawHalfCurveLine(float x1, float y1, float x2, float y2, int curveRadius, Path path) {

        float midX            = x1 + ((x2 - x1) / 2);
        float midY            = y1 + ((y2 - y1) / 2);
        float xDiff         = midX - x1;
        float yDiff         = midY - y1;
        double angle        = (Math.atan2(yDiff, xDiff) * (180 / Math.PI)) - 90;
        double angleRadians = Math.toRadians(angle);
        //float pointX        = (float) (midX + curveRadius * Math.cos(angleRadians));
        float pointY        = (float) (midY + curveRadius * Math.sin(angleRadians));

        RectF oval = new RectF();
        oval.set(x1, y1, x2, pointY);
        path.arcTo(oval, 180, -90);

        return path;

    }

}
