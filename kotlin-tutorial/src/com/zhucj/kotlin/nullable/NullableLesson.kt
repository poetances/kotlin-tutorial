package com.zhucj.kotlin.nullable

import kotlin.math.sign

/**
 * Kotlin 可空类型（Nullable Types）详解教程
 * 
 * ⭐ 这是 Kotlin 最重要、最核心的特性！
 * 
 * 本文件系统讲解：
 * 1. 为什么需要可空类型（Java 的痛点）
 * 2. 可空类型的基本概念
 * 3. 安全调用操作符 ?. 
 * 4. Elvis 运算符 ?: 
 * 5. 非空断言 !!
 * 6. let 函数处理可空值
 * 7. 智能转换（Smart Cast）
 * 8. 可空类型的实际应用场景
 * 9. 常见陷阱和最佳实践
 */
class NullableLesson {
    
    // ========================================
    // 1. 为什么需要可空类型？
    // ========================================
    
    /**
     * Java 的空指针问题演示
     * 
     * NullPointerException (NPE) 是 Java 中最常见的运行时异常
     * Tony Hoare（null 的发明者）称其为"十亿美元的错误"
     */
    fun demonstrateJavaNullProblem() {
        println("===== Java 的空指针问题 =====")
        
        println("""
        Java 中的问题：
        
        // 示例1: 方法返回 null
        public String findUserById(Long id) {
            if (id == 999) return null;  // 用户不存在
            return "张三";
        }
        
        String name = findUserById(999L);
        int length = name.length();  // 💥 NullPointerException!
        
        // 示例2: 对象属性为 null
        public class User {
            private Address address;  // 可能为 null
            
            public String getCity() {
                return address.getCity();  // 💥 如果 address 为 null
            }
        }
        
        // 示例3: 集合中包含 null
        List<String> names = Arrays.asList("Alice", null, "Bob");
        for (String name : names) {
            System.out.println(name.toUpperCase());  // 💥 NPE
        }
        
        统计数据显示：
        - NullPointerException 占 Java 应用崩溃的 70% 以上
        - 开发者需要大量使用 if (obj != null) 检查
        - 代码冗长且容易遗漏检查
        """.trimIndent())
    }
    
    // ========================================
    // 2. Kotlin 可空类型基础
    // ========================================
    
    /**
     * Kotlin 的类型系统：区分可空和非空类型
     * 
     * 核心规则：
     * - String: 非空类型，绝对不能为 null（编译期保证）
     * - String?: 可空类型，可以为 null 或 String
     * 
     * 编译器会强制你处理 null 的情况，从根源上避免 NPE
     */
    fun demonstrateNullableBasics() {
        println("\n===== 可空类型基础 =====")
        
        // ========== 非空类型（默认）==========
        println("--- 非空类型 ---")
        
        val nonNull: String = "Hello"  // ✅ 必须初始化，不能为 null
        println("非空字符串: $nonNull")
        
        // val error: String = null  ❌ 编译错误：Null can not be a value of a non-null type String
        
        // ========== 可空类型（加 ?）==========
        println("\n--- 可空类型 ---")
        
        var nullable: String? = "Hello"  // ✅ 可以为 String 或 null
        println("可空字符串（有值）: $nullable")
        
        nullable = null  // ✅ 允许赋值为 null
        println("可空字符串（null）: $nullable")
        
        // ========== 各种类型的可空版本 ==========
        println("\n--- 各种类型的可空版本 ---")
        
        val nullableInt: Int? = null
        val nullableDouble: Double? = null
        val nullableBoolean: Boolean? = null
        val nullableList: List<String>? = null
        val nullableObject: Any? = null
        
        println("Int?: $nullableInt")
        println("Double?: $nullableDouble")
        println("Boolean?: $nullableBoolean")
        println("List<String>?: $nullableList")
        println("Any?: $nullableObject")
        
        // ========== 可空类型的默认值 ==========
        println("\n--- 可空类型的默认值 ---")
        
        // 类的属性如果是可空类型，可以不初始化（默认为 null）
        class Person {
            var name: String? = null  // ✅ 可空属性，默认 null
            var age: Int = 0          // ❌ 非空属性，必须初始化
        }
        
        val person = Person()
        println("Person name: ${person.name}")  // null
        println("Person age: ${person.age}")    // 0
    }
    
    // ========================================
    // 3. 安全调用操作符 ?. ⭐
    // ========================================
    
