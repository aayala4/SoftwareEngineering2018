package edu.nd.se2018.homework.chipschallenge;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ChipsChallenge extends Application
{
	private Pane pane;
	private Scene scene;
	private final int dimensions = 25;
	private final int scale = 25;
	private Chip chip;
	private ChipsChallengeMap map;
	
	public static void main(String[] args)
	{
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception
	{
		map = new ChipsChallengeMap(dimensions, scale);
		stage.setTitle("Chip's Challenge");
		pane = new AnchorPane();
		scene = new Scene(pane, scale * 25, scale * 25);
		chip = new Chip(scale);
		map.drawMap(pane.getChildren());
		map.generateLevelOne(pane.getChildren(), chip);
		chip.addObserver(map);
		pane.getChildren().add(chip.getCurrentImageView());
		
		stage.setScene(scene);
		stage.show();
		startMoving();
		
	}
	
	private void startMoving()
	{
		scene.setOnKeyPressed(new EventHandler<KeyEvent>()
		{
			@Override
			public void handle(KeyEvent ke)
			{
				pane.getChildren().remove(chip.getCurrentImageView());
				switch(ke.getCode())
				{
					case RIGHT:
						chip.goRight(dimensions, map.getGrid());
						break;
					case LEFT:
						chip.goLeft(dimensions, map.getGrid());
						break;
					case UP:
						chip.goUp(dimensions, map.getGrid());
						break;
					case DOWN:
						chip.goDown(dimensions, map.getGrid());
						break;
					case ESCAPE:
						Platform.exit();
						break;
					default:
						break;
				}
				pane.getChildren().add(chip.getCurrentImageView());
			}
		});
	}

}


