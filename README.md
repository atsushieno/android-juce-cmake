It is an experimental repository to create a CMake-based JUCE project for Android.

Note that JUCE does not support CMake for Android by themselves yet. No support for audio plugins either.

For technical details on the project contents, see [my blog post](https://atsushieno.github.io/2021/01/16/juce-cmake-android-now-works.html) for details.

This comes with a tiny patch to JUCE which exposes `juce_CreateApplication()` function that is dynamically loaded by `juce_juceEventsAndroidStartApp()` (a patch `JUCE-support-Android-CMake.patch` is available for this).

Also note that there are handful of [known JUCE issues](https://github.com/juce-framework/JUCE/issues?q=is%3Aissue+is%3Aopen+Android) that are Android specific. Especially you would be likely suffered from the audio input channel issues.

The template is mostly based on [tomoyanonymous/juce_cmake_vscode_example](https://github.com/tomoyanonymous/juce_cmake_vscode_example).

![screenshot](https://raw.githubusercontent.com/atsushieno/atsushieno.github.io/master/images/sshot-template-project.png)