    /**
     * 安全调用操作符（Safe Call Operator）
     * 
     * 语法：object?.method()
     * 
     * 行为：
     * - 如果 object 不为 null，调用 method()
     * - 如果 object 为 null，整个表达式返回 null
     * - 不会抛出 NullPointerException
     * 
     * 返回值类型：T?（可空类型）
     */
    fun demonstrateSafeCallOperator() {
        println("\n===== 安全调用操作符 ?. =====")
        
        // ========== 基本用法 ==========
        println("--- 基本用法 ---")
        
        val name: String? = "Kotlin"
        val length = name?.length  // 如果 name 为 null，返回 null
        
        println("'$name' 的长度: $length")  // 6
        
        val nullName: String? = null
        val nullLength = nullName?.length
        
        println("null 的长度: $nullLength")  // null（不会崩溃！）
        
        // ========== 链式安全调用 ==========
        println("\n--- 链式安全调用（非常实用）---")
        
        // 定义嵌套的数据结构
        data class Address(val city: String?, val street: String?)
        data class Company(val name: String?, val address: Address?)
        data class Employee(val name: String, val company: Company?)
        
        // 场景1: 完整的对象链
        val employee1 = Employee(
            "张三",
            Company(
                "科技公司",
                Address("北京", "长安街")
            )
        )
        
        // 传统 Java 方式（需要多层 null 检查）
        // String city = null;
        // if (employee != null) {
        //     Company company = employee.getCompany();
        //     if (company != null) {
        //         Address address = company.getAddress();
        //         if (address != null) {
        //             city = address.getCity();
        //         }
        //     }
        // }
        
        // Kotlin 方式（一行搞定！）
        val city1 = employee1.company?.address?.city
        println("员工城市: $city1")  // 北京
        
        // 场景2: 中间某个对象为 null
        val employee2 = Employee("李四", null)
        val city2 = employee2.company?.address?.city
        println("员工城市: $city2")  // null（不会崩溃！）
        
        // 场景3: 更深的嵌套
        val employee3 = Employee(
            "王五",
            Company("另一公司", null)  // address 为 null
        )
        val city3 = employee3.company?.address?.city
        println("员工城市: $city3")  // null
        
        // ========== 安全调用 + 方法链 ==========
        println("\n--- 安全调用 + 方法链 ---")
        
        val text: String? = "  Hello Kotlin  "
        
        // 可以连续调用多个方法
        val result = text?.trim()?.uppercase()?.length
        println("处理后长度: $result")  // 12
        
        val nullText: String? = null
        val nullResult = nullText?.trim()?.uppercase()?.length
        println("null 处理后: $nullResult")  // null
        
        // ========== 安全调用 + 集合操作 ==========
        println("\n--- 安全调用 + 集合操作 ---")
        
        val list: List<String>? = listOf("apple", "banana", "orange")
        val size = list?.size
        val first = list?.firstOrNull()
        val filtered = list?.filter { it.startsWith("a") }

        println("列表大小: $size")       // 3
        println("第一个元素: $first")    // apple
        println("过滤结果: $filtered")   // [apple]
        
        val nullList: List<String>? = null
        println("null 列表大小: ${nullList?.size}")  // null
        
        // ========== 实际开发场景 ==========
        println("\n--- 实际开发场景 ---")
        
        // 场景1: API 响应处理
        data class UserData(val userName: String?, val email: String?)
        data class ApiResponse(val data: UserData?)

        fun processApiResponse(response: ApiResponse?) {
            val userName = response?.data?.userName
            val email = response?.data?.email
            
            println("用户名: $userName")
            println("邮箱: $email")
        }
        
        val apiResponse = ApiResponse(UserData("张三", "zhangsan@example.com"))
        processApiResponse(apiResponse)
        
        // 场景2: Map 中获取值（可能返回 null）
        val config = mapOf(
            "host" to "localhost",
            "port" to "8080"
        )
        
        val host = config["host"]?.uppercase()  // 安全调用
        val timeout = config["timeout"]?.toInt()  // 键不存在，返回 null
        
        println("\n配置 - Host: $host")      // LOCALHOST
        println("配置 - Timeout: $timeout")  // null
        
        // 场景3: Android 开发（常见场景）
        // textView?.text = "Hello"  // View 可能为 null
        // context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE)
    }
    
    // ========================================
    // 4. Elvis 运算符 ?: ⭐
    // ========================================
    
