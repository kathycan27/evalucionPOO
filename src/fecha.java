import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public class fecha {
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

        dListaDia=new ArrayList();
        rellenarDia();
        mListaMes=new ArrayList();
        rellenarMes();
        aListaAnio=new ArrayList();
        rellenarAnio();

        cbDia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
             if(cbMes.getSelectedItem().equals("enero"))
             {
                 mesesint =1;
             } else  {
                 if (cbMes.getSelectedItem().equals("febrero"))
                 {
                     mesesint=2;
                 }

             }

            }
        });
        cbMes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btgenerar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                JOptionPane.showMessageDialog(null, cbanio.getSelectedItem() + " " + cbMes.getSelectedItem() + " " + cbDia.getSelectedItem());
                String fechas = cbanio.getSelectedItem() + " " + cbMes.getSelectedItem() + " " + cbDia.getSelectedItem();
                connection = getConection();
                format=SimpleDa
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
