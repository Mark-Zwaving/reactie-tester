package model;
import java.awt.Color;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Klasse geeft willekeurige waarden voor een willekeurige figuur
 * of een lijst met figuren
 * @author   Mark zwaving
 * @see      Kleur
 * @version  1
 *
 */
public class RandomProperties 
{
	// Initiele waarden voor de bepaling van de marges en reikwijdten
	private int xPosMax, yPosMax, xPosMin, yPosMin, xyMarge;
	
	public RandomProperties ( PixelPoint xyLength )
	{
		xyMarge = 10;         // Marge moet zorgen dat alle figuren volledig op het scherm verschijnen
		xPosMin = xyMarge;    // Marge zorgt voor plaatsing met een kleine padding
		yPosMin = xyMarge;    // Marge houdt rekening met grootte figuur
		xPosMax = xyLength.x; // Max xPos
		yPosMax = xyLength.y; // Max yPos
	}
	
	/**
	 * Methode geeft een willekeurige xPositie binnen de marges van het scherm
	 * @return  integer
	 */
	public int getRandomXpos ( )
	{
		return ThreadLocalRandom.current().nextInt( xPosMin, xPosMax );
	}
	
	/**
	 * Methode geeft een willekeurige yPositie binnen de marges van het scherm
	 * @return  integer
	 */
	public int getRandomYpos ( )
	{
		return ThreadLocalRandom.current().nextInt( yPosMin, yPosMax );
	}
	
	/**
	 * Methode geeft een willekeurige figuur gekozen uit de vier gebruikte figuren
	 * @param   ArrayList<Figuur>
	 * @return  String
	 */
	public String getRandomFiguur ( ArrayList<String> rndFigurenLijst )
	{
		return rndFigurenLijst.get( ThreadLocalRandom.current().nextInt( rndFigurenLijst.size() ) );
	}
	
	/**
	 * Methode geeft een willekeurige kleur gekozen uit een geselecteerde lijst met kleuren
	 * @return  Color
	 */
	public Color getRandomKleur ( )
	{
		return new Kleur().getRndKleur();
	}
}
