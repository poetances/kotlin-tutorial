package com.zhucj.kotlin.function

/**
 * Kotlin 函数详解
 * 
 * ============================================================================
 * 【核心原理】
 * ============================================================================
 * 
 * 1. 函数的本质
 *    - Kotlin 的函数是一等公民（First-class citizen）
 *    - 函数可以作为参数传递、作为返回值、赋值给变量
 *    - 底层编译为 JVM 的静态方法或实例方法
 *    - 支持顶层函数（不需要类包裹）
 * 
 * 2. 与 Java 的关键区别
 * 
 *    ┌─────────────────┬──────────────────────┬──────────────────────┐
 *    │     特性         │      Java            │      Kotlin          │
 *    ├─────────────────┼──────────────────────┼──────────────────────┤
 *    │ 函数声明         │ public static void   │ fun functionName()   │
 *    │                  │ methodName() {}      │                      │
 *    │ 返回类型         │ 写在前面 void/int    │ 写在后面 : Int       │
 *    │ 默认参数         │ 不支持（需重载）      │ 原生支持             │
 *    │ 命名参数         │ 不支持               │ 原生支持             │
 *    │ 可变参数         │ Type... args         │ vararg args: Type    │
 *    │ 扩展函数         │ 不支持               │ 原生支持             │
 *    │ 内联函数         │ 不支持               │ inline 关键字        │
 *    │ 尾递归优化       │ 不支持               │ tailrec 关键字       │
 *    │ 局部函数         │ 不支持（需匿名类）    │ 原生支持             │
 *    │ 操作符重载       │ 不支持               │ 原生支持             │
 *    │ 中缀调用         │ 不支持               │ infix 关键字         │
 *    │ Lambda 表达式    │ 需要函数式接口        │ 直接支持             │
 *    └─────────────────┴──────────────────────┴──────────────────────┘
 * 
 * 3. 函数类型和高阶函数
 *    - 函数类型：(Int, String) -> Boolean
 *    - 高阶函数：接受函数作为参数或返回函数的函数
 *    - Lambda 表达式：{ x, y -> x + y }
 *    - 函数引用：::functionName
 * 
 * 4. 性能优化
 *    - inline 函数：避免 Lambda 创建对象的开销
 *    - tailrec 优化：将递归转换为循环，避免栈溢出
 *    - reified 泛型：在运行时保留泛型类型信息
 * 
 * 5. 作用域和闭包
 *    - 局部函数可以访问外部函数的变量（闭包）
 *    - Lambda 表达式捕获外部变量
 *    - 注意内存泄漏风险（捕获大对象）
 * 
 * 6. 函数重载 vs 默认参数
 *    - Java：通过方法重载实现不同参数组合
 *    - Kotlin：优先使用默认参数，减少代码冗余
 *    - Kotlin 也支持重载，但与默认参数配合使用时需注意
 * 
 * 7. 扩展函数的原理
 *    - 编译为静态方法，第一个参数是接收者对象
 *    - 不修改原类，只是语法糖
 *    - 不能访问私有成员
 *    - 解析优先级：成员函数 > 扩展函数
 * 
 * 8. 内联函数的原理
 *    - 编译器将函数体复制到调用处
 *    - 避免 Lambda 创建匿名类的开销
 *    - 适合高频调用的小函数
 *    - 过度使用会增加代码体积
 * 
 * ============================================================================
 */
class FunctionLesson {
    /**
     * 1. 基本函数定义
     */
    fun demonstrateBasicFunctions() {
        println("=== 1. 基本函数定义 ===")
        
        // 调用各种函数
        println("问候: ${greet("Alice")}")
        println("加法: ${add(5, 3)}")
        println("无返回值: ${printMessage("Hello")}")
        
        println()
    }

    // 简单函数
    fun greet(name: String): String {
        return "Hello, $name!"
    }
    
    // 单表达式函数（省略花括号和 return）
    fun add(a: Int, b: Int): Int = a + b
    
    // 返回 Unit（类似 void）
    fun printMessage(message: String): Unit {
        println("消息: $message")
    }
    
    // 省略 Unit（推荐）
    fun showMessage(message: String) {
        println("显示: $message")
    }
    
