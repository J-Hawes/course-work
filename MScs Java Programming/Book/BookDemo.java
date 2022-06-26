package Book;

public class BookDemo
{
    public static void main(String[ ] args)
    {
        Book newBook = new Book("Developing Java Software", 
        new Author("Russel", "Winderand"), 79.95);
        newBook.display();
    }
}
