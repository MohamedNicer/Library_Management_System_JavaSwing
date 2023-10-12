import java.util.Scanner;

public class Admin extends User{
    public Admin(String name){
        super(name);
        this.operations = new IOOperation[]{
                new ViewBook(),
                new AddBook(),
                new DeleteBook(),
                new Search(),
                new DeleteAllData(),
                new ViewOrders(),
                new Exit()
        };
    }

    public Admin(String name, String email, String phonenumber) {
        super(name, email, phonenumber);
        this.operations = new IOOperation[]{
                new ViewBook(),
                new AddBook(),
                new DeleteBook(),
                new Search(),
                new DeleteAllData(),
                new ViewOrders(),
                new Exit()
        };
    }

    @Override
    public void menu(Database database, User user) {
        System.out.println("1. View Books");
        System.out.println("2. Add Book");
        System.out.println("3. Delete Book");
        System.out.println("4. Search");
        System.out.println("5. Delete All Data");
        System.out.println("6. View Orders");
        System.out.println("7. Exit");

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        this.operations[n-1].operation(database, user);
        sc.close();
    }

    @Override
    public String toString() {
        return name + "<N/>" + email + "<N/>" + phonenumber + "<N/>"+"Admin";
    }
}
