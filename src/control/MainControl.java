package control;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.Timer;
import model.PixelPoint;
import model.RandomProperties;
import model.Route;
import model.Score;
import view.Figuur;
import view.MainView;

/**
 * Hoofd control. Klasse voor het verwerken van alle control opties in de app
 * @author  Mark Zwaving
 * @param   MainView
 * @version 4
 */
public class MainControl
{
	private MainView view;
	private Score score;
	private Route route;
	private boolean gameon, demoStart, rondeStart;
	private int iRndFiguur, aantalKeer, aantalMax, tijdRun, iStartDelayMin, iStartDelayMax, 
	            iEindDelayMin, iEindDelayMax, iRefreshDemo, iRefreshGame;
	private long tijdStart, tijdStop;
	private Timer gameTimer, demoTimer;
	private Figuur figuur;
	private ArrayList<Route> lijstRoutes;

	public MainControl( MainView view )
	{
		// Initialiseer startwaarden
		this.view   = view;
		score       = new Score();
		demoStart   = false; 
		gameon      = false;
		rondeStart  = false;
		iRndFiguur  =  0;
		aantalKeer  =  1; // Start game nummer
		aantalMax   = 10; // Tien keer spelen
		iStartDelayMin =  750; 
		iStartDelayMax = 1500;
		iEindDelayMin  = 1500; 
		iEindDelayMax  = 6500;
		iRefreshGame   = 0;
		iRefreshDemo   = 4;
		// Lijst met alle routes voor de figuren uit de pool
		lijstRoutes = new ArrayList<Route> (); 
		verbergFiguren(); // Alle figuren buiten het scherm, default waarden
		gameTimer = new Timer( iRefreshGame, new GameHandler() ); // Refreshrate voor de figuren in de game
		demoTimer = new Timer( iRefreshDemo, new DemoHandler() ); // Refreshrate voor de figuren in de demo
		demoTimer.setInitialDelay(500); // Start dely voor het opstarten van de demo
		demoTimer.start(); // Start de demo tijdens het opstarten van het programma
		// Listeners voor de verwerking van de kliks op de rechthoek, vierkant, cirkel en 
		// driehoek in de game
		ActionListener checkHandler = new CheckHandler();
		view.cirkelBtn.addActionListener(checkHandler);
        view.driehoekBtn.addActionListener(checkHandler);
        view.vierkantBtn.addActionListener(checkHandler);
        view.rechthoekBtn.addActionListener(checkHandler);

        // Voeg toe listener aan start button om de game te starten
        // Zorgt voor de voorbereidingen van een nieuwe game
		this.view.startBtn.addActionListener( new ActionListener () 
	    {
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				
				verbergFiguren ( ); // Verberg alle figuren
				if ( demoStart )
				{
					demoStart = false;
					demoTimer.stop();
				}
				if ( !gameon )
				{
					gameon = true;
					aantalKeer = 1;
					score.reset();
					schrijfScore();
					view.gameTitel.setText( "GAME ON" );
					view.gameTitel.setForeground( view.kleur.seegreen );
					setButtons(false,true,false,false,false,false,false);
					// Naar nieuw figuur
					volgendeFiguur ( );
				}
			}
	    } );

