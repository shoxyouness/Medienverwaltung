package pk1.mv.gui;

import javafx.application.Application;
import javafx.stage.Stage;
import pk1.mv.datenhaltung.ListeSerializeDAO;
import pk1.mv.fachlogik.Audio;
import pk1.mv.fachlogik.Bild;
//import pk1.mv.fachlogik.Audio;
//import pk1.mv.fachlogik.Bild;
import pk1.mv.fachlogik.MediumVerwaltung;

public class Warp extends Application
{
	public void start(Stage stage)
	{
		MediumVerwaltung mv = new MediumVerwaltung(new ListeSerializeDAO());
		
		mv.aufnehmen(new Audio("Titel",1995,"Younes",3));
		mv.aufnehmen(new Audio("Titel2",1997,"Younes",3));
		mv.aufnehmen(new Audio("Titel3",2000,"Younes",3));
		mv.aufnehmen(new Bild("Titel4",2001,"Germany"));
		
		System.out.println("4 Eintr�ge vorgefertigt.\n");
		
		Controller c = new Controller(mv, stage);
		
		HauptView hv = new HauptView(c);
		
		hv.showView(stage);
	}
	
	public static void main(String[] args)
	{
		launch();
	}
}
