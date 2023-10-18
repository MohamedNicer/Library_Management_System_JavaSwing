import javax.swing.*;
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
        String[] option = new String[7];
        option[0] = "View Books";
        option[1] = "Add Book";
        option[2] = "Delete Book";
        option[3] = "Search";
        option[4] = "Delete All Data";
        option[5] = "View Orders";
        option[6] = "Exit";
        JFrame frame = frame(option,database,user);
    }

    @Override
    public String toString() {
        return name + "<N/>" + email + "<N/>" + phonenumber + "<N/>"+"Admin";
    }
}
