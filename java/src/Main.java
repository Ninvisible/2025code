
import javax.swing.*;

public class Main extends BackgroundPanel {
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Tag Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 700);  
            frame.setLocationRelativeTo(null);

            BackgroundPanel backgroundPanel = new BackgroundPanel();
            frame.setContentPane(backgroundPanel);

            frame.setVisible(true);
        });
    }
}
