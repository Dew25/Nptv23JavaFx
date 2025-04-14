package ee.ivkhkdev.nptv23javafx.rating;

import ee.ivkhkdev.nptv23javafx.model.entity.Book;

public class BookRatingViewModel {
    private final Book book;
    private final Integer count;

    public BookRatingViewModel(Book book, Integer count) {
        this.book = book;
        this.count = count;
    }

    public Book getBook() {
        return book;
    }

    public String getTitle() {
        return book.getTitle(); // для отображения в колонке
    }

    public Integer getCount() {
        return count;
    }
}
