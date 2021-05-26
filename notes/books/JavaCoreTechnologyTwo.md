# java 核心技术卷二高级特性第10版

## 第一章 Java SE8 的流库
    流提供了一种比集合更高的概念级别上指定计算的数据视图。
    流遵循"做什么而非怎么做"的原则。

    从迭代到流的操作：
        流和集合都可以转换和获取数据，但是，它们存在显著差异。
            1、流并不存储其元素，这些元素可以存储做底层都集合中，或者是按需生产。
            2、流的操作不会修改其数据源。例如 filter 方法不会从新的流中移出元素，而是会生产一个新的流，其中不包含过滤掉的元素。
            3、流的操作是尽可能惰性执行的。

        工作流是操作流时的典型流程，包含三个阶段的操作管道：
            1、创建一个流。
            2、指定将初始流转换为其他流的中间操作，可能包含多个步骤。
            3、应用终止操作，从而产生结果。这个操作会强制执行之前的惰性操作。从此之后，这个流再也不能用了。

        java.util.stream.Stream<T>8
            Stream<T> filter(Predicate< ? super T> p);
                产生一个流，其中包含当前流中满足 P 的所有元素。
            long count();
                产生当前流中元素的数量，这是一个终止操作。

        Java.util.Collection<E> 1.2
            default Stream<E> stream()
            default Stream<E> parallelStream()
            产生当前集合中所有元素的顺序流或并行流。

    流的创建：
        Collection 接口的 stream 方法将任何集合转换为一个流。
        如果有一个数组，可以使用静态的 Stream.of() 方法。of方法具有可变长参数，因此我们可以构建具有任意数量引元的流。
            Stream<String> words = Stream.of(content.split("\\PL+")); 
            
            Array.stream(array, from, to)可以从数组中位于 from(包含)和to(不包含)的元素中创建一个流。
            Stream.empty() 创建不包含任何元素的流。

        Stream 接口中有两个用于创建无限流的静态方法：
            generate()方法会接受一个不包含任何引元的函数。
                Stream<String> echos = Stream.generate(() - > "Echo"); 获得一个常量流。
                Stream<Double> randoms = Stream.generate(Math::random); 获取一个随机数流。
            
            iterate()方法，它接受一个"种子"值 和一个函数，并且反复地将该函数应用到之前的结果上。
                Stream<BigInteger> integers = Stream.iterate(BigInteger.ZERO, n -> n.add(BigInteger.ONE));
                该序列第一个元素的种子 BigInteger.ZERO, 第二个元素是 f(seed),下一个元素是 f(f(seed)),类推。

        java.util.stream.Stream 8:
            static <T> Stream<T> of(T... values); 产生一个元素为给定值的流
            static <T> Stream<T> empty(); 产生一个不包含任何元素的流
            static <T> Stream<T> generate(Supplier<T> s); 产生一个无限流，它的值通过反复调用函数 s 而创建。
            static <T> Stream<T> iterate(T seed, UnaryOperator<T> f); 产生一个无限流，它的元素包含种子，中种子上调用f产生值。

        java.util.Arrays 1.2:
            static <T> Stream<T> stream(T[] array, int startInclusive, int endExclusive);
            产生一个流，它的元素有数组指定范围内的元素构造
        
        java.util.regex.Pattern 1.4
            Stream<String> splitAsStream(CharSequence input);
            产生一个流，它的元素是输入中由该模式界定的部分。

        java.nio.file.Files 7:
            static Steam<String> lines(path path); 8
            static Steam<String> lines(Path path, Charset cs); 8
            产生一个流，它的元素是指定文件中的行，该文件的字符集为 UTF-8

        java.util.function.Supplier<T> 8
            T get(); 提供一个值。

    filter、map 和 flatMap 方法：

        filter 转换会产生一个流，它的元素与某种条件相匹配。
        filter 的引元是 predicate<T>, 即从 T 到 boolean 的函数。
        map 会有一个函数应用到每个元素上，并且其结果是包含流应用该函数后所产生的多有结果的流。
        
        Stream<T> filter(Predicate<? super T> predicate);  
            产生一个流，它包含当前流中所有满足断言条件的元素。
        <R> Stream<R> map(Function<? super T, ? extends R> mapper);
            产生一个流，它包含将mapper 应用于当前流中所有元素锁产生的结果。
        <R> Stream<R> flatMap(Function<? super T, ? extends Steam<? extends R>> mapper)
            产生一流，它是通过将mapper应用于当前流中所产生的结果拼接到一起而获得的。

    抽取子流和链接流：
        Stream.limit(n) 会返回一个新的流，它在 n 元素之后结束。剪切无限流。
        Stream<Double> randoms = Stream.generate(Math::random).limit(100);
        Stream.skip(n) 正好相反，它会丢弃前 n 个元素。
        Stream.concat() 方法将两个流连起来。

        Stream<String> combined = Stream.concat(letters("Hello"), letters("World"));

    其他的流转换:
        distinct 方法会返回一个无重复数据的流。
        Stream<String> uniqueWords = Stream.of("merrily","merrily","gently","merrily").distinct();
        
    简单约简:
        约简是一种终结操作，将流约简为可以在程序中使用的非流值。返回类型是Optional<T>值。
        
        Optional<T> max(Comparator<? super T> comparator);
        Optional<T> min(Comparator<? super T> comparator);
            分别产生这个流的最大元素和最小元素，使用由给定比较器定义的排序规则，如果这个流为空，会产生一个Optioal对象。终止操作。
        Optional<T> findFirst();
        Optional<T> findAny();
            分别产生这个流的第一个和任意一个元素，如果这个流为空，会产生一个空的Optional对象。这些操作都是终结操作。
        
        boolean anyMatch(Predicate<? super T> predicate)
        boolean allMatch(Predicate<? super T> predicate)
        boolean noneMatch(Predicate<? super T> predicate)
            分别在这个流中任意元素、所有元素和没有任何元素匹配给定断言时返回 true。这些操作都是终结操作。

    Optional 类型:
        Optional<T> 对象时一种包装器对象，要么包装了类型T的对象，要么没有包装任何对象。  
                               
    收集结果:
                      
            

            
                
    

        
        




## 第二章 输入与输出

## 第三章 xml
				
## 第四章 网络

## 第五章 数据库编程

## 第六章 日期和时间 API

## 第七章 国际化

## 第八章 脚本、编译与注解处理

## 第九章 安全