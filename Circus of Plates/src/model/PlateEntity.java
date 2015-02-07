package model;

import java.awt.Image;
import java.awt.Rectangle;

import observer.Observable;
import observer.Observer;

import utilities.LinkHolder;
import utilities.ControlPanel;

public class PlateEntity extends Entity implements Observer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int position, num, color;
	private Rectangle bounds;
	private boolean visible;

	public PlateEntity(int x, int y, int color, int type, int position, int num) {
		int imageNum = getImageNum(color, type);
		setImage(LinkHolder.IMAGES[imageNum].getScaledInstance(
				ControlPanel.PLATE_WIDTH, ControlPanel.PLATE_HEIGHT[type],
				Image.SCALE_SMOOTH));

		setWidth(ControlPanel.PLATE_WIDTH);
		setHeight(ControlPanel.PLATE_HEIGHT[type]);

		setX(x);
		setY(y);

		setDx(ControlPanel.PLATE_DX);
		setDy(ControlPanel.PLATE_DY);

		this.position = position;

		this.num = num;

		this.color = color;

		bounds = new Rectangle(x, y, getWidth(), getHeight());

		visible = true;

		// System.out.println(type+"\t"+getHeight());
	}

	public void reset(Line line, int color, int type) {
		int lastPosition = line.getLastPlate().getX()
				- ControlPanel.PLATE_WIDTH;

		int plateX = lastPosition - ControlPanel.PLATE_WIDTH
				- ControlPanel.PLATE_DISTANCE;

		int plateY = line.getY() - ControlPanel.PLATE_HEIGHT[type];
		setX(plateX);
		setY(plateY);
		this.color = color;
		setHeight(ControlPanel.PLATE_HEIGHT[type]);
		visible = true;
		position = line.getNum();
		bounds.setBounds(getX(), getY(), getWidth(), getHeight());
		int imageNum = getImageNum(color, type);
		setImage(LinkHolder.IMAGES[imageNum].getScaledInstance(
				ControlPanel.PLATE_WIDTH, ControlPanel.PLATE_HEIGHT[type],
				Image.SCALE_SMOOTH));
	}

	public void reset() {
		setX(-100);
		setY(-100);
		position = 0;
		visible = false;
		bounds.setBounds(getX(), getY(), getWidth(), getHeight());
		setDx(ControlPanel.PLATE_DX);
		setDy(ControlPanel.PLATE_DY);
	}

	public void moveOnLine() {
		setX(getX() + getDx());
		bounds.setBounds(getX(), getY(), getWidth(), getHeight());
	}

	public void moveInSpace() {
		setY(getY() + getDy());
		bounds.setBounds(getX(), getY(), getWidth(), getHeight());
	}

	public void moveInStack(int movementDirection) {
		setX(getX() + movementDirection);
		bounds.setBounds(getX(), getY(), getWidth(), getHeight());
	}

	public int getNum() {
		return num;
	}

	private int getImageNum(int color, int type) {
		return (color * ControlPanel.PLATE_TYPES) + type;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getColor() {
		return color;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	@Override
	public void update(Observable object) {
		if (((Stack) object).getMovementState() == ControlPanel.PLAYER_MOVE_LEFT) {
			moveInStack(((Stack) object).getDx() * -1);
		} else if (((Stack) object).getMovementState() == ControlPanel.PLAYER_MOVE_RIGTH) {
			moveInStack(((Stack) object).getDx());
		} else {

		}
	}

}
