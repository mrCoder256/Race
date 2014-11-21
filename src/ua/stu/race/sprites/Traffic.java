package ua.stu.race.sprites;

import java.util.Random;

import ua.stu.race.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Traffic implements ISprite {

	private Context context;
	private Car[] cars;
//	private Car car1, car2, car3;
//	private Car car4, car5, car6;
	private static final int POS_Y = 215;
	private static final int MIDDLE = 213; //LEFTMOST=80; RIGHTMOST=346;
	
	private int y1 = -148; 
	private int y2 = -148; 
	private int speed = 6;

	private Random random;

	public Traffic(Context context) {
		random = new Random();
		
		this.context = context;
		cars = new Car[] {
			new Car(BitmapFactory.decodeResource(this.context.getResources(),
				R.drawable.car), 100, -148, false),
			new Car(BitmapFactory.decodeResource(this.context.getResources(),
				R.drawable.car), 213, -148, false),
			new Car(BitmapFactory.decodeResource(this.context.getResources(),
				R.drawable.car), 326, -148, false),
			new Car(BitmapFactory.decodeResource(this.context.getResources(),
				R.drawable.car), 100, -250, false),
			new Car(BitmapFactory.decodeResource(this.context.getResources(),
				R.drawable.car), 213, -250, false),
			new Car(BitmapFactory.decodeResource(this.context.getResources(),
				R.drawable.car), 326, -250, false)
			};
	}
	
	@Override
	public void onDraw(Canvas canvas) {
		cars[0].setY(cars[0].getY() + speed);
		if (cars[0].getY() > 480) {//canvas.getHeight() + 50) {
			cars[0].setY(-148);
		}
		canvas.drawBitmap(cars[0].getPicture(), cars[0].getX(), cars[0].getY(), null);
	}
	
	private void setVisibles(){		
		String mask = Integer.toBinaryString(random.nextInt(6) + 1);
		if (cars[0].getY() > cars[3].getY()) {
			for (int i=0; i < mask.length(); i++){
				
			}
				
		}
	}

}
