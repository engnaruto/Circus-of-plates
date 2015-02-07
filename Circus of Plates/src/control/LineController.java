package control;

import java.awt.Color;

import model.Line;
import utilities.ControlPanel;

public class LineController {
	private static LineController instance;

	private LineController() {

	}

	public static LineController getInstance() {
		if (instance == null) {
			instance = new LineController();
		}
		return instance;
	}

	public void draw(Line line) {
		ControlPanel.g.drawImage(line.getImage(), line.getX(), line.getY(),
				line.getLength(), ControlPanel.LINE_HEIGHT, null);
		ControlPanel.g.setColor(Color.WHITE);
//		StaticHolder.g.draw(line.getBounds());
	}
}
