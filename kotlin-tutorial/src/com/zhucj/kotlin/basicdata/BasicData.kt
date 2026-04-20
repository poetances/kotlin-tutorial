package com.zhucj.kotlin.basicdata

/**
 * Kotlin 基本数据类型详解教程
 * 
 * 本文件系统讲解 Kotlin 中的基本数据类型，包括：
 * 1. 数字类型（Numbers）
 * 2. 字符类型（Char）
 * 3. 布尔类型（Boolean）
 * 4. 字符串类型（String）
 * 5. 数组（Arrays）
 * 6. 可空类型（Nullable Types）- Kotlin 核心特性
 * 7. 类型推断与声明
 * 8. 类型转换（Type Casting）
 */
class BasicData {
    
    // ========================================
    // 1. 数字类型（Numbers）
    // ========================================
    
    /**
     * 整数类型演示
     * 
     * Kotlin 提供四种整数类型：
     * - Byte: 8位，范围 -128 ~ 127
     * - Short: 16位，范围 -32768 ~ 32767
     * - Int: 32位，范围 -2^31 ~ 2^31-1（最常用）
     * - Long: 64位，范围 -2^63 ~ 2^63-1（大数使用，需加 L 后缀）
     */
    fun demonstrateIntegerTypes() {
        println("===== 整数类型 =====")
        
        // Byte - 适用于小范围整数，节省内存
        val byteValue: Byte = 127
        println("Byte: $byteValue (范围: -128 ~ 127)")
        
        // Short - 中等范围整数
        val shortValue: Short = 32767
        println("Short: $shortValue (范围: -32768 ~ 32767)")
        
        // Int - 最常用的整数类型，大多数场景直接使用
        val intValue: Int = 2147483647
        println("Int: $intValue (最常用)")
        
        // Long - 大数值，如时间戳、ID等，需要 L 后缀
        val longValue: Long = 9223372036854775807L
        println("Long: $longValue (大数使用，注意 L 后缀)")
        
        // 实际开发场景
        val age: Int = 25                    // 年龄用 Int
        val userId: Long = 1234567890123L    // 用户ID用 Long
        val timestamp: Long = System.currentTimeMillis()  // 时间戳用 Long
        
        println("\n实际应用场景:")
        println("年龄: $age")
        println("用户ID: $userId")
        println("当前时间戳: $timestamp")
    }
    
    /**
     * 浮点类型演示
     * 
     * Kotlin 提供两种浮点类型：
     * - Float: 32位单精度浮点数（需加 f 后缀）
     * - Double: 64位双精度浮点数（默认浮点类型）
     */
    fun demonstrateFloatTypes() {
        println("\n===== 浮点类型 =====")
        
        // Float - 单精度，需要 f 后缀
        val floatValue: Float = 3.14f
        println("Float: $floatValue (注意 f 后缀)")
        
        // Double - 双精度，默认浮点类型，精度更高
        val doubleValue: Double = 3.141592653589793
        println("Double: $doubleValue (默认浮点类型)")
        
        // 实际开发场景
        val price: Double = 99.99            // 价格通常用 Double
        val rating: Float = 4.5f             // 评分可以用 Float
        val pi: Double = Math.PI             // 数学常数
        
        println("\n实际应用场景:")
        println("商品价格: ¥$price")
        println("用户评分: $rating / 5.0")
        println("圆周率: $pi")
        
        // 注意：浮点数计算可能存在精度问题
        val result = 0.1 + 0.2
        println("\n注意: 0.1 + 0.2 = $result (不是精确的 0.3)")
    }
    
    // ========================================
    // 2. 字符类型（Char）
    // ========================================
    
    /**
     * 字符类型演示
     * 
     * Char 表示单个字符，必须用单引号包裹
     * 不能直接用数字赋值，但可以获取其 Unicode 值
     */
    fun demonstrateCharType() {
        println("\n===== 字符类型 =====")
        
        // 字符必须用单引号
        val letter: Char = 'A'
        val digit: Char = '5'
        val symbol: Char = '@'
        val chinese: Char = '中'
        
        println("字母: $letter")
        println("数字字符: $digit")
        println("符号: $symbol")
        println("中文字符: $chinese")
        
        // 获取 Unicode 值
        println("\nUnicode 值:")
        println("'A' 的 Unicode: ${letter.code}")  // 65
        println("'a' 的 Unicode: ${'a'.code}")      // 97
        
        // 字符运算
        val nextLetter = letter + 1  // 'B'
        println("'$letter' + 1 = '$nextLetter'")
        
        // 判断字符类型
        println("\n字符判断:")
        println("'${digit}' 是数字: ${digit.isDigit()}")
        println("'${letter}' 是字母: ${letter.isLetter()}")
        println("'${letter}' 是大写: ${letter.isUpperCase()}")
    }
    
