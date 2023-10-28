package pl.edu.pw.ee.library.exceptions.book;

public class NullRequestException extends IllegalArgumentException{

    public NullRequestException(String message){
        super(message);
    }

}
