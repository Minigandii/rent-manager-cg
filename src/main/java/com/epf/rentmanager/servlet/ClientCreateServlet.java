package com.epf.rentmanager.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.server.ServerErrorException;


@WebServlet(name = "ClientCreateServlet", urlPatterns = "/users/create")
public class ClientCreateServlet extends HttpServlet {
    @Autowired
    private ClientService clientService;


    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        System.out.println("My servlet has been initialized");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Client> clientList = clientService.findAll();
            request.setAttribute("users", clientList);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        request.getServletContext().getRequestDispatcher("/WEB-INF/views/users/create.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        System.out.println(request.getParameter("birthdate"));
        Client client = new Client(Integer.parseInt(request.getParameter("id")),request.getParameter("nom"),request.getParameter("prenom"),request.getParameter("email"),LocalDate.parse(request.getParameter("naissance"), formatter));
        System.out.println(client);
        try {
            clientService.create(client);
        } catch (ServerErrorException | ServiceException e) {
            e.printStackTrace();
        }
        //doGet(request, response);
        response.sendRedirect("/rentmanager/users");
    }
}
