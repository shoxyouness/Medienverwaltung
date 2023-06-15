package pk1.mv.gui;

public class EmptyFilenameException extends Exception
{
	private static final long serialVersionUID = 100;
	
	public EmptyFilenameException()
	{
		super("Der Dateiname darf nicht leer sein.");
	}
	
	public EmptyFilenameException(String message)
	{
		super(message);
	}
}
