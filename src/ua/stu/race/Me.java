package ua.stu.race;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Me implements ISprite {

	private Context context;
	private Bitmap myCar;
	private static final int POS_Y = 215;
	private static final int MIDDLE = 213; //LEFTMOST=80; RIGHTMOST=346;

	public Me(Context context) {
		this.context = context;
		myCar = BitmapFactory.decodeResource(this.context.getResources(),R.drawable.me);
	}
	
	@Override
	public void onDraw(Canvas canvas) {
		float temp = MainActivity.getY();
		float pos = (temp > 10) ? 10 : (temp < -10) ? -10 : temp;		
		int x = MIDDLE - Math.round(pos * 13.3f);
		canvas.drawBitmap(myCar, x, POS_Y, null);
	}

}
