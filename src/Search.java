import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class Search implements IOOperation{
    @Override
    public void operation(Database database, User user) {
        JFrame frame = Main.frame(400,210);
        frame.setLayout(new BorderLayout());

        JLabel title = Main.title("Search Book");
        frame.getContentPane().add(title,BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(2,2,15,15));
        panel.setBorder(BorderFactory.createEmptyBorder(0,20,20,20));

        JLabel label = Main.label("Book Title:");
        JTextField book_title = Main.textField();
        JButton search = Main.button("Search Book");
        JButton cancel = Main.button("Cancel");
        panel.add(label);
        panel.add(book_title);
        panel.add(search);
        panel.add(cancel);

        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (book_title.getText().toString().matches("")){
                    JOptionPane.showMessageDialog(new JFrame(),"Book Title " +
                            "should be provided!");
                    return;
                }
                int index = database.getBook(book_title.getText().toString());
                if (index>-1){
                    JOptionPane.showMessageDialog(new JFrame(),database.getBook(index).toString());
                    frame.dispose();
                } else{
                    JOptionPane.showMessageDialog(new JFrame(),"Book doesn't exist!");
                }
            }
        });
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        frame.getContentPane().add(panel,BorderLayout.CENTER);
    }
}
