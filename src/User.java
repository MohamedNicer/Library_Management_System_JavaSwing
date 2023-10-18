import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class User {
    protected String name;
    protected String email;
    protected String phonenumber;
    protected IOOperation[] operations;

    public User() {
    }
    public User(String name) {
        this.name = name;
    }

    public User(String name, String email, String phonenumber) {
        this.name = name;
        this.email = email;
        this.phonenumber = phonenumber;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }
    abstract public void menu(Database database, User user);

    abstract public String toString();
    public JFrame frame(String[] strings, Database database, User user){
        JFrame jframe = new JFrame();
        jframe.setSize(400,500);
        jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        try {
            System.setProperty("sun.awt.noerasebackground","false");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            SwingUtilities.updateComponentTreeUI(jframe);
        } catch (Exception e){
            System.err.println(e.toString());
        }
        jframe.setLocationRelativeTo(null);
        jframe.setTitle("Library Management System (LMS)");
        jframe.setLayout(new BorderLayout());
        jframe.setVisible(true);

        JLabel label = Main.title("Welcome Mrs./Mr. "+ this.name);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setForeground(Color.black);
        jframe.getContentPane().add(label,BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(0,30,30,30));
        panel.setLayout(new GridLayout(7,1,15,15));

        for (int i=0;i<7;i++) {
            JButton button = new JButton(strings[i]);
            button.setFont(new Font("Arial",Font.BOLD,17));
            button.setForeground(Color.black);
            button.setHorizontalAlignment(SwingConstants.CENTER);
            int index = i;
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    operations[index].operation(database,user);
                }
            });
            panel.add(button);
        }
        jframe.getContentPane().add(panel,BorderLayout.CENTER);
        return jframe;
    }
}
