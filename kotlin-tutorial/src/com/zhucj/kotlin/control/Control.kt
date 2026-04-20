package com.zhucj.kotlin.control

/**
 * Kotlin 条件控制详解教程
 * 
 * 本文件系统讲解 Kotlin 中的条件控制语句：
 * 1. if 表达式（比 Java 更强大）
 * 2. when 表达式（替代 switch，功能更强）
 * 3. for 循环（增强的 for 循环）
 * 4. while 和 do-while 循环
 * 5. 跳转表达式（break、continue、return）
 * 6. 范围（Range）和区间
 * 7. 实际开发场景应用
 */
class Control {
    
    // ========================================
    // 1. if 表达式 ⭐
    // ========================================
    
    /**
     * if 表达式演示
     * 
     * Kotlin 的 if 是表达式（expression），不是语句（statement）
     * 这意味着 if 可以返回值，可以赋值给变量
     * 
     * 与 Java 的区别：
     * - Java: if 是语句，不能直接返回值
     * - Kotlin: if 是表达式，可以返回值
     */
    fun demonstrateIfExpression() {
        println("===== if 表达式 =====")
        
        // ========== 基本用法 ==========
        println("--- 基本用法 ---")
        
        val a = 10
        val b = 20
        
        // 传统用法（与 Java 类似）
        if (a > b) {
            println("a 大于 b")
        } else {
            println("a 小于或等于 b")
        }
        
        // ========== if 作为表达式（Kotlin 特色）==========
        println("\n--- if 作为表达式（可以返回值）---")
        
        // Java 需要三元运算符: int max = (a > b) ? a : b;
        // Kotlin 直接用 if 表达式
        val max = if (a > b) a else b
        println("最大值: $max")  // 20
        
        // 多行 if 表达式
        val result = if (a > b) {
            println("a 更大")
            a  // 最后一行是返回值
        } else {
            println("b 更大或相等")
            b  // 最后一行是返回值
        }
        println("结果: $result")
        
        // ========== 多重条件 ==========
        println("\n--- 多重条件 ---")
        
        val score = 85
        
        val grade = if (score >= 90) {
            "优秀"
        } else if (score >= 80) {
            "良好"
        } else if (score >= 70) {
            "中等"
        } else if (score >= 60) {
            "及格"
        } else {
            "不及格"
        }
        
        println("分数: $score, 等级: $grade")
        
        // ========== 实际开发场景 ==========
        println("\n--- 实际开发场景 ---")
        
        // 场景1: 空值处理
        val name: String? = null
        val displayName = if (name.isNullOrEmpty()) "匿名用户" else name
        println("显示名称: $displayName")
        
        // 场景2: 状态判断
        val isActive = true
        val statusText = if (isActive) "活跃" else "禁用"
        println("状态: $statusText")
        
        // 场景3: 权限检查
        val userRole = "admin"
        val canDelete = if (userRole == "admin" || userRole == "moderator") {
            true
        } else {
            false
        }
        println("是否可以删除: $canDelete")
        
        // 场景4: 价格计算
        val originalPrice = 100.0
        val isVip = true
        val finalPrice = if (isVip) {
            originalPrice * 0.8  // VIP 8折
        } else {
            originalPrice
        }
        println("原价: $originalPrice, VIP价: $finalPrice")
        
        // ========== if  vs 三元运算符 ==========
        println("\n--- if vs 三元运算符 ---")
        println("""
        Java: int max = (a > b) ? a : b;
        Kotlin: val max = if (a > b) a else b
        
        优势：
        ✅ Kotlin 不需要专门的三元运算符
        ✅ if 表达式更清晰、更易读
        ✅ 支持多行逻辑
        ✅ 可以包含复杂的代码块
        """.trimIndent())
    }
    
    // ========================================
    // 2. when 表达式 ⭐⭐⭐ 最强大
    // ========================================
    
