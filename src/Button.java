import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class Button {
    Panel p;

    public static String texteOfArea;
    public static String textOfAreaShowResult;


    static String[] operatorButtons = new String[] {"-", "+", "*", "/","√","^","(",")"};// Pour les opérateurs +, -, *, /
    static int[] numberButtons = new int[] {0,1,2,3,4,5,6,7,8,9};// Pour les chiffres

    private static JTextArea textArea;   // Déclaration de textArea pour y accéder dans update()
    private static JTextArea textAreaShowResult;   // Déclaration de textArea pour y accéder dans update()

    public static void setupButton(Panel p) {

        // Création de la zone d'affichage de l'entrée utilisateur de texte (JTextArea)
        textArea = new JTextArea(10, 30);
        textArea.setEditable(false);  // Si vous voulez seulement afficher du texte, sans édition
        textArea.append("2+9*9\n");  // Ajout d'un saut de ligne pour la lisibilité
        texteOfArea = textArea.getText();
        p.add(textArea);

        // Création de la zone d'affichage de sortie de texte (JTextArea)
        textAreaShowResult = new JTextArea(10, 30);
        textAreaShowResult.setEditable(false);  // Si vous voulez seulement afficher du texte, sans édition
        textAreaShowResult.append("");  // Ajout d'un saut de ligne pour la lisibilité
        textOfAreaShowResult = textAreaShowResult.getText();
        p.add(textAreaShowResult);

        //ENter Button
        JButton Enter = new JButton(String.valueOf("Enter"));
        p.add(Enter);

        // Ajout des écouteurs d'événements aux boutons
        Enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action exécutée lorsque le bouton Enter est cliqué
                if(texteOfArea == ""){
                    Calculs.error("Enter void");
                }else {

                    textAreaShowResult.setText("");
                    textOfAreaShowResult = String.valueOf(Calculs.zoneTexteToCalculs());
                }
            }
        });

        //Backspace Button
        JButton Back = new JButton(String.valueOf("Back"));
        p.add(Back);
        // Ajout des écouteurs d'événements aux boutons
        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action exécutée lorsque le bouton Enter est cliqué
                Calculs.backspace();
            }
        });

        // BOUTONS CHIFFRES
        for (int i = 0; i < numberButtons.length; i++) {
            JButton button = new JButton(String.valueOf(numberButtons[i]));
            p.add(button);
            // Ajout des écouteurs d'événements aux boutons
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Action exécutée lorsque le bouton "Afficher Hello" est cliqué
                    textArea.append(button.getText());
                    texteOfArea = textArea.getText();
                }
            });
        }


        //BOUTONS CARECTERES
        for (int i = 0; i < operatorButtons.length; i++) {
            JButton button = new JButton(String.valueOf(operatorButtons[i]));
            p.add(button);
            // Ajout des écouteurs d'événements aux boutons
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Action exécutée lorsque le bouton "Afficher Hello" est cliqué
                    textArea.append(button.getText());
                    texteOfArea = textArea.getText();
                }
            });
        }
    }

    public static void update() {
        textArea.setText("");
        textArea.append(texteOfArea);

        textAreaShowResult.setText("");
        textAreaShowResult.append(textOfAreaShowResult);
    }




}
