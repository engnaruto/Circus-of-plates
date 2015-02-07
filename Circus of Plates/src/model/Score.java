package model;

import java.io.Serializable;

import utilities.ControlPanel;

public class Score implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int x, y, score;

	// private Rectangle rectangle;

	public Score(int y) {
		x = ControlPanel.SCORE_X;
		this.y = ControlPanel.SCORE_Y_PLAYER[y - 1];
		score = 0;
		// rectangle = new Rectangle(StaticHolder.SCORE_X, this.y,
		// StaticHolder.SCORE_WIDTH, StaticHolder.SCORE_HEIGHT);

	}

	public void incrementScore() {
		score++;
	}

	public int getFontX() {
		return ControlPanel.SCORE_X;
		// return (int) rectangle.getCenterX() - 5;
	}

	//
	public int getFontY() {
		return y;
		// return (int) rectangle.getCenterY() + 5;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getScore() {
		return score;
	}

	// public Rectangle getRectangle() {
	// return rectangle;
	// }

}
