package steven.lambrinos.n01429063;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class StevenGradient extends View {

    public StevenGradient(Context context) {
        super(context);
    }
    public StevenGradient(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public StevenGradient(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public StevenGradient(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    protected void onDraw(Canvas canvas) {
        float w, h, cy, cx, radius;
        w = getWidth();
        h = getHeight();
        cy = h/4;
        cx = w/4;

        if(w > h) {
            radius = h/6;
        }else {
            radius = w/6;
        }

        Paint MyPaint = new Paint();
        MyPaint.setStyle(Paint.Style.FILL);

        int color1 = Color.RED;
        int color2 = Color.MAGENTA;
        int color3 = Color.WHITE;

        MyPaint.setAntiAlias(true);
        Shader linearGradiant;

        linearGradiant = new LinearGradient(
                cx, cy, cx+radius, cy+radius, color1, color3, Shader.TileMode.MIRROR);
        MyPaint.setShader(linearGradiant);
        canvas.drawCircle(cx, cy, radius, MyPaint);

    }
}
