package pk1.mv.gui.handler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import pk1.mv.fachlogik.MediumVerwaltung;
import pk1.mv.gui.*;
import pk1.mv.gui.imported.InputView;

public class medienInDateiHandler implements EventHandler<ActionEvent>
{
	private Stage stage;
	private MediumVerwaltung mv;
	
	public medienInDateiHandler(Stage stage, MediumVerwaltung mediumVerwaltung)
	{
		this.stage = stage;
		mv = mediumVerwaltung;
	}
	
	@Override
	public void handle(ActionEvent ae)
	{
		while(true)
		{
			try
			{
				String t = new InputView(stage, "Eingabe", "Ort: D:\\Desktop\\Java\\.Dateien\\<Dateiname>.txt\nDateiname Eingeben :", "").showView();
				if (t == null)
				{
					return;
				}
				if (t.isEmpty())
				{
					throw new EmptyFilenameException("Der Dateiname darf nicht leer sein.");
				}
				
				try (FileOutputStream fos = new FileOutputStream(new File("D:\\Desktop\\Java\\.Dateien\\" + t + ".txt")))
				{
					mv.zeigeMedien(fos);
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
				System.out.println("Schreiben erfolgreich.");
				
				break;
			}
			catch ( EmptyFilenameException e )
			{
				int temp = JOptionPane.showConfirmDialog(null, "Der Dateiname ist leer. Neuen Dateinamen wählen?","",JOptionPane.YES_NO_OPTION);
				if ( temp == JOptionPane.NO_OPTION || temp == JOptionPane.CLOSED_OPTION)	// == 0 ( == 1: No, == 2: Cancel, == -1: Closed)
				{
					break;
				}
			}
		}
	}
}
