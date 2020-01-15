package dev.carrion.calculatorlib

class CalculatorJNI {
    init {
        System.loadLibrary("CalculatorOperations")
    }


    external fun add(x: Int, y: Int, listener: JNIListener)
    external fun sub(x: Int, y: Int, listener: JNIListener)
    external fun multiply(x: Int, y: Int, listener: JNIListener)
    external fun divide(x: Int, y: Int, listener: JNIListener)

    fun getResult(x: Int, y: Int, type: Operation, listener: JNIListener) {
        when(type) {
            Operation.ADD -> {
                add(x, y, listener)
            }
            Operation.SUB -> {
                sub(x, y, listener)
            }
            Operation.MULTIPLY -> {
                multiply(x, y, listener)
            }
            Operation.DIVIDE -> {
                if(y != 0){
                    divide(x, y, listener)
                } else {
                    listener.onError("You can't divide by 0")
                }
            }
        }
    }

    interface JNIListener {
        fun onResultJNI(result: Double)
        fun onError(error: String)
    }
}

enum class Operation {
    ADD,
    SUB,
    MULTIPLY,
    DIVIDE;
}

fun Operation.getStringValue(): String {
    return when(this) {
        Operation.ADD -> "+"
        Operation.SUB -> "-"
        Operation.MULTIPLY -> "*"
        Operation.DIVIDE -> "/"
    }
}


