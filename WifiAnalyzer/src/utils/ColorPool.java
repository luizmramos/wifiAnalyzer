package utils;

import android.graphics.Color;


public class ColorPool {

	private static int[] colors = {
		Color.rgb(0, 200, 0),Color.rgb(200, 0, 0),
		Color.rgb(0, 0, 200),Color.rgb(200, 200, 0),
		Color.rgb(0, 200, 200),Color.rgb(200, 0, 200),
		Color.rgb(200, 100, 0),Color.rgb(0, 200, 100),
		Color.rgb(100, 0, 200),Color.rgb(200, 200, 200),
		Color.rgb(200, 50, 100),Color.rgb(100, 200, 50),
		Color.rgb(20, 100, 200),Color.rgb(0, 0, 0)};
	private static int[] transparents = {
		Color.argb(50,0, 200, 0),Color.argb(50,200, 0, 0),
		Color.argb(50,0, 0, 200),Color.argb(50,200, 200, 0),
		Color.argb(50,0, 200, 200),Color.argb(50,200, 0, 200),
		Color.argb(50,200, 100, 0),Color.argb(50,0, 200, 100),
		Color.argb(50,100, 0, 200),Color.argb(50,200, 200, 200),
		Color.argb(50,200, 50, 100),Color.argb(50,100, 200, 50),
		Color.argb(50,20, 100, 200),Color.argb(50,0, 0, 0)};	
	public static int getColor(int i){
		return colors[i%colors.length];
		
	}
	
	public static int getTransparentColor(int i){
		return transparents[i%transparents.length];
	}
	
	
}
