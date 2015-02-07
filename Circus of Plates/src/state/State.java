package state;

import java.util.ArrayList;

public interface State {

	public void mainMenu();

	public void gamePaused();

	public void gameOver();

	public void gameState();

	public void update();

	public void draw();

	public void keyPressed(ArrayList<Integer> keys);

}
