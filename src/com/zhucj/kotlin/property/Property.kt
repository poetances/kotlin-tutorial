package com.zhucj.kotlin.property

/**
 * Kotlin 属性详解教程
 * 
 * ============================================================================
 * 【核心原理】
 * ============================================================================
 * 
 * 1. 属性的本质
 *    - Kotlin 的属性是字段（Field）与访问器（Getter/Setter）的统一抽象。
 *    - 声明一个属性时，编译器会自动生成幕后字段（Backing Field）和对应的访问器方法。
 *    - 这种设计消除了 Java 中大量冗余的 Getter/Setter 样板代码。
 * 
 * 2. 与 Java 的关键区别
 * 
 *    ┌─────────────────┬──────────────────────┬──────────────────────┐
 *    │     特性         │      Java            │      Kotlin          │
 *    ├─────────────────┼──────────────────────┼──────────────────────┤
 *    │ 声明方式         │ private int age;     │ var age: Int = 0     │
 *    │                  │ public void set...   │                      │
 *    │                  │ public int get...    │                      │
 *    │ 只读属性         │ private final int x; │ val x: Int = 10      │
 *    │                  │ + getter             │ (只有 getter)        │
 *    │ 自定义逻辑       │ 在 setter 方法内写   │ set(value) { ... }   │
 *    │ 幕后字段标识     │ this.field           │ field 关键字         │
 *    │ 延迟初始化       │ 双重检查锁/静态内部类│ lateinit / by lazy   │
 *    │ 编译期常量       │ public static final  │ const val            │
 *    │ 委托属性         │ 需手动实现代理模式   │ by 关键字原生支持    │
 *    │ 扩展属性         │ 不支持               │ 原生支持             │
 *    └─────────────────┴──────────────────────┴──────────────────────┘
 * 
 * 3. 幕后字段 (Backing Fields)
 *    - 当你需要自定义 getter/setter 且需要存储实际值时，Kotlin 会自动生成幕后字段。
 *    - 使用 `field` 标识符在访问器中引用它。
 *    - 如果属性逻辑完全由 getter/setter 决定且不存储状态（如计算属性），则不会生成幕后字段。
 * 
 * 4. 委托属性 (Delegated Properties)
 *    - 利用 `by` 关键字将属性的存取逻辑交给另一个对象处理。
 *    - 常见委托：`lazy` (懒加载), `observable` (监听变化), `vetoable` (校验拦截), `map` (从 Map 取值)。
 *    - 原理：编译器会生成一个持有委托对象的字段，并在属性的访问器中调用委托对象的 `getValue`/`setValue`。
 * 
 * 5. 扩展属性 (Extension Properties)
 *    - 允许为现有类添加新的属性语法，但**不能存储状态**（没有幕后字段）。
 *    - 本质上是通过 getter/setter 实现的，每次访问都会执行对应的逻辑。
 * 
 * ============================================================================
 */
class Property {

    // ========================
    // 1. 基本属性声明 (Basic Properties)
    // ========================

    /**
     * 演示基本属性的声明与使用
     */
    fun demonstrateBasicProperties() {
        println("=== 1. 基本属性声明 ===")
        
        // 可变属性 (var): 拥有 getter 和 setter
        var name: String = "Kotlin"
        println("初始名称: $name")
        name = "Kotlin Updated" // 调用 setter
        println("修改后名称: $name") // 调用 getter
        
        // 只读属性 (val): 只有 getter，没有 setter
        val version: Double = 1.9
        println("当前版本: $version")
        // version = 2.0 ❌ 编译错误：Val cannot be reassigned
        
        println()
    }

    // ========================
    // 2. 自定义 Getter 和 Setter
    // ========================

    /**
     * 演示如何自定义属性的访问逻辑
     */
    fun demonstrateCustomAccessors() {
        println("=== 2. 自定义 Getter 和 Setter ===")
        
        // 自定义 Setter: 可以在设置值时进行校验或触发副作用
        val validator = PropertyValidator()
        try {
            validator.score = 150 // 抛出异常
        } catch (e: IllegalArgumentException) {
            println("捕获异常: ${e.message}")
        }
        validator.score = 95
        println("有效分数: ${validator.score}")
        
        // 自定义 Getter: 可以在获取值时执行逻辑（计算属性）
        val rect = Rectangle(5, 10)
        println("矩形面积 (计算属性): ${rect.area}")
        
        println()
    }
    
