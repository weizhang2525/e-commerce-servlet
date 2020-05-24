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
        HttpSession session = request.getSession();
        Map<Product, Integer> cart = (Map<Product, Integer>)session.getAttribute("cart");
        PrintWriter out = response.getWriter();
        JSONArray cartJson = new JSONArray();
        Iterator cartIterator = cart.entrySet().iterator();
        while(cartIterator.hasNext()){
                Map.Entry data = (Map.Entry)cartIterator.next();
                Product p = (Product)data.getKey();
                JSONObject item = new JSONObject();
                item.put("productID", p.getPid());
                item.put("price", p.getPrice());
                item.put("name", p.getName());
                item.put("quantity", cart.get(p));
                cartJson.put(item);
         }
//        out.println(cart);
        out.println(cartJson);
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
        PrintWriter out = response.getWriter();
        if(request.getParameter("actionType").equals("addToCart")){
//            String name = request.getParameter("name");
//            String pid = request.getParameter("pid");
//            double price = Double.valueOf(request.getParameter("price"));
            int quantity = Integer.valueOf(request.getParameter("quantity"));
            Product p1 = new Product("1A", 12.50, "Hello");
//            Product p = new Product(pid, price, name);
            addToCart(request, response, p1, quantity);
        }
        HttpSession session = request.getSession();        
        
        
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
        HttpSession session = request.getSession();
        if(session.getAttribute("cart") == null){
            Map<Product, Integer> cart = new HashMap<Product, Integer>();
            cart.put(product, quantity);
            session.setAttribute("cart", cart);
            session.setAttribute("cartTotal", product.getPrice()*quantity);
        }
        else{
            isProductInCart(product, quantity, request, response );                
        }
        
    }
    
    //adds product to cart if does not exist, update if it does
    public void isProductInCart(Product product, int quantity, HttpServletRequest request, HttpServletResponse response) throws IOException{
        HttpSession session = request.getSession();
        Map<Product, Integer> cart = (Map<Product, Integer>)session.getAttribute("cart");
        boolean inCart = false;
        double total = 0;
        PrintWriter out = response.getWriter();
        Iterator cartIterator = cart.entrySet().iterator();
        while(cartIterator.hasNext()){
                Map.Entry data = (Map.Entry)cartIterator.next();
                Product p = (Product)data.getKey();
//                out.println(p.getPid());
                if(p.getPid().equals(product.getPid())){
                    int updatedQuantity = Integer.valueOf(cart.get(p));
                    inCart = true;
                    updatedQuantity += quantity;
                    cart.replace(p, updatedQuantity);
//                    out.println(Integer.valueOf(cart.get(p)));
                    
                    session.setAttribute("cart", cart);
                }
                total += p.getPrice()*cart.get(p);                
         }
        if(!inCart){
            cart.put(product, quantity);
            session.setAttribute("cart", cart);
        }
        session.setAttribute("cartTotal", total);
    }
    
    public void viewCart(HttpServletResponse response){
        
    }

}
