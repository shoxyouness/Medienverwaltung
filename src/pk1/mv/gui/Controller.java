package pk1.mv.gui;

import java.util.Iterator;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import pk1.mv.datenhaltung.PersistenzException;
import pk1.mv.fachlogik.Audio;
import pk1.mv.fachlogik.Bild;
import pk1.mv.fachlogik.Medium;
import pk1.mv.fachlogik.MediumVerwaltung;
import pk1.mv.gui.handler.medienInDateiHandler;
import pk1.mv.gui.imported.MessageView;

public class Controller
{
	private MediumVerwaltung mv;
	private Stage stage;
	
	public Controller(MediumVerwaltung mediumVerwaltung, Stage stage)
	{
		mv = mediumVerwaltung;
		this.stage = stage;
	}
	
	public void init_laden(MenuItem laden, ListView<String> lv)
	{
		laden.setOnAction(	new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent ae)
			{
				try
				{
					mv.laden();

					lv.getItems().clear();

					for( Iterator<Medium> it = mv.iterator(); it.hasNext(); )
					{
						lv.getItems().add(it.next().toString());
					}
				}
				catch(PersistenzException e)
				{
//					StringWriter sw = new StringWriter();
//					e.printStackTrace(new PrintWriter(sw));			v sw.toString()
					
					new MessageView(stage, "Fehlermeldung", e.getMessage()).showView();
					e.printStackTrace();
		}}});
	}

	public void init_speichern(MenuItem speichern)
	{
		speichern.setOnAction(	new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent ae)
			{
				try
				{
					mv.speichern();
				}
				catch(PersistenzException e)
				{
					new MessageView(stage, "Fehlermeldung", e.getMessage()).showView();
					e.printStackTrace();
		}}});
	}

	public void init_medienlisteInDatei(MenuItem medienlisteInDatei)
	{
		medienlisteInDatei.setOnAction(new medienInDateiHandler(stage, mv));
	}
	
	public void init_audioNeu(MenuItem audioNeu, ListView<String> lv)
	{
		audioNeu.setOnAction(	new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent ae)
			{
				Audio a = new Audio();
				if (!new AudioErfassungsView(a, stage).showView())
				{
					mv.aufnehmen(a);
					lv.getItems().add(a.toString());
				}
		}});
	}

	public void init_bildNeu(MenuItem bildNeu, ListView<String> lv)
	{
		bildNeu.setOnAction(	new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent ae)
			{
				Bild b = new Bild();
				if (!new BildErfassungsView(b, stage).showView())
				{
					mv.aufnehmen(b);
					lv.getItems().add(b.toString());
				}
		}});
	}

	public void init_erscheinungsjahr(MenuItem erscheinungsjahr)
	{
		erscheinungsjahr.setOnAction(	new EventHandler<ActionEvent>()
		{
			@Override public void handle(ActionEvent ae)
			{
				new MessageView(stage, "Erscheinungsjahr", "Durchschnittliches Erscheinungsjahr: " + mv.berechneErscheinungsjahr()).showView();
		}});
	}

	public void init_neuestesMedium(MenuItem neuestesMedium)
	{
		neuestesMedium.setOnAction(	new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent ae)
			{
				Medium t = mv.sucheNeuesMedium();
				
				new MessageView(stage, "Neuestes Medium", (t == null) ? "Die Liste ist leer." : "Neuestes Medium: " + t).showView();
		}});
	}

	public void init_beenden(MenuItem beenden)
	{
		beenden.setOnAction(	new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent ae)
			{
				stage.close();
		}});
	}
}
