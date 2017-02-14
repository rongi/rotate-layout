package com.github.rongi.rotate_layout.layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.github.rongi.rotate_layout.R;

/**
 * Rotates first view in this layout by multiple of 90 angle.
 * <p>
 * This layout is supposed to have only one view. Behaviour of the views after the first one
 * is not defined.
 * <p>
 * Rotate angles can be only multiple of 90.
 * If angle is not multiple of 90 it will be reduced to the multiple of 90.
 * For example 89 will be reduced to 0, 91 will be reduced to 90.
 * <p>
 * XML attributes
 * See {@link com.github.rongi.rotate_layout.R.styleable#RotateLayout RotateLayout Attributes},
 */
public class RotateLayout extends ViewGroup {

  private int angle;

  private final Matrix rotateMatrix = new Matrix();

  private final Rect viewRectRotated = new Rect();

  private final RectF tempRectF1 = new RectF();
  private final RectF tempRectF2 = new RectF();

  private final float[] viewTouchPoint = new float[2];
  private final float[] childTouchPoint = new float[2];

  private boolean angleChanged = true;

  public RotateLayout(Context context) {
    this(context, null);
  }

  public RotateLayout(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public RotateLayout(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs);

    final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RotateLayout);
    final int angleFromAttrs = a.getInt(R.styleable.RotateLayout_angle, 0);
    angle = fixAngle(angleFromAttrs);
    a.recycle();

    setWillNotDraw(false);
  }

  /**
   * Returns current angle of this layout
   */
  public int getAngle() {
    return angle;
  }

  /**
   * Sets current angle of this layout.
   * If angle is not multiple of 90 it will be reduced to the multiple of 90.
   * For example 89 will be reduced to 0, 91 will be reduced to 90.
   */
  public void setAngle(int angle) {
    int fixedAngle = fixAngle(angle);
    if (this.angle != fixedAngle) {
      this.angle = fixedAngle;
      angleChanged = true;
      requestLayout();
    }
  }

  /**
   * Returns this layout's child or null if there is no any
   */
  public View getView() {
    if (getChildCount() > 0) {
      return getChildAt(0);
    } else {
      return null;
    }
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    final View view = getView();
    if (view != null) {
      if (Math.abs(angle % 180) == 90) {
        //noinspection SuspiciousNameCombination
        measureChild(view, heightMeasureSpec, widthMeasureSpec);
        setMeasuredDimension(
          resolveSize(view.getMeasuredHeight(), widthMeasureSpec),
          resolveSize(view.getMeasuredWidth(), heightMeasureSpec));
      } else {
        measureChild(view, widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(
          resolveSize(view.getMeasuredWidth(), widthMeasureSpec),
          resolveSize(view.getMeasuredHeight(), heightMeasureSpec));
      }
    } else {
      super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
  }

  @Override
  protected void onLayout(boolean changed, int l, int t, int r, int b) {
    if (angleChanged || changed) {
      final RectF layoutRect = tempRectF1;
      final RectF layoutRectRotated = tempRectF2;
      layoutRect.set(0, 0, r - l, b - t);
      rotateMatrix.setRotate(angle, layoutRect.centerX(), layoutRect.centerY());
      rotateMatrix.mapRect(layoutRectRotated, layoutRect);
      layoutRectRotated.round(viewRectRotated);
      angleChanged = false;
    }

    final View view = getView();
    if (view != null) {
      view.layout(viewRectRotated.left, viewRectRotated.top, viewRectRotated.right, viewRectRotated.bottom);
    }
  }

  @Override
  protected void dispatchDraw(Canvas canvas) {
    canvas.save();
    canvas.rotate(-angle, getWidth() / 2f, getHeight() / 2f);
    super.dispatchDraw(canvas);
    canvas.restore();
  }

  @Override
  public ViewParent invalidateChildInParent(int[] location, Rect dirty) {
    invalidate();
    return super.invalidateChildInParent(location, dirty);
  }

  @Override
  public boolean dispatchTouchEvent(MotionEvent event) {
    viewTouchPoint[0] = event.getX();
    viewTouchPoint[1] = event.getY();

    rotateMatrix.mapPoints(childTouchPoint, viewTouchPoint);

    event.setLocation(childTouchPoint[0], childTouchPoint[1]);
    boolean result = super.dispatchTouchEvent(event);
    event.setLocation(viewTouchPoint[0], viewTouchPoint[1]);

    return result;
  }

  /**
   * Takes any angle, makes it valid one for this view.
   * This means multiple of 90.
   */
  private static int fixAngle(int angle) {
    return (angle / 90) * 90;
  }

}
