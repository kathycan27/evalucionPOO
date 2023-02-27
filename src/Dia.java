public class Dia {
    int dia;

    public Dia() {
        dia=0;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    @Override
    public String toString() {
        String dias= String.valueOf(dia);
        return dias;
    }
}
