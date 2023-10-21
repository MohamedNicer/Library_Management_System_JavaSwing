import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReturnBook implements IOOperation{
    @Override
    public void operation(Database database, User user) {
        JFrame frame = Main.frame(400,210);
        frame.setLayout(new BorderLayout());

        JLabel title = Main.title("Return Book");
        frame.getContentPane().add(title,BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(2,2,15,15));
        panel.setBorder(BorderFactory.createEmptyBorder(0,20,20,20));
        panel.setBackground(null);

        JLabel label = Main.label("Book Title:");
        JTextField book_title = Main.textField();
        JButton return_book = Main.button("Return Book");
        JButton cancel = Main.button("Cancel");
        panel.add(label);
        panel.add(book_title);
        panel.add(return_book);
        panel.add(cancel);

        return_book.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (book_title.getText().toString().matches("")){
                    JOptionPane.showMessageDialog(new JFrame(),"Book Title " +
                            "should be provided!");
                    return;
                }
                if(!database.getAllBorrowings().isEmpty()){
                    for(Borrowing borrowing: database.getAllBorrowings()){
                        if(borrowing.getBook().getTitle().matches(book_title.getText().toString()) &&
                                borrowing.getUser().getName().matches(user.getName())){
                            Book book = borrowing.getBook();
                            int index = database.getAllBooks().indexOf(book);
                            if(borrowing.getDaysleft()>0){
                                JOptionPane.showMessageDialog(new JFrame(),"You're late!\n" + "As of today! Your fine is: " +
                                        Math.abs(borrowing.daysleft*20) + "\n" );
                            }
                            book.setBorrowcopies(book.getBorrowcopies()+1);
                            database.returnBook(borrowing,book, index);
                            JOptionPane.showMessageDialog(new JFrame(),"Thank you for returning the book!\n");
                            frame.dispose();
                        }else {
                            JOptionPane.showMessageDialog(new JFrame(),"You haven't borrowed this book!");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(new JFrame(),"You haven't borrowed this book!");
                }
                user.menu(database, user);
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