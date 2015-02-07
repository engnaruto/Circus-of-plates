package model;

import java.io.Serializable;
import java.util.ArrayList;

public class SavedState implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<PlayerEntity> players;
	private Timer timer;

	public SavedState(ArrayList<PlayerEntity> players, Timer timer) {
		this.players = players;
		this.timer = timer;
	}

	public ArrayList<PlayerEntity> getPlayers() {
		return players;
	}

	public Timer getTimer() {
		return timer;
	}

	public  void setSavedState(SavedState savedState) {
		this.players = savedState.getPlayers();
		this.timer = savedState.getTimer();
	}

}
