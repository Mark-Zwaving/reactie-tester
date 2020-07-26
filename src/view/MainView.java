package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import model.Kleur;
import model.Pool;

/**
 * Klasse voor het creëren van de hoofdview
 * @author  Mark Zwaving
 * @see     JPanel
 * @version 3
 */
public class MainView extends JPanel
{
	// Prive label eenmalige initialisatie, hoeven niet opdatebaar te zijn tijdens spelen game.
	private JLabel  hoofdTitel, scoreTitel, foutenTitel, goedTitel, topTijdSecsTitel, vierkantSecsTitel, 
	                driehoekSecsTitel, rechthoekSecsTitel, cirkelSecsTitel, gameTellerTitel, spacerOost,
	                spacerWest; 
	// Publieke labels voor de score en deze moeten publiek beschikbaar zijn om te kunnen updaten
	public  JLabel  gameTitel, scoreResult, foutenResult, goedResult, vierkantSecsResult, driehoekSecsResult, 
	                rechthoekSecsResult, cirkelSecsResult, topTijdSecsResult, gameTellerResult;
	// Alle panels in het programma zuid, west, noord, oost en de center (game) panel
	public  JPanel  southPanel, westPanel, oostPanel, noordPanel, gamePanel;
	// Button voor de uitvoering van de game
	public  JButton startBtn, stopBtn, resetBtn, cirkelBtn, driehoekBtn, vierkantBtn, rechthoekBtn;
	private String  sMainFont, sSpacer; // Hoofd font Calibri, en een spacer voor de breedte van de zijpanels
	public  String  sEmpthy; // Lege string voor opvulling als er geen waarden voor de scores zijn
	// Prive ints voor marges en aantal figuren in de pool
	private int     iHorBtnStrut, aantalInPool;
	// Verschillende fonts gemaakt voor toepassing in de game
	private Font    btnFont, scoreFontTitel, scoreFontTekst, mainTitelFont, gameTitelFont;
	private Border  borderPanel, borderJFrame; // Borders voor de afbakening van de panels in de game
	public  Kleur   kleur; // Kleur met vele kleuren voor de game
	public  ArrayList<Figuur> lijstFiguren; // Lijst met 100 willekeurige figuren
	private Pool    pool; // Maakt en bevat een lijst met willekeurige figuren om mee te gamen
	
	private JTextArea info;
	
