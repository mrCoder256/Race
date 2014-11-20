package ua.stu.race;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Me implements ISprite {

	private Context context;
	private Bitmap myCar;
	private int pos;

	//public static int speed = 6;

	public Me(Context context) {
		this.context = context;
		myCar = BitmapFactory.decodeResource(this.context.getResources(),R.drawable.me);
		pos = 0;
	}

	@Override
	public void onDraw(Canvas canvas) {
		canvas.drawBitmap(myCar, pos, 0, null);
	}

}
