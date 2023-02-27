public class Anio {
    int anio;

    public int Anio(int anio) {
        anio=0;
        return anio;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    @Override
    public String toString() {
        String anios= String.valueOf(anio);
        return anios;
    }
}
