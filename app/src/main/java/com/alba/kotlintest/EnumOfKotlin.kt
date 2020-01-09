package com.alba.kotlintest

//当前的用法java都囊括了,只是不知java enum的其他用法,kotlin是否也支持

enum class Color{
    RED, BLACK, BLUE, GREEN, WHITE
}

enum class RGB{ RED, GREEN, BLUE}

inline fun <reified T : Enum<T>> printAllValues(){
    println(enumValues<T>().joinToString { it.name })
}

fun main(args: Array<String>){
    var color:Color=Color.BLUE

    println(Color.values())//values()以数组形式返回枚举值
    println(Color.valueOf("RED"))//valueOf()转换指定name为枚举值,若未匹配成功,会抛出IllegalArgumentException
    println(color.name)//获取枚举名称
    println(color.ordinal)//获取枚举值在所有枚举数组中定义的顺序


    printAllValues<RGB>()

}