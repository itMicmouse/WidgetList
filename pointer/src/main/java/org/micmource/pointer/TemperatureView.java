package org.micmource.pointer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by yakun on 2016/8/31.
 */
public class TemperatureView extends View {
    private int mSize;
    private Paint outCirclePaint;
    private int progressRadius; // 进度弧的半径
    private Paint progressPaint;
    private static final int PADDING = 15; // 进度的宽度

    public TemperatureView(Context context) {
        super(context, null);
    }

    public TemperatureView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TemperatureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initPaint();
    }

    private void initPaint() {
        outCirclePaint = new Paint();
        outCirclePaint.setAntiAlias(true);
        outCirclePaint.setStrokeWidth(5);
        outCirclePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        outCirclePaint.setColor(getResources().getColor(R.color.temperatureBackground));


        progressPaint = new Paint();
        progressPaint.setAntiAlias(true);
        progressPaint.setStrokeWidth(dp2px(PADDING));
        progressPaint.setStyle(Paint.Style.STROKE);
        progressPaint.setStrokeCap(Paint.Cap.ROUND);
        progressPaint.setStrokeJoin(Paint.Join.ROUND);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int realWidth = startMeasure(widthMeasureSpec);
        int realHeigh = startMeasure(heightMeasureSpec);

        mSize = Math.min(realHeigh, realWidth);
        setMeasuredDimension(mSize, mSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mSize / 2, mSize / 2);

        // 画最外面的圆
        drawOutCircle(canvas);

        // 画进度
        drawProgress(canvas);
    }

    /**
     * 进度弧
     *
     * @param canvas
     */
    private void drawProgress(Canvas canvas) {
        // dp2px(10):留一点位置（可有可无）
        progressRadius = mSize / 2 - dp2px(10);
        canvas.save();
        RectF rectF = new RectF(-progressRadius, -progressRadius, progressRadius, progressRadius);
        // 设置为圆角
        progressPaint.setStrokeCap(Paint.Cap.ROUND);
        progressPaint.setColor(Color.GREEN);
        // 从150度位置开始，经过120度
        canvas.drawArc(rectF, 150, 120, false, progressPaint);
        progressPaint.setColor(Color.RED);
        progressPaint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawArc(rectF, 330, 60, false, progressPaint);
        progressPaint.setColor(Color.YELLOW);
        progressPaint.setStrokeCap(Paint.Cap.BUTT);
        canvas.drawArc(rectF, 270, 60, false, progressPaint);

        canvas.restore();

    }

    private void drawOutCircle(Canvas canvas) {
        // 已经将画布移到中心，所以圆心为（0,0）
        canvas.drawCircle(0, 0, mSize / 2 - dp2px(1), outCirclePaint);
        canvas.save();
    }


    /**
     * 测量大小
     *
     * @param whSpec
     * @return
     */
    private int startMeasure(int whSpec) {
        int result = 0;
        int size = MeasureSpec.getSize(whSpec);
        int mode = MeasureSpec.getMode(whSpec);
        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else {
            result = dp2px(200);
        }
        return result;
    }

    /**
     * 将 dp 转换为 px
     *
     * @param dp
     * @return
     */
    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }

    private int sp2px(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, getResources().getDisplayMetrics());
    }
}
