package com.zhucj.kotlin.array

/**
 * Kotlin Map 详解
 * 
 * ============================================================================
 * 【核心原理】
 * ============================================================================
 * 
 * 1. Map 的本质
 *    - Kotlin 的 Map 是一个接口（interface），不是具体的实现类
 *    - 底层实际使用的是 Java 的 HashMap、LinkedHashMap 或 TreeMap
 *    - Kotlin 通过扩展函数为 Map 提供了丰富的操作 API
 *    - Map 存储的是键值对（Key-Value pairs）
 * 
 * 2. 不可变 vs 可变的设计哲学
 *    - Map<K, V>（只读）：编译期保证不能修改，提供线程安全性
 *    - MutableMap<K, V>（可变）：继承自 Map，额外提供增删改方法
 *    - 这是 Kotlin "默认不可变" 设计理念的体现，减少副作用
 * 
 * 3. 与 Java 的关键区别
 * 
 *    ┌─────────────────┬──────────────────────┬──────────────────────┐
 *    │     特性         │      Java            │      Kotlin          │
 *    ├─────────────────┼──────────────────────┼──────────────────────┤
 *    │ 类型声明         │ Map<String, Integer> │ val map: Map<String, Int>│
 *    │ 创建方式         │ new HashMap<>()      │ mapOf() / mutableMapOf()│
 *    │ 不可变性         │ Collections.         │ 语言级别支持         │
 *    │                  │ unmodifiableMap()    │                      │
 *    │ 空安全           │ 可能 null            │ 默认非空，需显式声明  │
 *    │ 访问元素         │ map.get(key)         │ map[key] (运算符重载) │
 *    │ 大小             │ map.size()           │ map.size (属性)      │
 *    │ 遍历             │ entrySet() + for     │ forEach / for 循环   │
 *    │ 函数式操作       │ Stream API (Java 8+) │ 内置丰富的高阶函数    │
 *    │ 类型推断         │ 需要显式声明泛型      │ 自动推断类型          │
 *    │ Pair 创建        │ new AbstractMap.     │ key to value 语法糖  │
 *    │                  │ SimpleEntry()        │                      │
 *    └─────────────────┴──────────────────────┴──────────────────────┘
 * 
 * 4. 性能特点
 *    - HashMap: 查找/插入/删除 O(1) 平均，O(n) 最坏
 *    - LinkedHashMap: 保持插入顺序，性能略低于 HashMap
 *    - TreeMap: 保持键的自然顺序，操作 O(log n)
 *    - 内存开销：每个 Entry 需要存储 key、value、hash、next 引用
 * 
 * 5. 内存模型
 *    - Map 本身是引用类型，存储在堆上
 *    - Entry（键值对）也是对象，存储在堆上
 *    - Key 和 Value 可以是任何类型（包括 null，如果允许）
 *    - mapOf() 创建的小 Map 可能使用优化实现（单例或空 Map）
 *    - mutableMapOf() 直接创建 LinkedHashMap 实例
 * 
 * 6. 协变特性（Covariance）
 *    - Map<out K, out V> 是协变的，可以赋值给父类型
 *    - 例如：Map<String, Int> 可以赋值给 Map<Any, Any>
 *    - 这是因为只读 Map 不会修改内容，保证类型安全
 *    - MutableMap<K, V> 是不变的，不能协变（因为可修改）
 * 
 * 7. 与 Java 互操作
 *    - Kotlin Map 可以直接传给接受 java.util.Map 的方法
 *    - Java Map 可以直接在 Kotlin 中当作 Map 使用
 *    - 注意：Java 的可变 Map 在 Kotlin 中需要显式转换为 MutableMap
 *    - Kotlin 的 to 运算符创建的 Pair 与 Java 兼容
 * 
 * 8. hashCode 和 equals
 *    - Map 的相等性基于键值对的相等性，而非引用
 *    - 两个 Map 如果包含相同的键值对，则 equals 返回 true
 *    - Key 类型应该正确实现 hashCode 和 equals
 *    - 可变对象作为 Key 时要小心（修改后可能导致找不到值）
 * 
 * ============================================================================
 * 
 * Map 是 Kotlin 中用于存储键值对集合的重要数据结构。
 * Kotlin 提供了两种 Map 类型：
 * 1. Map<K, V> - 不可变映射（只读）
 * 2. MutableMap<K, V> - 可变映射（可读写）
 */
