//
// Created by Nacho on 15/01/2020.
//

#include <jni.h>

extern "C" JNIEXPORT void JNICALL
Java_dev_carrion_calculatorlib_CalculatorJNI_add(JNIEnv *env, jobject, jint x, jint y, jobject listener) {

    jclass cls = env->GetObjectClass(listener);
    jmethodID methodId = env->GetMethodID(cls, "onResultJNI", "(D)V");

    double result = x + y;
    env -> CallVoidMethod(listener, methodId, result);

}

extern "C" JNIEXPORT void JNICALL
Java_dev_carrion_calculatorlib_CalculatorJNI_sub( JNIEnv *env, jobject, jint x, jint y, jobject listener) {

    jclass cls = env->GetObjectClass(listener);
    jmethodID methodId = env->GetMethodID(cls, "onResultJNI", "(D)V");

    double result = x - y;
    env -> CallVoidMethod(listener, methodId, result);
}

extern "C" JNIEXPORT void JNICALL
Java_dev_carrion_calculatorlib_CalculatorJNI_multiply( JNIEnv *env, jobject, jint x, jint y, jobject listener) {

    jclass cls = env->GetObjectClass(listener);
    jmethodID methodId = env->GetMethodID(cls, "onResultJNI", "(D)V");

    double result = x * y;
    env -> CallVoidMethod(listener, methodId, result);
}

extern "C" JNIEXPORT void JNICALL
Java_dev_carrion_calculatorlib_CalculatorJNI_divide( JNIEnv *env, jobject, jint x, jint y, jobject listener) {

    jclass cls = env->GetObjectClass(listener);
    jmethodID methodId = env->GetMethodID(cls, "onResultJNI", "(D)V");

    double result = (double)x / (double)y;
    env -> CallVoidMethod(listener, methodId, result);
}

