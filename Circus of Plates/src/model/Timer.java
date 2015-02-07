package model;

import java.awt.Rectangle;
import java.io.Serializable;

import utilities.ControlPanel;

public class Timer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long time;
	private static long lastTime;
	private Rectangle rectangle;

	public Timer(long time) {
		this.time = time;
		rectangle = new Rectangle(ControlPanel.SCORE_X, ControlPanel.TIMER_Y,
				ControlPanel.SCORE_WIDTH, ControlPanel.SCORE_WIDTH);
		lastTime = System.nanoTime();
	}

	public void setTime(long time) {
		this.time = time;
	}

	public int getFontX() {
		return ControlPanel.SCORE_X;
		// return (int) rectangle.getCenterX() - 5;
	}

	public int getFontY() {
		return (int) rectangle.getCenterY() + 5;
	}

	public long getTime() {
		return time;
	}

	public long getLastTime() {
		return lastTime;
	}

	public static void resetTime() {
		lastTime = System.nanoTime();
	}

	public Rectangle getRectangle() {
		return rectangle;
	}

	public void decrementTime() {
		lastTime = System.nanoTime();
		time--;
	}

}