    // ========================================
    // 3. 布尔类型（Boolean）
    // ========================================
    
    /**
     * 布尔类型演示
     * 
     * Boolean 只有两个值：true 和 false
     * 主要用于条件判断、状态标志等
     */
    fun demonstrateBooleanType() {
        println("\n===== 布尔类型 =====")
        
        val isTrue: Boolean = true
        val isFalse: Boolean = false
        
        println("isTrue: $isTrue")
        println("isFalse: $isFalse")
        
        // 实际开发场景
        val isActive = true              // 账号是否激活
        val isEmpty = false              // 列表是否为空
        val hasPermission = true         // 是否有权限
        val isLoggedIn = false           // 是否已登录
        
        println("\n实际应用场景:")
        println("账号激活: $isActive")
        println("列表为空: $isEmpty")
        println("有权限: $hasPermission")
        println("已登录: $isLoggedIn")
        
        // 布尔运算
        println("\n布尔运算:")
        println("true && false = ${true && false}")  // 与运算
        println("true || false = ${true || false}")  // 或运算
        println("!true = ${!true}")                   // 非运算
        
        // 条件判断
        val age = 20
        val isAdult = age >= 18
        println("\n年龄 $age 岁，是否成年: $isAdult")
    }
    
    // ========================================
    // 4. 字符串类型（String）
    // ========================================
    
    /**
     * 字符串类型演示
     * 
     * String 是不可变的字符序列，Kotlin 提供了丰富的字符串操作
     * 支持字符串模板，让字符串拼接更优雅
     */
    fun demonstrateStringType() {
        println("\n===== 字符串类型 =====")
        
        // 基本字符串
        val greeting: String = "Hello, Kotlin!"
        println("基本字符串: $greeting")
        
        // 字符串模板 - Kotlin 的强大特性
        val name = "张三"
        val age = 25
        val message = "我叫 $name，今年 $age 岁"  // 使用 $ 变量名
        println("字符串模板: $message")
        
        // 支持表达式
        val calculation = "2 + 3 = ${2 + 3}"  // 使用 ${表达式}
        println("表达式模板: $calculation")
        
        // 多行字符串 - 使用三个双引号
        val multiLine = """
            这是第一行
            这是第二行
            这是第三行
        """.trimIndent()  // 去除首尾空白和统一缩进
        println("\n多行字符串:")
        println(multiLine)
        
        // 原始字符串（不处理转义字符）
        val rawString = """C:\Users\Documents\file.txt"""
        println("\n原始字符串: $rawString")
        
        // ========== 常用字符串操作 ==========
        println("\n===== 常用字符串操作 =====")
        
        val str = "  Hello Kotlin World  "
        
        // 长度
        println("长度: ${str.length}")
        
        // 大小写转换
        println("大写: ${str.uppercase()}")
        println("小写: ${str.lowercase()}")
        
        // 去除空格
        println("去除首尾空格: '${str.trim()}'")
        println("去除左侧空格: '${str.trimStart()}'")
        println("去除右侧空格: '${str.trimEnd()}'")
        
        // 截取子串
        println("截取(0-5): '${str.trim().substring(0, 5)}'")
        
        // 查找和判断
        println("包含 'Kotlin': ${str.contains("Kotlin")}")
        println("以 'Hello' 开头: ${str.trim().startsWith("Hello")}")
        println("以 'World' 结尾: ${str.trim().endsWith("World")}")
        println("是否为空: ${str.isEmpty()}")
        println("是否为空白: ${str.isBlank()}")  // 空或只包含空白字符
        
        // 分割字符串
        val csv = "apple,banana,orange"
        val fruits = csv.split(",")
        println("\n分割字符串: $fruits")
        
        // 替换
        val replaced = str.replace("Kotlin", "Java")
        println("替换后: '$replaced'")
        
        // 重复
        val repeated = "Ha".repeat(3)
        println("重复: $repeated")
        
        // 实际开发场景
        println("\n===== 实际开发场景 =====")
        
        // 场景1: 格式化输出
        val productName = "iPhone 15"
        val productPrice = 7999.0
        val formattedInfo = """
            商品名称: $productName
            商品价格: ¥${productPrice}
            库存状态: ${if (productPrice > 0) "有货" else "缺货"}
        """.trimIndent()
        println(formattedInfo)
        
        // 场景2: 邮箱验证（简单示例）
        val email = "user@example.com"
        val isValidEmail = email.contains("@") && email.contains(".")
        println("邮箱 '$email' 格式有效: $isValidEmail")
        
        // 场景3: 路径处理
        val filePath = "/home/user/documents/file.txt"
        val fileName = filePath.substringAfterLast("/")
        val extension = filePath.substringAfterLast(".")
        println("文件路径: $filePath")
        println("文件名: $fileName")
        println("扩展名: $extension")
    }
    
