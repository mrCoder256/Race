package ua.stu.race.sprites;

import ua.stu.race.MainActivity;
import ua.stu.race.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

public class Me implements ISprite {

	private static final String TAG = "myLogs";

	private Context context;
	private Bitmap myCar;
	private static int POS_Y;
	private static int MIDDLE; //LEFTMOST=80; RIGHTMOST=346;

	private int POS_X = 0;
	
	public Me(Context context) {
		this.context = context;
		myCar = BitmapFactory.decodeResource(this.context.getResources(),R.drawable.me);
	}
	
	@Override
	public void onDraw(Canvas canvas) {
		POS_Y = canvas.getHeight() - 10 - myCar.getHeight();
		MIDDLE = (canvas.getWidth() - myCar.getWidth()) / 2;
		
		if (POS_X == 0)
			POS_X = MIDDLE;
		
//		float temp = MainActivity.getY();
//		float pos = (temp > 10) ? 10 : (temp < -10) ? -10 : temp;		
//		int x = MIDDLE - Math.round(pos * 13.3f);
//		POS_X = x;
//		POS_X += MainActivity.getY();
//		canvas.drawBitmap(myCar, x, POS_Y, null);

		float temp = MainActivity.getY();
		float pos = (temp > 10) ? 10 : (temp < -10) ? -10 : temp;		
		POS_X += Math.round(pos);
		canvas.drawBitmap(myCar, POS_X, POS_Y, null);
		
        Log.d(TAG, String.valueOf("Me.POS_X=" + POS_X));
	}
	
	public Rect getRect() {
		return new Rect(POS_X, POS_Y, POS_X + myCar.getWidth(), POS_Y + myCar.getHeight());
	}
	
	

}
