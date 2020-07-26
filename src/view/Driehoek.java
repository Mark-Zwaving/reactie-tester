/**
 * 
 */
package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import model.PixelPoint;

/**
 * Klasse maakt een driehoek
 * @author Mark Zwaving
 * @see    Graphics
 */
public class Driehoek extends Figuur
{	
	public Driehoek ( String naam, int xPos, int yPos, Color kleur )
	{
		super( naam, xPos, yPos, kleur );
	    maxPoints = 3;
		xPoints = new int[maxPoints];
		yPoints = new int[maxPoints];
	}
	
	/**
	 * Methode tekent driehoek naar het scherm
	 */
	@Override
	public void tekenen ( Graphics graph )
	{
	    graph.setColor( kleur );
	    p1 = new PixelPoint( xPos, yPos );
	    p2 = new PixelPoint( xPos + 80, yPos + 80 );
	    p3 = new PixelPoint( xPos, yPos + 80 );
	    xPoints = new int[] { p1.x, p2.x, p3.x };
	    yPoints = new int[] { p1.y, p2.y, p3.y };
	    polygon = new Polygon( xPoints, yPoints, maxPoints); 
	    graph.fillPolygon( polygon );
	}
}
