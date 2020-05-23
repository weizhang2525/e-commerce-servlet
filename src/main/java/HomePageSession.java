/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
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
 * @author Robert Xavier Maldonado
 */
public class HomePageSession extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet HomePageSession</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HomePageSession at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        // [12:24 PM, 5/21/2020] Wei Zhang: price, pid, and name are missing from the pages rn when you click on the product
        // [12:24 PM, 5/21/2020] Wei Zhang: but after you do your stuff, it should be good
        // [12:25 PM, 5/21/2020] Wei Zhang: Include the output of two servlets to create the homepage for your e-commerce site: the first servlet should handle the displaying of the list of products obtained from a backend database, and the second servlet should use session tracking to display the last 5 products that the user has visited (viewed the product details page). In case this number is less than 5, show whatever amount of information you have stored in the session. You are required to use servlet "include" feature to implement this requirement.
        // [12:25 PM, 5/21/2020] Wei Zhang: for the second one, you wants you to use session to save the items the user has saved
        // [12:26 PM, 5/21/2020] Wei Zhang: but if you do some research, its not too bad to understand

        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        // out.println("TEST S - 1");
        
        try {
            // 0. Check for driver
            // out.println("TEST S - 2");
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 1. Get a connectiong to database
            // out.println("TEST S - 3");
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Nuance9?autoReconnect=true&useSSL=false", "test", "test");

            // 2. Create a statement3
            // out.println("TEST S - 4");
            Statement myStmt = myConn.createStatement();

            // 3. Execute SQL Query
            // out.println("TEST S - 5");
            ResultSet myResult = myStmt.executeQuery("SELECT * from products");

            // 4. Process result set
            // out.println("TEST S - 6");
            JSONArray productList = new JSONArray();
            while(myResult.next()) {
                JSONObject newProduct = new JSONObject();

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
            out.println(productList);

        } catch (Exception e) {
            out.println("[ERROR]: " + e.toString());
            // out.println("TEST S - 2");
            e.printStackTrace();
        }






        // try {
        //     out.println("TEST S - 1");

        //     // connection = startServer(response);
        //     Class.forName("com.mysql.cj.jdbc.Driver");
        //     out.println("TEST S - 2");
        //     connection = DriverManager.getConnection("jdbc:mysql://localhost/nuance9", "root", "password");
        //     out.println("TEST S - 3");
        //     statement = connection.createStatement();
        //     out.println("TEST S - 4");
        //     sqlSelectStatement = "SELECT * FROM products;";
        //     ResultSet sqlResult = statement.executeQuery(sqlSelectStatement);
        //     // out.println(sqlResult);
        //     out.println("TEST S - 5");
        // } catch (ClassNotFoundException e) {
        //     e.printStackTrace();
        // } catch (SQLException e) {
        //     e.printStackTrace();
        // }

        // out.println("TEST S - 2");
    }

    public Connection startServer(HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter(); 
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/nuance9", "user", "test123");
        }
        catch (Exception e){
            out.println(e);
        }
        return con;
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {     
        processRequest(request, response);
        System.out.print("TEST INSIDE SERVLET WORKS");    
    }

//     /**
//      * Returns a short description of the servlet.
//      *
//      * @return a String containing servlet description
//      */
//     @Override
//     public String getServletInfo() {
//         return "Short description";
//     }// </editor-fold>
    
}
