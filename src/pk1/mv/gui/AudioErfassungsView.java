package pk1.mv.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import pk1.mv.fachlogik.Audio;
import pk1.mv.gui.imported.MessageView;

public class AudioErfassungsView extends ModalStage
{
	private Audio a;
	private boolean aborted = false;
	
	public AudioErfassungsView(Audio audio, Stage stage)
	{
		super(stage);
		a = audio;
	}
	
	public boolean showView()
	{
		TextField tf1 = new TextField(a.getTitel());
		TextField tf2 = new TextField(a.getInterpret());
		TextField tf3 = new TextField(Integer.toString(a.getDauer()));
		TextField tf4 = new TextField(Integer.toString(a.getJahr()));
		
		Button b1 = new Button("Neu");
		Button b2 = new Button("Abbrechen");
		
		b1.setOnAction(	new EventHandler<ActionEvent>()
						{
							public void handle(ActionEvent ae)
							{
								a.setTitel(tf1.getText());
								a.setInterpret(tf2.getText());
								
								try
								{
									a.setDauer(Integer.parseInt(tf3.getText()));
									
									try
									{
										a.setJahr(Integer.parseInt(tf4.getText()));
										close();
									}
									catch ( NumberFormatException e )
									{
										new MessageView(AudioErfassungsView.this, "Fehlermeldung", "Bitte ein g�ltiges Jahr eingeben.").showView();
									}
								}
								catch ( NumberFormatException e )
								{
									new MessageView(AudioErfassungsView.this, "Fehlermeldung", "Bitte eine g�ltige Dauer eingeben.").showView();
								}}});

		b2.setOnAction(	new EventHandler<ActionEvent>()
						{
							public void handle(ActionEvent e)
							{
								aborted = true;
								close();	
							}
						}
					  );
		
		HBox hb = new HBox(b1, b2);
		hb.setAlignment(Pos.CENTER);
		hb.setPadding(new Insets(10.0));
		hb.setSpacing(10.0);
		
		GridPane gp = new GridPane();
		gp.setPadding(new Insets(10.0));
		gp.setVgap(10.0);
		gp.setHgap(10.0);
		
		gp.addRow(0, new Label("Titel:"), 		tf1);
		gp.addRow(1, new Label("Interpret:"), 	tf2);
		gp.addRow(2, new Label("Dauer:"), 		tf3);
		gp.addRow(3, new Label("Aufnahmejahr:"),tf4);
		gp.add(hb, 0, 4, 2, 1);
		
		GridPane.setHgrow(tf1, Priority.SOMETIMES);
		
		
		Scene scene = new Scene(gp, 500, 200);
		
		this.setScene(scene);
		this.showAndWait();
		
		return aborted;
	}
}
