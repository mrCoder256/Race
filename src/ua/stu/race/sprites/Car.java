package ua.stu.race.sprites;

import android.graphics.Bitmap;

public class Car {
	
	private boolean visible;
	private int x;
	private int y;
	private Bitmap picture;
	
	public Car(Bitmap picture, int x, int y, boolean visible) {
		super();
		this.visible = visible;
		this.x = x;
		this.y = y;
		this.picture = picture;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Bitmap getPicture() {
		return picture;
	}

	public void setPicture(Bitmap picture) {
		this.picture = picture;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

}