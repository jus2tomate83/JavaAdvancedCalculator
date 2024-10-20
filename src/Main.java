import com.sun.jdi.Value;
import javax.naming.Context;
import javax.swing.*;

public class Main {
    public static void main(String[] args){
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setTitle("Calculator");
        window.setSize(1000, 1200);
        window.setLocation(0, 0);

        Panel panel = new Panel();
        window.add(panel);

        Button buttonPanel = new Button();
        Button.setupButton(panel); // Ajout des boutons


        window.pack(); // Ajuster la taille
        window.setLocationRelativeTo(null); // Centrer la fenêtre
        window.setVisible(true); // Appeler une seule fois

        panel.startGameThread();

    }
}
