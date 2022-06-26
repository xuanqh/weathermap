#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_weathermap_nativelib_NativeLib_weatherMapKey(JNIEnv *env, jobject thiz) {
    std::string api_key = "60c6fbeb4b93ac653c492ba806fc346d";
    return env->NewStringUTF(api_key.c_str());
}