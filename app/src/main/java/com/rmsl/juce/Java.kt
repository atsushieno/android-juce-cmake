// For binary compatibility with JUCE JNI, we have to name this class under this package.

package com.rmsl.juce

import android.content.Context

class Java
{
    companion object {
        init {
            System.loadLibrary("juce_jni")
        }
        
        @JvmStatic
        external fun initialiseJUCE(applicationContext: Context)
    }
}
