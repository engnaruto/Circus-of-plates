package model;

import java.awt.Image;
import java.awt.Rectangle;

import utilities.LinkHolder;
import utilities.ControlPanel;

public class Line {
	private int x, y, length, num;
	private Rectangle bounds;
	Image image;
	private PlateEntity lastPlate;

	public Line(int x, int y, int length, int num) {
		this.x = x;
		this.y = y;
		this.length = length;
		this.num = num;
		bounds = new Rectangle(x, y - ControlPanel.PLATE_HEIGHT_MAX - 2, x
				+ length, ControlPanel.PLATE_HEIGHT_MAX + 2);
		image = LinkHolder.IMAGES[LinkHolder.IMAGES.length - 4];
		lastPlate = new PlateEntity(x + 51, y, 0, 0, num, -1);
		// System.out.println(lastPlate.getX());

	}

	public void debug() {
		System.out.println("Line: " + num + "\t" + getLastPlate().getX());
	}

	public int getStartPoint() {
		return x + 50;
	}

	public PlateEntity getLastPlate() {
		return lastPlate;
	}

	public void setLastPlate(PlateEntity lastPlate) {
		this.lastPlate = lastPlate;
	}

	public int getNum() {
		return num;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getLength() {
		return length;
	}

	public Image getImage() {
		return image;
	}

}
