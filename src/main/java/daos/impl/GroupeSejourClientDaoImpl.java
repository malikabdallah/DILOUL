package daos.impl;

import basededonnee.DBconnexion;
import com.mysql.jdbc.PreparedStatement;
import controlleurvue.sejour.CreerSejour;
import daos.AssociationGroupeSejourDao;
import daos.GroupeSejourClientDao;
import modele.Associationgroupesejour;
import modele.GroupeSejourClient;
import modele.Sejour;
import notification.Notification;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GroupeSejourClientDaoImpl extends Dao<Associationgroupesejour> implements GroupeSejourClientDao {

    public GroupeSejourClientDaoImpl(Connection conn) {
        super(conn);
    }

    @Override
    public int insererGroupeSejourClient(GroupeSejourClient groupeSejourClient) {
        int []tab=new int[2];
        String sql="INSERT INTO groupe_sejour_client (id_groupe,id_sejour,id_client,depart) VALUES (?,?,?,?)";
        ResultSet ress=null;
        int res=0;
        try {


            PreparedStatement ps=(PreparedStatement)connect.prepareStatement(sql);

            ps.setString(1, groupeSejourClient.idGroupe);
            ps.setString(2, groupeSejourClient.idSejour);
            ps.setString(3, groupeSejourClient.idClient);
            ps.setString(4,groupeSejourClient.depart);





            res=ps.executeUpdate();
             ress =ps.getGeneratedKeys();

        } catch (SQLException ex) {
            Logger.getLogger(CreerSejour.class.getName()).log(Level.SEVERE, null, ex);
        }


        return res;
    }

    @Override
    public List<GroupeSejourClient> getGroupeSejourClient(String id_groupe, String id_sejour) {

        List<GroupeSejourClient>list=new ArrayList<>();
        String sql="SELECT * FROM groupe_sejour_client where id_groupe ='"+id_groupe+"' AND id_sejour='"+id_sejour+"'";



        Connection connection= DBconnexion.getConnection();
        try {
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                GroupeSejourClient groupeSejourClient=new GroupeSejourClient(rs.getString(1),rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getString(5));
                list.add(groupeSejourClient);


            }
        }catch (Exception e){

        }
        return list;
    }



    @Override
    public GroupeSejourClient getGroupeSejourClient(String id_groupe, String id_sejour,String id_client) {

        GroupeSejourClient list=null;
      //  String sql="SELECT * FROM groupe_sejour_client where id_groupe ='"+id_groupe+"' AND id_sejour='"+id_sejour+
       //         "' AND id_client='"+id_client;
        String sql="SELECT * FROM groupe_sejour_client where id_groupe = ? AND id_sejour= ? AND id_client=?";




        Connection connection= DBconnexion.getConnection();
        try {
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sql);

            ps.setString(1,id_groupe);
            ps.setString(2,id_sejour);
            ps.setString(3,id_client);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list=new GroupeSejourClient(rs.getString(1),rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getString(5));


            }
        }catch (Exception e){

        }

        return list;
    }

    @Override
    public List<GroupeSejourClient> getGroupeSejourClientByIdSejour(String s) {
        List<GroupeSejourClient>list=new ArrayList<>();
        String sql="SELECT * FROM groupe_sejour_client where id_sejour '="+s+"'";
        Connection connection=DBconnexion.getConnection();
        try{
            PreparedStatement ps=(PreparedStatement)connection.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                GroupeSejourClient groupeSejourClient=new GroupeSejourClient(rs.getString(1),
                        rs.getString(2),rs.getString(3),rs.getString(4),
                        rs.getString(5));
                list.add(groupeSejourClient);

            }
        }catch (Exception e){

        }
        return list;
    }

}
