# Automatically generated makefile, created by the Projucer
# Don't edit this file! Your changes will be overwritten when you re-save the Projucer project!

cmake_minimum_required(VERSION 3.15)

PROJECT(JUCE_CMAKE_ANDROID_EXAMPLE
LANGUAGES C CXX
VERSION 0.0.1
)

# for clang-tidy(this enable to find system header files).
if(CMAKE_EXPORT_COMPILE_COMMANDS)
set(CMAKE_CXX_STANDARD_INCLUDE_DIRECTORIES ${CMAKE_CXX_IMPLICIT_INCLUDE_DIRECTORIES})
endif()

if (ANDROID)

# defs, some are specific to Android and need definisions in prior to `add_subdirectory(JUCE)`.
add_definitions(
    "-DJUCE_ANDROID=1" 
    "-DJUCE_ANDROID_API_VERSION=16" 
    "-DJUCE_PUSH_NOTIFICATIONS=1" 
    "-DJUCE_PUSH_NOTIFICATIONS_ACTIVITY=\"com/rmsl/juce/JuceActivity\"" 
    "-DJUCER_ANDROIDSTUDIO_7F0E4A25=1" 
    "-DJUCE_APP_VERSION=1.0.0" 
    "-DJUCE_APP_VERSION_HEX=0x10000"
    )

# Enable these lines if you use juce_audio_devices API
set(OBOE_DIR "../JUCE/modules/juce_audio_devices/native/oboe")
add_subdirectory (${OBOE_DIR} ./oboe)

# libcpufeatures

add_library("cpufeatures" STATIC "${ANDROID_NDK}/sources/android/cpufeatures/cpu-features.c")
set_source_files_properties("${ANDROID_NDK}/sources/android/cpufeatures/cpu-features.c" PROPERTIES COMPILE_FLAGS "-Wno-sign-conversion -Wno-gnu-statement-expression")

enable_language(ASM)

endif (ANDROID)

# build JUCE
add_subdirectory("../JUCE" ./JUCE)

# build App code (e.g. libExamplePlugin_Standalone.so)

add_subdirectory(src/main/cpp)

if (ANDROID)

add_library(juce_jni
    SHARED
    dummy.cpp
    )

target_link_libraries(juce_jni
    ExamplePlugin_Standalone
)

# Projucer-generated defs
if(JUCE_BUILD_CONFIGURATION MATCHES "DEBUG")
    add_definitions("-DJUCE_DISPLAY_SPLASH_SCREEN=0"
     "-DJUCE_PROJUCER_VERSION=0x60005"
     "-DJUCE_GLOBAL_MODULE_SETTINGS_INCLUDED=1"
     "-DJUCE_STRICT_REFCOUNTEDPOINTER=1"
     "-DJUCER_ANDROIDSTUDIO_7F0E4A25=1"
     "-DDEBUG=1"
     "-D_DEBUG=1"
     )
elseif(JUCE_BUILD_CONFIGURATION MATCHES "RELEASE")
    add_definitions("-DJUCE_DISPLAY_SPLASH_SCREEN=0" 
    "-DJUCE_PROJUCER_VERSION=0x60005" 
    "-DJUCE_GLOBAL_MODULE_SETTINGS_INCLUDED=1"
    "-DJUCE_STRICT_REFCOUNTEDPOINTER=1"
    "-DJUCER_ANDROIDSTUDIO_7F0E4A25=1"
   "-DNDEBUG=1")
else()
    message( FATAL_ERROR "No matching build-configuration found." )
endif()

target_compile_options(ExamplePlugin PRIVATE "-fsigned-char" )

endif (ANDROID)
