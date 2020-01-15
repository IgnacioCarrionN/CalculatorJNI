# CalculatorJNI

Libraries:
- AndroidX
- Koin (Dependency injection)

CalculatorJNI uses a MVVM arquitecture with LiveData.

Components:
- MainActivity handles the UI changes.
- CalculatorViewModel keeps the values and communicates with the CalculatorJNI. With this arquitecture I avoid problems with activity recreation.
- CalculatorJNI is the interface with the native module.
- CalculatorOperations is the native file with methods to make the operations and return a result in a callback.
