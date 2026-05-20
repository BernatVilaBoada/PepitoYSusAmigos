public class EquipCuranderaPuado extends Equip {
    public EquipCuranderaPuado(String nom) {
        super(nom);
        afegirPersonatge(new Puado("Puado", this));
        afegirPersonatge(new Curandera("Curandera", this));
    }
}