    /**
     * Elvis 运算符（Elvis Operator）
     * 
     * 语法：expression ?: defaultValue
     * 
     * 行为：
     * - 如果 expression 不为 null，返回 expression 的值
     * - 如果 expression 为 null，返回 defaultValue
     * 
     * 名称来源：看起来像猫王 Elvis Presley 的发型 😎
     * 
     * 用途：提供默认值，避免 null
     */
    fun demonstrateElvisOperator() {
        println("\n===== Elvis 运算符 ?: =====")
        
        // ========== 基本用法 ==========
        println("--- 基本用法 ---")
        
        val name: String? = null
        val displayName = name ?: "匿名用户"  // name 为 null，使用默认值
        
        println("显示名称: $displayName")  // 匿名用户
        
        val actualName: String? = "张三"
        val actualDisplayName = actualName ?: "匿名用户"
        
        println("显示名称: $actualDisplayName")  // 张三
        
        // ========== 提供默认值 ==========
        println("\n--- 提供默认值 ---")
        
        // 数字类型
        val count: Int? = null
        val actualCount = count ?: 0
        println("数量: $actualCount")  // 0
        
        // 布尔类型
        val enabled: Boolean? = null
        val isEnabled = enabled ?: false
        println("是否启用: $isEnabled")  // false
        
        // 集合类型
        val items: List<String>? = null
        val actualItems = items ?: emptyList()
        println("列表: $actualItems")  // []
        
        // ========== 链式 Elvis 运算符 ==========
        println("\n--- 链式 Elvis 运算符 ---")
        
        fun getConfigValue(key: String): String? {
            return when (key) {
                "theme" -> "dark"
                else -> null
            }
        }
        
        // 多级默认值
        val theme = getConfigValue("theme") ?: getConfigValue("default_theme") ?: "light"
        println("主题: $theme")  // dark
        
        val language = getConfigValue("language") ?: getConfigValue("default_language") ?: "en"
        println("语言: $language")  // en
        
        // ========== Elvis + 安全调用 ==========
        println("\n--- Elvis + 安全调用（常用组合）---")
        
        data class User(val name: String?, val age: Int?)
        
        val user: User? = User("张三", null)
        
        // 组合使用：安全调用 + 默认值
        val userName = user?.name ?: "未知用户"
        val userAge = user?.age ?: 0
        
        println("用户名: $userName")  // 张三
        println("年龄: $userAge")     // 0
        
        val nullUser: User? = null
        val nullUserName = nullUser?.name ?: "未知用户"
        println("用户名: $nullUserName")  // 未知用户
        
        // ========== Elvis + throw/return ==========
        println("\n--- Elvis + throw/return（参数验证）---")
        
        fun validateUser(name: String?, age: Int?): User {
            // 如果为 null，抛出异常
            val validName = name ?: throw IllegalArgumentException("姓名不能为空")
            val validAge = age ?: throw IllegalArgumentException("年龄不能为空")
            
            return User(validName, validAge)
        }
        
        try {
            val validUser = validateUser("李四", 25)
            println("有效用户: $validUser")
        } catch (e: IllegalArgumentException) {
            println("验证失败: ${e.message}")
        }
        
        try {
            val invalidUser = validateUser(null, 25)
        } catch (e: IllegalArgumentException) {
            println("验证失败: ${e.message}")  // 姓名不能为空
        }
        
        // return 示例
        fun findUserById(id: Long): User? {
            return if (id == 1L) User("张三", 25) else null
        }
        
        fun getUserInfo(id: Long): String {
            val user = findUserById(id) ?: return "用户不存在"  // 提前返回
            return "用户: ${user.name}, 年龄: ${user.age}"
        }
        
        println(getUserInfo(1L))   // 用户: 张三, 年龄: 25
        println(getUserInfo(2L))   // 用户不存在
        
        // ========== 实际开发场景 ==========
        println("\n--- 实际开发场景 ---")
        
        // 场景1: 配置读取
        data class AppConfig(
            val apiUrl: String?,
            val timeout: Int?,
            val retryCount: Int?
        )
        
        fun createConfig(config: AppConfig?) {
            val apiUrl = config?.apiUrl ?: "https://api.default.com"
            val timeout = config?.timeout ?: 3000
            val retryCount = config?.retryCount ?: 3
            
            println("API URL: $apiUrl")
            println("超时: ${timeout}ms")
            println("重试次数: $retryCount")
        }
        
        createConfig(null)  // 使用默认配置
        createConfig(AppConfig("https://api.example.com", 5000, null))
        
        // 场景2: 表单数据处理
        data class FormInput(
            val username: String?,
            val email: String?,
            val phone: String?
        )
        
        fun processForm(input: FormInput) {
            val validUsername = if (input.username.isNullOrBlank()) {
                "guest_${System.currentTimeMillis()}"  // 生成默认用户名
            } else {
                input.username
            }
            
            val contactEmail = input.email ?: input.phone ?: "无联系方式"
            
            println("用户名: $validUsername")
            println("联系方式: $contactEmail")
        }
        
        processForm(FormInput(null, "test@example.com", null))
        processForm(FormInput("张三", null, "13800138000"))
        
        // 场景3: JSON 解析
        fun parseJsonField(json: Map<String, Any?>, key: String): String {
            return json[key]?.toString() ?: "N/A"
        }
        
        val jsonData = mapOf(
            "name" to "张三",
            "age" to 25,
            "email" to null
        )
        
        println("\nJSON 解析:")
        println("name: ${parseJsonField(jsonData, "name")}")     // 张三
        println("age: ${parseJsonField(jsonData, "age")}")       // 25
        println("email: ${parseJsonField(jsonData, "email")}")   // N/A
        println("phone: ${parseJsonField(jsonData, "phone")}")   // N/A
    }
    
    // ========================================
    // 5. 非空断言 !! ⚠️
    // ========================================
    
