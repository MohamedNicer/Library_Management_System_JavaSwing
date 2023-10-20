import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Exit implements IOOperation{

    Database database;
    @Override
    public void operation(Database database, User user) {
        JFrame frame = Main.frame(500,300);

        this.database = new Database();

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,2,50,50));
        panel.setBorder(BorderFactory.createEmptyBorder(10,15,20,15));

        JLabel title = Main.label("Welcome to the library!");
        title.setBorder(BorderFactory.createEmptyBorder(15 ,15,15,15));
        title.setFont(new Font("Arial",Font.BOLD,20));
        frame.getContentPane().add(title,BorderLayout.NORTH);

        JLabel label = Main.label("Phone Number:");
        JLabel label1 = Main.label("Email:");
        JTextField phonenumber = Main.textField();
        JTextField email = Main.textField();
        JButton login = Main.button("Login");
        JButton signup = Main.button("Sign Up");

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
    private void signup() {
        JFrame frame = Main.frame(500,400);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5,2,15,15));
        panel.setBorder(BorderFactory.createEmptyBorder(10,15,20,15));

        JLabel title = Main.label("Sign Up");
        title.setBorder(BorderFactory.createEmptyBorder(15 ,15,15,15));
        title.setFont(new Font("Arial",Font.BOLD,20));
        frame.getContentPane().add(title,BorderLayout.NORTH);

        JLabel label = Main.label("Name:");
        JLabel label1 = Main.label("Phone Number:");
        JLabel label2 = Main.label("Email:");
        JTextField name = Main.textField();
        JTextField phonenumber = Main.textField();
        JTextField email = Main.textField();
        JRadioButton admin = Main.radioButton("Admin");
        JRadioButton customer = Main.radioButton("Customer");
        JButton createuser = Main.button("Create Account");
        JButton cancel = Main.button("Cancel");

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
        frame.setVisible(true);
    }
    private void login(String phonenumber, String email,JFrame frame){
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
    }

