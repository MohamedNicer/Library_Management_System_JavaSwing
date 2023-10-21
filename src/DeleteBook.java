import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteBook implements IOOperation{
    @Override
    public void operation(Database database, User user) {

        JFrame frame = Main.frame(400,210);
        frame.setLayout(new BorderLayout());

        JLabel title = Main.title("Delete Book");
        frame.getContentPane().add(title,BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(2,2,15,15));
        panel.setBorder(BorderFactory.createEmptyBorder(0,20,20,20));
        panel.setBackground(null);

        JLabel label = Main.label("Book Title:");
        JTextField book_title = Main.textField();
        JButton delete = Main.button("Delete Book");
        JButton cancel = Main.button("Cancel");
        panel.add(label);
        panel.add(book_title);
        panel.add(delete);
        panel.add(cancel);

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (book_title.getText().toString().matches("")){
                    JOptionPane.showMessageDialog(new JFrame(),"Book Title " +
                            "should be provided!");
                    return;
                }
                int index = database.getBook(book_title.getText().toString());
                if (index>-1){
                    database.deleteBook(index);
                    JOptionPane.showMessageDialog(new JFrame(),"Book deleted successfully!");
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
