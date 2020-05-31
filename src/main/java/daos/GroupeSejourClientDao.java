package daos;

import modele.GroupeSejourClient;

import java.util.List;

public interface GroupeSejourClientDao {
    public int insererGroupeSejourClient(GroupeSejourClient groupeSejourClient);

    List<GroupeSejourClient> getGroupeSejourClient(String id_groupe, String id_sejour);


    public GroupeSejourClient getGroupeSejourClient(String id_groupe, String id_sejour,String id_client) ;


    List<GroupeSejourClient> getGroupeSejourClientByIdSejour(String s);
}
