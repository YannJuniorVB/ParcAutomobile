import java.util.ArrayList;
public class Clients {
    private static  int nombreClients=0;
    private int idClient;
    private final String nom;
    private final String  prenom;
    private String permisConduire;
    private final String numero ;
    private static ArrayList<Clients> ListeDeClients = new ArrayList<>();
    private ArrayList<Vehicule> listeVehicule;

    public Clients(String nom, String prenom, int permisConduire, String numero) {
        this.nom = nom;
        this.prenom = prenom;
        setPermisConduire(permisConduire);
        this.numero = String.valueOf(numero);
        this.listeVehicule = new ArrayList<>();
        nombreClients++;
        idClient=nombreClients;
        ListeDeClients.add(this);
        listeVehicule=new ArrayList<>();
    }

    //Getters
    public String getnom() {
        return nom;
    }

    public String getprenom() {
        return prenom;
    }

    public String getPermisConduire() {
        return permisConduire;
    }

    public String getNumero() {
        return numero;
    }

    public static ArrayList<Clients> getListeDeClients() {
        return ListeDeClients;
    }

    public static int getNombreClients() {
        return nombreClients;
    }

    public int getIdClient() {
        return idClient;
    }

    public ArrayList<Vehicule> getListeVehicule() {
        return listeVehicule;

    }


    //Setters
    public void setPermisConduire(int permisConduire) {
        switch (permisConduire) {
            case 1:
                this.permisConduire ="C";
                break;
            case 2 :
                this.permisConduire="BC";
                break;
            default:
                this.permisConduire ="B";

        }


    }

    //methode

    public void louerVehicule(Vehicule vehicule ) throws ClientNoAutorisé {
        if (vehicule instanceof Camion) {
            if (this.permisConduire.equals("B")) {
                throw new ClientNoAutorisé ("accés clients indisponible ");
            } else {
                try{
                    ((Camion)vehicule).louer();
                    this.listeVehicule.add(vehicule);
                }catch(VehiculeNoDisponible e){
                    System.out.println(e.getMessage());

                }

            }

        } else if (vehicule instanceof Voiture ) {
            try{
                ((Voiture)vehicule).louer();
                this.listeVehicule.add(vehicule);
            }catch (VehiculeNoDisponible e){
                System.out.println(e.getMessage());
            }
        }
    }



    public void retournerVehicule(Vehicule vehicule ){
        if (vehicule instanceof Camion ){
            ((Camion ) vehicule ). retourner();

        } else if (vehicule instanceof Voiture ) {
            ((Voiture) vehicule).retourner();
        }


    }

    public String toString() {
        return this.idClient + " - {\n" +
                "\nNom: " + nom +
                "\nPrenoms: " + prenom +
                "\nPermis de conduire: " + permisConduire +
                "\nNumero de telephone: " + numero+" }";
    }
}
