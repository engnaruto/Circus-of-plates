package factory;

import model.Line;
import utilities.ControlPanel;

public class LineFactory {
	private static LineFactory instance;
	private static int LineCounter = 0;

	private LineFactory() {

	}

	public static LineFactory getInstance() {
		if (instance == null) {
			instance = new LineFactory();
		}
		return instance;
	}

	public Line createLine(int lastPosition, int lineLength) {

		int LineY = lastPosition + ControlPanel.LINE_DISTANCE;

		return new Line(0, LineY, lineLength, ++LineCounter);
	}
}
