package model;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import view.Cirkel;
import view.Driehoek;
import view.Figuur;
import view.Rechthoek;
import view.Vierkant;

/**
 * Klasse voor het maken van een pool met aantal figuren
 * @author  Mark Zwaving
 * @param   integer (aantal figuren in pool)
 * @version 2
 */
public class Pool 
{ 
	private int aantalKeus, aantalFiguren, xPosDefault, yPosDefault;
	private Kleur rndKleur;
	private ArrayList<Figuur> lijstFiguren;
	
	public Pool ( int aantal )
	{
		lijstFiguren  = new ArrayList<Figuur>(); // Lijst voor de figuren
		rndKleur = new Kleur(); // Nieuw kleuren object
		xPosDefault = -200; // Plaats figuur standaard buiten het panel
		yPosDefault = -200; // Plaats figuur standaard buiten het panel
		aantalFiguren = aantal;
		aantalKeus = 4; // Totaal aantal figuren
		nieuwLijst(); // Maak een nieuwe willekeurige lijst met figuren
	}
	
	/**
	 * Methode maakt een arraylist met een aantal willekeurig gekozen figuren
	 * met willekeuri gekozen posites en kleur
	 */
	public void nieuwLijst()
	{
		int max = aantalFiguren; // Totaal aantal figuren
		while ( --max >= 0 )
		{
			// Bepaal een willekeurige figuur
			switch ( ThreadLocalRandom.current().nextInt(0, aantalKeus) )
			{
				case 0: 
					lijstFiguren.add( 
						new Cirkel( "cirkel", xPosDefault, yPosDefault, rndKleur.getRndKleur() ) 
						);
					break;
				case 1: 
					lijstFiguren.add( 
						new Driehoek( "driehoek", xPosDefault, yPosDefault, rndKleur.getRndKleur() ) 
						);
					break;
				case 2: 
					lijstFiguren.add( 
						new Vierkant( "vierkant", xPosDefault, yPosDefault, rndKleur.getRndKleur() ) 
						);
					break;
				case 3: 
					lijstFiguren.add( 
						new Rechthoek( "rechthoek", xPosDefault, yPosDefault, rndKleur.getRndKleur() ) 
						);
					break;
				default:
					++max;
					break;
			}
		}
	}

	/**
	 * Methode geeft een arrayList met een aantal willekeurig gekozen figuren
	 * @return  ArrayList<Figuur>
	 */
	public ArrayList<Figuur> getLijst()
	{
		return lijstFiguren;
	}
}
