import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public class fecha {
    private HashMap<String, Integer> mesesMap;
    private JPanel panel1;
    private JComboBox cbDia;
    private JComboBox cbMes;
    private JComboBox cbanio;
    private JButton btgenerar;
    Connection connection;
    Statement st;
    PreparedStatement ps;
    ResultSet rs;
    ArrayList dListaDia;
    private ArrayList<Dia> diaf;
    ArrayList mListaMes;
    private ArrayList<Mes> mesf;
    ArrayList aListaAnio;
    private ArrayList<Anio> aniof;
    int mesesint;
    public void rellenarDia()
    {
        connection=getConection();
        cbDia.removeAllItems();
        dListaDia=getListDiaf();
        Iterator iterator = dListaDia.iterator();
        while (iterator.hasNext()) {
            Dia tTipo = (Dia) iterator.next();
            cbDia.addItem(tTipo);
        }
    }
    public ArrayList getListDiaf()
    {

        ArrayList dDiaD=new ArrayList();
        Dia dDia=null;
        Statement consulta;
        ResultSet resultado;
        connection=getConection();
        try {
            consulta= connection.createStatement();
            resultado=consulta.executeQuery("select * from dia");
            while (resultado.next())
            {
                dDia=new Dia();
                dDia.setDia(resultado.getInt("dia"));
                dDiaD.add(dDia);
            }

        }catch (SQLException e)
        {

        }
        return dDiaD;}
    public void rellenarMes()
    {
        connection=getConection();
        cbMes.removeAllItems();
        mListaMes=getmListaMes();
        Iterator iterator = mListaMes.iterator();
        while (iterator.hasNext()) {
            Mes mMes = (Mes) iterator.next();
            cbMes.addItem(mMes);
        }
    }
    public ArrayList getmListaMes()
    {

        ArrayList mMesM=new ArrayList();
        Mes mMes=null;
        Statement consulta;
        ResultSet resultado;
        connection=getConection();
        try {
            consulta= connection.createStatement();
            resultado=consulta.executeQuery("select * from mes");
            while (resultado.next())
            {
                mMes=new Mes();
                mMes.setMes(resultado.getString("mes"));
                mMesM.add(mMes);
            }

        }catch (SQLException e)
        {

        }
        return mMesM;}

    public void rellenarAnio()
    {
        connection=getConection();
        cbanio.removeAllItems();
        aListaAnio=getaListaAnio();
        Iterator iterator = aListaAnio.iterator();
        while (iterator.hasNext()) {
            Anio aAnio = (Anio) iterator.next();
            cbanio.addItem(aAnio);
        }
    }
    public ArrayList getaListaAnio()
    {

        ArrayList aAnioa=new ArrayList();
        Anio aAnio=null;
        Statement consulta;
        ResultSet resultado;
        connection=getConection();
        try {
            consulta= connection.createStatement();
            resultado=consulta.executeQuery("select * from anio");
            while (resultado.next())
            {
                aAnio=new Anio();
                aAnio.setAnio(resultado.getInt("anio"));
                aAnioa.add(aAnio);
            }

        }catch (SQLException e)
        {

        }
        return aAnioa;}
    public fecha() {

        Mes meses=new Mes();
        String a= String.valueOf(meses.getId());
        int mesenum=meses.getId();

        cbDia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        cbMes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection con;
                String query = null;
                try{
                    String mesStr = cbMes.getSelectedItem().toString();

                    cbMes.removeAllItems();
                    con = getConection();

                    if (mesenum == 4 || mesenum == 6 || mesenum == 9 || mesenum == 11) {
                        query = "SELECT * FROM dias LIMIT 30";
                        cbMes.removeAllItems();
                    }  else if(mesenum == 1 || mesenum ==3 || mesenum == 5 || mesenum == 7
                            || mesenum==8 || mesenum == 10|| mesenum == 12) {
                        query = "SELECT * FROM dias LIMIT 31";
                        cbMes.removeAllItems();
                    }
                    else if ((cbMes.getSelectedItem().equals("Febrero") && (Integer.parseInt(cbanio.getSelectedItem().toString()) % 4 == 0))){
                        mesenum == 2;
                        query = "SELECT * FROM dias LIMIT 29";
                        cbDia.removeAllItems();
                    }
                    else {
                        mesInt = 2;
                        query = "SELECT * FROM dias LIMIT 28"; //el limite de dias
                        cbDia.removeAllItems();
                    }
                    Statement s = con.createStatement();
                    ResultSet rs = s.executeQuery(query);
                    while(rs.next()){
                        cbDia.addItem(rs.getInt(2));
                    }
                } catch (HeadlessException | SQLException s){
                    System.out.println(s);
                }

            }
        });
        btgenerar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            int dia=Integer.parseInt(cbDia.getSelectedItem().toString());

               // int mesInt = mesesMap.get(mes);
                //String mesesnu= String.valueOf(mesInt);
            int anio= Integer.parseInt(cbanio.getSelectedItem().toString());
                String mesFinal = a;
                String datofecha=(anio+"-"+mesFinal+"-"+dia);

                //Date fecha = Date.valueOf(datofecha);


                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                java.sql.Date fechaConvertida=null;

                try {
                    Date parsed =  dateFormat.parse(datofecha);
                    fechaConvertida = new java.sql.Date(parsed.getDate());
                } catch(Exception j) {
                    System.out.println("Error occurred"+j);
                }
                //fecha_ingresada.setText(datofecha);//un jtext para mpstrar
                Connection con;
                try{
                    con = getConection();
                    ps = con.prepareStatement("INSERT INTO dato (fechas) VALUES (?)");
                    ps.setDate(1, (java.sql.Date) fechaConvertida);
                    System.out.println(ps);

                    int cont = ps.executeUpdate();
                    if(cont > 0){
                        JOptionPane.showMessageDialog(null, "Fecha guaradada correctamente");
                    }else{
                        JOptionPane.showMessageDialog(null, "Error no se pudo guardar la fecha");
                    }
                    con.close();
                }catch (SQLException s){
                    System.out.println("Error " + s.getMessage());
                }
            }
        });
    }

    public static Connection getConection() {
        Connection con = null;
        String base = "fecha";
        String url = "jdbc:mysql://localhost:3306/" + base;
        String user = "root";
        String password = "Luchito2724";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
            System.out.println("si se conecto");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e);

        }
        return con;


    }
    public static void main(String[] args) {
        JFrame frame=new JFrame("FECHAS");
        frame.setContentPane(new fecha().panel1);
        frame.setSize(300,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
