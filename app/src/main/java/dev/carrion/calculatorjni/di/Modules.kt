package dev.carrion.calculatorjni.di

import dev.carrion.calculatorjni.viewmodel.CalculatorViewModel
import dev.carrion.calculatorlib.CalculatorJNI
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val CalculatorModule = module {
    factory { CalculatorJNI() }
    viewModel { CalculatorViewModel(get()) }
}