class MapLesson {

    /**
     * 1. 创建 Map
     */
    fun demonstrateMapCreation() {
        println("=== 1. 创建 Map ===")
        
        // 不可变 Map（只读）
        val readOnlyMap = mapOf(
            "name" to "Alice",
            "age" to "25",
            "city" to "Beijing"
        )
        println("只读 Map: $readOnlyMap")
        
        // 可变 Map（可修改）
        val mutableMap = mutableMapOf(
            "name" to "Alice",
            "age" to "25"
        )
        println("可变 Map: $mutableMap")
        
        // 空 Map
        val emptyMap = emptyMap<String, String>()
        println("空 Map: $emptyMap")
        
        // 使用 Pair 创建
        val pairMap = mapOf(
            Pair("key1", "value1"),
            Pair("key2", "value2")
        )
        println("使用 Pair 创建: $pairMap")
        
        // 从列表创建
        val pairs = listOf("a" to 1, "b" to 2, "c" to 3)
        val fromList = pairs.toMap()
        println("从列表创建: $fromList")
        
        // LinkedHashMap（保持插入顺序）
        val linkedMap = linkedMapOf(
            "third" to 3,
            "first" to 1,
            "second" to 2
        )
        println("LinkedHashMap（保持顺序）: $linkedMap")
        
        // TreeMap（按键排序）
        val treeMap = sortedMapOf(
            "banana" to 2,
            "apple" to 1,
            "cherry" to 3
        )
        println("TreeMap（按键排序）: $treeMap")
        
        println()
    }

    /**
     * 2. 访问 Map 元素
     */
    fun demonstrateMapAccess() {
        println("=== 2. 访问 Map 元素 ===")
        
        val userMap = mapOf(
            "name" to "Alice",
            "age" to 25,
            "city" to "Beijing",
            "email" to "alice@example.com"
        )
        
        // 通过 key 访问（使用 [] 运算符）
        println("姓名: ${userMap["name"]}")
        println("年龄: ${userMap["age"]}")
        
        // 使用 get 方法
        println("城市: ${userMap.get("city")}")
        
        // 访问不存在的 key（返回 null）
        println("电话: ${userMap["phone"]}")
        
        // 提供默认值
        println("电话（默认值）: ${userMap.getOrElse("phone") { "未知" }}")
        
        // 获取或设置默认值（仅 MutableMap）
        val mutableUserMap = userMap.toMutableMap()
        val phone = mutableUserMap.getOrPut("phone") { "123456" }
        println("电话: $phone")
        println("更新后的 Map: $mutableUserMap")
        
        // 检查 key 或 value 是否存在
        println("包含 key 'name': ${userMap.containsKey("name")}")
        println("包含 value 'Alice': ${userMap.containsValue("Alice")}")
        
        // 获取所有 keys 和 values
        println("所有 keys: ${userMap.keys}")
        println("所有 values: ${userMap.values}")
        
        // 获取所有 entries
        println("所有 entries: ${userMap.entries}")
        
        println()
    }

