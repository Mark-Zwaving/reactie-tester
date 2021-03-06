/**
 * 
 */
package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import model.PixelPoint;

/**
 * Klasse maakt een vierkant
 * @author Mark Zwaving
 * @see    Graphics
 *
 */
public class Vierkant extends Figuur
{	
	public Vierkant( String naam, int xPos, int yPos, Color kleur )
	{
		super( naam, xPos, yPos, kleur );
		xPoints = new int[4];
		yPoints = new int[4];
	    maxPoints = 4;
	}
	
	/**
	 * Methode tekent een vierkant naar het scherm
	 * @return  Graphics
	 */	
	@Override
	public void tekenen ( Graphics graph )
	{
	    graph.setColor( kleur );
	    p1 = new PixelPoint( xPos, yPos );
	    p2 = new PixelPoint( xPos, yPos + 75 );
	    p3 = new PixelPoint( xPos + 75, yPos + 75 );
	    p4 = new PixelPoint( xPos + 75, yPos );
	    xPoints = new int[] { p1.x, p2.x, p3.x, p4.x };
	    yPoints = new int[] { p1.y, p2.y, p3.y, p4.y };
	    polygon = new Polygon( xPoints, yPoints, maxPoints); 
	    graph.fillPolygon( polygon );
	}
}
