/*
 * This file is part of Vojazento.

 * Vojazento is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.

 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.

 * Copyright (C) 2024 Sevtinge
 */
package com.sevtinge.vojazento.utils;

import de.robv.android.xposed.XposedBridge;


public class XposedLogUtils {
    public static void logI(String msg) {
        XposedBridge.log("[Vojazento][I]: " + msg);
    }

    public static void logI(String tagOpkg, String msg) {
        XposedBridge.log("[Vojazento][I][" + tagOpkg + "]: " + msg);
    }

    public static void logI(String tag, String pkg, String msg) {
        XposedBridge.log("[Vojazento][I][" + pkg + "][" + tag + "]: " + msg);
    }

    public static void logW(String msg) {
        XposedBridge.log("[Vojazento][W]: " + msg);
    }

    public static void logW(String tag, String pkg, String msg) {
        XposedBridge.log("[Vojazento][W][" + pkg + "][" + tag + "]: " + msg);
    }

    public static void logW(String tag, String pkg, Throwable log) {
        XposedBridge.log("[Vojazento][W][" + pkg + "][" + tag + "]: " + log);
    }

    public static void logW(String tag, String pkg, String msg, Exception exp) {
        XposedBridge.log("[Vojazento][W][" + pkg + "][" + tag + "]: " + msg + ", by: " + exp);
    }

    public static void logW(String tag, String pkg, String msg, Throwable log) {
        XposedBridge.log("[Vojazento][W][" + pkg + "][" + tag + "]: " + msg + ", by: " + log);
    }

    public static void logW(String tag, String msg) {
        XposedBridge.log("[Vojazento][W][" + tag + "]: " + msg);
    }

    public static void logW(String tag, Throwable log) {
        XposedBridge.log("[Vojazento][W][" + tag + "]: " + log);
    }

    public static void logW(String tag, String msg, Exception exp) {
        XposedBridge.log("[Vojazento][W][" + tag + "]: " + msg + ", by: " + exp);
    }

    public static void logE(String tagORpkg, String msg) {
        XposedBridge.log("[Vojazento][E][" + tagORpkg + "]: " + msg);
    }

    public static void logE(String msg) {
        XposedBridge.log("[Vojazento][E]: " + msg);
    }

    public static void logE(String tag, Throwable log) {
        XposedBridge.log("[Vojazento][E][" + tag + "]: " + log);
    }

    public static void logE(String tag, String pkg, String msg) {
        XposedBridge.log("[Vojazento][E][" + pkg + "][" + tag + "]: " + msg);
    }

    public static void logE(String tag, String pkg, Throwable log) {
        XposedBridge.log("[Vojazento][E][" + pkg + "][" + tag + "]: " + log);
    }

    public static void logE(String tag, String pkg, Exception exp) {
        XposedBridge.log("[Vojazento][E][" + pkg + "][" + tag + "]: " + exp);
    }

    public static void logE(String tag, String pkg, String msg, Throwable log) {
        XposedBridge.log("[Vojazento][E][" + pkg + "][" + tag + "]: " + msg + ", by: " + log);
    }

    public static void logE(String tag, String pkg, String msg, Exception exp) {
        XposedBridge.log("[Vojazento][E][" + pkg + "][" + tag + "]: " + msg + ", by: " + exp);
    }

    public static void logD(String msg) {
        XposedBridge.log("[Vojazento][D]: " + msg);
    }

    public static void logD(String tag, String pkg, String msg) {
        XposedBridge.log("[Vojazento][D][" + pkg + "][" + tag + "]: " + msg);
    }

}