    /**
     * when 表达式演示
     * 
     * when 是 Kotlin 中最强大的条件控制语句
     * 替代 Java 的 switch，但功能强大得多
     * 
     * 特点：
     * - 可以是表达式（返回值）或语句
     * - 支持任意类型的匹配
     * - 支持范围、类型、条件等多种匹配方式
     * - 自动 break（不需要手动 break）
     * - 编译期检查是否覆盖所有情况（sealed class）
     */
    fun demonstrateWhenExpression() {
        println("\n===== when 表达式（Kotlin 最强特性之一）=====")
        
        // ========== 基本用法（类似 switch）==========
        println("--- 基本用法 ---")
        
        val day = 3
        val dayName = when (day) {
            1 -> "星期一"
            2 -> "星期二"
            3 -> "星期三"
            4 -> "星期四"
            5 -> "星期五"
            6 -> "星期六"
            7 -> "星期日"
            else -> "无效日期"  // 默认分支（类似 default）
        }
        println("星期: $dayName")
        
        // ========== 多个值合并 ==========
        println("\n--- 多个值合并 ---")
        
        val month = 6
        val season = when (month) {
            3, 4, 5 -> "春季"
            6, 7, 8 -> "夏季"
            9, 10, 11 -> "秋季"
            12, 1, 2 -> "冬季"
            else -> "无效月份"
        }
        println("月份: $month, 季节: $season")
        
        // ========== 范围匹配 ==========
        println("\n--- 范围匹配（非常实用）---")
        
        val age = 25
        val lifeStage = when (age) {
            in 0..12 -> "儿童"
            in 13..17 -> "青少年"
            in 18..35 -> "青年"
            in 36..60 -> "中年"
            else -> "老年"
        }
        println("年龄: $age, 阶段: $lifeStage")
        
        // ========== 类型检查 ==========
        println("\n--- 类型检查（Smart Cast）---")
        
        fun describe(obj: Any): String {
            return when (obj) {
                is String -> "字符串: '$obj' (长度: ${obj.length})"
                is Int -> "整数: $obj (${if (obj > 0) "正数" else "非正数"})"
                is Double -> "浮点数: $obj"
                is List<*> -> "列表: ${obj.size} 个元素"
                else -> "未知类型"
            }
        }
        
        println(describe("Hello"))
        println(describe(42))
        println(describe(3.14))
        println(describe(listOf(1, 2, 3)))
        println(describe(true))
        
        // ========== 无条件 when（类似 if-else if 链）==========
        println("\n--- 无条件 when（替代 if-else if）---")
        
        val temperature = 25
        val weatherAdvice = when {
            temperature < 0 -> "极冷，注意保暖"
            temperature < 10 -> "很冷，穿厚衣服"
            temperature < 20 -> "凉爽，适合户外活动"
            temperature < 30 -> "温暖，舒适"
            temperature < 35 -> "炎热，注意防暑"
            else -> "极热，避免外出"
        }
        println("温度: $temperature°C, 建议: $weatherAdvice")
        
        // ========== 复杂条件 ==========
        println("\n--- 复杂条件 ---")
        
        val x = 10
        val y = 20
        
        val comparison = when {
            x > y -> "x 大于 y"
            x < y -> "x 小于 y"
            x == y -> "x 等于 y"
            else -> "无法比较"
        }
        println(comparison)
        
        // ========== 函数调用作为条件 ==========
        println("\n--- 函数调用作为条件 ---")
        
        fun isValidEmail(email: String): Boolean {
            return email.contains("@") && email.contains(".")
        }
        
        val email = "user@example.com"
        val emailStatus = when {
            email.isEmpty() -> "邮箱不能为空"
            !isValidEmail(email) -> "邮箱格式不正确"
            else -> "邮箱有效"
        }
        println("邮箱: $email, 状态: $emailStatus")
        
        // ========== when 作为语句（不返回值）==========
        println("\n--- when 作为语句 ---")
        
        val role = "admin"
        when (role) {
            "admin" -> {
                println("管理员权限")
                println("可以执行所有操作")
            }
            "editor" -> {
                println("编辑权限")
                println("可以编辑内容")
            }
            "viewer" -> {
                println("查看权限")
                println("只能查看内容")
            }
            else -> println("未知角色")
        }
        
        // ========== 实际开发场景 ==========
        println("\n--- 实际开发场景 ---")
        
        // 场景1: HTTP 状态码处理
        fun handleHttpStatus(code: Int): String {
            return when (code) {
                200 -> "请求成功"
                201 -> "创建成功"
                204 -> "无内容"
                400 -> "请求错误"
                401 -> "未授权"
                403 -> "禁止访问"
                404 -> "资源未找到"
                500 -> "服务器错误"
                502 -> "网关错误"
                503 -> "服务不可用"
                in 200..299 -> "成功类响应"
                in 400..499 -> "客户端错误"
                in 500..599 -> "服务器错误"
                else -> "未知状态码"
            }
        }
        
        println("HTTP 200: ${handleHttpStatus(200)}")
        println("HTTP 404: ${handleHttpStatus(404)}")
        println("HTTP 500: ${handleHttpStatus(500)}")
        
        // 场景2: 订单状态管理
        fun getOrderStatusText(status: OrderStatus): String {
            return when (status) {
                OrderStatus.PENDING -> "待支付"
                OrderStatus.PAID -> "已支付"
                OrderStatus.SHIPPED -> "已发货"
                OrderStatus.DELIVERED -> "已送达"
                OrderStatus.CANCELLED -> "已取消"
            }
        }
        
        println("\n订单状态: ${getOrderStatusText(OrderStatus.PAID)}")
        
        // 场景3: API 响应处理

        
        // 场景4: 菜单选择
        fun processMenuChoice(choice: String) {
            when (choice.lowercase()) {
                "1", "create" -> println("创建新项")
                "2", "read" -> println("读取数据")
                "3", "update" -> println("更新数据")
                "4", "delete" -> println("删除数据")
                "q", "quit", "exit" -> println("退出程序")
                else -> println("无效选择")
            }
        }
        
        println("\n菜单处理:")
        processMenuChoice("1")
        processMenuChoice("READ")
        processMenuChoice("quit")
        
        // ========== when vs switch 对比 ==========
        println("\n--- when vs switch 对比 ---")
        println("""
        Java switch 的限制：
        ❌ 只能用于特定类型（int、String、enum）
        ❌ 需要手动 break，容易遗漏
        ❌ 不支持范围匹配
        ❌ 不支持类型检查
        ❌ 不能直接返回值
        
        Kotlin when 的优势：
        ✅ 支持任意类型
        ✅ 自动 break，不会 fall-through
        ✅ 支持范围、类型、条件等多种匹配
        ✅ 可以是表达式，直接返回值
        ✅ 编译期检查密封类的所有分支
        ✅ 更简洁、更安全、更强大
        """.trimIndent())
    }
    
