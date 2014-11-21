package ua.stu.race.sprites;

import java.util.Random;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

@SuppressLint("DrawAllocation")
public class NaukExample implements ISprite {

	private int y = -50;
	private int y2 = 480;
	private int startX = 0;
	private int x = 0;
	private int y3 = -10;
	private Random rn;
	private int width = 60;

	private int speed = 6;

	public NaukExample() {
		rn = new Random();
		startX = rn.nextInt(290);
	}

	@Override
	public void onDraw(Canvas canvas) {

		Paint p = new Paint();
		p.setColor(Color.WHITE);
		canvas.drawText("Наюк хуй", 100, y, p);

		y += speed;
		if (y > canvas.getHeight() + 50) {
			y = -50;
		}

		canvas.drawText("Наюк хуй", 170, y2, p);

		y2 -= speed;
		if (y2 < -50) {
			y2 = 480;
		}

		y3 = y3 + speed;
		if (y3 > 480) {
			y3 = -10;
			width = rn.nextInt(60) + 20;
		}
		x = startX + ((int) (Math.sin(y * Math.PI / 180) * (width)));
		canvas.drawText("Наюк хуй", x, y3, p);

	}

}
