using System;
using System.IO;
using System.Collections.Generic;
using Newtonsoft.Json;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class DeleteBook : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {
        if (!Page.IsPostBack)
        {
            Get_JsonFile();
            BindListBox();
        }

        ShowBookDetails();
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
    }

    // Delete the current book from the collection based on the matchind ID
    protected void Delete_Book()
    {
        Get_JsonFile();
        int index = -1;
        for (int x = 0; x < BookCollections.books.Count; x++)
        {
            if (Int32.Parse(txtBook_ID.Text) == BookCollections.books[x].id)
            {
                index = x;
                break;
            }
        }
        if (index > -1)
        {
            BookCollections.books.RemoveAt(index);
        }
    }

    // Save new book list to Json File
    protected void Save_JsonFile()
    {
        string fileExport = JsonConvert.SerializeObject(BookCollections);
        File.WriteAllText(fullFileName, fileExport);
    }

    // Populate the Textbox data to match the selected book id
    protected void ShowBookDetails()
    {
        Get_JsonFile();
        string Title = "";
        string Author = "";
        string Year = "";
        string Publisher = "";
        string ISBN = "";
        for (var i = 0; i < BookCollections.books.Count; i++)
            if (Int32.Parse(txtBook_ID.Text) == BookCollections.books[i].id)
            {
                {
                    Title += BookCollections.books[i].title;
                    Author += BookCollections.books[i].author;
                    Year += BookCollections.books[i].year;
                    Publisher += BookCollections.books[i].publisher;
                    ISBN += BookCollections.books[i].isbn;
                }
            }
        txtTitle.Text = Title;
        txtAuthor.Text = Author;
        txtYear.Text = Year;
        txtPublisher.Text = Publisher;
        txtIsbn.Text = ISBN;
    }

    // Bind the list data to the Listbox
    protected void BindListBox()
    {
        listBook_ID.DataSource = BookCollections.books;
        listBook_ID.DataTextField = "ID_Title";
        listBook_ID.DataValueField = "id";
        listBook_ID.DataBind();
        listBook_ID.SelectedIndex = 0;
        txtBook_ID.Text = listBook_ID.SelectedValue.ToString();
    }

    protected void btnDelete_Click(object sender, EventArgs e)
    {
        Delete_Book();
        Save_JsonFile();
        lblSuccess.Text = "Successfully Updated";
        BindListBox();
        ShowBookDetails();
    }

    // Update book details in TextBoxes to match each currently selected item in the Listbox
    protected void listBook_ID_SelectedIndexChanged(object sender, EventArgs e)
    {
        txtBook_ID.Text = listBook_ID.SelectedValue.ToString();
        ShowBookDetails();
    }
}