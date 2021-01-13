package dev.atsushieno.juce

import android.content.Context
import androidx.startup.Initializer

class JuceAppInitializer : Initializer<Unit> {
    override fun create(context: Context) : Unit {
        com.rmsl.juce.Java.initialiseJUCE(context.applicationContext)
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf<Class<out Initializer<*>>>()
    }
}

