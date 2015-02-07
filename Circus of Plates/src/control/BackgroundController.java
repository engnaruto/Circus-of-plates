package control;

import model.Background;
import utilities.ControlPanel;

public class BackgroundController {
	private static BackgroundController instance;

	private BackgroundController() {

	}

	public static BackgroundController getInstance() {
		if (instance == null) {
			instance = new BackgroundController();
		}
		return instance;
	}

	public void update(Background background) {
		if (background.getAnimation() != null) {
			background.incrementX();
			background.incrementY();
		}
	}

	public void draw(Background background) {
		if (background.getBackground() != null) {
			ControlPanel.g.drawImage(background.getBackground(), 0, 0, null);
		}

		ControlPanel.g.drawImage(background.getAnimation(), background.getX(),
				background.getY(), null);

		if (background.getAnimation() != null) {
			if (background.getX() < 0) {
				ControlPanel.g.drawImage(background.getAnimation(),
						(int) background.getX() + ControlPanel.PANEL_WIDTH,
						(int) background.getY(), null);
			}
			if (background.getX() > 0) {
				ControlPanel.g.drawImage(background.getAnimation(),
						(int) background.getX() - ControlPanel.PANEL_WIDTH,
						(int) background.getY(), null);
			}
			if (background.getY() < 0) {
				ControlPanel.g.drawImage(background.getAnimation(),
						(int) background.getX(), (int) background.getY()
								+ ControlPanel.PANEL_HEIGHT, null);
			}
			if (background.getY() > 0) {
				ControlPanel.g.drawImage(background.getAnimation(),
						(int) background.getX(), (int) background.getY()
								- ControlPanel.PANEL_HEIGHT, null);
			}
		}
	}

}
