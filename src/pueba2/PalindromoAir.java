/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pueba2;

/**
 *
 * @author Balto
 */

public class PalindromoAir {

    private Ticket[] asientos;

    public PalindromoAir() {
        asientos = new Ticket[30];
    }

    public int firstAvailable() {
        return firstAvailableHelper(0);
    }

    private int firstAvailableHelper(int index) {
        if (index >= asientos.length) {
            return -1;
        }
        if (asientos[index] == null) {
            return index;
        }
        return firstAvailableHelper(index + 1);
    }

    public int searchPassenger(String name) {
        return searchPassengerHelper(name, 0);
    }

    private int searchPassengerHelper(String name, int index) {
        if (index >= asientos.length) {
            return -1;
        }
        if (asientos[index] != null && asientos[index].getNombrePasajero().equals(name)) {
            return index;
        }
        return searchPassengerHelper(name, index + 1);
    }

    public boolean isPalindromo(String name) {
        return isPalindromoHelper(name, 0, name.length() - 1);
    }

    private boolean isPalindromoHelper(String name, int left, int right) {
        if (left >= right) {
            return true;
        }
        if (name.charAt(left) != name.charAt(right)) {
            return false;
        }
        return isPalindromoHelper(name, left + 1, right - 1);
    }

    public void printPassengers() {
        printPassengersHelper(0);
    }

    private void printPassengersHelper(int index) {
        if (index >= asientos.length) {
            return;
        }
        if (asientos[index] != null) {
            asientos[index].print();
        }
        printPassengersHelper(index + 1);
    }

    public double income() {
        return incomeHelper(0);
    }

    private double incomeHelper(int index) {
        if (index >= asientos.length) {
            return 0;
        }
        double ingresoActual = (asientos[index] != null) ? asientos[index].getTotalPagado() : 0;
        return ingresoActual + incomeHelper(index + 1);
    }

    public void reset() {
        resetHelper(0);
    }

    private void resetHelper(int index) {
        if (index >= asientos.length) {
            return;
        }
        asientos[index] = null;
        resetHelper(index + 1);
    }

    public void sellTicket(String name) {
        int disponible = firstAvailable();
        if (disponible != -1) {
            double precio = 800;
            if (isPalindromo(name)) {
                precio *= 0.8;
            }
            asientos[disponible] = new Ticket(name, precio);
            System.out.println("Ticket vendido a " + name + " por $" + precio);
        } else {
            System.out.println("No hay asientos disponibles.");
        }
    }

    public boolean cancelTicket(String name) {
        int posicion = searchPassenger(name);
        if (posicion != -1) {
            asientos[posicion] = null;
            System.out.println("Ticket de " + name + " cancelado.");
            return true;
        }
        System.out.println("Pasajero " + name + " no encontrado.");
        return false;
    }

    public void dispatch() {
        double totalIngresos = income();
        System.out.println("Ingreso total: $" + totalIngresos);
        reset();
        System.out.println("Avión despachado y asientos reseteados.");
    }
}