    // ========================================
    // 5. 数组（Arrays）
    // ========================================
    
    /**
     * 数组类型演示
     * 
     * Kotlin 提供两种数组：
     * - Array<T>: 泛型数组，元素会被装箱（性能稍低）
     * - 专用数组: IntArray、DoubleArray 等，元素不装箱（性能更好）
     */
    fun demonstrateArrays() {
        println("\n===== 数组类型 =====")
        
        // ========== 泛型数组 ==========
        println("--- 泛型数组 Array<T> ---")


        // 创建数组
        val numbers: Array<Int> = arrayOf(1, 2, 3, 4, 5)
        val strings: Array<String> = arrayOf("apple", "banana", "orange")
        val mixed: Array<Any> = arrayOf(1, "hello", 3.14, true)
        
        println("数字数组: ${numbers.contentToString()}")
        println("字符串数组: ${strings.contentToString()}")
        println("混合数组: ${mixed.contentToString()}")
        
        // 访问和修改元素
        println("第一个元素: ${numbers[0]}")
        println("最后一个元素: ${numbers.last()}")
        numbers[0] = 10
        println("修改后: ${numbers.contentToString()}")
        
        // 数组属性
        println("数组大小: ${numbers.size}")
        println("是否为空: ${numbers.isEmpty()}")
        
        // 遍历数组
        println("\n遍历数组:")
        for (number in numbers) {
            print("$number ")
        }
        println()
        
        // 带索引遍历
        println("\n带索引遍历:")
        for ((index, value) in strings.withIndex()) {
            println("[$index]: $value")
        }
        
        // ========== 专用数组（推荐用于基本类型）==========
        println("\n--- 专用数组（性能更好）---")
        
        val intArray: IntArray = intArrayOf(1, 2, 3, 4, 5)
        val doubleArray: DoubleArray = doubleArrayOf(1.1, 2.2, 3.3)
        val charArray: CharArray = charArrayOf('a', 'b', 'c')
        val booleanArray: BooleanArray = booleanArrayOf(true, false, true)
        
        println("IntArray: ${intArray.contentToString()}")
        println("DoubleArray: ${doubleArray.contentToString()}")
        println("CharArray: ${charArray.contentToString()}")
        println("BooleanArray: ${booleanArray.contentToString()}")
        
        // 创建指定大小的数组
        val zeros = IntArray(5)  // [0, 0, 0, 0, 0]
        println("初始化为0: ${zeros.contentToString()}")
        
        val initialized = IntArray(5) { it * 2 }  // [0, 2, 4, 6, 8]
        println("初始化表达式: ${initialized.contentToString()}")
        
        // 数组常用操作
        println("\n===== 数组常用操作 =====")
        
        val scores = intArrayOf(85, 92, 78, 95, 88)
        
        // 求和、平均值、最大最小值
        println("总分: ${scores.sum()}")
        println("平均分: ${scores.average()}")
        println("最高分: ${scores.maxOrNull()}")
        println("最低分: ${scores.minOrNull()}")
        
        // 过滤
        val passedScores = scores.filter { it >= 90 }
        println("及格分数(>=90): $passedScores")
        
        // 映射
        val doubled = scores.map { it * 2 }
        println("翻倍后: $doubled")
        
        // 排序
        val sorted = scores.sorted()
        println("排序后: $sorted")
        
        // 实际开发场景
        println("\n===== 实际开发场景 =====")
        
        // 场景1: 存储用户ID列表
        val userIds = longArrayOf(1001L, 1002L, 1003L, 1004L)
        println("用户ID列表: ${userIds.contentToString()}")
        
        // 场景2: 存储温度数据
        val temperatures = doubleArrayOf(23.5, 25.0, 22.8, 24.3, 26.1)
        println("平均温度: ${temperatures.average()}°C")
        
        // 场景3: 二维数组（矩阵）
        val matrix = Array(3) { IntArray(3) }
        matrix[0][0] = 1
        matrix[1][1] = 5
        matrix[2][2] = 9
        println("\n矩阵对角线元素:")
        for (i in matrix.indices) {
            println("matrix[$i][$i] = ${matrix[i][i]}")
        }
    }
    
