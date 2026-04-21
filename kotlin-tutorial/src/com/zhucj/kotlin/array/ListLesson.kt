package com.zhucj.kotlin.array

/**
 * Kotlin List 详解
 * 
 * ============================================================================
 * 【核心原理】
 * ============================================================================
 * 
 * 1. List 的本质
 *    - Kotlin 的 List 是一个接口（interface），不是具体的实现类
 *    - 底层实际使用的是 Java 的 ArrayList 或 Arrays.asList()
 *    - Kotlin 通过扩展函数为 List 提供了丰富的操作 API
 * 
 * 2. 不可变 vs 可变的设计哲学
 *    - List<T>（只读）：编译期保证不能修改，提供线程安全性
 *    - MutableList<T>（可变）：继承自 List，额外提供增删改方法
 *    - 这是 Kotlin "默认不可变" 设计理念的体现，减少副作用
 * 
 * 3. 与 Java 的关键区别
 * 
 *    ┌─────────────────┬──────────────────────┬──────────────────────┐
 *    │     特性         │      Java            │      Kotlin          │
 *    ├─────────────────┼──────────────────────┼──────────────────────┤
 *    │ 类型声明         │ List<String> list    │ val list: List<String>│
 *    │ 创建方式         │ new ArrayList<>()    │ listOf() / mutableListOf()│
 *    │ 不可变性         │ 需要 Collections     │ 语言级别支持         │
 *    │                  │ .unmodifiableList()  │                      │
 *    │ 空安全           │ 可能 null            │ 默认非空，需显式声明  │
 *    │ 访问元素         │ list.get(0)          │ list[0] (运算符重载) │
 *    │ 大小             │ list.size()          │ list.size (属性)     │
 *    │ 遍历             │ for-each / iterator  │ forEach / for 循环   │
 *    │ 函数式操作       │ Stream API (Java 8+) │ 内置丰富的高阶函数    │
 *    │ 类型推断         │ 需要显式声明泛型      │ 自动推断类型          │
 *    └─────────────────┴──────────────────────┴──────────────────────┘
 * 
 * 4. 性能特点
 *    - 随机访问：O(1) - 基于数组实现
 *    - 尾部添加：O(1) 均摊 - ArrayList 动态扩容
 *    - 中间插入/删除：O(n) - 需要移动元素
 *    - 查找：O(n) - 线性搜索（未排序时）
 * 
 * 5. 内存模型
 *    - List 本身是引用类型，存储在堆上
 *    - 元素也是引用（对象）或值（基本类型装箱）
 *    - 使用 listOf() 创建的小列表可能使用优化实现
 *    - mutableListOf() 直接创建 ArrayList 实例
 * 
 * 6. 协变特性（Covariance）
 *    - List<out T> 是协变的，可以赋值给父类型
 *    - 例如：List<String> 可以赋值给 List<Any>
 *    - 这是因为只读列表不会修改元素，保证类型安全
 *    - MutableList<T> 是不变的，不能协变（因为可修改）
 * 
 * 7. 与 Java 互操作
 *    - Kotlin List 可以直接传给接受 java.util.List 的方法
 *    - Java List 可以直接在 Kotlin 中当作 List 使用
 *    - 注意：Java 的可变 List 在 Kotlin 中需要显式转换为 MutableList
 * 
 * ============================================================================
 * 
 * List 是 Kotlin 中最常用的集合类型之一，用于存储有序的元素集合。
 * Kotlin 提供了两种 List 类型：
 * 1. List<T> - 不可变列表（只读）
 * 2. MutableList<T> - 可变列表（可读写）
 */
class ListLesson {

