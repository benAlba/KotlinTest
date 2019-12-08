package com.alba.kotlintest

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

