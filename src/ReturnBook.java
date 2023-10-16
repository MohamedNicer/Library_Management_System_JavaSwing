import java.util.Scanner;

public class ReturnBook implements IOOperation{
    @Override
    public void operation(Database database, User user) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter book title: ");
        String booktitle = scanner.next();
        if(!database.getAllBorrowings().isEmpty()){
            for(Borrowing borrowing: database.getAllBorrowings()){
                if(borrowing.getBook().getTitle().matches(booktitle) &&
                borrowing.getUser().getName().matches(user.getName())){
                    Book book = borrowing.getBook();
                    int index = database.getAllBooks().indexOf(book);
                    if(borrowing.getDaysleft()<0){
                        System.out.println("You're late!\n" + "As of today! Your fine is: " +
                                Math.abs(borrowing.daysleft*20) + "\n" );
                    }
                    book.setBorrowcopies(book.getBorrowcopies()+1);
                    database.returnBook(borrowing,book, index);
                    System.out.println("Thank you for returning the book!\n");
            }
        }
    }else {
            System.out.println("You've borrowed this book!");
        }
        user.menu(database, user);
    }
}