    // ========================================
    // 3. for 循环 ⭐
    // ========================================
    
    /**
     * for 循环演示
     * 
     * Kotlin 的 for 循环比 Java 更简洁、更强大
     * 主要用于遍历集合、数组、范围等
     * 
     * 语法：for (item in collection) { ... }
     */
    fun demonstrateForLoop() {
        println("\n===== for 循环 =====")
        
        // ========== 遍历范围 ==========
        println("--- 遍历范围 ---")
        
        // 基本范围
        print("1 到 5: ")
        for (i in 1..5) {
            print("$i ")
        }
        println()
        
        // 不包含结束值
        print("1 直到 5: ")
        for (i in 1 until 5) {
            print("$i ")
        }
        println()
        
        // 倒序
        print("5 到 1: ")
        for (i in 5 downTo 1) {
            print("$i ")
        }
        println()
        
        // 带步长
        print("1 到 10，步长 2: ")
        for (i in 1..10 step 2) {
            print("$i ")
        }
        println()
        
        // ========== 遍历数组 ==========
        println("\n--- 遍历数组 ---")

        val numbers = arrayOf(10, 20, 30, 40, 50)
        
        // 基本遍历
        print("基本遍历: ")
        for (num in numbers) {
            print("$num ")
        }
        println()

        // 带索引遍历
        println("带索引遍历:")
        for ((index, value) in numbers.withIndex()) {
            println("  [$index]: $value")
        }
        
        // 使用 indices
        println("使用 indices:")
        for (i in numbers.indices) {
            println("  numbers[$i] = ${numbers[i]}")
        }
        
        // ========== 遍历集合 ==========
        println("\n--- 遍历集合 ---")
        
        val fruits = listOf("apple", "banana", "orange", "grape")
        
        // 基本遍历
        println("水果列表:")
        for (fruit in fruits) {
            println("  - $fruit")
        }
        
        // 带索引
        println("\n带索引:")
        for ((index, fruit) in fruits.withIndex()) {
            println("  $index. $fruit")
        }
        
        // ========== 遍历 Map ==========
        println("\n--- 遍历 Map ---")
        
        val userMap = mapOf(
            "name" to "张三",
            "age" to "25",
            "city" to "北京"
        )
        
        // 遍历键值对
        println("用户信息:")
        for ((key, value) in userMap) {
            println("  $key: $value")
        }
        
        // 只遍历键
        println("\n所有键:")
        for (key in userMap.keys) {
            print("$key ")
        }
        println()
        
        // 只遍历值
        println("\n所有值:")
        for (value in userMap.values) {
            print("$value ")
        }
        println()
        
        // ========== forEach（函数式风格）==========
        println("\n--- forEach（函数式风格）---")
        
        val names = listOf("Alice", "Bob", "Charlie")
        
        // forEach 是集合的高阶函数
        println("使用 forEach:")
        names.forEach { name ->
            println("  Hello, $name!")
        }
        
        // 简写形式（单个参数可以用 it）
        println("\n简写形式:")
        names.forEach { println("  Hi, $it!") }
        
        // 带索引的 forEach
        println("\n带索引:")
        names.forEachIndexed { index, name ->
            println("  $index. $name")
        }
        
        // ========== 嵌套循环 ==========
        println("\n--- 嵌套循环 ---")
        
        // 打印乘法表（部分）
        println("乘法表:")
        for (i in 1..3) {
            for (j in 1..3) {
                print("${i * j}\t")
            }
            println()
        }
        
        // ========== 实际开发场景 ==========
        println("\n--- 实际开发场景 ---")
        
        // 场景1: 数据处理
        val scores = listOf(85, 92, 78, 95, 88)
        var sum = 0
        
        for (score in scores) {
            sum += score
        }
        
        val average = sum.toDouble() / scores.size
        println("总分: $sum, 平均分: $average")
        
        // 场景2: 查找元素
        val target = 92
        var found = false
        
        for (score in scores) {
            if (score == target) {
                found = true
                break
            }
        }
        println("找到 $target: $found")
        
        // 场景3: 构建字符串
        val words = listOf("Hello", "World", "Kotlin")
        val stringBuilder = StringBuilder()
        
        for ((index, word) in words.withIndex()) {
            if (index > 0) stringBuilder.append(", ")
            stringBuilder.append(word)
        }
        
        println("\n拼接结果: $stringBuilder")
        
        // 场景4: 过滤和转换
        val numbers2 = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val evenSquares = mutableListOf<Int>()
        
        for (num in numbers2) {
            if (num % 2 == 0) {
                evenSquares.add(num * num)
            }
        }
        
        println("偶数的平方: $evenSquares")
        
        // ========== for vs while 选择 ==========
        println("\n--- for vs while 选择 ---")
        println("""
        使用 for 的场景：
        ✅ 遍历集合、数组、范围
        ✅ 已知循环次数
        ✅ 需要索引
        ✅ 代码更简洁
        
        使用 while 的场景：
        ✅ 未知循环次数
        ✅ 基于条件的循环
        ✅ 需要灵活控制
        """.trimIndent())
    }
    
