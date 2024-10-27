import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ParcAutomobile {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Vehicule> tousLesVehicules = new ArrayList<>();
    public static final String DISPONIBLE = "Disponible";
    public static final String LOUE = "Loué";

    public static void main(String[] args) {
        int choix;
        boolean continuer = true;
        do {
            System.out.println("\n1.Ajouter un véhicule");
            System.out.println("2.Ajouter un client");
            System.out.println("3.Location de véhicule");
            System.out.println("4.Retourner un véhicule");
            System.out.println("5.Afficher les clients");
            System.out.println("6.Afficher les véhicules disponibles");
            System.out.println("7.Afficher les véhicules loués");
            System.out.println("8.Afficher tous les véhicule");
            System.out.println("9.Afficher les locations d'un client");
            System.out.println("10.Quitter");
            System.out.print("\nQuel est votre choix ? : ");
            choix = scanner.nextInt();
            scanner.nextLine();
            switch (choix) {
                case 1 -> ajouterVehicule();
                case 2 -> ajouterClient();
                case 3 -> louerVehicule();
                case 4 -> retournerVehicule();
                case 5 -> afficherClients();
                case 6 -> afficherVehiculesLouesOuDisponibles(DISPONIBLE);
                case 7 -> afficherVehiculesLouesOuDisponibles(LOUE);
                case 8 -> afficherTousLesVehicules();
                case 9 -> afficherLocationsClient();
                default -> {
                    System.out.println("\nFin du programme...");
                    continuer = false;
                }
            }
        } while (continuer);
    }

    public static void ajouterVehicule() {
        int choix;
        String immatriculation;
        String marque;
        String modele;
        int annee;
        int kilometrage;
        int nbredeplace;
        int typedecarburant;
        int capacite;
        int nbreessieux;

        System.out.print("\nQuelle est votre immatriculation : ");
        immatriculation = scanner.nextLine();
        System.out.print("Quelle est la marque : ");
        marque = scanner.nextLine();
        System.out.print("Quel est le model : ");
        modele = scanner.nextLine();
        System.out.print("Quelle est l'année de service : ");
        annee = scanner.nextInt();
        System.out.print("Donner le kilometrage : ");
        kilometrage = scanner.nextInt();
        System.out.println("\n1.Ajouter une voiture");
        System.out.println("2.Ajouter un camion");
        System.out.print("\nQuel type de vehicule souhaitez vous ajouter ? : ");
        choix = scanner.nextInt();
        switch (choix) {
            case 1 -> {
                System.out.print("\nQuel est le nbre de place ? : ");
                nbredeplace = scanner.nextInt();
                System.out.println("\n1.Electrique");
                System.out.println("2.Diesel");
                System.out.println("3.Essence");
                System.out.print("\nQuel est le type de carburant ? : ");
                typedecarburant = scanner.nextInt();
                Voiture voiture = new Voiture(immatriculation, marque, modele, annee, kilometrage, nbredeplace, typedecarburant);
                tousLesVehicules.add(voiture);
            }
            case 2 -> {
                System.out.print("\nQuelle est la capacité ? : ");
                capacite = scanner.nextInt();
                System.out.print("Quel est le nombre d'essieux ? : ");
                nbreessieux = scanner.nextInt();
                Camion camion = new Camion(immatriculation, marque, modele, annee, kilometrage, capacite, nbreessieux);
                tousLesVehicules.add(camion);
            }
            default -> System.out.println("\nCette valeur ne correspond à aucun type de véhicule...");
        }
    }

    public static void afficherVehiculesLouesOuDisponibles(String statut) {
        for (Vehicule vehicule : tousLesVehicules) {
            if (vehicule.getStatut().equals(statut)) {
                if (vehicule instanceof Voiture voiture) {
                    System.out.println("\n"+voiture.toString());
                } else if (vehicule instanceof Camion camion) {
                    System.out.println("\n"+camion.toString());
                }
            }
        }
    }


    public static void ajouterClient() {
        String nom;
        String prenom;
        int permisdeconduire;
        String numero;
        System.out.print("\nQuel est le nom ? : ");
        nom = scanner.nextLine();
        System.out.print("Quels sont les prénoms ? : ");
        prenom = scanner.nextLine();
        System.out.println("\n1. Permis C");
        System.out.println("2. Permis BC");
        System.out.println("3. Permis B");
        System.out.print("\nQuel est le type de permis ? :  ");
        permisdeconduire = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Quel le numero de téléphone ? : ");
        numero = scanner.nextLine();
        new Clients(nom, prenom, permisdeconduire, numero );
    }

    public static void afficherClients() {
        for (Clients client : Clients.getListeDeClients()) {
            System.out.println("\n" + client.toString());
        }
    }

    public static void louerVehicule() {
        int i = 0;
        int numClient;
        int numVehicule;
        Clients client;
        Vehicule vehicule;
        afficherClients();
        System.out.print("\nQuel est le numéro du client qui effectue la location : ");
        numClient = scanner.nextInt();
        while (i < Clients.getNombreClients() && i < numClient) {
            i++;
        }
        if (i == numClient) {
            client = Clients.getListeDeClients().get(i-1);
            i = 0;
            afficherTousLesVehicules();
            System.out.print("\nQuel est le numéro du véhicule ? : ");
            numVehicule = scanner.nextInt();
            while (i < tousLesVehicules.size() && i < numVehicule) {
                i++;
            }
            if (i == numVehicule) {
                vehicule = tousLesVehicules.get(i-1);
                try {
                    client.louerVehicule(vehicule);
                } catch (ClientNoAutorisé e ) {
                    System.out.println("\n"+e.getMessage());
                }
            } else {
                System.out.println("\nCe numéro ne correspond à aucun véhicule de la liste...");
            }
        } else {
            System.out.println("\nCe numéro ne correspond à aucun client dans la liste...");
        }
    }

    public static void retournerVehicule() {
        int i = 0;
        int numVehicule;
        Vehicule vehicule;
        afficherTousLesVehicules();
        System.out.print("\nQuel est le numéro du véhicule à retourner ? : ");
        numVehicule = scanner.nextInt();
        while (i < tousLesVehicules.size() && i < numVehicule) {
            i++;
        }
        if (i == numVehicule) {
            vehicule = tousLesVehicules.get(i-1);
            if (vehicule instanceof Camion camion) {
                camion.retourner();
            } else if (vehicule instanceof Voiture voiture) {
                voiture.retourner();
            }
            System.out.println("\nVéhicule retourné avec succès...");
        } else {
            System.out.println("\nCe numéro ne correspond à aucun véhicule de la liste...");
        }
    }

    public static void afficherLocationsClient() {
        int i = 0;
        int numClient;
        Clients client;
        afficherClients();
        System.out.print("\nDe quel client voulez-vous obtenir les locations ? : ");
        numClient = scanner.nextInt();
        while(i < Clients.getNombreClients() && i < numClient) {
            i++;
        }
        if (i == numClient) {
            client = Clients.getListeDeClients().get(i-1);
            System.out.println("\n----------"+client.getnom()+" "+client.getprenom()+"----------");
            for(Vehicule vehicule : client.getListeVehicule()){
                if(vehicule instanceof Camion camion){
                    System.out.println("\n"+ camion);
                } else if (vehicule instanceof Voiture voiture) {
                    System.out.println("\n"+ voiture);
                }

            }
        }

    }

    public static void afficherTousLesVehicules() {
        for (Vehicule vehicule : tousLesVehicules) {
            System.out.println("\n" + vehicule.toString());
        }
    }
}
