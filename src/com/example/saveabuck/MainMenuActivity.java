package com.example.saveabuck;

import com.example.saveabuck.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;

public class MainMenuActivity extends Activity {
    /** Called when the activity is first created. */
    float values[]={300,400,100,500};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_principal);
		
        LinearLayout linear=(LinearLayout) findViewById(R.id.piechart);
        
        values=calculateData(values);
        linear.addView(new MyGraphview(this,values));		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_principal, menu);
		return true;
	}
	
	/** Called when the user clicks the Categorias button */
	public void buttonCategoriasClicked(View view) {
	    Intent intent = new Intent(this, GroupActivity.class);
	    startActivity(intent);		
	}

	/** Called when the user clicks the Contas button */
	public void buttonContasClicked(View view) {
	    Intent intent = new Intent(this, EnvelopeActivity.class);
	    startActivity(intent);		
	}	

	/** Called when the user clicks the Lancamento button */
	public void buttonLancamentoClicked(View view) {
	    Intent intent = new Intent(this, TransactionActivity.class);
	    startActivity(intent);		
	}	

	/** Called when the user clicks the Contas button */
	public void buttonClicked(View view) {
		
	}
	
    private float[] calculateData(float[] data) {
        // TODO Auto-generated method stub
        float total=0;
        for(int i=0;i<data.length;i++)
        {
            total+=data[i];
        }
        for(int i=0;i<data.length;i++)
        {
        data[i]=360*(data[i]/total);            
        }
        return data;

    }
    
    // TODO Pie Chart
    public class MyGraphview extends View
    {
        private Paint paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        private float[] value_degree;
        private int[] COLORS={Color.BLUE,Color.GREEN,Color.GRAY,Color.CYAN,Color.RED};
        int temp=0;
        int viewWidth;
        int viewHeight;
        RectF rectf = new RectF (0,0,0,0);
        
        
        public MyGraphview(Context context, float[] values) {

            super(context);
            value_degree=new float[values.length];
            for(int i=0;i<values.length;i++)
            {
                value_degree[i]=values[i];
            }
        }
        
        @Override
        protected void onSizeChanged(int xNew, int yNew, int xOld, int yOld){
            super.onSizeChanged(xNew, yNew, xOld, yOld);

            viewWidth = xNew;
            viewHeight = yNew;
       }        
        
        @Override
        protected void onDraw(Canvas canvas) {
            // TODO Auto-generated method stub
            super.onDraw(canvas);            
            
            int iHeight = this.getHeight();
            int iWidth = this.getWidth();
            
            rectf.set(200, 10, iHeight - 10, iWidth - 200);      
            
            for (int i = 0; i < value_degree.length; i++) {//values2.length; i++) {
                if (i == 0) {
                    paint.setColor(COLORS[i]);
                    canvas.drawArc(rectf, 0, value_degree[i], true, paint);
                } 
                else
                {
                        temp += (int) value_degree[i - 1];
                        paint.setColor(COLORS[i]);

                        canvas.drawArc(rectf, temp, value_degree[i], true, paint);

                }
            }
        }

    }	
}
