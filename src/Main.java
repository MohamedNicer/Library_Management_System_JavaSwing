import java.util.Scanner;

public class Main {
    static Scanner sc;
    static Database database;
    public static void main(String[] args) {
        database = new Database();
        System.out.println("Welcome to the Library!");
        int num;
        System.out.println("0.Exit\n1. Login\n2. Sign Up");
        sc = new Scanner(System.in);
        num = sc.nextInt();
        switch (num){
            case 1: login();
            case 2: signup();
        }
    }

    private static void signup() {
        System.out.println("Enter Full Name:");
        String name = sc.next();
        System.out.println("Enter Phone Number:");
        String phonenumber = sc.next();
        System.out.println("Enter Email:");
        String email = sc.next();
        System.out.println("1. Admin\n2. Customer");
        User user;
        int n = sc.nextInt();
        if (n==1){
            user = new Admin(name,email,phonenumber);
        } else{
            user = new Customer(name,email,phonenumber);
        }
        database.AddUser(user);
        user.menu(database,user);
    }

    private static void login(){
        System.out.println("Enter Phone Number:");
        String phonenumber = sc.next();
        System.out.println("Enter Email:");
        String email = sc.next();
        int n = database.login(phonenumber,email);
        if (n != -1){
            User user = database.getUser(n);
            user.menu(database,user);
        }else {
            System.out.println("User doesn't exist!");
        }
    }
}
