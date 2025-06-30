package app;

import view.AppGUI;
import controller.AppController;

import javax.swing.SwingUtilities;

public class Main {
	public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AppGUI app = new AppGUI(new AppController());
            app.setVisible(true);
        });
    }
}
