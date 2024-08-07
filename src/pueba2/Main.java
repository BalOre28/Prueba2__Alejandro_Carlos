/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pueba2;

/**
 *
 * @author Balto
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class Main extends JFrame {

    private PalindromoAir avion;
    private JTextArea displayArea;

    public Main() {
        avion = new PalindromoAir();
        setupGUI();
    }

    private void setupGUI() {
        setTitle("Palindromo Air");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(5, 1));

        JButton sellTicketButton = new JButton("Vender Ticket");
        sellTicketButton.setBackground(Color.BLUE);
        sellTicketButton.setForeground(Color.WHITE);
        sellTicketButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog("Ingrese el nombre del pasajero:");
                if (name != null && !name.trim().isEmpty()) {
                    avion.sellTicket(name.trim());
                    updateDisplay();
                } else {
                    JOptionPane.showMessageDialog(null, "Nombre inválido.");
                }
            }
        });
        controlPanel.add(sellTicketButton);

        JButton cancelTicketButton = new JButton("Cancelar Ticket");
        cancelTicketButton.setBackground(Color.RED);
        cancelTicketButton.setForeground(Color.WHITE);
        cancelTicketButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog("Ingrese el nombre del pasajero a cancelar:");
                if (name != null && !name.trim().isEmpty()) {
                    boolean result = avion.cancelTicket(name.trim());
                    if (result) {
                        JOptionPane.showMessageDialog(null, "Ticket cancelado.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Pasajero no encontrado.");
                    }
                    updateDisplay();
                } else {
                    JOptionPane.showMessageDialog(null, "Nombre inválido.");
                }
            }
        });
        controlPanel.add(cancelTicketButton);

        JButton dispatchButton = new JButton("Despachar Avión");
        dispatchButton.setBackground(Color.YELLOW);
        dispatchButton.setForeground(Color.BLACK);
        dispatchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double totalIncome = avion.income();
                if (totalIncome > 0) {
                    JOptionPane.showMessageDialog(null, "Ingresos totales: $" + totalIncome);
                    avion.dispatch();
                    updateDisplay();
                } else {
                    JOptionPane.showMessageDialog(null, "No hay boletos vendidos.");
                }
            }
        });
        controlPanel.add(dispatchButton);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setBackground(Color.BLACK);
        displayArea.setForeground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        add(controlPanel, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    private void updateDisplay() {
        displayArea.setText("");
        displayArea.append("Pasajeros actuales:\n");
        PrintStream printStream = new PrintStream(new JTextAreaOutputStream(displayArea));
        PrintStream oldOut = System.out;
        System.setOut(printStream);
        avion.printPassengers();
        System.setOut(oldOut);
    }

    public static void main(String[] args) {
      new Main();
    }
}

class JTextAreaOutputStream extends OutputStream {

    private JTextArea textArea;

    public JTextAreaOutputStream(JTextArea textArea) {
        this.textArea = textArea;
    }

    public void write(int b) throws IOException {
        textArea.append(String.valueOf((char) b));
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }

    public void write(byte[] b, int off, int len) throws IOException {
        textArea.append(new String(b, off, len));
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }

    public void write(byte[] b) throws IOException {
        textArea.append(new String(b));
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }
}