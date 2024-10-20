import java.util.Stack;

public class Calculs {
    static int[] calculs = new int[] {};
    public static int zoneTexteToCalculs() {
            Stack<Integer> numbers = new Stack<>();  // Pile pour les nombres
            Stack<Character> operators = new Stack<>();  // Pile pour les opérateurs

            StringBuilder currentNumber = new StringBuilder();
            char lastChar = ' '; // Variable pour suivre le dernier caractère

            for (int i = 0; i < Button.texteOfArea.length(); i++) {
                char c = Button.texteOfArea.charAt(i);

                if (Character.isDigit(c)) {
                    currentNumber.append(c);
                    lastChar = c;
                } else if (c == '(') {
                    // Ajouter l'opérateur '(' à la pile
                    operators.push(c);
                    lastChar = c;
                } else if (c == ')') {
                    processParentheses(numbers, operators, currentNumber); // Appel à la nouvelle méthode
                    lastChar = c;
                } else if (isOperator(c)) {
                    if (isOperator(lastChar) && lastChar != ')') {
                        error("OC");
                        return 0; // Ou gérer l'erreur comme vous le souhaitez
                    }

                    if (currentNumber.length() > 0) {
                        numbers.push((int) Double.parseDouble(currentNumber.toString()));
                        currentNumber.setLength(0);
                    }

                    while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(c)) {
                        processTopOperator(numbers, operators);
                    }

                    operators.push(c);
                    lastChar = c;
                }
            }

            // Traiter le dernier nombre après la boucle
            if (currentNumber.length() > 0) {
                numbers.push(Integer.parseInt(currentNumber.toString()));
            }

            // Traiter tous les opérateurs restants dans la pile
            while (!operators.isEmpty()) {
                // Avant d'effectuer une opération, assurez-vous qu'il y a au moins deux nombres dans la pile
                if (numbers.size() >= 2 || (operators.peek() == '√' && numbers.size() >= 1)) {
                    processTopOperator(numbers, operators);
                } else {
                    break;
                }
            }

            // Le résultat final se trouve en haut de la pile des nombres
            return numbers.pop();
        }

        // Méthode pour traiter le premier opérateur dans la pile
        private static void processTopOperator(Stack<Integer> numbers, Stack<Character> operators) {
            if (operators.isEmpty()) return;  // Vérification de sécurité

            char operator = operators.pop();
            int result = 0;

            switch (operator) {
                case '+':
                    if (numbers.size() >= 2) {
                        int b = numbers.pop();
                        int a = numbers.pop();
                        result = a + b;
                    }
                    break;
                case '-':
                    if (numbers.size() >= 2) {
                        int b = numbers.pop();
                        int a = numbers.pop();
                        result = a - b;
                    }
                    break;
                case '*':
                    if (numbers.size() >= 2) {
                        int b = numbers.pop();
                        int a = numbers.pop();
                        result = a * b;
                    }
                    break;
                case '/':
                    if (numbers.size() >= 2) {
                        int b = numbers.pop();
                        int a = numbers.pop();
                        if (b != 0) {
                            result = a / b;
                        } else {
                            error("/0");
                        }
                    }
                    break;
                case '√':
                    if (numbers.size() >= 1) {
                        int a = numbers.pop();
                        if (a >= 0) {
                            result = (int) Math.sqrt(a);
                        } else {
                            error("√(négatif)");
                        }
                    }
                    break;
                case '^':
                    if (numbers.size() >= 2) {
                        double exponent = numbers.pop();
                        double base = numbers.pop();
                        result = (int) Math.pow(base, exponent);
                    }
                    break;
            }

            // Pousser le résultat dans la pile des nombres
            numbers.push(result);
        }

        // Méthode pour vérifier si un caractère est un opérateur
        private static boolean isOperator(char c) {
            return c == '+' || c == '-' || c == '*' || c == '/' || c == '√' || c == '^';
        }

        // Méthode pour obtenir la priorité d'un opérateur
        private static int precedence(char operator) {
            switch (operator) {
                case '+':
                case '-':
                    return 1;  // Priorité basse
                case '*':
                case '/':
                case '√':
                case '^':
                    return 2;  // Priorité haute
            }
            return -1;
        }

    private static void processParentheses(Stack<Integer> numbers, Stack<Character> operators, StringBuilder currentNumber) {
        if (currentNumber.length() > 0) {
            numbers.push((int) Double.parseDouble(currentNumber.toString()));
            currentNumber.setLength(0);
        }

        while (!operators.isEmpty() && operators.peek() != '(') {
            processTopOperator(numbers, operators);
        }

        // Retirer la parenthèse ouvrante de la pile
        if (!operators.isEmpty()) {
            operators.pop();
        }
    }



    public static void backspace(){
        // Suppression du dernier caractère de la chaîne
        if(Button.texteOfArea == ""){
            error("Back void");
        }else {
            String back = Button.texteOfArea.substring(0, Button.texteOfArea.length() - 1);
            // Mettre à jour la variable texteOfArea
            Button.texteOfArea = back;
        }
    }

    public static void error(String message){
        if (message == "/0"){
            System.out.println("/0 impossible");
        }
        if (message == "Back void"){
            System.out.println("Plus d'éléments a supprimer");
        }
        if (message == "Enter void"){
            System.out.println("Pas de calcul possible avec une zone de texte vide !");
        }
        if (message == "√(négatif)"){
            System.out.println("La racine carrée d'un nombre négatif n'est pas définie.");
        }
        if(message == "/OC"){
            System.out.println("Erreur : Deux opérateurs consécutifs détectés.");
        }
    }

}
