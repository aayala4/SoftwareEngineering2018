package edu.nd.se2018.homework.chipschallenge;

import java.awt.Point;
import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * 
 * @author Alex Ayala
 * CollectibleChipBlocker class. This represents the tile that blocks unless you have all of the chips
 *
 */

public class CollectibleChipBlocker extends Unlockable
{
	private int chipsNeeded = -1;
	
	// Constructor
	CollectibleChipBlocker(int x, int y, int scale)
	{
		Image img;
		img = new Image("images/chip/textures/chipBlocker.png", scale, scale, true, true);
		imageView = new ImageView(img);
		imageView.setX(x*scale);
		imageView.setY(y*scale);
		location = new Point(x, y);
		opened = false;
	}
	
	// Set the number of chips needed to unlock this blocker
	public void setChipsNeeded(int c)
	{
		chipsNeeded = c;
	}
	
	// Unlocking is successful if the list passed has exactly the number of collectible chips needed
	@Override
	public boolean tryUnlocking(ArrayList<Collectible> cs)
	{
		int total = chipsNeeded;
		for(Collectible c: cs)
		{
			if(c instanceof CollectibleChip)
			{
				total -= 1;
			}
		}
		if(total == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

}