    // ========================================
    // 4. while 和 do-while 循环
    // ========================================
    
    /**
     * while 和 do-while 循环演示
     * 
     * while: 先判断条件，再执行循环体
     * do-while: 先执行循环体，再判断条件（至少执行一次）
     */
    fun demonstrateWhileLoops() {
        println("\n===== while 和 do-while 循环 =====")
        
        // ========== while 循环 ==========
        println("--- while 循环 ---")
        
        // 基本用法
        var count = 1
        while (count <= 5) {
            print("$count ")
            count++
        }
        println()
        
        // 实际场景：用户输入验证
        fun readUntilValid(): String {
            var input: String
            var attempts = 0
            
            // 模拟用户输入
            val inputs = listOf("", "  ", "abc", "valid")
            
            do {
                input = if (attempts < inputs.size) inputs[attempts] else "valid"
                attempts++
                println("  尝试 $attempts: '$input'")
            } while (input.isBlank() && attempts < 4)
            
            return input
        }
        
        println("\n输入验证:")
        val validInput = readUntilValid()
        println("有效输入: '$validInput'")
        
        // ========== do-while 循环 ==========
        println("\n--- do-while 循环 ---")
        
        // 至少执行一次
        var number = 10
        do {
            println("数字: $number")
            number -= 2
        } while (number > 0)
        
        // 实际场景：菜单系统
        fun showMenu() {
            var choice: String
            var exit = false
            var attemptCount = 0
            
            val choices = listOf("1", "2", "q")
            
            do {
                choice = if (attemptCount < choices.size) choices[attemptCount] else "q"
                attemptCount++
                
                println("\n--- 菜单 ---")
                println("1. 选项一")
                println("2. 选项二")
                println("q. 退出")
                println("选择: $choice")
                
                when (choice) {
                    "1" -> println("执行选项一")
                    "2" -> println("执行选项二")
                    "q" -> {
                        println("退出程序")
                        exit = true
                    }
                    else -> println("无效选择，请重试")
                }
            } while (!exit && attemptCount < 4)
        }
        
        println("\n菜单系统:")
        showMenu()
        
        // ========== while 的实际应用场景 ==========
        println("\n--- 实际应用场景 ---")
        
        // 场景1: 倒计时
        print("倒计时: ")
        var seconds = 5
        while (seconds > 0) {
            print("$seconds ")
            seconds--
        }
        println("时间到！")
        
        // 场景2: 累加直到满足条件
        var sum = 0
        var i = 1
        while (sum < 50) {
            sum += i
            i++
        }
        println("累加到 $sum，共加了 ${i - 1} 个数")
        
        // 场景3: 二分查找
        fun binarySearch(arr: IntArray, target: Int): Int {
            var left = 0
            var right = arr.size - 1
            
            while (left <= right) {
                val mid = left + (right - left) / 2
                
                when {
                    arr[mid] == target -> return mid
                    arr[mid] < target -> left = mid + 1
                    else -> right = mid - 1
                }
            }
            
            return -1
        }
        
        val sortedArray = intArrayOf(1, 3, 5, 7, 9, 11, 13, 15)
        val target = 7
        val index = binarySearch(sortedArray, target)
        println("\n二分查找: $target 在索引 $index")
        
        // 场景4: 读取数据直到结束
        fun simulateReadingData() {
            val dataList = listOf("data1", "data2", "data3", null)
            var index = 0
            
            println("\n读取数据:")
            while (index < dataList.size) {
                val data = dataList[index]
                if (data == null) {
                    println("数据结束")
                    break
                }
                println("  读取: $data")
                index++
            }
        }
        
        simulateReadingData()
        
        // ========== while vs for 对比 ==========
        println("\n--- while vs for 对比 ---")
        println("""
        while 循环适用场景：
        ✅ 循环次数未知
        ✅ 基于条件的循环
        ✅ 需要等待某个事件
        ✅ 游戏循环、服务器监听等
        
        for 循环适用场景：
        ✅ 遍历集合、数组
        ✅ 已知循环次数
        ✅ 范围迭代
        ✅ 代码更简洁
        
        选择原则：
        - 能使用 for 时优先用 for（更简洁）
        - 需要灵活控制时用 while
        """.trimIndent())
    }
    
