package com.zhucj.kotlin.array

/**
 * Kotlin Set 详解
 * 
 * ============================================================================
 * 【核心原理】
 * ============================================================================
 * 
 * 1. Set 的本质
 *    - Kotlin 的 Set 是一个接口（interface），不是具体的实现类
 *    - 底层实际使用的是 Java 的 HashSet、LinkedHashSet 或 TreeSet
 *    - Kotlin 通过扩展函数为 Set 提供了丰富的操作 API
 *    - Set 存储的是无序且不重复的元素集合
 * 
 * 2. 不可变 vs 可变的设计哲学
 *    - Set<T>（只读）：编译期保证不能修改，提供线程安全性
 *    - MutableSet<T>（可变）：继承自 Set，额外提供增删改方法
 *    - 这是 Kotlin "默认不可变" 设计理念的体现，减少副作用
 * 
 * 3. 与 Java 的关键区别
 * 
 *    ┌─────────────────┬──────────────────────┬──────────────────────┐
 *    │     特性         │      Java            │      Kotlin          │
 *    ├─────────────────┼──────────────────────┼──────────────────────┤
 *    │ 类型声明         │ Set<String> set      │ val set: Set<String> │
 *    │ 创建方式         │ new HashSet<>()      │ setOf() / mutableSetOf()│
 *    │ 不可变性         │ Collections.         │ 语言级别支持         │
 *    │                  │ unmodifiableSet()    │                      │
 *    │ 空安全           │ 可能 null            │ 默认非空，需显式声明  │
 *    │ 添加元素         │ set.add()            │ + 运算符 / plus      │
 *    │ 删除元素         │ set.remove()         │ - 运算符 / minus     │
 *    │ 大小             │ set.size()           │ set.size (属性)      │
 *    │ 遍历             │ for-each / iterator  │ forEach / for 循环   │
 *    │ 函数式操作       │ Stream API (Java 8+) │ 内置丰富的高阶函数    │
 *    │ 类型推断         │ 需要显式声明泛型      │ 自动推断类型          │
 *    │ 集合运算         │ retainAll/removeAll  │ intersect/union等    │
 *    └─────────────────┴──────────────────────┴──────────────────────┘
 * 
 * 4. Set 的实现类型及性能特点
 * 
 *    HashSet:
 *    - 基于哈希表实现
 *    - 查找/插入/删除: O(1) 平均
 *    - 不保证顺序
 *    - 允许一个 null 元素
 *    
 *    LinkedHashSet:
 *    - 基于哈希表 + 链表实现
 *    - 保持插入顺序
 *    - 性能略低于 HashSet
 *    - 查找/插入/删除: O(1) 平均
 *    
 *    TreeSet (sortedSet):
 *    - 基于红黑树实现
 *    - 保持元素的自然顺序或自定义顺序
 *    - 查找/插入/删除: O(log n)
 *    - 不允许 null 元素
 * 
 * 5. 内存模型
 *    - Set 本身是引用类型，存储在堆上
 *    - 每个元素也是引用（对象）或值（基本类型装箱）
 *    - HashSet 内部使用 HashMap 的 key 部分（value 为空对象）
 *    - setOf() 创建的小 Set 可能使用优化实现
 *    - mutableSetOf() 直接创建 LinkedHashSet 实例
 * 
 * 6. 协变特性（Covariance）
 *    - Set<out T> 是协变的，可以赋值给父类型
 *    - 例如：Set<String> 可以赋值给 Set<Any>
 *    - 这是因为只读 Set 不会修改元素，保证类型安全
 *    - MutableSet<T> 是不变的，不能协变（因为可修改）
 * 
 * 7. 与 Java 互操作
 *    - Kotlin Set 可以直接传给接受 java.util.Set 的方法
 *    - Java Set 可以直接在 Kotlin 中当作 Set 使用
 *    - 注意：Java 的可变 Set 在 Kotlin 中需要显式转换为 MutableSet
 *    - Kotlin 的集合运算符（+、-）返回新的 Set，不修改原集合
 * 
 * 8. hashCode 和 equals
 *    - Set 的相等性基于元素的相等性，而非顺序或引用
 *    - 两个 Set 如果包含相同的元素，则 equals 返回 true
 *    - 元素类型应该正确实现 hashCode 和 equals
 *    - 可变对象作为 Set 元素时要小心（修改后可能导致找不到元素）
 * 
 * 9. Set 的核心特性
 *    - 唯一性：不允许重复元素（基于 equals 和 hashCode 判断）
 *    - 无序性：HashSet 不保证顺序（LinkedHashSet 除外）
 *    - 高效查找：O(1) 时间复杂度检查元素是否存在
 *    - 集合运算：并集、交集、差集等数学运算
 * 
 * ============================================================================
 * 
 * Set 是 Kotlin 中用于存储不重复元素集合的重要数据结构。
 * Kotlin 提供了两种 Set 类型：
 * 1. Set<T> - 不可变集合（只读）
 * 2. MutableSet<T> - 可变集合（可读写）
 */
