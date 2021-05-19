# java 核心技术卷-基础知识第10版


## 第四章 对象与类

    




## 第六章 接口、lambda 表达式与内部类

    6.1.1 接口(interface)技术
        
        接口主要用来描述类具有什么功能，而并给出每个功能的具体实现。
        接口不是类，而是对类的一组需求的描述，这些类要遵循从接口描述的统一格式进行定义。
        更重要的是接口不能提供哪些功能: 接口绝不能包含实例域。
    
    6.1.2 接口特性
        
        接口不是类，不能使用new运算符实例化接口。但可以声明接口变量
        Comparable x; // ok
        接口变量必须引用实现了接口的类对象。
        x = new Employee(...);   // ok provided Employee implement Comparable
        
        如同使用 instanceof 检查一个对象是否属于某个特定类一样，也可以使用 instanceof 检查一个对象是否实现了特定接口。
        if(anObject instanceof Comparable){}
        
        与建立类的继承关系一样，接口也可以被扩展。
        public interface Powerd extends Moable{}
        
        一个类可以实现多个接口，中间用逗号隔开。
        class Employee implement Colonable, Comparable
        
    6.1.3 接口和抽象类
    
        抽象类: 抽象类表示通用属性存在这样一个问题:每个类只能扩展于一个类。类可以实现多个接口。
    
    6.1.4 静态方法
        
        Java SE 8 中，允许在接口中增加静态方法。
        通常的做法都是将静态方法放在伴随类中，在标准库中，成对出现的接口和使用工具类。Collection/Collections, Path/Paths。
        
        在Java SE 8中，静态方法可以写在接口中，不再需要为实用工具方法另外提供一个伴随类。
        
        Java SE 8 新特性:
            
            Lambda 表达式 - Lambda 允许把函数做为一个方法的参数。
            
            方法引用 - 方法引用提供了非常有用的语法，可以直接引用已有的Java类或对象（实例）的方法或构造器。与lambda联合使用，
                     方法引用可以使语言结构更紧凑简洁、减少代码冗余。
            
            默认方法 - 默认方法就是一个在接口里面有了一个实现的方法
            
            新工具 − 新的编译工具，如：Nashorn引擎 jjs、 类依赖分析器jdeps。
            
            Stream API −新添加的Stream API（java.util.stream） 把真正的函数式编程风格引入到Java中。
            
            Date Time API − 加强对日期与时间的处理。
            
            Optional 类 − Optional 类已经成为 Java 8 类库的一部分，用来解决空指针异常。
            
            Nashorn, JavaScript 引擎 − Java 8提供了一个新的Nashorn javascript引擎，它允许我们在JVM上运行特定的javascript应用。          
                             
        
    6.3.1 lambda 表达式
    
    6.3.1 为什么要引入 lambda 表达式
        
        lambda 表达式是一个可传递的代码块，可以在以后执行一次或多次。
        
        sort 方法可以传入一个 Comparator 对象:
       
            Class LengthComparator implements Comparator<String>{
                public int compare(String first, String second){
                return first.length() - second.length();    
                }    
            }
            ....
            Arrays.sort(String, new LengthComparator());
            
         compare 方法不是立即调用。 实际上， 在数组完成排序之前,sort 方法会一直调用
         compare 方法，只要元素的顺序不正确就会重新排列元素。   
         
         这个例子，是将一个代码块传递到某个对象（sort方法）。这个代码块会在将来某个时间调用。
         到目前为止，在Java中传递一个代码段并不容易，不能直接传递代码段。java是一个面向对象的语言。
         所以必须构造一个对象，这个对象的类需要有一个方法能包含所需的代码。
         
    6.3.2 lambda 表达式的语法
    
        再来考虑上一节讨论的排序例子。我们传入代码来检查一个字符串是否比另一个字符串
        短。这里要计算:
            first.lenth() - second.length();
        java是强类型语言，我们需要指定类型
            (String first, String second)->first.lenth() - second.length();
        这就是一个lambda表达式。lambda就是一个代码块，以及必须传入代码的变量规范。
        
        带参数变量的表达式被成为 lambda 表达式。
        
        java中的一种 lambda 表现形式: 参数，箭头(->)以及一个表达式。
        如果代码要完成的计算无法放在一个表达式中，就可以像写方法一样，把代码写在{}中，并包含显示的 return 语句。
        
            (String first, String second) -> {
                
                if(first.length() < second.length()) return -1;
                else if(first.length() > second.length()) return 1;
                elset return 0;
            }
            
        即使 lambda 表达式没有参数， 仍然要提供空括号，就像无参数方法一样： 
            () -> { for (int i = 100;i >= 0;i ) System.out.println(i); }    
        
        如果可以推导出一个 lambda 表达式的参数类型，则可以忽略其类型。
        如果方法只有一 参数， 而且这个参数的类型可以推导得出，那么甚至还可以省略小括号。
                
        无需指定 lambda 表达式的返回类型。lambda 表达式的返回类型总是会由上下文推导得
        出。例如，下面的表达式
            (String first, String second) -> first.length() - second.length()
        可以在需要 int 类型结果的上下文中使用
        
        
        如果一个 lambda 表达式只在某些分支返回一个值， 而在另外一些分支不返回值，
        这是不合法的。例如，（int x)-> { if(x >= 0) return 1; } 就不合法。
            
            
    6.3.3 函数式接口:
    
        java 中已经有很多封装代码块的接口，如果Comparator。lambda 表达式与这些接口是兼容的。
        对于只有一个抽象方法的接口，需要这种接口对象时，就可以提供一个lambda表达式。这种接口称为函数式接口。
        
        为了展示如何转换为函数式接口，下面考虑 Arrays.sort 方法。
        它的第二个参数需要一个 Comparator 实例， Comparator 就是只有一个方法的接口， 所以可以提供一个 lambda 表达式：
        Arrays.sort (words, (first, second) -> first.length() - second.length()) ;
        在底层， Arrays.sort 方法会接收实现了 Comparator<String> 的某个类的对象。
        在这个对象上调用 compare 方法执行这个 lambda 表达式的体。
        
        最好把 lambad 表达式看作是一个函数，而不是对象，接受 lambda表达式可以传递到函数式接口。
        lambda 表达式可以转换为接口，这一点让 lambda 表达式很有吸引力。
        在Java中， 对lambda 表达式所能做的也只是能转换为函数式接口。
        不能把 lambda 表达式赋给类型为 Object 的变量，Object 不是一个函数式接口。
        想要用 lambda 表达式做处理，还要谨记表达式用途，为它建立一个特定的函数式接口。
        
    6.3.4 方法引用:
        
        方法引用使用 :: 操作符分隔方法名与对象或类名。主要有 3 种情况。
            object::instanceMethod
            Class::staticeMethod
            Class::instanceMethod
        
        在前 2 种情况中，方法引用等价于提供方法参数的 lambda 表达式。前面已经提到，
        System.out::println 等价于 x -> System.out.println(x)。 
        类似地，Math::pow 等价于（x，y) -> Math.pow(x, y)。
        对于第 3 种情况，第 1 个参数会成为方法的目标。例如, String::compareToIgnoreCase 等
        同于 (x, y) -> x.compareToIgnoreCase(y) 0   
        
    6.3.6 变量作用域
    
        lambda 表达式有3个部分:
            a、一个代码块
            b、参数
            c、自由变量的值，这个是指非参数而且不在代码中定义的值。可以把l lambda 表达式转换为包含一个方法的对象
               这个自由变量的值就会复制到这个对象的实例变量中。
                
              lambda 表达式可以捕获外围作用域中变量的值。 在 Java 中，要确保所捕获的值是明确定义的，这里有一个重要的限制。
              在 lambda 表达式中， 只能引用值不会改变的变量。  之所以有这个限制是有原因的。
              如果在 lambda 表达式中改变变量， 并发执行多个动作时就会不安全。      
              
              另外如果在 lambda 表达式中引用变量， 而这个变量可能在外部改变，这也是不合法的。
              lambda 表达式中捕获的变量必须实际上是最终变量 ( effectivelyfinal)。
              实际上的最终变量是指，这个变量初始化之后就不会再为它赋新值。
        
              lambda 表达式的体与嵌套块有相同的作用域。
    
    6.3.7 处理 lambda 表达式
        
            lambda 表达式重点是 延迟执行。
                在一个单独的线程中运行代码
                多次运行代码
                在算法适当位置运行代码(例如: 排序中的比较操作)
                发生某种情况时执行代码
                只在必要时才运行代码
                
    6.4 内部类:
            
        内部类是定义在另一个类中的类。
        使用内部类的三个原因：
            内部类方法可以访问该类定义的作用域中的数据，包括私有的数据
            内部类可以对同一个包中的其他类隐藏起来
            当想定义一个回调函数且不想编写大量代码时，使用匿名内部类比较便捷。

        内部类特殊语法规则：
            编写内部对象的构造器： outerObject.new InnerClass(Construction Parameters)

        6.4.3 内部类是否有用、必要、安全
            内部类是一种编译器现象，与虚拟机无关。编译器将把内部类翻译成$符分隔外部类与内部类的常规类文件，而虚拟机则对此一无所知。

        6.4.6 匿名内部类
            假如只创建这个类的一个对象，就不必命名了，这种类称为匿名内部类。

            语法： 
                new SuperType(Construction parameters){
                    inner class methods and data;
                }
            
            构造一个类的新对象与构造一个扩展类的匿名内部类的对象之间的差别：
                Person queen = new Person("Mary");  // a person object
                
                Person cont = new Person("Dracula"); // an object of an inner class extending person
            
            如果构造的参数的小括号后面跟一个大括号，正在定义的就是匿名内部类。
        
        6.4.7 静态内部类
        
            有时间，使用内部类只是为了把一个类隐藏在另一个类的内部，并不需要内部类引用外围类对象。
            可以将内部类声明为static,以便消除产生的引用。

    6.5 代理        
        
        代理(proxy) 利用代理可以在运行时创建一个实现类一组给定接口定新类。这种功能只有在编译时无法确定
        需要实现那个接口时才有必要使用。

    
