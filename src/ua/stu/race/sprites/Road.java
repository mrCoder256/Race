package ua.stu.race.sprites;

import java.util.ArrayList;

import ua.stu.race.R;
import ua.stu.race.R.drawable;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;

public class Road implements ISprite {

	private Context context;
	private Bitmap roadSprite;
	private Bitmap roadSprite2;

	private ArrayList<Point> sprites;

	public static int speed = 6;

	public Road(Context context) {
		this.context = context;
		sprites = new ArrayList<Point>();
		roadSprite = BitmapFactory.decodeResource(this.context.getResources(),R.drawable.road);
		roadSprite2 = BitmapFactory.decodeResource(this.context.getResources(),R.drawable.road);
		initSprites();

	}

	@SuppressLint("DrawAllocation")
	public void onDraw(Canvas canvas) {

		canvas.drawBitmap(roadSprite2, sprites.get(1).x, sprites.get(1).y, null);
		sprites.get(1).y += speed;
		if (sprites.get(1).y >= roadSprite.getHeight() - speed) {
			sprites.get(1).y = roadSprite.getHeight() * (-1);
		}

		canvas.drawBitmap(roadSprite, sprites.get(0).x, sprites.get(0).y, null);
		sprites.get(0).y += speed;
		if (sprites.get(0).y >= roadSprite.getHeight() - speed) {
			sprites.get(0).y = roadSprite.getHeight() * (-1);
		}

	}

	private void initSprites() {
		Point p1 = new Point(0, 0);
		Point p2 = new Point(0, roadSprite.getHeight() * (-1));
		sprites.add(p1);
		sprites.add(p2);
	}

	public static void setSpeed(int s) {
		speed = s;
	}

}
