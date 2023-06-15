package pk1.mv.fachlogik;
import java.util.Objects;
import java.io.*;

public class Audio extends Medium implements Serializable
{
	private static final long serialVersionUID = 1000L;
	
	private String interpret;
	private int dauer;
	
	public String toString()
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
    	try
    	{
    		druckeDaten(baos);
    	}
    	catch(IOException e) { e.printStackTrace(); }
    	
    	return baos.toString();
	}
	
	public Audio()
	{
		super();
		interpret = "";
		dauer = 0;
	}
	
	public Audio(String t, int j, String in, int d)
	{
		super(t,j);
		interpret = in;
		dauer = d;
	}
	
	@Override
	public void druckeDaten()
	{
		System.out.println("ID = " + getId() + " \"" + getTitel() + "\" von " + getInterpret() + " aus " + getJahr() + " Spieldauer: " + getDauer() + " sek.");
	}

	@Override
	public void druckeDaten(OutputStream stream) throws IOException
	{	// Wenn der Printwriter hier bereits geschlossen wird, schlieﬂt dies direkt den gesamten Stream
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(stream)));
		
		pw.printf("ID = %d \"%s\" von %s aus %d Spieldauer: %d sek.\n",getId(),getTitel(),getInterpret(),getJahr(),getDauer());
		pw.flush();
	}
	
	public String getInterpret() { return interpret; }
	
	public int getDauer() { return dauer; }
	
	public void setInterpret(String interpret)
	{
		this.interpret = interpret;
	}
	
	public void setDauer(int dauer)
	{
		this.dauer = dauer;
	}
	
	@Override
	public int hashCode()
	{
		return Objects.hash(super.getJahr(),super.getTitel(),dauer,interpret);
	}
	@Override
	public boolean equals(Object o)
	{
		if (super.equals(o) && ((Audio) o).getInterpret().equals(getInterpret()) && ((Audio) o).getDauer() == getDauer())
		{
			return true;
		}
		
		return false;
	}
}