    /**
     * 非空断言操作符（Not-Null Assertion Operator）
     * 
     * 语法：variable!!
     * 
     * 行为：
     * - 如果 variable 不为 null，返回 variable 的值（转换为非空类型）
     * - 如果 variable 为 null，抛出 KotlinNullPointerException
     * 
     * ⚠️ 警告：谨慎使用！这会破坏 Kotlin 的空安全机制
     * 
     * 何时使用：
     * - 你 100% 确定变量不为 null（但编译器无法推断）
     * - 测试代码中
     * - 与遗留代码交互时
     * 
     * 最佳实践：尽量避免使用 !!，优先使用 ?. 和 ?: 
     */
    fun demonstrateNotNullAssertion() {
        println("\n===== 非空断言 !! （谨慎使用）=====")
        
        // ========== 基本用法 ==========
        println("--- 基本用法 ---")
        
        val name: String? = "Kotlin"
        val length = name!!.length  // 告诉编译器：我确定不为 null
        
        println("'$name' 的长度: $length")  // 6
        
        // ⚠️ 危险：如果为 null 会抛异常
        try {
            val nullName: String? = null
            val dangerousLength = nullName!!.length  // 💥 KotlinNullPointerException
        } catch (e: KotlinNullPointerException) {
            println("捕获异常: ${e.javaClass.simpleName}")
        }
        
        // ========== 对比：!! vs ?. ==========
        println("\n--- !! vs ?. 对比 ---")
        
        val text: String? = "Hello"
        
        // 方式1: 非空断言（危险）
        try {
            val len1 = text!!.length
            println("使用 !!: $len1")
        } catch (e: Exception) {
            println("!! 失败: ${e.message}")
        }
        
        // 方式2: 安全调用（推荐）
        val len2 = text?.length ?: 0
        println("使用 ?: $len2")
        
        // ========== 何时不得不使用 !! ==========
        println("\n--- 不得不使用 !! 的场景 ---")
        
        // 场景1: 第三方库返回可空类型，但你确定不为 null
        fun thirdPartyLibrary(): String? {
            return "Result"  // 实际上总是返回非 null
        }
        
        // 如果你 100% 确定，可以使用 !!
        val result = thirdPartyLibrary()!!
        println("第三方库结果: $result")
        
        // 但更好的做法是用 ?: 提供默认值
        val saferResult = thirdPartyLibrary() ?: "Default"
        println("更安全的结果: $saferResult")
        
        // 场景2: 单元测试
        fun testNotNullAssertion() {
            val expected: String? = "Expected Value"
            // 在测试中，我们期望它不为 null，如果为 null 说明测试失败
            val actual = expected!!  // 如果为 null，测试应该失败
            println("测试值: $actual")
        }
        
        testNotNullAssertion()
        
        // ========== 更好的替代方案 ==========
        println("\n--- 更好的替代方案 ---")
        
        // ❌ 不推荐
        fun badPractice(value: String?) {
            val result = value!!.uppercase()  // 可能崩溃
        }
        
        // ✅ 推荐方案1: 安全调用 + 默认值
        fun goodPractice1(value: String?) {
            val result = value?.uppercase() ?: "DEFAULT"
            println("方案1: $result")
        }
        
        // ✅ 推荐方案2: let 函数
        fun goodPractice2(value: String?) {
            value?.let {
                println("方案2: ${it.uppercase()}")
            } ?: run {
                println("方案2: DEFAULT")
            }
        }
        
        // ✅ 推荐方案3: 早期返回
        fun goodPractice3(value: String?) {
            if (value == null) {
                println("方案3: DEFAULT")
                return
            }
            println("方案3: ${value.uppercase()}")
        }
        
        goodPractice1(null)
        goodPractice2(null)
        goodPractice3(null)
        
        // ========== 实际建议 ==========
        println("\n--- 实际建议 ---")
        println("""
        使用 !! 的原则：
        
        1. 🚫 能不用就不用
        2. ⚠️ 如果必须用，添加注释说明为什么确定不为 null
        3. ✅ 优先使用以下替代方案：
           - 安全调用 ?. 
           - Elvis 运算符 ?: 
           - let 函数
           - 早期返回
           - requireNotNull() / checkNotNull()
        
        4. 🔍 Code Review 时重点关注 !! 的使用
        """.trimIndent())
    }
    
    // ========================================
    // 6. let 函数处理可空值 ⭐
    // ========================================
    
