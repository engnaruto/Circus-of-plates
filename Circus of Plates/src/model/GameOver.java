package model;

import java.awt.Rectangle;

import utilities.ControlPanel;

public class GameOver {
	private Rectangle menu;
	private int x, y, width, height;
	private String player1, player2, score1, score2;

	public GameOver() {
		x = ControlPanel.WIN_MENU_X;
		y = ControlPanel.WIN_MENU_Y;
		width = ControlPanel.WIN_MENU_WIDTH;
		height = ControlPanel.WIN_MENU_HEIGHT;
		menu = new Rectangle(x, y, width, height);
	}

	public Rectangle getMenu() {
		return menu;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public String getPlayer1() {
		return player1;
	}

	public void setPlayer1(String player1) {
		this.player1 = player1;
	}

	public String getPlayer2() {
		return player2;
	}

	public void setPlayer2(String player2) {
		this.player2 = player2;
	}

	public String getScore1() {
		return score1;
	}

	public void setScore1(String score1) {
		this.score1 = score1;
	}

	public String getScore2() {
		return score2;
	}

	public void setScore2(String score2) {
		this.score2 = score2;
	}

}
