package pk1.mv.fachlogik;

import java.time.LocalDate;
import java.util.Objects;		//Für Objects.hash(var, ...)
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;

public abstract class Medium implements Comparable<Medium>, Serializable
{
	private final int id;
	private String titel;
	private int jahr;
	private static int counter = 0;
	private static final long serialVersionUID = 1000L;
	
	@Override
	public int compareTo(Medium m)
	{
		return Integer.compare(jahr, m.getJahr());
	}
	
	public String toString()
	{
		return String.format("Titel: %-10s Jahr: %4d", titel, jahr);
	}
	
	Medium()
	{
		id = counter++;
		titel = "";
		jahr = 0;
	}
	
	Medium(String t, int j)
	{
		id = counter++;
		titel = t;
		jahr = j;
	}
	
	public int alter()
	{
		LocalDate ld = LocalDate.now();
		
		return ld.getYear() - jahr;
	}
	
	@Override
	public int hashCode()
	{
		return Objects.hash(titel,jahr);
	}
	
	@Override
	public boolean equals(Object o)
	{
		if (o.getClass() == getClass())
		{
			if (titel.equals(((Medium) o).getTitel()) && alter() == ((Medium) o).alter())
			{
				return true;
			}
		}
		return false;
	}
	
	public abstract void druckeDaten() throws IOException;
	public abstract void druckeDaten(OutputStream stream) throws IOException;
	
	public int getId() { return id; }
	
	public int getJahr() { return jahr; }
	
	public String getTitel() { return titel; }

	public void setJahr(int jahr)
	{
		this.jahr = jahr;
	}
	
	public void setTitel(String titel)
	{
		this.titel = titel;
	}
	
	public static void setCounter(int counter)
	{
		Medium.counter = counter;
	}
}