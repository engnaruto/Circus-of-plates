package utilities;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public class LinkHolder {
	public static String[] picturePaths = { "/pics/blueplate.png",
			"/pics/bluesquare.png", "/pics/greenplate.png",
			"/pics/greensquare.png", "/pics/redplate.png",
			"/pics/redsquare.png", "/pics/yellowplate.png",
			"/pics/yellowsquare.png", "/pics/player1.png", "/pics/player2.png",
			"/pics/draw.png", "/pics/black.png", "/pics/sparks.png",
			"/pics/mainmenu.png", "/pics/gameover.png", "/pics/gamepaused.png",
			"/pics/scoreboard.png", "/pics/line.png", "/pics/background.jpg",
			"/pics/clown1.png", "/pics/clown2.png" };
	public static Image IMAGES[];
	public static Image SCORE_BOARD;
	public static Image GAME_PAUSED;
	public static Image GAME_OVER;
	public static Image MAIN_MENU;
	public static Image MAIN_MENU_BACKGROUND;
	public static Image MAIN_MENU_ANIMATION;
	public static Image GAME_OVER_PLAYER1;
	public static Image GAME_OVER_PLAYER2;
	public static Image GAME_OVER_DRAW;
	public static Image PLAYER1;
	public static Image PLAYER2;

	public LinkHolder() throws IOException {
		IMAGES = new Image[picturePaths.length];
		for (int i = 0; i < IMAGES.length; i++) {
			// System.out.println(picturePaths[i]);
			IMAGES[i] = ImageIO.read(getClass().getResourceAsStream(
					picturePaths[i]));
		}
		// System.out.println("Done!!!");
		PLAYER1 = IMAGES[IMAGES.length - 1];
		PLAYER2 = IMAGES[IMAGES.length - 2];
		SCORE_BOARD = IMAGES[IMAGES.length - 5];
		GAME_PAUSED = IMAGES[IMAGES.length - 6];
		GAME_OVER = IMAGES[IMAGES.length - 7];
		MAIN_MENU = IMAGES[IMAGES.length - 8];
		MAIN_MENU_ANIMATION = IMAGES[IMAGES.length - 9];
		MAIN_MENU_BACKGROUND = IMAGES[IMAGES.length - 10];
		GAME_OVER_PLAYER1 = IMAGES[IMAGES.length - 13];
		GAME_OVER_PLAYER2 = IMAGES[IMAGES.length - 12];
		GAME_OVER_DRAW = IMAGES[IMAGES.length - 11];

	}
}
