import javax.swing.*;

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
        String[] option = new String[7];
        option[0] = "View Books";
        option[1] = "Search";
        option[2] = "Place Order";
        option[3] = "Borrow Book";
        option[4] = "Calculate Fine";
        option[5] = "Return Book";
        option[6] = "Exit";
        JFrame frame = frame(option,database,user);
    }
    @Override
    public String toString() {
        return name + "<N/>" + email + "<N/>" + phonenumber + "<N/>"+"Customer";
    }
}
