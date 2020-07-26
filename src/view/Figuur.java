package view;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import model.PixelPoint;

/**
 * Superklasse voor het instelen van basisgegevens van een figuur
 * @author Mark Zwaving
 * @see    Color, Graphics
 */
public class Figuur
{
	// Protected want moeten beschikbaar zijn in de overervende klassen
	protected int xPos, yPos;
	protected Color kleur;
	protected String naam;	
	protected Polygon polygon; 
	protected int[] xPoints, yPoints;
	protected int maxPoints;
	protected PixelPoint p1, p2, p3, p4;
	
	public Figuur ( String naam, int xPos, int yPos, Color kleur )
	{
		this.naam  = naam;
		this.xPos  = xPos;
		this.yPos  = yPos;
		this.kleur = kleur;
	}
	
	/**
	 * Methode geeft de naam/type van een figuur 
	 * @return    String
	 */
	public String getType (  )
	{
		return naam;
	}
	
	/**
	 * Methode bepaalt de x en y positie van een figuur mbv van een PixelPoint
	 * @param    PixelPoint
	 */
	public void setPixelPoint ( PixelPoint xy )
	{
		this.xPos = xy.x;
		this.yPos = xy.y;
	}
	
	/**
	 * Methode geeft de x en y positie van een figuur aan de hand van een PixelPoint
	 * @return PixelPoint
	 */
	public PixelPoint getPixelPoint ()
	{
		return new PixelPoint(this.xPos,this.yPos);
	}
	
	/**
	 * Methode bepaalt de kleur van een figuur
	 * @param    Color
	 */
	public void setKleur ( Color kleur )
	{
		this.kleur = kleur;
	}
	
	/**
	 * Methode geeft de kleur van een figuur
	 * @return   Color
	 */
	public Color getKleur ( )
	{
		return this.kleur;
	}
	
	public void tekenen ( Graphics graph ) { }
}
