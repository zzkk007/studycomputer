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
    
        再来考虑上一节讨论的排序例子。我们传人代码来检查一个字符串是否比另一个字符串
        短。这里要计算:
            first.lenth() - second.length();
        java是强类型语言，我们需要指定类型
            (String first, String second)->first.lenth() - second.length();
        这就是一个lambda表达式。lambda就是一个代码块，以及必须传入代码的变量规范。
        
        带参数变量的表达式被成为 lambda 表达式。
        
        
        
        
            
        
             




      
                
                

    
        
         
              
        
        
            
        
        
            
             
             
       
       
    

