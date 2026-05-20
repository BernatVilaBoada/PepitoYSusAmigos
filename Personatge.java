import java.util.ArrayList;

public abstract class Personatge {
    private String nom;
    private final int VIDA_INICIAL = 100;
    private boolean estaViu;
    private int vidaActual;
    private int malFet;
    private Equip equip;

    public Personatge(String nom, Equip equip) {
        this.nom = nom;
        estaViu = true;
        vidaActual = VIDA_INICIAL;
        malFet = 0;
        this.equip = equip;
    }

    public void torn(Combatents combatents) {
        ArrayList<Personatge> oponents = combatents.obtenirPersonatges();
        Personatge oponent = UI.escollirOponent(this, oponents);
        ferAccio(oponent);
    }

    abstract public void ferAccio(Personatge oponent);

    protected void atacarAmbMal(Personatge oponent, int mal) {
        if (estaViu) {
            UI.mostrarFerAtac(this, oponent, mal);
            oponent.rebreMal(mal);
            malFet = malFet + mal;
        }
    }

    public void rebreMal(int mal) {
        if (estaViu) {
            vidaActual = vidaActual - mal;
            if (vidaActual <=0) {
                estaViu = false;
            }
            UI.mostrarRebreAtac(this, vidaActual, estaViu);
        }
    }

    public void rebreCuracio(int curacio) {
        if (estaViu) {
            vidaActual = vidaActual + curacio;
            if (vidaActual > VIDA_INICIAL) {
                vidaActual = VIDA_INICIAL;
            }
        }
    }

    public boolean esViu() {
        return estaViu;
    }

    protected void augmentarMalFet(int malFet) {
        this.malFet = this.malFet + malFet;
    }

    public int getMalFet() {
        return malFet;
    }

    public String getVidaOMort() {
        if (estaViu) {
            return "vida " + Integer.toString(vidaActual);
        }
        else {
            return("mort");
        }
    }

    public String getNom() {
        return nom;
    }

    public Equip getEquip() {
        return equip;
    }
}
