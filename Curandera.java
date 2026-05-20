import java.util.ArrayList;

public class Curandera extends Personatge {

    private final int CURACIO = 10;

    public Curandera(String nom, Equip equip) {
        super(nom, equip);
    }

    @Override
    public void torn(Combatents combatents) {
        int accio = UI.escollirAccioCurandera(this);

        if (accio == 1) {
            // Atacar: primer escull oponent, després ataca
            ArrayList<Personatge> oponents = combatents.obtenirPersonatges();
            Personatge oponent = UI.escollirOponent(this, oponents);
            ferAccio(oponent);
        } else {
            // Curar: no cal oponent, passem null
            ferAccio(null);
        }
    }

    @Override
    public void ferAccio(Personatge oponent) {
        if (oponent != null) {
            int mal = Dau.tirar(6) + 14;
            atacarAmbMal(oponent, mal);
        } else {
            curarCompany();
        }
    }

    private void curarCompany() {
        ArrayList<Personatge> companys = getEquip().getPersonatges();

        Personatge mesFerit = null;
        for (Personatge p : companys) {
            if (p.esViu() && p != this) {
                mesFerit = p;
                break;
            }
        }

        if (mesFerit != null) {
            mesFerit.rebreCuracio(CURACIO);
            System.out.println(getNom() + " cura a " + mesFerit.getNom() + " per " + CURACIO + " punts de vida.");
        } else {
            System.out.println(getNom() + " no té companys a curar!");
        }
    }
}