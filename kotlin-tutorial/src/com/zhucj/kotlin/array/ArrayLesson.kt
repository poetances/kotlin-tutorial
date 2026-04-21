package com.zhucj.kotlin.array

/**
 * Kotlin 数组与集合详解
 * 
 * 本文件详细演示：
 * 1. Array<T> - 泛型数组
 * 2. 基本类型数组（IntArray, DoubleArray等）
 * 3. List/Set/Map 集合体系
 * 4. 底层实现逻辑与差异性分析
 */
class ArrayLesson {

    // ==================== 1. Array<T> 泛型数组 ====================
    
    /**
     * 演示 Array<T> 的使用
     * 
     * 底层实现：
     * - JVM 上编译为 Object[] 数组
     * - 支持泛型，可以存储任意对象类型
     * - 大小固定，创建后不可改变长度
     * - 性能相对较低（因为装箱/拆箱操作）
     */
    fun demonstrateGenericArray() {
        println("========== Array<T> 泛型数组 ==========")
        
        // 创建方式1：使用 arrayOf
        val stringArray: Array<String> = arrayOf("Kotlin", "Java", "Scala")
        println("字符串数组: ${stringArray.joinToString(", ")}")
        
        // 创建方式2：使用构造函数 + lambda
        val intArray: Array<Int> = Array(5) { index -> index * 2 }
        println("整数数组: ${intArray.joinToString(", ")}")
        
        // 创建方式3：空数组
        val emptyArray: Array<String> = emptyArray()
        println("空数组大小: ${emptyArray.size}")
        
        // 访问和修改元素
        stringArray[0] = "Python"  // 修改元素
        println("修改后: ${stringArray[0]}")
        
        // 常用操作
        println("数组大小: ${stringArray.size}")
        println("是否包含 Java: ${stringArray.contains("Java")}")
        println("第一个元素: ${stringArray.first()}")
        println("最后一个元素: ${stringArray.last()}")
        
        // 遍历
        print("遍历: ")
        for (item in stringArray) {
            print("$item ")
        }
        println()
        
        // 注意：Array 是可变大小的引用，但长度固定
        // stringArray.add("Go")  // ❌ 编译错误：Array 没有 add 方法
        
        println()
    }
    
    // ==================== 2. 基本类型数组 ====================
    
    /**
     * 演示基本类型数组（Primitive Arrays）
     * 
     * 包括：IntArray, LongArray, DoubleArray, FloatArray, 
     *       BooleanArray, CharArray, ShortArray, ByteArray
     * 
     * 底层实现：
     * - JVM 上直接对应基本类型数组（如 int[], double[]）
     * - 避免了装箱/拆箱，性能更高
     * - 内存占用更少
     * - 不支持泛型
     */
    fun demonstratePrimitiveArrays() {
        println("========== 基本类型数组 ==========")
        
        // IntArray - 对应 JVM 的 int[]
        val intArray: IntArray = intArrayOf(1, 2, 3, 4, 5)
        println("IntArray: ${intArray.joinToString(", ")}")
        println("IntArray 类型: ${intArray.javaClass.simpleName}")  // 输出: int[]
        
        // DoubleArray - 对应 JVM 的 double[]
        val doubleArray: DoubleArray = doubleArrayOf(1.1, 2.2, 3.3)
        println("DoubleArray: ${doubleArray.joinToString(", ")}")
        println("DoubleArray 类型: ${doubleArray.javaClass.simpleName}")  // 输出: double[]
        
        // 使用构造函数创建
        val largeArray: IntArray = IntArray(10) { it * 10 }
        println("大数组: ${largeArray.joinToString(", ")}")
        
        // 性能对比示例
        println("\n性能对比:")
        val iterations = 1_000_000
        
        // Array<Int> - 需要装箱
        val startTime1 = System.nanoTime()
        val boxedArray = Array(iterations) { it }
        var sum1 = 0
        for (i in boxedArray) {
            sum1 += i  // 自动拆箱
        }
        val endTime1 = System.nanoTime()
        
        // IntArray - 无需装箱
        val startTime2 = System.nanoTime()
        val primitiveArray = IntArray(iterations) { it }
        var sum2 = 0
        for (i in primitiveArray) {
            sum2 += i  // 直接操作基本类型
        }
        val endTime2 = System.nanoTime()
        
        println("Array<Int> 耗时: ${(endTime1 - startTime1) / 1_000_000.0} ms")
        println("IntArray 耗时: ${(endTime2 - startTime2) / 1_000_000.0} ms")
        println("IntSum=$sum1, PrimitiveSum=$sum2")
        
        // 基本类型数组的特有方法
        println("\nIntArray 特有方法:")
        println("求和: ${intArray.sum()}")
        println("平均值: ${intArray.average()}")
        println("最大值: ${intArray.maxOrNull()}")
        println("最小值: ${intArray.minOrNull()}")
        println("排序: ${intArray.sorted().joinToString(", ")}")
        
        println()
    }
    
