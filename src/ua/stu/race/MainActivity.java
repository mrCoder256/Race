package ua.stu.race;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity implements SensorEventListener {

    private SensorManager sManager;  
    private static float X;
    private static float Y;
    private static float Z;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(new GameView(this));

        sManager = (SensorManager) getSystemService(SENSOR_SERVICE);  
	}

	@Override  
    protected void onResume()  
    {  
        super.onResume();  
        sManager.registerListener(this, sManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),SensorManager.SENSOR_DELAY_FASTEST);  
    }  
  
  //When this Activity isn't visible anymore  
    @Override  
    protected void onStop()  
    {  
        //unregister the sensor listener  
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
