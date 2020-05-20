/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author weizhang
 */
public class FormSubmission extends HttpServlet {

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
            out.println("<title>Servlet FormSubmission</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FormSubmission at " + request.getContextPath() + "</h1>");
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
//        processRequest(request, response);
        
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
//        processRequest(request, response);
        
        PrintWriter out = response.getWriter();
        
        Map<String, String> formData = new HashMap<String, String>();
        
        Enumeration paraNames = request.getParameterNames();
        
        while(paraNames.hasMoreElements()){
           String paramName = (String)paraNames.nextElement();
           out.println(paramName);
           String paramValues = request.getParameterValues(paramName)[0];
           
           out.println(paramValues);
           
           formData.put(paramName, paramValues);
        }
        
        
        
        
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
    
    
    public Connection startServer(){
        try{
        Class.forName("org.gjt.mm.mysql.Driver");
        }
        catch( ClassNotFoundException e){
            
        }
        
        Connection con = null;
        
        try{
            con = DriverManager.getConnection("mysql://localhost:8080/Nuance9/", "user", "test123");
            
        }
        catch (SQLException e){
            
        }
       
        return con;
    }
    
    public void insertOrder(int cid, String ccnum, String pid, int quantity, float total, Connection con){
        String sql = "INSERT INTO orders (cid, ccnum, pid, quantity, order_date, total) VALUES (?, ?, ?, ?, NOW(), ?)";
        try{
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, cid);
            preparedStatement.setString(2, ccnum);
            preparedStatement.setString(3, pid);
            preparedStatement.setInt(4, quantity);
            preparedStatement.setFloat(6, total);
            preparedStatement.executeUpdate();
            
             //close all connections
            preparedStatement.close();
        }
        catch(SQLException e){
            System.out.println("Error while inserting into order table");
        }
    }
    
    public int insertCustomer(String fname, String lname, String email, String phone, String street_address, String city, String us_state, String zip, Connection con){
        String sql = "INSERT INTO customers (fname, lname, email, phone, street_address, city, us_state, zip) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        int cid = 0;
        try{
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, fname);
            preparedStatement.setString(2, lname);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, phone);
            preparedStatement.setString(5, street_address);
            preparedStatement.setString(6, city);
            preparedStatement.setString(7, us_state);
            preparedStatement.setString(8, zip);
            preparedStatement.executeUpdate();
            
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if(rs.next()){
                cid = Integer.parseInt(rs.getString(1));
            }
            
            //close all connections
            preparedStatement.close();

        }
        catch(SQLException e){
            System.out.println("Error while inserting into credit card table");
        }
        return cid;
    }
    
    public void insertCC(int cid, String ccnum, String cvv, String expiration, Connection con){
        String sql = "INSERT INTO creditcards (cid, ccnum, cvv, expiration) VALUES (?, ?, ?, ?)";
        try{
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, cid);
            preparedStatement.setString(2, ccnum);
            preparedStatement.setString(3, cvv);
            preparedStatement.setString(4, expiration);
            preparedStatement.executeUpdate();
            
            //close all connections
            preparedStatement.close();

        }
        catch(SQLException e){
            System.out.println("Error while inserting into credit card table");
        }
    }
    
    
}