    /**
     * let 函数：作用域函数的一种
     * 
     * 语法：variable?.let { ... }
     * 
     * 行为：
     * - 如果 variable 不为 null，执行 lambda 表达式
     * - lambda 中的 it 代表非空的 variable
     * - 如果 variable 为 null，不执行 lambda
     * 
     * 优势：
     * - 避免多次使用 ?. 
     * - 将可空值转换为非空值进行处理
     * - 代码更清晰、更简洁
     */
    fun demonstrateLetFunction() {
        println("\n===== let 函数处理可空值 =====")
        
        // ========== 基本用法 ==========
        println("--- 基本用法 ---")
        
        val name: String? = "Kotlin"
        
        // 只在非空时执行
        name?.let {
            // it 是非空的 String 类型
            println("名字: $it")
            println("长度: ${it.length}")
            println("大写: ${it.uppercase()}")
        }
        
        val nullName: String? = null
        nullName?.let {
            println("这行不会执行")
        } ?: run {
            println("名字为空")  // 为空时执行
        }
        
        // ========== 避免重复的安全调用 ==========
        println("\n--- 避免重复的安全调用 ---")
        
        data class User(val name: String?, val age: Int?)
        
        val user: User? = User("张三", 25)
        
        // ❌ 不好：重复使用 ?. 
        println("用户名: ${user?.name}")
        println("用户名长度: ${user?.name?.length}")
        println("用户名大写: ${user?.name?.uppercase()}")
        
        // ✅ 好：使用 let，只检查一次
        user?.let {
            println("用户名: ${it.name}")
            println("用户名长度: ${it.name?.length}")
            println("用户名大写: ${it.name?.uppercase()}")
        }
        
        // ========== let 的返回值 ==========
        println("\n--- let 的返回值 ---")
        
        val text: String? = "  hello  "
        
        // let 可以返回一个值
        val result = text?.let {
            it.trim().uppercase()
        } ?: "DEFAULT"
        
        println("处理结果: $result")  // HELLO
        
        // 实际应用场景：转换可空值
        fun formatUserName(name: String?): String {
            return name?.let {
                it.trim().capitalize()
            } ?: "Anonymous"
        }
        
        println("格式化: ${formatUserName("  john  ")}")  // John
        println("格式化: ${formatUserName(null)}")        // Anonymous
        
        // ========== 链式调用 ==========
        println("\n--- 链式调用 ---")
        
        val email: String? = "USER@EXAMPLE.COM"
        
        val processedEmail = email?.let {
            it.lowercase().trim()
        }?.let {
            if (it.contains("@")) it else null
        }
        
        println("处理后邮箱: $processedEmail")  // user@example.com
        
        // ========== 实际开发场景 ==========
        println("\n--- 实际开发场景 ---")
        
        // 场景1: 数据库查询
        data class Product(val id: Long, val name: String, val price: Double)
        
        fun findProductById(id: Long): Product? {
            return if (id == 1L) Product(1L, "iPhone", 7999.0) else null
        }
        
        fun displayProduct(id: Long) {
            findProductById(id)?.let { product ->
                println("商品: ${product.name}")
                println("价格: ¥${product.price}")
                println("折扣价: ¥${product.price * 0.9}")
            } ?: run {
                println("商品不存在")
            }
        }
        
        displayProduct(1L)
        displayProduct(2L)
        
        // 场景2: 网络请求
        data class ApiResponse(val code: Int, val message: String?, val data: String?)
        
        fun handleResponse(response: ApiResponse?) {
            response?.let {
                when (it.code) {
                    200 -> {
                        println("请求成功")
                        it.data?.let { data ->
                            println("数据: $data")
                        }
                    }
                    404 -> println("资源未找到")
                    500 -> println("服务器错误: ${it.message}")
                    else -> println("未知错误: ${it.code}")
                }
            } ?: run {
                println("网络请求失败")
            }
        }
        
        handleResponse(ApiResponse(200, "成功", "{\"user\":\"张三\"}"))
        handleResponse(ApiResponse(404, "Not Found", null))
        handleResponse(null)
        
        // 场景3: 可选参数处理
        fun createUser(
            name: String,
            email: String? = null,
            phone: String? = null,
            age: Int? = null
        ) {
            println("创建用户: $name")
            
            email?.let {
                println("  邮箱: $it")
            }
            
            phone?.let {
                println("  手机: $it")
            }
            
            age?.let {
                println("  年龄: $it")
            }
            
            if (email == null && phone == null) {
                println("  警告: 没有联系方式")
            }
        }
        
        createUser("张三", email = "zhangsan@example.com", age = 25)
        createUser("李四", phone = "13800138000")
        createUser("王五")
    }
    
    // ========================================
    // 7. 智能转换（Smart Cast）⭐
    // ========================================
    
