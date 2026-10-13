import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

// Варіант 48. Завдання 3.2. Controller.
public class Main {
    public static void main(String[] args) {
        BookModel model = new BookModel();
        BookView view = new BookView();
        Random random = new Random();
        String[] authors = {"Іваненко", "Петренко", "Коваль", "Гнатюк"};
        String[] publishers = {"Освіта", "Знання", "Світ", "Весна"};
        String[] years = {"2015", "2020", "2023", "2030"};
        view.message("Початкові книги:");
        view.show(model.books);

        while (true) {
            String choice = view.menu();
            if (choice.equals("0")) break;
            String value;
            if (choice.equals("1")) {
                value = authors[random.nextInt(authors.length)];
            } else if (choice.equals("2")) {
                value = publishers[random.nextInt(publishers.length)];
            } else if (choice.equals("3")) {
                value = years[random.nextInt(years.length)];
            } else if (choice.equals("4")) {
                model.sort();
                view.message("За видавництвом:");
                view.show(model.books);
                continue;
            } else {
                view.message("Введіть число від 0 до 4.");
                continue;
            }
            view.message("Пошук: " + value);
            view.show(model.find(choice, value));
        }
    }
}

class Book {
    String title, author, publisher;
    int year, pages;
    double price;

    public Book(String title, String author, String publisher,
                int year, int pages, double price) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
        this.pages = pages;
        this.price = price;
    }

    public String toString() {
        return title + " | " + author + " | " + publisher
                + " | " + year + " | " + pages + " | " + price;
    }
}

class BookModel {
    Book[] books = {
            new Book("Основи Java", "Іваненко", "Освіта", 2020, 250, 300),
            new Book("Алгоритми", "Петренко", "Знання", 2022, 320, 450),
            new Book("Бази даних", "Коваль", "Світ", 2019, 280, 350),
            new Book("Математика", "Іваненко", "Знання", 2015, 200, 220),
            new Book("Мережі", "Петренко", "Освіта", 2023, 310, 420),
            new Book("Веброзробка", "Коваль", "Світ", 2024, 350, 500),
            new Book("Фізика", "Іваненко", "Освіта", 2018, 190, 200),
            new Book("Історія", "Коваль", "Знання", 2021, 260, 280),
            new Book("Англійська мова", "Петренко", "Світ", 2017, 210, 250),
            new Book("Програмування", "Іваненко", "Освіта", 2025, 400, 600)
    };

    public Book[] find(String type, String value) {
        Book[] result = new Book[books.length];
        int count = 0;
        int year = 0;
        if (type.equals("3")) year = Integer.parseInt(value);

        for (int i = 0; i < books.length; i++) {
            boolean found = false;
            if (type.equals("1")) found = books[i].author.equals(value);
            if (type.equals("2")) found = books[i].publisher.equals(value);
            if (type.equals("3")) found = books[i].year > year;

            if (found) {
                result[count] = books[i];
                count++;
            }
        }
        return Arrays.copyOf(result, count);
    }

    public void sort() {
        Arrays.sort(books, new PublisherComparator());
    }
}

class PublisherComparator implements Comparator<Book> {
    public int compare(Book a, Book b) {
        return a.publisher.compareTo(b.publisher);
    }
}

class BookView {
    Scanner input = new Scanner(System.in);

    public void message(String text) {
        System.out.println(text);
    }

    public void show(Book[] books) {
        if (books.length == 0) {
            message("Книг не знайдено.");
            return;
        }
        message("Назва | Автор | Видавництво | Рік | Сторінки | Ціна (грн)");
        for (int i = 0; i < books.length; i++) {
            message(books[i].toString());
        }
    }

    public String menu() {
        message("\n1 - Автор\n2 - Видавництво\n3 - Після року\n4 - Сортування\n0 - Вихід");
        if (!input.hasNextLine()) return "0";
        return input.nextLine().trim();
    }
}