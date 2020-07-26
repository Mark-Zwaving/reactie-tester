/* CC0 1.0 Universeel s
 * 
 * The person who associated a work with this deed has dedicated the 
 * work to the public domain by waiving all of his or her rights to 
 * the work worldwide under copyright law, including all related and 
 * neighboring rights, to the extent allowed by law. You can copy, 
 * modify, distribute and perform the work, even for commercial 
 * purposes, all without asking permission.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 * 
 * @license:    https://creativecommons.org/publicdomain/zero/1.0/
 * @copyright:  2017 Mark Zwaving
 * @email:      markzwaving@gmail.com
 */
import java.awt.Dimension;
import javax.swing.JFrame;
import control.MainControl;
import view.MainView;

/**
 * Hoofd opstartklasse
 * @author  Mark Zwaving
 * @see     JFrame     
 * @version 1
 */
public class Application extends JFrame
{
	public static void main( String[] args )
	{
		JFrame app = new JFrame();
		app.setTitle( "  Reactie Tester  " ); // Titel
		app.setSize( new Dimension( 940, 740 ) ); // Groote app
		app.setResizable( false ); // Vaste grootte, dus scherm niet verkleinen of vergroten
		app.setLocationByPlatform( false ); // Gebruik geen standaard platform verschijningsvorm op het scherm
		app.setLocationRelativeTo( null ); // Zet window in center van scherm 
		app.setDefaultCloseOperation( EXIT_ON_CLOSE ); // Sluit programma goed af
		// View, Model, Controller
		MainView view = new MainView();  // View
		// Model classes voor de control worden rechtstreek aangeroepen in de control
		new MainControl( view ); // View toevoegen in de control
		app.setContentPane( view ); // Voeg view tevens aan JFrame toe
		app.setVisible( true ); // Zet app op zichtbaar
	}
}