    // ==================== 3. List 集合体系 ====================
    
    /**
     * 演示 List 集合
     * 
     * List 分为两种：
     * 1. List<T> - 只读接口（不可变视图）
     * 2. MutableList<T> - 可变接口
     * 
     * 底层实现：
     * - 默认实现是 ArrayList（基于动态数组）
     * - listOf() 返回的是只读视图，可能优化为单例或小型优化列表
     * - mutableListOf() 返回 ArrayList
     * - 支持动态扩容
     * - 随机访问 O(1)，插入/删除 O(n)
     */
    fun demonstrateList() {
        println("========== List 集合 ==========")
        
        // 只读 List - 不可变视图。里面的元素不能修改。其实底层也是 ArrayList实现。
        val readOnlyList: List<String> = listOf("Apple", "Banana", "Cherry")
        println("只读 List: $readOnlyList")
        println("只读 List 类型: ${readOnlyList.javaClass.simpleName}")

        // readOnlyList.add("Date")  // ❌ 编译错误：只读
        
        // 可变 List - 底层 ArrayList 实现
        val mutableList: MutableList<String> = mutableListOf("Apple", "Banana")
        println("可变 List: $mutableList")
        println("可变 List 类型: ${mutableList.javaClass.simpleName}")  // ArrayList
        
        mutableList.add("Cherry")
        mutableList.remove("Apple")
        mutableList[0] = "Blueberry"
        println("修改后: $mutableList")
        
        // 转换为可变/只读
        val toMutable: MutableList<String> = readOnlyList.toMutableList()
        val toReadOnly: List<String> = mutableList.toList()
        
        // 常用操作
        println("\nList 常用操作:")
        val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        println("原始列表: $numbers")
        println("过滤偶数: ${numbers.filter { it % 2 == 0 }}")
        println("映射加倍: ${numbers.map { it * 2 }}")
        println("前3个: ${numbers.take(3)}")
        println("跳过3个: ${numbers.drop(3)}")
        println("是否存在大于5: ${numbers.any { it > 5 }}")
        println("是否全部小于20: ${numbers.all { it < 20 }}")
        println("查找第一个大于5: ${numbers.find { it > 5 }}")
        
        // 性能特性
        println("\n性能特性:")
        println("随机访问 list[5]: O(1) - 基于数组索引")
        println("头部插入: O(n) - 需要移动所有元素")
        println("尾部追加: O(1) 摊销 - 动态扩容")
        println("中间删除: O(n) - 需要移动元素")

        println()
    }
    
    // ==================== 4. Set 集合体系 ====================
    
