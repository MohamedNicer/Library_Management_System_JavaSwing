import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ViewOrders implements IOOperation{
    @Override
    public void operation(Database database, User user) {
        JFrame frame = Main.frame(400,210);
        frame.setLayout(new BorderLayout());

        JLabel title = Main.title("View Orders");
        frame.getContentPane().add(title,BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(2,2,15,15));
        panel.setBorder(BorderFactory.createEmptyBorder(0,20,20,20));
        panel.setBackground(null);

        JLabel label = Main.label("Book Title:");
        JTextField book_title = Main.textField();
        JButton view = Main.button("View Orders");
        JButton cancel = Main.button("Cancel");
        panel.add(label);
        panel.add(book_title);
        panel.add(view);
        panel.add(cancel);

        view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (book_title.getText().toString().matches("")){
                    JOptionPane.showMessageDialog(new JFrame(),"Book Title " +
                            "should be provided!");
                    return;
                }
                int index = database.getBook(book_title.getText().toString());
                if(index > -1){
                    viewOrders(book_title.getText().toString(), database);
                    frame.dispose();
                } else {
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
    }
    private void viewOrders(String book_title, Database database){
        int rows = 1;

        for (Order order: database.getAllOrders()){
            if(order.getBook().getTitle().matches(book_title)) {
                rows++;
            }
        }
        int height = rows * 55 + 100;
        JFrame frame = Main.frame(500, height);

        JLabel title = Main.title("View Orders");
        frame.getContentPane().add(title, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(rows, 4, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 30, 30, 30));
        panel.setBackground(null);

        String[] first_row = new String[]{"Book", "User", "Quantity", "Price"};
        for (String str : first_row) {
            JLabel label = label(str);
            panel.add(label);
        }
        for (Order order: database.getAllOrders()){
            if(order.getBook().getTitle().matches(book_title)) {
                JLabel label = label(order.getUser().getName());
                JLabel label1 = label(order.getBook().getTitle());
                JLabel label2 = label(String.valueOf(order.getBook().getQty()));
                JLabel label3 = label(String.valueOf(order.getBook().getPrice()));
                panel.add(label);
                panel.add(label1);
                panel.add(label2);
                panel.add(label3);
            }
        }
        frame.getContentPane().add(panel,BorderLayout.CENTER);
    }
    private JLabel label(String text){
        JLabel label = Main.label(text);
        label.setBackground(Color.white);
        label.setBorder(BorderFactory.createLineBorder(Color.black,1));
        label.setOpaque(true);
        return label;
    }
}
