public class Order {
    private User user;
    private Book book;
    private int qty;
    private double price;

    public Order() {
    }

    public Order(User user, Book book, int qty, double price) {
        this.user = user;
        this.book = book;
        this.qty = qty;
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Username: " + user.getName() + "\n"+
                "Book Title :" + book.getTitle() + "\n" +
                "Price: " + String.valueOf(price) + "\n"+
                "Qty: " + String.valueOf(qty) ;
    }
    public String toString2(){
        return user.getName()+"<N/>"+book.getTitle()+"<N/>"+
                String.valueOf(qty)+"<N/>"+ String.valueOf(price);
    }
}
