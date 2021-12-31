package net.himeki.sfeaturelist.xposed;

import java.util.HashMap;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class XposedEntry implements IXposedHookLoadPackage {
    HashMap<String, String> featureString = new HashMap<>();
    HashMap<String, Boolean> featureBoolean = new HashMap<>();

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        featureString.put("CscFeature_VoiceCall_ConfigRecording", "RecordingAllowed");
        featureString.put("CscFeature_SmartManager_ConfigSubFeatures", "applock");

        featureBoolean.put("CscFeature_SystemUI_SupportRecentAppProtection", true);
        featureBoolean.put("CscFeature_SystemUI_ConfigOverrideDataIcon", true);
        featureBoolean.put("CscFeature_SystemUI_SupportDataUsageViewOnQuickPanel", true);
        featureBoolean.put("CscFeature_Camera_ShutterSoundMenu", true);
        featureBoolean.put("CscFeature_Camera_EnableCameraDuringCall", true);
        featureBoolean.put("CscFeature_Setting_SupportRealTimeNetworkSpeed", true);
        featureBoolean.put("CscFeature_Setting_EnableMenuBlockCallMsg", true);



        XposedHelpers.findAndHookMethod("com.samsung.android.feature.SemCscFeature", lpparam.classLoader, "getString", String.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
                String featureValue = featureString.get(param.args[0].toString());
                if (featureValue != null) {
                    param.setResult(featureValue);
                    XposedBridge.log(lpparam.packageName + " called getString(\"" + param.args[0].toString() + "\");\n");
                    XposedBridge.log("Set result to " + featureValue + "\n");
                }
            }
        });

        XposedHelpers.findAndHookMethod("com.samsung.android.feature.SemCscFeature", lpparam.classLoader, "getBoolean", String.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
                Boolean featureValue = featureBoolean.get(param.args[0].toString());
                if (featureValue != null) {
                    param.setResult(featureValue);
                    XposedBridge.log(lpparam.packageName + " called getBoolean(\"" + param.args[0].toString() + "\");\n");
                    XposedBridge.log("Set result to " + featureValue + "\n");
                }
            }
        });

        XposedHelpers.findAndHookMethod("com.samsung.android.feature.SemCscFeature", lpparam.classLoader, "getBoolean", String.class, boolean.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
                Boolean featureValue = featureBoolean.get(param.args[0].toString());
                if (featureValue != null) {
                    param.setResult(featureValue);
                    XposedBridge.log(lpparam.packageName + " called getBoolean(\"" + param.args[0].toString() + "\", " + param.args[1].toString() + ");\n");
                    XposedBridge.log("Set result to " + featureValue + "\n");
                }
            }
        });
    }
}