    // ========================================
    // 5. 跳转表达式
    // ========================================
    
    /**
     * 跳转表达式演示
     * 
     * break: 跳出循环
     * continue: 跳过本次循环，继续下一次
     * return: 从函数返回
     * 
     * Kotlin 还支持标签（label），可以精确控制跳转到哪里
     */
    fun demonstrateJumpExpressions() {
        println("\n===== 跳转表达式 =====")
        
        // ========== break ==========
        println("--- break（跳出循环）---")
        
        // 找到第一个偶数就停止
        print("查找第一个偶数: ")
        for (i in 1..10) {
            if (i % 2 == 0) {
                println(i)
                break  // 跳出循环
            }
            print("$i ")
        }
        
        // ========== continue ==========
        println("\n--- continue（跳过本次循环）---")
        
        // 跳过奇数，只打印偶数
        print("偶数: ")
        for (i in 1..10) {
            if (i % 2 != 0) {
                continue  // 跳过本次循环
            }
            print("$i ")
        }
        println()
        
        // ========== 标签（Label）==========
        println("\n--- 标签（精确控制跳转）---")
        
        // 外层循环标签
        outer@ for (i in 1..3) {
            for (j in 1..3) {
                println("i=$i, j=$j")
                if (i == 2 && j == 2) {
                    break@outer  // 跳出外层循环
                }
            }
        }
        
        // continue 标签
        println("\ncontinue 标签:")
        outer2@ for (i in 1..3) {
            for (j in 1..3) {
                if (j == 2) {
                    continue@outer2  // 继续外层循环的下一次
                }
                println("i=$i, j=$j")
            }
        }
        
        // ========== return ==========
        println("\n--- return（从函数返回）---")
        
        fun findFirstPositive(numbers: List<Int>): Int? {
            for (num in numbers) {
                if (num > 0) {
                    return num  // 找到就立即返回
                }
            }
            return null  // 没找到返回 null
        }
        
        val numbers = listOf(-3, -1, 0, 5, 8)
        val firstPositive = findFirstPositive(numbers)
        println("第一个正数: $firstPositive")
        
        // ========== 实际开发场景 ==========
        println("\n--- 实际开发场景 ---")
        
        // 场景1: 数据验证
        fun validateUserData(data: Map<String, String>): Boolean {
            // 检查必需字段
            val requiredFields = listOf("name", "email", "age")
            
            for (field in requiredFields) {
                if (data[field].isNullOrBlank()) {
                    println("缺少必需字段: $field")
                    return false  // 验证失败，立即返回
                }
            }
            
            // 检查邮箱格式
            val email = data["email"]
            if (email != null && !email.contains("@")) {
                println("邮箱格式不正确")
                return false
            }
            
            return true
        }
        
        val validData = mapOf("name" to "张三", "email" to "zhangsan@example.com", "age" to "25")
        val invalidData = mapOf("name" to "李四", "email" to "invalid")
        
        println("有效数据验证: ${validateUserData(validData)}")
        println("无效数据验证: ${validateUserData(invalidData)}")
        
        // 场景2: 搜索并处理
        fun processUntilCondition(items: List<String>) {
            for (item in items) {
                if (item.startsWith("ERROR")) {
                    println("发现错误: $item")
                    break  // 遇到错误就停止
                }
                
                if (item.startsWith("SKIP")) {
                    println("跳过: $item")
                    continue  // 跳过该项
                }
                
                println("处理: $item")
            }
        }
        
        println("\n处理项目:")
        val items = listOf("INFO: Start", "SKIP: Temp", "INFO: Process", "ERROR: Failed", "INFO: End")
        processUntilCondition(items)
        
        // 场景3: 分页加载
        fun loadPages(totalPages: Int) {
            for (page in 1..totalPages) {
                println("加载第 $page 页")
                
                // 模拟加载失败
                if (page == 3) {
                    println("加载失败，停止加载")
                    break
                }
                
                // 模拟空页
                if (page == 2) {
                    println("页面为空，跳过")
                    continue
                }
                
                println("第 $page 页加载成功")
            }
        }
        
        println("\n分页加载:")
        loadPages(5)
    }
    
