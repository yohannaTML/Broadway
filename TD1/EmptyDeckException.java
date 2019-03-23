import java.lang.Exception;

class EmptyDeckException extends Exception{

	private String message;

	public EmptyDeckException(String message){

		this.message=message;
		System.out.println(message);

	}

	public String getMessage(){

		return this.message;

	}
    
};
