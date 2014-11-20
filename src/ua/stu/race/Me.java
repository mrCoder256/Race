package ua.stu.race;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Me implements ISprite {

	private Context context;
	private Bitmap myCar;
	private static int POS_X;
	private static int POS_Y;

	//public static int speed = 6;

	public Me(Context context) {
		this.context = context;
		myCar = BitmapFactory.decodeResource(this.context.getResources(),R.drawable.me);
		POS_Y = 215;
		POS_Y = 213;
	}

	public static void setPOS_X(int pOS_X) {
		POS_X = pOS_X;
	}

	public static void setPOS_Y(int pOS_Y) {
		POS_Y = pOS_Y;
	}

	@Override
	public void onDraw(Canvas canvas) {
		canvas.drawBitmap(myCar, POS_X, POS_Y, null);
	}

}
