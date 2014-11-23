package ua.stu.race.sprites;

import java.util.Random;

import ua.stu.race.R;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Traffic implements ISprite {

	private Context context;
	private Car[] cars;
	
	private static int speed = 6;

	private Random random;
	
	private boolean isInit = false;

	public Traffic(Context context) {
		random = new Random();
		
		this.context = context;
		cars = new Car[] {
			new Car(BitmapFactory.decodeResource(this.context.getResources(),
				R.drawable.car), 0, -148, false),
			new Car(BitmapFactory.decodeResource(this.context.getResources(),
				R.drawable.car), 0, -148, false),
			new Car(BitmapFactory.decodeResource(this.context.getResources(),
				R.drawable.car), 0, -148, false)
			};
	}
	
	@Override
	public void onDraw(Canvas canvas) {
		
		if ( !isInit ) {
			initTraffic(canvas);
		} 
		
		
		 cars[0].setY(cars[0].getY() + speed);
		
		 if (cars[0].getY() > canvas.getHeight()) {
			 cars[0].setY(-148);
			 int pos = random.nextInt(canvas.getWidth() - cars[0].getPicture().getWidth()); 
			cars[0].setX(pos);
		 }
		canvas.drawBitmap(cars[0].getPicture(), cars[0].getX(), cars[0].getY(), null);
	}
	
	private void initTraffic(Canvas canvas) {
		int pos = random.nextInt(canvas.getWidth() - cars[0].getPicture().getWidth()); 
		cars[0].setX(pos);
		isInit = true;
	}
	
	public static void setSpeed(int s) {
		speed = s;
	}
	
	public Car[] getCars() {
		return cars;
	}


}