    /**
     * 3. 修改可变 Map
     */
    fun demonstrateMutableMapOperations() {
        println("=== 3. 修改可变 Map ===")
        
        val mutableMap = mutableMapOf(
            "name" to "Alice",
            "age" to 25
        )
        println("原始 Map: $mutableMap")
        
        // 添加或更新元素
        mutableMap["city"] = "Beijing"
        println("添加后: $mutableMap")
        
        // 使用 put 方法
        mutableMap.put("email", "alice@example.com")
        println("put 后: $mutableMap")
        
        // 更新现有值
        mutableMap["age"] = 26
        println("更新年龄后: $mutableMap")
        
        // 批量添加
        mutableMap.putAll(mapOf("phone" to "123456", "country" to "China"))
        println("批量添加后: $mutableMap")
        
        // 删除元素
        mutableMap.remove("phone")
        println("删除 phone 后: $mutableMap")
        
        // 删除并返回被删除的值
        val removedValue = mutableMap.remove("country")
        println("删除的值: $removedValue")
        println("删除后: $mutableMap")
        
        // 条件删除
        mutableMap.remove("email", "wrong@example.com")  // 值不匹配，不删除
        println("条件删除（失败）: $mutableMap")
        mutableMap.remove("email", "alice@example.com")  // 值匹配，删除
        println("条件删除（成功）: $mutableMap")
        
        // 清空 Map
        mutableMap.clear()
        println("清空后: $mutableMap")
        
        println()
    }

    /**
     * 4. Map 常用操作函数
     */
    fun demonstrateMapOperations() {
        println("=== 4. Map 常用操作函数 ===")
        
        val scores = mapOf(
            "Alice" to 85,
            "Bob" to 92,
            "Charlie" to 78,
            "David" to 95,
            "Eve" to 88
        )
        println("原始 Map: $scores")
        
        // 大小
        println("Map 大小: ${scores.size}")
        
        // 判断是否为空
        println("是否为空: ${scores.isEmpty()}")
        println("是否非空: ${scores.isNotEmpty()}")
        
        // 获取第一个和最后一个 entry
        println("第一个 entry: ${scores.entries.first()}")
        println("最后一个 entry: ${scores.entries.last()}")
        
        // 检查是否包含
        println("包含 key 'Alice': ${"Alice" in scores}")
        println("包含 key 'Frank': ${"Frank" in scores}")
        
        println()
    }

    /**
     * 5. 过滤和转换
     */
    fun demonstrateFilterAndTransform() {
        println("=== 5. 过滤和转换 ===")
        
        val scores = mapOf(
            "Alice" to 85,
            "Bob" to 92,
            "Charlie" to 78,
            "David" to 95,
            "Eve" to 88
        )
        println("原始 Map: $scores")
        
        // 过滤（返回 Map）
        val passed = scores.filter { (_, score) -> score >= 80 }
        println("及格的学生: $passed")
        
        // 过滤 keys
        val specificKeys = scores.filterKeys { it.startsWith("A") || it.startsWith("B") }
        println("名字以 A 或 B 开头: $specificKeys")
        
        // 过滤 values
        val highScores = scores.filterValues { it > 90 }
        println("高分（>90）: $highScores")
        
        // 映射（转换 values）
        val graded = scores.mapValues { (_, score) ->
            when {
                score >= 90 -> "A"
                score >= 80 -> "B"
                score >= 70 -> "C"
                else -> "D"
            }
        }
        println("等级转换: $graded")
        
        // 映射 keys
        val upperKeys = scores.mapKeys { (key, _) -> key.uppercase() }
        println("大写 keys: $upperKeys")
        
        // 同时映射 keys 和 values
        val transformed = scores.map { (key, value) ->
            key.uppercase() to "Score: $value"
        }.toMap()
        println("完全转换: $transformed")
        
        println()
    }

    /**
     * 6. 遍历 Map
     */
    fun demonstrateMapIteration() {
        println("=== 6. 遍历 Map ===")
        
        val userMap = mapOf(
            "name" to "Alice",
            "age" to 25,
            "city" to "Beijing"
        )
        
        // forEach
        println("forEach 遍历:")
        userMap.forEach { (key, value) ->
            println("  $key: $value")
        }
        
        // forEach 使用 entry
        println("\nforEach 使用 entry:")
        userMap.forEach { entry ->
            println("  ${entry.key} = ${entry.value}")
        }
        
        // for 循环解构
        println("\nfor 循环解构:")
        for ((key, value) in userMap) {
            println("  $key: $value")
        }
        
        // 遍历 keys
        println("\n遍历 keys:")
        for (key in userMap.keys) {
            println("  Key: $key")
        }
        
        // 遍历 values
        println("\n遍历 values:")
        for (value in userMap.values) {
            println("  Value: $value")
        }
        
        // 遍历 entries
        println("\n遍历 entries:")
        for (entry in userMap.entries) {
            println("  ${entry.key} -> ${entry.value}")
        }
        
        println()
    }

