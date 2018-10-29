package servlet;

import model.Business;
import service.BusinessService;
import util.JspPathUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/business-info")
public class BusinessInfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Business business = BusinessService.getInstance().getById(Long.valueOf(req.getParameter("id")));
        req.setAttribute("business", business);
        System.out.println(business);
        getServletContext()
                .getRequestDispatcher(JspPathUtil.get("business-info"))
                .forward(req, resp);
    }
}
