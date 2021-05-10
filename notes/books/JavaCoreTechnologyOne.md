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
                
     6.4 内部类







##  第 7 章 异常、断言和日志

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
                       
        
        
        
        
             




      
                
                

    
        
         
              
        
        
            
        
        
            
             
             
       
       
    

