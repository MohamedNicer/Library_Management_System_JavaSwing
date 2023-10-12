public class Book {
    private String title ;
    private String author;
    private String publisher;
    private String address;
    private String status;
    private int qty; // Copies up for sale
    private double price;
    private int borrowcopies; // Copies available for borrowing

    public Book() {
    }

    public Book(String title, String author, String publisher, String address, int qty, double price, int borrowcopies) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.address = address;
        this.qty = qty;
        this.price = price;
        this.borrowcopies = borrowcopies;
    }

    @Override
    public String toString() {
        return "Book{" +
                "Book Title='" + title + '\'' +
                ",Book Author='" + author + '\'' +
                ",Book Publisher='" + publisher + '\'' +
                ",Book Collection Address='" + address + '\'' +
                ", Qty=" + String.valueOf(qty) +
                ", Price=" + String.valueOf(price) +
                ", Borrowing copies=" + String.valueOf(borrowcopies) +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getAddress() {
        return address;
    }

    public String getStatus() {
        return status;
    }

    public int getQty() {
        return qty;
    }

    public double getPrice() {
        return price;
    }

    public int getBorrowcopies() {
        return borrowcopies;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setBorrowcopies(int borrowcopies) {
        this.borrowcopies = borrowcopies;
    }
}

