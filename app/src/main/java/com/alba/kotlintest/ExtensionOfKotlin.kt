package com.alba.kotlintest

//扩展，第一次听说

class User(var name:String)

/**User为函数的接收者，也就是函数扩展的对象；Print为扩展函数名称*/
fun User.Print(){
    print("用户名$name")
}

fun MutableList<Int>.swap(index1:Int,index2:Int){
    /**this关键字指代接收者对象(receiver object)(也就是调用扩展函数时，在点号之前指定的对象实例)*/
    val tmp = this[index1]
    this[index1] = this[index2]
    this[index2] = tmp
}


//什么是虚拟？什么是静态？

open class C{
    /**若扩展函数和成员函数一致，则使用该函数时，会优先使用成员函数*/
    fun bar(){ println("成员函数") }
}

class D: C()

fun C.bar(){ println("扩展函数") }

fun C.foo() = "c"   //扩展函数foo

fun D.foo() = "d"   //扩展函数foo


fun printFoo(c: C){
    println(c.foo())    //类型是C类
}

fun Any?.toString(): String {
    if ( this == null ) return "null"
    /**空检测之后，"this"会自动转换为非空对象，所以下面的toString()解析为Any类的成员函数*/
    return toString()
}

/**扩展属性*/
val <T> List<T>.lastIndex: Int
    get() = size - 1

/**扩展属性允许定义在类或者kotlin文件中，不允许定义在函数中.初始化属性因为属性没有后端字段(backing field)，
   所以不允许被初始化，只能由显示提供的getter/setter定义*/
//val Foo.bar =1 //错误:扩展属性不能有初始化器   *扩展属性只能被声明为val



//什么是伴生对象?
//kotlin中,在类中定义的对象(object)声明,可使用companion修饰,这样此对象(object)就是伴生对象
//kotlin取消了static关键字,所以kotlin引入了伴生对象来弥补没有静态成员的不足

class MyClass {
    companion object {  }  //将被称为 "Compaion"
}

fun MyClass.Companion.foo(){
    println("伴生对象的扩展函数")
}

val MyClass.Companion.no: Int //伴生对象的扩展属性
    get() = 10


open class F {
    fun bar() { println("F bar") }
}

class F1 : F() {

}

open class E {
    /**该函数在分发接收者和扩展接收者均存在，则以扩展接收者优先,要引用分发接收者的成员你可以使用
       限定的this语法*/
    fun bar(){ println("E bar") } //与F类的bar同名

    fun baz() { println("E baz") }

    open fun F.big(){
        println("F.big in E")
    }

    open fun F1.big(){
        println("F1.big in E")
    }


    /**在E类内，创建了F类的扩展。此时E成为分发接收者,F成为扩展接收者.
       从本例可以看出,在扩展函数中，可以调用派发接收者的成员函数*/
    fun F.foo(){
        bar()
        baz()
    }

    fun F.mix(){
        bar()  //调用F.bar()
        this@E.bar()  //调用E.bar()
    }

    fun caller(f: F) {
        f.foo()
    }

    fun lead(f: F){
        f.mix()
    }

    fun wTInvoke(f: F){
        f.big()
    }

}

/**以成员的形式定义的扩展函数,可以声明为open,而且可以在子类中覆盖.也就是说,在这类扩展函数的派发过程中,
   针对分发接收者时虚拟的(virtual),但针对扩展接受者仍然是静态的*/
class E1 : E(){
    override fun F.big(){
        println("F.big in E1")
    }

    override fun F1.big(){
        println("F1.big in E1")
    }
}

fun main(arg:Array<String>){
    /*var user = User("Runoob")
    user.Print()*/

    /*val l = mutableListOf(1,2,3)
    *//**this关键字指代接收者对象，也就是调用扩展函数时，在点号之前指定的对象实例*//*
    l.swap(0,2)//'swap()'函数内的'this'将指向l的值
    println(l.toString())*/


    /**这里输出的是c，扩展函数是静态解析的，并不是接收者类型的虚拟成员，在调用扩展函数时，
     具体被调用的是哪一个函数，由调用函数的对象表达式来决定的，而不是动态的类型决定的*/
    //printFoo(D())//由参数C决定，而不是传入的D决定

    /*var c = C()
    c.bar()*///会优先使用成员函数

    /*var t = null
    println(t.toString())*///输出为null

    /*println("no:${MyClass.no}")
    MyClass.foo()*/

    /*val e: E = E()
    val f: F = F()
    e.caller(f)*/

    /*val e: E = E()
    val f: F = F()
    e.lead(f)*/


    E().wTInvoke(F())
    E1().wTInvoke(F())
    E().wTInvoke(F1())
}