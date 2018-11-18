package servlet;

import database.config.PersistenceConfig;
import database.model.Currency;
import database.model.Payment;
import database.model.PaymentType;
import database.repository.PaymentRepository;
import database.util.JspPathUtil;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.PageRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@WebServlet("/payments")
public class PaymentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LocalDate initialDateFrom = LocalDate.now().minusYears(1);
        LocalDate initialDateTill = LocalDate.now();

        List<Currency> currencies = Arrays.asList(Currency.values());
        req.setAttribute("currencies", currencies);
        List<PaymentType> types = Arrays.asList(PaymentType.values());
        req.setAttribute("PaymentType", types);
        req.setAttribute("datefrom", initialDateFrom);
        req.setAttribute("datetill", initialDateTill);
        req.setAttribute("limit", 100);
        req.setAttribute("offset", 1);

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PersistenceConfig.class);
        PaymentRepository bean = context.getBean(PaymentRepository.class);
        List<Payment> payments = bean.findAll();
        req.setAttribute("payments", payments);

        getServletContext()
                .getRequestDispatcher(JspPathUtil.get("payments"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PersistenceConfig.class);
        PaymentRepository bean = context.getBean(PaymentRepository.class);

        List<Payment> payments = bean.findByCurrencyAndPaymentDateBetween(
                Currency.valueOf(req.getParameter("currency")),
                LocalDate.parse(req.getParameter("datefrom")),
                LocalDate.parse(req.getParameter("datetill")),
                PageRequest.of(Integer.valueOf(req.getParameter("offset")),
                        Integer.valueOf(req.getParameter("limit"))));


        req.setAttribute("payments", payments);

        List<Currency> currencies = Arrays.asList(Currency.values());
        req.setAttribute("currencies", currencies);
        List<PaymentType> types = Arrays.asList(PaymentType.values());
        req.setAttribute("PaymentType", types);
        req.setAttribute("datefrom", req.getParameter("datefrom"));
        req.setAttribute("datetill", req.getParameter("datetill"));
        req.setAttribute("limit", req.getParameter("limit"));
        req.setAttribute("offset", req.getParameter("offset"));

        getServletContext()
                .getRequestDispatcher(JspPathUtil.get("payments"))
                .forward(req, resp);
    }

}
