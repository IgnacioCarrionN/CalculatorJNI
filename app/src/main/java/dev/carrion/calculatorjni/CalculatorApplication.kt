package dev.carrion.calculatorjni

import android.app.Application
import androidx.annotation.Keep
import dev.carrion.calculatorjni.di.CalculatorModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@Keep
class CalculatorApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CalculatorApplication)
            modules(CalculatorModule)
        }
    }
}