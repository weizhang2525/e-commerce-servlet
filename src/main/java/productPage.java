/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONObject;
import java.sql.*;  

/**
 *
 * @author apizzle
 */
@WebServlet(urlPatterns={"/productPage"})
public class productPage extends HttpServlet {

    String productID = "woo";
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet productPage</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet productPage at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

    	PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        
        String pid = (String)session.getAttribute("prod");

        out.println(pid);


                
        try {
            // 0. Check for driver
//             out.println("TEST S - 2");
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 1. Get a connectiong to database
//             out.println("TEST S - 3");
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Nuance9?useSSL=false&serverTimezone=PST", "test", "test");

            // 2. Create a statement3
//             out.println("TEST S - 4");
            Statement myStmt = myConn.createStatement();

            // 3. Execute SQL Query
//             out.println("TEST S - 5");
            
            PreparedStatement st = myConn.prepareStatement("SELECT * FROM products WHERE pid=?");
            st.setString(1,pid);
            ResultSet myResult = st.executeQuery();
            
//            ResultSet myResult = myStmt.executeQuery("SELECT * from products where pid = " + pid);
            
            JSONArray productList = new JSONArray();

            ArrayList<String> ar = new ArrayList<String>();

            String returnString = "";
            
            while(myResult.next()) {
                JSONObject newProduct = new JSONObject();
                
                ar.add(myResult.getString("pid"));
                ar.add(myResult.getString("pname"));
                ar.add(myResult.getString("price"));
                ar.add(myResult.getString("desc"));
                ar.add(myResult.getString("quantity"));
                ar.add(myResult.getString("srcOne"));
                ar.add(myResult.getString("srcTwo"));
                ar.add(myResult.getString("srcThree"));
                ar.add(myResult.getString("alt"));

                returnString += myResult.getString("pid");
                returnString += "@";
                returnString += myResult.getString("pname");
                returnString += "@";
                returnString += myResult.getString("price");
                returnString += "@";
                returnString += myResult.getString("desc");
                returnString += "@";
                returnString += myResult.getString("quantity");
                returnString += "@";
                returnString += myResult.getString("srcOne");
                returnString += "@";
                returnString += myResult.getString("srcTwo");
                returnString += "@";
                returnString += myResult.getString("srcThree");
                returnString += "@";
                returnString += myResult.getString("alt");
                
                
                

                

                newProduct.put("pid", myResult.getString("pid"));
                newProduct.put("pname", myResult.getString("pname"));
                newProduct.put("price", myResult.getString("price"));
                newProduct.put("desc", myResult.getString("desc"));
                newProduct.put("quantity", myResult.getString("quantity"));
                newProduct.put("srcOne", myResult.getString("srcOne"));
                newProduct.put("srcTwo", myResult.getString("srcTwo"));
                newProduct.put("srcThree", myResult.getString("srcThree"));
                newProduct.put("alt", myResult.getString("alt"));

                productList.put(newProduct);
            }


            out.println(returnString);
            

            // out.println("TEST S - 7 - BEFORE");
            
            // out.println("TEST S - 8 - AFTER");

            

            

        } catch (Exception e) {
            out.println("[ERROR - S]: " + e.toString());
            // out.println("TEST S - 2");
            e.printStackTrace();
        }


 
        

    } 


    protected void createProductPage(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();

        out.println();

        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");

        out.println("<head>");
        out.println("<meta charset='utf-8'>");
        out.println("<style type='text/css'>");
        out.println("body { margin:0; }");
        
        out.println("navbar-brand {");
        out.println("display: inline-block;");
        out.println("padding-top: .3125rem;");
        out.println("padding-bottom: .3125rem;");
        out.println("margin-right: 1rem;");
        out.println("font-size: 1.25rem;");
        out.println("line-height: inherit;");
        out.println("white-space: nowrap;");
        out.println("}");

        out.println(".navbar {");
        out.println("padding: 0;");
        out.println("background-color: #363062;");
        out.println("}");

/*Background color for nav bar elements **************************************/
        out.println(".topnav {");
        out.println("background-color:#363062;");
        out.println("overflow: hidden;");
        out.println("padding: 15px;");
        out.println("}");

/* Nav bar links */
    out.println(".topnav a {");
    out.println("float: left;");
    out.println("color: #d8b9c3;");
    out.println("text-align: center;");
    out.println("padding: 15px 15px;");
    out.println("text-decoration: none;");
    out.println("font-size: 15px;");
    out.println("}");

/* Nav bar links hover */
    out.println(".topnav a:hover {");
    out.println("background-color: #4d4c7d;");
    out.println("color: #ebdde1;");
    out.println("}");

/* Nav bar active link */
    out.println(".topnav a.active {");
    out.println("ackground-color: #d8b9c3;");
    out.println("color: #363062;");
    out.println("}");

    

    out.println(".Product{");
    out.println("margin-top: 100px;");
    out.println("margin-left:100px;");
    out.println("}");

    out.println(".col-md-7 h2{");
    out.println("color: hsl(247, 34%, 29%);");
    out.println("align-items: center;");
    out.println("}");

    out.println(".product-disciption{");
    out.println("font-size: 8px;");
    out.println("}");

    out.println(".rating{");
    out.println("height: 60px;");
    
    out.println("}");

    out.println(".price{");
    out.println("color:#363062;");
    out.println("font-size:26px;");
    out.println("font-weight: bold;");
    out.println("padding-top:20px;");
    out.println("}");

    out.println(".input{");
    out.println("border: 5px;");
    out.println("font-weight: bold;");
    out.println("height:33px;");
    out.println("text-align: center;");
    out.println("background-color: #f2e4e7;");
    

    out.println("}");

    out.println(".cart{");
    out.println("background:orange;");
    out.println("color: black;");
    out.println("font-size: 15px;");
    out.println("margin-left:20px;");
    out.println("}");


    out.println("</style>");
    
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1, shrink-to-fit=no'");

        out.println("<link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css' integrity='sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh' crossorigin='anonymous'>");
        out.println("<link rel='stylesheet' href='css/product-page.css'>");
        
        out.println("<script src='https://code.jquery.com/jquery-3.2.1.min.js'></script>");
        out.println("<script src='js/product-info.js'></script>");
        out.println("<script src = 'js/form.js'></script>");
        out.println("<script>");
        
        out.println("$(document).ready(function() {");
            out.println("$('#addToCart').click(function() {");               
                    out.println("var pid = $('#pid').val();");
                    out.println("var quantity = $('#quantity option:selected').text();");
                    out.println("var name = $('#name').val();");
                    out.println("var price = $('#price').val();");
                    out.println("$.ajax({");
                    out.println("type: 'POST',");
                    out.println("data: {");
                        out.println("pid: pid,");
                        out.println("quantity: quantity,");
                        out.println("name: name,");
                        out.println("price: price,");
                        out.println("actionType: 'addToCart'");
                    out.println("},");
                    out.println("url: 'CartSession',");
                    out.println("success: function(data){");
//                        $("#result").html(data);
                        out.println("alert('Item added to cart');");
                    out.println("},");
                    out.println("error: function(){");
                        out.println("alert('Error');");
                    out.println("}");
                out.println("});");
//                

                
            out.println("});");
        out.println("});");
    out.println("</script>");
out.println("</head>");

out.println("<body id='body'>");
    
    out.println("<nav class='navbar navbar-expand-lg fixed-top'>");
        out.println("<div class='container'>");
            out.println("<a class='navbar-brand' href='index.html'>");
                out.println("<img src='assets/Nuance9-Logo.png' alt='Nuance 9 Logo'>");
            out.println("</a>");
            out.println("<div class='nav-bar-links-container'>");
                out.println("<div class='topnav' id='nav-bar-tabs'>");
                    out.println("<a href='index.html'>Apparel</a>");
                    out.println("<a href='about.html'>About</a>");
                out.println("</div>");
            out.println("</div>");
        out.println("</div>");
        out.println("</div>");
    out.println("</nav>");
 

    out.println("<div class='Product'>");
        out.println("<div class='row'>");
            out.println("<div class='col-md-5'>");
//                    
                    out.println("<div id='carouselExampleControls' class='carousel slide' data-ride='carousel'>");
                        out.println("<div class='carousel-inner'>");
                          out.println("<div class='carousel-item active'>");
                            out.println("<img class='d-block w-100' id='first-image' alt='First slide'>");
                          out.println("</div>");
                          out.println("<div class='carousel-item'>");
                            out.println("<img class='d-block w-100' id='second-image' alt='Second slide'>");
                          out.println("</div>");
                          out.println("<div class='carousel-item'>");
                            out.println("<img class='d-block w-100' id='third-image' alt='Third slide'>");
                          out.println("</div>");
                        out.println("</div>");
                        out.println("<a class='carousel-control-prev' href='#carouselExampleControls' role='button' data-slide='prev'>");
                          out.println("<span class='carousel-control-prev-icon' aria-hidden='true'></span>");
                          out.println("<span class='sr-only'>Previous</span>");
                        out.println("</a>");
                        out.println("<a class='carousel-control-next' href='#carouselExampleControls' role='button' data-slide='next'>");
                          out.println("<span class='carousel-control-next-icon' aria-hidden='true'></span>");
                          out.println("<span class='sr-only'>Next</span>");
                        out.println("</a>");
                      out.println("</div>");   
//                    
//                              
            out.println("</div>");
            out.println("<div class='col-md-7'>");
                out.println("<h2 id='name'></h2>");
                out.println("<p id='prd-color'></p>");
                out.println("<p id='description'></p>");
                out.println("<img src ='assets/5-star-png-12.png' class='rating'>");
                out.println("<p id = 'pid'></p>");
                out.println("<p class='price' id='price'></p>");
//        
                out.println("<label for='size'>Select a size</label>");
                out.println("<select id='sizes'>");
                    out.println("<option value='Small'>Small</option>");
                    out.println("<option value='Medium>Medium</option>");
                    out.println("<option value='large'>Large</option>");
                out.println("</select>");
//          
                out.println("<br><label for='quantity'>Select quantity</label>");
                out.println("<select id='quantity' name = 'quantity'>");
                    out.println("<option value='1'>1</option>");
                    out.println("<option value='2/>2</option>");
                    out.println("<option value='3'>3</option>");
                    out.println("<option value='4'>4</option>");
                    out.println("<option value='5'>5</option>");
                    out.println("<option value='6'>6</option>");
                    out.println("<option value='7'>7</option>");
//                    
//                    
//                    
               out.println("</select>");
//                
                out.println("<p><b>Brand:</b>Nuance9</p>");
//
//                

                 out.println("<button class='btn btn-default cart' id='addToCart'>");
                    out.println("Add to cart");
                 out.println("</button>");
                


            out.println("</div>");
            
            out.println("<p id='result'></p>");

        out.println("</div>");
          

    out.println("</div>");
    out.println("</body>");

       

















    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
//        processRequest(request, response);
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        if(session.getAttribute("prod") == null){
            String productID = request.getParameter("prod");
            
            session.setAttribute("prod", productID);
        }
        else{
            String productID = request.getParameter("prod");
            
            session.setAttribute("prod", productID);
                           
        }

        out.println("hello");
        //out.println(session.getAttribute("prod"));

        response.sendRedirect(request.getContextPath() + "/product-page.html");
      
    }


    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
