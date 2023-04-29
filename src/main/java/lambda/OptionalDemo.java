package lambda;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class OptionalDemo {

    public static Author getAuthor() {
        Author author = new Author(1L, "超人", 30,"SuperMan" ,Lists.newArrayList());
        return author;
    }

    public static Optional<Author> getAuthorOptional() {
        Author author = new Author(1L, "蝙蝠侠", 50,"BatMan" ,Lists.newArrayList());
        return Optional.ofNullable(author);
    }

    public static Optional<Author> getOf() {
        Author author = null;
        return Optional.of(author);
    }



    /**
     * Optional.ofNullable(T); 此方法无论传入的对象是否为空 都不会空指针
     */
    public static void main(String[] args) {
//        Author author = getAuthor();
//        Optional<Author> author1 = Optional.ofNullable(author);
//        author1.ifPresent(author22 -> System.out.println(author22.getName()));
//        Optional<Author> authorOptional = getAuthorOptional();
//        authorOptional.ifPresent(author2 -> System.out.println(author2.getName()));
//        Optional<Author> of = getOf();
//
        Optional<Author> authorOptional1 = getAuthorOptional();
        Author author2 = authorOptional1.orElseGet(new Supplier<Author>() {
            @Override
            public Author get() {
                return new Author(2L, "神奇女侠", 5000, "女神", Lists.newArrayList());
            }
        });
        System.out.println(author2.toString());


    }
}