    /**
     * 演示 Set 集合
     * 
     * Set 分为两种：
     * 1. Set<T> - 只读接口
     * 2. MutableSet<T> - 可变接口
     * 
     * 底层实现：
     * - 默认实现是 LinkedHashSet（保持插入顺序）
     * - setOf() 小集合可能优化为单元素或空集合实例
     * - 基于哈希表，元素唯一
     * - 查找、插入、删除都是 O(1)
     */
    fun demonstrateSet() {
        println("========== Set 集合 ==========")
        
        // 只读 Set
        val readOnlySet: Set<String> = setOf("Apple", "Banana", "Cherry", "Apple")
        println("只读 Set（自动去重）: $readOnlySet")
        println("只读 Set 类型: ${readOnlySet.javaClass.simpleName}")
        
        // 可变 Set - LinkedHashSet 实现
        val mutableSet: MutableSet<String> = mutableSetOf("Apple", "Banana")
        println("可变 Set: $mutableSet")
        println("可变 Set 类型: ${mutableSet.javaClass.simpleName}")  // LinkedHashSet
        
        mutableSet.add("Cherry")
        mutableSet.add("Apple")  // 重复，不会添加
        println("添加后: $mutableSet")
        
        mutableSet.remove("Banana")
        println("删除后: $mutableSet")
        
        // HashSet vs LinkedHashSet vs TreeSet
        println("\n不同 Set 实现:")
        
        // HashSet - 不保证顺序，性能最好
        val hashSet = hashSetOf("C", "A", "B")
        println("HashSet（无序）: $hashSet")
        
        // LinkedHashSet - 保持插入顺序（默认）
        val linkedHashSet = linkedSetOf("C", "A", "B")
        println("LinkedHashSet（插入顺序）: $linkedHashSet")
        
        // TreeSet - 自然排序
        val treeSet = sortedSetOf("C", "A", "B")
        println("TreeSet（排序）: $treeSet")
        
        // Set 操作
        println("\nSet 集合运算:")
        val set1 = setOf(1, 2, 3, 4, 5)
        val set2 = setOf(4, 5, 6, 7, 8)
        
        println("集合1: $set1")
        println("集合2: $set2")
        println("并集: ${set1.union(set2)}")
        println("交集: ${set1.intersect(set2)}")
        println("差集: ${set1.subtract(set2)}")
        
        println("\n性能特性:")
        println("查找 contains: O(1) - 基于哈希")
        println("插入 add: O(1) - 基于哈希")
        println("删除 remove: O(1) - 基于哈希")
        println("遍历: O(n)")
        
        println()
    }
    
    // ==================== 5. Map 集合体系 ====================
    
    /**
     * 演示 Map 集合
     * 
     * Map 分为两种：
     * 1. Map<K, V> - 只读接口
     * 2. MutableMap<K, V> - 可变接口
     * 
     * 底层实现：
     * - 默认实现是 LinkedHashMap（保持插入顺序）
     * - mapOf() 小集合可能优化
     * - 基于哈希表，键唯一
     * - 查找、插入、删除都是 O(1)
     */
    fun demonstrateMap() {
        println("========== Map 集合 ==========")
        
        // 只读 Map
        val readOnlyMap: Map<String, Int> = mapOf(
            "Apple" to 1,
            "Banana" to 2,
            "Cherry" to 3
        )
        println("只读 Map: $readOnlyMap")
        println("只读 Map 类型: ${readOnlyMap.javaClass.simpleName}")
        
        // 可变 Map - LinkedHashMap 实现
        val mutableMap: MutableMap<String, Int> = mutableMapOf("Apple" to 1)
        println("可变 Map: $mutableMap")
        println("可变 Map 类型: ${mutableMap.javaClass.simpleName}")  // LinkedHashMap
        
        mutableMap["Banana"] = 2
        mutableMap.put("Cherry", 3)
        println("添加后: $mutableMap")
        
        mutableMap.remove("Apple")
        println("删除后: $mutableMap")
        
        // 访问元素
        println("\n访问元素:")
        println("get Banana: ${mutableMap["Banana"]}")
        println("getOrDefault Grape: ${mutableMap.getOrElse("Grape") { 0 }}")
        println("getValue Cherry: ${mutableMap.getValue("Cherry")}")
        
        // 遍历
        println("\n遍历 Map:")
        for ((key, value) in mutableMap) {
            println("  $key -> $value")
        }
        
        // Map 转换
        println("\nMap 转换:")
        val scores = mapOf("Alice" to 85, "Bob" to 92, "Charlie" to 78)
        println("原始: $scores")
        println("过滤 > 80: ${scores.filterValues { it > 80 }}")
        println("映射加分: ${scores.mapValues { it.value + 10 }}")
        println("按键排序: ${scores.toSortedMap()}")
        
        // 不同 Map 实现
        println("\n不同 Map 实现:")
        val hashMap = hashMapOf("C" to 3, "A" to 1, "B" to 2)
        println("HashMap（无序）: $hashMap")
        
        val linkedHashMap = linkedMapOf("C" to 3, "A" to 1, "B" to 2)
        println("LinkedHashMap（插入顺序）: $linkedHashMap")
        
        val treeMap = sortedMapOf("C" to 3, "A" to 1, "B" to 2)
        println("TreeMap（排序）: $treeMap")
        
        println("\n性能特性:")
        println("查找 get: O(1) - 基于哈希")
        println("插入 put: O(1) - 基于哈希")
        println("删除 remove: O(1) - 基于哈希")
        println("遍历: O(n)")
        
        println()
    }
    
    // ==================== 6. 底层实现深度对比 ====================
    
