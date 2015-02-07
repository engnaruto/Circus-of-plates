package control;

import utilities.ControlPanel;
import model.Score;

public class ScoreController {
	private static ScoreController instance;

	private ScoreController() {

	}

	public static ScoreController getInstance() {
		if (instance == null) {
			instance = new ScoreController();
		}
		return instance;
	}

	public void update(Score score) {
		score.incrementScore();
	}

	public void draw(Score score) {
//		StaticHolder.g.setColor(StaticHolder.SCORE_RECTANGLE_COLOR);
//		StaticHolder.g.fill(score.getRectangle());

		ControlPanel.g.setFont(ControlPanel.SCORE_FONT);
		ControlPanel.g.setColor(ControlPanel.SCORE_FONT_COLOR);
		ControlPanel.g.drawString(score.getScore() + "", score.getFontX(),
				score.getFontY());
	}
}
