import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BorrowBook implements IOOperation{
    @Override
    public void operation(Database database, User user) {

        JFrame frame = Main.frame(400,210);
        frame.setLayout(new BorderLayout());

        JLabel title = Main.title("Borrow Book");
        frame.getContentPane().add(title,BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(2,2,15,15));
        panel.setBorder(BorderFactory.createEmptyBorder(0,20,20,20));
        panel.setBackground(null);

        JLabel label = Main.label("Book Title:");
        JTextField book_title = Main.textField();
        JButton borrow = Main.button("Borrow Book");
        JButton cancel = Main.button("Cancel");
        panel.add(label);
        panel.add(book_title);
        panel.add(borrow);
        panel.add(cancel);

        borrow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (book_title.getText().toString().matches("")){
                    JOptionPane.showMessageDialog(new JFrame(),"Book Title " +
                            "should be provided!");
                    return;
                }
                int index = database.getBook(book_title.getText().toString());
                boolean b = true;
                for (Borrowing borrowing: database.getAllBorrowings()){
                    if (borrowing.getBook().getTitle().matches(book_title.getText().toString()) &&
                            borrowing.getUser().getName().matches(user.getName())){
                        b = false;
                        JOptionPane.showMessageDialog(new JFrame(),"You've already borrowed this book!\n");
                    }
                }
                if(b){
                    if (index>-1){
                        Book book = database.getBook(index);
                        if(book.getBorrowcopies()>1){
                            Borrowing borrowing = new Borrowing(user,book);
                            book.setBorrowcopies(book.getBorrowcopies()-1);
                            database.borrowBook(borrowing,book,index);
                            JOptionPane.showMessageDialog(new JFrame(),"\nThe book with title: "+ book_title.getText().toString() + " should be " +
                                    "returned in the next 14 days\n" + "Expiry date: " +
                                    borrowing.getFinish() + "\nEnjoy it\n");
                            frame.dispose();
                        }else {
                            JOptionPane.showMessageDialog(new JFrame(),"Book unavailable!");
                        }
                    } else{
                        JOptionPane.showMessageDialog(new JFrame(),"Book doesn't exist!");
                    }
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