    /**
     * 7. 合并和操作多个 Map
     */
    fun demonstrateMapMerging() {
        println("=== 7. 合并和操作多个 Map ===")
        
        val map1 = mapOf("a" to 1, "b" to 2, "c" to 3)
        val map2 = mapOf("c" to 30, "d" to 4, "e" to 5)
        
        println("Map1: $map1")
        println("Map2: $map2")
        
        // 合并（后面的覆盖前面的）
        val merged = map1 + map2
        println("合并（map2 覆盖）: $merged")
        
        // 使用 merge 函数自定义合并逻辑
        val mergedWithLogic = map1.toMutableMap().apply {
            map2.forEach { (key, value) ->
                merge(key, value) { oldValue, newValue -> oldValue + newValue }
            }
        }
        println("合并（值相加）: $mergedWithLogic")
        
        // 减去一个 Map
        val subtracted = map1 - setOf("b", "c")
        println("减去 keys: $subtracted")
        
        println()
    }

    /**
     * 8. 分组和聚合
     */
    fun demonstrateGroupingAndAggregation() {
        println("=== 8. 分组和聚合 ===")
        
        data class Student(val name: String, val score: Int, val grade: String)
        
        val students = listOf(
            Student("Alice", 85, "A"),
            Student("Bob", 92, "A"),
            Student("Charlie", 78, "B"),
            Student("David", 95, "A"),
            Student("Eve", 88, "B")
        )
        
        // 按年级分组
        val groupedByGrade = students.groupBy { it.grade }
        println("按年级分组:")
        groupedByGrade.forEach { (grade, students) ->
            println("  $grade: ${students.map { it.name }}")
        }
        
        // 计算每个年级的平均分
        val averageByGrade = students.groupBy { it.grade }
            .mapValues { (_, students) ->
                students.map { it.score }.average()
            }
        println("\n各年级平均分: $averageByGrade")
        
        // 关联（将列表转换为 Map）
        val nameToStudent = students.associateBy { it.name }
        println("\n姓名到学生的映射: $nameToStudent")
        
        // 关联转换
        val nameToScore = students.associate { it.name to it.score }
        println("姓名到分数的映射: $nameToScore")
        
        println()
    }

    /**
     * 9. 安全操作和默认值
     */
    fun demonstrateSafeOperations() {
        println("=== 9. 安全操作和默认值 ===")
        
        val config = mapOf(
            "host" to "localhost",
            "port" to 8080
        )
        
        // getOrElse - 提供默认值
        val timeout = config.getOrElse("timeout") { 30 }
        println("超时时间: $timeout")
        
        // getOrDefault - Java 风格
        val maxConnections = config.getOrDefault("maxConnections", 100)
        println("最大连接数: $maxConnections")
        
        // getValue - 不存在时抛出异常
        try {
            val host = config.getValue("host")
            println("主机: $host")
            
            val database = config.getValue("database")  // 会抛出异常
            println("数据库: $database")
        } catch (e: NoSuchElementException) {
            println("错误: ${e.message}")
        }
        
        // 使用 let 安全处理 nullable
        val optionalValue = config["optional"]
        optionalValue?.let {
            println("可选值: $it")
        } ?: println("可选值不存在")
        
        println()
    }

    /**
     * 10. Map 与其他集合的转换
     */
    fun demonstrateMapConversions() {
        println("=== 10. Map 与其他集合的转换 ===")
        
        val map = mapOf("a" to 1, "b" to 2, "c" to 3)
        println("原始 Map: $map")
        
        // Map 转 List of Pairs
        val pairs = map.toList()
        println("转为 List of Pairs: $pairs")
        
        // Map keys 转 List
        val keysList = map.keys.toList()
        println("Keys 转 List: $keysList")
        
        // Map values 转 List
        val valuesList = map.values.toList()
        println("Values 转 List: $valuesList")
        
        // List of Pairs 转 Map
        val pairList = listOf("x" to 10, "y" to 20, "z" to 30)
        val fromPairs = pairList.toMap()
        println("\nList of Pairs 转 Map: $fromPairs")
        
        // 两个 List 组合成 Map
        val keys = listOf("name", "age", "city")
        val values = listOf("Alice", 25, "Beijing")
        val zippedMap = keys.zip(values).toMap()
        println("两个 List 组合成 Map: $zippedMap")
        
        println()
    }

