#!/bin/bash

APK_PATH="app/build/outputs/apk/release/app-release-unsigned.apk"
JAVA_FILE="app/src/main/java/com/sevtinge/vojazento/XposedInit.java"
KEYWORD="boolean IS_CHINA_MAINLAND_BUILD"

build_and_move_apk() {
    local build_type=$1
    ./gradlew build
    mv "$APK_PATH" "$(dirname APK_PATH)/Vojazento-$build_type.apk"
}

chmod +x ./gradle

if grep -q "$KEYWORD = true" $JAVA_FILE; then
    build_and_move_apk "China"
    sed -i "/$KEYWORD/s/true/false/" $JAVA_FILE
    build_and_move_apk "Global"
elif grep -q "$KEYWORD = false" $JAVA_FILE; then
    build_and_move_apk "Global"
    sed -i "/$KEYWORD/s/false/true/" $JAVA_FILE
    build_and_move_apk "China"
else
    echo "ERROR: $KEYWORD not found in $JAVA_FILE, please check it."
    exit 1
fi
