package model;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Iterator;

import utilities.LinkHolder;
import utilities.ControlPanel;
import factory.LineFactory;

public class Game implements Iterator<PlateEntity> {
	// private Image background;
	// private Rectangle Scorebar;

	private Image Scorebar;
	private Background background;

	private ArrayList<PlateEntity> plateEntities;
	private ArrayList<Line> lines;
	private ArrayList<PlayerEntity> players;
	private LineFactory lineFactory;
	private Timer timer;
	private int iterator;
	private SavedState savedState;
	private static Game instance;

	private Game() throws Exception {
		System.out.println("INSTANE");
		Image backgroundImage = LinkHolder.IMAGES[LinkHolder.IMAGES.length - 3]
				.getScaledInstance(ControlPanel.PANEL_WIDTH,
						ControlPanel.PANEL_HEIGHT, Image.SCALE_SMOOTH);

		background = new Background(ControlPanel.GAME_ANIMATION_DX,
				ControlPanel.GAME_ANIMATION_DY, null, backgroundImage);
		Scorebar = LinkHolder.SCORE_BOARD.getScaledInstance(
				ControlPanel.SCOREBAR_WIDTH, ControlPanel.SCOREBAR_HEIGHT,
				Image.SCALE_SMOOTH);
		// Scorebar = new Rectangle(StaticHolder.SCOREBAR_X,
		// StaticHolder.SCOREBAR_Y, StaticHolder.SCOREBAR_WIDTH,
		// StaticHolder.SCOREBAR_HEIGHT);

		timer = new Timer(ControlPanel.TIMER);

		// winMenu = new GameOver();

		plateEntities = new ArrayList<>();
		lines = new ArrayList<>();
		players = new ArrayList<>();

		players.add(new PlayerEntity(ControlPanel.PLAYER1_X_POSITION,
				ControlPanel.PLAYER_Y_POSITION,
				LinkHolder.picturePaths[LinkHolder.IMAGES.length - 1], 1));
		players.add(new PlayerEntity(ControlPanel.PLAYER2_X_POSITION,
				ControlPanel.PLAYER_Y_POSITION,
				LinkHolder.picturePaths[LinkHolder.IMAGES.length - 2], 2));
		// players.add(new PlayerEntity(StaticHolder.PLAYER1_X_POSITION,
		// StaticHolder.PLAYER_Y_POSITION, 1));
		// players.add(new PlayerEntity(StaticHolder.PLAYER2_X_POSITION,
		// StaticHolder.PLAYER_Y_POSITION, 2));

		lineFactory = LineFactory.getInstance();

		for (int i = 0; i < ControlPanel.LINE_LENGTH.length; i++) {
			lines.add(lineFactory.createLine(getLastLine(),
					ControlPanel.LINE_LENGTH[i]));
		}

		iterator = 0;

	}

	public static Game getInstance() throws Exception {
		if (instance == null) {
			instance = new Game();
		}
		return instance;
	}

	public void reset() {

		players.clear();
		try {
			// players.add(new PlayerEntity(StaticHolder.PLAYER1_X_POSITION,
			// StaticHolder.PLAYER_Y_POSITION,
			// LinkHolder.IMAGES[LinkHolder.IMAGES.length - 1], 1));
			// players.add(new PlayerEntity(StaticHolder.PLAYER2_X_POSITION,
			// StaticHolder.PLAYER_Y_POSITION,
			// LinkHolder.IMAGES[LinkHolder.IMAGES.length - 2], 2));
			players.add(new PlayerEntity(ControlPanel.PLAYER1_X_POSITION,
					ControlPanel.PLAYER_Y_POSITION,
					LinkHolder.picturePaths[LinkHolder.IMAGES.length - 1], 1));
			players.add(new PlayerEntity(ControlPanel.PLAYER2_X_POSITION,
					ControlPanel.PLAYER_Y_POSITION,
					LinkHolder.picturePaths[LinkHolder.IMAGES.length - 2], 2));
			timer = new Timer(ControlPanel.TIMER);

		} catch (Exception e) {
			e.printStackTrace();
		}
		// return instance = null;
		// try {
		// instance = new Game();
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
	}

	public void addPlate(PlateEntity plate) {
		plateEntities.add(plate);
	}

	public void removePlateStack(int index) {
		for (int i = 0; i < plateEntities.size(); i++) {
			if (plateEntities.get(i).getNum() == index) {
				plateEntities.remove(i);
			}
		}
	}

	public void removePlateNormal(int index) {
		plateEntities.remove(index);
	}

	private int getLastLine() {
		try {
			Line line = lines.get(lines.size() - 1);
			return line.getY();
		} catch (Exception e) {
			return -20;
		}
	}

	public ArrayList<PlayerEntity> getPlayers() {
		return players;
	}

	public Background getBackground() {
		return background;
	}

	public int countPlates() {
		return plateEntities.size();
	}

	public PlateEntity getPlateStack(int plateNum) {
		for (int i = 0; i < plateEntities.size(); i++) {
			if (plateEntities.get(i).getNum() == plateNum) {
				return plateEntities.get(i);
			}
		}
		System.out.println("Error getPlate!!!");
		return plateEntities.get(plateNum);
	}

	public int countLines() {
		return lines.size();
	}

	public Line getLine(int index) {
		return lines.get(index);
	}

	public Image getScorebar() {
		return Scorebar;
	}

	// public Rectangle getScorebar() {
	// return Scorebar;
	// }

	public Timer getTimer() {
		return timer;
	}

	public PlateEntity getPlateNormal(int index) {
		return plateEntities.get(index);
	}

	// @Override
	// public PlateEntity getNextPlate() {
	//
	// }

	@Override
	public PlateEntity next() {
		// System.out.println(iterator);
		PlateEntity plate = plateEntities.get(iterator++);
		if (plate != null) {
			return plate;
		}
		// System.out.println("iterator exception!!!");
		return null;
	}

	@Override
	public boolean hasNext() {
		if (iterator < plateEntities.size()) {
			return true;
		}
		// reset();
		iterator = 0;
		return false;
	}

	@Override
	public void remove() {
		plateEntities.remove(iterator - 1);
	}

	public void loadPlayerPic() {
		for (int i = 0; i < players.size(); i++) {
			players.get(i).loadImages();
		}
	}

	public void load(SavedState savedState) {
		players = savedState.getPlayers();
		timer.setTime(savedState.getTimer().getTime());
		for (int i = 0; i < players.size(); i++) {
			players.get(i).loadImages();
		}
	}

	public SavedState save() {
		return getSavedState();
	}

	public SavedState getSavedState() {
		for (int i = 0; i < players.size(); i++) {
			players.get(i).nullifyImages();
		}

		savedState = new SavedState(players, timer);
		return savedState;
	}

}