    /**
     * 11. 实际应用场景
     */
    fun practicalExercise() {
        println("=== 11. 实际应用场景 ===")
        
        // 场景1: 词频统计
        println("场景1: 词频统计")
        val text = "kotlin is great and kotlin is fun and kotlin is powerful"
        val words = text.split(" ")
        
        val wordFrequency = words.groupingBy { it }.eachCount()
        println("原文: $text")
        println("词频统计: $wordFrequency")
        
        // 按频率排序
        val sortedByFrequency = wordFrequency.entries
            .sortedByDescending { it.value }
            .associate { it.toPair() }
        println("按频率排序: $sortedByFrequency")
        
        println()
        
        // 场景2: 缓存模拟
        println("场景2: 缓存模拟")
        val cache = mutableMapOf<String, String>()
        
        fun getCachedData(key: String): String {
            return cache.getOrPut(key) {
                println("  从数据源加载: $key")
                "Data for $key"  // 模拟从数据库或 API 获取
            }
        }
        
        val data1 = getCachedData("user1")
        println("第一次获取: $data1")
        
        val data2 = getCachedData("user1")  // 应该从缓存获取
        println("第二次获取: $data2")
        
        val data3 = getCachedData("user2")
        println("获取新用户: $data3")
        
        println("缓存内容: $cache")
        
        println()
        
        // 场景3: 配置管理
        println("场景3: 配置管理")
        
        data class AppConfig(
            val host: String,
            val port: Int,
            val debug: Boolean,
            val maxConnections: Int
        )
        
        val configMap = mapOf(
            "host" to "localhost",
            "port" to "8080",
            "debug" to "true",
            "maxConnections" to "100"
        )
        
        // 从 Map 构建配置对象
        val config = AppConfig(
            host = configMap.getOrElse("host") { "0.0.0.0" },
            port = configMap["port"]?.toIntOrNull() ?: 3000,
            debug = configMap["debug"]?.toBoolean() ?: false,
            maxConnections = configMap["maxConnections"]?.toIntOrNull() ?: 50
        )
        
        println("配置对象: $config")
        
        println()
        
        // 场景4: 数据索引和快速查找
        println("场景4: 数据索引和快速查找")
        
        data class Product(val id: Int, val name: String, val price: Double)
        
        val products = listOf(
            Product(1, "Laptop", 999.99),
            Product(2, "Phone", 699.99),
            Product(3, "Tablet", 499.99),
            Product(4, "Watch", 299.99)
        )
        
        // 创建 ID 到产品的索引
        val productById = products.associateBy { it.id }
        println("产品索引: $productById")
        
        // 快速查找
        val productId = 2
        val product = productById[productId]
        println("\n查找产品 ID $productId: $product")
        
        // 批量查找
        val idsToFind = listOf(1, 3, 5)
        val foundProducts = idsToFind.mapNotNull { productById[it] }
        println("批量查找 $idsToFind: $foundProducts")
        
        println()
    }
}

fun main() {
    val mapLesson = MapLesson()
    
    // 运行所有示例
    mapLesson.demonstrateMapCreation()
    mapLesson.demonstrateMapAccess()
    mapLesson.demonstrateMutableMapOperations()
    mapLesson.demonstrateMapOperations()
    mapLesson.demonstrateFilterAndTransform()
    mapLesson.demonstrateMapIteration()
    mapLesson.demonstrateMapMerging()
    mapLesson.demonstrateGroupingAndAggregation()
    mapLesson.demonstrateSafeOperations()
    mapLesson.demonstrateMapConversions()
    mapLesson.practicalExercise()
}