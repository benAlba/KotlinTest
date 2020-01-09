package com.alba.kotlintest

import kotlin.properties.Delegates
import kotlin.reflect.KProperty

//委托模式时软件设计模式中的一项基本技巧,
//在委托模式中,有两个对象参与处理同一个请求,
//接受请求的对象将请求委托给另一个对象来处理


//类委托
//创建接口
interface Base {
    fun print()
}
//实现此接口的被委托的类
class BaseImpl(val x: Int) : Base {
    override fun print() {
        println(x)
    }
}
/**kotlin通过关键字by实现委托*/
class Derived(b: Base) : Base by b//通过关键字by 建立委托类

fun main(args: Array<String>) {
    val b = BaseImpl(10)
    Derived(b).print()//这里会调用到BaseImpl的print()

    val e = Example()
    println(e.p)//访问该属性, 调用getValue()函数

    e.p = "Runoob"//调用 setValue()函数
    println(e.p)

    println(lazyValue)//第一次执行, 执行两次输出表达式
    println(lazyValue)//第二次执行, 只输出返回值

    val people = People()
    people.name = "第一次赋值"
    people.name = "第二次赋值"

    val site = InternetSite(mapOf(//这里如果使用var属性, 需要把Map换成MutableMap
        "name" to "菜鸟教程",
        "url"  to "www.runoob.com"
    ))
    println(site.name)
    println(site.url)

    var zoo = Zoo()
    zoo.notNullBar = "bar"
    println(zoo.notNullBar)
}


//属性委托
//定义包含属性委托的类
class Example {
    var p: String by Delegate()
}
//委托的类
class Delegate {
    operator fun getValue(thisRef:Any?, property: KProperty<*>): String {
        return "$thisRef, 这里委托了${property.name} 属性"
    }
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$thisRef 的 ${property.name} 属性赋值为 $value")
    }
}

//标准委托
/**延迟属性lazy: lazy()是一个函数, 接受一个Lambda表达式作为参数, 返回一个Lazy<T>实例的函数, 返回的实例
   可以作为实现延迟属性的委托: 第一次调用get()会执行已传递给lazy()表达式并记录结果, 后续调用get()只是返回记录的结果*/
 val lazyValue: String by lazy {
    println("computed!")
    "Hello"
}

//可观察属性Observable
/**observable可以用于实现观察者模式
   Delegates.observable()函数接受两个参数: 第一个是初始化值, 第二个是属性值变化事件的响应器(handler)
   在属性赋值后会执行事件的响应器(handler), 它有3个参数: 被赋值的属性、旧值和新值*/
class People{
    var name: String by Delegates.observable("初始值"){
        prop, old, new ->
        println("旧值:$old -> 新值:$new")
    }
}

//把属性存储在映射中
/**一个常见的用例是在一个映射(map)里存储属性的值,
   在这种情况下, 你可以使用映射作为委托来实现委托属性*/
class InternetSite(val map: Map<String, Any?>){
    val name: String by map
    val url: String by map
}

//Not Null
/**notNull适用于那些无法在初始化阶段就确定属性值的场合*/
class Zoo {
    var notNullBar: String by Delegates.notNull<String>()
}

//局部委托属性
/*
fun sea(computeFoo:() ->Foo){
    val memorizedFoo by lazy(computeFoo)

    if (someCondition && memorizedFoo.isValid()){
        memorizedFoo.doSomething()
    }
}
*/

//属性委托要求

//翻译规则

//提供委托

