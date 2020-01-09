package com.alba.kotlintest

interface MyInterface{
    var name: String  //name属性，抽象的

    fun bar()               //未实现
    //允许方法有默认实现
    fun foo(){              //已实现
        //可选的方法体
        println("foo")
    }
}

interface HerInterface{
    fun bar(){              //已实现
        println("Her bar")
    }

    fun foo(){              //已实现
        println("her foo")
    }
}

class Boy : MyInterface{
    override var name: String = "Luyan"

    override fun bar() {
        println("bar")      //重写
    }

}

//主构造函数中重写属性
class Child(override var name: String) : MyInterface, HerInterface{
    //override var name: String ="LuJunFeng"

    init {
        name = "LuJunFeng"
    }

    fun tellyou(){
        println("name:"+name)
    }

    override fun foo() {
        println("our foo")
    }

    override fun bar() {
        //方法体
        println("our bar")
    }
}

fun main(args: Array<String>){
    val c = Child("zero")
    c.tellyou()
    c.foo()
    c.bar()

}