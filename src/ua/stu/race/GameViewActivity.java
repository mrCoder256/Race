package ua.stu.race;

import java.util.List;

import ua.stu.race.sprites.Road;
import ua.stu.race.sprites.Traffic;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

@SuppressLint("NewApi")
public class GameViewActivity extends Activity implements SensorEventListener {

	private SensorManager sManager;
	private Sensor mAccelerometerSensor;

	private static float X;
	private static float Y;
	private static float Z;

	public static String userName;

	private GameView gameView;

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		this.finish();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		sManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		List<Sensor> sensors = sManager.getSensorList(Sensor.TYPE_ALL);
		if (sensors.size() > 0) {
			for (Sensor sensor : sensors) {
				switch (sensor.getType()) {
				case Sensor.TYPE_ACCELEROMETER:
					if (mAccelerometerSensor == null)
						mAccelerometerSensor = sensor;
					break;
				default:
					break;
				}
			}
		}

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		setContentView(new GameView(getBaseContext(), this));

	}

	@Override
	protected void onResume() {
		super.onResume();
		sManager.registerListener(this, mAccelerometerSensor,// sManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
				SensorManager.SENSOR_DELAY_FASTEST);
		// sManager.registerListener(this, mAccelerometerSensor,
		// SensorManager.SENSOR_DELAY_GAME);
	}

	// When this Activity isn't visible anymore
	@Override
	protected void onStop() {
		// unregister the sensor listener
		sManager.unregisterListener(this);
		super.onStop();
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		if (event.accuracy == SensorManager.SENSOR_STATUS_UNRELIABLE) {
			return;
		}
		setX(event.values[2]);
		setY(event.values[1]);
		setZ(event.values[0]);

		Road.setSpeed((int) (X / 1.5) + 1);
		Traffic.setSpeed((int) (X / 1.5));
	}

	public static float getX() {
		return X;
	}

	public static void setX(float x) {
		X = x;
	}

	public static float getY() {
		return Y;
	}

	public static void setY(float y) {
		Y = y;
	}

	public static float getZ() {
		return Z;
	}

	public static void setZ(float z) {
		Z = z;
	}

}
