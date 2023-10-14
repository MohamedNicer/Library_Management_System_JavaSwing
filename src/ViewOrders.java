import java.util.ArrayList;
import java.util.Scanner;

public class ViewOrders implements IOOperation{
    @Override
    public void operation(Database database, User user) {
        System.out.println("Enter book's title: ");
        Scanner scanner = new Scanner(System.in);
        String booktitle = scanner.next();
        int index = database.getBook(booktitle);
        if(index > -1){
            System.out.println("User\t\tBook\t\tQty\tPrice");
            for (Order order: database.getAllOrders()){
                if(order.getBook().getTitle().matches(booktitle)) {
                    System.out.println(order.getUser().getName() + "\t\t" + order.getBook().getTitle()
                            + "\t\t" + order.getBook().getQty() + "\t" + order.getBook().getPrice());
                }
            }
            System.out.println();
        } else {
            System.out.println("Book doesn't exist!");
        }
        user.menu(database, user);
    }
}
