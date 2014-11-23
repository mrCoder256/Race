package ua.stu.race.sprites;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

public class Road implements ISprite {


	private Paint paint;

	private int lineHeight = 0;
	private int lineWidth = 0;

	private boolean isInit = false;

	public static int speed = 10;

	private ArrayList<Point> lines;

	public Road(Context context) {
		lines = new ArrayList<Point>();
		paint = new Paint();
	}

	@SuppressLint("DrawAllocation")
	public void onDraw(Canvas canvas) {

		if (!isInit) {
			initLines(canvas);
		}

		paint.setColor(Color.BLACK);
		canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), paint);
		paint.setColor(Color.WHITE);
		for (Point p : lines) {
			canvas.drawRect(p.x, p.y, p.x + lineWidth, p.y + lineHeight, paint);
			canvas.drawRect(canvas.getWidth() / 3, p.y, canvas.getWidth() / 3
					+ lineWidth, p.y + lineHeight, paint);
			canvas.drawRect(canvas.getWidth() * 2 / 3, p.y, canvas.getWidth()
					* 2 / 3 + lineWidth, p.y + lineHeight, paint);
			canvas.drawRect(canvas.getWidth() - lineWidth, p.y,
					canvas.getWidth() - lineWidth + lineWidth,
					p.y + lineHeight, paint);
			p.y += speed;
			//bug..will be fixed
			if (p.y >= canvas.getHeight() + lineHeight / 2) {
				p.y = 0 - lineHeight / 2 - lineHeight;
			}
		}
	}

	private void initLines(Canvas canvas) {
		lineHeight = canvas.getHeight() / 4;
		lineWidth = canvas.getWidth() / 30;
		isInit = true;
		Point p1 = new Point(0, 0);
		Point p2 = new Point(0, lineHeight + lineHeight / 2);
		Point p3 = new Point(0, lineHeight * 2 + lineHeight);
		Point p4 = new Point(0, 0 - lineHeight / 2 - lineHeight);
		lines.add(p1);
		lines.add(p2);
		lines.add(p3);
		lines.add(p4);
	}

	public static void setSpeed(int s) {
		speed = s;
	}
	
	public static int getSpeed() {
		return speed;
	}

}
