package com.ml.demo;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

@WebServlet(name = "login", value = "/login")
public class LoginJ extends HttpServlet {
    private String message;

    public void init() {
        System.out.println("LoginJ is initialized . whhoop");
        message = "<!doctype html> <html> <head> <meta charset=\"UTF-8\"> <title>" + message +  "</title> </head> <body> \n" +
                "            <div id=\"login-square\" style=\"background-color: lightgray; width: 300px; height: 300px; margin: 0 auto; padding: 50px\" > \n" +
                "            <h3> Log-In to our Webshop Susi wants to Chat! </h3>\n" +
                "            <form style=\"padding: 20px\" method=\"post\">\n" +
                "                <input type=\"text\" placeholder=\"Username\" id=\"cred-user\" name=\"cred-user\" <br>\n" +
                "                <input type=\"password\" placeholder=\"Password\" id=\"cred-password\" name=\"cred-password\"> <br>\n" +
                "                <input type=\"submit\" id=\"login-button\" value=\"login\">\n" +
                "            </form>" +
                "            </div>\n" +
                "            </body>\n" +
                "            </html>";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String email = request.getParameter("cred-user");
        String pass = request.getParameter("cred-password");
        System.out.println("We arrived at the login page and got credentials");


        if(Validate.checkUser(email, pass))
        {
            response.sendRedirect("/webshop");
        }
        else
        {
            out.println("Username or Password incorrect");
            RequestDispatcher rs = request.getRequestDispatcher("index.html");
            rs.include(request, response);
        }
    }


    public void destroy() {
    }
}