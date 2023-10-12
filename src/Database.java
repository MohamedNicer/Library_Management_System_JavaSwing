import java.io.*;
import java.util.ArrayList;

public class Database {
    private ArrayList<User> users = new ArrayList<User>();
    private ArrayList<String> usernames = new ArrayList<String>();
    private ArrayList<Book> books = new ArrayList<Book>();
    private ArrayList<String> booktitles = new ArrayList<String>();
    private File usersfile = new File("/Users/mohamednicer/Documents/IntellijIDEA/Library_Management_System_JavaSwing/data/Users");
    private File booksfile = new File("/Users/mohamednicer/Documents/IntellijIDEA/Library_Management_System_JavaSwing/data/Books");
    private File folder = new File("/Users/mohamednicer/Documents/IntellijIDEA/Library_Management_System_JavaSwing/data");

    public Database() {
        if (!folder.exists()) {
            folder.mkdirs();
        }
        if (!usersfile.exists()) {
            try {
                usersfile.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!booksfile.exists()) {
            try {
                booksfile.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        getUsers();
        getBooks();
    }

    public void AddUser(User user) {
        users.add(user);
        usernames.add(user.getName());
        saveUsers();
    }

    public int login(String phonenumber, String email) {
        int n = -1;
        for (User s : users) {
            if (s.getPhonenumber().matches(phonenumber) && s.getEmail().matches(email)) {
                n = users.indexOf(s);
                //System.out.println(n);
                break;
            }
        }
        //System.out.println(n);
        return n;
    }

    public User getUser(int n) {
        return users.get(n);
    }

    public void AddBook(Book book) {
        books.add(book);
        booktitles.add(book.getTitle());
        saveBooks();
    }

    private void getUsers() {
        StringBuilder text = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(usersfile));
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                text.append(str);
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.err.println(e.toString());
        }
        if (!text.toString().matches("") || (!text.isEmpty())) {
            String[] s1 = text.toString().split("<NewUser/>");
            for (String s2 : s1) {
                String[] s3 = s2.split("<N/>");
                if (s3[3].matches("Admin")) {
                    User user = new Admin(s3[0], s3[1], s3[2]);
                    users.add(user);
                    usernames.add(user.getName());
                } else {
                    User user = new Customer(s3[0], s3[1], s3[2]);
                    users.add(user);
                    usernames.add(user.getName());
                }
            }
        }
    }

    public void saveUsers() {
        StringBuilder text1 = new StringBuilder();
        for (User user : users) {
            text1.append(user.toString()).append("<NewUser/>\n");
        }
        try {
            PrintWriter printWriter = new PrintWriter(usersfile);
            printWriter.print(text1);
            printWriter.close();
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    public void saveBooks() {
        StringBuilder text1 = new StringBuilder();
        for (Book book : books) {
            text1.append(book.toString2()).append("<NewBook/>\n");
        }
        try {
            PrintWriter printWriter = new PrintWriter(booksfile);
            printWriter.print(text1);
            printWriter.close();
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    private void getBooks() {
        StringBuilder text = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(booksfile));
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                text.append(str);
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.err.println(e.toString());
        }
        if (!text.toString().matches("") || (!text.isEmpty())) {
            String[] s1 = text.toString().split("<NewBook/>");
            for (String s2 : s1) {
                Book book = parseBook(s2);
                books.add(book);
                booktitles.add(book.getTitle());
            }
        }
    }
    public ArrayList<Book> getAllBooks(){
        return books;
    }
    public Book parseBook(String str){
        String[] strings = str.split("<N/>");
        Book book = new Book();
        book.setTitle(strings[0]);
        book.setAuthor(strings[1]);
        book.setPublisher(strings[2]);
        book.setAddress(strings[3]);
        book.setQty(Integer.parseInt(strings[4]));
        book.setPrice(Double.parseDouble(strings[5]));
        book.setBorrowcopies(Integer.parseInt(strings[6]));
        return book;
    }
}

