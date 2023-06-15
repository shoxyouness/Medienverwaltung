package pk1.mv.gui;

import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class HauptView extends Stage
{
	private Controller c;
	
	public HauptView(Controller controller)
	{
		c = controller;
	}
	
	public void showView(Stage stage)
	{
		Menu datei = new Menu("Datei");
		MenuItem laden = new MenuItem("Laden");
		MenuItem speichern = new MenuItem("Speichern");
		MenuItem medienlisteInDatei = new MenuItem("Medienliste in Datei");
		MenuItem beenden = new MenuItem("Beenden");
		datei.getItems().addAll(laden,speichern, new SeparatorMenuItem(), medienlisteInDatei, new SeparatorMenuItem(),beenden);
		
		Menu medium = new Menu("Medium");
		MenuItem audioNeu = new MenuItem("Audio neu");
		MenuItem bildNeu = new MenuItem("Bild neu");
		medium.getItems().addAll(audioNeu,bildNeu);
		
		Menu anzeige = new Menu("Anzeige");
		MenuItem erscheinungsjahr = new MenuItem("Erscheinungsjahr");
		MenuItem neuestesMedium = new MenuItem("Neuestes Medium");
		anzeige.getItems().addAll(erscheinungsjahr, neuestesMedium);
		
		MenuBar mb = new MenuBar();
		mb.getMenus().addAll(datei, medium, anzeige);
		
		ListView<String> lv = new ListView<String>();
		
		BorderPane bp = new BorderPane();
		bp.setTop(mb);
		bp.setCenter(lv);
		
		
		c.init_laden(laden, lv);
		
		c.init_speichern(speichern);
		
		c.init_medienlisteInDatei(medienlisteInDatei);
		
		c.init_audioNeu(audioNeu, lv);
		
		c.init_bildNeu(bildNeu, lv);
		
		c.init_erscheinungsjahr(erscheinungsjahr);
		
		c.init_neuestesMedium(neuestesMedium);
		
		c.init_beenden(beenden);
		

		Scene scene = new Scene(bp, 500, 300);
		
		stage.setScene(scene);
		stage.setTitle("Medienverwaltung");
		stage.show();
	}
}
