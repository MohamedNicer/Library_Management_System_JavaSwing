import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculateFine implements IOOperation{
    @Override
    public void operation(Database database, User user) {
        JFrame frame = Main.frame(400,210);
        frame.setLayout(new BorderLayout());

        JLabel title = Main.title("Borrow Book");
        frame.getContentPane().add(title,BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(2,2,15,15));
        panel.setBorder(BorderFactory.createEmptyBorder(0,20,20,20));

        JLabel label = Main.label("Book Title:");
        JTextField book_title = Main.textField();
        JButton calculus = Main.button("Calculate Fine");
        JButton cancel = Main.button("Cancel");
        panel.add(label);
        panel.add(book_title);
        panel.add(calculus);
        panel.add(cancel);

        calculus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (book_title.getText().toString().matches("")) {
                    JOptionPane.showMessageDialog(new JFrame(), "Book Title " +
                            "should be provided!");
                    return;
                }
                boolean g = true;
                for (Borrowing borrowing: database.getAllBorrowings()){
                    if(borrowing.getBook().getTitle().matches(book_title.getText().toString()) &&
                            borrowing.getUser().getName().matches(user.getName())){
                        if(borrowing.getDaysleft()>0){
                            JOptionPane.showMessageDialog(new JFrame(),"You're late!\n" + "As of today! Your fine is: " +
                                    Math.abs(borrowing.daysleft*20) + "\n" );
                        } else {
                            JOptionPane.showMessageDialog(new JFrame(),"You've nothing additional to pay!\n");
                        }
                        g = false;
                        break;
                    }
                }
                if(g){
                    JOptionPane.showMessageDialog(new JFrame(),"You didn't borrow this book!\n");
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