    // ========================================
    // 6. 可空类型（Nullable Types）⭐ 核心特性
    // ========================================
    
    /**
     * 可空类型演示
     * 
     * Kotlin 的类型系统区分可空和非空类型，从编译层面避免空指针异常
     * - String: 非空类型，不能为 null
     * - String?: 可空类型，可以为 null
     * 
     * 这是 Kotlin 最重要的特性之一！
     */
    fun demonstrateNullableTypes() {
        println("\n===== 可空类型（Kotlin 核心特性）=====")
        
        // ========== 非空类型 vs 可空类型 ==========
        println("--- 非空类型 vs 可空类型 ---")
        
        val nonNull: String = "Hello"  // 非空，不能赋值为 null
        var nullable: String? = "Hello"  // 可空，可以赋值为 null
        
        println("非空字符串: $nonNull")
        println("可空字符串: $nullable")
        
        nullable = null  // ✅ 允许
        println("设置为 null 后: $nullable")
        
        // ========== 安全调用操作符 ?. ==========
        println("\n--- 安全调用操作符 ?. ---")
        
        val name: String? = "Kotlin"
        
        // 安全调用：如果为 null，返回 null，不会抛异常
        val length = name?.length
        println("'$name' 的长度: $length")  // 7
        
        val nullName: String? = null
        val nullLength = nullName?.length
        println("null 的长度: $nullLength")  // null
        
        // 链式安全调用
        data class Address(val city: String?)
        data class User(val name: String?, val address: Address?)
        
        val user = User("张三", Address("北京"))
        val city = user.address?.city
        println("\n用户城市: $city")  // 北京
        
        val userWithoutAddress = User("李四", null)
        val city2 = userWithoutAddress.address?.city
        println("无地址用户的城市: $city2")  // null
        
        // ========== Elvis 运算符 ?: ==========
        println("\n--- Elvis 运算符 ?:（提供默认值）---")
        
        val displayName = nullName ?: "匿名用户"
        println("显示名称: $displayName")  // 匿名用户
        
        val actualName = name ?: "匿名用户"
        println("实际名称: $actualName")  // Kotlin
        
        // 实际开发中常用
        val config: String? = null
        val effectiveConfig = config ?: "default_config"
        println("有效配置: $effectiveConfig")
        
        // ========== 非空断言 !! ==========
        println("\n--- 非空断言 !!（谨慎使用）---")
        
        val certainName: String? = "Kotlin"
        // val len = certainName!!.length  // 确定不为 null 时使用
        println("确定非空时的长度: ${certainName?.length}")  // 建议用 ?. 代替 !!
        
        // ⚠️ 危险：如果为 null 会抛出 NullPointerException
        // val dangerous = nullName!!.length  // 运行时异常！
        
        // ========== let 函数处理可空值 ==========
        println("\n--- let 函数处理可空值 ---")
        
        nullable?.let {
            // 只在非空时执行，it 代表非空值
            println("名字非空: $it，长度: ${it.length}")
        } ?: run {
            // 为空时执行
            println("名字为空，使用默认值")
        }
        
        // ========== 智能转换 ==========
        println("\n--- 智能转换（Smart Cast）---")
        
        val text: String? = "Hello"
        if (text != null) {
            // 在 if 块内，text 自动转换为非空类型 String
            println("智能转换后的长度: ${text.length}")  // 不需要 ?
        }
        
        // ========== when 表达式处理可空值 ==========
        println("\n--- when 表达式处理可空值 ---")
        
        val status: String? = "active"
        val statusText = when (status) {
            "active" -> "活跃"
            "inactive" -> "非活跃"
            null -> "未知状态"
            else -> "其他状态"
        }
        println("状态: $statusText")
        
        // ========== 实际开发场景 ==========
        println("\n===== 实际开发场景 =====")
        
        // 场景1: API 返回数据处理
        data class ApiResponse(val code: Int, val message: String?, val data: String?)
        
        val response = ApiResponse(200, "成功", "{\"user\":\"张三\"}")
        val errorMessage = response.message ?: "未知错误"
        val responseData = response.data ?: "{}"
        println("API 消息: $errorMessage")
        println("API 数据: $responseData")
        
        // 场景2: 表单验证
        val username: String? = ""  // 可能为空或空字符串
        val validUsername = if (username.isNullOrBlank()) {
            "请输入用户名"
        } else {
            "用户名有效: $username"
        }
        println(validUsername)
        
        // 场景3: 可选参数
        fun greet(name: String?, greeting: String? = null) {
            val actualGreeting = greeting ?: "你好"
            val actualName = name ?: "朋友"
            println("$actualGreeting, $actualName!")
        }
        
        greet("张三")
        greet(null, "Hello")
        greet("李四", "Welcome")
    }
    
