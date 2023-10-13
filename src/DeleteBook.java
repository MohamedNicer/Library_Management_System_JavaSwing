import java.util.Scanner;

public class DeleteBook implements IOOperation{
    @Override
    public void operation(Database database, User user) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter book title: ");
        String booktitle = scanner.next();
        int index = database.getBook(booktitle);
        if (index>-1){
            database.deleteBook(index);
            System.out.println("Book deleted successfully!");
        } else{
            System.out.println("Book doesn't exist!");
        }
        user.menu(database,user);
    }
}
