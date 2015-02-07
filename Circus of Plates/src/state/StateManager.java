package state;

import java.util.ArrayList;

public class StateManager {

	private State gameState;
	private State mainMenuState;
	private State gamePausedState;
	private State gameOverState;

	private State currentState;

	public StateManager() {

		try {
//			 gameState = GameState.getInstance(this);
			// mainMenuState = MainMenuState.getInstance();
			// gamePausedState = GamePausedState.getInstance();
			// gameOverState = GameOverState.getInstance();
			gameState = new GameState(this);
			mainMenuState = new MainMenuState(this);
			gamePausedState = new GamePausedState(this);
			gameOverState = new GameOverState(this);

			currentState = mainMenuState;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void setCurrentState(State currentState) {
		this.currentState = currentState;
	}

	public State getMainMenu() {
		return mainMenuState;
	}

	public State getGameState() {
		((GameState) gameState).setEscPressed(false);
		return gameState;
	}

	public State getGameOverState() {
		return gameOverState;
	}

	public State getGamePausedState() {
		return gamePausedState;
	}

	public void draw() {
		currentState.draw();
	}

	public void update() {
		currentState.update();
	}

	public void keyPressed(ArrayList<Integer> keys) {
		currentState.keyPressed(keys);
	}

	// public static StateManager getInstance() {
	// if (instance == null) {
	// instance = new StateManager();
	// }
	// return instance;
	// }

}
