public class Camion  extends Vehicule implements InterfaceLouable {
    private int capacite;
    private double nbEssieux;



    public Camion(String immatriculation, String marque, String model, int annee, int kilométrage,  double capacite, double nbDeEssieux) {
        super(immatriculation ,marque ,model,annee,kilométrage );
        setCapacite(capacite);
        setNbEssieux(nbEssieux);
    }

    //Getters and setters


    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(double capacite) {
        if (capacite > 0){ ;
            this.capacite = (int) capacite;
        }else{

            this.capacite = 10;
        }
    }

    public double getNbEssieux() {
        return nbEssieux;
    }

    public void setNbEssieux(double nbEssieux) {
        if (nbEssieux>0) {
            this.nbEssieux=nbEssieux;
        }else{

            {

                this.capacite = 10;
            }

        }
    }
    @Override
    public void louer () throws VehiculeNoDisponible {
        if (this.statut.equals(ParcAutomobile.LOUE)) {
            throw new VehiculeNoDisponible("le véhicule est déjà loué ");
        }else{
            this.statut = ParcAutomobile.LOUE;
        }
    }
    @Override
    public void retourner() {
        this.statut = ParcAutomobile.DISPONIBLE;
    }

    @Override
    public double calculerPrixLoccation() {
        if(this.capacite > 12){
            return 320000;
        }else{
            return 150000;
        }
    }

}