    /**
     * 智能转换（Smart Cast）
     * 
     * Kotlin 编译器会自动跟踪变量的 null 检查
     * 在检查之后，自动将可空类型转换为非空类型
     * 
     * 无需手动类型转换，编译器帮你完成！
     */
    fun demonstrateSmartCast() {
        println("\n===== 智能转换（Smart Cast）=====")
        
        // ========== 基本用法 ==========
        println("--- 基本用法 ---")
        
        var name: String? = "Kotlin"
        
        // 检查是否为 null
        if (name != null) {
            // 在这个块内，name 自动转换为 String（非空类型）
            // 不需要使用 ?. 或 !!
            println("名字: $name")
            println("长度: ${name.length}")  // ✅ 直接访问，不需要 ?.length
            println("大写: ${name.uppercase()}")  // ✅ 直接调用方法
        }
        
        // ========== 多种检查方式 ==========
        println("\n--- 多种检查方式 ---")
        
        // 方式1: != null 检查
        fun example1(value: String?) {
            if (value != null) {
                println("长度: ${value.length}")  // Smart Cast 生效
            }
        }
        
        // 方式2: == null 检查（反向）
        fun example2(value: String?) {
            if (value == null) {
                println("值为空")
                return
            }
            // 这里 value 已经被 Smart Cast 为 String
            println("长度: ${value.length}")
        }
        
        // 方式3: is 检查（类型检查）
        fun example3(value: Any?) {
            if (value is String) {
                // value 被 Smart Cast 为 String
                println("字符串长度: ${value.length}")
            }
        }
        
        // 方式4: when 表达式
        fun example4(value: Any?) {
            when (value) {
                is String -> println("字符串: ${value.length}")
                is Int -> println("整数: $value")
                null -> println("空值")
            }
        }
        
        example1("Hello")
        example2("World")
        example3("Test")
        example4("Kotlin")
        
        // ========== Smart Cast 的限制 ==========
        println("\n--- Smart Cast 的限制 ---")
        
        // ❌ 限制1: var 变量可能被其他线程修改
        var mutableValue: String? = "Hello"
        
        if (mutableValue != null) {
            // mutableValue.length  // ❌ 编译错误：Smart Cast 不可用
            // 因为 mutableValue 可能在检查和使用时被其他线程修改
            
            // ✅ 解决方案1: 使用 val
            val immutableValue = mutableValue
            if (immutableValue != null) {
                println("长度: ${immutableValue.length}")  // ✅ 可以
            }
            
            // ✅ 解决方案2: 使用 ?. 
            println("长度: ${mutableValue?.length}")  // ✅ 可以
        }
        
        // ❌ 限制2: 开放属性（open property）可能被重写
        open class Base {
            open val value: String? = "Base"
        }
        
        class Derived : Base() {
            override val value: String?
                get() = if (Math.random() > 0.5) "Derived" else null
        }
        
        val obj: Base = Derived()
        if (obj.value != null) {
            // println(obj.value.length)  // ❌ 编译错误
            println(obj.value?.length)   // ✅ 使用安全调用
        }
        
        // ========== 实际开发场景 ==========
        println("\n--- 实际开发场景 ---")
        
        // 场景1: 参数验证
        fun processInput(input: String?) {
            if (input == null) {
                println("输入为空")
                return
            }
            
            // 这里 input 已经是 String 类型
            if (input.isEmpty()) {
                println("输入为空字符串")
                return
            }
            
            println("处理输入: ${input.uppercase()}")
        }
        
        processInput("Hello")
        processInput(null)
        
        // 场景2: 类型判断和处理
        fun handleValue(value: Any?) {
            when (value) {
                is String -> {
                    // value 是 String 类型
                    println("字符串: '${value.uppercase()}'")
                }
                is Int -> {
                    // value 是 Int 类型
                    println("整数: ${value * 2}")
                }
                is List<*> -> {
                    // value 是 List 类型
                    println("列表大小: ${value.size}")
                }
                null -> {
                    println("空值")
                }
                else -> {
                    println("未知类型: ${value::class.simpleName}")
                }
            }
        }
        
        handleValue("Hello")
        handleValue(42)
        handleValue(listOf(1, 2, 3))
        handleValue(null)
        
        // 场景3: 可选字段处理
        data class Profile(
            val name: String,
            val bio: String?,
            val website: String?,
            val location: String?
        )
        
        fun displayProfile(profile: Profile) {
            println("姓名: ${profile.name}")
            
            // 使用 Smart Cast
            if (profile.bio != null) {
                println("简介: ${profile.bio}")
            }
            
            if (profile.website != null) {
                println("网站: ${profile.website}")
            }
            
            if (profile.location != null) {
                println("位置: ${profile.location}")
            }
        }
        
        displayProfile(Profile("张三", "开发者", "https://example.com", "北京"))
    }
    
    // ========================================
    // 8. 可空类型的工具函数
    // ========================================
    
    /**
     * Kotlin 标准库提供的可空类型工具函数
     */
    fun demonstrateUtilityFunctions() {
        println("\n===== 可空类型工具函数 =====")
        
        // ========== isNullOrEmpty() ==========
        println("--- isNullOrEmpty() ---")
        
        val str1: String? = null
        val str2: String? = ""
        val str3: String? = "Hello"
        
        println("null.isNullOrEmpty(): ${str1.isNullOrEmpty()}")    // true
        println("\"\".isNullOrEmpty(): ${str2.isNullOrEmpty()}")    // true
        println("\"Hello\".isNullOrEmpty(): ${str3.isNullOrEmpty()}") // false
        
        // ========== isNullOrBlank() ==========
        println("\n--- isNullOrBlank() ---")
        
        val blank: String? = "   "
        println("null.isNullOrBlank(): ${str1.isNullOrBlank()}")      // true
        println("\"\".isNullOrBlank(): ${str2.isNullOrBlank()}")      // true
        println("\"   \".isNullOrBlank(): ${blank.isNullOrBlank()}")  // true
        println("\"Hello\".isNullOrBlank(): ${str3.isNullOrBlank()}") // false
        
        // 实际应用场景：表单验证
        fun validateUsername(username: String?): Boolean {
            return !username.isNullOrBlank() && username.length >= 3
        }
        
        println("\n用户名验证:")
        println("null: ${validateUsername(null)}")              // false
        println("\"\": ${validateUsername("")}")               // false
        println("\"ab\": ${validateUsername("ab")}")           // false
        println("\"abc\": ${validateUsername("abc")}")         // true
        
        // ========== orEmpty() ==========
        println("\n--- orEmpty() ---")
        
        // 如果为 null，返回空字符串；否则返回原字符串
        val nullableStr: String? = null
        val safeStr = nullableStr.orEmpty()
        
        println("null.orEmpty(): '$safeStr'")  // ''
        println("长度: ${safeStr.length}")      // 0
        
        val nonNullStr: String? = "Hello"
        println("\"Hello\".orEmpty(): '${nonNullStr.orEmpty()}'")  // 'Hello'
        
        // 对集合也适用
        val nullList: List<String>? = null
        val safeList = nullList.orEmpty()
        println("null 列表大小: ${safeList.size}")  // 0
        
        // ========== requireNotNull() / checkNotNull() ==========
        println("\n--- requireNotNull() / checkNotNull() ---")
        
        // requireNotNull: 用于参数验证，抛出 IllegalArgumentException
        try {
            fun processName(name: String?) {
                val validName = requireNotNull(name) { "姓名不能为空" }
                println("处理姓名: $validName")
            }
            
            processName("张三")
            processName(null)  // 抛出异常
        } catch (e: IllegalArgumentException) {
            println("捕获异常: ${e.message}")
        }
        
        // checkNotNull: 用于状态检查，抛出 IllegalStateException
        try {
            var state: String? = null
            
            fun doSomething() {
                state = "Initialized"
                val currentState = checkNotNull(state) { "状态未初始化" }
                println("当前状态: $currentState")
            }
            
            doSomething()
        } catch (e: IllegalStateException) {
            println("捕获异常: ${e.message}")
        }
        
        // ========== takeIf() / takeUnless() ==========
        println("\n--- takeIf() / takeUnless() ---")
        
        val number: Int? = 42
        
        // takeIf: 如果满足条件，返回原值；否则返回 null
        val evenNumber = number?.takeIf { it % 2 == 0 }
        println("偶数: $evenNumber")  // 42
        
        val oddNumber = number?.takeIf { it % 2 != 0 }
        println("奇数: $oddNumber")  // null
        
        // takeUnless: 如果不满足条件，返回原值；否则返回 null
        val notZero = number?.takeUnless { it == 0 }
        println("非零: $notZero")  // 42
        
        // 实际应用场景：过滤无效值
        fun parseAndValidate(input: String?): Int? {
            return input?.toIntOrNull()?.takeIf { it > 0 }
        }
        
        println("\n解析验证:")
        println("\"42\": ${parseAndValidate("42")}")      // 42
        println("\"-5\": ${parseAndValidate("-5")}")      // null
        println("\"abc\": ${parseAndValidate("abc")}")    // null
        println("null: ${parseAndValidate(null)}")        // null
    }
    