	public MainView ( )
	{
		// Initialiseer basis variabelen
		sMainFont = "Calibri";
		sEmpthy   = " ... ";
		iHorBtnStrut = 15;
		aantalInPool = 100;
		sSpacer      = "----------------------------------------";
		// Game kleuren
		kleur = new Kleur();
		// Init Fonts voor game
		mainTitelFont  = new Font( sMainFont, Font.BOLD, 36 );
		gameTitelFont  = new Font( sMainFont, Font.BOLD, 24 );
		btnFont        = new Font( sMainFont, Font.BOLD, 18 );
		scoreFontTitel = new Font( sMainFont, Font.BOLD, 24 );
		scoreFontTekst = new Font( sMainFont, Font.PLAIN, 24 );
		// Border Panels
		borderPanel  = BorderFactory.createLineBorder( kleur.brightgray, 4, true );
		borderJFrame = BorderFactory.createLineBorder( kleur.ivory, 16, true );
		// Init buttons voor game
		startBtn     = new JButton("START");
		stopBtn      = new JButton("STOP");
		resetBtn     = new JButton("DEMO");
		cirkelBtn    = new JButton("CIRKEL");
		driehoekBtn  = new JButton("DRIEHOEK");
		vierkantBtn  = new JButton("VIERKANT");
		rechthoekBtn = new JButton("RECHTHOEK");
		// Enable/disable buttons
		startBtn.setEnabled(true);
		stopBtn.setEnabled(false);
		resetBtn.setEnabled(false);
		cirkelBtn.setEnabled(false);
		driehoekBtn.setEnabled(false);
		vierkantBtn.setEnabled(false);
		rechthoekBtn.setEnabled(false);
		// Grootte buttons
		startBtn.setPreferredSize(new Dimension(88, 42));
		stopBtn.setPreferredSize(new Dimension(88, 42));
		resetBtn.setPreferredSize(new Dimension(88, 42));
		cirkelBtn.setPreferredSize(new Dimension(150, 42));
		driehoekBtn.setPreferredSize(new Dimension(150, 42));
		vierkantBtn.setPreferredSize(new Dimension(150, 42));
		rechthoekBtn.setPreferredSize(new Dimension(150, 42));
		// Font buttons
		startBtn.setFont(btnFont);
		stopBtn.setFont(btnFont);
		resetBtn.setFont(btnFont);
		cirkelBtn.setFont(btnFont);
		driehoekBtn.setFont(btnFont);
		vierkantBtn.setFont(btnFont);
		rechthoekBtn.setFont(btnFont);		
		// Init labels
		hoofdTitel    = new JLabel("Test je reactie !");
		gameTitel     = new JLabel("GAME OFF");
		scoreTitel    = new JLabel(" TOTAALSCORE ");
		foutenTitel   = new JLabel(" FOUT ");
		goedTitel     = new JLabel(" GOED ");
        scoreResult   = new JLabel(sEmpthy);
        foutenResult  = new JLabel(sEmpthy);
        goedResult    = new JLabel(sEmpthy);
        gameTellerTitel     = new JLabel(" GAME ");
        topTijdSecsTitel    = new JLabel(" TOPTIJD ");
        vierkantSecsTitel   = new JLabel(" VIERKANT "); 
        driehoekSecsTitel   = new JLabel(" DRIEHOEK ");  
        rechthoekSecsTitel  = new JLabel(" RECHTHOEK "); 
        cirkelSecsTitel     = new JLabel(" CIRKEL "); 
        vierkantSecsResult  = new JLabel(sEmpthy);  
        driehoekSecsResult  = new JLabel(sEmpthy);  
        rechthoekSecsResult = new JLabel(sEmpthy);  
        cirkelSecsResult    = new JLabel(sEmpthy);
        topTijdSecsResult   = new JLabel(sEmpthy); 
        gameTellerResult    = new JLabel(sEmpthy);
        spacerOost = new JLabel(sSpacer);
        spacerWest = new JLabel(sSpacer);
        // Info tekst
        info = new JTextArea();
        info.setEditable(false);
        info.setSize(60, 400);
        info.setMinimumSize(new Dimension(60,400));
        info.setMaximumSize(new Dimension(60,500));
        info.setBackground(kleur.beige);
        info.setForeground(kleur.seegreen);
        info.setMargin( new Insets( 25, 10, 0, 10) );
        info.setText( "@course:\tENPT-VI5.V.15\n"
        		+ "@copyright:\t2017 Mark Zwaving\n"
        		+ "@email:\tmarkzwaving@gmail.com\n"
        		+ "@license:\tCC0 - No Rights Reserved" );
        
        // Label fonts
        gameTellerTitel.setFont(scoreFontTitel);
		scoreTitel.setFont(scoreFontTitel);
		foutenTitel.setFont(scoreFontTitel);
		goedTitel.setFont(scoreFontTitel);
        scoreResult.setFont(scoreFontTekst);
        foutenResult.setFont(scoreFontTekst);
        goedResult.setFont(scoreFontTekst);
        vierkantSecsTitel.setFont(scoreFontTitel);
        driehoekSecsTitel.setFont(scoreFontTitel);
        rechthoekSecsTitel.setFont(scoreFontTitel);
        cirkelSecsTitel.setFont(scoreFontTitel);
        topTijdSecsTitel.setFont(scoreFontTitel); 
        vierkantSecsResult.setFont(scoreFontTekst);
        driehoekSecsResult.setFont(scoreFontTekst); 
        rechthoekSecsResult.setFont(scoreFontTekst); 
        cirkelSecsResult.setFont(scoreFontTekst);
        topTijdSecsResult.setFont(scoreFontTekst); 
        gameTellerResult.setFont(scoreFontTekst);    
		// Label alignments
		scoreTitel.setAlignmentX(Component.CENTER_ALIGNMENT);
		foutenTitel.setAlignmentX(Component.CENTER_ALIGNMENT);
		goedTitel.setAlignmentX(Component.CENTER_ALIGNMENT);
        scoreResult.setAlignmentX(Component.CENTER_ALIGNMENT);
        foutenResult.setAlignmentX(Component.CENTER_ALIGNMENT);
        goedResult.setAlignmentX(Component.CENTER_ALIGNMENT);
        vierkantSecsTitel.setAlignmentX(Component.CENTER_ALIGNMENT);
        driehoekSecsTitel.setAlignmentX(Component.CENTER_ALIGNMENT);
        rechthoekSecsTitel.setAlignmentX(Component.CENTER_ALIGNMENT);
        cirkelSecsTitel.setAlignmentX(Component.CENTER_ALIGNMENT);
        vierkantSecsResult.setAlignmentX(Component.CENTER_ALIGNMENT);
        driehoekSecsResult.setAlignmentX(Component.CENTER_ALIGNMENT);
        rechthoekSecsResult.setAlignmentX(Component.CENTER_ALIGNMENT);
        cirkelSecsResult.setAlignmentX(Component.CENTER_ALIGNMENT);
        topTijdSecsTitel.setAlignmentX(Component.CENTER_ALIGNMENT);
        topTijdSecsResult.setAlignmentX(Component.CENTER_ALIGNMENT);
        gameTellerTitel.setAlignmentX(Component.CENTER_ALIGNMENT);
        gameTellerResult.setAlignmentX(Component.CENTER_ALIGNMENT);
        spacerWest.setAlignmentX(Component.CENTER_ALIGNMENT);
        spacerOost.setAlignmentX(Component.CENTER_ALIGNMENT);
        // Colors
		hoofdTitel.setForeground(Color.GRAY);
		gameTitel.setForeground(kleur.crimson);
		goedTitel.setForeground(kleur.seegreen);
		foutenTitel.setForeground(kleur.crimson);
		scoreTitel.setForeground(kleur.dodgerblue);
        topTijdSecsTitel.setForeground(kleur.chartreuse);
        vierkantSecsTitel.setForeground(kleur.teal);
        driehoekSecsTitel.setForeground(kleur.teal);
        rechthoekSecsTitel.setForeground(kleur.teal);
        cirkelSecsTitel.setForeground(kleur.teal);
		gameTellerTitel.setForeground(kleur.salmon);
        scoreResult.setForeground(kleur.brightgray);
        foutenResult.setForeground(kleur.brightgray);
        goedResult.setForeground(kleur.brightgray);
        vierkantSecsResult.setForeground(kleur.brightgray);
        driehoekSecsResult.setForeground(kleur.brightgray);
        rechthoekSecsResult.setForeground(kleur.brightgray);
        cirkelSecsResult.setForeground(kleur.brightgray);
        topTijdSecsResult.setForeground(kleur.brightgray);
        gameTellerResult.setForeground(kleur.salmon);
		startBtn.setForeground(kleur.seegreen);
		stopBtn.setForeground(kleur.crimson);
		cirkelBtn.setForeground(kleur.teal);
		driehoekBtn.setForeground(kleur.teal);
		vierkantBtn.setForeground(kleur.teal);
		rechthoekBtn.setForeground(kleur.teal);
		resetBtn.setForeground(kleur.salmon);
		spacerOost.setForeground(kleur.cornsilk);
		spacerWest.setForeground(kleur.cornsilk);
		// Vul noord deel
		noordPanel = new JPanel();
		noordPanel.setLayout( new BoxLayout(noordPanel, BoxLayout.X_AXIS) );
		noordPanel.setBackground(kleur.beige);
		noordPanel.setBorder(borderPanel); // Rand
		hoofdTitel.setFont(mainTitelFont);
		hoofdTitel.setAlignmentX(Component.LEFT_ALIGNMENT);
		hoofdTitel.setBackground(kleur.beige);
		gameTitel.setFont(gameTitelFont);
		gameTitel.setAlignmentX(Component.LEFT_ALIGNMENT);
		gameTitel.setBackground(kleur.beige);
		
		JPanel localTopTitel = new JPanel();
		localTopTitel.setBorder(new EmptyBorder( 10, 80, 5, 0 ) );
		Component vStrut0 = Box.createVerticalStrut( 10 );
		localTopTitel.setLayout(new BoxLayout(localTopTitel, BoxLayout.Y_AXIS));
		localTopTitel.setBackground(kleur.beige);
		localTopTitel.add(hoofdTitel);
		localTopTitel.add(vStrut0);
		localTopTitel.add(gameTitel);
		
		noordPanel.add(info);
		noordPanel.add(localTopTitel);
		// Buttons
		southPanel  = new JPanel();
		southPanel.setLayout( new BoxLayout(southPanel, BoxLayout.X_AXIS) );
		southPanel.setBorder( borderPanel ); // Rand
		southPanel.setBackground(kleur.beige);
		southPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		Component hStrut0 = Box.createHorizontalStrut( 5 );
		Component hStrut1 = Box.createHorizontalStrut( iHorBtnStrut );
		Component hStrut2 = Box.createHorizontalStrut( iHorBtnStrut );
		southPanel.add(hStrut0);
		southPanel.add(startBtn);
		southPanel.add(stopBtn);
		southPanel.add(hStrut1);
		southPanel.add(cirkelBtn);
		southPanel.add(driehoekBtn);
		southPanel.add(vierkantBtn);
		southPanel.add(rechthoekBtn);
		southPanel.add(hStrut2);
		southPanel.add(resetBtn);
		// Maak Oost deel in GUI
		oostPanel  = new JPanel();
		oostPanel.setLayout( new BoxLayout(oostPanel, BoxLayout.Y_AXIS) );
		oostPanel.setBackground(kleur.cornsilk);
		oostPanel.setBorder(borderPanel); // Rand
		oostPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		oostPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
		Component vStruto1 = Box.createVerticalStrut( 35 );
		Component vStruto2 = Box.createVerticalStrut( 35 );
		Component vStruto3 = Box.createVerticalStrut( 35 );
		Component vStruto4 = Box.createVerticalStrut( 35 );
		Component vStruto5 = Box.createVerticalStrut( 35 );
		Component vStruto6 = Box.createVerticalStrut( 0 );
		oostPanel.add(vStruto1);
		oostPanel.add(scoreTitel);
		oostPanel.add(scoreResult);
		oostPanel.add(vStruto2);
		oostPanel.add(goedTitel);
		oostPanel.add(goedResult);
		oostPanel.add(vStruto3);
		oostPanel.add(foutenTitel);
		oostPanel.add(foutenResult);
		oostPanel.add(vStruto4);
		oostPanel.add(topTijdSecsTitel);
		oostPanel.add(topTijdSecsResult);
		oostPanel.add(vStruto5);
		oostPanel.add(gameTellerTitel);
		oostPanel.add(gameTellerResult);
		oostPanel.add(vStruto6);
		oostPanel.add(spacerOost);
		// Maak Westdeel in GUI
		westPanel  = new JPanel(); 
		westPanel.setLayout( new BoxLayout(westPanel, BoxLayout.Y_AXIS) );
		westPanel.setBorder( borderPanel ); // Rand
		westPanel.setBackground(kleur.cornsilk);
		westPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		westPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
		Component vStrutw1 = Box.createVerticalStrut( 60 );
		Component vStrutw2 = Box.createVerticalStrut( 60 );
		Component vStrutw3 = Box.createVerticalStrut( 60 );
		Component vStrutw4 = Box.createVerticalStrut( 60 );
		Component vStrutw5 = Box.createVerticalStrut( 0 );
		westPanel.add(vStrutw1);
		westPanel.add(vierkantSecsTitel);
		westPanel.add(vierkantSecsResult);
		westPanel.add(vStrutw2);
		westPanel.add(driehoekSecsTitel);
		westPanel.add(driehoekSecsResult);
		westPanel.add(vStrutw3);
		westPanel.add(rechthoekSecsTitel); 
		westPanel.add(rechthoekSecsResult);
		westPanel.add(vStrutw4);
		westPanel.add(cirkelSecsTitel);
		westPanel.add(cirkelSecsResult);
		westPanel.add(vStrutw5);
		westPanel.add(spacerWest);
		// Maak een pool van 100 figuren met default xy, buiten de panel
		pool = new Pool ( aantalInPool ); 
		lijstFiguren = pool.getLijst();
		// Maak de GamePanel met de figuren
		gamePanel = new GamePanel(lijstFiguren);
		gamePanel.setSize(500, 500);
		gamePanel.setBorder(borderPanel); // Rand
		gamePanel.setBackground(kleur.honeydew); 
		// Algemene panel eigenschappen van game
		setFont( mainTitelFont ); // Font van programma
		setForeground(Color.DARK_GRAY); // Tekstkleur van programma
		setLayout( new BorderLayout() ); // Hoofdpanel heeft een borderlayout
		setBorder( borderJFrame ); // Witte rand om alles
		// Voeg windstreken ;-) panels toe aan hoofdpanel 
		add( noordPanel, BorderLayout.NORTH );
		add( southPanel, BorderLayout.SOUTH );
		add( westPanel,  BorderLayout.WEST );
		add( oostPanel,  BorderLayout.EAST );
		add( gamePanel,  BorderLayout.CENTER );
		/* Done maken totale view voor het programma ! */
	}
	
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
	}
}
