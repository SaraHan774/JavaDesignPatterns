package facade


/**
 * 서로 다른 액터를 뒷받침 하는 코드를 분리하도록 한다.
 * Employee 클래스 안에 아래의 모든 메서드를 포함하면
 * 1. 우발적 중복 : 초과근무 시간을 계산하는 regularHours 란 함수를 두 부서에서 다른 목적으로, 다르게 구성하려는 경우 문제 발생
 * 2. 병합 : 두 개의 다른 부서에서 다른 목적으로 하나의 Employee 코드를 수정할 위험
 * 과 같은 문제점이 나타난다.
 */
data class Employee(
    val id: Int,
    val name: String,
    val dept: String,
    val totalWorkHours: Int,
    val totalWorkDays: Int,
    val costPerHour: Int,
) {
    private val employeeData = EmployeeData()

    fun calculatePay(): Int {
        return employeeData.calculatePay(totalWorkHours, costPerHour)
    }

    fun reportHours() {
        employeeData.reportHours(totalWorkHours)
    }

    fun save() {
        employeeData.saver(this)
    }
}

class PayCalculator {  // 회계팀에서 기능을 정의, CFO 보고를 위해 사용
    fun calculatePay(totalWorkHours: Int, costPerHour: Int): Int {
        val pay = totalWorkHours * costPerHour
        regularHours(totalWorkHours)
        return pay
    }

    private fun regularHours(totalWorkHours: Int) {

    }
}

class HourReporter { // 인사팀에서 기능을 정의하고 사용하며, COO 보고를 위해 사용
    fun reportHours(totalWorkHours: Int) {
        println("[REPORT TOTAL WORK HOURS] : $totalWorkHours hours")
        regularHours()
    }

    private fun regularHours() {
    }
}

class EmployeeSaver { // DBA 가 기능을 정의하고, CTO 보고를 위해 사용
    fun saveEmployee(employee: Employee) {
        println("saving employee ... ${employee.name}")
    }
}

class EmployeeData {
    private val payCalculator: PayCalculator = PayCalculator()
    private val hourReporter: HourReporter = HourReporter()
    private val employeeSaver: EmployeeSaver = EmployeeSaver()

    fun calculatePay(totalWorkHours: Int, costPerHour: Int) =
        payCalculator.calculatePay(totalWorkHours, costPerHour)

    fun reportHours(totalWorkHours: Int) = hourReporter.reportHours(totalWorkHours)

    fun saver(employee: Employee) = employeeSaver.saveEmployee(employee)
}

fun main() {
    val sara = Employee(1, "Sara", "department of chinese", 120, 20, 15)
    val pay = sara.calculatePay()
    println("[TOTAL PAY] : $$pay")
    sara.reportHours()
    sara.save()
}