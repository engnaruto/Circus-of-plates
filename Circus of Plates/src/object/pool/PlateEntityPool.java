package object.pool;

import java.util.Stack;

import utilities.ControlPanel;

import model.Line;
import model.PlateEntity;

import factory.PlateFactory;

public class PlateEntityPool {
	private Stack<PlateEntity> freePlates;
	private final int MAX_PLATES = ControlPanel.MAX_PLATES;
	private PlateFactory plateFactory;
	private static int plateCounter;
	private static PlateEntityPool instance;
	private int lastIndex;

	private PlateEntityPool() {
		lastIndex = 0;
		plateFactory = PlateFactory.getInstance();
		freePlates = new Stack<>();
		for (int i = 0; i < MAX_PLATES; i++) {
			freePlates.add(plateFactory.createBlankPlate());
		}
	}

	public static PlateEntityPool getInstance() {
		if (instance == null) {
			instance = new PlateEntityPool();
		}
		return instance;
	}

	public PlateEntity getPlate(Line line) {
		PlateEntity plate = null;
		if (freePlates.isEmpty()) {
			plate = plateFactory.createBlankPlate();
			plateFactory.resetPlate(line, plate);
			plateCounter++;
			// System.out.println("Create Plate");
		} else {
			plate = freePlates.pop();
			plateFactory.resetPlate(line, plate);
			// System.out.println("Reset Plate");
		}
		return plate;
	}

	public void free(PlateEntity plate) {
		// System.out.println("Free Plate");
		plateFactory.setPlateBlank(plate);
		if (freePlates.size() < MAX_PLATES) {
			freePlates.push(plate);
		}
	}

	public void reset() {
		freePlates.clear();
	}

	public void debug() {
		System.out.println("Current Pool Size: " + freePlates.size());
	}

	public void debug2() {
		System.out.println("Plates Created: " + plateCounter);
	}

	public int getLastIndex() {
		return lastIndex;
	}

	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}

}
