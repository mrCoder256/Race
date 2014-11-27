package ua.stu.race;

import java.util.List;

import ua.stu.race.sprites.Road;
import ua.stu.race.sprites.Traffic;
import ua.stu.result.Result;
import ua.stu.result.ResultManager;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

@SuppressLint("NewApi")
public class MainActivity extends Activity {

	
	private ResultManager resultManager; 
	
    private SensorManager sManager;  
    private Sensor mAccelerometerSensor;
    
    private static float X;
    private static float Y;
    private static float Z;

    public static String userName;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		 Button startGameBtn = (Button) findViewById(R.id.startBtn);
	        startGameBtn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
					builder.setTitle("Enter your name");

					final EditText input = new EditText(MainActivity.this);
					input.setInputType(InputType.TYPE_CLASS_TEXT);
					builder.setView(input);

					builder.setPositiveButton("OK", new DialogInterface.OnClickListener() { 
					    @Override
					    public void onClick(DialogInterface dialog, int which) {
					    	if (input.getText().toString().isEmpty())
					    		return;
					    	MainActivity.userName = input.getText().toString();
					    	
					    	Intent intent = new Intent(MainActivity.this, GameViewActivity.class);
					    	MainActivity.this.startActivity(intent);					    	
					    	
					    	// switch to game activity
					    	// setContentView(new GameView(MainActivity.this));
					    }
					});
					builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					    @Override
					    public void onClick(DialogInterface dialog, int which) {
					        dialog.cancel();
					    }
					});
					builder.show();
				}
			});
	        
	        resultManager = new ResultManager(this);
	        showTopResults();
	       
	        sManager = (SensorManager) getSystemService(SENSOR_SERVICE);  
			List<Sensor> sensors = sManager.getSensorList(Sensor.TYPE_ALL);
	        if(sensors.size() > 0)
	        {
	            for (Sensor sensor : sensors) {
	                switch(sensor.getType())
	                {
	                case Sensor.TYPE_ACCELEROMETER:
	                    if(mAccelerometerSensor == null) mAccelerometerSensor = sensor;
	                    break;
	                default:
	                    break;
	                }
	            }
		    }
	}
	
	public void showTopResults() {
		List<Result> results = (List<Result>) resultManager.getTopResults();
    	if (results == null || results.isEmpty())
    		return;	    	
    	ListView listView = (ListView) findViewById(R.id.listView);
        ArrayAdapter<Result> adapter = new ArrayAdapter<Result>(this,
            android.R.layout.simple_list_item_1, results);
        listView.setAdapter(adapter);
	}

	


}
