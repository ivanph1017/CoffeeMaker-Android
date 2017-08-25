package com.eveexite.coffeemaker_android.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.eveexite.coffeemaker_android.R;
import com.eveexite.coffeemaker_android.ui.utils.TitleUtils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

/**
 * Created by ivan on 6/13/17.
 */

public class TitleView extends SurfaceView implements SurfaceHolder.Callback{

    private Paint textPaint;

    private RectF rectContainer;

    private int width;
    private int height;
    private int textSize;
    private int position;

    private float counterTextSize;

    private String text;

    private SurfaceHolder holder;

    public TitleView(Context context) {
        super(context);
        textSize = 16;
        init();
    }

    public TitleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TitleView, 0, 0);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();

        try {

            textSize = a.getDimensionPixelSize(R.styleable.TitleView_textSize,
                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, textSize, displayMetrics));

            text = a.getString(R.styleable.TitleView_text);

        } finally {
            a.recycle();
        }

        init();
    }

    public void init() {
        textPaint = new Paint();
        textPaint.setColor(Color.parseColor("#362416"));
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setAntiAlias(true);
        textPaint.setFakeBoldText(true);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(textSize);

        rectContainer = new RectF();

        setZOrderOnTop(true);
        holder = getHolder();
        holder.setFormat(PixelFormat.TRANSLUCENT);
        holder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Observable.interval(TimeUnit.SECONDS.toNanos(1) / 300, TimeUnit.NANOSECONDS)
                .subscribe(aLong -> {
                    Canvas c = holder.lockCanvas();
                    if (c != null) {
                        onDraw(c);
                        holder.unlockCanvasAndPost(c);
                    }
                });
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //Para limpiar el canvas anterior
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

        width = getMeasuredWidth();
        height = getMeasuredHeight();
        rectContainer.set(0, 0, width, height);

        if(text != null) {
            if(text.length() < 28) {
                drawOnelineText(canvas);
            } else {
                drawTwolinesText(canvas);
            }
        }
        
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        this.position = 0;
        this.counterTextSize = 0.0f;
    }

    public void drawOnelineText(Canvas canvas) {
        float offsetStart = width / 2 - (textSize + 4) * (text.length() < 0 ? 0 : text.length() - 1) / 4;

        for (int i = 0; i < text.length(); i++) {

            if (i < position - 1) {
                canvas.drawText(String.valueOf(text.charAt(i)),
                        offsetStart + ((textSize + 4) * i / 2),
                        (rectContainer.height() / 1.75f) - TitleUtils.textHeight(textPaint) / 2,
                        textPaint);
            } else if (i > position) {
                canvas.drawText(String.valueOf(text.charAt(i)),
                        offsetStart + ((textSize + 4) * i / 2),
                        (rectContainer.height() / 1.75f) - TitleUtils.textHeight(textPaint) / 2,
                        textPaint);
            } else if (i == position){
                //Se achica el anterior sin considerar el primer carácter
                if (i > 0) {
                    textPaint.setTextSize(textSize + 20 - counterTextSize);
                    canvas.drawText(String.valueOf(text.charAt(i - 1)),
                            offsetStart + ((textSize + 4) * (i - 1) / 2),
                            (rectContainer.height() / 1.75f) - TitleUtils.textHeight(textPaint) / 2,
                            textPaint);
                }

                //Se agranda
                counterTextSize += 1.5f;
                textPaint.setTextSize(textSize + counterTextSize);
                textPaint.setColor(Color.parseColor("#6A2E18"));
                canvas.drawText(String.valueOf(text.charAt(i)),
                        offsetStart + ((textSize + 4) * i / 2),
                        (rectContainer.height() / 1.75f) - TitleUtils.textHeight(textPaint) / 2,
                        textPaint);
                textPaint.setColor(Color.parseColor("#362416"));
                textPaint.setTextSize(textSize);
            }

        }

        if (counterTextSize > 20) {
            counterTextSize = 0.0f;
            position = position == text.length() - 1 ? 0 : position + 1;
        }

    }

    public void drawTwolinesText(Canvas canvas) {
        int firstLineLength = text.substring(0, 26).lastIndexOf(" ");
        int secondLineLength = text.length() - firstLineLength;

        float offsetStart1 = width / 2 - (textSize + 4) * (firstLineLength < 0 ? 0 : firstLineLength - 1) / 4;
        float offsetStart2 = width / 2 - (textSize + 4) * (secondLineLength < 0 ? 0 : secondLineLength - 1) / 4;

        int i = 0;
        while(i < firstLineLength) {
            if (i < position - 1) {
                canvas.drawText(String.valueOf(text.charAt(i)),
                        offsetStart1 + ((textSize + 4) * i / 2),
                        (rectContainer.height() / 1.75f) + TitleUtils.textHeight(textPaint) * 2,
                        textPaint);
            } else if (i > position) {
                canvas.drawText(String.valueOf(text.charAt(i)),
                        offsetStart1 + ((textSize + 4) * i / 2),
                        (rectContainer.height() / 1.75f) + TitleUtils.textHeight(textPaint) * 2,
                        textPaint);
            } else if (i == position){
                //Se achica el anterior sin considerar el primer carácter
                if (i > 0) {
                    textPaint.setTextSize(textSize + 20 - counterTextSize);
                    canvas.drawText(String.valueOf(text.charAt(i - 1)),
                            offsetStart1 + ((textSize + 4) * (i - 1) / 2),
                            (rectContainer.height() / 1.75f) + TitleUtils.textHeight(textPaint) * 2,
                            textPaint);
                }

                //Se agranda
                counterTextSize += 1.5f;
                textPaint.setTextSize(textSize + counterTextSize);
                textPaint.setColor(Color.parseColor("#6A2E18"));
                canvas.drawText(String.valueOf(text.charAt(i)),
                        offsetStart1 + ((textSize + 4) * i / 2),
                        (rectContainer.height() / 1.75f) + TitleUtils.textHeight(textPaint) * 2,
                        textPaint);
                textPaint.setColor(Color.parseColor("#362416"));
                textPaint.setTextSize(textSize);
            }

            i++;

        }

        while(i < text.length()) {
            if (i < position - 1) {
                canvas.drawText(String.valueOf(text.charAt(i)),
                        offsetStart2 + ((textSize + 4) * (i - firstLineLength) / 2),
                        (rectContainer.height() / 1.75f) - TitleUtils.textHeight(textPaint) * 2,
                        textPaint);
            } else if (i > position) {
                canvas.drawText(String.valueOf(text.charAt(i)),
                        offsetStart2 + ((textSize + 4) * (i - firstLineLength) / 2),
                        (rectContainer.height() / 1.75f) - TitleUtils.textHeight(textPaint) * 2,
                        textPaint);
            } else if (i == position){
                //Se achica el anterior si está en la segunda línea y sin considerar el primer carácter
                if (i > 0 && i > firstLineLength) {
                    textPaint.setTextSize(textSize + 20 - counterTextSize);
                    canvas.drawText(String.valueOf(text.charAt(i - 1)),
                            offsetStart2 + ((textSize + 4) * ((i - firstLineLength) - 1) / 2),
                            (rectContainer.height() / 1.75f) - TitleUtils.textHeight(textPaint) * 2,
                            textPaint);
                    //Se achica el anterior si está en la última posición de la primera línea y sin considerar el primer carácter
                } else if(i > 0) {
                    textPaint.setTextSize(textSize + 20 - counterTextSize);
                    canvas.drawText(String.valueOf(text.charAt(i - 1)),
                            offsetStart1 + ((textSize + 4) * (i - 1) / 2),
                            (rectContainer.height() / 1.75f) + TitleUtils.textHeight(textPaint) * 2,
                            textPaint);
                }

                //Se agranda
                counterTextSize += 1.5f;
                textPaint.setTextSize(textSize + counterTextSize);
                textPaint.setColor(Color.parseColor("#6A2E18"));
                canvas.drawText(String.valueOf(text.charAt(i)),
                        offsetStart2 + ((textSize + 4) * (i - firstLineLength) / 2),
                        (rectContainer.height() / 1.75f) - TitleUtils.textHeight(textPaint) * 2,
                        textPaint);
                textPaint.setColor(Color.parseColor("#362416"));
                textPaint.setTextSize(textSize);
            }

            i++;

        }

        if (counterTextSize > 20) {
            counterTextSize = 0.0f;
            position = position == text.length() - 1 ? 0 : position + 1;
        }

    }

}
