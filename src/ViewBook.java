import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ViewBook implements IOOperation{
    @Override
    public void operation(Database database, User user) {

        int rows = database.getAllBooks().size()+1;
        int height = rows * 60 + 100;
        JFrame frame = Main.frame(1000, height);

        JLabel title = Main.title("View Books");
        frame.getContentPane().add(title, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(rows, 7, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 30, 30, 30));

        String[] first_row = new String[]{"Title", "Author", "Publisher", "Col-Add",
                "Quantity", "Price", "Borr-Cps"};
        for (String str : first_row) {
            JLabel label = label(str);
            panel.add(label);
        }
        for (Book b : database.getAllBooks()) {
            JLabel label = label(b.getTitle());
            JLabel label1 = label(b.getAuthor());
            JLabel label2 = label(b.getPublisher());
            JLabel label3 = label(b.getAddress());
            JLabel label4 = label(String.valueOf(b.getQty()));
            JLabel label5 = label(String.valueOf(b.getPrice()));
            JLabel label6 = label(String.valueOf(b.getBorrowcopies()));
            panel.add(label);
            panel.add(label1);
            panel.add(label2);
            panel.add(label3);
            panel.add(label4);
            panel.add(label5);
            panel.add(label6);
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
