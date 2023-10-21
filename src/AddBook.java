import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddBook implements IOOperation{
    @Override
    public void operation(Database database, User user) {

        JFrame frame = Main.frame(500,600);

        JLabel title = Main.title("Add New Book");
        frame.getContentPane().add(title,BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8,2,15,15));
        panel.setBorder(BorderFactory.createEmptyBorder(0,30,30,30));
        panel.setBackground(null);

        JLabel label = Main.label("Book Title:");
        JLabel label1 = Main.label("Book Author:");
        JLabel label2 = Main.label("Book Publisher:");
        JLabel label3 = Main.label("Book Collection Address:");
        JLabel label4 = Main.label("Book Quantity:");
        JLabel label5 = Main.label("Book Price:");
        JLabel label6 = Main.label("Borrowing Copies:");

        JTextField book_title = Main.textField();
        JTextField author = Main.textField();
        JTextField publisher = Main.textField();
        JTextField address = Main.textField();
        JTextField qty = Main.textField();
        JTextField price = Main.textField();
        JTextField borrowCopies = Main.textField();

        JButton add = Main.button("Add Book");
        JButton cancel = Main.button("Cancel");

        panel.add(label);
        panel.add(book_title);
        panel.add(label1);
        panel.add(author);
        panel.add(label2);
        panel.add(publisher);
        panel.add(label3);
        panel.add(address);
        panel.add(label4);
        panel.add(qty);
        panel.add(label5);
        panel.add(price);
        panel.add(label6);
        panel.add(borrowCopies);
        panel.add(add);
        panel.add(cancel);

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (book_title.getText().toString().matches("")){
                    JOptionPane.showMessageDialog(new JFrame(),"Book Title " +
                            "shouldn't be empty!");
                    return;
                }
                if (author.getText().toString().matches("")){
                    JOptionPane.showMessageDialog(new JFrame(),"Book Author " +
                            "shouldn't be empty!");
                    return;
                }
                if (publisher.getText().toString().matches("")){
                    JOptionPane.showMessageDialog(new JFrame(),"Book Publisher " +
                            "shouldn't be empty!");
                    return;
                }
                if (address.getText().toString().matches("")){
                    JOptionPane.showMessageDialog(new JFrame(),"Book Collection " +
                            "Address shouldn't be empty!");
                    return;
                }
                if (qty.getText().toString().matches("")){
                    JOptionPane.showMessageDialog(new JFrame(),"Book Quantity " +
                            "shouldn't be empty!");
                    return;
                }
                if (price.getText().toString().matches("")){
                    JOptionPane.showMessageDialog(new JFrame(),"Book Price " +
                            "shouldn't be empty!");
                    return;
                }
                if (borrowCopies.getText().toString().matches("")){
                    JOptionPane.showMessageDialog(new JFrame(),"Number of Copies" +
                            " To Borrow shouldn't be empty!");
                    return;
                }
                try{
                    Integer.parseInt(qty.getText().toString());
                } catch (Exception e1){
                    JOptionPane.showMessageDialog(new JFrame(),"Book Quantity " +
                            "should be integer!");
                    return;
                }
                try {
                    Double.parseDouble(price.getText().toString());
                } catch (Exception e1){
                    JOptionPane.showMessageDialog(new JFrame(),"Book Price " +
                            "should be numerical!");
                }
                try{
                    Integer.parseInt(borrowCopies.getText().toString());
                } catch (Exception e1){
                    JOptionPane.showMessageDialog(new JFrame(),"Number of Copies " +
                            "To Borrow should be integer!");
                    return;
                }
                Book book = new Book();
                if (database.getBook(book_title.getText().toString()) > -1) {
                    JOptionPane.showMessageDialog(new JFrame(), "Book Already Exists!");
                    user.menu(database, user);
                } else {
                    book.setTitle(book_title.getText().toString());
                    book.setAuthor(author.getText().toString());
                    book.setPublisher(publisher.getText().toString());
                    book.setAddress(address.getText().toString());
                    book.setQty(Integer.parseInt(qty.getText().toString()));
                    book.setPrice(Double.parseDouble(price.getText().toString()));
                    book.setBorrowcopies(Integer.parseInt(borrowCopies.getText().toString()));
                    database.AddBook(book);
                    JOptionPane.showMessageDialog(new JFrame(), "Book Added Successfully!");
                    frame.dispose();
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
