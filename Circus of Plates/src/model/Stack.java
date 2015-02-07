package model;

import java.awt.Rectangle;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import observer.Observable;
import observer.Observer;

import utilities.ControlPanel;

public class Stack implements Observer, Observable, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int x, y, dx, num, movementState;
	private ArrayList<Integer> stack;
	private Rectangle bounds;

	public Stack(int x, int y, int dx, int num) {
		this.x = x;
		this.y = y;
		this.dx = dx;

		this.num = num;

		stack = new ArrayList<>();

		bounds = new Rectangle(x, y - ControlPanel.STACK_HEIGHT,
				ControlPanel.STACK_WIDTH, ControlPanel.STACK_HEIGHT);

		movementState = ControlPanel.PLAYER_MOVE_STOP;
	}

	public int size() {
		return stack.size();
	}

	public void moveLeft(int playerSpeed) {
		x -= playerSpeed;
		bounds.setBounds(x, y - ControlPanel.STACK_HEIGHT,
				ControlPanel.STACK_WIDTH, ControlPanel.STACK_HEIGHT);
		movementState = ControlPanel.PLAYER_MOVE_LEFT;
	}

	public void moveRight(int playerSpeed) {
		x += playerSpeed;
		bounds.setBounds(x, y - ControlPanel.STACK_HEIGHT,
				ControlPanel.STACK_WIDTH, ControlPanel.STACK_HEIGHT);
		movementState = ControlPanel.PLAYER_MOVE_RIGTH;
	}

	public void moveStop() {
		// x += playerSpeed;
		// bounds.setBounds(x, y - StaticHolder.STACK_HEIGHT,
		// StaticHolder.STACK_WIDTH, StaticHolder.STACK_HEIGHT);
		movementState = ControlPanel.PLAYER_MOVE_STOP;
	}

	public void incrementBoundreis(PlateEntity plate) {
		bounds.setBounds(x, y - plate.getHeight() - ControlPanel.STACK_HEIGHT,
				ControlPanel.STACK_WIDTH, ControlPanel.STACK_HEIGHT);
		// x = plate.getX();
		y -= plate.getHeight();
	}

	public void decrementBoundreis(PlateEntity lastPlate) {
		bounds.setBounds(x, y + lastPlate.getHeight()
				- ControlPanel.STACK_HEIGHT, ControlPanel.STACK_WIDTH,
				ControlPanel.STACK_HEIGHT);
		// x = lastPlate.getX();
		y += lastPlate.getHeight();
	}

	public int getPlateNum(int index) {
		return stack.get(index);
	}

	public void reset(PlayerEntity player) {
		if (num % 2 == -1) {
			x = player.getX();
		} else {
			x = player.getX() + player.getWidth() - ControlPanel.STACK_WIDTH;
		}
		y = player.getY();
		bounds.setBounds(x, y - ControlPanel.STACK_HEIGHT,
				ControlPanel.STACK_WIDTH, ControlPanel.STACK_HEIGHT);
	}

	public void addPlate(int plateNum) {
		stack.add(plateNum);
	}

	public void removeLastPlate() {
		stack.remove(stack.size() - 1);
	}

	public int Size() {
		return stack.size();
	}

	public ArrayList<Integer> getStack() {
		return stack;
	}

	public Rectangle getBounds() {
		return bounds;
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

	public int getNum() {
		return num;
	}

	@Override
	public void update(Observable object) {
		if (((PlayerEntity) object).getMovementState() == ControlPanel.PLAYER_MOVE_LEFT) {
			moveLeft(((PlayerEntity) object).getDx());
		} else if (((PlayerEntity) object).getMovementState() == ControlPanel.PLAYER_MOVE_RIGTH) {
			moveRight(((PlayerEntity) object).getDx());
		} else {
			moveStop();
		}
		notifyObservers();
	}

	@Override
	public void notifyObservers() {
		try {
			Iterator<PlateEntity> iterator = Game.getInstance();
			while (iterator.hasNext()) {
				PlateEntity plate = iterator.next();
				if (plate.getPosition() == num) {
					plate.update(this);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addObserver(Observer observer) {
		stack.add(((PlateEntity) observer).getNum());

	}

	@Override
	public void removeObserver(Observer observer) {
		stack.remove(((PlateEntity) observer).getNum());

	}

	public int getDx() {
		return dx;
	}

	public int getMovementState() {
		return movementState;
	}

	public void clear() {
		stack.clear();
	}

}
