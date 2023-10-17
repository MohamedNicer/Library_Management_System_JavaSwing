import java.util.Scanner;

public class BorrowBook implements IOOperation{
    @Override
    public void operation(Database database, User user) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter book title: ");
        String booktitle = scanner.next();
        int index = database.getBook(booktitle);
        boolean b = true;
        for (Borrowing borrowing: database.getAllBorrowings()){
            if (borrowing.getBook().getTitle().matches(booktitle) &&
                    borrowing.getUser().getName().matches(user.getName())){
                b = false;
                System.out.println("You've already borrowed this book!\n");
            }
        }
        if(b){
            if (index>-1){
                Book book = database.getBook(index);
                if(book.getBorrowcopies()>1){
                    Borrowing borrowing = new Borrowing(user,book);
                    book.setBorrowcopies(book.getBorrowcopies()-1);
                    database.borrowBook(borrowing,book,index);
                    System.out.println("\nThe book with title: "+ booktitle + " should be " +
                            "returned in the next 14 days\n" + "Expiry date: " +
                            borrowing.getFinish() + "\nEnjoy it\n");
                }else {
                    System.out.println("Book unavailable!");
                }
            } else{
                System.out.println("Book doesn't exist!");
            }
        }
        user.menu(database,user);

    }
}