    class PropertyValidator {
        var score: Int = 0
            set(value) {
                if (value in 0..100) {
                    field = value // field 是 Kotlin 提供的幕后字段标识符
                } else {
                    throw IllegalArgumentException("分数必须在 0 到 100 之间")
                }
            }
    }
    
    class Rectangle(val width: Int, val height: Int) {
        // 计算属性：没有幕后字段，每次访问都重新计算
        val area: Int
            get() = width * height
    }

    // ========================
    // 3. 延迟初始化 (Lateinit & Lazy)
    // ========================

    /**
     * 演示两种延迟初始化的场景
     */
    fun demonstrateDelayedInitialization() {
        println("=== 3. 延迟初始化 ===")
        
        // lateinit: 用于非空类型的延迟赋值（常用于依赖注入或单元测试）
        val service = LateinitDemo()
        if (!::service.databaseService.isInitialized) {
            println("数据库服务尚未初始化")
            service.databaseService = "MySQL Connected"
        }
        println("数据库服务: ${service.databaseService}")
        
        // lazy: 懒加载委托，线程安全，只在第一次访问时计算
        val loader = LazyLoader()
        println("首次访问重型资源: ${loader.heavyResource}")
        println("再次访问重型资源: ${loader.heavyResource}") // 不再打印日志
        
        println()
    }
    
    class LateinitDemo {
        lateinit var databaseService: String
    }
    
    class LazyLoader {
        val heavyResource: String by lazy {
            println("正在加载重型资源...")
            "资源加载完成"
        }
    }

    // ========================
    // 4. 常量与静态成员 (Constants)
    // ========================

    /**
     * 演示常量的定义
     */
    fun demonstrateConstants() {
        println("=== 4. 常量与静态成员 ===")
        println("应用名称: ${APP_NAME}")
        println("最大尺寸: ${MAX_SIZE}")
        println()
    }
    
    companion object {
        const val MAX_SIZE = 100
        const val APP_NAME = "KotlinTutorial"
    }

    // ========================
    // 5. 委托属性 (Delegated Properties)
    // ========================

    /**
     * 演示委托属性的强大功能
     */
    fun demonstrateDelegatedProperties() {
        println("=== 5. 委托属性 ===")
        
        // 观察属性变化 (Observable)
        val observer = PropertyObserver()
        observer.userName = "张三"
        observer.userName = "李四"
        
        // 使用 Map 作为属性的委托 (常用于解析 JSON 或配置)
        val userMap = mapOf("name" to "王五", "age" to 30)
        val user = User(userMap)
        println("Map 委托用户: ${user.name}, 年龄: ${user.age}")
        
        println()
    }
    
    class PropertyObserver {
        var userName: String by Delegates.observable("初始值") { property, oldValue, newValue ->
            println("${property.name} 从 '$oldValue' 变为 '$newValue'")
        }
    }
    
    class User(val map: Map<String, Any?>) {
        val name: String by map
        val age: Int by map
    }

    // ========================
    // 6. 扩展属性 (Extension Properties)
    // ========================

    /**
     * 演示扩展属性的使用
     */
    fun demonstrateExtensionProperties() {
        println("=== 6. 扩展属性 ===")
        
        val text = "Hello Kotlin"
        println("'$text' 的最后一个字符是: ${text.lastChar}")
        
        println()
    }
}

// 定义一个扩展属性示例（必须在类外部定义）
val String.lastChar: Char
    get() = this[this.length - 1]

fun main() {
    val p = Property()
    
    // 依次运行所有演示
    p.demonstrateBasicProperties()
    p.demonstrateCustomAccessors()
    p.demonstrateDelayedInitialization()
    p.demonstrateConstants()
    p.demonstrateDelegatedProperties()
    p.demonstrateExtensionProperties()
    
    println("=".repeat(50))
    println("✅ 属性演示完成！")
    println("=".repeat(50))
}