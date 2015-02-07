package utilities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import view.GamePanel;

public class ControlPanel {
	// panel constatnts
	public static Graphics2D g;
	public static final int PANEL_WIDTH = 500;
	// public static final int PANEL_WIDTH = 679;
	public static final int PANEL_HEIGHT = 349;

	// player size
	public static final int PLAYER_WIDTH = 60;
	public static final int PLAYER_HEIGHT = 60;

	// players' position
	public static final int PLAYER1_X_POSITION = 350;
	public static final int PLAYER2_X_POSITION = 50;
	public static final int PLAYER_Y_POSITION = PANEL_HEIGHT - PLAYER_HEIGHT;

	public static final int PLAYER_MOVE_STOP = 0;
	public static final int PLAYER_MOVE_LEFT = -1;
	public static final int PLAYER_MOVE_RIGTH = 1;

	// player's speed
	public static final int PLAYER_MAX_SPEED = 10;

	// stack's length
	public static final int STACK_WIDTH = 20;
	public static final int STACK_HEIGHT = 1;

	// plate's information
	public static final int PLATE_COLORS = 4;
	public static final int PLATE_TYPES = 2;

	public static final int PLATE_WIDTH = 20;
	public static final int[] PLATE_HEIGHT = { 5, 20 };
	public static int PLATE_HEIGHT_MAX;

	// plate's speed
	public static final int PLATE_DX = 1;
	public static final int PLATE_DY = 2;

	// distance between two consecutive plates
	public static final int PLATE_DISTANCE = 10;

	public static final int[] LINE_Y = { 30, 60, 90 };
	public static final int[] LINE_LENGTH = { 400, 350, 200 };
	// public static final int[] LINE_LENGTH = { 150, 350, 200 };
	public static final int LINE_HEIGHT = 10;
	public static final int LINE_DISTANCE = 50;

	public static final int SCOREBAR_WIDTH = 75;
	public static final int SCOREBAR_HEIGHT = PANEL_HEIGHT;

	public static final int SCOREBAR_X = PANEL_WIDTH - SCOREBAR_WIDTH;
	public static final int SCOREBAR_Y = 0;
	public static final Color SCOREBAR_COLOR = Color.BLACK;

	public static final int SCORE_X = SCOREBAR_X + 30;
	public static final int SCORE_Y_PLAYER[] = { 200, 270 };

	public static final int SCORE_WIDTH = 50;
	public static final int SCORE_HEIGHT = 50;

	public static final Color SCORE_FONT_COLOR = Color.RED;
	// public static final Color SCORE_RECTANGLE_COLOR = Color.WHITE;

	public static final Font SCORE_FONT = new Font("Serif", Font.BOLD, 20);

	// game timer
	public static final int TIMER = 30;
	public static final int TIMER_Y = 35;

	// max plates
	public static final int MAX_PLATES = 50;

	// game loop
	// public static boolean GAME_LOOP = true;

	// main menu
	public static boolean MAIN_MENU = true;

	// win menu
	public static final int WIN_MENU_X = 200;
	public static final int WIN_MENU_Y = 100;

	public static final int WIN_MENU_WIDTH = 300;
	public static final int WIN_MENU_HEIGHT = 200;

	public static final int WIN_MENU_PLAYER_X = 390;
	public static final int WIN_MENU_PLAYER_Y = 160;

	public static final int WIN_MENU_PLAYER_WIDTH = 75;
	public static final int WIN_MENU_PLAYER_HEIGHT = 30;

	public static final int WIN_MENU_SCORE_X = 365;

	public static final int WIN_MENU_SCORE1_Y = 226;
	public static final int WIN_MENU_SCORE2_Y = 270;
	// game animation
	public static final int GAME_ANIMATION_DX = 0;
	public static final int GAME_ANIMATION_DY = 10;

	// main menu animation
	public static final int MAIN_MENU_ANIMATION_DX = -1;
	public static final int MAIN_MENU_ANIMATION_DY = 0;

	public static final int MAIN_MENU_SELECT_DX = 210;
	public static final int MAIN_MENU_SELECT_DY[] = { 155, 200, 245 };

	public static final int MAIN_MENU_SELECT_WIDTH = 278;
	public static final int MAIN_MENU_SELECT_HEIGHT = 39;

	public static final String CLASS_LOADER_PASS = "E:/scr/";

	public ControlPanel() {
		// g = (Graphics2D) BallGame.getG();
		g = GamePanel.getG();
		getMaxPlateHeight();
	}

	private void getMaxPlateHeight() {
		int max = 0;
		for (int i = 0; i < PLATE_HEIGHT.length; i++) {
			max = Math.max(max, PLATE_HEIGHT[i]);
		}
		PLATE_HEIGHT_MAX = max;
	}
}