    /**
     * 1. 创建 List
     */
    fun demonstrateListCreation() {
        println("=== 1. 创建 List ===")
        
        // 不可变列表（只读）
        val readOnlyList = listOf("Apple", "Banana", "Cherry")
        println("只读列表: $readOnlyList")
        
        // 可变列表（可修改）
        val mutableList = mutableListOf("Apple", "Banana", "Cherry")
        println("可变列表: $mutableList")
        
        // 空列表
        val emptyList = emptyList<String>()
        println("空列表: $emptyList")
        
        // 使用 listOfNotNull 过滤 null 值
        val listWithNulls = listOfNotNull("Apple", null, "Banana", null, "Cherry")
        println("过滤null后的列表: $listWithNulls")
        
        // 初始化列表（根据索引生成元素）
        val initializedList = List(5) { index -> "Item $index" }
        println("初始化列表: $initializedList")
        
        println()
    }

    /**
     * 2. 访问 List 元素
     */
    fun demonstrateListAccess() {
        println("=== 2. 访问 List 元素 ===")
        
        val fruits = listOf("Apple", "Banana", "Cherry", "Date", "Elderberry")
        
        // 通过索引访问
        println("第一个元素: ${fruits[0]}")
        println("第二个元素: ${fruits[1]}")
        
        // 使用 get 方法
        println("第三个元素: ${fruits.get(2)}")
        
        // 获取第一个和最后一个元素
        println("第一个元素: ${fruits.first()}")
        println("最后一个元素: ${fruits.last()}")
        
        // 安全访问（避免越界）
        val index = 10
        println("索引 $index 的元素: ${fruits.getOrNull(index)}")
        
        // 查找元素
        println("'Cherry' 的索引: ${fruits.indexOf("Cherry")}")
        println("'Grape' 的索引: ${fruits.indexOf("Grape")}")  // 不存在返回 -1
        
        println()
    }

    /**
     * 3. 修改可变列表
     */
    fun demonstrateMutableListOperations() {
        println("=== 3. 修改可变列表 ===")
        
        val mutableList = mutableListOf("Apple", "Banana", "Cherry")
        println("原始列表: $mutableList")
        
        // 添加元素
        mutableList.add("Date")
        println("添加后: $mutableList")
        
        // 在指定位置插入
        mutableList.add(1, "Blueberry")
        println("插入后: $mutableList")
        
        // 修改元素
        mutableList[0] = "Apricot"
        println("修改后: $mutableList")
        
        // 删除元素
        mutableList.remove("Banana")
        println("删除 'Banana' 后: $mutableList")
        
        // 删除指定位置的元素
        mutableList.removeAt(1)
        println("删除索引1后: $mutableList")
        
        // 清空列表
        mutableList.clear()
        println("清空后: $mutableList")
        
        println()
    }

    /**
     * 4. List 常用操作函数
     */
    fun demonstrateListOperations() {
        println("=== 4. List 常用操作函数 ===")
        
        val numbers = listOf(5, 2, 8, 1, 9, 3, 7, 4, 6)
        println("原始列表: $numbers")
        
        // 大小
        println("列表大小: ${numbers.size}")
        
        // 判断是否为空
        println("是否为空: ${numbers.isEmpty()}")
        println("是否非空: ${numbers.isNotEmpty()}")
        
        // 包含判断
        println("是否包含 5: ${numbers.contains(5)}")
        println("是否包含 10: ${numbers.contains(10)}")
        
        // 排序
        println("升序排序: ${numbers.sorted()}")
        println("降序排序: ${numbers.sortedDescending()}")
        
        // 反转
        println("反转: ${numbers.reversed()}")
        
        // 去重
        val withDuplicates = listOf(1, 2, 2, 3, 3, 3, 4)
        println("去重前: $withDuplicates")
        println("去重后: ${withDuplicates.distinct()}")
        
        println()
    }

