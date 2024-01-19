package com.example.lsm_app

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.net.wifi.ScanResult
import android.net.wifi.WifiManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import java.security.MessageDigest
import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import android.os.Build
import android.Manifest
import android.content.pm.PackageManager





class conexion : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_conexion, container, false)
    }

}