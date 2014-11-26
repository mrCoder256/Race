package ua.stu.race.sprites;

import ua.stu.race.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Health implements ISprite {

	private int health = 5;
	
	private Bitmap heart;
	
	public Health (Context context) {
		heart = BitmapFactory.decodeResource(context.getResources(),R.drawable.heart);
	}
	
	@Override
	public void onDraw(Canvas canvas) {
		for (int i = 1; i <= health; i++) {
			canvas.drawBitmap(heart, canvas.getWidth() - ( heart.getWidth() * i ) - 5 ,10, null);
		}
	}
	
	public int getHealth() {
		return health;
	}
	
	public void decHealth() {
		health--;
	}

}
