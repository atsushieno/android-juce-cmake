# This template is a modified version of https://github.com/tomoyanonymous/juce_cmake_vscode_example/blob/master/src/CMakeLists.txt

juce_add_plugin(ExamplePlugin
    # VERSION ...                                     # Set this if the plugin version is different to the project version
    # ICON_BIG ""   # ICON_* arguments specify a path to an image file to use as an icon for the Standalone
    #   ICON_SMALL "${CMAKE_SOURCE_DIR}/Assets/icon16.png " 
    COMPANY_NAME "Atsushi Eno"                        # Specify the name of the plugin's author
    COMPANY_COPYRIGHT "Copyright (C) 2021 Atsushi Eno"
    COMPANY_WEBSITE "https://github.com/atsushieno"
    COMPANY_EMAIL "atsushieno@gmail.com"
    IS_SYNTH FALSE                              # Is this a synth or an effect?
    # NEEDS_MIDI_INPUT TRUE/FALSE               # Does the plugin need midi input?
    # NEEDS_MIDI_OUTPUT TRUE/FALSE              # Does the plugin need midi output?
    # IS_MIDI_EFFECT TRUE/FALSE                 # Is this plugin a MIDI effect?
    # EDITOR_WANTS_KEYBOARD_FOCUS TRUE/FALSE    # Does the editor need keyboard focus?
    COPY_PLUGIN_AFTER_BUILD TRUE                # Should the plugin be installed to a default location after building?
    PLUGIN_MANUFACTURER_CODE CCCC               # A four-character manufacturer id with at least one upper-case character
    PLUGIN_CODE XXXX                            # A unique four-character plugin id with at least one upper-case character
    FORMATS 
    # The formats to build. Other valid formats are: AAX Unity VST AU AUv3
            Standalone 
            # AU
            # AUv3 
            # VST3 
            # Unity 
            ${AAX_BUILD_FLAG}               
    VST3_CATEGORIES "Fx" 
    AU_MAIN_TYPE "kAudioUnitType_Effect"
    # AU_SANDBOX_SAFE TRUE
    # AAX_CATEGORY ""
    # HARDENED_RUNTIME_ENABLED # macOS app settings
    # HARDENED_RUNTIME_OPTIONS
    # APP_SANDBOX_ENABLED
    # APP_SANDBOX_INHERIT
    # DESCRIPTION ""
    MICROPHONE_PERMISSION_ENABLED TRUE
    MICROPHONE_PERMISSION_TEXT "This applicaion requires a permission to use an audio input device of your computer. By Default, Built-In microphone will be used."

    PRODUCT_NAME "ExamplePlugin")        # The name of the final 

target_compile_features(ExamplePlugin PUBLIC cxx_std_17)


target_compile_definitions(ExamplePlugin
    PUBLIC
    # JUCE_WEB_BROWSER and JUCE_USE_CURL would be on by default, but you might not need them.
    JUCE_WEB_BROWSER=0  # If you remove this, add `NEEDS_WEB_BROWSER TRUE` to the `juce_add_plugin` call
    JUCE_USE_CURL=0     # If you remove this, add `NEEDS_CURL TRUE` to the `juce_add_plugin` call
    JUCE_VST3_CAN_REPLACE_VST2=0
    JUCE_DISPLAY_SPLASH_SCREEN=0 #if your plugin is distributed with GPL license or paid
)

juce_add_binary_data(ExamplePluginBinaries
SOURCES 
../juce-assets/juce-logo.svg
)

target_sources(ExamplePlugin PRIVATE
    PluginEditor.cpp
    PluginProcessor.cpp
)

# begin Android specifics -->
if (ANDROID)

# dependencies
find_library(log "log")
find_library(android "android")
find_library(glesv2 "GLESv2")
find_library(egl "EGL")
set(cpufeatures_lib "cpufeatures")
set(oboe_lib "oboe")

target_include_directories( ExamplePlugin PRIVATE
    "${ANDROID_NDK}/sources/android/cpufeatures"
    "${OBOE_DIR}/include"
)

endif (ANDROID)
# <-- end Android specifics

target_link_libraries(ExamplePlugin PUBLIC
ExamplePluginBinaries
juce::juce_audio_basics
juce::juce_audio_devices
#juce::juce_audio_formats
#juce::juce_audio_plugin_client
juce::juce_audio_processors
juce::juce_audio_utils
juce::juce_core
#juce::juce_cryptography
juce::juce_data_structures
#juce::juce_dsp
juce::juce_events
juce::juce_graphics
juce::juce_gui_basics
juce::juce_gui_extra
#juce::juce_opengl
#juce::juce_product_unlocking
# These are Android additions
${log}
${android}
${glesv2}
${egl}
${cpufeatures_lib}
${oboe_lib}
)

juce_generate_juce_header(ExamplePlugin)
