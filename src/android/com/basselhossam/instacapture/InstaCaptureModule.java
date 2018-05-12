package com.basselhossam.instacapture;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.basselhossam.instacapture.utility.FileUtility;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import com.tarek360.instacapture.utility.Logger;
import com.tarek360.instacapture.listener.SimpleScreenCapturingListener;
import com.tarek360.instacapture.Instacapture;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class InstaCaptureModule extends CordovaPlugin {

    final private String ENABLE_LOGGING = "enableLogging";
    final private String SET_JPEG_COMPRESSION_QUALITY = "setJpegCompressionQuality";
    final private String CAPTURE_SCREENSHOT = "captureScreenshot";
    final private String GET_IMAGES = "getImages";

    @Override
    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
        if (action.equals(ENABLE_LOGGING)) {
            Log.d("Instacapture","enableLogging");
            Boolean enable = args.getBoolean(0);
            if(enable)
                Logger.enable();
            else
                Logger.disable();
            Instacapture.enableLogging(enable);
            callbackContext.success();
        }else if(action.equals(SET_JPEG_COMPRESSION_QUALITY)){
            int quality = args.getInt(0);
            FileUtility.setJpegCompressionQuality(quality);
            callbackContext.success();
        }else if(action.equals(CAPTURE_SCREENSHOT)){
            Logger.d("captureScreenshot");
            Instacapture.capture(cordova.getActivity(), new SimpleScreenCapturingListener() {
                @Override
                public void onCaptureComplete(Bitmap bitmap) {
                    callbackContext.success(FileUtility.saveBitmapToFile(cordova.getActivity().getApplicationContext(),bitmap).getAbsolutePath());
                }
            }, null);
        }else if(action.equals(GET_IMAGES)) {
            Logger.d("getImages");
            String screenShotsPath = FileUtility.getScreenshotsDirectoryName(cordova.getActivity().getApplicationContext());
            Logger.d(screenShotsPath);
            File folder = new File(screenShotsPath);
            File[] listOfFiles = folder.listFiles();
            ArrayList<String> images = new ArrayList<>();
            if (listOfFiles != null) {
                for (int i = 0; i < listOfFiles.length; i++) {
                    if (listOfFiles[i].isFile()) {
                        Logger.d("File " + listOfFiles[i].getName());
                        images.add(listOfFiles[i].getAbsolutePath());
                    }
                }
                JSONArray ret = new JSONArray(images);
                callbackContext.success(ret);
            }
        }else {
            return false;  // Returning false results in a "MethodNotFound" error.
        }
        return true;
    }
}