        // Voeg toe listener aan stop button om de game netjes te stoppen
		this.view.stopBtn.addActionListener( new ActionListener () 
	    {
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
		        gameTimer.stop();
				gameon = false;
				view.gameTitel.setText( "GAME OFF" );
				view.gameTitel.setForeground( view.kleur.crimson );
				setButtons(true,false,false,false,false,false,true);
			}
	    } );

        // Voeg toe listener aan reset button om de game en alle (score)
		// waarden te resetten. Maar ook om de demo scherm op te starten.
		this.view.resetBtn.addActionListener( new ActionListener () 
	    {
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				score.reset();
		        view.scoreResult.setText(view.sEmpthy);
		        view.foutenResult.setText(view.sEmpthy);
		        view.goedResult.setText(view.sEmpthy);
		        view.vierkantSecsResult.setText(view.sEmpthy);
		        view.driehoekSecsResult.setText(view.sEmpthy);
		        view.rechthoekSecsResult.setText(view.sEmpthy);
		        view.cirkelSecsResult.setText(view.sEmpthy);
		        view.topTijdSecsResult.setText(view.sEmpthy);
		        setButtons(true,false,false,false,false,false,false); 
				demoTimer.setInitialDelay(0);
		        demoTimer.start();
		        gameTimer.stop();
			}
	    } );
	}
	
	/**
	 * Methode geeft een willekeurige key uit een lijst met figuren
	 * @return  integer
	 */
	private int rndNumUitLijst ( )
	{
		return ThreadLocalRandom.current().nextInt( 0, view.lijstFiguren.size() );
	}
	
	/**
	 * Methode geeft een pixelpoint met de juiste afmetingen voor de verschillende figuren. 
	 * Dit gebeurt op basis van de afmetingen van de gamepanel en de figuur zelf;
	 * @param   String (naam van figuur)
	 * @return  integer
	 */
	private PixelPoint getMaxWidthHeight ( String naam )
	{
		PixelPoint result = null;
		// Game panel breedte en hoogte
		int xPosMax = view.gamePanel.getWidth(), 
			yPosMax = view.gamePanel.getHeight();
		// Bepaal op basis van het type figuur 
		// De juiste afmetingen voor de breedte en hoogte
		switch ( naam )
		{
			case "vierkant":
				result = new PixelPoint(xPosMax - 75, yPosMax - 75);
				break;
			case "rechthoek":
				result = new PixelPoint(xPosMax - 100, yPosMax - 60);
				break;
			case "driehoek":
				result = new PixelPoint(xPosMax - 80, yPosMax - 80);
				break;
			case "cirkel":
				result = new PixelPoint(xPosMax - 80, yPosMax - 80);
				break;
		}
		return result;
	}
	
	/**
	 * Methode schudt alle posities van alle figuren uit de lijst. 
	 * Wordt gebruikt voor het opstarten van een nieuwe demo scherm.
	 */
	private void schudPosFiguren( )
	{
		// Schud posities figuren op van basis van panel breedte en hoogte
		for ( Figuur fig : view.lijstFiguren )
		{
			PixelPoint xyMax = getMaxWidthHeight( fig.getType() );
			RandomProperties rnd = new RandomProperties( new PixelPoint( xyMax.x, xyMax.y ) );
			fig.setPixelPoint( new PixelPoint(rnd.getRandomXpos(), rnd.getRandomYpos()) );
		}
	}
	
	/**
	 * Methode schrijft de score naar de view. Uiteraard op basis van de score 
	 * op dat moment behaald uit de score klasse.
	 */
	private void schrijfScore()
	{
		view.scoreResult.setText(Integer.toString(score.getTotaalScore()));
		view.foutenResult.setText(Integer.toString(score.getAantalFout()));
		view.goedResult.setText(Integer.toString(score.getAantalGoed()));
		view.vierkantSecsResult.setText( Integer.toString(score.getSnelsteVierkant()) + "ms");
		view.driehoekSecsResult.setText(Integer.toString(score.getSnelsteDriehoek()) + "ms");
		view.rechthoekSecsResult.setText(Integer.toString(score.getSnelsteRechthoek()) + "ms");
		view.cirkelSecsResult.setText(Integer.toString(score.getSnelsteCirkel()) + "ms");
		view.topTijdSecsResult.setText(Integer.toString(score.getSnelsteTijd()) + "ms");
		
		String out = Integer.toString(aantalKeer) + " van " + Integer.toString(aantalMax);
		view.gameTellerResult.setText(out);
	}
	
	/**
	 * Methode zet alle figuren buiten het scherm. 
	 * Wordt gebruikt bij het starten van een nieuw spel.
	 * Zodat geen enkele figuur al in het scherm staat.
	 */
	private void verbergFiguren ( )
	{
		for ( Figuur f : view.lijstFiguren )
		{
			f.setPixelPoint( new PixelPoint(-200,-200) );
		}
		view.repaint();	
	}
	
	/**
	 * Methode geeft een willekeurig delay (in aantal milliseconden) 
	 * De delay startwaarden liggen tussen de 500 en 1500 milliseconden
	 * De delay eindwaarden liggen tussen de 1500 en 500 milliseconden
	 * Deze waarden zijn uiteraard aanpasbaar.
	 */
	private int willekeurigDelay ()
	{
		int wachttijdStart = ThreadLocalRandom.current().nextInt( iStartDelayMin, iStartDelayMax );
		int wachttijdEinde = ThreadLocalRandom.current().nextInt( iEindDelayMin, iEindDelayMax );
		return ThreadLocalRandom.current().nextInt( wachttijdStart, wachttijdEinde );
	}
	
	/**
	 * Methode voor het enabelen en disabelen van de buttons in de game
	 */
	private void setButtons( Boolean start, Boolean stop, Boolean cirkel, Boolean driehoek,
			                 Boolean vierkant, Boolean rechthoek, Boolean demo )
	{
		if ( start     != null ) view.startBtn.setEnabled(start);
		if ( stop      != null ) view.stopBtn.setEnabled(stop);
		if ( cirkel    != null ) view.cirkelBtn.setEnabled(cirkel);
		if ( driehoek  != null ) view.driehoekBtn.setEnabled(driehoek);
		if ( vierkant  != null ) view.vierkantBtn.setEnabled(vierkant);
		if ( rechthoek != null ) view.rechthoekBtn.setEnabled(rechthoek);
		if ( demo      != null ) view.resetBtn.setEnabled(demo);
	}
	
	/**
	 * Methode selecteert willekeurig een volgende figuur. 
	 * De geselecteerde figuur krijgt een willekurige x en y positie in de de game panel.
	 * Daarbij wordt een nieuwe route voor de figuur bepaalt op basis van de willekeurig
	 * gegenereerd x en y positie van een figuur.
	 * Is figuur gemaakt start timer voor de game en het meten van de reactie snelheid 
	 * voor de game.
	 */
	private void volgendeFiguur ( )
	{
		// Kies een willekeurig figuur uit de lijst
		iRndFiguur = rndNumUitLijst();
		figuur = view.lijstFiguren.get(iRndFiguur);
		// Maak random coördinaten
		PixelPoint xyMax = getMaxWidthHeight( figuur.getType() );
		RandomProperties rnd = new RandomProperties( new PixelPoint( xyMax.x, xyMax.y ) );
		// Bepaal een route met een willekeurige startpositie
		PixelPoint xyMin   = new PixelPoint( 0, 0 );
		PixelPoint xyStart = new PixelPoint( rnd.getRandomXpos(), rnd.getRandomYpos() );
		route = new Route( xyStart, xyMin, xyMax );	
		// Start beweging figuur / Start game
		int iWillekeurigDelay = willekeurigDelay();
        gameTimer.stop(); // Altijd stoppen, voor de zekerheid
		gameTimer.setInitialDelay(iWillekeurigDelay);
		gameTimer.start();
		rondeStart = true;
	}
	
	/**
	 * (Interne) Klasse is een listener voor de refresh rate in de game.
	 * Klasse schrijft elke keer als het wordt aangeroepen.
	 * de actuele figuur op een nieuwe positie in het scherm.
	 */
	private class GameHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			if ( rondeStart )
			{
				rondeStart = false;
				setButtons(null,null,true,true, true,true,null);
				view.cirkelBtn.setEnabled(true);
				view.driehoekBtn.setEnabled(true);
				view.vierkantBtn.setEnabled(true);
				view.rechthoekBtn.setEnabled(true);
				tijdStart  = new Date().getTime();
			}
			PixelPoint xyNext = route.getNext();
			figuur.setPixelPoint(xyNext);
			view.repaint();	// Schrijf scherm opnieuw
		}
	};

	/**
	 * (Interne) Klasse is een listener voor als de demotimer wordt gestart. Dit gebeurt als 
	 * op de demo/reset button wordt geklikt. Bij de eerste keer worden alle figuren geschud en 
	 * worden nieuwe routes gemaakt. Daarna volgende alle figuren in de lijst gewoon hun eigen 
	 * route.
	 */
	private class DemoHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			if ( !demoStart )
			{
				demoStart = true;
				view.gameTitel.setText("GAME OFF");
				view.gameTellerResult.setText("DEMO");
				view.gameTitel.setForeground(view.kleur.crimson);
				setButtons(true,false,false,false,false,false,false);
				schudPosFiguren();
				
				for ( int x = 0; x <  view.lijstFiguren.size(); x++ )
				{
					figuur = view.lijstFiguren.get(x);
					PixelPoint xyStart = figuur.getPixelPoint();
					PixelPoint xyMin = new PixelPoint(0, 0 );
					PixelPoint xyMax = getMaxWidthHeight(figuur.getType());
					lijstRoutes.add( new Route( xyStart, xyMin, xyMax ) );
				}
			}
			else 
			{
				for ( int ndx = 0; ndx <  view.lijstFiguren.size(); ndx++ )
				{
					PixelPoint xyNext = lijstRoutes.get(ndx).getNext();
					view.lijstFiguren.get(ndx).setPixelPoint(xyNext);
				}
			}
			view.repaint();	
		}
	};
	
	/**
	 * (Interne) Klasse is een listener voor de kliks van de speler in de game.
	 * Klasse checkt of een juiste figuur is aangeklikt en update op basis daarvan 
	 * de score en schrijft we score naar het scherm.
	 */
	private class CheckHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			gameTimer.stop(); // Stop tevens deze game
			setButtons(null,null,false,false,false,false,null);
			tijdStop = new Date().getTime(); // Stop de tijd
			tijdRun  = (int) (tijdStop - tijdStart); // Bereken de nieuwe tijd

			// Check of de reactie goed of fout is geweest
			boolean goed = false;
			if      ( e.getSource() == view.cirkelBtn    && figuur.getType() == "cirkel"    ) goed = true;
			else if ( e.getSource() == view.driehoekBtn  && figuur.getType() == "driehoek"  ) goed = true;
			else if ( e.getSource() == view.vierkantBtn  && figuur.getType() == "vierkant"  ) goed = true;
			else if ( e.getSource() == view.rechthoekBtn && figuur.getType() == "rechthoek" ) goed = true; 
			// Is de score goed, verhoog dan het aantal goed in de score zoniet verhoog het aantal fout.
			if ( goed )
				score.verhoogAantalGoed();
			else
				score.verhoogAantalFout();
			// Zet de type figuur in de score. Hiermee kan een score op basis van een specifieke figuur 
			// worden berekend.
			score.setType(figuur.getType());
			score.setNieuwTijd( tijdRun ); // Zet de score/reactie tijd
			score.updateScore(); // Bereken de nieuwe score
			
			// Schrijf de nieuwe score naar het scherm
			schrijfScore(); 
			// Verhoog aantal spellen en nog verder spelen of is alle al gespeeld
			if ( aantalKeer++ < aantalMax )
				volgendeFiguur ( ); // Nog niet uitgespeeld. Naar een volgende figuur
			else
				view.stopBtn.doClick(); // Stop spel en voer methode voor de stop button uit.
		}
	}
}