    // ========================================
    // 7. 类型推断与变量声明
    // ========================================
    
    /**
     * 类型推断与变量声明演示
     * 
     * Kotlin 支持类型推断，编译器可以自动推断变量类型
     * val: 不可变引用（类似 Java 的 final）
     * var: 可变引用
     * 
     * 最佳实践：优先使用 val，只有在确实需要修改时才用 var
     */
    fun demonstrateTypeInference() {
        println("\n===== 类型推断与变量声明 =====")
        
        // ========== 显式声明类型 ==========
        println("--- 显式声明类型 ---")
        val explicitInt: Int = 42
        val explicitString: String = "Hello"
        val explicitDouble: Double = 3.14
        
        println("显式 Int: $explicitInt")
        println("显式 String: $explicitString")
        println("显式 Double: $explicitDouble")
        
        // ========== 类型推断（推荐）==========
        println("\n--- 类型推断（更简洁，推荐）---")
        val inferredInt = 42          // 编译器推断为 Int
        val inferredString = "Hello"  // 编译器推断为 String
        val inferredDouble = 3.14     // 编译器推断为 Double
        val inferredList = listOf(1, 2, 3)  // 推断为 List<Int>
        
        println("推断 Int: $inferredInt (${inferredInt::class.simpleName})")
        println("推断 String: $inferredString (${inferredString::class.simpleName})")
        println("推断 Double: $inferredDouble (${inferredDouble::class.simpleName})")
        println("推断 List: $inferredList (${inferredList::class.simpleName})")
        
        // ========== val vs var ==========
        println("\n--- val（不可变）vs var（可变）---")
        
        // val - 不可变引用（类似 Java 的 final）
        val constant = 10
        // constant = 20  ❌ 编译错误：Val cannot be reassigned
        println("val 常量: $constant")
        
        // var - 可变引用
        var variable = 10
        variable = 20  // ✅ 允许重新赋值
        println("var 变量: $variable")
        
        // ========== 延迟初始化 ==========
        println("\n--- 延迟初始化 ---")
        
        // lateinit - 延迟初始化（只能用于 var，且不能是基本类型）
        lateinit var delayedInit: String
        // println(delayedInit)  ❌ 未初始化就访问会抛异常
        delayedInit = "Initialized later"
        println("延迟初始化: $delayedInit")
        
        // lazy - 懒加载（第一次访问时初始化，线程安全）
        val lazyValue: String by lazy {
            println("正在初始化...")
            "Lazy Value"
        }
        println("首次访问 lazy: $lazyValue")  // 会打印"正在初始化..."
        println("再次访问 lazy: $lazyValue")  // 不会再次初始化
        
        // ========== 实际开发最佳实践 ==========
        println("\n===== 实际开发最佳实践 =====")
        
        // ✅ 推荐：优先使用 val
        val userName = "张三"
        val userAge = 25
        val userEmail = "zhangsan@example.com"
        
        // ✅ 推荐：利用类型推断，代码更简洁
        val count = 100
        val price = 99.99
        val isActive = true
        
        // ⚠️ 仅在需要修改时使用 var
        var currentScore = 0
        currentScore += 10
        currentScore += 20
        println("当前分数: $currentScore")
        
        // 实际场景：配置对象
        data class Config(
            val apiUrl: String,
            val timeout: Int,
            val retryCount: Int
        )
        
        val config = Config(
            apiUrl = "https://api.example.com",
            timeout = 3000,
            retryCount = 3
        )
        println("\nAPI URL: ${config.apiUrl}")
        println("超时时间: ${config.timeout}ms")
        println("重试次数: ${config.retryCount}")
    }
    
