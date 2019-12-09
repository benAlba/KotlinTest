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

class Person {

    var lastName: String = "Lu"
        get() = field.toUpperCase() //将变量赋值后转换为大写
        set

    var no: Int = 100
        get() = field               //后端变量

        //Type mismatch: inferred type is () -> Unit but Unit was expected
        set(value) = {
            if (value<10){
                field = value       //如果传入的值小于10返回该值
            }else{
                field = -1          //如果传入的值大于等于10返回-1
            }
        }

    var heiht: Float = 145.4f
        private set

}

//测试
fun main(args: Array<String>){
    var  person: Person = Person()

    person.lastName = "yang"

    println("lastname:${person.lastName}")

    person.no = 9
    println("no:${person.no}")

    person.no = 20
    println("no:${person.no}")
}

