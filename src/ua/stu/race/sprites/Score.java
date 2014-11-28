package ua.stu.race.sprites;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Score implements ISprite {

	private int x = 20;
	private int y = 20;
	
	private Paint paint;
	
	public Score () {
		paint = new Paint();
		paint.setColor(Color.CYAN);
		paint.setTextSize(20);
		CurrentScore.score = 0;
	}
	
	@Override
	public void onDraw(Canvas canvas) {
		CurrentScore.score += Road.getSpeed();
		canvas.drawText(String.valueOf((int)(CurrentScore.score * 2/100)), x, y, paint);
	}

}
