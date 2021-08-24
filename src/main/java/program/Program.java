package program;

import GUI.AuthForm;
import GUI.ParameterForm;
import GUI.StartForm;
import db.DAO;
import java.util.prefs.Preferences;
import model.User;
import org.hibernate.QueryException;

public class Program {

   
    private static final String LAST_USER = "def user";

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ParameterForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ParameterForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ParameterForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ParameterForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                   Preferences prefs = Preferences.userRoot().node("UserLogins");
                    User user = (User) DAO.getObjectByParam("login", prefs.get(LAST_USER, ""), User.class);
                    DAO.closeOpenedSession();
                    if (user != null) {
                        prefs.put(LAST_USER, user.getLogin());
                        new StartForm(user).setVisible(true);                     
                    }
                    else {
                        new AuthForm().setVisible(true);
                    }
                } catch (QueryException | NullPointerException e) {
                    //??
                }
            }
        });
    }
}
