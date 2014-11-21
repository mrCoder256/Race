package ua.stu.race.sprites;

import ua.stu.race.MainActivity;
import ua.stu.race.R;
import ua.stu.race.R.drawable;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Car implements ISprite {

	private Context context;
	private Bitmap car1, car2, car3;
	private Bitmap car4, car5, car6;
	private static final int POS_Y = 215;
	private static final int MIDDLE = 213; //LEFTMOST=80; RIGHTMOST=346;

	public Car(Context context) {
		this.context = context;
		car1 = BitmapFactory.decodeResource(this.context.getResources(),R.drawable.car);
		car2 = BitmapFactory.decodeResource(this.context.getResources(),R.drawable.car);
		car3 = BitmapFactory.decodeResource(this.context.getResources(),R.drawable.car);
		car4 = BitmapFactory.decodeResource(this.context.getResources(),R.drawable.car);
		car5 = BitmapFactory.decodeResource(this.context.getResources(),R.drawable.car);
		car6 = BitmapFactory.decodeResource(this.context.getResources(),R.drawable.car);
	}
	
	@Override
	public void onDraw(Canvas canvas) {
		float temp = MainActivity.getY();
		float pos = (temp > 10) ? 10 : (temp < -10) ? -10 : temp;		
		int x = MIDDLE - Math.round(pos * 13.3f);
		canvas.drawBitmap(car1, x, POS_Y, null);
	}

}
