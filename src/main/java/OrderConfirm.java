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
 * @author jonathanlun
 */
public class OrderConfirm extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
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
            out.println("<title>Servlet OrderConfirm</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderConfirm at " + request.getContextPath() + "</h1>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        JSONArray cartJson = new JSONArray();
        try {
            // 0. Check for driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 1. Get a connectiong to database
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Nuance9?autoReconnect=true&useSSL=false", "test", "test");

            // 2. Create a statement3
            Statement myStmt = myConn.createStatement();

            // 3. Get last customer id
             ResultSet order = myStmt.executeQuery("SELECT * FROM orders WHERE 'cid' = MAX('cid')");
             
            // 4. Get essential info first
            JSONObject orderdate = new JSONObject();
            orderdate.put("date", order.getString("order_date"));

            int customer = order.getInt("cid");
            ResultSet cus = myStmt.executeQuery("SELECT * FROM customers WHERE 'cid' =" + customer);
            JSONObject customerinfo= new JSONObject();
            customerinfo.put("name",cus.getString("fname") + " " + cus.getString("lname"));
            customerinfo.put("email", cus.getString("email"));
            customerinfo.put("phone", cus.getString("phone"));
            customerinfo.put("address", cus.getString("street_address"));
            customerinfo.put("csz", cus.getString("city") + ", " + cus.getString("us_state") + " " + cus.getString("zip"));

            ResultSet cc = myStmt.executeQuery("SELECT * FROM creditcards WHERE 'cid' =" + customer);
            JSONObject creditcard = new JSONObject();
            creditcard.put("ccnum", cc.getString("ccnum"));
            creditcard.put("exp", cc.getString("expiration"));
            
            // 5. Get product set
            float customerTotal = 0;
            while(order.next())
            {
                JSONObject item = new JSONObject();
                customerTotal += order.getInt("total");
                String pid = order.getString("pid");
                ResultSet product = myStmt.executeQuery("SELECT * FROM products WHERE 'pid' =" + pid);
                item.put("name", product.getString("pname"));
                item.put("pid", pid);
                item.put("quantity", order.getInt("quantity"));
            }
            
            //6. Get total
            JSONObject total = new JSONObject();
            total.put("t", customerTotal);
        }
        catch (Exception e) {
            out.println("[ERROR - S]: " + e.toString());
            // out.println("TEST S - 2");
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
