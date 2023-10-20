import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteAllData implements IOOperation{
    @Override
    public void operation(Database database, User user) {
        JFrame frame = Main.frame(600,170);
        frame.setLayout(new BorderLayout());

        JLabel title = Main.title("Are you sure?");
        frame.getContentPane().add(title,BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(2,2,15,15));
        panel.setBorder(BorderFactory.createEmptyBorder(0,20,20,20));

        JButton proceed = Main.button("Yes, Continue");
        JButton cancel = Main.button("Cancel");

        panel.add(proceed);
        panel.add(cancel);

        proceed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                database.deleteAllData();
                frame.dispose();
                new Exit().operation(database, user);
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                user.menu(database, user);
            }
        });
        frame.getContentPane().add(panel,BorderLayout.CENTER);
    }
}
