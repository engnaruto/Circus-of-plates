package state;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

import model.Game;
import model.GameOverMenu;
import model.Line;
import model.PlateEntity;
import model.PlayerEntity;
import model.Stack;
import object.pool.PlateEntityPool;
import utilities.LinkHolder;
import utilities.ControlPanel;
import control.BackgroundController;
import control.LineController;
import control.PlateController;
import control.PlayerController;
import control.StackController;
import control.TimerController;

public class GameState implements State {
	private PlayerController playerController;
	private PlateController plateController;
	private LineController lineController;
	private static StackController stackController;
	private TimerController timerController;
	private BackgroundController backgroundController;
	// private Serialization serialization;

	// private static GameState instance;
	private StateManager stateManager;

	private static PlateEntityPool plateEntityPool;
	private static Iterator<PlateEntity> plateIterator;
	private static Game game;
	private GameOverMenu gameOverMenu;
	private boolean escPressed;

	public GameState(StateManager stateManager) throws Exception {
		new LinkHolder();
		new ControlPanel();
		game = Game.getInstance();
		plateController = PlateController.getInstance();
		playerController = PlayerController.getInstance();
		lineController = LineController.getInstance();
		stackController = StackController.getInstance();
		timerController = TimerController.getInstance();
		plateEntityPool = PlateEntityPool.getInstance();
		// serialization = Serialization.getInstance();
		// gameOverController = GameOverState.getInstance();
		backgroundController = BackgroundController.getInstance();

		plateIterator = Game.getInstance();

		this.stateManager = stateManager;

		// StaticHolder.GAME_LOOP = true;

		gameOverMenu = GameOverMenu.getInstane();

		escPressed = false;
	}

	// public GameState() {
	// }

	// public static GameState getInstance(StateManager stateManager)
	// throws Exception {
	// if (instance == null) {
	// instance = new GameState(stateManager);
	// }
	// return instance;
	// }

	// public void save() {
	// // serialization.save(game);
	// }

	public void setEscPressed(boolean escPressed) {
		this.escPressed = escPressed;
	}

	@Override
	public void update() {

		// if (StaticHolder.GAME_LOOP && !escPressed) {
		if (!escPressed) {
			int time = 1000000000 - 100000;
			if (timerController.update(game.getTimer(), time)) {
				addPlatesToLines();
			}

			if (game.getTimer().getTime() < 0) {
				gameOverMenu.setScore1(game.getPlayers().get(0).getScore()
						.getScore()
						+ "");
				gameOverMenu.setScore2(game.getPlayers().get(1).getScore()
						.getScore()
						+ "");
				stateManager.setCurrentState(stateManager.getGameOverState());
			}
			detectCollision();
			backgroundController.update(game.getBackground());
		}

		// plateEntityPool.debug();
		try {
			while (plateIterator.hasNext()) {
				PlateEntity plate = plateIterator.next();
				boolean isOnScreen = plateController.update(plate);
				if (!isOnScreen || !plate.isVisible()) {
					plateEntityPool.free(plate);
					plateIterator.remove();
				}
			}

		} catch (Exception e) {
			System.out.println("game controller exception!!!");
		}

	}

	public static void reset() {
		System.out.println("RESET");

		game.reset();
		for (int i = 0; i < game.getPlayers().size(); i++) {
			PlayerEntity player = game.getPlayers().get(i);

			for (int j = 0; j < player.getStacks().size(); j++) {
				Stack stack = player.getStacks().get(j);
				// stack.clear();
				while (stack.size() != 0) {
					stackController.removeLastPlate(player, stack);
				}
			}
			while (plateIterator.hasNext()) {
				System.out.println("Reset free");
				PlateEntity plate = plateIterator.next();
				plateEntityPool.free(plate);
				plateIterator.remove();
			}

			System.out.println(game.countPlates());
		}
		//
		// try {
		// // game = null;
		// // game = Game.getInstance();
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// if (game == null) {
		// System.out.println("null");
		// try {
		// game = Game.getInstance();
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// }
		// StaticHolder.GAME_LOOP = true;
	}

