package control;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import model.PlayerEntity;
import utilities.ControlPanel;

public class PlayerController {

	private static PlayerController instance;

	private StackController stackController;
	private ScoreController scoreController;

	private PlayerController() throws Exception {
		stackController = StackController.getInstance();
		scoreController = ScoreController.getInstance();
	}

	public static PlayerController getInstance() throws Exception {
		if (instance == null) {
			instance = new PlayerController();
		}
		return instance;
	}

	public void draw(PlayerEntity player) {
		ControlPanel.g.drawImage(player.getImage(), player.getX(),
				player.getY(), null);

		// if (player.getPlayerNum() == 1) {
		// StaticHolder.g.drawImage(LinkHolder.PLAYER1, player.getX(),
		// player.getY(), null);
		// } else {
		// StaticHolder.g.drawImage(LinkHolder.PLAYER2, player.getX(),
		// player.getY(), null);
		// }
		stackController.draw(player.getStack1());
		stackController.draw(player.getStack2());
		scoreController.draw(player.getScore());
	}

	public void updatePlayers(ArrayList<PlayerEntity> players,
			ArrayList<Integer> keys) {
		// if (StaticHolder.GAME_LOOP) {

		if (keys.contains(new Integer(KeyEvent.VK_LEFT))) {
			// boolean moved =
			players.get(0).moveLeft();
			// if (moved) {
			// // stackController.move(players.get(0),
			// // StaticHolder.PLAYER_MOVE_LEFT);
			// }
		}
		if (keys.contains(new Integer(KeyEvent.VK_RIGHT))) {
			// boolean moved =
			players.get(0).moveRigth();
			// if (moved) {
			// // stackController.move(players.get(0),
			// // StaticHolder.PLAYER_MOVE_RIGTH);
			// }
		}
		if (keys.contains(new Integer(KeyEvent.VK_A))) {
			// boolean moved =
			players.get(1).moveLeft();
			// if (moved) {
			// // stackController.move(players.get(1),
			// // StaticHolder.PLAYER_MOVE_LEFT);
			// }
		}
		if (keys.contains(new Integer(KeyEvent.VK_D))) {
			// boolean moved =
			players.get(1).moveRigth();
			// if (moved) {
			// // stackController.move(players.get(1),
			// // StaticHolder.PLAYER_MOVE_RIGTH);
			// }
		}

	}

	// }
}
