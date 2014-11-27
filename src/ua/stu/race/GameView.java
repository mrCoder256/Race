package ua.stu.race;

import java.util.Random;

import ua.stu.race.sprites.*;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.app.Activity;

@SuppressLint("WrongCall")
public class GameView extends SurfaceView {
	private GameThread mThread;

	private Road road;
	private Me me;
	private Traffic traffic;
	private Score score;
	private Health health;
	private Random random;
	private MediaPlayer mediaPlayer;
	private Context context;

	public GameView(Context context) {
		super(context);

		this.context = context;
		mediaPlayer = MediaPlayer.create(context, R.raw.boom);
		random = new Random();
		road = new Road(context);
		me = new Me(context);
		traffic = new Traffic(context);
		score = new Score();
		health = new Health(context);

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
		health.onDraw(canvas);
		detectCollision(traffic.getCars()[0], me, canvas);
	}

	private long bangCnt = 0;

	private void detectCollision(Car car, Me player, Canvas canvas) {
		Rect r1 = car.getRect();
		Rect r2 = player.getRect();

		if (me.isBang()) {
			if (bangCnt < 20) {
				me.bang();
				bangCnt++;
			} else {
				bangCnt = 0;
				me.setBang(false);
			}

		}

		if (r1.intersect(r2)) {
			// mThread.setRunning(false);
			me.setBang(true);
			// mThread.setRunning(false);
			if (health.getHealth() != 0) {
				/*
				 * if (mediaPlayer.isPlaying()) { mediaPlayer.stop(); } //тупит
				 * mediaPlayer.start();
				 */
				health.decHealth();
				car.setY(-148);
				car.setX(random.nextInt(canvas.getWidth())
						- car.getPicture().getWidth());
			} else {
				// mThread.stop();
				// Activity a = (Activity)context;
				// a.setContentView(R.layout.activity_main);
			}
		}
	}
}