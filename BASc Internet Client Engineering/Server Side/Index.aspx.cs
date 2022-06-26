using System;
using System.IO;
using System.Collections.Generic;
using Newtonsoft.Json;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class _Default : System.Web.UI.Page
{

    protected void Page_Load(object sender, EventArgs e)
    {
        Get_JsonFile();
        BindGridView();
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

    // Save new book list to Json File
    protected void Save_JsonFile()
    {
        string fileExport = JsonConvert.SerializeObject(BookCollections);
        File.WriteAllText(fullFileName, fileExport);
    }

    // Bind the list data to GridView
    protected void BindGridView()
    {
        gvMainGrid.DataSource = BookCollections.books;
        gvMainGrid.DataBind();
    }

    protected void OnPageIndexChanging(object sender, GridViewPageEventArgs e)
    {
        gvMainGrid.PageIndex = e.NewPageIndex;
        gvMainGrid.DataBind();
    }

}