class SetLesson {

    /**
     * 1. 创建 Set
     */
    fun demonstrateSetCreation() {
        println("=== 1. 创建 Set ===")
        
        // 不可变 Set（只读）
        val readOnlySet = setOf("Apple", "Banana", "Cherry", "Apple")
        println("只读 Set（自动去重）: $readOnlySet")
        
        // 可变 Set（可修改）
        val mutableSet = mutableSetOf("Apple", "Banana", "Cherry")
        println("可变 Set: $mutableSet")
        
        // 空 Set
        val emptySet = emptySet<String>()
        println("空 Set: $emptySet")
        
        // HashSet（不保证顺序）
        val hashSet = hashSetOf("third", "first", "second")
        println("HashSet: $hashSet")
        
        // LinkedHashSet（保持插入顺序）
        val linkedSet = linkedSetOf("third", "first", "second")
        println("LinkedHashSet（保持顺序）: $linkedSet")
        
        // TreeSet（排序）
        val treeSet = sortedSetOf("banana", "apple", "cherry")
        println("TreeSet（排序）: $treeSet")
        
        // 从 List 转换（去重）
        val listWithDuplicates = listOf(1, 2, 2, 3, 3, 3, 4, 4, 4, 4)
        val setFromList = listWithDuplicates.toSet()
        println("\n原始 List: $listWithDuplicates")
        println("转为 Set（去重）: $setFromList")
        
        println()
    }

    /**
     * 2. 访问和检查 Set 元素
     */
    fun demonstrateSetAccess() {
        println("=== 2. 访问和检查 Set 元素 ===")
        
        val fruits = setOf("Apple", "Banana", "Cherry", "Date")
        println("Set: $fruits")
        
        // 检查元素是否存在
        println("包含 'Apple': ${fruits.contains("Apple")}")
        println("包含 'Grape': ${fruits.contains("Grape")}")
        
        // 使用 in 运算符（更简洁）
        println("'Banana' in set: ${"Banana" in fruits}")
        println("'Orange' in set: ${"Orange" in fruits}")
        
        // 大小
        println("Set 大小: ${fruits.size}")
        
        // 判断是否为空
        println("是否为空: ${fruits.isEmpty()}")
        println("是否非空: ${fruits.isNotEmpty()}")
        
        // 获取第一个和最后一个元素
        println("第一个元素: ${fruits.first()}")
        println("最后一个元素: ${fruits.last()}")
        
        // 条件查找
        println("以 'A' 开头的: ${fruits.firstOrNull { it.startsWith("A") }}")
        println("以 'Z' 开头的: ${fruits.firstOrNull { it.startsWith("Z") }}")
        
        println()
    }

    /**
     * 3. 修改可变 Set
     */
    fun demonstrateMutableSetOperations() {
        println("=== 3. 修改可变 Set ===")
        
        val mutableSet = mutableSetOf("Apple", "Banana", "Cherry")
        println("原始 Set: $mutableSet")
        
        // 添加单个元素
        mutableSet.add("Date")
        println("添加 'Date' 后: $mutableSet")
        
        // 添加已存在的元素（不会重复）
        mutableSet.add("Apple")
        println("再次添加 'Apple' 后: $mutableSet")
        
        // 批量添加
        mutableSet.addAll(setOf("Elderberry", "Fig"))
        println("批量添加后: $mutableSet")
        
        // 删除单个元素
        mutableSet.remove("Banana")
        println("删除 'Banana' 后: $mutableSet")
        
        // 删除不存在的元素（返回 false）
        val removed = mutableSet.remove("Grape")
        println("删除 'Grape' 结果: $removed")
        println("当前 Set: $mutableSet")
        
        // 批量删除
        mutableSet.removeAll(setOf("Cherry", "Date"))
        println("批量删除后: $mutableSet")
        
        // 保留指定元素（删除其他）
        mutableSet.retainAll(setOf("Apple", "Elderberry"))
        println("保留指定元素后: $mutableSet")
        
        // 清空 Set
        mutableSet.clear()
        println("清空后: $mutableSet")
        
        println()
    }

