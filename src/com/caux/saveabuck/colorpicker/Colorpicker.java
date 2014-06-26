package com.caux.saveabuck.colorpicker;

import com.example.saveabuck.R;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.OvershootInterpolator;;

public class Colorpicker extends ViewGroup {
	private RectF bounds;
	private Paint paint;
	private Integer[] colors;
	
	private Integer maxRegions = 8;
	private Integer numberOfRows = 2;

	private Integer selectedRegion = -1;
	
	private GestureDetector gestureDetector;
	
	private ObjectAnimator colorAnimatorSelect;
	private ObjectAnimator colorAnimatorNoSelection;
	
	private int selectedRadiusDifference;
	private int maxRadiusDifference = 20;
	
	public Colorpicker(Context context) {
		super(context);		
		init();
	}
	
	public Colorpicker(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public Colorpicker(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	protected void init() {

		// Paint
		paint = new Paint();
		paint.setStyle(Paint.Style.FILL);
		
		Resources res = getResources();
		
		colors = new Integer[maxRegions];
		colors[0] = res.getColor(R.color.dark_red);
		colors[1] = res.getColor(R.color.red);
		colors[2] = res.getColor(R.color.orange);
		colors[3] = res.getColor(R.color.yellow);
		colors[4] = res.getColor(R.color.green);
		colors[5] = res.getColor(R.color.cyan);
		colors[6] = res.getColor(R.color.blue);
		colors[7] = res.getColor(R.color.magenta);
	
		// Create a gesture detector to handle onTouch messages
		gestureDetector = new GestureDetector(Colorpicker.this.getContext(), new GestureListener());
	
		// Animator to change size of the selected color		
		colorAnimatorSelect = ObjectAnimator.ofInt(Colorpicker.this, "SelectedRadiusDifference", 0, maxRadiusDifference);		
		colorAnimatorSelect.setDuration(300);
		colorAnimatorSelect.setInterpolator(new AnticipateOvershootInterpolator());
		
		// Animator to change size of the selected color		
		colorAnimatorNoSelection = ObjectAnimator.ofInt(Colorpicker.this, "SelectedRadiusDifference", -maxRadiusDifference, 0);		
		colorAnimatorNoSelection.setDuration(300);
		colorAnimatorNoSelection.setInterpolator(new OvershootInterpolator());
		
		
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		
		for(Integer count = 0; count < maxRegions; count++)
		{
			RectF regionRect = getRegionRect(count);
			paint.setColor(colors[count]);
			float radius = getRadius(count);
			
			canvas.drawCircle(regionRect.centerX(), regionRect.centerY(), radius, paint);			
		}
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		bounds = new RectF(0, 0, w, h);
	}	

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// Let the GestureDetector interpret this event
		boolean result = gestureDetector.onTouchEvent(event);

		// If the GestureDetector doesn't want this event, do some custom processing.
		// This code just tries to detect when the user is done scrolling by looking
		// for ACTION_UP events.
		if (!result) {
			if (event.getAction() == MotionEvent.ACTION_UP) {
				// User is done scrolling, it's now safe to do things like autocenter
				result = true;
			}
		}
		return result;
	}	
	
	// maxRegions regions in total in numberOfRows rows
	protected RectF getRegionRect(Integer region) {
		RectF regionRect = new RectF();

		Integer numberOfColumns = (maxRegions / numberOfRows);		

		Integer column = region % (maxRegions / numberOfRows);
		Integer row = region / numberOfColumns;
		
		float spacingHor = (bounds.right / numberOfColumns);
		float spacingVer = (bounds.bottom / numberOfRows);
		
		regionRect.left 	= column * spacingHor;
		regionRect.right 	= (column + 1) * spacingHor;		
		regionRect.top 		= row * spacingVer;
		regionRect.bottom 	= (row + 1) * spacingVer;			
		
		return regionRect;
	}
	
	protected int getRegionClicked(PointF pointClicked) {
		Integer numberOfColumns = (maxRegions / numberOfRows);		
		
		float spacingHor = (bounds.right / numberOfColumns);
		float spacingVer = (bounds.bottom / numberOfRows);

		Integer selectedColumn = (int) (pointClicked.x / spacingHor);
		Integer selectedRow = (int) (pointClicked.y / spacingVer);
		
		return (selectedRow * numberOfColumns) + selectedColumn;
	}
	
	protected float getRadius(Integer region) {
		
		Integer numberOfColumns = (maxRegions / numberOfRows);		
		
		float spacingHor = (bounds.right / numberOfColumns);
		float spacingVer = (bounds.bottom / numberOfRows);
		
		float baseRadius = (float) ((Math.min(spacingHor, spacingVer) / 2) - maxRadiusDifference * 0.75);
		
		
		if(selectedRegion == -1) {
			return baseRadius + selectedRadiusDifference;
		}
			
		if(region == selectedRegion) {
			return (float) (baseRadius + (selectedRadiusDifference * 0.75));
		}
		else {
			return (float) (baseRadius - (selectedRadiusDifference * 0.25));

		}		
	}
	
	@Override
	protected void onLayout(boolean arg0, int arg1, int arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub
	}
	
	private class GestureListener extends GestureDetector.SimpleOnGestureListener {
		@Override
		public boolean onDown(MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN){
                PointF clicky = new PointF(event.getX(), event.getY());
    			
                int clickedRegion = Colorpicker.this.getRegionClicked(clicky);
 
    			Colorpicker.this.selectedRegion = clickedRegion;
    			

    			colorAnimatorSelect.start();
            }
			
			
			
			return true;
		}
		

	}
	
	public void showNoSelection() {
		colorAnimatorNoSelection.start();
	}

	public void setSelectedRadiusDifference(int selectedRadiusDifference) {
		this.selectedRadiusDifference = selectedRadiusDifference;
		Colorpicker.this.invalidate();

	}

	public Integer getSelectedColor() {
		return selectedRegion;
	}
	
	
}
