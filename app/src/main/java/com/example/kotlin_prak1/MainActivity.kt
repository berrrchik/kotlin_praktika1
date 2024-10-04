package com.example.kotlin_prak
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.kotlin_prak1.FragmentOne
import com.example.kotlin_prak1.R
import java.text.SimpleDateFormat
import java.util.Date

// MainActivity.kt
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Загрузка первого фрагмента при старте приложения
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, FragmentOne())
                .addToBackStack(null)
                .commit()
        }

//        // Кнопка для навигации с помощью Navigation API
//        findViewById<Button>(R.id.btnNavigationApi).setOnClickListener {
//            setupNavigation()
//        }
    }

    // Метод для ручной навигации
    fun navigateToFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null)
            .commit()
    }

//    // Настройка навигации с использованием Navigation API
//    private fun setupNavigation() {
//        val navController = findNavController(R.id.nav_host_fragment)
//        navController.navigate(R.id.action_global_fragmentOne) // Переход к первому фрагменту
//    }
}





// Класс, представляющий один расход
class Expense(val amount: Double, val category: String, val date: Date) {

    constructor(amount: Double, category: String) : this(amount, category, Date()) {
        println("текущая дата")
    }


    // Метод для вывода информации о расходе
    fun displayExpense() {
        val dateFormat = SimpleDateFormat("dd-MM-yyyy")
        println("Сумма: $amount, Категория: $category, Дата: ${dateFormat.format(date)}")
    }
}

// Класс для управления списком всех расходов
class ExpenseManager {
    private val expenses = mutableListOf<Expense>()

    // Метод для добавления нового расхода
    fun addExpense(expense: Expense) {
        expenses.add(expense)
        println("Расход добавлен: ${expense.category}, Сумма: ${expense.amount}")
    }

    val hasExpense: (Double) -> Boolean = { amount ->
        expenses.any { it.amount == amount }
    }
    val res : (Double) -> Boolean = fun(amount: Double) : Boolean {
        return false
    }


    // Метод для вывода всех расходов
    fun displayAllExpenses() {
        if (expenses.isEmpty()) {
            println("Список расходов пуст.")
        } else {
            println("Все расходы:")
            for (expense in expenses) {
                expense.displayExpense()
            }
        }
    }

    // Метод для подсчета суммы расходов по каждой категории
    fun displayTotalByCategory() {
        if (expenses.isEmpty()) {
            println("Нет данных для подсчета.")
            return
        }

        val categoryTotals = mutableMapOf<String, Double>()

        for (expense in expenses) {
            categoryTotals[expense.category] = categoryTotals.getOrDefault(expense.category, 0.0) + expense.amount
        }

        println("Сумма расходов по категориям:")
        for ((category, total) in categoryTotals) {
            println("$category: $total")
        }
    }
}

fun main() {
    val expenseManager = ExpenseManager()

    while (true) {
        println("\n1. Добавить расход")
        println("2. Показать все расходы")
        println("3. Показать сумму по категориям")
        println("4. Выйти")
        print("Выберите действие: ")

        when (readlnOrNull()?.toIntOrNull()) {
            1 -> {
                print("Введите сумму расхода: ")
                val amount = readlnOrNull()?.toDoubleOrNull()
                if (amount == null || amount <= 0) {
                    println("Неверная сумма.")
                    continue
                }

                print("Введите категорию расхода: ")
                val category = readlnOrNull() ?: ""

                print("Введите дату расхода (dd-MM-yyyy): ")
                val dateInput = readlnOrNull()
                val dateFormat = SimpleDateFormat("dd-MM-yyyy")
                val date = try {
                    dateFormat.parse(dateInput.toString())
                } catch (e: Exception) {
                    println("Неверный формат даты.")
                    continue
                }

                val expense = Expense(amount, category, date)
                expenseManager.addExpense(expense)
            }
            2 -> expenseManager.displayAllExpenses()
            3 -> expenseManager.displayTotalByCategory()
            4 -> break
            else -> println("Неверный выбор, попробуйте снова.")
        }
    }
}

