package control;

import java.awt.Color;

import model.PlateEntity;
import model.Stack;
import utilities.ControlPanel;

public class PlateController {

	private static PlateController instance;

	private PlateController() {
	}

	public static PlateController getInstance() {
		if (instance == null) {
			instance = new PlateController();
		}
		return instance;
	}

	public boolean update(PlateEntity plate) {
		if (plate.getPosition() == 0) {
			if (plate.getY() > ControlPanel.PANEL_HEIGHT) {
				return false;
			}
			plate.moveInSpace();
		} else if (plate.getPosition() > 0) {
			plate.moveOnLine();
		}
		return true;
	}

	public void putAtStack(Stack stack, PlateEntity plate) {
		plate.setPosition(stack.getNum());
		plate.setX(stack.getX());
		plate.setY(stack.getY() - plate.getHeight());
		plate.getBounds().setBounds(plate.getX(), plate.getY(),
				plate.getWidth(), plate.getHeight());
	}

//	public void moveInStack(PlateEntity plate, int movementDirection) {
//		plate.moveInStack(movementDirection);
//	}

	public void draw(PlateEntity plate) {
		if (plate.isVisible()
				&& plate.getY() + plate.getHeight() < ControlPanel.PANEL_HEIGHT) {
			ControlPanel.g.drawImage(plate.getImage(), plate.getX(),
					plate.getY(), null);
			// System.out.println(plate.getNum() + "\t" + plate.getPosition());
			ControlPanel.g.setColor(Color.WHITE);
			ControlPanel.g.draw(plate.getBounds());
		}
		if (!plate.isVisible()) {
			System.out.println("not visible");
			ControlPanel.g.setColor(Color.RED);
			ControlPanel.g.draw(plate.getBounds());
			ControlPanel.g.setColor(Color.WHITE);
		}

	}

}
