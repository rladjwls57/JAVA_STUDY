import java.util.ArrayList;

public class BookArrayList {
    private ArrayList<Book> arrayList;

    public BookArrayList() {
        arrayList = new ArrayList<Book>();
    }

    public void addBook(Book book) {
        arrayList.add(book);
    }

    public boolean removeBook(int BookId) {
        for (int i = 0; i < arrayList.size(); i++) {
            Book book = arrayList.get(i);
            int tempId = book.getBookId();
            if (tempId == BookId) {
                arrayList.remove(i);
                return true;
            }
        }
        System.out.println(BookId + "가 존재하지 않습니다.");
        return false;
    }

    public void insertBook(int index, Book book) {
        if (index >= 0 && index <= arrayList.size()) {
            arrayList.add(index, book);
            System.out.println(book.getBookName() + "이(가) " + index + "번 위치에 추가되었습니다.");
        } else {
            System.out.println("올바르지 않은 위치입니다.");
        }
    }

    public void showAllBook() {
        for (Book book : arrayList) {
            System.out.println(book);
        }
        System.out.println();
    }
}
