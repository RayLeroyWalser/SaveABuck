package com.caux.saveabuck.colorpicker;

import com.caux.saveabuck.piechart.PieChart;
import com.example.saveabuck.R;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewGroup;

public class Colorpicker extends ViewGroup {
	private RectF bounds;
	private Paint paint;
	private Integer[] colors;
	
	private Integer maxRegions = 8;
	private Integer numberOfRows = 2;

	private Integer selectedRegion = -1;
	
	private GestureDetector mDetector;
	

	
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
		paint = new Paint();
		
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
		mDetector = new GestureDetector(Colorpicker.this.getContext(), new GestureListener());
		
		
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		// Style of paint
		paint.setStyle(Paint.Style.FILL);
		
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
		boolean result = mDetector.onTouchEvent(event);

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
		
		if(region == selectedRegion) {
			return (Math.min(spacingHor, spacingVer) / 2);
		}
		else {
			return (Math.min(spacingHor, spacingVer) / 2) - 15;

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
    			
    			Colorpicker.this.invalidate();
            }
			
			
			
			return true;
		}
		

	}
}