    /**
     * 5. 过滤和转换
     */
    fun demonstrateFilterAndTransform() {
        println("=== 5. 过滤和转换 ===")
        
        val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        println("原始列表: $numbers")
        
        // 过滤
        val evenNumbers = numbers.filter { it % 2 == 0 }
        println("偶数: $evenNumbers")
        
        val greaterThanFive = numbers.filter { it > 5 }
        println("大于5的数: $greaterThanFive")
        
        // 映射（转换）
        val doubled = numbers.map { it * 2 }
        println("翻倍: $doubled")
        
        val strings = numbers.map { "Number $it" }
        println("转换为字符串: $strings")
        
        // 过滤并映射
        val evenDoubled = numbers.filter { it % 2 == 0 }.map { it * 2 }
        println("偶数翻倍: $evenDoubled")
        
        // flatMap（扁平化映射）
        val nestedLists = listOf(listOf(1, 2), listOf(3, 4), listOf(5, 6))
        val flattened = nestedLists.flatMap { it }
        println("扁平化: $flattened")
        
        println()
    }

    /**
     * 6. 聚合操作
     */
    fun demonstrateAggregationOperations() {
        println("=== 6. 聚合操作 ===")
        
        val numbers = listOf(5, 2, 8, 1, 9, 3, 7, 4, 6)
        println("列表: $numbers")
        
        // 求和
        println("总和: ${numbers.sum()}")
        
        // 平均值
        println("平均值: ${numbers.average()}")
        
        // 最大值和最小值
        println("最大值: ${numbers.maxOrNull()}")
        println("最小值: ${numbers.minOrNull()}")
        
        // 计数
        println("偶数个数: ${numbers.count { it % 2 == 0 }}")
        
        // 归约（reduce）
        val product = numbers.reduce { acc, num -> acc * num }
        println("所有数的乘积: $product")
        
        // fold（带初始值的归约）
        val sumWithInitial = numbers.fold(100) { acc, num -> acc + num }
        println("带初始值100的总和: $sumWithInitial")
        
        println()
    }

    /**
     * 7. 切片和子列表
     */
    fun demonstrateSlicing() {
        println("=== 7. 切片和子列表 ===")
        
        val fruits = listOf("Apple", "Banana", "Cherry", "Date", "Elderberry", "Fig")
        println("原始列表: $fruits")
        
        // 获取前 n 个元素
        println("前3个: ${fruits.take(3)}")
        
        // 获取后 n 个元素
        println("后2个: ${fruits.takeLast(2)}")
        
        // 丢弃前 n 个元素
        println("丢弃前2个: ${fruits.drop(2)}")
        
        // 丢弃后 n 个元素
        println("丢弃后2个: ${fruits.dropLast(2)}")
        
        // 切片（子列表）
        println("索引1到3: ${fruits.subList(1, 4)}")
        
        // 满足条件的元素（从头开始）
        println("长度<=5的前缀: ${fruits.takeWhile { it.length <= 5 }}")
        
        println()
    }

    /**
     * 8. 分组和分区
     */
    fun demonstrateGroupingAndPartitioning() {
        println("=== 8. 分组和分区 ===")
        
        val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        println("原始列表: $numbers")
        
        // 按条件分组
        val groupedByEven = numbers.groupBy { if (it % 2 == 0) "偶数" else "奇数" }
        println("按奇偶分组: $groupedByEven")
        
        // 按字符串长度分组
        val words = listOf("apple", "bat", "car", "dog", "elephant")
        val groupedByLength = words.groupBy { it.length }
        println("按长度分组: $groupedByLength")
        
        // 分区（分成两组）
        val partitioned = numbers.partition { it % 2 == 0 }
        println("分区 - 偶数: ${partitioned.first}")
        println("分区 - 奇数: ${partitioned.second}")
        
        println()
    }

