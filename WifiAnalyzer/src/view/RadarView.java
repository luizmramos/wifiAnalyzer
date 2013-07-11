package view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.Log;
import android.view.View;

public class RadarView extends View{
	
	private Paint radarBackground,textPaint,radarGreen,radarYellow,radarRed,linePaint,cursorPaint;
	private RectF radarOval;
	private int textHeight,lineSize;
	
	
	private float cursorAngle,nextCursorAngle;
	
	public RadarView(Context context) {
		super(context);
		initRadarView();
		Log.d("radar","Initializing radar.");
	}
	
	private void initRadarView(){
		cursorAngle=nextCursorAngle=0;
		
		radarBackground = new Paint();
		radarBackground.setColor(Color.GRAY);
		radarBackground.setAntiAlias(true);
		
		
		textPaint = new Paint();
		textPaint.setColor(Color.WHITE);
		textPaint.setStrokeWidth(3);
		
		textHeight=(int)textPaint.measureText("yY");
		lineSize = 8;
		linePaint = new Paint();
		linePaint.setColor(Color.WHITE);
		linePaint.setStrokeWidth(2);
		linePaint.setAntiAlias(true);
		
		
		radarOval = new RectF(0,0,getMeasuredWidth(),getMeasuredHeight());
		
		int strokeWidth = 4;

		radarGreen = new Paint();
		radarGreen.setStyle(Style.STROKE);
		radarGreen.setColor(Color.GREEN);
		radarGreen.setStrokeWidth(strokeWidth);
		radarGreen.setAntiAlias(true);
		
		radarYellow = new Paint();
		radarYellow.setStyle(Style.STROKE);
		radarYellow.setColor(Color.YELLOW);
		radarYellow.setStrokeWidth(strokeWidth);
		radarYellow.setAntiAlias(true);
		
		radarRed = new Paint();
		radarRed.setStyle(Style.STROKE);
		radarRed.setColor(Color.RED);
		radarRed.setStrokeWidth(strokeWidth);
		radarRed.setAntiAlias(true);
		
		cursorPaint = new Paint();
		cursorPaint.setStyle(Style.STROKE);
		cursorPaint.setColor(Color.RED);
		cursorPaint.setAntiAlias(true);
		
		
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		int radius = Math.min(getMeasuredWidth()/2,getMeasuredHeight());
		radarOval.set(0,5,2*radius,2*radius);
		Log.d("radar", "ta desenhando");
		canvas.drawArc(radarOval, -150, 120, true, radarBackground);
		
		
		canvas.drawArc(radarOval, -60, 30, false, radarGreen);
		canvas.drawArc(radarOval, -120, 60, false, radarYellow);
		canvas.drawArc(radarOval, -150, 30, false, radarRed);
		
		
		canvas.save();
		canvas.translate(0, radarOval.top);
		canvas.rotate(-60,radarOval.right/2,radarOval.bottom/2);
		for(int i=-100;i<=-40;i+=1){
			if(i%10==0){
				canvas.drawLine(radarOval.right/2, 0,radarOval.right/2, 2*lineSize,linePaint);
				canvas.save();
				canvas.translate(0, lineSize*2+textHeight);
				canvas.drawText(""+i, radarOval.right/2-textPaint.measureText(""+i)/2, 0, textPaint);
				canvas.restore();
			}
			else{
				canvas.drawLine(radarOval.right/2, 0,radarOval.right/2, lineSize,linePaint);
			}
			canvas.rotate(2,radarOval.right/2,radarOval.bottom/2);
		}
		
		
		canvas.restore();
		canvas.save();
		canvas.translate(0, radarOval.top);
		canvas.rotate(cursorAngle,radarOval.right/2,radarOval.bottom/2);
		canvas.drawLine(radarOval.right/2, radarOval.top,radarOval.right/2, radarOval.bottom/2,cursorPaint);
		canvas.restore();
		
		
	}
	@Override
	public void onWindowFocusChanged(boolean hasWindowFocus) {
		if(!hasFocus()){
			
		}
		super.onWindowFocusChanged(hasWindowFocus);
	}
	
	
	public void setStrength(int strength){
		if(strength>-40){
			strength = -40;
		}
		if(strength<-100){
			strength=-100;
		}
		nextCursorAngle=-60+(strength+100)*2;
		Log.d("radar","forca: " + strength+" angle" + cursorAngle);
	}
	
	public void update(){
		if(cursorAngle==nextCursorAngle)return;
		cursorAngle+=(nextCursorAngle-cursorAngle)/2.0f;
		invalidate();
	}
	
	

}
