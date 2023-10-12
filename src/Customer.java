import java.util.Scanner;

public class Customer extends User{
    public Customer(String name) {
        super(name);
        this.operations = new IOOperation[]{
                new ViewBook(),
                new Search(),
                new PlaceOrder(),
                new BorrowBook(),
                new CalculateFine(),
                new ReturnBook(),
                new Exit()
        };
    }

    public Customer(String name, String email, String phonenumber) {
        super(name, email, phonenumber);
        this.operations = new IOOperation[]{
                new ViewBook(),
                new Search(),
                new PlaceOrder(),
                new BorrowBook(),
                new CalculateFine(),
                new ReturnBook(),
                new Exit()
        };
    }

    @Override
    public void menu(Database database, User user) {
        System.out.println("1. View Books");
        System.out.println("2. Search");
        System.out.println("3. Place Order");
        System.out.println("4. Borrow Book");
        System.out.println("5. Calculate Fine");
        System.out.println("6. Return Book");
        System.out.println("7. Exit");

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        this.operations[n-1].operation(database, user);
        sc.close();
    }
    @Override
    public String toString() {
        return name + "<N/>" + email + "<N/>" + phonenumber + "<N/>"+"Customer";
    }
}
