# java 核心技术卷-基础知识第10版

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
        
        
         
          
                       
        
        
        
        
             




      
                
                

    
        
         
              
        
        
            
        
        
            
             
             
       
       
    
