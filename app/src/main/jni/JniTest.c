#include "com_alala_binderdemo_jni_JniTest.h"
#include <stdio.h>

JNIEXPORT jstring JNICALL Java_com_alala_binderdemo_jni_JniTest_get
        (JNIEnv * , jobject) {
    printf("invoked get in c\n");
    return (*JNIEnv)->NewStringUTF(JNIEnv, "Hello from JNI.\n");
}

JNIEXPORT void JNICALL Java_com_alala_binderdemo_jni_JniTest_set
(JNIEnv*, jobject, jstring){
printf("invoked set in c\n");
char* str = (char *) (*JNIEnv)->GetStringUTFChars(JNIEnv, jstring, NULL);
printf("%s\n" str);
(*JNIEnv)->ReleaseStringUTFChars(JNIEnv,jstring, str);
}