    /**
     * 4. 集合运算（并集、交集、差集）
     */
    fun demonstrateSetOperations() {
        println("=== 4. 集合运算 ===")
        
        val setA = setOf(1, 2, 3, 4, 5)
        val setB = setOf(4, 5, 6, 7, 8)
        
        println("Set A: $setA")
        println("Set B: $setB")
        
        // 并集（Union）- 所有元素
        val union = setA.union(setB)
        println("\n并集 (A ∪ B): $union")
        
        // 也可以使用 + 运算符
        val unionOperator = setA + setB
        println("使用 + 运算符: $unionOperator")
        
        // 交集（Intersection）- 共同元素
        val intersection = setA.intersect(setB)
        println("交集 (A ∩ B): $intersection")
        
        // 差集（Difference）- 在 A 中但不在 B 中
        val difference = setA.subtract(setB)
        println("差集 (A - B): $difference")
        
        // 也可以使用 - 运算符
        val differenceOperator = setA - setB
        println("使用 - 运算符: $differenceOperator")
        
        // 对称差集 - 只在其中一个集合中的元素
        val symmetricDifference = (setA - setB) + (setB - setA)
        println("对称差集: $symmetricDifference")

        println()
    }

    /**
     * 5. 过滤和转换
     */
    fun demonstrateFilterAndTransform() {
        println("=== 5. 过滤和转换 ===")
        
        val numbers = setOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        println("原始 Set: $numbers")
        
        // 过滤
        val evenNumbers = numbers.filter { it % 2 == 0 }
        println("偶数: $evenNumbers")
        
        val greaterThanFive = numbers.filter { it > 5 }
        println("大于5的数: $greaterThanFive")
        
        // 过滤返回的是 List，转回 Set
        val evenSet = numbers.filter { it % 2 == 0 }.toSet()
        println("偶数 Set: $evenSet")
        
        // 映射（转换）
        val doubled = numbers.map { it * 2 }
        println("翻倍（List）: $doubled")
        
        val doubledSet = numbers.map { it * 2 }.toSet()
        println("翻倍（Set）: $doubledSet")
        
        // 过滤并映射
        val evenDoubled = numbers.filter { it % 2 == 0 }.map { it * 2 }.toSet()
        println("偶数翻倍: $evenDoubled")
        
        // flatMap（扁平化映射）
        val stringSet = setOf("hello", "world")
        val chars = stringSet.flatMap { it.toCharArray().toList() }.toSet()
        println("\n字符串 Set: $stringSet")
        println("所有字符（去重）: $chars")
        
        println()
    }

    /**
     * 6. 遍历 Set
     */
    fun demonstrateSetIteration() {
        println("=== 6. 遍历 Set ===")
        
        val fruits = setOf("Apple", "Banana", "Cherry")
        
        // forEach
        println("forEach 遍历:")
        fruits.forEach { println("  - $it") }
        
        // forEachIndexed（Set 没有索引，但可以手动计数）
        println("\n带序号遍历:")
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
        
        // 迭代器
        println("\n使用迭代器:")
        val iterator = fruits.iterator()
        while (iterator.hasNext()) {
            println("  - ${iterator.next()}")
        }
        
        println()
    }

    /**
     * 7. 聚合操作
     */
    fun demonstrateAggregationOperations() {
        println("=== 7. 聚合操作 ===")
        
        val numbers = setOf(5, 2, 8, 1, 9, 3, 7, 4, 6)
        println("Set: $numbers")
        
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
        
        // any - 是否有满足条件的元素
        println("是否有偶数: ${numbers.any { it % 2 == 0 }}")
        
        // all - 是否所有元素都满足条件
        println("是否都大于0: ${numbers.all { it > 0 }}")
        
        // none - 是否没有满足条件的元素
        println("是否没有负数: ${numbers.none { it < 0 }}")
        
        println()
    }

    /**
     * 8. Set 与其他集合的转换
     */
    fun demonstrateSetConversions() {
        println("=== 8. Set 与其他集合的转换 ===")
        
        val set = setOf("Apple", "Banana", "Cherry")
        println("原始 Set: $set")
        
        // Set 转 List
        val list = set.toList()
        println("转为 List: $list")
        
        // Set 转 Array
        val array = set.toTypedArray()
        println("转为 Array: ${array.contentToString()}")
        
        // List 转 Set（去重）
        val listWithDuplicates = listOf(1, 2, 2, 3, 3, 3)
        val setFromList = listWithDuplicates.toSet()
        println("\nList（有重复）: $listWithDuplicates")
        println("转为 Set（去重）: $setFromList")
        
        // Array 转 Set
        val stringArray = arrayOf("a", "b", "c")
        val setFromArray = stringArray.toSet()
        println("\nArray: ${stringArray.contentToString()}")
        println("转为 Set: $setFromArray")
        
        println()
    }