## 第 7 章 异常、断言和日志

    错误不可避免，代码至少做到下面三点：向用户通告错误、保存所有的工作结果、允许用户以妥善的形式退出程序。

    7.1 处理错误
        异常处理的任务是将控制权从错误产生的地方转移给能够处理这种情况的错误处理器。
        
        在Java中，如果某个方法不能够采用正常的途径完整它的任务，就可以通告另外一个路径退出方法。
        在这种情况下，方法并不返回任何值，而是抛出(throw)一个封装类错误信息的对象。注意：方法会立刻退出，
        并不返回任何值。此外，调用这个方法的代码也将无法继续执行，取而代之的是，异常处理机制开始搜索能够
        处理这种异常的异常处理器(exception handler)。

        7.1.1 异常分类
        
            Java 语言规范将派生 Error类或RuntimeException类的所有异常称为非受查异常，其他的都是受查异常。
            编译器将核查是否为所有的受查异常提个了异常处理器。

            一个方法必须声明所有可能抛出的受查异常，而非受查异常要么不可控（Error）,要么就应该避免发生(RuntimeException).
            如果方法没有声明可能发生的受查异常，编译器报错。

        7.1.3 如何抛出异常
        
            对于一个已经存在的异常类，将其抛出非常容易。
                throw new RuntimeException();
                找到合适的异常类，创建这个类对象，将对象抛出。

    7.2 捕获异常
        
        如果异常发生没有捕获，那么程序会中止执行，并在控制台打印出异常信息，包括异常类型和堆栈内容。
        
            try{
                code
            }catch(ExceptionType e){
                handler for this type;
            }
        
        如果方法中抛出了一个在 catch 子句中没有声明的异常类型，那么方法立刻退出。
        通常，捕获知道如何处理的异常，传递不知道怎么处理的异常。
        想传递一个异常，必须在方法首部添加一个 throws 说明符，以便告知调用着这个方法可能抛出的异常。

        在catch子句中可以抛出一个异常，这种做的目的是改变异常的类型。
        
            try{
                access database
            } catch(SQLException e){
                throw new SeveltException("database error:" +  e.getMessage());
            }

        还有一种更好的方法，将原始异常信息设置为新异常的"原因"
            try{
                access database
            }catch (SQLException e){
                Throw se = new SevletException("database error");
                se.initCause(e);
                throw se;
            }

        当捕获异常时，可以使用下面语句重新获取原始异常
            Throwable e = se.getCause();
    
        
        带资源的 try 语句：
            
            try(Resource res = ...){
                work with res
            }

            try(Scanner in = new Scanner(new FileInputStream("/usr/share/dict/works"), "UTF-8")){
                while(in.hasNext())
                    ...
            }
            代码正常退出或异常时，都会调用 in.close() 方法，好像使用了 finally 一样。

    7.3 使用异常技巧
        
        a、异常不能代替简单的测试
        
            例如，退栈操作，检测栈是否为空。
                if(!s.empty()) s.pop()
           捕获异常
                try{
                    s.pop()
                }catch(EmptyStackException e){
                }
            捕获异常耗时更多
        
        b、不要过分细化异常
        
        c、利用异常层次机构
            不要只抛出 RuntimeException 异常。应该寻找更加适当的子类或创建自己的异常类。
            不要只捕获 Thowable 异常， 否则，会使程序代码更难读、 更难维护。
            考虑受查异常与非受查异常的区别。已检查异常本来就很庞大，不要为逻辑错误抛出这些异常。
            
        d、不要压制异常
            public Image loadImage(String s) {
                try
                { // code that threatens to throw checked exceptions
                }
                catch (Exception e) {} // so there
                }
            现在，这段代码就可以通过编译了。除非发生异常，否则它将可以正常地运行。即使发
            生了异常也会被忽略。如果认为异常非常重要，就应该对它们进行处理。        
        
        e、  在检测错误时，"苛刻" 要比放任更好
            检测数据，在出错的地方抛出一个 EmptyStackException异常要比在后面抛出一个 NullPointerException 异常更好。           

        f、不要修羞于传递异常

        当Java程序执行try块、catch块遇到return语句时，当系统执行完return语句之后，并不会立即结束该方法，
        而是去寻找该异常处理流程中是否包含finally块，如果没有finally块，方法终止，返回相应的返回值。
        如果有finally块，系统立即开始执行finally块——只有当finally块执行完成后，
        系统才会再次跳回来根据return语句结束方法。如果finally块里使用了return语句来导致方法结束，
        则finally块已经结束了方法，系统不会跳回去执行try、catch块里的任何代码。
    
    
    7.4 使用断言
        在 java 语言中,给出了3种出来系统错误的机制：抛出一个异常、日志、使用断言
        
        什么时候选择断言：
            断言失败是致命的、不可恢复的错误。
            断言检查只用于开发和测试阶段。
            

