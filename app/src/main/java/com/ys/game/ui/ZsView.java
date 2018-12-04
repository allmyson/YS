package com.ys.game.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.ys.game.util.DensityUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lh
 * @version 1.0.0
 * @filename ZsView
 * @description -------------------------------------------------------
 * @date 2018/11/7 10:07
 */
public class ZsView extends View {
    private Context context;
    private int width;
    private int height;
    private Paint paint;
    private int itemHeight;
    private int defaultHeight = 1400;
    private int defaultWidth = 400;
    private List<Integer> list;
    private int itemWidth;
    private int gdWidth;
    private List<Path> pathList;

    public ZsView(Context context) {
        this(context, null);
    }

    public ZsView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ZsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {
        itemHeight = DensityUtil.dp2px(context, 40);
        gdWidth = DensityUtil.dp2px(context, 70);
        paint = new Paint();
        paint.setColor(Color.parseColor("#fe794a"));
        paint.setStyle(Paint.Style.STROKE);//设置为空心
        paint.setStrokeWidth(3);
        paint.setAntiAlias(true);
//        setLayerType(View.LAYER_TYPE_SOFTWARE, paint);//关闭View硬件加速，解决绘制超长path问题
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (list != null && list.size() > 0) {
            for (Path path : pathList) {
//                if (path != pathList.get(pathList.size() - 1)) {
                canvas.drawPath(path, paint);
//                }
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
// 获取宽-测量规则的模式和大小
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        // 获取高-测量规则的模式和大小
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        // 设置wrap_content的默认宽 / 高值
        // 默认宽/高的设定并无固定依据,根据需要灵活设置
        // 类似TextView,ImageView等针对wrap_content均在onMeasure()对设置默认宽 / 高值有特殊处理,具体读者可以自行查看
//        int mWidth = 400;
//        int mHeight = 3400;

        // 当布局参数设置为wrap_content时，设置默认值
        if (getLayoutParams().width == ViewGroup.LayoutParams.WRAP_CONTENT && getLayoutParams().height == ViewGroup
                .LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(defaultWidth, defaultHeight);
            // 宽 / 高任意一个布局参数为= wrap_content时，都设置默认值
        } else if (getLayoutParams().width == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(defaultWidth, heightSize);
        } else if (getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(widthSize, defaultHeight);
        }
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.e("lh", "onSizeChanged执行");
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        itemWidth = (width - gdWidth) / 10;
//        itemWidth = width / defaultColumn;
//        initRectList();
        Log.e("lh", "width=" + width + "====height=" + height + "---itemWidth=" + itemWidth);
    }

    public void setData(List<Integer> list) {
        this.list = list;
        if (list == null) {
            return;
        }
        defaultHeight = itemHeight * list.size();
        pathList = getPaths(getPath());
        requestLayout();
    }

    private Path getPath() {
        Path path = new Path();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                int x = gdWidth + list.get(i) * itemWidth + itemWidth / 2;
                int y = i * itemHeight + itemHeight / 2;
                if (i == 0) {
                    path.moveTo(x, y);
                } else {
                    path.lineTo(x, y);
                }
            }
        }
        return path;
    }

    public static final int DEFAULT_SEGMENT_LENGTH = 10;

    /**
     * 截取path
     *
     * @param path
     */
    private List<Path> getPaths(Path path) {
        List<Path> list = new ArrayList<>();
        PathMeasure pm = new PathMeasure(path, false);
        float length = pm.getLength();
        int segmentSize = (int) Math.ceil(length / DEFAULT_SEGMENT_LENGTH);
        Path tempPath;
        for (int i = 0; i < segmentSize; i++) {
            tempPath = new Path();
            pm.getSegment((i - 1) * DEFAULT_SEGMENT_LENGTH - 0.4f, Math.min(i * DEFAULT_SEGMENT_LENGTH, length),
                    tempPath, true);
            list.add(tempPath);
        }
        return list;
    }
}
