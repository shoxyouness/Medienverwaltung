package pk1.mv.fachlogik;
import java.util.Objects;
import java.io.*;

public class Bild extends Medium implements Serializable
{
	private static final long serialVersionUID = 1000L;
	
	private String ort;
	
	public Bild()
	{
		super();
		ort = "";
	}
	
	public Bild(String t, int j, String o)
	{
		super(t,j);
		ort = o;
	}
	
	public void druckeDaten() throws IOException
	{
		druckeDaten(System.out);
	}
	
	@Override
	public void druckeDaten(OutputStream stream) throws IOException
	{
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(stream)));
		pw.printf("ID = %d \"%s\" aufgenommen im Jahr %d in %s.\n",getId(),getTitel(),getJahr(),getOrt());
		pw.flush();
	}
	
	public String getOrt() { return ort; }
	
	public void setOrt(String ort)
	{
		this.ort = ort;
	}
	
	@Override
	public int hashCode()
	{
		return Objects.hash(super.getJahr(),super.getTitel(),ort);
	}
	@Override
	public boolean equals(Object o)
	{
		if (super.equals(o) && ((Bild) o).getOrt().equals(getOrt()))
		{
			return true;
		}
		
		return false;
	}
	
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
}