	private void addPlatesToLines() {
		System.out.println("ADD TO LINES");
		for (int i = 0; i < game.countLines(); i++) {
			Line line = game.getLine(i);
			// line.debug();
			if (line.getLastPlate().getX() > line.getStartPoint()) {
				PlateEntity plate = plateEntityPool.getPlate(line);
				// plateEntityPool.debug2();
				// plateEntityPool.debug();
				// System.out.println();
				game.addPlate(plate);
			}
		}
	}

	private void detectCollision() {
		detectCollisionPlateLines();
		detectCollisionPlateStacks();
		// draw();
	}

	private void detectCollisionPlateLines() {
		for (int i = 0; i < game.countLines(); i++) {
			Line line = game.getLine(i);
			for (int j = 0; j < game.countPlates(); j++) {
				PlateEntity plate = game.getPlateNormal(j);
				if (plate.getPosition() == line.getNum()) {
					if (!line.getBounds().intersects(plate.getBounds())) {
						// System.out.println(line.getBounds().intersects(plate.getBounds()));
						// System.out.println(plate.getNum() + "\t" +
						// plate.getPosition());
						plate.setPosition(0);
					}
				}
			}
		}
	}

	private void detectCollisionPlateStacks() {
		for (int i = 0; i < game.countPlates(); i++) {
			PlateEntity plate = game.getPlateNormal(i);
			if (plate.getPosition() == 0 && plate.isVisible()) {
				for (int j = 0; j < game.getPlayers().size(); j++) {
					PlayerEntity player = game.getPlayers().get(j);
					for (int k = 0; k < player.getStacks().size(); k++) {
						Stack stack = player.getStacks().get(k);
						if (stack.getBounds().intersects(plate.getBounds())) {
							stackController.update(player, stack, plate);
						}
					}
				}
			}
		}
	}

	@Override
	public void draw() {
		// if (StaticHolder.GAME_LOOP) {
		// StaticHolder.g.drawImage(game.getBackground(), 0, 0, null);
		backgroundController.draw(game.getBackground());
		ControlPanel.g.setColor(ControlPanel.SCOREBAR_COLOR);
		ControlPanel.g.drawImage(game.getScorebar(), ControlPanel.SCOREBAR_X,
				ControlPanel.SCOREBAR_Y, null);
		// StaticHolder.g.fill(game.getScorebar());
		timerController.draw(game.getTimer());
		for (int i = 0; i < game.countLines(); i++) {
			lineController.draw(game.getLine(i));
		}
		for (int i = 0; i < game.getPlayers().size(); i++) {
			playerController.draw(game.getPlayers().get(i));
		}
		for (int i = 0; i < game.countPlates(); i++) {
			// if (game.getPlateNormal(i).isVisible()) {
			plateController.draw(game.getPlateNormal(i));
			// }
		}
		// }
		// else {
		// winMenuController.draw(game.getWinMenu());
		// }
	}

	@Override
	public void keyPressed(ArrayList<Integer> keys) {
		if (keys.contains(new Integer(KeyEvent.VK_ESCAPE))) {
			escPressed = true;
			stateManager.setCurrentState(stateManager.getGamePausedState());
		} else {
			playerController.updatePlayers(game.getPlayers(), keys);
		}
	}

	@Override
	public void mainMenu() {
		stateManager.setCurrentState(stateManager.getMainMenu());
	}

	@Override
	public void gamePaused() {
		stateManager.setCurrentState(stateManager.getGamePausedState());

	}

	@Override
	public void gameOver() {
		stateManager.setCurrentState(stateManager.getGameOverState());
	}

	@Override
	public void gameState() {
		System.out.println("GameState >>> GameState");
	}
}