## 第八章  泛型程序设计   

    8.1 为什么要使用泛型程序设计
        泛型程序设计: 意味着编写的代码可以被很多不同类型的对象所重用。ArrayList类是泛型程序设计的一个实例。
        使用泛型机制编写的程序代码要比那些杂乱地使用 Object 变量，然后再进行强制类型转换的代码具有更好的安全性和可读性。
    
        泛型提供了一个类型参数(type parameters)用来指示元素的类型。
        ArrayList<String> files = new ArrayList<String>();
        这使得代码有更好的可读性。
        
        一个泛型类（ generic class) 就是具有一个或多个类型变量的类。  
        类型变量使用大写形式，且比较短， 这是很常见的。在 Java 库中， 使用变量 E 表示集合的元素类型， 
        K 和 V 分别表示表的关键字与值的类型。T ( 需要时还可以用临近的字母 U 和 S) 表示“ 任意类型”。
    
        泛型类可看作普通类的工厂    
    
    8.2 泛型方法：
    
        泛型方法既可以定义在普通类中，也可以定义在泛型类中。
        当调用一个泛型方法时,在方法名前的尖括号中放人具体的类型。
        当调用一个泛型方法时，在方法名前当尖括号中放入具体当类型。

    8.4 类型变量当限定
    
        有时，类或方法需要对类型变量加以约束。
            
            class ArrayAlg{
                public static <T> T min(T[] a){
                    if (a == null || a.length() == 0) return null;
                    for(int i = 1; i < a.length; i ++){
                        if(smallest.compareTo(a[i]) >0) smallest = a[i];
                    }
                    return smallest;
                }
            }        

        变量 smallest 类型为 T，怎么确信 T 所属对类有 compareTo 方法呢？
            public static <T extends Comparable> T min(T[] a)

        记法：
            <T extends BoundingType> 表示 T 英国时绑定类型对子类型，T和绑定类型可以时类，也可以是接口。
            一个类型变量或通配符可以有多个限定， 例如： T extends Comparable & Serializable
            限定类型用“ &” 分隔，而逗号用来分隔类型变量。

    8.5 泛型代码和虚拟机：

        虚拟机没有泛型类型对象--所有对象都属于普通类。
        无论何时定义一个泛型类型，都自动提供类一个相应都原始类型，原始类型都名字就是删去类型参数后的泛型类型名。
    
    8.6 约束与局限性

        大多数限制都是由类型擦除引起的。

        1、不能用基本类型实例化类型参数，没有Pair<double>,只有Pair<Double>,当然原因是类型擦除，Pair类含有 Object 类型域
        而 Object 不能存储 doubel 值。

        2、运行时类型查询只使用于原始类型
            虚拟机的对象总有一个特定的非泛型类型。因此，所有的类型查询只产生原始类型。
            例如：if(a instanceof Pair<String>) // Error
                 if(a instanceof Pair<T>)      // Error

        3、不能创建参数化类型的数组
            不能实例化参数化类型的数组，例如：Pair<String>[] table = new Pair<String>[10]; //Error
            数组会记住它的元素类型，如果试图存储其他类型的元素，就会抛出一个 ArrayStorException 异常。

        4、不能实例化类型变量
            不能使用像 new T(...), new T[...] 或 T.class 这样的表达式的类型变量。

        5、不能构造泛型数组

        6、泛型类的静态上下文中类型变量无效
            public class Sington<T>{
                private static T singleInstance; // Error
                public static T getSingleInstance(){ // Error
                }
            }

    8.7 泛型类型的继承规则：

        考虑一个类和一个子类 如 Employee 和 Manager.
        Pair<manager> 和 Pair<Employee> 没有关系。
        无论 S 和 T 什么关系，Pair<S> 和 Pair<T> 没有关系。


    8.8 通配符类型：
        
        固定的泛型类型系统使用起来并没有那么令人愉快。通配符类型解决类这个烦恼。
        通配符概念，通配符类型中，运行类型参数变化。如
            Pair<? extends Employee>
        表示任何泛型 Pair 类型，他的类型是 Employee的子类，如 Pari<Manager>,但不是Pair<String>。
        public static void printBuddies<Pair<? extends Employee> p>

        通配符的超类型限定：
            通配符限定与类型限定十分类似，但可以指定一个超类型限定： 
                ？super Manager 
            这个通配符限制为 Manager的所有超类型。

        带有超类型限定的通配符可以向泛型对象写入，带有子类型限定的通配符可以从泛型对象读取。

        通配符不是类型变量，不能在编写代码中使用 "?" 作为一种类型。
            
    8.9 反射和泛型
        