    // ========================================
    // 8. 类型转换（Type Casting）
    // ========================================
    
    /**
     * 类型转换演示
     * 
     * Kotlin 不支持隐式数值类型转换，必须显式转换
     * 提供了安全的转换方法，避免运行时异常
     */
    fun demonstrateTypeConversion() {
        println("\n===== 类型转换 =====")
        
        // ========== 数字类型转换 ==========
        println("--- 数字类型转换 ---")
        
        val int: Int = 10
        
        // 转换为其他数字类型
        val long: Long = int.toLong()
        val double: Double = int.toDouble()
        val float: Float = int.toFloat()
        val short: Short = int.toShort()
        val byte: Byte = int.toByte()
        
        println("Int: $int")
        println("转为 Long: $long")
        println("转为 Double: $double")
        println("转为 Float: $float")
        println("转为 Short: $short")
        println("转为 Byte: $byte")
        
        // ⚠️ 注意：向下转换可能丢失精度或溢出
        val largeInt = 300
        val asByte = largeInt.toByte()  // 溢出：300 -> 44
        println("\n⚠️ 溢出示例: $largeInt 转为 Byte = $asByte")
        
        // ========== 字符串与数字互转 ==========
        println("\n--- 字符串与数字互转 ---")
        
        // 字符串转数字
        val strNum = "123"
        val parsedInt = strNum.toInt()
        val parsedLong = "1234567890".toLong()
        val parsedDouble = "3.14".toDouble()
        val parsedFloat = "2.5f".toFloat()
        
        println("字符串 \"123\" 转为 Int: $parsedInt")
        println("字符串 \"1234567890\" 转为 Long: $parsedLong")
        println("字符串 \"3.14\" 转为 Double: $parsedDouble")
        println("字符串 \"2.5f\" 转为 Float: $parsedFloat")
        
        // 数字转字符串
        val number = 42
        val strFromNumber = number.toString()
        println("\n数字 $number 转为字符串: \"$strFromNumber\"")
        
        // ========== 安全转换（推荐）==========
        println("\n--- 安全转换（推荐）---")
        
        // toIntOrNull() - 转换失败返回 null，不会抛异常
        val validNum = "123".toIntOrNull()
        val invalidNum = "abc".toIntOrNull()
        val emptyStr = "".toIntOrNull()
        
        println("\"123\" 安全转换: $validNum")       // 123
        println("\"abc\" 安全转换: $invalidNum")     // null
        println("\"\" 安全转换: $emptyStr")          // null
        
        // 结合 Elvis 运算符提供默认值
        val safeInt = "not_a_number".toIntOrNull() ?: 0
        println("转换失败使用默认值: $safeInt")  // 0
        
        // 实际开发中的安全解析
        fun parseAge(input: String?): Int {
            return input?.toIntOrNull() ?: 0
        }
        
        println("\n解析年龄 \"25\": ${parseAge("25")}")
        println("解析年龄 \"abc\": ${parseAge("abc")}")
        println("解析年龄 null: ${parseAge(null)}")
        
        // ========== 进制转换 ==========
        println("\n--- 进制转换 ---")
        
        val decimal = 255
        println("十进制: $decimal")
        println("二进制: ${decimal.toString(2)}")
        println("八进制: ${decimal.toString(8)}")
        println("十六进制: ${decimal.toString(16).uppercase()}")
        
        // 从其他进制解析
        val fromBinary = "11111111".toInt(2)
        val fromHex = "FF".toInt(16)
        println("\n二进制 \"11111111\" 转为十进制: $fromBinary")
        println("十六进制 \"FF\" 转为十进制: $fromHex")
        
        // ========== 实际开发场景 ==========
        println("\n===== 实际开发场景 =====")
        
        // 场景1: 用户输入处理
        fun processUserInput(input: String) {
            val number = input.toIntOrNull()
            if (number != null) {
                println("有效的数字输入: $number")
            } else {
                println("无效的数字输入: $input")
            }
        }
        
        processUserInput("42")
        processUserInput("abc")
        
        // 场景2: 单位转换
        fun metersToCentimeters(meters: Double): Int {
            return (meters * 100).toInt()
        }
        
        val meters = 1.5
        val centimeters = metersToCentimeters(meters)
        println("\n$meters 米 = $centimeters 厘米")
        
        // 场景3: 颜色值转换
        val hexColor = "FF5733"
        val red = hexColor.substring(0, 2).toInt(16)
        val green = hexColor.substring(2, 4).toInt(16)
        val blue = hexColor.substring(4, 6).toInt(16)
        println("\n十六进制颜色 #$hexColor")
        println("RGB: ($red, $green, $blue)")
    }
    
