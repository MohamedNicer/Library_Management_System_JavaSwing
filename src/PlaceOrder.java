import java.util.Scanner;

public class PlaceOrder implements IOOperation{
    @Override
    public void operation(Database database, User user) {
        Order order = new Order();
        System.out.println("Enter book's title: ");
        Scanner scanner = new Scanner(System.in);
        String booktitle = scanner.next();
        int index = database.getBook(booktitle);
        if(index <= -1){
            System.out.println("Book doesn't exist!");
        } else {
            Book book = database.getBook(index);
            order.setBook(book);
            order.setUser(user);
            System.out.println("Enter the quantity that you want: ");
            int qty = scanner.nextInt();
            order.setQty(qty);
            order.setPrice(book.getPrice()*qty);
            int bookindex = database.getBook(book.getTitle());
            book.setQty(book.getQty()-qty);
            database.addOrder(order,book,bookindex);
            System.out.println("Order placed successfully!");
        }
        user.menu(database, user);
    }
}
