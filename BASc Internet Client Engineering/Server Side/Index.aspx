<%@ Page Language="C#" AutoEventWireup="true" CodeFile="Index.aspx.cs" Inherits="_Default" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>Melbourne Public Library - Catalogue Site Home</title>
    <meta name="author" content="James Hawes" />
    <link href="Theme.css" rel="stylesheet" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
</head>

<body onload="pageTitle()">
    <div id="main-wrapper">

        <header>

            <nav>
                <div id="logo">
                    <img src="Images/logo.png" alt="logo.png"/>
                </div>


                <div id="navigation">
                    <ul class="top-menu" id="mainMenu">
                        <li id="active-page">
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
                        <li>
                            <a href="DeleteBook.aspx">Delete Book</a>
                        </li>
                        <li class="hamburger">
                            <a href="javascript:void(0);" onclick="openMenu()">&#9776;</a>
                        </li>
                    </ul>
                </div>


            </nav>
        </header>

        <form id="frmIndex" runat="server">
            <div id="content">
                <asp:GridView ID="gvMainGrid" runat="server" CellSpacing="2" Height="100%" AutoGenerateColumns="False" CssClass="gridview">  
                    <HeaderStyle BackColor="#f9fbff" ForeColor="Black"></HeaderStyle>
                    <PagerSettings Position="TopAndBottom" />
                    <RowStyle BackColor="White" CssClass="gridview"></RowStyle>
                    
                    <Columns>
                        <asp:BoundField DataField="id" HeaderText="ID" ReadOnly="True"/>
                        <asp:BoundField DataField="title" HeaderText="Title" ReadOnly="True" />
                        <asp:BoundField DataField="author" HeaderText="Author" ReadOnly="True" />
                        <asp:BoundField DataField="year" HeaderText="Year" ReadOnly="True" />
                        <asp:BoundField DataField="publisher" HeaderText="Publisher" ReadOnly="True" />
                        <asp:BoundField DataField="isbn" HeaderText="ISBN" ReadOnly="True" />
                    </Columns>

                    
                </asp:GridView>
            </div>
        </form>

        <footer>
            <div id="footer">
                <div class="footerwrap">

                    <a href="https://validator.w3.org/">
                        <img src="https://www.w3.org/html/logo/badge/html5-badge-h-css3-semantics.png" width="88" height="35"
                            alt="HTML5 Powered with CSS3 / Styling, and Semantics" title="HTML5 Powered with CSS3 / Styling, and Semantics" />
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



    <%--Script to append the class of the nav bar on smaller screens to enable styling--%>
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

    <%--Script to set the title of the page in the responsive-title list element for smaller screens less than 1000 pixels--%>
    <script>
        function pageTitle() {
            var x = document.getElementById("active-page").textContent;
            document.getElementById("responsive-title").textContent = x;
        }

    </script>

</body>
</html>
