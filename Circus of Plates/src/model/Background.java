package model;

import java.awt.Image;

import utilities.ControlPanel;

public class Background {
	private Image animation, background;
	private int x, y, dx, dy;

	public Background(int dx, int dy, Image background, Image animation) {
		this.dx = dx;
		this.dy = dy;
		this.background = background;
		this.animation = animation;
		x = 0;
		y = 0;
	}

	public Image getBackground() {
		return background;
	}

	public int getDx() {
		return dx;
	}

	public int getDy() {
		return dy;
	}

	public Image getAnimation() {
		return animation;
	}

	public void incrementX() {
		// System.out.println("update");
		x += dx;
		x %= ControlPanel.PANEL_WIDTH;
	}

	public void incrementY() {
		y += dy;
		y %= ControlPanel.PANEL_HEIGHT;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