## 第九章 集合
    
    9.1 java 集合框架
       
        1、将集合的接口与实现分离
            java集合类库将接口与实现分离。如队列(queue)是如何实现分离的。
            队列接口指出可以在队列的尾部添加元素，在队列的头部删除元素，并且可以查找队列中元素的个数。
            
            队列接口最简单的形式如下：
                public interface Queue<E>{
                    void add(E element);
                    E remove();
                    int size();
                }         
    
            这个接口并没有说明队列如何实现的，队列常用的两种实现方式：一种是使用循环数组；另一种是使用链表。
            每一个实现都可以通过一个实现了 Queue 接口的类表示。
            public class CircularArrayQueue<E> implements Queue<E>{
                private int head;
                private int tail;
                
                CircularArrayQueue(int capacity){...}
                public void add(E elemet){...}
                public E remove(){...}
                public int size(){...}
                private E[] elements;
            }
            
            public class LinkedListQueue<E> implements Queue<E>{
                private Link head;
                private Link tail;
                
                LinkedListQueue(){...}
                public void add(E elemet){....}
                public E remove(){...}
                public int size(){...}
            }
            
            实际上，Java 类库没有名为 CircularArrayQueue 和 LinkedListQueue 的类。 这里，
            只是以这些类作为示例， 解释一下集合接口与实现在概念上的不同。如果需要一个循环
            数组队列，就可以使用 ArrayDeque 类。如果需要一个链表队列， 就直接使用 LinkedList
            类， 这个类实现了 Queue 接口。
            
        2、Collection 接口：
            
            在 Java l类库中，集合类的基本接口是 Collection 接口。这个接口有两个基本的方法：
            
                public interface Collection<E
                {
                    boolean add(E element)
                    Iterator<E> iterator();
                }
                
            add 方法用于向集合中添加元素，如果添加元素确实改变了集合就返回 ture,如果没有发生改变就false。
            如果试图向集合中添加一个对象，而这个对象在集合中已经存在，这个添加请求无效，因为集合中不允许有重复对象。
            
            iterator方法用于返回实现了 Iterator 接口的对象。可以使用迭代器对象依次访问集合中的元素。
            
        3、迭代器：
            
            Iterator 接口包含四个方法。
            
            public interface Iterator<E>
            {
                E next();
                boolean hasNext();
                void remove();
                default void forEachRemaining(Consumer<? Super E> action)
            }
            
            通过反复调用 next 方法，可以逐个访问集合中的每个元素。但是，如果到达了集合的末
            尾，next 方法将抛出一个 NoSuchElementException。 因此，需要在调用 next 之前调用 hasNext
            方法。如果迭代器对象还有多个供访问的元素， 这个方法就返回 true。如果想要査看集合中的
            所有元素，就请求一个迭代器，并在 hasNext 返回 true 时反复地调用 next 方法。
            
            Collection<String> c = ...;
            Iterator<String> iter = c.iterator();
            while(iter.hasNext()){
                String element = iter.next();
                // do something with element
            }
            用“for each” 循环可以更加简练地表示同样的循环操作。
            for(String element : c){
                //do something with element;
            }
            编译器简单地将 “for each”循环翻译为带有迭代器的循环。
            
            "for each" 循环可以与任何实现了 Iterable 接口的对象一起工作，这个接口包含一个抽象方法。
            public interface Iterable<E>{
                Iterator<E> iterator();
            }
            
            Collection接口扩展了 Iterable 接口。
    
       4、 泛型实用方法
       
            Collection 接口声明了很多有用的方法，所有的实现类都必须提供这些方法。
                int sizeO
                boolean isEmptyO
                boolean contains(Object obj)
                boolean containsAl1 (Col1ection<?> c)
                boolean equals(Object other)
                boolean addAll (Collection<? extends E> from)
                boolean remove (Object obj)
                boolean removeAl1 (Col1ection<?> c)
                void cl ear()
                boolean retainAl1 (Col1ection<?> c)
                Object口 toArrayO
                <T> T[] toA「ray(T[] arrayToFill)
            
            当然， 如果实现 Collection 接口的每一个类都要提供如此多的例行方法将是一件很烦人的
            事情。为了能够让实现者更容易地实现这个接口，Java 类库提供了一个类 AbstractCollection，
            它将基础方法 size 和 iterator 抽象化了
            
                public abstract class Abstracted1ection<E> implements Collection<E>
                {
                    ....
                    public abstract Iterator<E> iterator() ;
                    
                    public boolean contai ns(Object obj) {
                        for (E element : this) // calls iteratorO
                            if (element ,equals(obj))
                                return = true;
                        return false;
                    }
                    ....
                }
                
            此时， 一个具体的集合类可以扩展 AbstractCollection 类了。现在要由具体的集合类提供
            iterator 方法， 而 contains 方法已由 AbstractCollection 超类提供了。
            然而， 如果子类有更加有效的方式实现 contains 方法， 也可以由子类提供， 就这点而言，没有什么限制。
            对于 Java SE 8, 这种方法有些过时了。 如果这些方法是 Collection 接口的默  
                
       5、集合框架中的接口：
            
            集合有两个基本接口: Collection 和 Map 
            Collection : List、Set、Queue
            Map: sortedMap
            
            List 接口： List 是一个有序集合。元素会增加到容器中的特定位置。
            可以采用两种方式访问元素: 使用迭代器访问，或使用一个整数索引来访问(随机访问)。
            List接口定义了多个用于随机访问的方法：
                void add(int index, E element);
                void remove(int index);
                E get(int index);
                E set(int index, E element);
                
            Listlterator 接口是 Iterator 的一个子接口。它定义了一个方法用于在迭代器位置前面增加
            一个元素：void add(E element)
           
           Set 接口：等同于 Collection 接口，不过方法更严谨。add方法不允许增加重复元素。
                            
    9.2 具体的集合：
    
        
        ArrayList       一种可以动态增长和缩减的索引序列，数组列表
        LinkedList      一种可以在任何位置进行高效地插人和删除操作的有序序列， 链表
       
        ArrayDeque      一种用循环数组实现的双端队列
       
        HashSet         一种没有重复元素的无序集合
        TreeSet         一种有序集
        EnumSet         一种包含枚举类型值的集
        LinkedHashSet   一种可以记住元素插人次序的集
        PriorityQueue   一种允许高效删除最小元素的集合

        HashMap         一种存储键 / 值关联的数据结构
        TreeMap         一种键值有序排列的映射表
        EnumMap         一种键值属于枚举类型的映射表
        LinkedHashMap   一种可以记住键 / 值项添加次序的映射表
        WeakHashMap     一种其值无用武之地后可以被垃圾回收器回收的映射表
        IdentityHashMap 一种用 =而不是用 equals 比较键值的映射表
         
        Java 库映射两个实现：HashMap 和 TreeMap, 散列映射对键进行散列，树映射用键对整体顺序对元素进行排序，
        并将其组织成搜索树。散列或比较函数只能作用于键，于键关联对值不能进行散列或比较。

    9.3 映射：
        
        1、更新映射项
            counts.put(word, conts.get(word) + 1);
            当第一次看的 word 时，get会返回null,会出现一个 NullPointerException 异常。
            简单补救方法，使用getOrDefault 方法：
            counts.put(world, counts.getOrDefault(word, 0) + 1);
            
            另一种方法是首先调用 putIfAbsent方法，只有当键原先存在时才放入一个值。
            counts.putIfAbsent(word, 0);
            counts.put(word, conts.get(word) + 1);

            merge方法可以简化这个常见对操作。
            counts.merge(word, 1, Integer::sum);
            将把word与1关联，否则使用 Intger::sum函数组合原值和1（将原值与1求和）。

        2、映射视图：
            集合框架不认为映射本身是一个集合。但可以得当映射但视图。
            有三种视图：键集 Set<K> keySet()、值集合 Collection<V> values()、以及键/值对集 Set<Map.Entry<k, v>> entrySet()。

    9.5 算法：
        
        泛型集合接口又一个很大大优点，即算法只需要实现一次。
        