    /**
     * 深度对比各种数组/集合的底层实现
     */
    fun demonstrateImplementationDifferences() {
        println("========== 底层实现深度对比 ==========")
        
        println("\n1. 内存布局对比:")
        println("┌─────────────────┬──────────────────┬──────────────┐")
        println("│ 类型            │ JVM 实际类型     │ 内存效率     │")
        println("├─────────────────┼──────────────────┼──────────────┤")
        println("│ Array<Int>      │ Integer[]        │ 低（装箱）   │")
        println("│ IntArray        │ int[]            │ 高（原生）   │")
        println("│ List<Int>       │ ArrayList<Integer│ 低（装箱）   │")
        println("│ Array<String>   │ String[]         │ 中（引用）   │")
        println("└─────────────────┴──────────────────┴──────────────┘")
        
        println("\n2. 性能特征对比:")
        println("┌──────────────┬──────────┬──────────┬──────────┬──────────┐")
        println("│ 操作         │ Array    │ List     │ Set      │ Map      │")
        println("├──────────────┼──────────┼──────────┼──────────┼──────────┤")
        println("│ 随机访问     │ O(1)     │ O(1)     │ N/A      │ O(1)*    │")
        println("│ 查找         │ O(n)     │ O(n)     │ O(1)     │ O(1)     │")
        println("│ 插入(尾部)   │ N/A      │ O(1)*    │ O(1)     │ O(1)     │")
        println("│ 插入(中间)   │ N/A      │ O(n)     │ O(1)     │ O(1)     │")
        println("│ 删除         │ N/A      │ O(n)     │ O(1)     │ O(1)     │")
        println("│ 内存占用     │ 低       │ 中       │ 中高     │ 中高     │")
        println("└──────────────┴──────────┴──────────┴──────────┴──────────┘")
        println("* 摊销复杂度")
        
        println("\n3. 实际内存占用测试:")
        val size = 10_000
        
        // Array<Int> - 每个 Integer 对象约 16 字节 + 4 字节 int 值 = 20 字节
        val arrayBoxed = Array(size) { it }
        println("Array<Int> ($size 元素): 约 ${size * 20 / 1024} KB")
        
        // IntArray - 每个 int 只需 4 字节
        val arrayPrimitive = IntArray(size) { it }
        println("IntArray ($size 元素): 约 ${size * 4 / 1024} KB")
        
        // List<Int> - ArrayList + Integer 对象
        val listBoxed = List(size) { it }
        println("List<Int> ($size 元素): 约 ${size * 20 / 1024 + 100} KB (含ArrayList开销)")
        
        println("\n结论: IntArray 比 Array<Int> 节省约 80% 内存！")
        
        println("\n4. 选择建议:")
        println("✓ 存储基本类型大量数据 → 使用 IntArray, DoubleArray 等")
        println("✓ 需要动态增删 → 使用 MutableList")
        println("✓ 需要唯一性 → 使用 Set")
        println("✓ 需要键值对 → 使用 Map")
        println("✓ 固定大小且频繁随机访问 → 使用 Array")
        println("✓ 只需要读取 → 使用只读接口 List/Set/Map")
        
        println()
    }
    
    // ==================== 7. 高级特性 ====================
    
    /**
     * 演示 Kotlin 集合的高级特性
     */
    fun demonstrateAdvancedFeatures() {
        println("========== 高级特性 ==========")
        
        // 序列（Sequence）- 惰性求值
        println("\n1. Sequence 惰性求值:")
        val sequenceResult = (1..100).asSequence()
            .filter { 
                println("  过滤: $it")
                it % 2 == 0 
            }
            .map { 
                println("  映射: $it")
                it * 2 
            }
            .take(3)
            .toList()
        println("结果: $sequenceResult")
        println("注意: 只有前几个元素被处理，而不是全部100个")
        
        // 不可变性
        println("\n2. 不可变性优势:")
        val immutableList = listOf(1, 2, 3)
        // immutableList.add(4)  // ❌ 编译期阻止修改
        val modifiedList = immutableList + 4  // ✅ 创建新列表
        println("原列表: $immutableList")
        println("新列表: $modifiedList")
        println("原列表未改变: ${immutableList.size == 3}")
        
        // 解构声明
        println("\n3. 解构声明:")
        val pair = Pair("Key", "Value")
        val (key, value) = pair
        println("Pair 解构: key=$key, value=$value")
        
        val triple = Triple(1, 2, 3)
        val (a, b, c) = triple
        println("Triple 解构: a=$a, b=$b, c=$c")
        
        // 扩展函数
        println("\n4. 常用扩展函数:")
        val numbers = listOf(1, 2, 3, 4, 5)
        println("原始: $numbers")
        println("chunked(2): ${numbers.chunked(2)}")
        println("windowed(3): ${numbers.windowed(3)}")
        println("zipWithNext: ${numbers.zipWithNext { a, b -> a + b }}")
        println("associateBy: ${numbers.associateBy { it * it }}")
        println("groupBy: ${numbers.groupBy { if (it % 2 == 0) "even" else "odd" }}")
        
        println()
    }
    
