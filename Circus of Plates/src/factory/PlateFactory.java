package factory;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import model.Line;
import model.PlateEntity;
import reflection.LoadClass;
import utilities.ControlPanel;

public class PlateFactory {
	private static PlateFactory instance;
	private static int PlateCounter = 0;
	private Random r;
	private Constructor<PlateEntity> constructor;
	File classPath = new File(ControlPanel.CLASS_LOADER_PASS);

	@SuppressWarnings("unchecked")
	private PlateFactory() {
		r = new Random();
		constructor = new LoadClass().getConstructor(classPath, "PlateEntity");
	}

	public static PlateFactory getInstance() {
		if (instance == null) {
			instance = new PlateFactory();
		}

		return instance;
	}

	public PlateEntity createPlate(Line line) {
		int plateColor = r.nextInt(100) % ControlPanel.PLATE_COLORS;
		int plateType = r.nextInt(100) % ControlPanel.PLATE_TYPES;

		int lastPosition = line.getLastPlate().getX()
				- ControlPanel.PLATE_WIDTH;

		int plateX = lastPosition - ControlPanel.PLATE_WIDTH
				- ControlPanel.PLATE_DISTANCE;

		int plateY = line.getY() - ControlPanel.PLATE_HEIGHT[plateType];

		// PlateEntity plate = createPlate(lastPosition, line.getNum());
		PlateEntity plate = null;
		try {
			plate = (PlateEntity) constructor.newInstance(plateX, plateY,
					plateColor, plateType, line.getNum(), PlateCounter++);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}

		// new PlateEntity(plateX, plateY, plateColor,
		// plateType, line.getNum(), PlateCounter++);

		line.setLastPlate(plate);

		return plate;
	}

	public void resetPlate(Line line, PlateEntity plate) {
		int plateColor = r.nextInt(100) % ControlPanel.PLATE_COLORS;
		int plateType = r.nextInt(100) % ControlPanel.PLATE_TYPES;
		plate.reset(line, plateColor, plateType);
	}

	public PlateEntity createBlankPlate() {
		return new PlateEntity(-100, -100, 0, 0, 0, PlateCounter++);
	}

	public void setPlateBlank(PlateEntity plate) {
		plate.reset();
	}
}
