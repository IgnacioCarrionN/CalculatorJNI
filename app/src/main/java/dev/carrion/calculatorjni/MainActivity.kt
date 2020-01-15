package dev.carrion.calculatorjni

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Observer
import dev.carrion.calculatorjni.viewmodel.CalculatorViewModel
import dev.carrion.calculatorlib.Operation
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val calculatorViewModel by viewModel<CalculatorViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        calculatorViewModel.firstNumberLiveData.observe(this, Observer {
            etFirstNumber.setText(it.toString())
        })

        calculatorViewModel.secondNumberLiveData.observe(this, Observer {
            etSecondNumber.setText(it.toString())
        })

        calculatorViewModel.operationLiveData.observe(this, Observer {
            changeStateOperationButtons(it)
        })

        calculatorViewModel.resultLiveData.observe(this, Observer {
            txtResult.text = it
        })

        calculatorViewModel.errorLiveData.observe(this, Observer {
            if (it.isNotEmpty()) {
                txtResult.text = it
            }
        })


        etFirstNumber.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                etFirstNumber.setText("")
            }
        }

        etSecondNumber.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                etSecondNumber.setText("")
            }
        }

        btnAdd.setOnClickListener {
            calculatorViewModel.changedOperation(Operation.ADD)
        }

        btnSub.setOnClickListener {
            calculatorViewModel.changedOperation(Operation.SUB)
        }

        btnMultiply.setOnClickListener {
            calculatorViewModel.changedOperation(Operation.MULTIPLY)
        }

        btnDivide.setOnClickListener {
            calculatorViewModel.changedOperation(Operation.DIVIDE)
        }

        btnResult.setOnClickListener {
            calculatorViewModel.changedFirstNumber(etFirstNumber.text.toString())
            calculatorViewModel.changedSecondNumber(etSecondNumber.text.toString())
            calculatorViewModel.getResult()
        }

    }

    private fun changeStateOperationButtons(operation: Operation) {
        setOperationButtonsDisable()
        when (operation) {
            Operation.ADD -> setOperationButtonEnabled(btnAdd)
            Operation.SUB -> setOperationButtonEnabled(btnSub)
            Operation.MULTIPLY -> setOperationButtonEnabled(btnMultiply)
            Operation.DIVIDE -> setOperationButtonEnabled(btnDivide)
        }
    }

    private fun setOperationButtonsDisable() {
        btnAdd.setBackgroundColor(
            ResourcesCompat.getColor(
                resources,
                R.color.operationBtnDisabled,
                null
            )
        )
        btnSub.setBackgroundColor(
            ResourcesCompat.getColor(
                resources,
                R.color.operationBtnDisabled,
                null
            )
        )
        btnMultiply.setBackgroundColor(
            ResourcesCompat.getColor(
                resources,
                R.color.operationBtnDisabled,
                null
            )
        )
        btnDivide.setBackgroundColor(
            ResourcesCompat.getColor(
                resources,
                R.color.operationBtnDisabled,
                null
            )
        )
    }

    private fun setOperationButtonEnabled(btn: Button) {
        btn.setBackgroundColor(
            ResourcesCompat.getColor(
                resources,
                R.color.operationBtnEnabled,
                null
            )
        )
    }
}