    // 多返回值（使用 Pair 或 Triple）
    fun getCoordinates(): Pair<Double, Double> {
        return Pair(10.5, 20.3)
    }
    
    fun getUserInfo(): Triple<String, Int, String> {
        return Triple("Alice", 25, "Beijing")
    }

    /**
     * 2. 默认参数
     */
    fun demonstrateDefaultParameters() {
        println("=== 2. 默认参数 ===")
        
        // 使用默认参数
        println(createUser("Alice"))
        println(createUser("Bob", 30))
        println(createUser("Charlie", 25, "Shanghai"))
        
        // 构造函数中的默认参数
        val config = AppConfig()
        println("\n默认配置: $config")
        
        val customConfig = AppConfig(host = "example.com", port = 9090)
        println("自定义配置: $customConfig")
        
        println()
    }
    
    // 带默认参数的函数
    fun createUser(name: String, age: Int = 18, city: String = "Unknown"): String {
        return "User($name, $age, $city)"
    }
    
    data class AppConfig(
        val host: String = "localhost",
        val port: Int = 8080,
        val debug: Boolean = false,
        val timeout: Int = 30
    )

    /**
     * 3. 命名参数
     */
    fun demonstrateNamedArguments() {
        println("=== 3. 命名参数 ===")
        
        // 使用命名参数（提高可读性）
        println(buildUrl(
            protocol = "https",
            domain = "example.com",
            port = 443,
            path = "/api/users"
        ))
        
        // 跳过某些默认参数
        println(buildUrl(domain = "test.com", path = "/home"))
        
        // 混合使用位置参数和命名参数
        // 注意：位置参数必须在命名参数之前
        println(buildUrl("http", "localhost", path = "/debug"))
        
        println()
    }
    
    fun buildUrl(
        protocol: String = "https",
        domain: String,
        port: Int = 80,
        path: String = "/"
    ): String {
        return "$protocol://$domain:$port$path"
    }

    /**
     * 4. 可变参数（Vararg）
     */
    fun demonstrateVararg() {
        println("=== 4. 可变参数 ===")
        
        // 传入多个参数
        println("总和: ${sum(1, 2, 3, 4, 5)}")
        println("连接: ${concatStrings("Hello", "World", "Kotlin")}")
        
        // 展开运算符（Spread operator）
        val numbers = intArrayOf(10, 20, 30)
        println("数组展开: ${sum(*numbers)}")
        
        val words = arrayOf("Foo", "Bar", "Baz")
        println("数组展开: ${concatStrings(*words)}")
        
        // 混合使用
        println("混合: ${sum(1, 2, *numbers, 100)}")
        
        println()
    }
    
    fun sum(vararg numbers: Int): Int {
        return numbers.sum()
    }
    
    fun concatStrings(vararg strings: String): String {
        return strings.joinToString(", ")
    }

    /**
     * 5. 函数重载
     */
    fun demonstrateFunctionOverloading() {
        println("=== 5. 函数重载 ===")
        
        println(operate(5, 3))           // 调用 Int 版本
        println(operate(5.5, 3.2))       // 调用 Double 版本
        println(operate("Hello", "World")) // 调用 String 版本
        
        println()
    }
    
    fun operate(a: Int, b: Int): String {
        return "Int: ${a + b}"
    }
    
    fun operate(a: Double, b: Double): String {
        return "Double: ${a + b}"
    }
    
    fun operate(a: String, b: String): String {
        return "String: $a $b"
    }

    /**
     * 6. 扩展函数
     */
    fun demonstrateExtensionFunctions() {
        println("=== 6. 扩展函数 ===")
        
        // 使用扩展函数
        val text = "  Hello World  "
        println("原始: '$text'")
        println("去除空格并大写: '${text.cleanAndUpper()}'")
        
        val number = 42
        println("\n数字: $number")
        println("是否偶数: ${number.isEven()}")
        println("是否奇数: ${number.isOdd()}")
        
        val list = listOf(1, 2, 3, 4, 5)
        println("\n列表: $list")
        println("第二个元素: ${list.secondOrNull()}")
        
        println()
    }
    
