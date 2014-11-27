package ua.stu.race.sprites;

import ua.stu.race.MainActivity;
import ua.stu.race.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;

public class Me implements ISprite {

	private static final String TAG = "myLogs";

	private Canvas canvas;
	
	private Context context;
	private Bitmap myCar;
	private static int POS_Y;

	private int POS_X = 0;
	
	private boolean init = false;
	
	public Me(Context context) {
		this.context = context;
		myCar = BitmapFactory.decodeResource(this.context.getResources(),R.drawable.me);
	}
	
	@Override
	public void onDraw(Canvas canvas) {
		if (!init)
			start(canvas);
		
		POS_Y = canvas.getHeight() - 10 - myCar.getHeight();
		
		float temp = MainActivity.getY();	
		POS_X += Math.round(temp);
		
		if (POS_X + myCar.getWidth() > canvas.getWidth())
			POS_X = canvas.getWidth() - myCar.getWidth();
		else if (POS_X < 0)
			POS_X = 0;
		
		canvas.drawBitmap(myCar, POS_X, POS_Y, null);
	}

	private void start(Canvas canvas) {
		this.canvas = canvas;
		POS_X = (canvas.getHeight() - myCar.getWidth()) / 2;
		init = true;
	}

	public void bang() {
		int x = myCar.getWidth();
		int x1 = myCar.getWidth() / 4;
		Point center = new Point(POS_X + x / 2, POS_Y);
		
		Paint bangPaint = new Paint();
		bangPaint.setColor(Color.RED);
		bangPaint.setStyle(Style.FILL);

		Path wallpath = new Path();
		
		wallpath.moveTo(center.x, center.y - (int)(x * 0.7));
		
		wallpath.lineTo(center.x + (int)(x1 * 0.7), center.y - (int)(x1 * 0.7));		
		wallpath.lineTo(center.x + (int)(x * 0.7), center.y - (int)(x * 0.7));
		wallpath.lineTo(center.x + x1, center.y);
		wallpath.lineTo(center.x + (int)(x * 0.7), center.y + (int)(x * 0.7));
		wallpath.lineTo(center.x + (int)(x1 * 0.7), center.y + (int)(x1 * 0.7));
		wallpath.lineTo(center.x, center.y + x / 2);
		wallpath.lineTo(center.x - (int)(x1 * 0.7), center.y + (int)(x1 * 0.7));
		wallpath.lineTo(center.x - (int)(x * 0.7), center.y + (int)(x * 0.7));
		wallpath.lineTo(center.x - x1, center.y);
		wallpath.lineTo(center.x - (int)(x * 0.7), center.y - (int)(x * 0.7));
		wallpath.lineTo(center.x - (int)(x1 * 0.7), center.y - (int)(x1 * 0.7));
		wallpath.lineTo(center.x, center.y - x / 2);

		canvas.drawPath(wallpath, bangPaint);			

		Paint text = new Paint();
		text.setColor(Color.GREEN);
		text.setTextSize(25);
		canvas.drawText("BANG", center.x - x / 2, center.y + x1 / 2, text);
	}
	
	public Rect getRect() {
		return new Rect(POS_X, POS_Y, POS_X + myCar.getWidth(), POS_Y + myCar.getHeight());
	}
	
	

}