    // ========================================
    // 综合实战演练
    // ========================================
    
    /**
     * 综合实战：模拟用户注册系统
     * 
     * 综合运用所有基本数据类型，展示实际开发中的应用
     */
    fun practicalExercise() {
        println("\n" + "=".repeat(50))
        println("综合实战：用户注册系统")
        println("=".repeat(50))
        
        // 用户数据结构
        data class User(
            val id: Long,
            val username: String?,
            val email: String?,
            val age: Int?,
            val isActive: Boolean,
            val registerTime: Long,
            val score: Double
        )
        
        // 模拟用户注册
        fun registerUser(
            username: String?,
            email: String?,
            age: String?  // 假设从表单接收的是字符串
        ): User {
            val userId = System.currentTimeMillis()  // 使用时间戳作为 ID
            val parsedAge = age?.toIntOrNull()  // 安全转换年龄
            val currentTime = System.currentTimeMillis()
            
            return User(
                id = userId,
                username = username,
                email = email,
                age = parsedAge,
                isActive = true,
                registerTime = currentTime,
                score = 100.0  // 初始积分
            )
        }
        
        // 注册用户
        val user1 = registerUser("张三", "zhangsan@example.com", "25")
        val user2 = registerUser("李四", null, "abc")  // 邮箱为空，年龄无效
        
        // 显示用户信息
        fun displayUser(user: User) {
            println("\n--- 用户信息 ---")
            println("用户ID: ${user.id}")
            println("用户名: ${user.username ?: "未设置"}")
            println("邮箱: ${user.email ?: "未绑定"}")
            println("年龄: ${user.age?.toString() ?: "未填写"}")
            println("状态: ${if (user.isActive) "活跃" else "禁用"}")
            println("注册时间: ${user.registerTime}")
            println("积分: ${user.score}")
        }
        
        displayUser(user1)
        displayUser(user2)
        
        // 用户列表管理
        val users = mutableListOf(user1, user2)
        println("\n当前用户总数: ${users.size}")
        
        // 统计活跃用户
        val activeUsers = users.filter { it.isActive }
        println("活跃用户数: ${activeUsers.size}")
        
        // 计算平均积分
        val averageScore = users.mapNotNull { it.score }.average()
        println("平均积分: $averageScore")
        
        // 查找特定用户
        val searchName = "张三"
        val foundUser = users.find { it.username == searchName }
        println("\n查找用户 \"$searchName\": ${foundUser?.username ?: "未找到"}")
    }
}

/**
 * 主函数 - 运行所有演示
 */
fun main() {
    val basicData = BasicData()
    
    // 依次运行所有演示
    basicData.demonstrateIntegerTypes()
    basicData.demonstrateFloatTypes()
    basicData.demonstrateCharType()
    basicData.demonstrateBooleanType()
    basicData.demonstrateStringType()
    basicData.demonstrateArrays()
    basicData.demonstrateNullableTypes()
    basicData.demonstrateTypeInference()
    basicData.demonstrateTypeConversion()
    basicData.practicalExercise()
    
    println("\n" + "=".repeat(50))
    println("✅ 所有演示完成！")
    println("=".repeat(50))
}