using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

/// <summary>
/// Summary description for Book
/// </summary>
/// 


public class Book
{
    public int id { get; set; }
    public string title { get; set; }
    public string author { get; set; }
    public string year { get; set; }
    public string publisher { get; set; }
    public string isbn { get; set; }

    public string ID_Title
    {
        get
        {
            return string.Format(" {0} : ({1})", id, title);
        }
    }
}


