import java.util.Scanner;

public class CalculateFine implements IOOperation{
    @Override
    public void operation(Database database, User user) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter book title: ");
        String booktitle = scanner.next();
        boolean g = true;
        for (Borrowing borrowing: database.getAllBorrowings()){
            if(borrowing.getBook().getTitle().matches(booktitle) &&
                    borrowing.getUser().getName().matches(user.getName())){
                if(borrowing.getDaysleft()>0){
                    System.out.println("You're late!\n" + "As of today! Your fine is: " +
                            borrowing.daysleft*20 + "\n" );
                } else {
                    System.out.println("You've nothing additional to pay!");
                }
                g = false;
                break;
            }
        }
        if(g){
            System.out.println("You didn't borrow this book!");
        }
        user.menu(database, user);
    }
}
