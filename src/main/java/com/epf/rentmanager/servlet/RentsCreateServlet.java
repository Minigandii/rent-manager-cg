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
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

@WebServlet(name = "RentsCreateServlet", urlPatterns = "/rents/create")
public class RentsCreateServlet extends HttpServlet {
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private ReservationService reservationService;

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        System.out.println("My servlet has been initialized");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Vehicle> vehicles = vehicleService.findAll();
            List<Client> clients = clientService.findAll();
            request.setAttribute("vehicules", vehicles);
            request.setAttribute("clients", clients);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        request.getServletContext().getRequestDispatcher("/WEB-INF/views/rents/create.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        Reservation reservation = null;
        try {
            reservation = new Reservation(Integer.parseInt(request.getParameter("id")),
                    clientService.findById(Integer.parseInt(request.getParameter("client"))),
                    vehicleService.findById(Integer.parseInt(request.getParameter("vehicle"))),
                    LocalDate.parse(request.getParameter("dateDebut"), formatter),
                    LocalDate.parse(request.getParameter("dateFin"), formatter));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        try {
            reservationService.create(reservation);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        response.sendRedirect(request.getContextPath() + "/rents");
    }
}
