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
package com.sevtinge.vojazento;

import static com.sevtinge.vojazento.utils.PackageUtils.*;

import com.sevtinge.vojazento.utils.XposedLogUtils;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class XposedInit implements IXposedHookLoadPackage {

    boolean IS_CHINA_MAINLAND_BUILD = false;

    int ERROR_COUNTER = 0;

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        XposedLogUtils.logI(lpparam.packageName, "versionName = " + getPackageVersionName(lpparam) + ", versionCode = " + getPackageVersionCode(lpparam));
        try {
            XposedHelpers.setStaticBooleanField(XposedHelpers.findClassIfExists("miui.os.Build", lpparam.classLoader), "IS_INTERNATIONAL_BUILD", !IS_CHINA_MAINLAND_BUILD); //是否为国际构建
        } catch (Throwable t) {
            ERROR_COUNTER = ERROR_COUNTER + 1;
            XposedLogUtils.logE(lpparam.packageName, "A problem occurred hook \"miui.os.Build.IS_INTERNATIONAL_BUILD\":" + t);
        }
        try {
            XposedHelpers.setStaticBooleanField(XposedHelpers.findClassIfExists("miuix.os.Build", lpparam.classLoader), "IS_INTERNATIONAL_BUILD", !IS_CHINA_MAINLAND_BUILD); //MiuiX是否为国际构建
        } catch (Throwable t) {
            ERROR_COUNTER = ERROR_COUNTER + 1;
            XposedLogUtils.logE(lpparam.packageName, "A problem occurred hook \"miuix.os.Build.IS_INTERNATIONAL_BUILD\":" + t);
        }
        try {
            XposedHelpers.setStaticBooleanField(XposedHelpers.findClassIfExists("miui.os.Build", lpparam.classLoader), "IS_GLOBAL_BUILD", !IS_CHINA_MAINLAND_BUILD); //是否为全球构建
        } catch (Throwable t) {
            ERROR_COUNTER = ERROR_COUNTER + 1;
            XposedLogUtils.logE(lpparam.packageName, "A problem occurred hook \"miui.os.Build.IS_GLOBAL_BUILD\":" + t);
        }
        try {
            XposedHelpers.setStaticBooleanField(XposedHelpers.findClassIfExists("miui.os.Build", lpparam.classLoader), "IS_CM_CUSTOMIZATION", IS_CHINA_MAINLAND_BUILD); //是否为中国大陆版
        } catch (Throwable t) {
            ERROR_COUNTER = ERROR_COUNTER + 1;
            XposedLogUtils.logE(lpparam.packageName, "A problem occurred hook \"miui.os.Build.IS_CM_CUSTOMIZATION\":" + t);
        }
        try {
            XposedHelpers.setStaticBooleanField(XposedHelpers.findClassIfExists("miui.os.Build", lpparam.classLoader), "IS_CM_CUSTOMIZATION_TEST", IS_CHINA_MAINLAND_BUILD); //是否为中国大陆测试版
        } catch (Throwable t) {
            ERROR_COUNTER = ERROR_COUNTER + 1;
            XposedLogUtils.logE(lpparam.packageName, "A problem occurred hook \"miui.os.Build.IS_CM_CUSTOMIZATION_TEST\":" + t);
        }
        try {
            XposedHelpers.findAndHookMethod(XposedHelpers.findClassIfExists("android.os.SystemProperties", lpparam.classLoader), "get", String.class, String.class, new XC_MethodHook() { //设备名称中是否包含国际版标识
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    String RETURN = (String) param.getResult();
                    if (param.args[0].equals("ro.product.mod_device")) {
                        if ((RETURN.contains("_global") || RETURN.contains("_tw") || RETURN.contains("_hk") || RETURN.contains("_sg") || RETURN.contains("_in") || RETURN.contains("_id") || RETURN.contains("_eu")) && IS_CHINA_MAINLAND_BUILD) {
                            RETURN = RETURN.replace("_global", "");
                            RETURN = RETURN.replace("_tw", "");
                            RETURN = RETURN.replace("_hk", "");
                            RETURN = RETURN.replace("_sg", "");
                            RETURN = RETURN.replace("_id", "");
                            RETURN = RETURN.replace("_in", "");
                            RETURN = RETURN.replace("_eu", "");
                            param.setResult(RETURN);
                        } else if (!RETURN.contains("_global") && !IS_CHINA_MAINLAND_BUILD) {
                            param.setResult(param.args[1] + "_global");
                        }
                    }
                }
            });
        } catch (Throwable t) {
            ERROR_COUNTER = ERROR_COUNTER + 1;
            XposedLogUtils.logE(lpparam.packageName, "A problem occurred hook \"android.os.SystemProperties.get(String, String)\":" + t);
        }
        try {
            XposedHelpers.findAndHookMethod(XposedHelpers.findClassIfExists("miuix.core.util.SystemProperties", lpparam.classLoader), "get", String.class, String.class, new XC_MethodHook() {  //miuix设备名称中是否包含国际版标识
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    String RETURN = (String) param.getResult();
                    if (param.args[0].equals("ro.product.mod_device")) {
                        if ((RETURN.contains("_global") || RETURN.contains("_tw") || RETURN.contains("_hk") || RETURN.contains("_sg") || RETURN.contains("_in") || RETURN.contains("_id") || RETURN.contains("_eu")) && IS_CHINA_MAINLAND_BUILD) {
                            RETURN = RETURN.replace("_global", "");
                            RETURN = RETURN.replace("_tw", "");
                            RETURN = RETURN.replace("_hk", "");
                            RETURN = RETURN.replace("_sg", "");
                            RETURN = RETURN.replace("_id", "");
                            RETURN = RETURN.replace("_in", "");
                            RETURN = RETURN.replace("_eu", "");
                            param.setResult(RETURN);
                        } else if (!RETURN.contains("_global") && !IS_CHINA_MAINLAND_BUILD) {
                            param.setResult(param.args[1] + "_global");
                        }
                    }
                }
            });
        } catch (Throwable t) {
            ERROR_COUNTER = ERROR_COUNTER + 1;
            XposedLogUtils.logE(lpparam.packageName, "A problem occurred hook \"miuix.core.util.SystemProperties.get(String, String)\":" + t);
        }
        try {
            XposedHelpers.findAndHookMethod(XposedHelpers.findClassIfExists("miui.os.Build", lpparam.classLoader), "getRegion", new XC_MethodHook() {  //miuix设备名称中是否包含国际版标识
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    if (!IS_CHINA_MAINLAND_BUILD) {
                        param.setResult("GL");
                    } else {
                        param.setResult("CN");
                    }
                }
            });
        } catch (Throwable t) {
            ERROR_COUNTER = ERROR_COUNTER + 1;
            XposedLogUtils.logE(lpparam.packageName, "A problem occurred hook \"miui.os.Build.getRegion()\":" + t);
        }
        try {
            XposedHelpers.findAndHookMethod(XposedHelpers.findClassIfExists("miui.os.Build", lpparam.classLoader), "checkRegion", String.class, new XC_MethodHook() {  //miuix设备名称中是否包含国际版标识
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    String REGION = (String) param.args[0];
                    if (!REGION.equals("CN") && IS_CHINA_MAINLAND_BUILD) {
                        param.setResult(true);
                    } else if (REGION.contains("CN") && !IS_CHINA_MAINLAND_BUILD) {
                        param.setResult(false);
                    }
                }
            });
        } catch (Throwable t) {
            ERROR_COUNTER = ERROR_COUNTER + 1;
            XposedLogUtils.logE(lpparam.packageName, "A problem occurred hook \"miui.os.Build.checkRegion(String)\":" + t);
        }
        XposedLogUtils.logI(lpparam.packageName, "Hook over with " + ERROR_COUNTER + " error(s).");
        ERROR_COUNTER = 0;
    }
}
