package view;

import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Paint;

import com.androidplot.series.XYSeries;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.PointLabelFormatter;
import com.androidplot.xy.XValueMarker;
import com.androidplot.xy.YLayoutStyle;
import com.androidplot.xy.YPositionMetric;

public class WifiStrengthXYSeries implements XYSeries{
	private final float scale = 1;
	private String name;
	private int channel;
	private float strength;
	private float nextStrength;
	private int color, transparent;
	public void setChannel(int channel) {
		this.channel = channel;
	}

	public void setStrength(int strength) {
		this.nextStrength = strength;
	}

	private static final int MINIMUM=100;
	LineAndPointFormatter formatter;
	
	public WifiStrengthXYSeries(String name, int channel, int strength, int color, int transparent){
		this.name = name;
		this.channel = channel;
		this.strength = strength;
		this.transparent = transparent;
		this.color=color;
		formatter = new LineAndPointFormatter(
				color,                   // line color
                null,                   // point color
                transparent,                                   // fill color (none)
                new PointLabelFormatter(Color.TRANSPARENT));         // text
	}
	
	@Override
	public String getTitle() {
		return name;
	}

	@Override
	public int size() {
		return 11;
	}

	@Override
	public Number getX(int x) {
		return (channel+(5-x)/2.5);
	}

	@Override
	public Number getY(int y) {
		return -MINIMUM+(strength+MINIMUM)*(1-(5-y)*(5-y)/25.0);
	}

	public LineAndPointFormatter getFormatter() {
		return formatter;
	}
	
	public void update(){
		float inc = 0;
		if(strength>nextStrength+30){
			inc = -10;
		}
		else if(strength>nextStrength+20){
			inc = -8;
		}
		else if(strength>nextStrength+10){
			inc = -4;
		}
		else if(strength>nextStrength+5){
			inc = -2;
		}
		else if(strength<nextStrength-30){
			inc = 10;
		}
		else if(strength<nextStrength-20){
			inc = 8;
		}
		else if(strength<nextStrength-10){
			inc = 4;
		}
		else if(strength<nextStrength-5){
			inc = 2;
		}
		strength+=inc*scale;
	}
	
	public XValueMarker getMarker(int orientation){
		int yPos;
		float xPos;
		if(orientation==Configuration.ORIENTATION_LANDSCAPE){
			yPos=(int)((strength+MINIMUM)*3.8);
			xPos=channel-name.length()/16.0f;
		}
		else{
			yPos=(int)((strength+MINIMUM)*6.8);
			xPos=channel-name.length()/8.0f;
		}
		XValueMarker xValueMarker = new XValueMarker(xPos, name);
        xValueMarker.setTextPosition(new YPositionMetric(yPos,YLayoutStyle.ABSOLUTE_FROM_BOTTOM));
        Paint paint = new Paint();
        paint.setColor(Color.TRANSPARENT);
        xValueMarker.setLinePaint(paint);
        Paint p2 = new Paint();
        p2.setColor(color);
        xValueMarker.setTextPaint(p2);
        return xValueMarker;
	}
	


}