    // ==================== 8. 实战练习 ====================
    
    /**
     * 综合实战练习
     */
    fun practicalExercise() {
        println("========== 实战练习 ==========")
        
        // 场景1: 学生成绩管理
        data class Student(val name: String, val score: Int)
        
        val students = listOf(
            Student("Alice", 85),
            Student("Bob", 92),
            Student("Charlie", 78),
            Student("David", 95),
            Student("Eve", 88)
        )
        
        println("\n1. 学生成绩分析:")
        println("所有学生: $students")
        
        // 按分数排序
        val sorted = students.sortedByDescending { it.score }
        println("按分数降序: $sorted")
        
        // 找出优秀学生（>= 90）
        val excellent = students.filter { it.score >= 90 }
        println("优秀学生: $excellent")
        
        // 计算平均分
        val average = students.map { it.score }.average()
        println("平均分: %.2f".format(average))
        
        // 分组
        val grouped = students.groupBy { 
            when {
                it.score >= 90 -> "优秀"
                it.score >= 80 -> "良好"
                it.score >= 70 -> "中等"
                else -> "待提高"
            }
        }
        println("分组统计: $grouped")
        
        // 场景2: 商品库存管理
        println("\n2. 商品库存管理:")
        val inventory = mutableMapOf(
            "iPhone" to 50,
            "iPad" to 30,
            "MacBook" to 20
        )
        
        println("当前库存: $inventory")
        
        // 更新库存
        inventory["iPhone"] = inventory.getOrDefault("iPhone", 0) - 5
        inventory["AirPods"] = 100  // 新增商品
        
        println("更新后: $inventory")
        
        // 库存预警
        val lowStock = inventory.filter { it.value < 25 }
        println("低库存商品: $lowStock")
        
        // 总库存数量
        val totalStock = inventory.values.sum()
        println("总库存: $totalStock")
        
        // 场景3: 使用 Set 去重
        println("\n3. 用户标签去重:")
        val userTags = listOf("Kotlin", "Java", "Android", "Kotlin", "iOS", "Java")
        println("原始标签: $userTags")
        
        val uniqueTags = userTags.toSet()
        println("去重后: $uniqueTags")
        
        // 场景4: 使用 Array 进行矩阵运算
        println("\n4. 矩阵运算（2x2）:")
        val matrix1 = Array(2) { IntArray(2) }
        val matrix2 = Array(2) { IntArray(2) }
        
        matrix1[0][0] = 1; matrix1[0][1] = 2
        matrix1[1][0] = 3; matrix1[1][1] = 4
        
        matrix2[0][0] = 5; matrix2[0][1] = 6
        matrix2[1][0] = 7; matrix2[1][1] = 8
        
        println("矩阵1:")
        matrix1.forEach { row -> println("  ${row.joinToString("\t")}") }
        
        println("矩阵2:")
        matrix2.forEach { row -> println("  ${row.joinToString("\t")}") }
        
        // 矩阵加法
        val result = Array(2) { IntArray(2) }
        for (i in 0..1) {
            for (j in 0..1) {
                result[i][j] = matrix1[i][j] + matrix2[i][j]
            }
        }
        
        println("相加结果:")
        result.forEach { row -> println("  ${row.joinToString("\t")}") }
        
        println()
    }
    
    // ==================== 主函数 ====================
}

fun main() {
    val lesson = ArrayLesson()
    
    lesson.demonstrateGenericArray()
    lesson.demonstratePrimitiveArrays()
    lesson.demonstrateList()
    lesson.demonstrateSet()
    lesson.demonstrateMap()
    lesson.demonstrateImplementationDifferences()
    lesson.demonstrateAdvancedFeatures()
    lesson.practicalExercise()
}