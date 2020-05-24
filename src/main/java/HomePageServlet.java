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
 * @author Robert Xavier Maldonado
 */
public class HomePageServlet extends HttpServlet {

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
            out.println("<title>Servlet HomePageServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HomePageServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        // out.println("TEST S - 1");
        
        try {
            // 0. Check for driver
            // out.println("TEST S - 2");
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 1. Get a connectiong to database
            // out.println("TEST S - 3");
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Nuance9?useSSL=false&serverTimezone=PST", "test", "test");

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

            // out.println("TEST S - 7 - BEFORE");
            String servletUrl = "UserSessionTrackingServlet";
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(servletUrl);
            requestDispatcher.include(request, response);
            // out.println("TEST S - 8 - AFTER");

            JSONArray recentlyVisitedList = (JSONArray) session.getAttribute("recentlyVisited");

            JSONObject homePageResponse = new JSONObject();
            homePageResponse.put("productList", productList);
            homePageResponse.put("recentlyVisitedList", recentlyVisitedList);

            out.println(homePageResponse);

        } catch (Exception e) {
            out.println("[ERROR - S]: " + e.toString());
            // out.println("TEST S - 2");
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
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
