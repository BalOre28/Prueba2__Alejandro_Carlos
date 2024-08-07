/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pueba2;

/**
 *
 * @author Balto
 */
import javax.swing.*;

public class Ticket {

    private String nombrePasajero;
    private double totalPagado;

    public Ticket(String nombrePasajero, double totalPagado) {
        this.nombrePasajero = nombrePasajero;
        this.totalPagado = totalPagado;
    }

    public String getNombrePasajero() {
        return nombrePasajero;
    }

    public double getTotalPagado() {
        return totalPagado;
    }

    public void print() {
        System.out.println("Nombre del pasajero: " + nombrePasajero);
        System.out.println("Total pagado: $" + totalPagado);
    }
}
