package pk1.mv.fachlogik;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import pk1.mv.datenhaltung.IDao;
import pk1.mv.datenhaltung.PersistenzException;

public class MediumVerwaltung
{
	private LinkedList<Medium> l = new LinkedList<Medium>();
	private IDao idao;
	
	public MediumVerwaltung(IDao idao)
	{
		this.idao = idao;
	}
	
	public Iterator<Medium> iterator()
	{
		return l.iterator();
	}

	public void speichern() throws PersistenzException
	{
		idao.speichern(l);
	}
	
	public void laden() throws PersistenzException
	{
		List<Medium> l_laden = idao.laden();
		
		l = new LinkedList<Medium>();
		int counter = 0;
		
		for( Iterator<Medium> it = l_laden.iterator(); it.hasNext(); )
		{
			l.add(it.next());
			counter++;
		}
		Medium.setCounter(counter);
	}
	
	public void aufnehmen(Medium x)
	{
		l.add(x);
	}
	
	public void zeigeMedien()
	{
		try
		{
			zeigeMedien(System.out);
		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}
	}
	
	public void zeigeMedien(OutputStream stream) throws IOException
	{
		Collections.sort(l);
		
		for (Medium x: l)
		{
			x.druckeDaten(stream);
		}
	}
	
	public Medium sucheNeuesMedium()
	{
		if (l.isEmpty())
		{
			return null;
		}
		
		Iterator<Medium> it = l.iterator();
		
		Medium t, m;	
		
		t = it.next();
		
		while(it.hasNext())
		{
			m = it.next();
			if (t.getJahr() < m.getJahr())
			{
				t = m;
			}
		}
		
		return t;
	}
	
	public double berechneErscheinungsjahr()
	{
		if (l.isEmpty())
		{
			return -1;
		}
		
		Iterator<Medium> it = l.iterator();
		
		double x = 0;
		
		while(it.hasNext())
		{
			x += (it.next()).getJahr();
		}
		
		return x/l.size();
	}
}