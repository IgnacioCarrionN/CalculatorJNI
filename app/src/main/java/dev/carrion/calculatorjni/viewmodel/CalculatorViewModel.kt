package dev.carrion.calculatorjni.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.carrion.calculatorlib.CalculatorJNI
import dev.carrion.calculatorlib.Operation
import dev.carrion.calculatorlib.getStringValue
import java.lang.Exception

class CalculatorViewModel(private val calculatorJNI: CalculatorJNI) : ViewModel(),
    CalculatorJNI.JNIListener {

    private val firstNumber: MutableLiveData<Int> = MutableLiveData(0)
    private val secondNumber: MutableLiveData<Int> = MutableLiveData(0)
    private val operation: MutableLiveData<Operation> = MutableLiveData(Operation.ADD)

    private val result: MutableLiveData<String> = MutableLiveData("0")

    private val error: MutableLiveData<String> = MutableLiveData("")

    val firstNumberLiveData: LiveData<Int>
        get() = firstNumber

    val secondNumberLiveData: LiveData<Int>
        get() = secondNumber

    val operationLiveData: LiveData<Operation>
        get() = operation

    val resultLiveData: LiveData<String>
        get() = result

    val errorLiveData: LiveData<String>
        get() = error


    fun changedFirstNumber(x: String) {
        try {
            firstNumber.value = x.toInt()
        } catch (e: Exception) {
            error.value = "First value is not a number"
        }
    }

    fun changedSecondNumber(y: String) {
        try {
            secondNumber.value = y.toInt()
        } catch (e: Exception) {
            error.value = "Second value is not a number"
        }
    }

    fun changedOperation(operation: Operation) {
        this.operation.value = operation
    }

    fun getResult() {
        calculatorJNI.getResult(
            firstNumber.value ?: 0,
            secondNumber.value ?: 0,
            operation.value ?: Operation.ADD,
            this
        )
    }

    override fun onResultJNI(result: Double) {
        this.result.value = "${firstNumber.value} ${operation.value?.getStringValue()} ${secondNumber.value} = $result"
        this.error.value = ""
    }

    override fun onError(error: String) {
        this.error.value = error
    }
}