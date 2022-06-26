using System;
using System.IO;
using System.Collections.Generic;
using Newtonsoft.Json;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class AddBook : System.Web.UI.Page
{

    protected void Page_Load(object sender, EventArgs e)
    {
        Get_JsonFile();
        lblSuccess.Text = "";
    }

    // Locate and store the Json file path for easier access
    const string FILENAME = "Books.json";
    string fullFileName = Path.Combine(AppDomain.CurrentDomain.BaseDirectory, FILENAME);

    // Initialise a new list of BookCollection
    BookCollection BookCollections = new BookCollection();

    // Retrieve and Load the Json File
    protected void Get_JsonFile()
    {
        string fileImport = File.ReadAllText(fullFileName);
        BookCollections = JsonConvert.DeserializeObject<BookCollection>(fileImport);

        // Create a new ID for each new entry
        int newID = BookCollections.books.Count + 1;
        txtBook_ID1.Text = newID.ToString();
    }

    protected void Add_Book()
    {
        // Initialise a new book with data populated from the Textboxes
        Book book = new Book();
        book.id = Int32.Parse(txtBook_ID1.Text);
        book.title = txtTitle.Text;
        book.author = txtAuthor.Text;
        book.year = txtYear.Text;
        book.publisher = txtPublisher.Text;
        book.isbn = txtIsbn.Text;

        // Add new book to list of BookCollections
        BookCollections.books.Add(book);
    }

    // Save new book list to Json File
    protected void Save_JsonFile()
    {
        string fileExport = JsonConvert.SerializeObject(BookCollections);
        File.WriteAllText(fullFileName, fileExport);
    }

    // Iterate through controls and set all Textboxes to Empty Strings
    protected void ClearTextBoxes(Control AddBookPage)
    {
        foreach (Control ctrl in AddBookPage.Controls)
        {
            // Find all Textboxes
            if (ctrl is TextBox)
            {
                TextBox txt = ctrl as TextBox;
                // If Textbox contains data, set to empty
                if (txt != null)
                {
                    txt.Text = String.Empty;
                }
            }
            else
            {
                if (ctrl.Controls.Count > 0)
                {
                    ClearTextBoxes(ctrl);
                }
            }
        }
    }

    protected void btnAdd_Click(object sender, EventArgs e)
    {
        Add_Book();
        Save_JsonFile();
        lblSuccess.Text = "Successfully Updated";
        ClearTextBoxes(Page);
        Get_JsonFile();

    }
}