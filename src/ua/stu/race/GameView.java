package ua.stu.race;

import ua.stu.race.sprites.Traffic;
import ua.stu.race.sprites.Me;
import ua.stu.race.sprites.NaukExample;
import ua.stu.race.sprites.Road;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

@SuppressLint("WrongCall")
public class GameView extends SurfaceView {
	private GameThread mThread;

	private Road road;
	private Me me;
	private Traffic traffic;
	
	//will be removed
	private NaukExample nauk;

	
	public GameView(Context context) {
		super(context);
		
		road = new Road(context);
		me = new Me(context);
		traffic = new Traffic(context);
		
		//will be removed
		nauk = new NaukExample();
		
		mThread = new GameThread(this);

		getHolder().addCallback(new SurfaceHolder.Callback() {
			public void surfaceCreated(SurfaceHolder holder) {
				mThread.setRunning(true);
				mThread.start();
			}
			
			public void surfaceDestroyed(SurfaceHolder holder) {
				boolean retry = true;
				mThread.setRunning(false);
				while (retry) {
					try {
						mThread.join();
						retry = false;
					} catch (InterruptedException e) {
					}
				}
			}

			public void surfaceChanged(SurfaceHolder holder, int format,
					int width, int height) {
			}
		});
	}

	public void onDraw(Canvas canvas) {
		//canvas.drawColor(Color.WHITE); //clear screen
		road.onDraw(canvas);
		me.onDraw(canvas);
		traffic.onDraw(canvas);
		//nauk.onDraw(canvas);
	}
}