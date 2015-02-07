package control;

import model.Timer;
import utilities.ControlPanel;

public class TimerController {
	private static TimerController instance;

	private TimerController() {

	}

	public static TimerController getInstance() {
		if (instance == null) {
			instance = new TimerController();
		}
		return instance;
	}

	public boolean update(Timer timer, long time) {
		boolean ok = false;
		if (System.nanoTime() - timer.getLastTime() > time) {
			ok = true;
		}
		if (System.nanoTime() - timer.getLastTime() > Math.pow(10, 9)) {
			timer.decrementTime();
		}

		return ok;
	}

	public boolean checkTimeInterval(Timer timer, long time) {
		if (System.nanoTime() - timer.getLastTime() > time) {
			// timer.decrementTime();
			return true;
		}
		return false;
	}

	public void draw(Timer timer) {
//		StaticHolder.g.setColor(StaticHolder.SCORE_RECTANGLE_COLOR);
//		StaticHolder.g.fill(timer.getRectangle());

		ControlPanel.g.setFont(ControlPanel.SCORE_FONT);
		ControlPanel.g.setColor(ControlPanel.SCORE_FONT_COLOR);
		ControlPanel.g.drawString(timer.getTime() + "", timer.getFontX(),
				timer.getFontY());
	}
}
