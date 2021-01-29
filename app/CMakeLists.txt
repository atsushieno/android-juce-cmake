cmake_minimum_required(VERSION 3.15)

PROJECT(JUCE_CMAKE_ANDROID_EXAMPLE
LANGUAGES C CXX
VERSION 0.0.1
)

# for clang-tidy(to find system header files).
if(CMAKE_EXPORT_COMPILE_COMMANDS)
set(CMAKE_CXX_STANDARD_INCLUDE_DIRECTORIES ${CMAKE_CXX_IMPLICIT_INCLUDE_DIRECTORIES})
endif()

# defs, some are specific to Android and need definisions in prior to `add_subdirectory(JUCE)`.
add_definitions(
    "-DJUCE_ANDROID=1" 
    "-DJUCE_PUSH_NOTIFICATIONS=1" 
    "-DJUCE_PUSH_NOTIFICATIONS_ACTIVITY=\"com/rmsl/juce/JuceActivity\"" 
    )

# Enable these lines if you use juce_audio_devices API
set(OBOE_DIR "../JUCE/modules/juce_audio_devices/native/oboe")
add_subdirectory (${OBOE_DIR} ./oboe)

# libcpufeatures
add_library("cpufeatures" STATIC "${ANDROID_NDK}/sources/android/cpufeatures/cpu-features.c")
set_source_files_properties("${ANDROID_NDK}/sources/android/cpufeatures/cpu-features.c" PROPERTIES COMPILE_FLAGS "-Wno-sign-conversion -Wno-gnu-statement-expression")

enable_language(ASM)

# build JUCE
add_subdirectory("../JUCE" ./JUCE)

# build App code (e.g. libExamplePlugin_Standalone.so)

add_subdirectory(src/main/cpp)

add_library(juce_jni
    SHARED
    dummy.cpp
    )

target_link_libraries(juce_jni
    ExamplePlugin_Standalone
)

# Not sure if we really need this, but Projucer generates this option.
target_compile_options(ExamplePlugin PRIVATE "-fsigned-char" )

