package pk1.mv.gui;

import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import pk1.mv.fachlogik.Bild;
import pk1.mv.gui.imported.MessageView;

public class BildErfassungsView extends ModalStage
{
	private Bild b;
	private boolean aborted = false;
	
	public BildErfassungsView(Bild bild, Stage stage)
	{
		super(stage);
		b = bild;
	}
	
	public boolean showView()
	{
		TextField tf1 = new TextField(b.getTitel());
		TextField tf2 = new TextField(b.getOrt());
		TextField tf3 = new TextField(Integer.toString(b.getJahr()));
		
		Button b1 = new Button("Neu");
		Button b2 = new Button("Abbrechen");
		
		b1.setOnAction(	new EventHandler<ActionEvent>()
						{
							public void handle(ActionEvent ae)
							{
								b.setTitel(tf1.getText());
								b.setOrt(tf2.getText());

								try
								{
									b.setJahr(Integer.parseInt(tf3.getText()));
									close();
								}
								catch ( NumberFormatException e )
								{
									new MessageView(BildErfassungsView.this, "Fehlermeldung", "Bitte ein gültiges Jahr eingeben.").showView();
								}}});
		
		b2.setOnAction(	new EventHandler<ActionEvent>()
						{
							public void handle(ActionEvent e)
							{
								aborted = true;
								BildErfassungsView.this.close();
							}
						}
					  );
		
		HBox hb = new HBox();
		hb.getChildren().addAll(b1, b2);
		hb.setAlignment(Pos.CENTER);
		hb.setPadding(new Insets(10.0));
		hb.setSpacing(10.0);
		
		GridPane gp = new GridPane();
		gp.setVgap(10);
		gp.setHgap(10);
		gp.setPadding(new Insets(10));
		
		gp.addRow(0,new Label("Titel:"),tf1);
		gp.addRow(1,new Label("Ort:"),tf2);
		gp.addRow(2,new Label("Aufnahmejahr:"),tf3);
		
		GridPane.setHgrow(tf1,Priority.SOMETIMES);
		
		
		BorderPane bp = new BorderPane();
		bp.setBottom(hb);
		bp.setCenter(gp);
		
		
		Scene scene = new Scene(bp, 500, 200);
		
		this.setScene(scene);
		this.showAndWait();
		
		return aborted;
	}
}