    // String 扩展函数
    fun String.cleanAndUpper(): String {
        return this.trim().uppercase()
    }
    
    // Int 扩展函数
    fun Int.isEven(): Boolean {
        return this % 2 == 0
    }
    
    fun Int.isOdd(): Boolean {
        return this % 2 != 0
    }
    
    // List 扩展函数
    fun <T> List<T>.secondOrNull(): T? {
        return if (this.size >= 2) this[1] else null
    }

    /**
     * 7. 局部函数
     */
    fun demonstrateLocalFunctions() {
        println("=== 7. 局部函数 ===")
        
        val result = validateUser("Alice", 25, "alice@example.com")
        println("验证结果: $result")
        
        println()
    }
    
    fun validateUser(name: String, age: Int, email: String): Boolean {
        // 局部函数 - 只在父函数中可见
        fun validateName(name: String): Boolean {
            return name.isNotBlank() && name.length >= 2
        }
        
        fun validateAge(age: Int): Boolean {
            return age in 0..150
        }
        
        fun validateEmail(email: String): Boolean {
            return email.contains("@") && email.contains(".")
        }
        
        // 使用局部函数
        return validateName(name) && validateAge(age) && validateEmail(email)
    }

    /**
     * 8. 尾递归优化
     */
    fun demonstrateTailRecursion() {
        println("=== 8. 尾递归优化 ===")

        println("阶乘 5: ${factorial(5)}")
        println("阶乘 10: ${factorial(10)}")
        
        println("斐波那契 10: ${fibonacci(10)}")
        println("斐波那契 20: ${fibonacci(20)}")
        
        println()
    }
    
    // 尾递归阶乘
    tailrec fun factorial(n: Int, accumulator: Long = 1): Long {
        return if (n <= 1) accumulator
        else factorial(n - 1, n * accumulator)
    }
    
    // 尾递归斐波那契
    tailrec fun fibonacci(n: Int, a: Long = 0, b: Long = 1): Long {
        return when {
            n == 0 -> a
            n == 1 -> b
            else -> fibonacci(n - 1, b, a + b)
        }
    }

    /**
     * 9. 高阶函数和 Lambda
     */
    fun demonstrateHigherOrderFunctions() {
        println("=== 9. 高阶函数和 Lambda ===")
        
        // Lambda 表达式
        val add: (Int, Int) -> Int = { a, b -> a + b }
        println("Lambda 加法: ${add(5, 3)}")
        
        // 将 Lambda 传递给高阶函数
        val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        
        val evenNumbers = numbers.filter { it % 2 == 0 }
        println("偶数: $evenNumbers")
        
        val doubled = numbers.map { it * 2 }
        println("翻倍: $doubled")
        
        val sum = numbers.reduce { acc, num -> acc + num }
        println("总和: $sum")
        
        // 自定义高阶函数
        val result = calculate(10, 5) { a, b -> a * b + 10 }
        println("自定义计算: $result")
        
        // 函数引用
        println("函数引用: ${operateWithFunction(6, 3, ::multiplyValues)}")
        
        // 匿名函数
        val multiply = fun(x: Int, y: Int): Int = x * y
        println("匿名函数: ${multiply(4, 5)}")
        
        println()
    }
    
    // 自定义高阶函数
    fun calculate(a: Int, b: Int, operation: (Int, Int) -> Int): Int {
        return operation(a, b)
    }
    
    fun multiplyValues(a: Int, b: Int): Int = a * b
    
    fun operateWithFunction(a: Int, b: Int, operation: (Int, Int) -> Int): Int {
        return operation(a, b)
    }

    /**
     * 10. 内联函数
     */
    fun demonstrateInlineFunctions() {
        println("=== 10. 内联函数 ===")
        
        val list = listOf(1, 2, 3, 4, 5)
        
        // 内联函数 - 避免 Lambda 创建对象的开销
        val doubled = list.mapInline { it * 2 }
        println("内联 map: $doubled")
        
        // 带 reified 的内联函数
        val mixedList = listOf(1, "hello", 2.5, "world", 3)
        val strings = mixedList.filterIsType<String>()
        println("过滤字符串: $strings")
        
        val integers = mixedList.filterIsType<Int>()
        println("过滤整数: $integers")
        
        // timing 工具函数
        val result = measureTime {
            Thread.sleep(100)  // 模拟耗时操作
            "完成"
        }
        println("执行结果: ${result.first}, 耗时: ${result.second}ms")
        
        println()
    }
    
