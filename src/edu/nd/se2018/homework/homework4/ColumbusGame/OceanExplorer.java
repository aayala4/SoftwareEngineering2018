package edu.nd.se2018.homework.homework4.ColumbusGame;


import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * 
 * @author Alex Ayala
 * Homework 04
 * OceanExplorer does some setup work for the game including visuals
 * and actually runs the Columbus game
 *
 */

public class OceanExplorer extends Application
{
	Pane pane;
	Scene scene;
	OceanMap map;
	Image shipImage;
	Image pirateImage;
	ImageView[] pirateImageViews;
	ImageView shipImageView;
	Ship ship;
	PirateShip[] pirates;
	final int scale = 50;
	final int dimensions = 10;
	
	// Run the game
	public static void main(String[] args)
	{
		launch(args);
	}
	
	// Do Ship, map, and visual setup for the game and start the game
	@Override
	public void start(Stage oceanStage) throws Exception
	{
		map = new OceanMap(dimensions, this);
		ship = new Ship(map);
		oceanStage.setTitle("Columbus Sailed the Ocean Blue");
		pane = new AnchorPane();
		scene = new Scene(pane, scale * 10, scale * 10);
		map.generateIslandsAndPirates(ship.getShipLocation());
		map.drawMap(pane.getChildren(), scale, ship.getShipLocation());
		pirateImage = new Image("images/pirateship.gif", scale, scale, true, true);
		pirateImageViews = new ImageView[map.pirateShips.length];
		pirates = map.pirateShips;
		for(int i = 0; i < map.pirateShips.length; i++)
		{
			PirateShip p = pirates[i];
			ship.addObserver(p);
			pirateImageViews[i] = new ImageView(pirateImage);
			pirateImageViews[i].setX(p.getPirateShipLocation().x * scale);
			pirateImageViews[i].setY(p.getPirateShipLocation().y * scale);
			pane.getChildren().add(pirateImageViews[i]);
		}
		shipImage = new Image("images/ColumbusShip.png", scale, scale,true,true);
		shipImageView = new ImageView(shipImage);
		shipImageView.setX(ship.getShipLocation().x * scale);
		shipImageView.setY(ship.getShipLocation().y * scale);
		pane.getChildren().add(shipImageView);
		oceanStage.setScene(scene);
		oceanStage.show();
		startSailing();
	}
	
	// Listen for key presses and have the ship update its location accordingly
	private void startSailing()
	{
		scene.setOnKeyPressed(new EventHandler<KeyEvent>()
		{
			@Override
			public void handle(KeyEvent ke)
			{
				switch(ke.getCode())
				{
					case RIGHT:
						ship.goEast(dimensions);
						break;
					case LEFT:
						ship.goWest(dimensions);
						break;
					case UP:
						ship.goNorth(dimensions);
						break;
					case DOWN:
						ship.goSouth(dimensions);
						break;
					default:
						break;
				}
				shipImageView.setX(ship.getShipLocation().x*scale);
				shipImageView.setY(ship.getShipLocation().y*scale);
			}
		});
	}
	
	// Redraw the pirate ship image views
	public void redrawPirateShips()
	{
		for(int i = 0; i < pirateImageViews.length; i++)
		{
			pirateImageViews[i].setX(pirates[i].getPirateShipLocation().x*scale);
			pirateImageViews[i].setY(pirates[i].getPirateShipLocation().y*scale);
		}
	}
	
}