    // ========================================
    // 9. 综合实战演练
    // ========================================
    
    /**
     * 综合实战：用户管理系统
     * 
     * 综合运用所有可空类型特性
     */
    fun practicalExercise() {
        println("\n" + "=".repeat(50))
        println("综合实战：用户管理系统")
        println("=".repeat(50))
        
        // 数据模型
        data class Address(
            val street: String?,
            val city: String?,
            val zipCode: String?
        )
        
        data class UserProfile(
            val userId: Long,
            val username: String?,
            val email: String?,
            val phone: String?,
            val age: Int?,
            val address: Address?,
            val isActive: Boolean
        )
        
        // 模拟数据库
        val userDatabase = mutableMapOf<Long, UserProfile>(
            1L to UserProfile(
                1L, "zhangsan", "zhangsan@example.com",
                "13800138000", 25,
                Address("长安街", "北京", "100000"),
                true
            ),
            2L to UserProfile(
                2L, "lisi", "lisi@example.com",
                null, 30,
                null,  // 没有地址
                true
            ),
            3L to UserProfile(
                3L, "wangwu", null,
                "13900139000", null,  // 没有年龄
                Address("南京路", "上海", "200000"),
                false  // 非活跃用户
            )
        )
        
        // 功能1: 查找用户
        fun findUser(userId: Long): UserProfile? {
            return userDatabase[userId]
        }
        
        // 功能2: 显示用户信息（综合运用可空类型）
        fun displayUserInfo(userId: Long) {
            println("\n--- 用户 ID: $userId ---")
            
            val user = findUser(userId)
            
            // 使用 let 处理可空用户
            user?.let {
                // 用户名
                val displayName = it.username ?: "未知用户"
                println("用户名: $displayName")
                
                // 联系方式（至少需要一个）
                val contact = it.email ?: it.phone ?: "无联系方式"
                println("联系方式: $contact")
                
                // 年龄
                val ageInfo = it.age?.let { age ->
                    "$age 岁"
                } ?: "未填写"
                println("年龄: $ageInfo")
                
                // 地址（可能多层嵌套为 null）
                val city = it.address?.city ?: "未知城市"
                val street = it.address?.street ?: "未知街道"
                println("地址: $city $street")
                
                // 完整地址
                it.address?.let { addr ->
                    val fullAddress = listOf(
                        addr.street,
                        addr.city,
                        addr.zipCode
                    ).filterNotNull().joinToString(" ")
                    
                    if (fullAddress.isNotEmpty()) {
                        println("完整地址: $fullAddress")
                    }
                }
                
                // 状态
                val status = if (it.isActive) "活跃" else "禁用"
                println("状态: $status")
                
            } ?: run {
                println("用户不存在")
            }
        }
        
        // 功能3: 更新用户信息
        fun updateUser(
            userId: Long,
            newEmail: String? = null,
            newPhone: String? = null,
            newAge: Int? = null
        ): Boolean {
            val user = userDatabase[userId] ?: return false
            
            // 创建新的用户对象（Kotlin 推荐使用不可变对象）
            val updatedUser = user.copy(
                email = newEmail ?: user.email,
                phone = newPhone ?: user.phone,
                age = newAge ?: user.age
            )
            
            userDatabase[userId] = updatedUser
            return true
        }
        
        // 功能4: 搜索用户
        fun searchUsers(query: String?): List<UserProfile> {
            if (query.isNullOrBlank()) {
                return userDatabase.values.toList()
            }
            
            return userDatabase.values.filter { user ->
                user.username?.contains(query, ignoreCase = true) == true ||
                user.email?.contains(query, ignoreCase = true) == true ||
                user.phone?.contains(query) == true
            }
        }
        
        // 功能5: 统计信息
        fun printStatistics() {
            println("\n--- 统计信息 ---")
            
            val users = userDatabase.values
            
            // 总用户数
            println("总用户数: ${users.size}")
            
            // 活跃用户数
            val activeCount = users.count { it.isActive }
            println("活跃用户: $activeCount")
            
            // 有邮箱的用户数
            val emailCount = users.count { !it.email.isNullOrEmpty() }
            println("有邮箱: $emailCount")
            
            // 平均年龄（只计算填写了年龄的用户）
            val averageAge = users
                .mapNotNull { it.age }  // 过滤掉 null
                .average()
            
            if (!averageAge.isNaN()) {
                println("平均年龄: ${String.format("%.1f", averageAge)}")
            } else {
                println("平均年龄: 无数据")
            }
            
            // 有地址的用户
            val withAddress = users.count { it.address != null }
            println("有地址: $withAddress")
        }
        
        // 执行演示
        println("\n【1】显示所有用户信息")
        userDatabase.keys.forEach { displayUserInfo(it) }
        
        println("\n【2】查找不存在的用户")
        displayUserInfo(999L)
        
        println("\n【3】更新用户信息")
        updateUser(1L, newEmail = "new_zhangsan@example.com", newAge = 26)
        displayUserInfo(1L)
        
        println("\n【4】搜索用户")
        val searchResults = searchUsers("zhang")
        println("搜索结果: ${searchResults.map { it.username }}")
        
        println("\n【5】统计信息")
        printStatistics()
    }
    
