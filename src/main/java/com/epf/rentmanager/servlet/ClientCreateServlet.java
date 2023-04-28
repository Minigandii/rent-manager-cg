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

import com.epf.rentmanager.exception.DaoException;
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
    Client client;


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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String nom=request.getParameter("nom");
            String prenom=request.getParameter("prenom");
            LocalDate naissance = LocalDate.parse(request.getParameter("naissance"));
            String email=request.getParameter("email");
            client = new Client(nom,prenom,email, naissance);
            request.setAttribute("client", clientService.create(client));
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        this.doGet(request,response);
    }
}
