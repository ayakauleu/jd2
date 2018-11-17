package servlet;

import dao.PaymentDaoImpl;
import dto.PaymentFilterDto;
import model.Currency;
import model.Payment;
import model.PaymentType;
import util.JspPathUtil;

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

        LocalDate initialDateFrom = LocalDate.now().minusMonths(12);
        LocalDate initialDateTill = LocalDate.now();

        List<Currency> currencies = Arrays.asList(Currency.values());
        req.setAttribute("currencies", currencies);
        List<PaymentType> types = Arrays.asList(PaymentType.values());
        req.setAttribute("PaymentType", types);
        req.setAttribute("datefrom", initialDateFrom);
        req.setAttribute("datetill", initialDateTill);
        req.setAttribute("limit", 100);
        req.setAttribute("offset", 1);

        List<Payment> payments = PaymentDaoImpl.getInstance().findAll();
        req.setAttribute("payments", payments);

        getServletContext()
                .getRequestDispatcher(JspPathUtil.get("payments"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PaymentFilterDto dto = PaymentFilterDto.builder()
                .currency(Currency.valueOf(req.getParameter("currency")))
                //              .paymentType(req.getParameter("type"))
                .dateFrom(LocalDate.parse(req.getParameter("datefrom")))
                .dateTill(LocalDate.parse(req.getParameter("datetill")))
                .limit(Integer.valueOf(req.getParameter("limit")))
                .offset(Integer.valueOf(req.getParameter("offset")))
                .build();
        List<Payment> payments = PaymentDaoImpl.getInstance().findFiltered(dto);
        req.setAttribute("payments", payments);

        List<Currency> currencies = Arrays.asList(Currency.values());
        req.setAttribute("currencies", currencies);
        List<PaymentType> types = Arrays.asList(PaymentType.values());
        req.setAttribute("PaymentType", types);
        req.setAttribute("datefrom", dto.getDateFrom());
        req.setAttribute("datetill", dto.getDateTill());
        req.setAttribute("limit", dto.getLimit());
        req.setAttribute("offset", dto.getOffset());

        getServletContext()
                .getRequestDispatcher(JspPathUtil.get("payments"))
                .forward(req, resp);
    }

}
