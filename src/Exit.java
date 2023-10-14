import java.util.Scanner;

public class Exit implements IOOperation{
    Scanner scanner;
    Database database;
    User user;
    @Override
    public void operation(Database database, User user) {
        this.database = database;
        this.user = user;
        System.out.println("Are you sure?\n" + "1.Yes\n2.Main Menu");
        scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        if(choice==1){
            System.out.println("0. Exit\n1. Login\n2. Sign Up");
            int num = scanner.nextInt();
            switch (num){
                case 1: login();break;
                case 2: signup();break;
            }

        } else user.menu(database, user);
    }
    private  void signup() {
        System.out.println("Enter Full Name:");
        String name = scanner.next();
        System.out.println("Enter Phone Number:");
        String phonenumber = scanner.next();
        System.out.println("Enter Email:");
        String email = scanner.next();
        System.out.println("1. Admin\n2. Customer");
        User user;
        int n = scanner.nextInt();
        if (n==1){
            user = new Admin(name,email,phonenumber);
        } else{
            user = new Customer(name,email,phonenumber);
        }
        database.AddUser(user);
        user.menu(database,user);
    }

    private void login(){
        System.out.println("Enter Phone Number:");
        String phonenumber = scanner.next();
        System.out.println("Enter Email:");
        String email = scanner.next();
        int n = database.login(phonenumber,email);
        if (n != -1){
            User user = database.getUser(n);
            user.menu(database,user);
        }else {
            System.out.println("User doesn't exist!");
        }
    }
}
