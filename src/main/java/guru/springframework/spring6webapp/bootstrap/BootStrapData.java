package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.domain.Publisher;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import guru.springframework.spring6webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    private  final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric  = new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Bosman");

        Book book = new Book();
        book.setTitle("How to be successful at spring");
        book.setIsbn("545454");

        eric.getBooks().add(book);
        book.getAuthors().add(eric);
        Author ericSaved = authorRepository.save(eric);
        Book bookSaved =bookRepository.save(book);


        ericSaved.getBooks().add(bookSaved);

        Publisher publisher = new Publisher();
        publisher.setPublisherName("Pearson");
        publisher.setAddress("3445 e main dr");
        Publisher savedPublisher = publisherRepository.save(publisher);

        book.setPublisher(publisher);
        bookRepository.save(bookSaved);
        authorRepository.save(eric);




        System.out.println("In Bootstrap");
        System.out.println("Author Count:" + authorRepository.count());
        System.out.println("Book Count:" + bookRepository.count());
        System.out.println("PUblisher Count:" + publisherRepository.count());

    }
}