    // 内联扩展函数
    inline fun <T> Iterable<T>.mapInline(transform: (T) -> T): List<T> {
        val result = mutableListOf<T>()
        for (item in this) {
            result.add(transform(item))
        }
        return result
    }
    
    // 带 reified 的内联函数
    inline fun <reified T> Iterable<*>.filterIsType(): List<T> {
        val result = mutableListOf<T>()
        for (item in this) {
            if (item is T) {
                result.add(item)
            }
        }
        return result
    }
    
    // 测量执行时间
    inline fun measureTime(block: () -> Any): Pair<Any, Long> {
        val startTime = System.currentTimeMillis()
        val result = block()
        val endTime = System.currentTimeMillis()
        return Pair(result, endTime - startTime)
    }

    /**
     * 11. 中缀函数
     */
    fun demonstrateInfixFunctions() {
        println("=== 11. 中缀函数 ===")
        
        // 使用中缀表示法
        val result1 = 5 add 3
        println("5 add 3 = $result1")
        
        val result2 = "Hello" concat " World"
        println("'Hello' concat ' World' = $result2")
        
        // 链式中缀调用
        val result3 = 10 add 5 subtract 3
        println("10 add 5 subtract 3 = $result3")
        
        // Map 的 to 就是中缀函数
        val map = mapOf("key1" to "value1", "key2" to "value2")
        println("Map: $map")
        
        println()
    }
    
    infix fun Int.add(other: Int): Int = this + other
    
    infix fun Int.subtract(other: Int): Int = this - other
    
    infix fun String.concat(other: String): String = this + other

    /**
     * 12. 操作符重载
     */
    fun demonstrateOperatorOverloading() {
        println("=== 12. 操作符重载 ===")
        
        val point1 = Point(1, 2)
        val point2 = Point(3, 4)
        
        println("Point1: $point1")
        println("Point2: $point2")
        
        // 使用 + 运算符
        val sum = point1 + point2
        println("Point1 + Point2 = $sum")
        
        // 使用 - 运算符
        val diff = point2 - point1
        println("Point2 - Point1 = $diff")
        
        // 使用 unaryPlus
        val positive = +point1
        println("+Point1 = $positive")
        
        // 使用 unaryMinus
        val negative = -point1
        println("-Point1 = $negative")
        
        // 使用 *= 运算符
        val point3 = Point(2, 3)
        point3 *= 2
        println("Point(2,3) *= 2 => $point3")
        
        println()
    }
    
    data class Point(var x: Int, var y: Int) {
        // 重载 + 运算符
        operator fun plus(other: Point): Point {
            return Point(x + other.x, y + other.y)
        }
        
        // 重载 - 运算符
        operator fun minus(other: Point): Point {
            return Point(x - other.x, y - other.y)
        }
        
        // 重载一元 + 运算符
        operator fun unaryPlus(): Point {
            return Point(+x, +y)
        }
        
        // 重载一元 - 运算符
        operator fun unaryMinus(): Point {
            return Point(-x, -y)
        }
        
        // 重载 *= 运算符
        operator fun timesAssign(factor: Int) {
            x *= factor
            y *= factor
        }
    }

    /**
     * 13. 函数组合和管道
     */
    fun demonstrateFunctionComposition() {
        println("=== 13. 函数组合和管道 ===")
        
        val text = "  hello world  "
        
        // 传统方式
        val result1 = text.trim().uppercase().reversed()
        println("传统方式: $result1")
        
        // 使用 let 链式调用
        val result2 = text
            .let { it.trim() }
            .let { it.uppercase() }
            .let { it.reversed() }
        println("let 链式: $result2")
        
        // 使用 also 进行调试
        val result3 = text
            .also { println("原始: '$it'") }
            .trim()
            .also { println("trim后: '$it'") }
            .uppercase()
            .also { println("uppercase后: '$it'") }
            .reversed()
        println("also 调试: $result3")
        
        println()
    }

