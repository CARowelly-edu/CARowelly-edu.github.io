package view;

import controller.AppController;
import model.Appointment;
import model.Contact;
import model.Task;

import javax.swing.*;
import java.awt.*;
import java.time.*;
import java.util.List;

public class AppGUI extends JFrame {
    private final AppController controller;
    private JPanel calendarPanel;
    private JPanel calendarTopBar;
    private boolean isMonthView = true;
    private LocalDate selectedDay = LocalDate.now();
    private LocalDateTime currentMonth = LocalDateTime.now().withDayOfMonth(1);

    public AppGUI(AppController controller) {
        this.controller = controller;

        setTitle("Appointment Scheduler");
        setSize(1800, 900);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Calendar", createCalendarPanel());
        tabbedPane.addTab("Manage Records", createManagementPanel());

        add(tabbedPane);
    }

    private JPanel createManagementPanel() {
        JPanel mainPanel = new JPanel(new GridLayout(3, 1));
        mainPanel.add(createAppointmentSection());
        mainPanel.add(createContactSection());
        mainPanel.add(createTaskSection());
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        mainPanel.add(new JLabel("Management Panel Placeholder"));
        // You can add sections for Appointments, Contacts, and Tasks here

        return mainPanel;
    }
    private JPanel createAppointmentSection() {
        JPanel section = new JPanel(new GridLayout(0, 2, 10, 5));
        section.setBorder(BorderFactory.createTitledBorder("Appointments"));

        JTextField idField = new JTextField();
        JTextField dateField = new JTextField("2025-07-01T15:00");
        JTextField descField = new JTextField();

        section.add(new JLabel("ID:"));
        section.add(idField);
        section.add(new JLabel("Date (yyyy-MM-ddTHH:mm):"));
        section.add(dateField);
        section.add(new JLabel("Description:"));
        section.add(descField);

        JButton add = new JButton("Add");
        JButton delete = new JButton("Delete");
        JButton update = new JButton("Update");

        add.addActionListener(e -> {
            try {
                controller.addAppointment(new Appointment(idField.getText(),
                        LocalDateTime.parse(dateField.getText()), descField.getText()));
                JOptionPane.showMessageDialog(section, "Appointment added");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(section, "Error: " + ex.getMessage());
            }
        });

        delete.addActionListener(e -> {
            boolean success = controller.deleteAppointment(idField.getText());
            JOptionPane.showMessageDialog(section, success ? "Deleted" : "Not found");
        });

        update.addActionListener(e -> {
            Appointment existing = controller.getAppointment(idField.getText());
            if (existing != null) {
                try {
                    Appointment updated = new Appointment(existing.getAppointmentId(),
                            LocalDateTime.parse(dateField.getText()), descField.getText());
                    controller.deleteAppointment(existing.getAppointmentId());
                    controller.addAppointment(updated);
                    JOptionPane.showMessageDialog(section, "Updated");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(section, "Update failed: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(section, "Appointment not found");
            }
        });

        section.add(add); section.add(delete); section.add(update);
        return section;
    }

    private JPanel createContactSection() {
        JPanel section = new JPanel(new GridLayout(0, 2, 10, 5));
        section.setBorder(BorderFactory.createTitledBorder("Contacts"));

        JTextField idField = new JTextField();
        JTextField fNameField = new JTextField();
        JTextField lNameField = new JTextField();
        JTextField addressField = new JTextField();
        JTextField phoneField = new JTextField();

        section.add(new JLabel("ID:"));
        section.add(idField);
        section.add(new JLabel("First Name:"));
        section.add(fNameField);
        section.add(new JLabel("Last Name:"));
        section.add(lNameField);
        section.add(new JLabel("Address:"));
        section.add(addressField);
        section.add(new JLabel("Phone (10 digits):"));
        section.add(phoneField);

        JButton add = new JButton("Add");
        JButton delete = new JButton("Delete");
        JButton update = new JButton("Update");

        add.addActionListener(e -> {
            try {
                controller.addContact(new Contact(idField.getText(), fNameField.getText(),
                        lNameField.getText(), addressField.getText(), phoneField.getText()));
                JOptionPane.showMessageDialog(section, "Contact added");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(section, "Error: " + ex.getMessage());
            }
        });

        delete.addActionListener(e -> {
            boolean success = controller.deleteContact(idField.getText());
            JOptionPane.showMessageDialog(section, success ? "Deleted" : "Not found");
        });

        update.addActionListener(e -> {
            Contact existing = controller.getContact(idField.getText());
            if (existing != null) {
                try {
                    Contact updated = new Contact(existing.getContactId(), fNameField.getText(),
                            lNameField.getText(), addressField.getText(), phoneField.getText());
                    controller.deleteContact(existing.getContactId());
                    controller.addContact(updated);
                    JOptionPane.showMessageDialog(section, "Updated");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(section, "Update failed: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(section, "Contact not found");
            }
        });

        section.add(add); section.add(delete); section.add(update);
        return section;
    }

    private JPanel createTaskSection() {
        JPanel section = new JPanel(new GridLayout(0, 2, 10, 5));
        section.setBorder(BorderFactory.createTitledBorder("Tasks"));

        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField descField = new JTextField();

        section.add(new JLabel("ID:"));
        section.add(idField);
        section.add(new JLabel("Name:"));
        section.add(nameField);
        section.add(new JLabel("Description:"));
        section.add(descField);

        JButton add = new JButton("Add");
        JButton delete = new JButton("Delete");
        JButton update = new JButton("Update");

        add.addActionListener(e -> {
            try {
                controller.addTask(new Task(idField.getText(), nameField.getText(), descField.getText()));
                JOptionPane.showMessageDialog(section, "Task added");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(section, "Error: " + ex.getMessage());
            }
        });

        delete.addActionListener(e -> {
            boolean success = controller.deleteTask(idField.getText());
            JOptionPane.showMessageDialog(section, success ? "Deleted" : "Not found");
        });

        update.addActionListener(e -> {
            Task existing = controller.getTask(idField.getText());
            if (existing != null) {
                try {
                    Task updated = new Task(existing.getTaskId(), nameField.getText(), descField.getText());
                    controller.deleteTask(existing.getTaskId());
                    controller.addTask(updated);
                    JOptionPane.showMessageDialog(section, "Updated");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(section, "Update failed: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(section, "Task not found");
            }
        });

        section.add(add); section.add(delete); section.add(update);
        return section;
    }

    private JPanel createCalendarPanel() {
        calendarPanel = new JPanel(new BorderLayout());

        JButton toggleViewButton = new JButton("Switch to Day View");
        toggleViewButton.addActionListener(e -> {
            isMonthView = !isMonthView;
            toggleViewButton.setText(isMonthView ? "Switch to Day View" : "Switch to Month View");
            refreshCalendarView();
        });

        calendarTopBar = new JPanel(new BorderLayout());
        calendarTopBar.add(toggleViewButton, BorderLayout.EAST);
        calendarPanel.add(calendarTopBar, BorderLayout.NORTH);

        refreshCalendarView();
        return calendarPanel;
    }

    private void refreshCalendarView() {
        calendarPanel.removeAll();
        List<Appointment> allAppointments = controller.getAllAppointments();

        calendarPanel.add(calendarTopBar, BorderLayout.NORTH);

        if (isMonthView) {
            calendarPanel.add(buildMonthView(allAppointments), BorderLayout.CENTER);
        } else {
            calendarPanel.add(buildDayView(allAppointments), BorderLayout.CENTER);
        }

        calendarPanel.revalidate();
        calendarPanel.repaint();
    }

    private JPanel buildMonthView(List<Appointment> allAppointments) {
        JPanel panel = new JPanel(new BorderLayout());

        String monthYear = currentMonth.getMonth().getDisplayName(java.time.format.TextStyle.FULL, java.util.Locale.ENGLISH)
                + " " + currentMonth.getYear();
        JLabel headerLabel = new JLabel(monthYear, SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JButton prevButton = new JButton("\u25C0");
        JButton nextButton = new JButton("\u25B6");

        prevButton.addActionListener(e -> {
            currentMonth = currentMonth.minusMonths(1);
            refreshCalendarView();
        });

        nextButton.addActionListener(e -> {
            currentMonth = currentMonth.plusMonths(1);
            refreshCalendarView();
        });

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.add(prevButton, BorderLayout.WEST);
        headerPanel.add(headerLabel, BorderLayout.CENTER);
        headerPanel.add(nextButton, BorderLayout.EAST);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel daysOfWeekBar = new JPanel(new GridLayout(1, 7));
        String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        for (String day : days) {
            JLabel dayLabel = new JLabel(day, SwingConstants.CENTER);
            dayLabel.setFont(new Font("Arial", Font.BOLD, 14));
            daysOfWeekBar.add(dayLabel);
        }

        JPanel gridWrapper = new JPanel(new BorderLayout());
        gridWrapper.add(daysOfWeekBar, BorderLayout.NORTH);

        JPanel calendarGrid = new JPanel(new GridLayout(5, 7, 5, 5));
        LocalDateTime firstOfMonth = currentMonth;
        int dayOfWeekOffset = firstOfMonth.getDayOfWeek().getValue() % 7;
        LocalDateTime cellDate = firstOfMonth.minusDays(dayOfWeekOffset);

        for (int i = 0; i < 35; i++) {
            LocalDate currentDate = cellDate.toLocalDate();
            JPanel dayCell = new JPanel();
            dayCell.setLayout(new BoxLayout(dayCell, BoxLayout.Y_AXIS));
            dayCell.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

            if (currentDate.equals(LocalDate.now())) {
                dayCell.setBackground(new Color(173, 216, 230));
                dayCell.setOpaque(true);
            }

            JLabel dayLabel = new JLabel(String.valueOf(currentDate.getDayOfMonth()));
            dayLabel.setFont(new Font("Arial", Font.BOLD, 14));
            dayLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            dayCell.add(dayLabel);

            for (Appointment appt : allAppointments) {
                if (appt.getAppointmentDate().toLocalDate().equals(currentDate)) {
                    JLabel banner = new JLabel(appt.getAppointmentDescription());
                    banner.setFont(new Font("Arial", Font.PLAIN, 10));
                    banner.setOpaque(true);
                    banner.setBackground(new Color(135, 206, 250));
                    banner.setMaximumSize(new Dimension(Integer.MAX_VALUE, 15));
                    dayCell.add(banner);
                }
            }

            dayCell.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    selectedDay = currentDate;
                    openAddAppointmentDialog(currentDate.atStartOfDay());
                }
            });

            calendarGrid.add(dayCell);
            cellDate = cellDate.plusDays(1);
        }

        gridWrapper.add(calendarGrid, BorderLayout.CENTER);

        panel.add(headerPanel, BorderLayout.NORTH);
        panel.add(gridWrapper, BorderLayout.CENTER);
        return panel;
    }
    private JPanel buildDayView(List<Appointment> allAppointments) {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel dayLabel = new JLabel("Appointments for " + selectedDay, SwingConstants.CENTER);
        dayLabel.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(dayLabel, BorderLayout.NORTH);

        JPanel daySchedule = new JPanel(new GridLayout(24, 1));
        JScrollPane scrollPane = new JScrollPane(daySchedule);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Determine scroll target hour (now if today, or 9 AM)
        int scrollToHour = selectedDay.equals(LocalDate.now()) ? LocalTime.now().getHour() : 9;

        // Track the vertical position to scroll to
        Component scrollTarget = null;

        for (int hour = 0; hour < 24; hour++) {
            LocalDateTime slot = selectedDay.atTime(hour, 0);
            JPanel hourPanel = new JPanel();
            hourPanel.setLayout(new BoxLayout(hourPanel, BoxLayout.Y_AXIS));
            hourPanel.setBorder(BorderFactory.createTitledBorder(String.format("%02d:00", hour)));

            if (hour == scrollToHour) {
                scrollTarget = hourPanel;
            }

            for (Appointment appt : allAppointments) {
                if (appt.getAppointmentDate().getHour() == hour &&
                    appt.getAppointmentDate().toLocalDate().equals(selectedDay)) {

                    JLabel label = new JLabel(appt.getAppointmentDescription());
                    label.setOpaque(true);
                    label.setBackground(new Color(135, 206, 250));
                    label.setFont(new Font("Arial", Font.PLAIN, 12));
                    label.setAlignmentX(Component.LEFT_ALIGNMENT);

                    // Add click listener to edit/remove
                    label.addMouseListener(new java.awt.event.MouseAdapter() {
                        @Override
                        public void mouseClicked(java.awt.event.MouseEvent e) {
                            openEditAppointmentDialog(appt);
                        }
                    });

                    hourPanel.add(label);
                }
            }

            daySchedule.add(hourPanel);
        }

//        // Auto-scroll to target hour
//        if (scrollTarget != null) {
//            SwingUtilities.invokeLater(() -> ((JComponent) scrollTarget).scrollRectToVisible(scrollTarget.getBounds()));
//        }

        return panel;
    }
    private void openEditAppointmentDialog(Appointment appt) {
        JTextField idField = new JTextField(appt.getAppointmentId());
        idField.setEditable(false);

        JTextField dateTimeField = new JTextField(appt.getAppointmentDate().toString());
        JTextField descField = new JTextField(appt.getAppointmentDescription());

        JPanel dialogPanel = new JPanel(new GridLayout(0, 1));
        dialogPanel.add(new JLabel("Appointment ID:"));
        dialogPanel.add(idField);
        dialogPanel.add(new JLabel("Date/Time (yyyy-MM-ddTHH:mm):"));
        dialogPanel.add(dateTimeField);
        dialogPanel.add(new JLabel("Description:"));
        dialogPanel.add(descField);

        Object[] options = {"Update", "Delete", "Cancel"};
        int result = JOptionPane.showOptionDialog(
            this,
            dialogPanel,
            "Edit Appointment",
            JOptionPane.YES_NO_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null,
            options,
            options[0]
        );

        if (result == JOptionPane.YES_OPTION) {
            try {
                LocalDateTime newDateTime = LocalDateTime.parse(dateTimeField.getText());
                Appointment updated = new Appointment(appt.getAppointmentId(), newDateTime, descField.getText());
                controller.deleteAppointment(appt.getAppointmentId());
                controller.addAppointment(updated);
                refreshCalendarView();
                JOptionPane.showMessageDialog(this, "Appointment updated!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        } else if (result == JOptionPane.NO_OPTION) {
            controller.deleteAppointment(appt.getAppointmentId());
            refreshCalendarView();
            JOptionPane.showMessageDialog(this, "Appointment deleted.");
        }
    }
    private void openAddAppointmentDialog(LocalDateTime dateTime) {
        JTextField idField = new JTextField();
        JTextField descField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Appointment ID:"));
        panel.add(idField);
        panel.add(new JLabel("Description:"));
        panel.add(descField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Add Appointment for " + dateTime.toLocalDate(),
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                Appointment appt = new Appointment(idField.getText(), dateTime, descField.getText());
                controller.addAppointment(appt);
                refreshCalendarView();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
    