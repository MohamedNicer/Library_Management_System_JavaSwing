import java.util.Scanner;

public class AddBook implements IOOperation{
    @Override
    public void operation(Database database, User user) {

        Scanner scanner = new Scanner(System.in);
        Book book = new Book();
        System.out.println("Enter Book's title: ");
        book.setTitle(scanner.next());
        System.out.println("Enter Book's author: ");
        book.setAuthor(scanner.next());
        System.out.println("Enter Book's publisher: ");
        book.setPublisher(scanner.next());
        System.out.println("Enter Book's Collection address: ");
        book.setAddress(scanner.next());
        System.out.println("Enter Book's Quantity in Inventory: ");
        book.setQty(scanner.nextInt());
        System.out.println("Enter Book's Price: ");
        book.setPrice(scanner.nextDouble());
        System.out.println("Enter Book's Number of copies to borrow: ");
        book.setBorrowcopies(scanner.nextInt());
        database.AddBook(book);
        System.out.println("Book Added Successfully!\n");
        user.menu(database,user);
        scanner.close();
    }
}
