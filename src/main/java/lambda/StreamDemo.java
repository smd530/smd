package lambda;

import com.google.common.collect.Lists;
import lambda.Author;
import lambda.Book;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamDemo {

    /**
     * Stream操作
     * 1. 创建Stream
     * 2. 各种中间操作
     * 3. 终结操作
     * 没有终结操作不启动
     * 4.单列集合 List Array
     * 5.双列集合 Map 先转化成单列再创建流 map.entrySet()
     */
    public static void main(String[] args) {
        List<Author> authors = getAuthors();
//        List<Author> collect = authors.stream()
//                .filter(author -> author.getAge() < 18)
//                .distinct()
//                .collect(Collectors.toList());
        // 数组转流
//        arrToStream();

        // 双列map转单列集合
//        mapToEntrySet();

        // 打印所有姓名长度大于1的作家姓名 用filter()
//        getAuthorNameLengthMorethanOne(authors);

        // 打印所有作家的名字 使用map()
//        getAuthorNameByMap(authors);
        // 打印map并且计算
//        getAuthorAgeListByMap(authors);

        // 去重作家姓名 distinct内部是靠equals方法来实现的 记得重写equals
//        getAuthorNameDistinct(authors);

        // 对流中的元素按照年龄进行降序排序 并且不能重复
//        getSortedAuthorsDistinct(authors);

        // 对流中的元素按照年龄排序 并且不能重复 然后打印年龄最大的两个作家名字
//        getAuthorNameByLimit(authors);

        // 打印除了年龄最的作家外的其他作家 不能有重复元素 并且按照年龄排序
//        getAuthorExpectOldest(authors);

        // flatMap 可以把一个对象转换成多个对象作为流中的元素 比如一个作家有多本书 把书都拿出来
//        userFlatMap(authors);

        // 拿到所有数据的分类 对分类进行去重 不能出现：哲学,爱情
//        getCategory(authors);

        // 统计所有作家的书并去重
//        getAllBookDistinct(authors);

        // 分别获取这些作家所出书籍的最高分和最低分
//        getBooksMaxScoreAndMinScore(authors);

        // 获取一个存放所有作者名字的List集合
//        getAuthorNameList(authors);

        // 获取一个所有书名的Set集合
//        getBookNameSet(authors);

        // 获取一个map map的key为作者名 value为List<Book> 参数是两个Function
//        getAuthorNameBookListMap(authors);

    }

    /**
     * 获取一个map map的key为作者名 value为List<Book> 参数是两个Function
     * @param authors
     */
    private static void getAuthorNameBookListMap(List<Author> authors) {
        Map<String, List<Book>> nameBookListMap = authors.stream()
                .distinct()
                .collect(Collectors.toMap(Author::getName, Author::getBooks));
        for (Map.Entry<String, List<Book>> stringListEntry : nameBookListMap.entrySet()) {
            System.out.println(stringListEntry.getKey());
            System.out.println(stringListEntry.getValue().toString());
        }
    }

    /**
     * 获取一个所有书名的Set集合
     * @param authors
     */
    private static void getBookNameSet(List<Author> authors) {
        Set<String> bookNameSet = authors.stream()
                .flatMap((Function<Author, Stream<Book>>) author -> author.getBooks().stream())
                .map(Book::getName)
                .collect(Collectors.toSet());
        for (String bookName : bookNameSet) {
            System.out.println(bookName);
        }
    }

    /**
     * 获取一个存放所有作者名字的List集合
     * @param authors
     */
    private static void getAuthorNameList(List<Author> authors) {
        List<String> authorName = authors.stream()
                .map(Author::getName)
                .collect(Collectors.toList());
        for (String name: authorName) {
            System.out.println(name);
        }
    }

    /**
     * 分别获取这些作家所出书籍的最高分和最低分
     * ****
     * ****
     * 一个流经过一次终结操作之后就关闭了
     * 想要另一个终结操作需要再创建一个流
     * @param authors
     */
    private static void getBooksMaxScoreAndMinScore(List<Author> authors) {
        Optional<Integer> max = authors.stream()
                .flatMap((Function<Author, Stream<Book>>) author -> author.getBooks().stream())
                .map(Book::getScore)
                .max((o1, o2) -> o1 - o2);

        Optional<Integer> min = authors.stream()
                .flatMap((Function<Author, Stream<Book>>) author -> author.getBooks().stream())
                .map(Book::getScore)
                .min((o1, o2) -> o1 - o2);
        max.ifPresent(System.out::println);
        min.ifPresent(System.out::println);
    }


    /**
     * 统计所有作家的书并去重
     * @param authors
     */
    private static void getAllBookDistinct(List<Author> authors) {
        long count = authors.stream()
                .flatMap((Function<Author, Stream<Book>>) author -> author.getBooks().stream())
                .distinct()
                .count();
        System.out.println(count);
    }

    /**
     * 拿到所有数据的分类 对分类进行去重 不能出现：哲学,爱情
     * * @param authors
     */
    private static void getCategory(List<Author> authors) {
//        authors.stream().flatMap(new Function<Author, Stream<Book>>() {
//            @Override
//            public Stream<Book> apply(Author author) {
//                return author.getBooks().stream().filter((book) -> {return !book.getCategory().equals("哲学,爱情");});
//            }
//        }).map(new Function<Book, String>() {
//            @Override
//            public String apply(Book book) {
//                return book.getCategory();
//            }
//        }).distinct().forEach(new Consumer<String>() {
//            @Override
//            public void accept(String category) {
//                System.out.println(category);
//            }
//        });
        authors.stream()
                .flatMap(author -> author.getBooks().stream().distinct())
                .flatMap(book -> Arrays.stream(book.getCategory().split(",")))
                .distinct()
                .forEach(System.out::println);
    }


    /**
     * flatMap 可以把一个对象转换成多个对象作为流中的元素
     * @param authors
     */
    private static void userFlatMap(List<Author> authors) {
        authors.stream()
                .flatMap((Function<Author, Stream<Book>>) author -> author.getBooks()
                        .stream()).forEach(book -> System.out.println(book.getName()));

    }

    /**
     * 打印除了年龄最的作家外的其他作家 不能有重复元素 并且按照年龄排序
     * @param authors
     */
    private static void getAuthorExpectOldest(List<Author> authors) {
        authors.stream()
                .distinct()
                .sorted((o1, o2) -> o2.getAge() - o1.getAge())
                .skip(1)
                .forEach((author -> {
                    System.out.println(author.getName());
                }));
    }

    /**
     * 数组转流
     */
    private static void arrToStream() {
        int [] arr = {1,2,3,4,4,5,5,6};
        IntStream arrs = Arrays.stream(arr);
        arrs.distinct().filter((i) -> {
            return i > 2;
        }).forEach(System.out::println);
    }

    /**
     * 双列map转单列集合
     */
    private static void mapToEntrySet() {
        Map<String, Integer> map = new HashMap<>();
        map.put("超人", 20);
        map.put("蝙蝠侠", 30);
        map.put("神奇女侠", 5000);
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        entries.stream()
                .filter((entry) -> {
            return entry.getValue() < 5000;})
                .forEach(entry -> System.out.println(entry.getKey()));
    }

    /**
     * 打印所有姓名长度大于1的作家姓名 用filter()
     * @param authors
     */
    private static void getAuthorNameLengthMoreThanOne(List<Author> authors) {
        List<Author> collect = authors.stream()
                .filter(author -> author.getName().length() > 1)
                .collect(Collectors.toList());
        for (Author author : collect) {
            System.out.println(author.toString());
        }
    }

    /**
     * 打印所有作家的名字 使用map()
     * @param authors
     */
    private static void getAuthorNameByMap(List<Author> authors) {
        List<String> collect = authors.stream()
                .map((author) -> {
            return author.getName();})
                .collect(Collectors.toList());
        for (String s : collect) System.out.println(s);
    }

    /**
     * 打印map并且计算
     * @param authors
     */
    private static void getAuthorAgeListByMap(List<Author> authors) {
        Stream<Author> stream = getAuthorStream(authors);
        stream.map(author -> author.getAge()).map(age -> age + 10).forEach(System.out::println);
    }

    private static Stream<Author> getAuthorStream(List<Author> authors) {
        Stream<Author> stream = authors.stream();
        return stream;
    }

    /**
     * 去重作家姓名 distinct内部是靠equals方法来实现的 记得重写equals
     * @param authors
     */
    private static void getAuthorNameDistinct(List<Author> authors) {
        authors.stream()
                .map(Author::getName)
                .distinct()
                .forEach(System.out::println);
    }

    /**
     * 对流中的元素按照年龄进行降序排序 并且不能重复
     * @param authors
     */
    private static void getSortedAuthorsDistinct(List<Author> authors) {
        authors.stream()
                .distinct()
                .sorted((o1, o2) -> o2.getAge() - o1.getAge())
                .forEach(System.out::println);
    }

    /**
     * // 对流中的元素按照年龄排序 并且不能重复 然后打印年龄最大的两个作家名字
     * @param authors
     */
    private static void getAuthorNameByLimit(List<Author> authors) {
        authors.stream()
                .distinct()
                .sorted((o1, o2) -> o2.getAge() - o1.getAge())
                .limit(2)
                .forEach((author) -> System.out.println(author.getName()));
    }


    private static List<Author> getAuthors() {
        //数据初始化
        Author author = new Author(1L,"蒙多",33,"一个从菜刀中明悟哲理的祖安人",null);
        Author author2 = new Author(2L,"亚拉索",15,"狂风也追逐不上他的思考速度",null);
        Author author3 = new Author(3L,"易",14,"是这个世界在限制他的思维",null);
        Author author4 = new Author(3L,"易",14,"是这个世界在限制他的思维",null);

        //书籍列表
        List<Book> books1 = new ArrayList<>();
        List<Book> books2 = new ArrayList<>();
        List<Book> books3 = new ArrayList<>();

        books1.add(new Book(1L,"刀的两侧是光明与黑暗","哲学,爱情",88,"用一把刀划分了爱恨"));
        books1.add(new Book(2L,"一个人不能死在同一把刀下","个人成长,爱情",99,"讲述如何从失败中明悟真理"));

        books2.add(new Book(3L,"那风吹不到的地方","哲学",85,"带你用思维去领略世界的尽头"));
        books2.add(new Book(3L,"那风吹不到的地方","哲学",85,"带你用思维去领略世界的尽头"));
        books2.add(new Book(4L,"吹或不吹","爱情,个人传记",56,"一个哲学家的恋爱观注定很难把他所在的时代理解"));

        books3.add(new Book(5L,"你的剑就是我的剑","爱情",56,"无法想象一个武者能对他的伴侣这么的宽容"));
        books3.add(new Book(6L,"风与剑","个人传记",100,"两个哲学家灵魂和肉体的碰撞会激起怎么样的火花呢？"));
        books3.add(new Book(6L,"风与剑","个人传记",100,"两个哲学家灵魂和肉体的碰撞会激起怎么样的火花呢？"));

        author.setBooks(books1);
        author2.setBooks(books2);
        author3.setBooks(books3);
        author4.setBooks(books3);

        List<Author> authorList = new ArrayList<>(Arrays.asList(author,author2,author3,author4));
        return authorList;
    }

}