    // ========================================
    // 6. 范围（Range）和区间
    // ========================================
    
    /**
     * 范围和区间演示
     * 
     * Kotlin 提供了强大的范围操作符
     * .. : 闭区间（包含两端）
     * until : 开区间（不包含右端）
     * downTo : 递减区间
     * step : 步长
     */
    fun demonstrateRanges() {
        println("\n===== 范围（Range）和区间 =====")
        
        // ========== 基本范围 ==========
        println("--- 基本范围 ---")
        
        // 闭区间：1..10 表示 [1, 10]
        println("1..10: ${1..10}")
        println("包含 5: ${5 in 1..10}")
        println("包含 15: ${15 in 1..10}")
        
        // 开区间：1 until 10 表示 [1, 10)
        println("\n1 until 10: ${1 until 10}")
        println("包含 9: ${9 in 1 until 10}")
        println("包含 10: ${10 in 1 until 10}")
        
        // 递减区间
        println("\n10 downTo 1: ${10 downTo 1}")
        print("遍历: ")
        for (i in 10 downTo 1) {
            print("$i ")
        }
        println()
        
        // 带步长
        println("\n1..10 step 2: ${1..10 step 2}")
        print("遍历: ")
        for (i in 1..10 step 2) {
            print("$i ")
        }
        println()
        
        // ========== 字符范围 ==========
        println("\n--- 字符范围 ---")
        
        println("'a'..'z': ${'a'..'z'}")
        println("'c' in 'a'..'z': ${'c' in 'a'..'z'}")
        
        print("字母: ")
        for (ch in 'a'..'e') {
            print("$ch ")
        }
        println()
        
        // ========== 范围判断 ==========
        println("\n--- 范围判断 ---")
        
        val age = 25
        when (age) {
            in 0..12 -> println("儿童")
            in 13..17 -> println("青少年")
            in 18..60 -> println("成年人")
            else -> println("老年人")
        }
        
        // ========== 范围的实际应用 ==========
        println("\n--- 实际应用 ---")
        
        // 场景1: 成绩分级
        fun getGrade(score: Int): String {
            return when (score) {
                in 90..100 -> "A"
                in 80..89 -> "B"
                in 70..79 -> "C"
                in 60..69 -> "D"
                in 0..59 -> "F"
                else -> "无效分数"
            }
        }
        
        println("95分: ${getGrade(95)}")
        println("85分: ${getGrade(85)}")
        println("55分: ${getGrade(55)}")
        
        // 场景2: 时间段判断
        fun getTimePeriod(hour: Int): String {
            return when (hour) {
                in 0..5 -> "凌晨"
                in 6..11 -> "上午"
                12 -> "中午"
                in 13..17 -> "下午"
                in 18..23 -> "晚上"
                else -> "无效时间"
            }
        }
        
        println("\n9点: ${getTimePeriod(9)}")
        println("14点: ${getTimePeriod(14)}")
        println("20点: ${getTimePeriod(20)}")
        
        // 场景3: 生成序列
        println("\n生成偶数序列:")
        val evenNumbers = (2..20 step 2).toList()
        println(evenNumbers)
        
        println("\n生成递减序列:")
        val countdown = (10 downTo 1 step 2).toList()
        println(countdown)
    }
    
    // ========================================
    // 7. 综合实战演练
    // ========================================
    
