It is an experimental repository to create a CMake-based JUCE project for Android.

It works, but only with a tiny patch to JUCE which exposes `juce_CreateApplication()` function that is dynamically loaded by `juce_juceEventsAndroidStartApp()`: 
https://gist.github.com/atsushieno/7da120ef87826c9d8fdf8ad6542a16f6

The template is mostly based on [tomoyanonymous/juce_cmake_vscode_example](https://github.com/tomoyanonymous/juce_cmake_vscode_example).

