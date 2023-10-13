import java.util.ArrayList;

public class ViewBook implements IOOperation{
    @Override
    public void operation(Database database, User user) {
        ArrayList<Book> books = database.getAllBooks();
        System.out.println("Title\t\tAuthor\t\tPublisher\tCol-Address\tStatus\tQty"
                        +"\t\tPrice\tBor-Cps");
        for (Book b: books) {
            System.out.println(b.getTitle()+"\t\t"+b.getAuthor()+"\t\t\t"+ b.getPublisher()+"\t\t\t"
                    +b.getAddress()+"\t"+b.getStatus()+"\t"+b.getQty()+"\t\t"+b.getPrice()+"\t"+
                    b.getBorrowcopies()+"\t");
        }
        System.out.println();
        user.menu(database,user);
    }
}
