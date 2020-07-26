/**
 * 
 */
package view;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Klasse maakt een cirkel
 * @author Mark Zwaving
 * @see    Graphics
 */
public class Cirkel extends Figuur
{
	public Cirkel ( String naam, int xPos, int yPos, Color kleur )
	{
		super( naam, xPos, yPos, kleur );
	}
	
	/**
	 * Methode tekent cirkel naar het scherm
	 * @return  Graphics
	 */
	@Override
	public void tekenen ( Graphics graph )
	{
		graph.setColor( kleur );
		graph.fillOval(xPos, yPos, 80, 80);
	}
}
