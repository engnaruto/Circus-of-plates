package model;

import java.awt.Image;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;

import observer.Observable;
import observer.Observer;

import utilities.ControlPanel;

public class PlayerEntity extends Entity implements Observable,
		Iterator<Stack>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Stack> stacks;
	private Score score;
	private int iterator;
	private int movementState;

	private String imagePath;

	public PlayerEntity(int x, int y, String imagePath, int stackNum)
			throws Exception {
		setWidth(ControlPanel.PLAYER_WIDTH);
		setHeight(ControlPanel.PANEL_HEIGHT);
		this.imagePath = imagePath;
		setImage(ImageIO.read(getClass().getResourceAsStream(imagePath))
				.getScaledInstance(ControlPanel.PLAYER_WIDTH,
						ControlPanel.PLAYER_HEIGHT, Image.SCALE_SMOOTH));
		setX(x);
		setY(y);
		setDx(ControlPanel.PLAYER_MAX_SPEED);
		if (stackNum == 0) {
			throw new Exception("0 is not allowed in stackNum");
		}
		stacks = new ArrayList<>();
		stacks.add(new Stack(getX(), getY(), getDx(), stackNum * -2 + 1));
		stacks.add(new Stack(getX() + getWidth() - ControlPanel.STACK_WIDTH,
				getY(), getDx(), stackNum * -2));
		score = new Score(stackNum);
		iterator = 0;
		movementState = 0;
	}

	public void nullifyImages() {
		setImage(null);
	}

	public void loadImages() {
		try {
			setImage(ImageIO.read(getClass().getResourceAsStream(imagePath))
					.getScaledInstance(ControlPanel.PLAYER_WIDTH,
							ControlPanel.PLAYER_HEIGHT, Image.SCALE_SMOOTH));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void notifyObservers() {
		Iterator<Stack> iterator = this;
		while (iterator.hasNext()) {
			Stack stack = iterator.next();
			stack.update(this);
		}
	}

	@Override
	public void addObserver(Observer stack) {
		stacks.add((Stack) stack);
	}

	@Override
	public void removeObserver(Observer stack) {
		stacks.remove((Stack) stack);

	}

	@Override
	public boolean hasNext() {
		if (iterator < stacks.size()) {
			return true;
		}
		// reset();
		iterator = 0;
		return false;
	}

	@Override
	public Stack next() {
		// System.out.println(iterator);
		Stack stack = stacks.get(iterator++);
		if (stack != null) {
			return stack;
		}
		// System.out.println("iterator exception!!!");
		return null;
	}

	@Override
	public void remove() {
		stacks.remove(iterator - 1);
	}

	public void moveRigth() {
		if (getX() + getDx() <= ControlPanel.PANEL_WIDTH
				- ControlPanel.PLAYER_WIDTH - ControlPanel.SCOREBAR_WIDTH) {
			setX(getX() + getDx());
			// stacks.get(0).moveRight(getDx());
			// stacks.get(1).moveRight(getDx());
			// System.out.println("Right");
			movementState = ControlPanel.PLAYER_MOVE_RIGTH;
			// notifyObservers();
			// return true;
		} else {
			movementState = ControlPanel.PLAYER_MOVE_STOP;
		}
		notifyObservers();
		// return false;
	}

	public void moveLeft() {
		if (getX() - getDx() >= 0) {
			setX(getX() - getDx());
			// stacks.get(0).moveLeft(getDx());
			// stacks.get(1).moveLeft(getDx());
			// System.out.println("Left");
			movementState = ControlPanel.PLAYER_MOVE_LEFT;
			// notifyObservers();
			// return true;
		} else {
			movementState = ControlPanel.PLAYER_MOVE_STOP;
		}
		notifyObservers();
		// return false;
	}

	public Stack getStack1() {
		return stacks.get(0);
	}

	public Stack getStack2() {
		return stacks.get(1);
	}

	public ArrayList<Stack> getStacks() {
		return stacks;
	}

	public Score getScore() {
		return score;
	}

	public void incrementScore() {
		score.incrementScore();
	}

	public int getMovementState() {
		return movementState;
	}

}
