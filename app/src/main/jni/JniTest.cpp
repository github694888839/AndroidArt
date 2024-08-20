#include "com_alala_binderdemo_jni_JniTest.h"
#include <stdio.h>

JNIEXPORT jstring JNICALL Java_com_alala_binderdemo_jni_JniTest_get
        (JNIEnv * , jobject) {
    printf("invoked get in cpp\n");
    return JNIEnv->NewStringUTF("Hello from JNI.\n");
}

JNIEXPORT void JNICALL Java_com_alala_binderdemo_jni_JniTest_set
(JNIEnv*, jobject, jstring){
    printf("invoked set in cpp\n");
    char *str = (char *) JNIEnv->GetStringUTFChars(jstring, NULL);
    printf("%s\n" str);
    JNIEnv->ReleaseStringUTFChars(jstring, str);
}