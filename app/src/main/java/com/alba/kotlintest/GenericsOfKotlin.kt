package com.alba.kotlintest


//泛型
class Box<T>(t : T){
    var value = t
}

fun <T> doPrintln(content: T){

    when(content){
        is Int -> println("整型数字为$content")
        is String -> println("字符串转换为大写:${content.toUpperCase()}")
        else -> println("T 不是整型, 也不是字符串")
    }

}

//泛型约束？？？


//型变 包括声明处型变、星号投射

//声明处型变
class Ilusion<out A>(val a: A){//定义一个支持协变的类
/**使用out使得一个类型参数协变, 协变类型参数只能用作输出，可以作为返回值类型但是无法作为入参的类型*/
    fun foo(): A{
        return a
    }
}

class Dslite<in Z>(z: Z){//定义一个支持逆变的类
/**使用in使得一个类型参数逆变, 逆变类型参数只能用作输入, 可以作为入参的类型但是无法作为返回值类型*/
    fun foo(z: Z){

    }
}

//星号投射 关于星号投射, 其实就是*代指了所有类型, 相当于Any?
class A<T>(val t : T, val t2 : T, val t3 : T)
class Apple(var name : String)



//定义泛型类型变量, 可以完整地写明类型参数, 如果编译器可以自动推定类型参数, 也可以省略类型参数
fun main(args: Array<String>){
    var boxInt = Box<Int>(10)
    var boxString = Box<String>("Excellent")

    println(boxInt.value)
    println(boxString.value)


    val age = 23
    val name = "excellent"
    val bool = true

    doPrintln(age)
    doPrintln(name)
    doPrintln(bool)

    var strCo: Ilusion<String> = Ilusion("DCZL")
    var anyCo: Ilusion<Any> = Ilusion("WX3")
    anyCo = strCo
    println(anyCo.foo())

    var strZo: Dslite<String> = Dslite("Apostle")
    var anyZo: Dslite<Any> = Dslite("Xxx")
    strZo = anyZo


    //使用类
    val a1:A<*> = A(12, "String", Apple("苹果"))
    val a2:A<Any?> = A(12, "String", Apple("苹果"))
    val apple = a1.t3   //参数类型为Any
    println(apple)
    val apple2 = apple as Apple //强转为Apple类
    println(apple2.name)
    //使用数组
    val l:ArrayList<*> = arrayListOf("String", 1, 1.2f, Apple("苹果"))
    for (item in l)
    {
        println(item)
    }


}

