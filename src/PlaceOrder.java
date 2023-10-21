import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlaceOrder implements IOOperation{
    @Override
    public void operation(Database database, User user) {

        JFrame frame = Main.frame(400,270);
        frame.setLayout(new BorderLayout());

        JLabel title = Main.title("Place Order");
        frame.getContentPane().add(title,BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(3,2,15,15));
        panel.setBorder(BorderFactory.createEmptyBorder(0,20,20,20));

        JLabel label = Main.label("Book Title:");
        JTextField book_title = Main.textField();
        JLabel label1 = Main.label("Quantity:");
        JTextField book_qty = Main.textField();
        JButton place_order = Main.button("Place Order");
        JButton cancel = Main.button("Cancel");
        panel.add(label);
        panel.add(book_title);
        panel.add(label1);
        panel.add(book_qty);
        panel.add(place_order);
        panel.add(cancel);

        place_order.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (book_title.getText().toString().matches("")){
                    JOptionPane.showMessageDialog(new JFrame(),"Book Title " +
                            "should be provided!");
                    return;
                }
                if (book_qty.getText().toString().matches("")){
                    JOptionPane.showMessageDialog(new JFrame(),"The Quantity " +
                            "should be provided!");
                    return;
                }
                try{
                    Integer.parseInt(book_qty.getText().toString());
                } catch (Exception e1){
                    JOptionPane.showMessageDialog(new JFrame(),"The Quantity " +
                            "should be integer!");
                    return;
                }
                Order order = new Order();
                int index = database.getBook(book_title.getText().toString());
                if (index>-1){
                    Book book = database.getBook(index);
                    order.setBook(book);
                    order.setUser(user);
                    order.setQty(Integer.parseInt(book_qty.getText().toString()));
                    order.setPrice(book.getPrice()*Integer.parseInt(book_qty.getText().toString()));
                    int bookindex = database.getBook(book.getTitle());
                    book.setQty(book.getQty()-Integer.parseInt(book_qty.getText().toString()));
                    database.addOrder(order,book,bookindex);
                    JOptionPane.showMessageDialog(new JFrame(),"Order placed successfully!");
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