## 第14章 并发

    目前单台拥有多个CPU的计算机，但是，并发执行的线程数目并不是由CPU数目制约的。
    操作系统将CPU的时间片分配给每一个进程，给人并行处理的感觉。

    多线程程序在较低的层次上扩展类多任务的概念：一个程序同时执行多任务。通常，每个任务称为一个线程。
    它是线程控制的简称。可以同时运行以一个以上的线程的程序称为多线程程序。

    线程与进程区别：本质区别是每个进程拥有自己的一整套变量，而线程则共享数据，有数据风险。
                 共享变量使线程之间的通信比进程之间的通信更有效、更容易。有数据安全。
                线程更"轻量级"创建、撤销开销更小。

    
    14.1 什么是线程：
        java.lang.Thread: 
            Thread(Runnable target); 构造一个新线程，用于调用给定目标的run()方法。
            void start(); 启动这个线程，将引发调用 run()方法。这个方法将立刻返回，并且新线程将并行执行。
            void run(); 调用关联 Runnable 的 run 方法。

        java.lang.Runnable 
            void run(); 必须覆盖这个方法，并在这个方法中提供所要执行的任务指令。

        一个单独的线程中执行一个简单的任务过程：
            1、将任务代码移到实现了 Runnable 接口的类的 run 方法中。这个接口非常简单，只有一个方法：
                public interface Runnable{
                    void run();
                }
                由于 Runnable 是一个函数式接口，可以用lambda表达式建立一个实例：
                    Runnable r = ()->{task cod};

            2、由 Runnable 创建一个 Thread对象：
                Thread t = new Thread(r);
            3、启动线程
                t.start();

        也可以构建一个 Thread 类的子类定义一个线程。
            class MyThread extends Thread{
                public void run(){
                    task code;
                }
            }
        然后，构建一个子类的对象，并调用 start 方法。不推荐使用。
        应该将要并行运行的任务与运行机制解耦合(线程池)。
    
    14.2 中断线程：
        
        当线程当 run 方法体中最后一条语句后，并经由执行 return 语句返回时，
        或者出现了在方法中没有捕获当异常时，线程将终止。

        没有可以强制线程终止当方法。然而，interrupt方法可以用来请求终止线程。
        当对一个线程调用 interrupt 方法时，线程对中断状态将被置位。这是每个
        线程都具有都 boolean 标志。每个线程都应该不时地检查这个标志，以判断线程是否被终止。
        要想弄清楚中断状态是否被置位，首先调用静态的 Thread.currentThread 方法获得当前
        线程，然后调用 isInterrupted方法：
            while(!Thread.currentThread().isInterrupted() && more work to do){
                do more work
            }
        但是，如果线程被阻塞，就无法检测中断状态。这是产生 InterruptedException异常的地方。
        当在一个被阻塞当线程（sleep或wait)上调用 interrupt方法时，阻塞调用将会被Interrupted Exception异常中断。
        
        没有任何语言方面当需求要求一个被中断当线程应该终止。中断一个线程不过时引起它当注意。被中断当线程可以决定如何响应中断。
        更普遍当情况是，线程将简单地将中断作为一个终止请求。这种线程当 run 方法具有如下形式：

            Runnable r = ()->{
                try{
                    ...
                    while(!Thread.currentThread().isInterrupted() && more work to do){
                        do more work
                    }
                    ...
                }catch(InterruptedException e){
                    // thread was interrupted during sleep or wait
                }finally{
                    // cleanup,if required
                }
                // exiting the run method terminates the thread
            };
        
        
        java.lang.Thread
            void interrupt();   向线程发送中断请求，线程当中断状态将被设置成 true。
                                如果目前该线程被一个sleep调用阻塞，那么，InterruptedException 异常被抛出。

            static boolean interrupted(); 测试当前线程（即正在执行这一命令当线程）是否被中断。
                                注意，这是一个静态方法。这一调用会产生副作用-它将当前线程当中断状态重置为 false。
        
            boolean isInterrupted(); 测试线程是否被终止，不像静态当中断方法，这一调用不改变线程当中断状态。

            static Thread currentThread(); 返回代表当前执行线程当 Thread 对象。

    14.3 线程当状态：
        
        线程可以有 6 种状态：
        
        1、New （新创建）： 
            new Thread(r) 创建一个线程，还没运行。在运行之前需要一些基础工作要做。

        2、Runnable（可运行）：
            一旦调用 start方法，线程处于 runnable 状态。一个可运行当线程可能正在运行也可能没有运行
            取决与操作系统给线程提供当可运行时间。（Java 没有单独一个运行状态，运行的线程仍处于可运行状态）
        
        当线程处于被阻塞或等待状态时，它暂时不活动，不运行任何代码且消耗最少资源。直到线程调用器重新激活它。
            细节取决于它是怎么达到非活动状态当。
        3、Blocked（被阻塞）：
            当一个线程试图获取一个内部当对象锁（不是java.util.concurrent库中当锁）。而该锁被其他线程持有。
            则该线程进入阻塞状态。当所有其他线程释放该锁，并且线程调用器允许本线程持有它当时候，该线程将变成非阻塞状态。
        4、Waiting（等待）：
            当线程等待另一个线程通知调用器一个条件时，它自己进入等待状态。
            在调用 Object.wait(), Thread.join() 或者是等待 Java.util.concurrent 库中当 Lock 或 Condition时，
            就会出现这种情况。被阻塞与等待状态是有很大当不同当。
                 
        5、Timed waiting（计时等待）：
            有几个方法有一个超时参数，调用他们导致线程进入计时等待状态。这个状态一直保持到超时期满或者接收到适当通知。
            Thread.sleep 和 Object.wait、Thread.join、Lock.tryLock以及 Condition.await当计时版。

        6、Terminated(被终止)
            线程因如下两个原因之一而被终止
            因为run方法正常退出而自然死亡
            因为一个没有捕获当异常终止了run方法而意外死亡。

        确定一个线程当当前状态，调用 getState 方法。
        Java.lang.Thread
            void join()； 等待终止指定当线程
            void join(long millis); 等待指定当线程死亡或者经过指定当毫秒数
            Thread.State getState(); 得到这一线程状态；NEW、EUNNABLWE、BLOCKED、WAITING、TIMED_WAITING、TERMINATED。
    
    14.4 线程属性：线程优先级、守护线程、线程组以及处理未捕获异常当处理器
        
        1、线程优先级
            每个线程有一个优先级，默认机场它的父线程优先级。可以用 setPriority 方法设置或降低优先级。
            MIN_PRIORITY(Thread 定义为1)与MAX_PRIORITY（10）之间的任何值。NORM_PRIOPRITY定义为5。
            
            线程的优先级高度依赖与系统，虚拟机依赖宿主主机平台的线程实现机制。Java 线程优先级映射到宿主平台
            的优先级上，优先级也许更多，也许更少。
            Windows 有7个优先级。有的平台线程优先级忽略。不要将程序构建的功能正确性依赖于优先级。
            
            java.lang.Thread:
                void setPriority(int newPriority); 设置优先级。
                static void yield(); 导致当前执行线程处于让步状态，如果其他线程可运行线程具体至少与此线程
                                     同样高的优先级，那么这些线程接下来会被调度。这是一个静态方法。

        2、守护线程：
            
            t.setDaemon(ture); 将线程转换为守护线程（daemon thread）
            守护线程的唯一用途是为其他线程提供服务。例如它定时发送"计时器嘀嗒"信号给其他线程。
            当只剩下守护线程时，虚拟机就退出了，如果只剩下守护线程，就没有必要继续运行程序。
            守护线程永远不去访问固有资源：文件、数据库。
            
            Java.lang.Thread
                void setDaemon(boolean isDaemon); 标识该线程为守护线程或用户线程，方法必须在线程启动前调用。

        3、未捕获异常处理器：

            线程当 run 方法不能排除任何受查异常，但是，非受查异常会导致线程终止。在这种情况下，线程就死亡了。
            但是，不需要任何 catch 子句来处理被传播当异常。相反，在线程死亡之前，异常被传递到一个用未捕获异常的处理器。
            
            该处理器必须属于一个实现 Thread.UncaughtExceptionHandler 接口的类。

    14.5 同步：
        
        1、锁对象：有两种机制防止代码块受并发访问的干扰。synchronized关键字。Java5 引入 ReentrantLock类。
                Java.until.concurrent 框架为这些基础机制提供独立的类。

            用 ReentrantLock 保护代码块的基础结构如下：
                myLock.lock(); // a ReentrantLock object
                try{
                    // critical section
                }finally{
                    mylock.unlock(); // make sure the lock is unlocked even if an exception is throw 
                }
            这个接口确保任何时刻只有一个线程进入临界区，一旦一个线程封锁类锁对象，其他任何线程都无法通过 lock语句。
            当其他线程调用 lock 时，会被阻塞，直到线程是释放锁对象。

            java.util.concurrent.locks.Lock 5.0
                void lock(); 获取这个锁，如果锁同时被另一个线程拥有则发生阻塞
                void unlock(); 释放这个锁

            Java.util.concurrent.locks.ReentrantLock 5.0
                ReentrantLock(); 构建一个可重入 锁

        2、条件对象（被称为条件变量）：
            通常，线程进入临界区，却发现在某一条件满足之后它才能执行。要使用一个条件对象来管理那些已经
            获得了一个锁但是却不能做有用工作都线程。

            一个锁对象可以有一个或多个相关都条件对象。可以用newCondition方法获得一个条件对象。
            例如设置一个"余额充足"条件：
                class Bank{
                    private Condition sufficientFunds;
                    ...
                    public Bank(){
                        ...
                        sufficientFunds = bankLock.newConndition();
                    }
                }                
            如果 transfer 方法发现余额不足，它调用 sufficientFunds.await();
            当前线程现在被阻塞来，并放弃来锁。希望另一个线程增加余额。
            等待获得锁都线程和调用 await 方法都线程存在本质上都不同。
            一旦一个线程调用 await 方法，它进入该条件都等待集。当锁可用时，该线程不能马上解除阻塞。
            相反，它处于阻塞状态，直到另一个线程调用同一个条件上的 signalAll 方法时为止。
            当另一个线程转账时，它应该调用：sufficientFunds.signalAll();
            调用重新激活因为这一条件而等待当所有线程。线程从等待集中移出，再次成为可运行状态，调度器再次激活它们。
            一旦锁可以，获得锁并从被阻塞当地方继续执行。

            至关重要当最终需要某个其他线程调用 signalAll 方法。当一个线程调用 await 时，它没有办法重新激活自身。
            寄希望其他线程，如果没有其他线程，它永远无法运行，导致死锁现象。

            何时调用 signalAll,在对象当状态有利于等待线程当方向改变时调用 signalAll。
            调用 signalALl 不会立刻激活一个等待线程。它仅仅解除等待线程当阻塞，以便线程
            可以在当前线程退出同步方法之后，通告竞争实现对象的访问。
            
            java.util.concurrent.locks.Lock:
                Condition newCondition(); 返回一个与该锁相关的条件对象
           java.util.concurrent.locks.Condition
                void await(); 将该线程放到条件的等待集中
                void signalAll(); 解除等待集中所有线程的阻塞状态
                void signal(); 从该条件的等待集中随机地选择一个线程，解除其阻塞状态。

        3、synchronized 关键字：
            锁与条件的关键之处：
                锁用来保护代码片段，任何时刻只能有一个线程执行被保护的代码
                锁可以管理试图进入保护代码段的线程
                锁可以拥有一个或多个相关的条件对象
                每个条件对象管理那些已经进入保护的代码段但不能运行的线程。

            从1.0开始，Java 中的每一个对象都有一个内部锁。如果一个方法用synchronized关键字声明
            那么对象的锁将保护整个方法。调用该方法，线程必须获得内部的对象锁。

                public synchronized void method(){
                    method body
                }
            等价于
                public void method(){
                    this.intrinsicLock.lock();
                    try{
                        method body
                    }
                    finally{
                        this.intrinsicLock.unlock();
                    }
                }
            
            内部锁对象锁只有一个相关条件，wait方法添加一个线程到等待集中，notifyAll/nofify方法解除等待线程的阻塞状态。
            调用wait或notifyAll等价于：
                intrinsicCondition.await();
                intrinsicCondition.signalAll();
            
            wait、notifyAll、notify 方法是Object类的 final 方法。
            Condition 方法必须被命名为 await、signalALL和signal 以便不会与那些方法发生冲突。
            
            内部锁和条件存在一些局限：
                不能中断一个正在试图获得锁的线程
                试图获得锁时不能设定超时
                每个锁仅有单一的条件，不可能是不够的

            在代码中应该使用那种？Lock 和 Condition对象还是同步方法？建议：
                1、最好既不使用 Lock/Condition 也不使用 synchronized 关键字。
                    可以使用Java.until.concurrent包的一种机制，它会为类处理所有的加锁。
                2、如果synchronized 关键字适合你的程序，那么请尽量使用它，可以减少代码量。
                3、如果特别需要 Lock/Condition 结构提供的独有特性时，才使用Lock/Condition.

            java.lang.Object:
                void notifyAll(); 解除该对象上调用wait方法的线程的阻塞状态。该方法只能在同步方法或同步块内调用。
                void notify(); 随机选择一个
                void wait(); 导致该线程进入等待状态直到它被通知。该方法只能在同步方法中调用。
                void wait(long millis);
                void wait(long millis, int nanos);

        4、同步阻塞：
            每个 Java 对象有一个锁，线程可以通过调用同步方法获得锁。
            还有另一种机制可以获得锁，通过进入一个同步阻塞。当线程进入如下形式当阻塞：
                synchronized(obj){  // this is the syntax for a synchronized block
                    critical section
                }
            于是它获得 obj 当锁。

            public calss Bank{
                private double[] accounts;
                private Object lock = new Object();
                ....
                public void transfer(int from, int to, int amount){
                    synchronized(lock){  // an ad-hoc lock
                        accounts[from] -= amount;
                        accounts[to] += amount;
                    }
                    System.out.println(...);
                }
            }
            在此，lock 对象被创建仅仅是用来使用每个 java 对象持有当锁。
            使用一个对象当锁来实现额外当原子操作，称为客户端锁定。客户端锁定非常脆弱，不推荐使用。

        5、监视器概念：
            锁和条件是线程同步当强大工具，但是，严格来说，它们不是面向对象当。
            努力寻找一种方法，可以不需要程序员考虑如果加锁当情况下，可以保证线程当安全性。
            最成功当解决方案之一--监视器。用Java的术语讲，监视器具有如下特性：
                a、监视器是只包含私有域的类。
                b、每个监视器类的对象有一个相关的锁。
                c、使用该锁对所有对方法进行加锁。换句话说，如果客户端调用obj.mehtod(),
                    那么obj对象对锁是在方法调用开始时自动获得，当方法返回时自动释放该锁。
                    因为所有域都是私有的，保证一个线程在对对象操作时，没有其他线程能访问该域。
                d、该锁可以有任意多个相关条件。
        
        6、Volatile 域：
            仅仅写一个两个实例域使用同步，显得开销过大，比较，什么地方能出错呢？遗憾对是，使用
            现代处理器和编译器，出错的可能性很大。
                多处理器的计算机能够暂时在寄存器或本地内存缓冲区中保存内存中的值。
                结果是，运行在不同处理器上的线程可能在同一个内存位置取到不同的值。

                编译器可以改变指令的执行的顺序以使吞吐量最大化。这种顺序上的变化不会
                改变代码语义，但是编译器假定内存的值仅仅在代码中有显式的修改指令时才会改变。
                然而，内存的值可以被另一个线程改变。

            如果你使用锁来保护被多个线程访问的代码，可以不用考虑这种问题，编译器被要求通过
            在必要的适合刷新本地缓存来保持锁的效应，并且不能不正当地重新排序指令。

            同步格言：如果向一个变量写入值，而这个变量接下来可能被另一个线程读取，或者，从一个
                    变量读值，而这个变量可能是之前被另一个线程写入的。此时必须使用同步。

            volatile 关键字为实例域的同步访问提供来一种免锁机制。如果声明一个域为 volatile
            那么编译器和虚拟机就知道该域是可能被另一个线程并发更新的。

            假定一个对象的布尔标记done,它的值被一个线程设置却被另一个线程查询。你可以同步锁
                private boolean done;
                public synchronized boolean isDone(){return done;}
                public synchronized void setDone(){done = true;}
                内部锁不是一个好主意，方法可能阻塞。
                
                这种情况，可以将域声明为 volatile 是合理的。
                private volatile boolean done;
                public boolean isDone(){return done;}
                public void setDone(done = true;)
            
            volatiole 变量不能提供原子性。
        
        7、final 变量：
            除非使用锁或 volatile 修饰符，否则无法从多个线程安全读取一个域。
            还有一种情况可以安全地访问一个共享域，即这个域声明为 final时：
            final Map<String, Double> accounts = new HashMap<>();
            其他线程会在构造函数完成构造之后才能看的这个accounts 变量。
            
            如果不使用 final，就不能保证其他线程看的的accounts更新后的值，它们看能都只是看的null，
            而不是构造的HashMap。

            当然，对这个映射表对操作并不是线程安全对。如果多个线程写这个映射表，仍需要同步。

        8、原子性：

            假设对共享变量除了赋值之外并不完成其他操作，那么可以将这些共享变量声明为 volatile.
            java.util.concurrent.atomic包中有很多类使用类很高效对机器级指令来保证其他操作对
            原子性。例如，AtomicInteger类提供类方法incrementAndGet 和 decrementAndGet.
            它们分别以原子方式将一个整数自增或自减。
                public static AtomicLong nextNumber = new AtomicLong();
                long id = nextNumber.incrementAndGet();

        9、死锁：
            锁和条件不能解决多线程中对所有问题。

        10、线程局部变量：
            在线程间共享变量有风险，又是可能要避免共享变量。使用ThreadLocal 辅助类为各个线程提供各自对实例。
            public static final ThreadLocal<SimpleDateFormat> dateFormat = 
                ThreadLocal.withInitial(()->new SimpleDateFormat("yyyy-MM-dd"));

            具体访问格式化：
                String dateStamp = dateFormat.get().format(new Date());
            在一个给定线程中首次调用get时，会调用 initialValue方法。之后，get方法会返回属于当前线程对那个实例。


            java.lang.ThreadLocal<T>:
                T get(); 得到这个线程对当前值，如果是首次调用get,会调用initialize来得到这个值。
                protected initialize(); 应覆盖这个方法来提供一个初始值。默认情况下，返回null.
                void set(T t); 为这个线程设置一个新值。
                void remove(); 删除对应这个线程对值。
                static <S> ThreadLocal<S> withInitial(Supplier<? extends S> supper);
                    创建一个线程局部变量，其初始值通过调用给定的supplier生成。
        
        11、锁测试与超时：
            线程在调用 lock方法来获取另一线程所持有的锁的时候，可能发生阻塞。应更谨慎申请锁。
            tryLock方法试图申请一个锁，成功返回true，否则，立刻返回 false。线程可以去做其他事。

            if(myLock.tryLock()){
                try{...}
                finally {myLock.unlock();}
            }else{
                // do something else
            }

            可以调用 tryLock时，使用超时参数。
            if(myLock.tryLock(100, TimeUnit.MILLISECONDS))

            lock 方法不能被中断，如果一个线程在等待获得一个锁时被中断，中断线程在获得锁之前一直
            处于阻塞状态，如果出现死锁，那么 lock方法无法终止。

            调用带有超时参数的 tryLock,那么如果线程在等待期间被中断，将抛出InterruptedException异常。
            
            boolean tryLock();
            boolean tryLock(long time, TimeUtil unit);
            void lockInterruptibly();
        
        12、读写锁：
            java.util.concurrent.locks 定义来两个锁类，ReentrantLock类和RenntrantReadWriteLock类。
            
            使用读/写锁的必要步骤：
                1、构造一个 RenntrantReadWriteLock对象；
                    private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
                2、抽取读锁和写锁：
                    private Lock readLock = rwl.readLock();
                    private Lock writeLock = rwl.writeLock();
                3、所有读取的方法加读锁
                    public double getTotalBalance(){
                        readLock.lock();
                        try{...}
                        finally(readLock.unlock())
                    }
                4、所有修改方法加写锁：
                    public void transfer(...){
                        writeLock.lock();
                        try{...}
                        finally(writeLock.unlock())
                    }

            java.util.concurrent.locks.ReentrantReadWriteLock
                Lock readLock();多个读操作共用的读锁，但排斥写操作
                Lock writeLock(); 写锁，排斥所有读写操作。

    14.6 阻塞队列    
        
        对于多线程多问题，可以通过一个或多个队列以优雅且安全多方式将其形式化。
        生产者线程向队列插入元素，消费者线程则取出它们。使用队列，可以安全地
        从一个线程向另一个线程传递数据。
        
        当试图向队列添加元素而队列已满，或是想从队列移出元素而队列为空当时候，阻塞队列导致线程阻塞。
        
        java.until.concurrent.*
            ArrayBlockingQueue(int capacity) //阻塞队列，循环数组实现
            LinkedBlockingQueue(); //无上限阻塞队列，链表实现
            LinkedBlockingDeque(); //无上限阻塞队列，双向队列
            LinkedBlockingQueue(int capacity); // 指定容量
            LinkedBlockingDeque(int capacity); //指定容量
            PriorityBlockingQueue(); //优先队列
            
            void put(E element); // 添加元素，必要时阻塞。
            E take(); //移出并返回元素，必要时阻塞。
            boolean offer(E element, long time, TimeUnit unit); 添加给定元素，成功返回 true
            E poll(long time, TimeUnit unit); 移除并返回元素，必要时阻塞。

        
    14.7 线程安全当集合：

        Java 类库提供当一些线程安全集合：
        java.util.concurrent 包提供了映射、有序集和队列当高效实现：
            ConcurrentHashMap、ConcurrentSkipListMap、ConcurrentSkipListSet和ConcurrentLinkedQueue. 
        这些集合使用复杂当算法，通过允许并发地访问数据机构当不同部分来使竞争极小化。

        任何集合都可以通过使用 同步包装器变成线程安全的：
            List<E> synchArrayList = Collections.synchronziedList(new ArrayList<E>);
            Map<K, V>synchHashMap = Collections.synchronziedMap(new HashMap<K, V>);

    14.8 Callable 与 Future：

        Runnable 封装一个异步运行的任务，可以把它想象成为一个没有参数和返回值的异步方法。
        Callable 与 Runnable 类似，但是有返回值。Callable 接口是一个参数化的类型。只有一个方法 call。
            
            public interface Callable<V>{
                V call() throw Exception;
            }
        
        类型参数是返回值的类型。例如，Callable<Interger> 表示一个最终返回 Integer对象的异步计算。
        Future 保存异步计算的结果。可以启动一个计算，将 Future 对象交给某个线程，然后忘掉它。
        Future 对象的所有者在结果计算好之后就可以获得它。
        
        Future 接口具有下面的方法:
            public interface Future<V>{
                V get() throw ...;  // 计算未完成被阻塞;计算线程中断将抛 InterruptedException;计算完成立刻返回。  
                V get(long timeout, TimeUnit unit) throw ...; // 计算未完成超时抛出 TimeoutException异常;计算线程中断将抛 InterruptedException;计算完成立刻返回。
                void cancel(boolean mayInterrupt); //如果计算还没有开始，它被取消且不在开始，如果计算运行中，mayInterrupt为ture,它被中断。
                boolean isCancelled(); //如果任务完成前被取消了，则返回 true
                boolean isDone(); // 如果任务结束，无论正常结束、中途取消、发生异常，都返回 true。
            }

        FutureTask 包装器是一种非常便利的机制，可将 Callable 转化成 Future 和 Runnable,它同时实现二者接口。
            Callable<Interger> myComputation = ...;
            FutureTask<Integer> task = new FutureTask<Integer>(myComputation);
            Thread t = new Thread(task);
            t.stat();
            ....
            Integer result = task.get(); 
    
    14.9 执行器:
        
        构建一个新的线程是有一定代价的，因为涉及与操作系统交互。如果程序中创建了大量的生命期很短的线程，应该使用线程池。
        一个线程池中包含许多准备运行的空闲线程。将 Runnable 对象交给线程池，就会有一个线程调用 run 方法。当 run 方法
        退出时，线程不会死亡，而是在池中准备下一个请求提供服务。
        另一个使用线程池的理由是减少并发线程的数目。创建大量线程会大大降低性能甚至使虚拟机崩溃，线程池限制并发线程数量。
        
        执行器(Executor)类有许多静态工厂方法用来建构线程池。
            newCachedThreadPool    必要时创建新线程，空闲线程会被保留 60 秒
            newFixedThreadPool     该池包含固定数量的线程，空闲线程会一直保留
            
        使用线程池应该做的事：
            1、调用 Executors 类中静态的方法 newCachedThreadPool或newFixedThreadPool。
            2、调用 submit 提交 Runnable 或 Callable 对象
            3、如果想取消一个任务，或如果提交 Callable 对象，那就要保存返回 Future 对象。
            4、当不再提交任何任务时，调用 shutdown。
            
        java.util.concurrent.Executors:
            ExecutorService newCachedThreadPool();
                返回一个带缓存的线程池，该池在必要的时候创建线程，在线程空闲60秒之后终止线程。
            ExecutorService newFixedThreadPool(int threads);
                返回要给线程池，该池中的线程数由参数指定
            ExecutorService newSingleThreadExecutor();
                返回一个执行器，它在一个单个的线程中依次执行各个任务。
                           
        java.util.concurrent.ExecutorServices:
            
            Future<T> submit(Callable<T> task)
            Future<T> submit(Runnable task, T result)
            Future<?> submit(Runnable task)
                提交指定的任务去执行
            void sutdown();
                关闭服务,会先完成已经提交的任务而不再接收新的任务。
        
    14.10 同步器:
        信号量、倒计时门栓、障栅、交换器、同步队列
        
        


        

            
        
        
        

        
            
                
            
            
        

            
            
            


                
        
        
             
    
        


        
    
        
             
        
        
        
        
        
        
        
        
        
        
        
        
                    
            
            
            
            
                 
             
                 
               
            
            
                
       
             
            
            
            
                  
          
            
    
            
    









        
        
        
    
            

        
         
          
                       
        
        
        
        
             




      
                
                

    
        
         
              
        
        
            
        
        
            
             
             
       
       
    

