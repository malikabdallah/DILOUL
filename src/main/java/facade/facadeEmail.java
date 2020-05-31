package facade;

import java.io.File;

public interface facadeEmail {

    void sendFromGMail(String from, String pass, String[] to, String subject, String body, File file);


}
