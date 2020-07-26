package view;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * Game klasse. Panel voor de willekeurige figuren
 * @author  Mark Zwaving
 * @version 1
 */
public class GamePanel extends JPanel
{
	private ArrayList<Figuur> lijstFiguren;
	
	public GamePanel ( ArrayList<Figuur> lijst ) 
	{
		lijstFiguren = lijst;
	}
	
	/**
	 * Methode schrijft aantal (willekeurige) figuren naar het scherm
	 * @param  Graphics g
	 */
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		for ( Figuur figuur : lijstFiguren ) 
			figuur.tekenen(g);
	}
}