    /**
     * 14. 实际应用场景
     */
    fun practicalExercise() {
        println("=== 14. 实际应用场景 ===")
        
        // 场景1: 数据处理管道
        println("场景1: 数据处理管道")
        
        data class User(val name: String, val age: Int, val email: String)
        
        val users = listOf(
            User("Alice", 25, "alice@example.com"),
            User("Bob", 17, "bob@example.com"),
            User("Charlie", 30, "charlie@example.com"),
            User("David", 15, "david@example.com"),
            User("Eve", 28, "eve@example.com")
        )
        
        // 使用函数式编程处理数据
        val adultUserNames = users
            .filter { it.age >= 18 }                    // 过滤成年人
            .sortedBy { it.name }                       // 按姓名排序
            .map { it.name.uppercase() }                // 转大写
            .take(3)                                    // 取前3个
        
        println("成年用户前3名: $adultUserNames")
        
        println()
        
        // 场景2: 策略模式
        println("场景2: 策略模式")
        
        fun processNumbers(numbers: List<Int>, strategy: (Int) -> Boolean): List<Int> {
            return numbers.filter(strategy)
        }
        
        val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        
        val evens = processNumbers(numbers) { it % 2 == 0 }
        println("偶数: $evens")
        
        val greaterThanFive = processNumbers(numbers) { it > 5 }
        println("大于5: $greaterThanFive")
        
        val perfectSquares = processNumbers(numbers) { 
            val sqrt = Math.sqrt(it.toDouble()).toInt()
            sqrt * sqrt == it
        }
        println("完全平方数: $perfectSquares")
        
        println()
        
        // 场景3: 构建器模式
        println("场景3: 构建器模式")
        
        class EmailBuilder {
            private var to: String = ""
            private var subject: String = ""
            private var body: String = ""
            
            fun to(recipient: String) = apply { this.to = recipient }
            fun subject(title: String) = apply { this.subject = title }
            fun body(content: String) = apply { this.body = content }
            
            fun build(): String {
                return "To: $to\nSubject: $subject\n\n$body"
            }
        }
        
        fun email(init: EmailBuilder.() -> Unit): String {
            return EmailBuilder().apply(init).build()
        }
        
        val emailContent = email {
            to("alice@example.com")
            subject("Meeting Tomorrow")
            body("Hi Alice,\n\nLet's meet at 10 AM tomorrow.\n\nBest regards")
        }
        
        println(emailContent)
        
        println()
        
        // 场景4: 缓存装饰器
        println("场景4: 缓存装饰器")
        
        val cache = mutableMapOf<String, Any>()
        
        fun <T> cached(key: String, compute: () -> T): T {
            @Suppress("UNCHECKED_CAST")
            return cache.getOrPut(key) {  } as T
        }
        
        fun expensiveComputation(n: Int): Int {
            println("  执行耗时计算: $n")
            Thread.sleep(100)  // 模拟耗时
            return n * n
        }
        
        val key1 = "square_5"
        val result1 = cached(key1) { expensiveComputation(5) }
        println("第一次计算结果: $result1")
        
        val result2 = cached(key1) { expensiveComputation(5) }  // 从缓存获取
        println("第二次计算结果（缓存）: $result2")
        
        println("缓存内容: $cache")
        
        println()
    }
}

fun main() {
    val lesson = FunctionLesson()
    
    // 运行所有示例
    lesson.demonstrateBasicFunctions()
    lesson.demonstrateDefaultParameters()
    lesson.demonstrateNamedArguments()
    lesson.demonstrateVararg()
    lesson.demonstrateFunctionOverloading()
    lesson.demonstrateExtensionFunctions()
    lesson.demonstrateLocalFunctions()
    lesson.demonstrateTailRecursion()
    lesson.demonstrateHigherOrderFunctions()
    lesson.demonstrateInlineFunctions()
    lesson.demonstrateInfixFunctions()
    lesson.demonstrateOperatorOverloading()
    lesson.demonstrateFunctionComposition()
    lesson.practicalExercise()
}

