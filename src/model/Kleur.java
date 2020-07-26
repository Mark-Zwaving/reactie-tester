package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 * Klasse maakt kleuren voor gebruik in de app
 * 
 * @author Mark zwaving
 * @see    Color, ArrayList, Random
 *   
 */
public class Kleur
{
	public Color crimson, rood, geel, groen, beige, wheat, tan, maroon, teal, salmon, 
	             blauw, dodgerblue, brown, chartreuse, olivedrab, brightgray, cornsilk,
	             ivory, honeydew, seegreen, deepskyblue, darkblue, darkmagenta, lavenderblush,
	             wit, zwart;
	
	private ArrayList<Color> kleurenLijst;
	
	/**
	 * Constructor maakt de kleuren en zet de
	 * kleuren in een lijst
	 */
	public Kleur ( )
	{
		crimson = new Color( 220,  20,  60, 255 );
		rood =  new Color( 255,   0,   0, 255 );
		geel = new Color( 255, 255,   0, 255 );
		groen =  new Color(   0, 255,   0, 255 );
		beige = new Color( 245, 245, 220, 255 );
		wheat = new Color( 245, 222, 179, 255 );
		tan = new Color( 210, 180, 140, 255 );
		maroon = new Color( 128,   0,   0, 255 );
		teal = new Color(  56, 142, 142, 255 );
		salmon = new Color( 198, 113, 113, 255 );
		blauw = new Color(   0,   0, 255, 255 );
		dodgerblue = new Color(  30, 144, 255, 255 );
		brown = new Color( 165,  42,  42, 255 );
		chartreuse = new Color( 113, 198, 113, 255 );
		olivedrab = new Color( 142, 142,  56, 255 );
		brightgray = new Color( 197, 193, 170, 255 );
		cornsilk = new Color( 255, 248, 220, 255 );
		ivory = new Color( 255, 255, 240, 255 );
		honeydew = new Color( 240, 255, 240, 255 );
		seegreen = new Color(  46, 139,  87, 255 );
		deepskyblue = new Color(   0, 191, 255, 255 );
		darkblue = new Color(   0,   0, 139, 255 );
		darkmagenta = new Color( 139,   0, 139, 255 );
		lavenderblush = new Color( 255, 240, 245, 255 );
		wit = new Color( 255, 255, 255, 255 );
		zwart = new Color(   0,   0,   0, 255 );
		
		kleurenLijst = new ArrayList<Color>();
		kleurenLijst.add(crimson);
		kleurenLijst.add(rood);
		kleurenLijst.add(geel);
		kleurenLijst.add(groen);
		kleurenLijst.add(wheat);
		kleurenLijst.add(tan);
		kleurenLijst.add(maroon);
		kleurenLijst.add(teal);
		kleurenLijst.add(salmon);
		kleurenLijst.add(blauw);
		kleurenLijst.add(dodgerblue);
		kleurenLijst.add(brown);
		kleurenLijst.add(chartreuse);
		kleurenLijst.add(olivedrab);
		kleurenLijst.add(brightgray);
		//kleurenLijst.add(cornsilk);
		//kleurenLijst.add(beige);
		//kleurenLijst.add(ivory);
		//kleurenLijst.add(honeydew);
		//kleurenLijst.add(wit);
//		kleurenLijst.add(zwart);
//		kleurenLijst.add(Color.pink);
		kleurenLijst.add(seegreen);
		kleurenLijst.add(deepskyblue);
		kleurenLijst.add(darkblue);
		kleurenLijst.add(darkmagenta);
		kleurenLijst.add(lavenderblush);
		kleurenLijst.add(Color.lightGray);
		kleurenLijst.add(Color.blue);
		kleurenLijst.add(Color.magenta);
		kleurenLijst.add(Color.cyan);
		kleurenLijst.add(Color.orange);
		kleurenLijst.add(Color.darkGray);
		kleurenLijst.add(Color.gray);
	}
	
	/**
	 * Methode geeft een willekeurige kleur uit een lijst met geselecteerde kleuren
	 * @return  Color
	 */
	public Color getRndKleur () 
	{
		Random rand = new Random(); 
		return kleurenLijst.get( rand.nextInt(kleurenLijst.size()) ); 
	}
}