    /**
     * 9. 性能比较示例
     */
    fun demonstratePerformanceComparison() {
        println("=== 9. 性能比较示例 ===")
        
        // 创建大型集合
        val largeList = (1..10000).toList()
        val largeSet = largeList.toSet()
        
        println("List 大小: ${largeList.size}")
        println("Set 大小: ${largeSet.size}")
        
        // 查找性能比较
        val searchElement = 9999
        
        // List 查找（线性搜索）
        val listStartTime = System.nanoTime()
        val listContains = largeList.contains(searchElement)
        val listEndTime = System.nanoTime()
        val listTime = listEndTime - listStartTime
        
        // Set 查找（哈希查找）
        val setStartTime = System.nanoTime()
        val setContains = largeSet.contains(searchElement)
        val setEndTime = System.nanoTime()
        val setTime = setEndTime - setStartTime
        
        println("\n查找元素 $searchElement:")
        println("List contains: $listContains (耗时: ${listTime / 1000} μs)")
        println("Set contains: $setContains (耗时: ${setTime / 1000} μs)")
        println("Set 比 List 快: ${listTime.toDouble() / setTime} 倍")
        
        println()
    }

    /**
     * 10. 实际应用场景
     */
    fun practicalExercise() {
        println("=== 10. 实际应用场景 ===")
        
        // 场景1: 去重
        println("场景1: 数据去重")
        val duplicateEmails = listOf(
            "alice@example.com",
            "bob@example.com",
            "alice@example.com",
            "charlie@example.com",
            "bob@example.com"
        )
        println("原始列表: $duplicateEmails")
        
        val uniqueEmails = duplicateEmails.toSet()
        println("去重后: $uniqueEmails")
        
        // 如果需要保持顺序并使用 List
        val uniqueEmailsList = duplicateEmails.distinct()
        println("去重后（List）: $uniqueEmailsList")
        
        println()
        
        // 场景2: 权限管理
        println("场景2: 权限管理")
        
        val userPermissions = setOf("read", "write", "execute")
        val requiredPermissions = setOf("read", "write")
        val adminPermissions = setOf("read", "write", "execute", "delete", "admin")
        
        println("用户权限: $userPermissions")
        println("所需权限: $requiredPermissions")
        
        // 检查是否有额外权限
        val extraPermissions = userPermissions - requiredPermissions
        println("额外权限: $extraPermissions")
        
        // 合并权限
        val combinedPermissions = userPermissions.union(setOf("delete"))
        println("合并后权限: $combinedPermissions")
        
        println()
        
        // 场景3: 好友关系（共同好友）
        println("场景3: 社交网络 - 共同好友")
        
        val aliceFriends = setOf("Bob", "Charlie", "David", "Eve")
        val bobFriends = setOf("Alice", "Charlie", "Frank", "Grace")
        
        println("Alice 的好友: $aliceFriends")
        println("Bob 的好友: $bobFriends")
        
        // 共同好友
        val mutualFriends = aliceFriends.intersect(bobFriends)
        println("共同好友: $mutualFriends")
        
        // 推荐好友（在 Bob 的好友中但不在 Alice 的好友中）
        val recommendedFriends = bobFriends - aliceFriends - setOf("Alice")
        println("推荐给 Alice 的好友: $recommendedFriends")
        
        println()
        
        // 场景4: 标签系统
        println("场景4: 文章标签系统")
        
        data class Article(val title: String, val tags: Set<String>)
        
        val articles = listOf(
            Article("Kotlin 入门", setOf("kotlin", "programming", "beginner")),
            Article("Java 高级", setOf("java", "programming", "advanced")),
            Article("Kotlin 协程", setOf("kotlin", "coroutines", "advanced")),
            Article("Spring Boot", setOf("java", "spring", "web"))
        )
        
        // 获取所有唯一标签
        val allTags = articles.flatMap { it.tags }.toSet()
        println("所有标签: $allTags")
        
        // 查找包含特定标签的文章
        val kotlinTag = "kotlin"
        val kotlinArticles = articles.filter { kotlinTag in it.tags }
        println("\n包含 '$kotlinTag' 标签的文章:")
        kotlinArticles.forEach { println("  - ${it.title}") }
        
        // 场景5: 数据验证
        println("场景5: 数据验证 - 检查唯一性")
        
        fun <T> hasDuplicates(collection: Collection<T>): Boolean {
            return collection.size != collection.toSet().size
        }
        
        val testData1 = listOf(1, 2, 3, 4, 5)
        val testData2 = listOf(1, 2, 2, 3, 4)
        
        println("$testData1 有重复: ${hasDuplicates(testData1)}")
        println("$testData2 有重复: ${hasDuplicates(testData2)}")
        
        println()
    }
}

fun main() {
    val setLesson = SetLesson()
    
    // 运行所有示例
    setLesson.demonstrateSetCreation()
    setLesson.demonstrateSetAccess()
    setLesson.demonstrateMutableSetOperations()
    setLesson.demonstrateSetOperations()
    setLesson.demonstrateFilterAndTransform()
    setLesson.demonstrateSetIteration()
    setLesson.demonstrateAggregationOperations()
    setLesson.demonstrateSetConversions()
    setLesson.demonstratePerformanceComparison()
    setLesson.practicalExercise()
}