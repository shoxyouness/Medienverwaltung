package pk1.mv.datenhaltung;

import java.util.List;

import pk1.mv.fachlogik.*;

public interface IDao
{
	public void speichern(List<Medium> liste) throws PersistenzException;
	public List<Medium> laden() throws PersistenzException;
}
