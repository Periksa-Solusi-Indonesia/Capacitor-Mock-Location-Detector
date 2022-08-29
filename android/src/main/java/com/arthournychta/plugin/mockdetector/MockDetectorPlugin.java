package com.arthournychta.plugin.mockdetector;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import com.getcapacitor.JSObject;
import com.getcapacitor.PermissionState;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

import com.getcapacitor.annotation.Permission;
import com.getcapacitor.annotation.PermissionCallback;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

@CapacitorPlugin(
        name = "MockDetector",
        permissions = {
                @Permission(
                        alias = "location",
                        strings = {
                                Manifest.permission.ACCESS_BACKGROUND_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION,
                                Manifest.permission.ACCESS_FINE_LOCATION
                        }
                )
        })
public class MockDetectorPlugin extends Plugin {

    private MockDetector implementation = new MockDetector();

    @PluginMethod
    public void echo(PluginCall call) {
        String value = call.getString("value");
        JSObject ret = new JSObject();
        ret.put("value", implementation.echo(value));
        call.resolve(ret);
    }

    @PluginMethod
    public void detectMock(PluginCall call) {
        Log.i("location", String.valueOf(getPermissionState("location")));
        if (getPermissionState("location") != PermissionState.GRANTED) {
            requestPermissionForAlias("location", call, "locPermissionCallback");
        } else {
            FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this.getActivity().getApplicationContext());
            if (ActivityCompat.checkSelfPermission(this.getActivity().getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this.getActivity().getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this.getActivity(),
                    new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            Log.i("Location", String.valueOf(location));
                            JSObject data = new JSObject();
                            if (location == null) {
                                data.put("value", null);
                            } else {
                                if (Build.VERSION.SDK_INT <= 30) {
                                    boolean value = location.isFromMockProvider();
                                    Log.i("Location Value", String.valueOf(value));
                                    String provider = location.getProvider();
                                    data.put("value", value);
                                    data.put("provider", provider);
                                    data.put("location", location);
                                } else if (Build.VERSION.SDK_INT >= 31) {
                                    boolean value = location.isMock();
                                    String provider = location.getProvider();
                                    Log.i("Location Value", String.valueOf(value));
                                    data.put("value", value);
                                    data.put("provider", provider);
                                    data.put("location", location);
                                }
                            }
                            call.resolve(data);
                        }
                    });
        }
    }

    private void getMock() {

    }

    @PermissionCallback
    private void locPermissionCallback(PluginCall call) {
        if (getPermissionState("location") == PermissionState.GRANTED) {
            this.detectMock(call);
        } else {
            call.reject("Permission is required to take a location");
        }
    }


}