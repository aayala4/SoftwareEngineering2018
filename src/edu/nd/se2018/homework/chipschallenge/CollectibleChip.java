package edu.nd.se2018.homework.chipschallenge;

import java.awt.Point;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * 
 * @author Alex Ayala
 * CollectibleChip class. These chips are collected to unlock CollectibleChipBlocker tiles
 *
 */

public class CollectibleChip extends Collectible
{
	// Constructor
	CollectibleChip(int x, int y, int scale)
	{
		Image img;
		img = new Image("images/chip/textures/chipItem.png", scale, scale, true, true);
		imageView = new ImageView(img);
		imageView.setX(x*scale);
		imageView.setY(y*scale);
		location = new Point(x, y);
		acquired = false;
	}
	
	// Display that a chip is collected
	@Override
	public void showCollectedMessage()
	{
		System.out.println("Collected a chip");
	}

}
