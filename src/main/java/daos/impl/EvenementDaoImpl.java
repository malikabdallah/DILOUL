package daos.impl;

import com.mysql.jdbc.PreparedStatement;
import controlleurvue.centre.CreerCentre;
import daos.EvenementDao;
import javafx.beans.property.StringProperty;
import modele.Annulation;
import modele.Centre;
import modele.Evenement;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EvenementDaoImpl extends Dao<Annulation> implements EvenementDao {
    public EvenementDaoImpl(Connection conn) {
        super(conn);
    }


    @Override
    public int insererEvenement(Evenement evenement) {

        int res=0;
        String sql="INSERT INTO evenement (codeclient,codesejour,evenementa,somme,dateevenement,methode)VALUES (?,?,?,?,?,?)";

        try {

            String sdf=new SimpleDateFormat("dd-MM-yyyy").format(new Date());
            PreparedStatement ps=(PreparedStatement)connect.prepareStatement(sql);
            ps.setString(1, evenement.codeclient);
            ps.setString(2,evenement.codesejour);
            ps.setString(3,evenement.event);
            ps.setString(4,evenement.somme);
            ps.setString(5,sdf);
            ps.setString(6,evenement.methode);


            res=ps.executeUpdate();

        } catch (SQLException e) {
            Logger.getLogger(CreerCentre.class.getName()).log(Level.SEVERE, null, e);
        }

        return res;
    }
}
