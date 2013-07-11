/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Clouduser;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Enumeration;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.ClouduserFacade;
import session.ZeonuserFacade;

/**
 *
 * @author fyntom
 */
@WebServlet(name = "webctrl", loadOnStartup = 1, urlPatterns = {"/signin", "/upload"})
public class webctrl extends HttpServlet {

    @EJB
    ClouduserFacade cloudUserFacade;
    @EJB
    ZeonuserFacade zeonUserFacade;

    @Override
    public void init() throws ServletException {
        Long test = cloudUserFacade.sumFreespace();

        test = test / 1024 / 1024 / 1024;
        getServletContext().setAttribute("freespace", test);
    }

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String userPath = request.getServletPath();
        if ("/signin".equals(userPath)) {
            // TODO: обработка запроса статьи
            String email = null;
            String pass = null;
            String pass2 = null;
            String type = null;
            Enumeration<String> params = request.getParameterNames();
            while (params.hasMoreElements()) {
                String param = params.nextElement();
                email = "email".equals(param) ? request.getParameter(param) : email;
                pass = "password".equals(param) ? request.getParameter(param) : pass;
                pass2 = "password2".equals(param) ? request.getParameter(param) : pass2;
                type = "reg".equals(param) ? "reg" : "signin";
            }

            if (type.equals("reg")) {
                int codeOperation = zeonUserFacade.addUser(email, pass, pass2);

                if (codeOperation ==2) {
                    request.setAttribute("notif", "Пароли не совпадают или вы забыли про E-mail" );
                } 
                if (codeOperation ==3) {
                    request.setAttribute("notif", "Пользователь с e-mail "+email+" уже зарегестрирован" );
                } 
                if (codeOperation == 0) {
                    request.setAttribute("notif", "Пользователь " + email + " успешно создан!");
                }
            } 
            
            if (type.equals("signin")) {
                request.setAttribute("notif","Заходим под пользователем " + email + "#" + pass);
            }
        } else if ("/upload".equals(userPath)) {
            //TODO: обработка запроса регистрации
        }

        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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
