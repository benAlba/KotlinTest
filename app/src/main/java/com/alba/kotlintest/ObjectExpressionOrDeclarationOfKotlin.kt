package com.alba.kotlintest

//对象表达式
class G{
/**匿名对象可以用作只在本地和私有作用域中声明的类型,
   如果你使用匿名对象作为公有函数的返回类型或者用作公有属性的类型,
   那么该函数或属性的实际类型会是匿名对象声明的超类型,如果你没有声明
   任何超类型,就会是Any,在匿名对象中添加的成员将无法访问*/
    private fun foo() = object{//私有函数,所以其返回类型是匿名对象类型
        val x: String = "x"
    }

    fun publicFoo() = object{//公有函数,所以其返回类型是Any
        val x: String = "x"
    }

    fun bar(){
        val x1 = foo().x //没问题
        //val x2 = publicFoo().x //错误:未能解析的引用"x"
    }
}

fun main(args: Array<String>){
/**通过对象表达式可以越过类的定义直接得到一个对象*/
    val site = object {
        var name: String = "一撸神"
        var url: String = "www.ilussion.com"
    }
    println(site.name)
    println(site.url)


    var s1 = Site
    var s2 = Site
    s1.url = "www.dslite.com"
    println(s1.url)
    println(s2.url)

/**与对象表达式不同,当对象声明在另一个类的内部时,这个对象并不能通过外部类的实例访问,
   而只能通过类名来访问;同样该对象也不能直接访问到外部类的方法和变量*/
    var address = Address()
    //address.DeskTop.url //错误,不能通过外部类的实例访问
    Address.DeskTop.url
}

//对象声明
/**Kotlin使用object关键字来声明一个对象,Kotlin中我们可以方便地通过对象声明来获得一个单例*/
object Site {
    var url :String = ""
    var name:String = "神乳社"
}

class Address {
    var name = "ilussion中国"
    object DeskTop{
        var url = "www.ilussioncn.com"
        fun showName(){
            //println("desk legs $name") // 错误,该对象不能直接访问到外部类的方法和变量
        }
    }
}



interface Factory<T> {
    fun create(): T
}
//伴生对象
/**一个类里面只能声明一个内部关联对象,即关键字companion只能使用一次*/
class WhichClass {
    /**可以使用companion替代需要声明的对象名,即对象名可省略*/
    companion object : Factory<WhichClass> {
        override fun create(): WhichClass =WhichClass()
    }
}

val x = WhichClass.Companion