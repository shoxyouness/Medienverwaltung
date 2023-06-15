package pk1.mv.datenhaltung;

public class PersistenzException extends Exception
{
	private static final long serialVersionUID = 100;
	
	public PersistenzException()
	{
		super();
	}
	
	public PersistenzException(String message)
	{
		super(message);
	}
}
