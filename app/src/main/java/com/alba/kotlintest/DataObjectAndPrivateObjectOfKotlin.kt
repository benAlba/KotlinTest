package com.alba.kotlintest

//数据类 关键字为data
data class Earth(val name: String, val age: Int)

//密封类用来表示受限的类继承结构:当一个值为有限几种的类型,而不能有任何其他类型时
//密封类的一个子类可以有包含状态的多个实例
sealed class Expr
data class Const(val number: Double) : Expr()
data class Sum(val e1: Expr, val e2: Expr): Expr()
object NotANumber : Expr()


fun eval(expr: Expr): Double = when(expr) {
    is Const -> expr.number
    is Sum -> eval(expr.e1) + eval(expr.e2)
    NotANumber -> Double.NaN
}

fun main(args: Array<String>){
    val jack = Earth(name = "Jack", age = 20)
    /**通过copy()函数复制对象并修改部分属性*/
    val olderJack = jack.copy(age = 60)
    println(jack)
    println(olderJack)


    //什么是组件函数？ 什么是结构声明？

    //组件函数允许数据类在解构声明中使用
    val jane = Earth("Jane",35)
    val (name, age) = jane
    println("$name, $age years of age") //Jane, 35 years of age
}


