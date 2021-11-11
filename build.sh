#!/bin/sh

if [ ! -f JUCE/.stamp-juce ] ; then
cd JUCE && patch -i ../JUCE-support-Android-CMake.patch -p1 && touch .stamp-juce && cd .. ;
fi

./gradlew assembleRelease

