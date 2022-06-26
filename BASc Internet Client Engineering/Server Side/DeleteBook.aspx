<%@ Page Language="C#" AutoEventWireup="true" CodeFile="DeleteBook.aspx.cs" Inherits="DeleteBook" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>Melbourne Public Library - DeleteBook</title>
    <meta name="author" content="James Hawes" />
    <link href="Theme.css" rel="stylesheet" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
</head>
<body onload="pageTitle()">
    <div id="main-wrapper">

        <header>
            <nav>
                <div id="logo">
                    <img src="Images/logo.png" alt="logo.png" />
                </div>


                <div id="navigation">
                    <ul class="top-menu" id="mainMenu">
                        <li>
                            <a href="Index.aspx">HOME</a>
                        </li>
                        <li class="return-home"><a href="Index.aspx"><i class="fa fa-home"></i></a></li>

                        <li>
                            <a href="AddBook.aspx">Add Book</a>
                        </li>

                        <li id="responsive-title"></li>

                        <li>
                            <a href="EditBook.aspx">Edit Book</a>
                        </li>
                        <li id="active-page">
                            <a href="DeleteBook.aspx">Delete Book</a>
                        </li>
                        <li class="hamburger">
                            <a href="javascript:void(0);" onclick="openMenu()">&#9776;</a>
                        </li>
                    </ul>
                </div>
            </nav>
        </header>

        <form id="frmDelete" runat="server">
            <div id="content">
                <asp:ListBox ID="listBook_ID" runat="server" CssClass="listBox" OnSelectedIndexChanged="listBook_ID_SelectedIndexChanged" AutoPostBack="True"></asp:ListBox>

                <p></p>
                <asp:Label ID="lblBook_ID" runat="server" Text="Book ID : " CssClass="labels"></asp:Label>
                <asp:TextBox ID="txtBook_ID" runat="server" CssClass="textinputs" Enabled="false"></asp:TextBox>
                <p></p>
                <asp:Label ID="lblTitle" runat="server" Text="Title : " CssClass="labels"></asp:Label>
                <asp:TextBox ID="txtTitle" runat="server" CssClass="textinputs" Enabled="false"></asp:TextBox>
                <p></p>
                <asp:Label ID="lblAuthor" runat="server" Text="Author : " CssClass="labels"></asp:Label>
                <asp:TextBox ID="txtAuthor" runat="server" CssClass="textinputs" Enabled="false"></asp:TextBox>
                <p></P>
                <asp:Label ID="lblYear" runat="server" Text="Year : " CssClass="labels"></asp:Label>
                <asp:TextBox ID="txtYear" runat="server" CssClass="textinputs" Enabled="false"></asp:TextBox>
                <p></p>
                <asp:Label ID="lblPublisher" runat="server" Text="Publisher : " CssClass="labels"></asp:Label>
                <asp:TextBox ID="txtPublisher" runat="server" CssClass="textinputs" Enabled="false"></asp:TextBox>
                <p></p>
                <asp:Label ID="lblIsbn" runat="server" Text="ISBN : " CssClass="labels"></asp:Label>
                <asp:TextBox ID="txtIsbn" runat="server" CssClass="textinputs" Enabled="false"></asp:TextBox>
                <p></p>
                <asp:Button ID="btnDelete" runat="server" Text="Delete Book" CssClass="buttons" OnClick="btnDelete_Click"/>
                <asp:Label ID="lblSuccess" runat="server" Text="" CssClass="Successmsg"></asp:Label>
            </div>
        </form>

        <footer>
            <div id="footer">
                <div class="footerwrap">

                    <a href="https://validator.w3.org/">
                        <img src="https://www.w3.org/html/logo/badge/html5-badge-h-css3-semantics.png" width="88" height="35"
                            alt="HTML5 Powered with CSS3 / Styling, and Semantics" title="HTML5 Powered with CSS3 / Styling, and Semantics">
                    </a>
                    <a href="http://jigsaw.w3.org/css-validator">
                        <img style="border: 0; width: 88px; height: 35px"
                            src="http://jigsaw.w3.org/css-validator/images/vcss"
                            alt="Valid CSS!" title="Valid CSS!" />
                    </a>

                    <div id="footer-copywrite">
                        © Melbourne Public Library 2017  |  Version 1.00  |  19376877@students.latrobe.edu.au 
                    </div>
                </div>
            </div>
        </footer>

    </div>



    <script type="text/javascript">
        function openMenu() {
            var x = document.getElementById("mainMenu");
            if (x.className === "top-menu") {
                x.className += " responsive";
            } else {
                x.className = "top-menu";
            }
        }
    </script>

    <script>
        function pageTitle() {
            var x = document.getElementById("active-page").textContent;
            document.getElementById("responsive-title").textContent = x;
        }

    </script>

</body>
</html>