    // ========================================
    // 10. 常见陷阱和最佳实践
    // ========================================
    
    /**
     * 常见陷阱和最佳实践总结
     */
    fun bestPractices() {
        println("\n" + "=".repeat(50))
        println("最佳实践总结")
        println("=".repeat(50))
        
        println("""
        
        ✅ 推荐做法：
        
        1. 优先使用 val（不可变），减少 null 的可能性
        
        2. 合理使用可空类型
           - 只有在确实可能为 null 时才使用 ?
           - 不要滥用可空类型
        
        3. 使用安全调用 ?. 和 Elvis ?: 
           - 这是处理 null 的首选方式
           - 代码简洁且安全
        
        4. 利用 let 函数
           - 避免重复的安全调用
           - 让代码更清晰
        
        5. 善用智能转换
           - 减少不必要的 ?. 
           - 让代码更像普通代码
        
        6. 使用标准库函数
           - isNullOrEmpty(), isNullOrBlank()
           - orEmpty(), requireNotNull()
        
        7. 设计 API 时
           - 尽量避免返回 null
           - 使用 Optional 模式或 Result 类型
           - 提供合理的默认值
        
        
        ❌ 避免做法：
        
        1. 避免使用 !!
           - 除非你 100% 确定
           - 即使如此，也要添加注释
        
        2. 不要过度使用可空类型
           - 如果能提供默认值，就不要用可空类型
           - 例如：List<String> 比 List<String>? 更好
        
        3. 不要忽略 null 检查
           - 编译器警告要认真对待
           - 不要 suppress 警告
        
        4. 避免深层嵌套的 null 检查
           - 使用 let、also 等作用域函数
           - 提取方法，降低复杂度
        
        
        📊 选择指南：
        
        场景                          推荐方式
        ─────────────────────────────────────────
        简单的 null 检查              ?. 或 ?: 
        需要执行多步操作              ?.let { }
        需要提供默认值                ?: defaultValue
        确定不为 null（编译器不知）   requireNotNull()
        调试/测试                     !!（谨慎）
        参数验证                      requireNotNull() { msg }
        状态检查                      checkNotNull() { msg }
        集合可能为 null               .orEmpty()
        字符串可能为空或空白          .isNullOrBlank()
        
        
        🎯 核心原则：
        
        1. 让 null 显式化
           - 使用 ? 明确标识可能为 null
           - 不要隐藏 null 的可能性
        
        2. 在边界处理 null
           - 在 API 入口处验证
           - 在数据源出口处处理
        
        3. 尽早处理 null
           - 不要传递 null
           - 转换为有意义的值或抛出异常
        
        4. 文档化 null 行为
           - 注释说明什么情况下返回 null
           - KDoc 中标注 @return 可能为 null
        
        """.trimIndent())
    }
}

/**
 * 主函数 - 运行所有演示
 */
fun main() {
    val lesson = NullableLesson()
    
    // 依次运行所有演示
    lesson.demonstrateJavaNullProblem()
    lesson.demonstrateNullableBasics()
    lesson.demonstrateSafeCallOperator()
    lesson.demonstrateElvisOperator()
    lesson.demonstrateNotNullAssertion()
    lesson.demonstrateLetFunction()
    lesson.demonstrateSmartCast()
    lesson.demonstrateUtilityFunctions()
    lesson.practicalExercise()
    lesson.bestPractices()
    
    println("\n" + "=".repeat(50))
    println("✅ 可空类型教程完成！")
    println("=".repeat(50))
}