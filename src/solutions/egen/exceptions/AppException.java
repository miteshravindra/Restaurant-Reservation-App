package solutions.egen.exceptions;

public class AppException extends Exception{
	
	
 	private static final long serialVersionUID = 2579842882480137022L;


	public AppException(String msg){
		super(msg);
	}
	
	public AppException(String msg,Throwable cause){
		super(msg,cause);
	}

}
