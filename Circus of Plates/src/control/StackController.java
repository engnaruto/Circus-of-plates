package control;

import java.awt.Color;

import model.Game;
import model.PlateEntity;
import model.PlayerEntity;
import model.Stack;
import utilities.ControlPanel;

public class StackController {
	private PlateController plateController;
	// private PlateEntityPool plateEntityPool;
	private Game game;

	private static StackController instance;

	private StackController() throws Exception {
		game = Game.getInstance();
		plateController = PlateController.getInstance();
		// plateEntityPool = PlateEntityPool.getInstance();
	}

	public static StackController getInstance() throws Exception {
		if (instance == null) {
			instance = new StackController();
		}
		return instance;
	}

	// public void move(PlayerEntity player, int movementDirection) {
	// for (int i = 0; i < game.countPlates(); i++) {
	// PlateEntity plate = game.getPlateNormal(i);
	// int position = plate.getPosition();
	// if (position < 0 && plate.isVisible()) {
	// // System.out.println(plate.getNum() + "\t" + position);
	// for (int j = 0; j < player.getStacks().size(); j++) {
	// Stack stack = player.getStacks().get(j);
	// if (position == stack.getNum()) {
	// if (movementDirection == StaticHolder.PLAYER_MOVE_LEFT) {
	// plateController.moveInStack(plate, player.getDx()
	// * -1);
	// } else {
	// plateController.moveInStack(plate, player.getDx());
	// }
	// }
	// }
	// }
	// }
	//
	// }

	public void update(PlayerEntity player, Stack stack, PlateEntity plate) {
		plateController.putAtStack(stack, plate);
		stack.incrementBoundreis(plate);
		addPlateToStack(stack, plate);
		removeSamePlates(player, stack);
		// System.out.println(stack.Size());
	}

	public void addPlateToStack(Stack stack, PlateEntity plate) {
		stack.addPlate(plate.getNum());
		// System.out.println("plate added at Stack " + stack.getNum() * -1);
	}

	public void removeSamePlates(PlayerEntity player, Stack stack) {
		try {
			// System.out.println(stack.toString());
			// System.out.println("remove same plates");
			if (stack.getStack().size() >= 3) {

				// PlateEntity plate1 =
				// game.getPlateStack(stack.getStack().get(stack.size() - 1));
				// PlateEntity plate2 =
				// game.getPlateStack(stack.getStack().get(stack.size() - 2));
				// PlateEntity plate3 =
				// game.getPlateStack(stack.getStack().get(stack.size() - 3));
				PlateEntity plate1 = game.getPlateStack(stack.getPlateNum(stack
						.size() - 1));
				PlateEntity plate2 = game.getPlateStack(stack.getPlateNum(stack
						.size() - 2));
				PlateEntity plate3 = game.getPlateStack(stack.getPlateNum(stack
						.size() - 3));

				if (plate1.getColor() == plate2.getColor()
						&& plate1.getColor() == plate3.getColor()) {
					for (int i = 0; i < 3; i++) {
						removeLastPlate(player, stack);
					}
					player.incrementScore();
				}
				// System.out.println("Score++");
			}
		} catch (Exception e) {
			System.out.println("error at stack!!!");
		}
	}

	public void removeLastPlate(PlayerEntity player, Stack stack) {
		try {

			int plateNum = stack.getStack().get(stack.size() - 1);

			PlateEntity plate = game.getPlateStack(plateNum);

			stack.decrementBoundreis(plate);

			// stack.getStack().remove(stack.getStackSize() - 1);
			stack.removeLastPlate();

			if (stack.Size() == 0) {
				stack.reset(player);
			}

			plate.setVisible(false);
			plate.setPosition(0);
			// System.out.println(lastPlate.getPosition());
			// plateEntityPool.free(lastPlate);
			// lastPlate = null;
			// System.out.println("plate removed from Stack " + stack.getNum()
			// * -1);
			// lastPlate = game.getPlate(lastPlateNum);
			// System.out.println(plate.getNum() + "\t" + plate.getPosition());

		} catch (Exception e) {
			e.setStackTrace(null);
			// stack.reset(player);
			System.out.println("stack reset exception!!!");
		}

	}

	public void draw(Stack stack) {
		ControlPanel.g.setColor(Color.WHITE);
		ControlPanel.g.draw(stack.getBounds());
	}

}
