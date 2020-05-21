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

/**
 *
 * @author weizhang
 */
public class CartSession extends HttpServlet {

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
            out.println("<title>Servlet CartSession</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CartSession at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
//        out.println(request.getParameter("pid"));
//        out.println(request.getParameter("price"));
        if(request.getParameter("actionType").equals("addToCart")){
//            out.println("hi");
//            String name = request.getParameter("name");
//            String pid = request.getParameter("pid");
//            double price = Double.valueOf(request.getParameter("price"));
            int quantity = Integer.valueOf(request.getParameter("quantity"));
            Product p1 = new Product("1A", 12.50, "Hello");
////            Product p = new Product(pid, price, name);
            addToCart(request, response, p1, quantity);
        }
        else{
            out.println("hmm");
        }
        HttpSession session = request.getSession();
        out.println(session.getAttribute("cart"));
        out.println();
        
        
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
    
    
    //reference: http://learningprogramming.net/java/jsp-servlet/shopping-cart-with-session-in-jsp-servlet/
    public void addToCart(HttpServletRequest request, HttpServletResponse response, Product product, int quantity) throws IOException{
        //cart data struction: hashmap
        //Cart: {Product: Quantity}
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        if(session.getAttribute("cart") == null){
            Map<Product, Integer> cart = new HashMap<Product, Integer>();
            cart.put(product, quantity);
            session.setAttribute("cart", cart);
        }
        else{
            Map<Product, Integer> cart = (Map<Product, Integer>)session.getAttribute("cart");
//            Iterator cartIterator = cart.entrySet().iterator();
//            while(cartIterator.hasNext()){
//                Map.Entry data = (Map.Entry)cartIterator.next();
//                out.println(data.getKey());
//                Product p = (Product)data.getKey();
//                out.println(p.getPid().equals(product.getPid()));
//                out.println(data.getValue());
//            }
            isProductInCart(product, cart, quantity, request, response );                
        }
    }
    
    //adds product to cart if does not exist, update if it does
    public void isProductInCart(Product product, Map<Product, Integer> cart, int quantity, HttpServletRequest request, HttpServletResponse response) throws IOException{
        HttpSession session = request.getSession();
        boolean inCart = false;
        PrintWriter out = response.getWriter();
        Iterator cartIterator = cart.entrySet().iterator();
        while(cartIterator.hasNext()){
                Map.Entry data = (Map.Entry)cartIterator.next();
                Product p = (Product)data.getKey();
                if(p.getPid().equals(product.getPid())){
                    int updatedQuantity = Integer.valueOf(cart.get(p));
                    inCart = true;
                    updatedQuantity += quantity;
                    cart.replace(p, updatedQuantity);
                    out.println(Integer.valueOf(cart.get(p)));
                    session.setAttribute("cart", cart);
                }
         }
        if(!inCart){
            cart.put(product, quantity);
            session.setAttribute("cart", cart);
        }
    }

}