    /**
     * 9. 遍历 List
     */
    fun demonstrateIteration() {
        println("=== 9. 遍历 List ===")
        
        val fruits = listOf("Apple", "Banana", "Cherry")
        
        // forEach
        println("forEach 遍历:")
        fruits.forEach { println("  - $it") }
        
        // forEachIndexed（带索引）
        println("\nforEachIndexed 遍历:")
        fruits.forEachIndexed { index, fruit ->
            println("  [$index] $fruit")
        }
        
        // for 循环
        println("\nfor 循环遍历:")
        for (fruit in fruits) {
            println("  - $fruit")
        }
        
        // for 循环带索引
        println("\nfor 循环带索引:")
        for ((index, fruit) in fruits.withIndex()) {
            println("  [$index] $fruit")
        }
        
        println()
    }

    /**
     * 10. List 与 Array 的转换
     */
    fun demonstrateListArrayConversion() {
        println("=== 10. List 与 Array 的转换 ===")
        
        // List 转 Array
        val list = listOf("Apple", "Banana", "Cherry")
        val array = list.toTypedArray()
        println("List: $list")
        println("Array: ${array.contentToString()}")
        
        // Array 转 List
        val newArray = arrayOf("Date", "Elderberry", "Fig")
        val newList = newArray.toList()
        println("\nArray: ${newArray.contentToString()}")
        println("List: $newList")
        
        // IntArray 转 List
        val intArray = intArrayOf(1, 2, 3, 4, 5)
        val intList = intArray.toList()
        println("\nIntArray: ${intArray.contentToString()}")
        println("List: $intList")
        
        println()
    }

    /**
     * 11. 实际应用场景
     */
    fun practicalExercise() {
        println("=== 11. 实际应用场景 ===")
        
        // 场景1: 学生成绩管理
        data class Student(val name: String, val score: Double)
        
        val students = listOf(
            Student("Alice", 85.5),
            Student("Bob", 92.3),
            Student("Charlie", 78.0),
            Student("David", 95.7),
            Student("Eve", 88.2)
        )
        
        println("学生成绩管理:")
        
        // 找出及格的学生（>= 60）
        val passedStudents = students.filter { it.score >= 60 }
        println("及格学生: ${passedStudents.map { it.name }}")
        
        // 找出优秀学生（>= 90）
        val excellentStudents = students.filter { it.score >= 90 }
        println("优秀学生: ${excellentStudents.map { "${it.name}(${it.score})" }}")
        
        // 按成绩排序
        val sortedByScore = students.sortedByDescending { it.score }
        println("按成绩降序: ${sortedByScore.map { "${it.name}:${it.score}" }}")
        
        // 计算平均分
        val averageScore = students.map { it.score }.average()
        println("平均成绩: %.2f".format(averageScore))
        
        // 最高分和最低分
        val highestScore = students.maxByOrNull { it.score }
        val lowestScore = students.minByOrNull { it.score }
        println("最高分: ${highestScore?.name} (${highestScore?.score})")
        println("最低分: ${lowestScore?.name} (${lowestScore?.score})")
        
        println()
        
        // 场景2: 数据处理管道
        println("数据处理管道:")
        val rawData = listOf("  apple ", "BANANA", "  cherry  ", "DATE", "  elderberry  ")
        println("原始数据: $rawData")
        
        // 清理、转换、过滤、排序
        val processed = rawData
            .map { it.trim() }           // 去除空格
            .map { it.lowercase() }      // 转小写
            .filter { it.length > 4 }    // 过滤长度>4的
            .sorted()                     // 排序
        
        println("处理后: $processed")
        
        println()
    }
}

fun main() {
    val listLesson = ListLesson()
    
    // 运行所有示例
    listLesson.demonstrateListCreation()
    listLesson.demonstrateListAccess()
    listLesson.demonstrateMutableListOperations()
    listLesson.demonstrateListOperations()
    listLesson.demonstrateFilterAndTransform()
    listLesson.demonstrateAggregationOperations()
    listLesson.demonstrateSlicing()
    listLesson.demonstrateGroupingAndPartitioning()
    listLesson.demonstrateIteration()
    listLesson.demonstrateListArrayConversion()
    listLesson.practicalExercise()
}