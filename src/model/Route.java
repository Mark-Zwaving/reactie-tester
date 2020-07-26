package model;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Klasse start op basis van start waarden een richting NO, NW, ZW, ZO, N, Z, W en O
 * voor een figuur en berekent daarna de nieuwe coordinaten x,y. Zo wordt een route 
 * bepaald voor een figuur. De klasse houdt rekening met de randen in het spelpanel.
 * 
 * @author Mark Zwaving
 * @param  PixelPoint xyStart  (Willekeurige startwaarden)
 * @param  PixelPoint xyMin    (Minimum waarden randen panel)
 * @param  PixelPoint xyMax    (Maxmimum waarden randen panel)
 * @see    PixelPoint
 */
public class Route 
{
	private int iMin_xPos, iMin_yPos, 
	            iMax_xPos, iMax_yPos, 
	            iAct_xPos, iAct_yPos, 
	            iOffsetX,  iOffsetY;
	private String richting;
	
	public Route ( PixelPoint xyStart, PixelPoint xyMin, PixelPoint xyMax ) 
	{ 
		// Init waarden
	    iOffsetX = 1; 
	    iOffsetY = 1; 
		iAct_xPos = xyStart.x;
		iAct_yPos = xyStart.y;
	    iMin_xPos = xyMin.x;
	    iMin_yPos = xyMin.y;
	    iMax_xPos = xyMax.x;
	    iMax_yPos = xyMax.y;
		
	    // Bepaal een willekeurige start-richting
		switch ( ThreadLocalRandom.current().nextInt( 0, 8 ) ) 
		{
			case 0:
				richting = "ZO";
				break;
			case 1:
				richting = "NO";
				break;
			case 2:
				richting = "ZW";
				break;
			case 3:
				richting = "NW";
				break;	
			case 4:
				richting = "N";
				break;	
			case 5:
				richting = "Z";
				break;	
			case 6:
				richting = "W";
				break;	
			case 7:
				richting = "O";
				break;
		}
	}
	
	/**
	 * Methode voor het bepalen of een rand van een panel is bereikt. 
	 * Zo ja dan wordt een nieuwe richting van de rand af bepaalt.
	 * @return  PixelPoint
	 */
	private void checkRandenGame ( )
	{
		// Twee willekurige getallen voor het kiezen van een willekeurige richting
		int rnd1 = ThreadLocalRandom.current().nextInt(0,10); 
		int rnd2 = ThreadLocalRandom.current().nextInt(0,2);
		if ( iAct_xPos >= iMax_xPos )
		{
			if      ( richting == "ZO" ) richting = rnd1 < 8 ? "ZW" : rnd2 == 0 ?  "W" : "NW"; // 80% ZW 10% W of NW
			else if ( richting == "NO" ) richting = rnd1 < 8 ? "NW" : rnd2 == 0 ?  "W" : "ZW";
			else if ( richting == "O"  ) richting = rnd1 < 5 ?  "W" : rnd2 == 0 ? "ZW" : "NW"; // 50% W 25% ZW of NW
		}
		else if ( iAct_xPos <= iMin_xPos )
		{
			if      ( richting == "NW" ) richting = rnd1 < 8 ? "NO" : rnd2 == 0 ?  "O" : "ZO";
			else if ( richting == "ZW" ) richting = rnd1 < 8 ? "ZO" : rnd2 == 0 ?  "O" : "ZO";
			else if ( richting ==  "W" ) richting = rnd1 < 5 ?  "O" : rnd2 == 0 ? "ZO" : "NO";
		}
		else if ( iAct_yPos >= iMax_yPos ) /* OK */
		{
			if      ( richting == "ZO" ) richting = rnd1 < 8 ? "NO" : rnd2 == 0 ?  "N" : "NW";
			else if ( richting == "ZW" ) richting = rnd1 < 8 ? "NW" : rnd2 == 0 ?  "N" : "NO";
			else if ( richting ==  "Z" ) richting = rnd1 < 5 ?  "N" : rnd2 == 0 ? "NW" : "NO";
		}
		else if ( iAct_yPos <= iMin_yPos ) /* OK */
		{
			if      ( richting == "NO" ) richting = rnd1 < 8 ? "ZO" : rnd2 == 0 ? "ZW" :  "Z";
			else if ( richting == "NW" ) richting = rnd1 < 8 ? "ZW" : rnd2 == 0 ? "ZO" :  "Z";
			else if ( richting ==  "N" ) richting = rnd1 < 5 ?  "Z" : rnd2 == 0 ? "ZW" : "ZO";
		}
	}
	
	/**
	 * Methode geeft de volgende pixelpoint, betekent een x-positie en een y-positie, 
	 * in de route voor een figuur
	 * @return  PixelPoint
	 */
	public PixelPoint getNext ( )
	{
		checkRandenGame(); // Randen bereikt dan veranderen van richting 
		
		if ( richting == "NW" )
		{
			iAct_xPos -= iOffsetX;
			iAct_yPos -= iOffsetY;
		}
		else if ( richting == "ZW" )
		{
			iAct_xPos -= iOffsetX;
			iAct_yPos += iOffsetY;
		}
		else if ( richting == "NO" )
		{
			iAct_xPos += iOffsetX;
			iAct_yPos -= iOffsetY;
		}
		else if ( richting == "ZO" )
		{
			iAct_xPos += iOffsetX;
			iAct_yPos += iOffsetY;
		}
		else if ( richting == "N" )
		{
			iAct_xPos += 0;
			iAct_yPos -= iOffsetY;
		}
		else if ( richting == "Z" )
		{
			iAct_xPos += 0;
			iAct_yPos += iOffsetY;
		}
		else if ( richting == "W" )
		{
			iAct_xPos -= iOffsetX;
			iAct_yPos -= 0;
		}
		else if ( richting == "O" )
		{
			iAct_xPos += iOffsetX;
			iAct_yPos -= 0;
		}
		// Nieuwe x en y positie in een PixelPoint
		return new PixelPoint(iAct_xPos, iAct_yPos); 
	}
}
