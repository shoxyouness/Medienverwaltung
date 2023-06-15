package pk1.mv.datenhaltung;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import pk1.mv.fachlogik.Medium;

public class ListeSerializeDAO implements IDao
{
	@SuppressWarnings("unchecked")
	@Override
	public List<Medium> laden() throws PersistenzException
	{
		File f = new File("C:\\Users\\medyo\\Desktop\\Studium\\pk1\\PK P_09\\src\\daten1.ser");
		
		try ( ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(f))))
		{
			return (List<Medium>)ois.readObject();
		}
		catch (FileNotFoundException e)
		{
			PersistenzException pe = new PersistenzException(String.format("Die Datei daten1.ser wurde nicht gefunden. (%s)\n", f.getAbsolutePath()));
			pe.setStackTrace(e.getStackTrace());

			throw pe;
		}
		catch (IOException e)
		{
			PersistenzException pe = new PersistenzException("IOException.");
			pe.setStackTrace(e.getStackTrace());

			throw pe;
		}
		catch (ClassNotFoundException e)
		{
			PersistenzException pe = new PersistenzException("Die Klasse wurde nicht gefunden.");
			pe.setStackTrace(e.getStackTrace());
	
			throw pe;
		}
	}

	@Override
	public void speichern(List<Medium> liste) throws PersistenzException
	{
		try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File("C:\\Users\\medyo\\Desktop\\Studium\\pk1\\PK P_09\\src\\daten1.ser")))))
		{
			oos.writeObject(liste);
			System.out.println("Gespeichert.");
		}
		catch (IOException e)
		{
			PersistenzException pe = new PersistenzException("IOException.");
			pe.setStackTrace(e.getStackTrace());
			
			throw pe;
		}
	}
}
