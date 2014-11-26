package ua.stu.race;

import ua.stu.race.sprites.*;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

@SuppressLint("WrongCall")
public class GameView extends SurfaceView {
	private GameThread mThread;

	private Road road;
	private Me me;
	private Traffic traffic;
	private Score score;
	
	public GameView(Context context) {
		super(context);
		
		road = new Road(context);
		me = new Me(context);
		traffic = new Traffic(context);
		score = new Score();
		
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
		road.onDraw(canvas);
		me.onDraw(canvas);
		traffic.onDraw(canvas);
		score.onDraw(canvas);
		detectCollision(traffic.getCars()[0], me);
	}
	
	private void detectCollision(Car car, Me player) {
		Rect r1 = car.getRect();
		Rect r2 = player.getRect();
		if (r1.intersect(r2)) {
			mThread.setRunning(false);
		}
	}
}