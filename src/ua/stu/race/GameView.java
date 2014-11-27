package ua.stu.race;

import java.util.Random;

import ua.stu.race.sprites.Car;
import ua.stu.race.sprites.Health;
import ua.stu.race.sprites.Me;
import ua.stu.race.sprites.Road;
import ua.stu.race.sprites.Score;
import ua.stu.race.sprites.Traffic;
import ua.stu.result.Result;
import ua.stu.result.ResultManager;
import ua.stu.result.StorageManager;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

@SuppressLint("WrongCall")
public class GameView extends SurfaceView {
	
	private static final String TAG = "myLogs";
	
	private GameThread mThread;

	private Road road;
	private Me me;
	private Traffic traffic;
	private Score score;
	private Health health;
	private Random random;
	private MediaPlayer mediaPlayer;
	private Context mainContext;
	private Context baseContext;

	/*
	 * mainContext - контекст MainActivity
	 * baseContext - контекст GameViewActivity (для AlertDialog)
	 */
	public GameView(Context mainContext, Context baseContext) {
		super(mainContext);

		this.mainContext = mainContext;
		this.baseContext = baseContext;
		mediaPlayer = MediaPlayer.create(mainContext, R.raw.boom);
		random = new Random();
		road = new Road(mainContext);
		me = new Me(mainContext);
		traffic = new Traffic(mainContext);
		score = new Score();
		health = new Health(mainContext);

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
			me.setBang(true);
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
				mThread.setRunning(false);
				showFinishAllert();
//				mThread.stop();
				// Activity a = (Activity)context;
				// a.setContentView(R.layout.activity_main);
			}
		}
	}

	private void showFinishAllert() {
		int scores = Score.getScore();
		Result currentResult = new Result(MainActivity.userName, scores);
		ResultManager resultManager = new ResultManager(mainContext);
		resultManager.addResult(currentResult);
		
		AlertDialog.Builder builder = new AlertDialog.Builder(baseContext);
		builder.setTitle("Игра закончина!")
			.setMessage("Вы набрали " + String.valueOf(scores) + " очков.")
			.setCancelable(false)
			.setNegativeButton("Окай",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		AlertDialog alert = builder.create();
		alert.show();

	}
}