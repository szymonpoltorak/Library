package pl.edu.pw.ee.library.api.book.validators;

import pl.edu.pw.ee.library.api.book.data.BookRequest;

import static pl.edu.pw.ee.library.api.book.validators.constants.ValidatorConstants.*;

public final class BookRequestValidator {

    private BookRequestValidator(){
    }

    public static void validateBookRequest(BookRequest bookRequest){
        if(bookRequest==null){
            throw new IllegalArgumentException(BOOK_REQUEST_NULL);
        }
        validateString(bookRequest.author());
        validateString(bookRequest.title());
        validateBooksInStock(bookRequest.booksInStock());
    }

    private static void validateString(String string){
        if(string==null){
            throw new IllegalArgumentException(STRING_NULL);
        }
        if(string.isBlank()){
            throw new IllegalArgumentException(STRING_EMPTY_BLANK);
        }
    }

    private static void validateBooksInStock(int booksInStock){
        if(booksInStock<1){
            throw new IllegalArgumentException(TOO_FEW_BOOKS);
        }
        if(booksInStock>100){
            throw new IllegalArgumentException(TOO_MANY_BOOKS);
        }
    }

}
