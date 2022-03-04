package demo.web;

import demo.impl.MessageWall_and_RemoteLogin_Impl;
import demo.spec.RemoteLogin;
import demo.spec.UserAccess;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ControllerServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        process(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        process(request, response);
    }

    protected void process(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        
        String view = perform_action(request);
        forwardRequest(request, response, view);
    }

    protected String perform_action(HttpServletRequest request)
        throws IOException, ServletException {
        
        String serv_path = request.getServletPath();
        HttpSession session = request.getSession();
        request.getParameter(serv_path);

        if (serv_path.equals("/login.do")) {
            String user = (String) request.getParameter("user");
            String password = (String) request.getParameter("password");
            
            if (session.getAttribute("useraccess") != null) {
                return "/view/wallview.jsp";
            }
             
            MessageWall_and_RemoteLogin_Impl mes_wall = (MessageWall_and_RemoteLogin_Impl) getServletContext().getAttribute("remoteLogin");
            if (mes_wall.connect(user, password)!=null){
                request.getSession().setAttribute("useraccess", mes_wall.connect(user, password));
                return "/view/wallview.jsp";
            }
            else return "/error-no-user_access.html";
        } 
        
        else if (serv_path.equals("/put.do")) {
            String msg = (String) request.getParameter("msg");
            UserAccess user = (UserAccess)request.getSession().getAttribute("useraccess");
            user.put(msg);
            request.getSession().setAttribute("useraccess",user);
            return "/view/wallview.jsp";

            //return "/error-not-loggedin.html";
        } 
        
        else if (serv_path.equals("/refresh.do")) {
            return "/view/wallview.jsp";
        } 
        
        else if (serv_path.equals("/logout.do")) {
            session.removeAttribute("useraccess");
            return "/goodbye.html";
        }  
        
        else if (serv_path.equals("/delete.do")) {
            int counter = Integer.parseInt(request.getParameter("index"));
            UserAccess userAccess = (UserAccess) session.getAttribute("useraccess");
            //if(userAccess.delete(counter))
                userAccess.delete(counter);
            return "/view/wallview.jsp";
        }
        
        else {
            return "/error-bad-action.html";
        }
    }

    public RemoteLogin getRemoteLogin() {
        return (RemoteLogin) getServletContext().getAttribute("remoteLogin");
    }
    public void forwardRequest(HttpServletRequest request, HttpServletResponse response, String view) 
            throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(view);
        if (dispatcher == null) {
            throw new ServletException("No dispatcher for view path '"+view+"'");
        }
        dispatcher.forward(request,response);
    }
}


