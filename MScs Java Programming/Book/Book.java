package Book;

public class Book
{
    private String title;
    private Author author;
    private double price;

    public Book(String title, Author name, double price)
    {
        this.title = title;
        this.author = name;
        this.price = price;
    }

    public void display()
    {
        System.out.println("Book Title : " + title); 
        author.display();
        System.out.println("Price : $" + price);
    }

    public Author getAuthor()
    {
        return author;
    }

    public String getTitle()
    {
        return title;
    }
    
    public void setAuthor(String firstName, String lastName)
    {
        author.setFirstName(firstName);
        author.setLastName(lastName);
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }
}
