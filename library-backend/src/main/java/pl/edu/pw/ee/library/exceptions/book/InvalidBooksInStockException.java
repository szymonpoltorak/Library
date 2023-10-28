package pl.edu.pw.ee.library.exceptions.book;

public class InvalidBooksInStockException extends IllegalArgumentException{

    public InvalidBooksInStockException(String message){
        super(message);
    }

}
