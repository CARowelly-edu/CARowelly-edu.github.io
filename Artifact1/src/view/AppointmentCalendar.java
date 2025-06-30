package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class AppointmentCalendar extends JFrame {
    private JLabel dateLabel;
    private JTable appointmentTable;
    private DefaultTableModel tableModel;
    private LocalDate currentDate;
    private Scheduler scheduler;

    public AppointmentCalendar(Scheduler scheduler) {
        this.scheduler = scheduler;
        currentDate = LocalDate.now();

        setTitle("Appointment Calendar");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout setup
        JPanel topPanel = new JPanel(new BorderLayout());
        JButton prevButton = new JButton("< Previous");
        JButton nextButton = new JButton("Next >");
        dateLabel = new JLabel(currentDate.toString(), SwingConstants.CENTER);

        prevButton.addActionListener(e -> changeDate(-1));
        nextButton.addActionListener(e -> changeDate(1));

        topPanel.add(prevButton, BorderLayout.WEST);
        topPanel.add(dateLabel, BorderLayout.CENTER);
        topPanel.add(nextButton, BorderLayout.EAST);

        // Appointment Table
        tableModel = new DefaultTableModel(new Object[]{"Time", "Status"}, 0);
        appointmentTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(appointmentTable);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        refreshTable();
        setVisible(true);
    }

    private void changeDate(int days) {
        currentDate = currentDate.plusDays(days);
        dateLabel.setText(currentDate.toString());
        refreshTable();
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        List<Appointment> appointments = scheduler.getAppointmentsForDate(currentDate);
        for (Appointment appointment : appointments) {
            tableModel.addRow(new Object[]{appointment.getTimeSlot(), appointment.getStatus()});
        }
    }

    // Demo main method
    public static void main(String[] args) {
        Scheduler mockScheduler = new Scheduler();
        new AppointmentCalendar(mockScheduler);
    }

    // Mock Appointment class
    static class Appointment {
        private String timeSlot;
        private String status;

        public Appointment(String timeSlot, String status) {
            this.timeSlot = timeSlot;
            this.status = status;
        }

        public String getTimeSlot() {
            return timeSlot;
        }

        public String getStatus() {
            return status;
        }
    }

    // Mock Scheduler class
    static class Scheduler {
        public List<Appointment> getAppointmentsForDate(LocalDate date) {
            List<Appointment> appointments = new ArrayList<>();
            appointments.add(new Appointment("9:00 AM", "Available"));
            appointments.add(new Appointment("10:00 AM", "Booked"));
            appointments.add(new Appointment("11:00 AM", "Available"));
            appointments.add(new Appointment("1:00 PM", "Booked"));
            return appointments;
        }
    }
}

