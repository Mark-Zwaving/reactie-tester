package model;

/**
 * Klasse voor het bijhouden updaten van de score
 * @author  Mark Zwaving
 * version  5
 */
public class Score 
{
	private String type;
	private boolean fout;
	private int totaal_score, aantal_goed, aantal_fout, snelste_tijd, nieuw_tijd,
	            snelste_cirkel, snelste_driehoek, snelste_vierkant, snelste_rechthoek,
	            max_demo, nieuw_score;
	
	public Score ( ) 
	{
		reset(); // Init score op 0;
	}
	
	/**
	 * Zet alle scores op de begin waarden 0, behalve snelste tijd
	 */
	public void reset ( ) 
	{
		max_demo          = Integer.MAX_VALUE;
		nieuw_tijd        = 0;
		nieuw_score       = 0;
		totaal_score      = 0; 
		aantal_goed       = 0;
		aantal_fout       = 0;
		snelste_tijd      = max_demo;
        snelste_cirkel    = max_demo;
        snelste_driehoek  = max_demo;
        snelste_vierkant  = max_demo;
        snelste_rechthoek = max_demo;
        fout              = true;
	}
	
	/**
	 * Berekent de totaal score
	 */
	public void updateScore ( )
	{
		// Bereken nieuwe score
		nieuw_score  = fout ? 0 : 2500 - nieuw_tijd; // Alleen punten bij goed
		nieuw_score  = nieuw_score < 0 ? 0 : nieuw_score; // Geen negatieve punten bij later dan 2500ms
		nieuw_score += fout ? -500 : 500; // +-500 punten voor een goed/fout antwoord
		
		// Verwerk nieuwe score in de totale score
		totaal_score += nieuw_score;
		
		// Update eventueel de snelste tijden per type en eventueel snelste tijd
		switch ( type ) 
		{
			case "cirkel":
				if ( nieuw_tijd < snelste_cirkel ) 
				{
					snelste_cirkel = nieuw_tijd;
					if ( snelste_cirkel < snelste_tijd ) 
						snelste_tijd = snelste_cirkel;
				}
				break;
			case "driehoek":
				if ( nieuw_tijd < snelste_driehoek ) 
				{
					snelste_driehoek = nieuw_tijd;
					if ( snelste_driehoek < snelste_tijd ) 
						snelste_tijd = snelste_driehoek;
				}
				break;
			case "vierkant":
				if ( nieuw_tijd < snelste_vierkant ) 
				{
					snelste_vierkant = nieuw_tijd;
					if ( snelste_vierkant < snelste_tijd ) 
						snelste_tijd = snelste_vierkant;
				}
				break;
			case "rechthoek":
				if ( nieuw_tijd < snelste_rechthoek ) 
				{
					snelste_rechthoek = nieuw_tijd;
					if ( snelste_rechthoek < snelste_tijd ) 
						snelste_tijd = snelste_rechthoek;
				}
				break;
		}
		// Een score lager dan nul, doen we niet aan
		totaal_score = totaal_score < 0 ? 0 : totaal_score;
	}
	
	/**
	 * Zet de type van het figuur. Nodig voor het checken van de tijden per figuur.
	 */
	public void setType (String type )
	{
		this.type = type;
	}
	
	/**
	 * Zet de nieuwe reactie tijd
	 */
	public void setNieuwTijd (int nieuw_tijd )
	{
		this.nieuw_tijd = nieuw_tijd;
	}

	/**
	 * Verhoogt het aantal goede antwoorden met 1
	 */
	public void verhoogAantalGoed() 
	{
		fout = false;
		aantal_goed += 1;
	}

	/**
	 * Verhoogt het aantal foute antwoorden met 1
	 */
	public void verhoogAantalFout() 
	{
		fout = true;
		aantal_fout += 1;
	}
	
	/**
	 * @return the aantal_goed
	 */
	public int getAantalGoed() 
	{
		return aantal_goed;
	}

	/**
	 * @return the aantal_fout
	 */
	public int getAantalFout() 
	{
		return aantal_fout;
	}

	/**
	 * @return the snelste_tijd
	 */
	public int getSnelsteTijd() 
	{
		return snelste_tijd == max_demo ? 0 : snelste_tijd;
	}

	/**
	 * @return the snelste_cirkel
	 */
	public int getSnelsteCirkel() 
	{
		return snelste_cirkel == max_demo ? 0 : snelste_cirkel;
	}
	
	/**
	 * @return the snelste_driehoek
	 */
	public int getSnelsteDriehoek() 
	{
		return snelste_driehoek == max_demo ? 0 : snelste_driehoek;
	}

	/**
	 * @return the snelste_vierkant
	 */
	public int getSnelsteVierkant() 
	{
		return snelste_vierkant == max_demo ? 0 : snelste_vierkant;
	}

	/**
	 * @return the snelste_rechthoek
	 */
	public int getSnelsteRechthoek() 
	{
		return snelste_rechthoek == max_demo ? 0 : snelste_rechthoek;
	}
	/**
	 * @return the totaal_score
	 */
	public int getTotaalScore() 
	{
		return totaal_score;
	}
}
