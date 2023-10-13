import java.util.Scanner;

public class Search implements IOOperation{
    @Override
    public void operation(Database database, User user) {
        System.out.println("Enter Book's Title: ");
        Scanner scanner = new Scanner(System.in);
        String booktitle = scanner.next();
        int index = database.getBook(booktitle);
        if (index>-1){
            System.out.println("\n" + database.getBook(index).toString() + "\n");
        } else{
            System.out.println("Book doesn't exist!");
        }
        user.menu(database,user);
    }
}
