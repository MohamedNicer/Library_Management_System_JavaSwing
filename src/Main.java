import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    static Database database;
    public static void main(String[] args) {
        database = new Database();
        JFrame frame = frame(500,300);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,2,50,50));
        panel.setBorder(BorderFactory.createEmptyBorder(10,15,20,15));

        JLabel title = label("Welcome to the library!");
        title.setBorder(BorderFactory.createEmptyBorder(15 ,15,15,15));
        title.setFont(new Font("Arial",Font.BOLD,20));
        frame.getContentPane().add(title,BorderLayout.NORTH);

        JLabel label = label("Phone Number:");
        JLabel label1 = label("Email:");
        JTextField phonenumber = textField();
        JTextField email = textField();
        JButton login = button("Login");
        JButton signup = button("Sign Up");

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (phonenumber.getText().toString().matches("")){
                    JOptionPane.showMessageDialog(new JFrame(),"Phone Number " +
                            "mustn't be left empty!");
                }
                if (email.getText().toString().matches("")){
                    JOptionPane.showMessageDialog(new JFrame(),"Email " +
                            "mustn't be left empty!");
                }
                login(phonenumber.getText().toString(),email.getText().toString(),frame);
            }
        });
        signup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signup();
                frame.dispose();
            }
        });

        panel.add(label);
        panel.add(phonenumber);
        panel.add(label1);
        panel.add(email);
        panel.add(login);
        panel.add(signup);

        frame.getContentPane().add(panel,BorderLayout.CENTER);

    }

    private static void signup() {
        JFrame frame = frame(500,400);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5,2,15,15));
        panel.setBorder(BorderFactory.createEmptyBorder(10,15,20,15));

        JLabel title = label("Sign Up");
        title.setBorder(BorderFactory.createEmptyBorder(15 ,15,15,15));
        title.setFont(new Font("Arial",Font.BOLD,20));
        frame.getContentPane().add(title,BorderLayout.NORTH);

        JLabel label = label("Name:");
        JLabel label1 = label("Phone Number:");
        JLabel label2 = label("Email:");
        JTextField name = textField();
        JTextField phonenumber = textField();
        JTextField email = textField();
        JRadioButton admin = radioButton("Admin");
        JRadioButton customer = radioButton("Customer");
        JButton createuser = button("Create Account");
        JButton cancel = button("Cancel");

        panel.add(label);
        panel.add(name);
        panel.add(label1);
        panel.add(phonenumber);
        panel.add(label2);
        panel.add(email);
        panel.add(admin);
        panel.add(customer);
        panel.add(createuser);
        panel.add(cancel);

        admin.addActionListener(e -> {
            if (admin.isSelected()){
                admin.setSelected(true);
            }
        });
        customer.addActionListener(e -> {
            if (customer.isSelected()){
                customer.setSelected(true);
            }
        });
        createuser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(database.userExists(name.getText().toString())){
                    JOptionPane.showMessageDialog(new JFrame(),"Username" +
                            " Already Exists!\nTry Another One!");
                    return;
                }
                if(name.getText().toString().matches("")){
                    JOptionPane.showMessageDialog(new JFrame(),"Username" +
                            " can't be left empty!");
                    return;
                }
                if(phonenumber.getText().toString().matches("")){
                    JOptionPane.showMessageDialog(new JFrame(),"Phone Number" +
                            " can't be left empty!");
                    return;
                }
                if(email.getText().toString().matches("")){
                    JOptionPane.showMessageDialog(new JFrame(),"Email" +
                            " can't be left empty!");
                    return;
                }
                if(!admin.isSelected() && !customer.isSelected()){
                    JOptionPane.showMessageDialog(new JFrame(),"You" +
                            " must choose an account type!");
                    return;
                }
                User user;
                if (admin.isSelected()){
                    user = new Admin(name.getText().toString(),
                            email.getText().toString(),
                            phonenumber.getText().toString());
                } else{
                    user = new Customer(name.getText().toString(),
                            email.getText().toString(),
                            phonenumber.getText().toString());
                }
                frame.dispose();
                database.AddUser(user);
                user.menu(database,user);
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
    private static void login(String phonenumber, String email,JFrame frame){
        int n = database.login(phonenumber,email);
        if (n != -1){
            User user = database.getUser(n);
            user.menu(database,user);
            frame.dispose();
        }else {
            JOptionPane.showMessageDialog(new JFrame(),"Phone Number And/Or" +
                    " Email Wrong!");
        }
    }
    public static JFrame frame(int width, int height){
        JFrame jframe = new JFrame();
        jframe.setSize(width,height);
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
        return jframe;
    }
    public static JLabel label(String text){
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Arial",Font.BOLD,17));
        label.setForeground(Color.black);
        return label;
    }
    public static JTextField textField(){
        JTextField textField = new JTextField();
        textField.setFont(new Font("Arial",Font.BOLD,17));
        textField.setForeground(Color.black);
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        textField.setBackground(Color.white);
        //textField.setBounds(700,0,100,40);
        return textField;
    }
    public static JButton button(String text){
        JButton button = new JButton(text);
        button.setFont(new Font("Arial",Font.BOLD,17));
        button.setForeground(Color.black);
        button.setHorizontalAlignment(SwingConstants.CENTER);
        return button;
    }
    public static JRadioButton radioButton(String text){
        JRadioButton radioButton = new JRadioButton();
        radioButton.setForeground(Color.black);
        radioButton.setHorizontalAlignment(SwingConstants.CENTER);
        radioButton.setText(text);
        radioButton.setFont(new Font("Arial",Font.BOLD,17));
        return radioButton;
    }
    public static JLabel title(String text){
        JLabel title = new JLabel(text);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBorder(BorderFactory.createEmptyBorder(15 ,15,15,15));
        title.setFont(new Font("Arial",Font.BOLD,20));
        return title;
    }
}
