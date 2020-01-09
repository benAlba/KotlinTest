package com.alba.kotlintest

//fun main(args: Array<String>){

    /*var x = 0
    if (x>0){
        println("x大于0")
    }else if (x==0){
        println("x等于0")
    }else{
        println("x小于0")
    }

    var a = 1
    var b = 2
    val c = if (a>=b) a else b
    println("c 的值为 $c")

    val m = 5
    val n = 9
    if (m in 1..8){
        println("m 在区间内")
    }*/

    /*var x =0;

    when(x){
        0,1 -> println("x == 0 or x == 1")
        else -> println("otherwise")
    }

    when(x){
        1 -> println("x == 1")
        2 -> println("x == 2")
        else ->{//注意这个块
            println("x 不是1， 也不是2")
        }
    }


    when(x){
        in 0..10 -> println("x在该区间范围内")
        else -> println("x 不在该区间范围内")
    }

    val items = setOf("apple","banana","kiwi")
    when{
        "orange" in items -> println("juicy")
        "apple"  in items -> println("apple is fine too")
    }*/

    /* val items = listOf("apple", "banana", "kiwi")

     for (item in items){
         println(item)
     }

     for (index in items.indices){
         println("item at $index is ${items[index]}")
     }*/

    /* var person: Person = Person()
     person.lastName = "lu"

     println("lastName:${person.lastName}")

     person.no = 9
     println("no:${person.no}")

     person.no = 20
     println("no:${person.no}")*/

//}

/*
class Person {

    var lastName: String = "Lu"
        get() = field.toUpperCase() //将变量赋值后转换为大写
        set

    var no: Int = 100
        get() = field               //后端变量

        //Type mismatch: inferred type is () -> Unit but Unit was expected
        set(value) {
            if (value<10){
                field = value       //如果传入的值小于10返回该值
            }else{
                field = -1          //如果传入的值大于等于10返回-1
            }
        }

    var heiht: Float = 145.4f
        private set

}
*/
/*fun main(args: Array<String>){
    var  person: Person = Person()

    person.lastName = "Yang"

    println("lastname:${person.lastName}")

    person.no = 9
    println("no:${person.no}")

    person.no = 20
    println("no:${person.no}")
}*/

//类和对象
/*class Kof constructor(name: String){//类名为Kof,构造器没有注解，没有可见度修饰符，constructor关键字可省略
    var url: String = "http://www.kof.com"
    var country: String = "CN"
    var siteName = name

    init {//初始化代码段，以init关键字作为前缀
        println("初始化网站名: ${name}")
    }

    //次构造函数, 需要加前缀constructor   this代表通过另一个次构造函数代理主构造函数
    constructor(name: String,alexa: Int) : this(name){
        println("Alexa排名 $alexa")
    }

    fun printTest(){
        println("我是类的函数")
    }
}
fun main(args: Array<String>){

    val kof = Kof("拳皇2002", 100)
    println(kof.siteName)
    println(kof.url)
    println(kof.country)
    kof.printTest()

}*/


interface Sport{
    //接口的成员变量默认是open的

    val time: Int

    fun running(){
        println("RUNNING")
    }
    fun swimming()
    {
        println("SWIMMING")
    }
}

open class Person(name:String){  //父类用open关键字修饰

    /**次级构造函数*/
    constructor(name: String, age: Int):this(name){
        //初始化
        println("-------------基类次级构造函数-------------")
    }

    open fun study(){     //函数默认为final修饰,不能被子类重写。使用open关键字代表允许子类重写
        println("我毕业了")
    }

    open fun running(){
        println("running")
    }
}

//子类有主构造函数，基类必须在主构造函数中立即初始化    可以用一个var属性重写一个val属性,反之不行
class Man(name: String, age: Int ,override var time: Int) : Person(name,age) , Sport{

    /*constructor(name: String, age: Int, override val time: Int):super(name,age){
        println("-------------继承基类次级构造函数-------------")
    }*/

    init {
        println("I will stand tall")
    }

    override fun running() {
        //super<Sport>.running()
        println("I like run too")
    }

}

class Student : Person , Sport{
    //属性重写使用override关键字,属性必须具有兼容类型，每一个声明的属性可以通过初始化程序或者getter方法重写
    override val time: Int = 0

    /**super关键字初始化基类，或者在代理另一个构造函数;初始化基类时，可以调用基类的不同构造方法*/
    constructor(name: String, age: Int, no: String, score: Int):super(name,age){
        println("------------继承基类次级构造函数------------")
        println("学生名:${name}")
        println("年龄:${age}")
        println("学生号:${no}")
        println("成绩:${score}")
    }

    override fun study() { //使用override关键字重写基类的方法
        super.study()
        println("我在读大学")
    }

    //如果有多个相同的方法(继承或者实现自其他类，如Person、Sport类)，则必须要重写该方法，可以使用super范型去选择性地调用父类的实现
    override fun running(){
        super<Sport>.running()
        println("I like run")
    }

}

fun main(args: Array<String>){
    var s = Student("LuJunFeng",26,"1101",358)
    s.study()
    s.running()

    var m = Man("LuYan",26,1800)
    m.running()
}