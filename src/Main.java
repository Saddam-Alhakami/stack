
import java.util.Stack;
import java.util.Scanner;
public class Main {




        public static void main(String[] args) {
            Stack<String> historyStack = new Stack<>();
            // Stack om de "vooruit"-geschiedenis op te slaan wanneer je teruggaat
            Stack<String> forwardStack = new Stack<>();
            Scanner scanner = new Scanner(System.in);
            String currentWebsite = "";

            while (true) {
                System.out.println("\n-- Simpele Browser --");
                System.out.println("1. Bezoek Website 1 (www.vlaanderen.be)");
                System.out.println("2. Bezoek Website 2 (www.antwerpen.be)");
                System.out.println("3. Bezoek Website 3 (www.brussel.be)");
                System.out.println("4. Ga terug naar vorige website (pop)");
                System.out.println("5. Ga vooruit naar de volgende website (push)");
                System.out.println("6. Bekijk huidige website (peek)");
                System.out.println("7. Toon alle bezochte websites");
                System.out.println("8. Sluit de browser");
                System.out.print("Maak je keuze: ");

                int keuze = scanner.nextInt();
                scanner.nextLine();  // Voor de enter key na de input

                switch (keuze) {
                    case 1:
                        bezoekWebsite("www.vlaanderen.be", historyStack, forwardStack, currentWebsite);
                        currentWebsite = "www.vlaanderen.be";
                        break;
                    case 2:
                        bezoekWebsite("www.antwerpen.be", historyStack, forwardStack, currentWebsite);
                        currentWebsite = "www.antwerpen.be";
                        break;
                    case 3:
                        bezoekWebsite("www.brussel.be", historyStack, forwardStack, currentWebsite);
                        currentWebsite = "www.brussel.be";
                        break;
                    case 4:
                        currentWebsite = gaTerug(historyStack, forwardStack, currentWebsite);
                        break;
                    case 5:
                        currentWebsite = gaVooruit(forwardStack, historyStack, currentWebsite);
                        break;
                    case 6:
                        bekijkHuidigeWebsite(currentWebsite);
                        break;
                    case 7:
                        toonGeschiedenis(historyStack);
                        break;
                    case 8:
                        System.out.println("Browser afsluiten.");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Ongeldige keuze, probeer opnieuw.");
                }
            }
        }

    // Methode om een website te bezoeken en stacks bij te werken
    public static void bezoekWebsite(String website, Stack<String> historyStack, Stack<String> forwardStack, String currentWebsite) {
        if (!currentWebsite.isEmpty()) {
            // Voeg de huidige website toe aan de geschiedenis
            historyStack.push(currentWebsite);
        }
        // Nieuwe website bezoeken
        System.out.println("Bezoek nu: " + website);
        // Leeg de vooruit-stack omdat je een nieuwe weg volgt
        forwardStack.clear();
    }

    // Methode om terug te gaan naar de vorige website (pop)
    public static String gaTerug(Stack<String> historyStack, Stack<String> forwardStack, String currentWebsite) {
        if (!historyStack.isEmpty()) {
            // Voeg de huidige website toe aan de vooruit-stack
            forwardStack.push(currentWebsite);
            // Ga terug naar de vorige website
            String vorigeWebsite = historyStack.pop();
            System.out.println("Terug naar: " + vorigeWebsite);
            return vorigeWebsite;
        } else {
            System.out.println("Geen vorige website in de geschiedenis.");
            return currentWebsite;
        }
    }

    // Methode om vooruit te gaan naar een recent bezochte website (pop)
    public static String gaVooruit(Stack<String> forwardStack, Stack<String> historyStack, String currentWebsite) {
        if (!forwardStack.isEmpty()) {
            // Voeg de huidige website toe aan de geschiedenis
            historyStack.push(currentWebsite);
            // Ga naar de volgende website
            String volgendeWebsite = forwardStack.pop();
            System.out.println("Vooruit naar: " + volgendeWebsite);
            return volgendeWebsite;
        } else {
            System.out.println("Geen volgende website beschikbaar.");
            return currentWebsite;
        }
    }

    // Methode om de huidige website te bekijken (peek)
    public static void bekijkHuidigeWebsite(String currentWebsite) {
        if (!currentWebsite.isEmpty()) {
            System.out.println("Huidige website: " + currentWebsite);
        } else {
            System.out.println("Geen website geopend.");
        }
    }

    // Methode om alle bezochte websites te tonen
    public static void toonGeschiedenis(Stack<String> historyStack) {
        if (!historyStack.isEmpty()) {
            System.out.println("Bezochte websites:");
            for (String website : historyStack) {
                System.out.println(website);
            }
        } else {
            System.out.println("Geen websites in de geschiedenis.");
        }
    }
}
