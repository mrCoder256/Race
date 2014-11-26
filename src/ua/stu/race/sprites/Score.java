package ua.stu.race.sprites;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Score implements ISprite {

	private int x = 20;
	private int y = 20;
	
	private Paint paint;
	
	private int score;
	
	public Score () {
		paint = new Paint();
		paint.setColor(Color.WHITE);
		paint.setTextSize(15);
		score = 0;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	@Override
	public void onDraw(Canvas canvas) {
		score += Road.getSpeed();
		canvas.drawText(String.valueOf((Integer)(score * 2/100)), x, y, paint);
	}

}
