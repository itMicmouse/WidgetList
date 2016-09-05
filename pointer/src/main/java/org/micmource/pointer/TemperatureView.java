package org.micmource.pointer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
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
    private Paint scaleArcPaint;
    private Paint progressTextPaint;
    private Paint leftPointerPaint; // 表针左半部分
    private Paint rightPointerPaint; // 表针右半部分
    private Paint pointerCirclePaint; // 表针的圆轴
    private String scale; // 刻度数值
    private Paint panelTextPaint; // 表盘文字

    private static final int PADDING = 15; // 进度的宽度
    private int scaleArcRadius; // 刻度弧的半径
    private int mTikeCount = 40; // 40条刻度(包括长短)
    private int mLongTikeHeight = dp2px(10); // 长刻度
    private int mShortTikeHeight = dp2px(5); // 短刻度
    private Paint scalePaint; // 刻度
    private int pointRadius = dp2px(17); // 中心圆半径
    private Paint pointPaint; // 中心圆
    private float currentTemp;
    private static final int OFFSET = 5;

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

        progressTextPaint = new Paint();
        progressTextPaint.setAntiAlias(true);
        progressTextPaint.setStyle(Paint.Style.FILL);
        progressTextPaint.setColor(Color.BLACK);
        progressTextPaint.setTypeface(Typeface.DEFAULT_BOLD);

        scaleArcPaint = new Paint();
        scaleArcPaint.setAntiAlias(true);
        scaleArcPaint.setStrokeWidth(dp2px(2));
        scaleArcPaint.setStyle(Paint.Style.STROKE);

        panelTextPaint = new Paint();
        panelTextPaint.setAntiAlias(true);
        panelTextPaint.setStyle(Paint.Style.FILL);
        panelTextPaint.setColor(Color.BLACK);

        scalePaint = new Paint();
        scalePaint.setAntiAlias(true);
        scalePaint.setStrokeWidth(5);
        scalePaint.setStyle(Paint.Style.STROKE);

        pointPaint = new Paint();
        pointPaint.setAntiAlias(true);
        pointPaint.setStyle(Paint.Style.FILL);
        pointPaint.setColor(Color.GRAY);

        leftPointerPaint = new Paint();
        leftPointerPaint.setAntiAlias(true);
        leftPointerPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        leftPointerPaint.setColor(getResources().getColor(R.color.leftPointer));

        rightPointerPaint = new Paint();
        rightPointerPaint.setAntiAlias(true);
        rightPointerPaint.setColor(getResources().getColor(R.color.rightPointer));
        rightPointerPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        pointerCirclePaint = new Paint();
        pointerCirclePaint.setAntiAlias(true);
        pointerCirclePaint.setColor(Color.GRAY);
        pointerCirclePaint.setStyle(Paint.Style.FILL);
        pointerCirclePaint.setDither(true);
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

        drawProgressText(canvas);

        drawPanel(canvas);
    }

    private void drawPanel(Canvas canvas) {
        // 画刻度弧
        drawScaleArc(canvas);
        //画中间圆
        drawInPoint(canvas);
        // 画指针
        drawPointer(canvas);
        // 绘制文字
        drawPanelText(canvas);
    }


    /**
     * 表盘上的文字
     * @param canvas
     */
    private void drawPanelText(Canvas canvas) {
        canvas.save();
        String text = "当前温度";
        float length = panelTextPaint.measureText(text);
        panelTextPaint.setTextSize(sp2px(15));
        canvas.drawText(text, -length/2, scaleArcRadius/2 + dp2px(20), panelTextPaint);
        String temp = currentTemp + " ℃";
        panelTextPaint.setTextSize(sp2px(15));
        float tempTextLength = panelTextPaint.measureText(temp);
        canvas.drawText(temp, -tempTextLength/2, scaleArcRadius, panelTextPaint);
        canvas.restore();

    }


    private void drawPointer(Canvas canvas) {
        RectF rectF = new RectF(-pointRadius/2, -pointRadius/2, pointRadius/2, pointRadius/2);
        canvas.save();
        // 先将指针与刻度0位置对齐
        canvas.rotate(60, 0, 0);
        float angle = currentTemp * (240/mTikeCount);
        canvas.rotate(angle, 0, 0);

        // 表针左半部分
        Path leftPointerPath = new Path();
        leftPointerPath.moveTo(pointRadius/2, 0);
        leftPointerPath.addArc(rectF, 0, 360);
        leftPointerPath.lineTo(0, scaleArcRadius - mLongTikeHeight - dp2px(OFFSET) - dp2px(15));
        leftPointerPath.lineTo(-pointRadius/2, 0);
        leftPointerPath.close();
        // 表针右半部分
        Path rightPointerPath = new Path();
        rightPointerPath.moveTo(-pointRadius/2, 0);
        rightPointerPath.addArc(rectF, 0, -180);
        rightPointerPath.lineTo(0, scaleArcRadius - mLongTikeHeight - dp2px(OFFSET) - dp2px(15));
        rightPointerPath.lineTo(0, pointRadius/2);
        rightPointerPath.close();
        // 表针的圆
        Path circlePath = new Path();
        circlePath.addCircle(0, 0, pointRadius/4, Path.Direction.CW);

        canvas.drawPath(leftPointerPath, leftPointerPaint);
        canvas.drawPath(rightPointerPath, rightPointerPaint);
        canvas.drawPath(circlePath, pointerCirclePaint);
        canvas.restore();

    }

    private void drawInPoint(Canvas canvas) {
        canvas.save();
        canvas.drawCircle(0, 0, pointRadius, pointPaint);
        canvas.restore();
    }

    /**
     * 画坐标弧和刻度
     * @param canvas
     */
    private void drawScaleArc(Canvas canvas) {
        // 画弧
        RectF rectF = new RectF(-scaleArcRadius, -scaleArcRadius,
                scaleArcRadius, scaleArcRadius);
        canvas.drawArc(rectF, 150, 240, false, scaleArcPaint);

        //画刻度线
        float mRangle = 240f / mTikeCount;
        //画右半部分刻度线
        for (int i = 0; i <= mTikeCount/2; i++) {
            if(i%5==0){//大刻度线
                scale = 20+i+"";
                panelTextPaint.setTextSize(sp2px(15));
                float scanleWidth = panelTextPaint.measureText(scale);
                canvas.drawLine(0,-scaleArcRadius,0,-scaleArcRadius+mLongTikeHeight,scalePaint);
                canvas.drawText(scale,-scanleWidth/2,-scaleArcRadius+mLongTikeHeight + dp2px(15),panelTextPaint);
            }else {
                canvas.drawLine(0,-scaleArcRadius,0,-scaleArcRadius+mShortTikeHeight,scalePaint);
            }
            canvas.rotate(mRangle,0,0);
        }
        canvas.rotate(-mRangle * mTikeCount/2 - 6,0,0);

        //画左半部分刻度线
        for (int i = 0; i <= mTikeCount/2; i++) {
            if(i%5==0){//大刻度线
                scale = 20-i+"";
                panelTextPaint.setTextSize(sp2px(15));
                float scanleWidth = panelTextPaint.measureText(scale);
                canvas.drawLine(0,-scaleArcRadius,0,-scaleArcRadius+mLongTikeHeight,scalePaint);
                canvas.drawText(scale,-scanleWidth/2,-scaleArcRadius+mLongTikeHeight + dp2px(15),panelTextPaint);
            }else {
                canvas.drawLine(0,-scaleArcRadius,0,-scaleArcRadius+mShortTikeHeight,scalePaint);
            }
            canvas.rotate(-mRangle,0,0);
        }
        canvas.rotate(-mRangle * mTikeCount/2 + 6, 0, 0);
        canvas.restore();
    }

    private void drawProgressText(Canvas canvas) {
        canvas.save();
        String normal = "正常";
        String warn = "预警";
        String danger = "危险";

        scaleArcRadius = mSize / 2 - (dp2px(15) + dp2px(PADDING) / 4);

        canvas.rotate(-60, 0, 0);
        progressTextPaint.setTextSize(sp2px(12));
        canvas.drawText(normal, -dp2px(12), -scaleArcRadius - dp2px(4), progressTextPaint);

        canvas.rotate(90, 0, 0);
        canvas.drawText(warn, -dp2px(12), -scaleArcRadius - dp2px(4), progressTextPaint);

        canvas.rotate(60, 0, 0);
        canvas.drawText(danger, -dp2px(12), -scaleArcRadius - dp2px(4), progressTextPaint);

        canvas.restore();
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


    /**
     * 设置当前温度
     * @param currentTemp
     */
    public void setCurrentTemp(float currentTemp) {
        if (currentTemp < 0) {
            currentTemp = 0;
        } else if (currentTemp > 40) {
            currentTemp = 40;
        } else {
            this.currentTemp = currentTemp;
            postInvalidate();
        }
    }

    public float getCurrentTemp() {
        return currentTemp;
    }
}
