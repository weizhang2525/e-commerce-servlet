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

import javax.json.JsonObject;
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
public class UserSessionTrackingServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UserSessionTrackingServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserSessionTrackingServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        // out.println("TEST - T - 0");

        try {
            // out.println("TEST - T - 1");
            if (session.getAttribute("recentlyVisited") == null) {
                // out.println("TEST - T - 2");
                JSONArray recentlyVisitedEmpty = new JSONArray(); // FOR PROD
                // String[] recentlyVisitedEmpty = new String[] {"1A"}; // FOR TESTING
                session.setAttribute("recentlyVisited", recentlyVisitedEmpty);
            } else {
                // out.println("TEST - T - 3");
                // String[] recentlyVisited = (String[]) session.getAttribute("recentlyVisited");

                // JSONArray recentlyVisitedList = new JSONArray();
                // recentlyVisitedList.put(1, recentlyVisited[0]);

                // out.println(recentlyVisitedList);

                // out.println("recentlyVisited.length: " + recentlyVisited.length);

            }
        } catch (Exception e) {
            out.println("[ERROR - T]: " + e.toString());
            e.printStackTrace();
        }

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
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        // String product = request.getParameter("product");

        // out.println("TEST - T - P - 0");
        String pid = request.getParameter("pid");
        // out.println(pid);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Nuance9?useSSL=false&serverTimezone=PST", "test", "test");
            Statement myStmt = myConn.createStatement();
            ResultSet myResult = myStmt.executeQuery("SELECT * from products WHERE pid='" + pid + "'");

            // Make the object from the SQL data
            JSONObject newProduct = getNewProductFromSQLResult(myResult);

            JSONArray recentlyVisited = (JSONArray) session.getAttribute("recentlyVisited");
            
            if (addProductToRecentlyVisited(newProduct, recentlyVisited)) {
                if (recentlyVisited.length() == 5) {
                    recentlyVisited.remove(0);
                    recentlyVisited.put(newProduct);
                } else {
                    recentlyVisited.put(newProduct);
                }
                session.setAttribute("recentlyVisited", recentlyVisited);
            }
        } catch (Exception e) {
            out.println("[ERROR - T - P]: " + e.toString());
            e.printStackTrace();
        }   
    }

    JSONObject getNewProductFromSQLResult(ResultSet myResult) {
        JSONObject newProduct = new JSONObject();
        try {
            myResult.next(); // move the pointer to the object
            newProduct.put("pid", myResult.getString("pid"));
            newProduct.put("pname", myResult.getString("pname"));
            newProduct.put("price", myResult.getString("price"));
            newProduct.put("desc", myResult.getString("desc"));
            newProduct.put("quantity", myResult.getString("quantity"));
            newProduct.put("srcOne", myResult.getString("srcOne"));
            newProduct.put("srcTwo", myResult.getString("srcTwo"));
            newProduct.put("srcThree", myResult.getString("srcThree"));
            newProduct.put("alt", myResult.getString("alt"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newProduct;
    }

    Boolean addProductToRecentlyVisited(JSONObject newProduct, JSONArray recentlyVisited) {
        Boolean result = true;
        for (int i = 0; i < recentlyVisited.length(); i++) {
            JSONObject tempProduct = (JSONObject) recentlyVisited.get(i);
            if (tempProduct.similar(newProduct)) {
                result = false;
            }
        }
        return result;
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