    /**
     * 综合实战：学生成绩管理系统
     * 
     * 综合运用所有条件控制语句
     */
    fun practicalExercise() {
        println("\n" + "=".repeat(50))
        println("综合实战：学生成绩管理系统")
        println("=".repeat(50))
        
        // 数据模型
        data class Student(
            val id: Int,
            val name: String,
            val scores: Map<String, Int>  // 科目 -> 分数
        ) {
            // 计算平均分
            fun averageScore(): Double {
                return if (scores.isEmpty()) 0.0
                else scores.values.average()
            }
            
            // 获取等级
            fun getGrade(): String {
                val avg = averageScore()
                return when {
                    avg >= 90 -> "优秀"
                    avg >= 80 -> "良好"
                    avg >= 70 -> "中等"
                    avg >= 60 -> "及格"
                    else -> "不及格"
                }
            }
            
            // 是否有不及格科目
            fun hasFailedSubject(): Boolean {
                return scores.any { it.value < 60 }
            }
        }
        
        // 创建学生数据
        val students = listOf(
            Student(1, "张三", mapOf("数学" to 95, "英语" to 88, "语文" to 92)),
            Student(2, "李四", mapOf("数学" to 72, "英语" to 65, "语文" to 58)),
            Student(3, "王五", mapOf("数学" to 85, "英语" to 90, "语文" to 88)),
            Student(4, "赵六", mapOf("数学" to 45, "英语" to 52, "语文" to 60)),
            Student(5, "孙七", mapOf("数学" to 78, "英语" to 82, "语文" to 75))
        )
        
        // 功能1: 显示所有学生信息
        fun displayAllStudents() {
            println("\n【1】所有学生信息")
            println("-".repeat(50))
            
            for ((index, student) in students.withIndex()) {
                println("${index + 1}. ${student.name} (ID: ${student.id})")
                
                // 显示各科成绩
                for ((subject, score) in student.scores) {
                    val status = if (score >= 60) "✓" else "✗"
                    println("   $subject: $score $status")
                }
                
                println("   平均分: ${String.format("%.1f", student.averageScore())}")
                println("   等级: ${student.getGrade()}")
                println()
            }
        }
        
        // 功能2: 统计信息
        fun printStatistics() {
            println("\n【2】统计信息")
            println("-".repeat(50))
            
            val totalStudents = students.size
            println("学生总数: $totalStudents")
            
            // 各等级人数
            var excellent = 0
            var good = 0
            var medium = 0
            var pass = 0
            var fail = 0
            
            for (student in students) {
                when (student.getGrade()) {
                    "优秀" -> excellent++
                    "良好" -> good++
                    "中等" -> medium++
                    "及格" -> pass++
                    "不及格" -> fail++
                }
            }
            
            println("\n等级分布:")
            println("  优秀: $excellent")
            println("  良好: $good")
            println("  中等: $medium")
            println("  及格: $pass")
            println("  不及格: $fail")
            
            // 有不及格科目的学生
            val failedStudents = students.filter { it.hasFailedSubject() }
            println("\n有不及格科目的学生: ${failedStudents.size}")
            for (student in failedStudents) {
                print("  - ${student.name}: ")
                val failedSubjects = student.scores.filter { it.value < 60 }.keys
                println(failedSubjects.joinToString(", "))
            }
            
            // 最高分和最低分
            val highestAvg = students.maxByOrNull { it.averageScore() }
            val lowestAvg = students.minByOrNull { it.averageScore() }
            
            println("\n最高平均分: ${highestAvg?.name} (${String.format("%.1f", highestAvg?.averageScore() ?: 0.0)})")
            println("最低平均分: ${lowestAvg?.name} (${String.format("%.1f", lowestAvg?.averageScore() ?: 0.0)})")
        }
        
        // 功能3: 搜索学生
        fun searchStudent(query: String) {
            println("\n【3】搜索结果: '$query'")
            println("-".repeat(50))
            
            val results = students.filter {
                it.name.contains(query, ignoreCase = true) ||
                it.id.toString().contains(query)
            }
            
            if (results.isEmpty()) {
                println("未找到匹配的学生")
                return
            }
            
            for (student in results) {
                println("${student.name} (ID: ${student.id}) - 平均分: ${String.format("%.1f", student.averageScore())}")
            }
        }
        
        // 功能4: 按等级分组
        fun groupByGrade() {
            println("\n【4】按等级分组")
            println("-".repeat(50))
            
            val groups = mutableMapOf<String, MutableList<Student>>()
            
            for (student in students) {
                val grade = student.getGrade()
                if (!groups.containsKey(grade)) {
                    groups[grade] = mutableListOf()
                }
                groups[grade]?.add(student)
            }
            
            // 按固定顺序显示
            val gradeOrder = listOf("优秀", "良好", "中等", "及格", "不及格")
            
            for (grade in gradeOrder) {
                val group = groups[grade]
                if (!group.isNullOrEmpty()) {
                    println("\n$grade:")
                    for (student in group) {
                        println("  - ${student.name}: ${String.format("%.1f", student.averageScore())}")
                    }
                }
            }
        }
        
        // 功能5: 生成报告
        fun generateReport() {
            println("\n【5】成绩报告")
            println("=".repeat(50))
            
            for (student in students) {
                println("\n学生: ${student.name}")
                println("ID: ${student.id}")
                
                // 显示各科成绩及评价
                for ((subject, score) in student.scores) {
                    val evaluation = when {
                        score >= 90 -> "优秀"
                        score >= 80 -> "良好"
                        score >= 70 -> "中等"
                        score >= 60 -> "及格"
                        else -> "需努力"
                    }
                    println("  $subject: $score ($evaluation)")
                }
                
                println("  平均分: ${String.format("%.1f", student.averageScore())}")
                println("  总体评价: ${student.getGrade()}")
                
                // 给出建议
                if (student.hasFailedSubject()) {
                    println("  ⚠️  建议: 有不及格科目，需要加强学习")
                } else if (student.averageScore() >= 90) {
                    println("  ✓ 表现优秀，继续保持")
                } else if (student.averageScore() >= 80) {
                    println("  ✓ 表现良好，争取更优秀")
                }
            }
        }
        
        // 执行演示
        displayAllStudents()
        printStatistics()
        searchStudent("张")
        searchStudent("3")
        groupByGrade()
        generateReport()
    }
    
