package ua.stu.race.sprites;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Score implements ISprite {

	private int x = 20;
	private int y = 20;
	
	private Paint paint;
	
	private static int score;
	
	public Score () {
		paint = new Paint();
		paint.setColor(Color.CYAN);
		paint.setTextSize(20);
		score = 0;
	}
	
	public static int getScore() {
		return score;
	}
	
	@Override
	public void onDraw(Canvas canvas) {
		score += Road.getSpeed();
		canvas.drawText(String.valueOf((Integer)(score * 2/100)), x, y, paint);
	}

}
