package pk1.mv.gui;

import javafx.stage.Modality;
import javafx.stage.Stage;

public abstract class ModalStage extends Stage
{
	public ModalStage(Stage stage)
	{
	   this.initOwner(stage);
	   this.initModality(Modality.WINDOW_MODAL);
	}
}