    // ========================================
    // 8. 最佳实践总结
    // ========================================
    
    /**
     * 条件控制最佳实践总结
     */
    fun bestPractices() {
        println("\n" + "=".repeat(50))
        println("最佳实践总结")
        println("=".repeat(50))
        
        println("""
        
        ✅ if 表达式最佳实践：
        
        1. 优先使用 if 表达式代替三元运算符
           val max = if (a > b) a else b
        
        2. 保持简洁，复杂逻辑提取为函数
        
        3. 避免过深的嵌套，考虑使用 when 或提前返回
        
        
        ✅ when 表达式最佳实践：
        
        1. 优先使用 when 代替多个 if-else
        
        2. 利用 when 的强大功能：
           - 范围匹配: in 1..10
           - 类型检查: is String
           - 多值合并: 1, 2, 3 -> ...
           - 无条件 when: when { ... }
        
        3. 总是提供 else 分支（除非编译器能保证覆盖所有情况）
        
        4. 对于密封类，利用编译期检查确保所有分支都被处理
        
        
        ✅ for 循环最佳实践：
        
        1. 遍历集合时优先使用 for 或 forEach
        
        2. 需要索引时使用 withIndex()
        
        3. 简单遍历优先考虑高阶函数（map、filter、forEach）
        
        4. 避免在循环中修改集合
        
        
        ✅ while 循环最佳实践：
        
        1. 确保循环条件最终会变为 false（避免死循环）
        
        2. 对于已知次数的循环，优先使用 for
        
        3. 使用 do-while 时需要至少执行一次的场景
        
        
        ✅ 跳转表达式最佳实践：
        
        1. 合理使用 break 和 continue 提高代码可读性
        
        2. 避免过多的嵌套，使用早期返回
        
        3. 标签（label）谨慎使用，只在必要时使用
        
        4. 优先使用函数式编程（filter、find 等）代替手动循环
        
        
        📊 选择指南：
        
        场景                      推荐方式
        ──────────────────────────────────────────
        两个分支                  if-else
        多个分支                  when
        遍历集合                  for 或 forEach
        已知次数循环              for
        未知次数循环              while
        至少执行一次              do-while
        简单条件返回              if 表达式
        复杂条件匹配              when
        过滤集合                  filter（高阶函数）
        查找元素                  find（高阶函数）
        
        
        🎯 核心原则：
        
        1. 代码可读性优先
           - 选择最清晰的表达方式
           - 避免过度复杂的嵌套
        
        2. 利用 Kotlin 特性
           - if 表达式
           - when 的强大匹配
           - 范围操作符
           - 高阶函数
        
        3. 保持简洁
           - 能一行搞定就不要多行
           - 提取重复逻辑为函数
        
        4. 安全性
           - 处理所有可能的分支
           - 提供默认值（else 分支）
           - 避免死循环
        
        """.trimIndent())
    }
}

enum class OrderStatus { PENDING, PAID, SHIPPED, DELIVERED, CANCELLED }


/**
 * 主函数 - 运行所有演示
 */
fun main() {
    val control = Control()
    
    // 依次运行所有演示
    control.demonstrateIfExpression()
    control.demonstrateWhenExpression()
    control.demonstrateForLoop()
    control.demonstrateWhileLoops()
    control.demonstrateJumpExpressions()
    control.demonstrateRanges()
    control.practicalExercise()
    control.bestPractices()
    
    println("\n" + "=".repeat(50))
    println("✅ 条件控制教程完成！")
    println("=".repeat(50))
}