package edu.nd.se2018.homework.chipschallenge;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * 
 * @author Alex Ayala
 * ChipsChallenge class. This class runs the actual game and sets up key press management
 *
 */

public class ChipsChallenge extends Application
{
	private Pane pane;
	private Scene scene;
	private final int dimensions = 25;
	private final int scale = 25;
	private Chip chip;
	
	public static void main(String[] args)
	{
		launch(args);
	}
	
	// Initial setup for the game
	@Override
	public void start(Stage stage) throws Exception
	{
		pane = new AnchorPane();
		ChipsChallengeMap map = ChipsChallengeMap.getInstance(dimensions, scale, pane.getChildren());
		stage.setTitle("Chip's Challenge");
		scene = new Scene(pane, scale * 25, scale * 25);
		chip = new Chip(scale);
		map.loadLevel(chip);
		chip.addObserver(map);		
		stage.setScene(scene);
		stage.show();
		startMoving();
	}
	
	// Set the scene up to manage arrow key presses to move Chip
	private void startMoving()
	{
		scene.setOnKeyPressed(new EventHandler<KeyEvent>()
		{
			@Override
			public void handle(KeyEvent ke)
			{
				ChipsChallengeMap map = ChipsChallengeMap.getInstance();
				switch(ke.getCode())
				{
					// Move Chip if arrow keys are pressed
					case RIGHT:
						chip.goRight(dimensions, map.getGrid(), pane.getChildren());
						break;
					case LEFT:
						chip.goLeft(dimensions, map.getGrid(), pane.getChildren());
						break;
					case UP:
						chip.goUp(dimensions, map.getGrid(), pane.getChildren());
						break;
					case DOWN:
						chip.goDown(dimensions, map.getGrid(), pane.getChildren());
						break;
					// Quit the game if ESC is pressed
					case ESCAPE:
						Platform.exit();
						break;
					default:
						break;
				}
			}
		});
	}

}


