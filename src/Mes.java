public class Mes {
    String mes;
    int id;

    public Mes() {
        mes="";
        id=0;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    @Override
    public String toString() {

        return getMes();
    }
}
