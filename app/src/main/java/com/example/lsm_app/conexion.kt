package com.example.lsm_app

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import android.Manifest
import androidx.camera.core.AspectRatio
import androidx.camera.core.ImageAnalysis
import com.google.mediapipe.tasks.vision.core.RunningMode
import java.util.concurrent.Executors

class conexion : Fragment() {

    private lateinit var gestureRecognizerHelper: GestureRecognizerHelper
    private lateinit var previewView: PreviewView
    private val cameraExecutor = Executors.newSingleThreadExecutor()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_conexion, container, false)

        previewView = view.findViewById<PreviewView>(R.id.camera)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (allPermissionsGranted()) {

                gestureRecognizerHelper = GestureRecognizerHelper(
                minHandDetectionConfidence = 0.5f,
                minHandTrackingConfidence = 0.5f,
                minHandPresenceConfidence = 0.5f,
                currentDelegate = GestureRecognizerHelper.DELEGATE_CPU,
                runningMode = RunningMode.LIVE_STREAM,
                context = requireContext(),
                gestureRecognizerListener = object : GestureRecognizerHelper.GestureRecognizerListener {
                    override fun onError(error: String, errorCode: Int) {
                        Log.e(TAG, "Gesture Recognizer Error: $error")
                    }

                    override fun onResults(resultBundle: GestureRecognizerHelper.ResultBundle) {
                        Results(resultBundle)
                    }
                }
            )

            startCamera()

        } else {
            requestCameraPermissions()
        }
    }

    private fun startCamera() {

        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())

        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()
            val preview = Preview.Builder().build()
            preview.setSurfaceProvider(previewView.surfaceProvider)

            val imageAnalysis = ImageAnalysis.Builder().setTargetAspectRatio(AspectRatio.RATIO_4_3)
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .setOutputImageFormat(ImageAnalysis.OUTPUT_IMAGE_FORMAT_RGBA_8888)
                .build()

            imageAnalysis.setAnalyzer(cameraExecutor, { imageProxy ->
                try {
                    gestureRecognizerHelper.recognizeLiveStream(imageProxy)
                } catch (e: Exception) {
                    Log.e(TAG, "Error processing image: ${e.message}")
                } finally {
                    imageProxy.close()
                }
            })

            val cameraSelector = CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_FRONT)
                .build()

            cameraProvider.bindToLifecycle(
                this, cameraSelector, preview, imageAnalysis
            )
        }, ContextCompat.getMainExecutor(requireContext()))
    }

    fun Results(
        resultBundle: GestureRecognizerHelper.ResultBundle
    ) {
        activity?.runOnUiThread {
            val gestureCategories = resultBundle.results.firstOrNull()?.gestures()
            if (gestureCategories != null && gestureCategories.isNotEmpty()) {

                val gesture = gestureCategories.first()
                val string =gesture.toString()

                //Se sacaron los datos de las respuestas

                val regex = Regex("\"(.*?)\"")
                val regex2 = Regex("score=([0-9\\.]+)")
                val match = regex.find(string)
                val match2 = regex2.find(string)

                if (match != null) {
                    val gesto = match.groupValues[1]
                    Log.d(TAG, "Detected gesture: $gesto")
                } else {
                    println("No se encontró texto entre comillas")
                }

                if (match2 != null) {
                    val score = match2.groupValues[1]
                    Log.d(TAG, "Detected gesture: $score")
                } else {
                    println("No se encontró texto entre comillas")
                }


            } else {

                Log.d(TAG, "No gestures detected.")
            }
        }
    }

    private fun allPermissionsGranted(): Boolean {
        return REQUIRED_PERMISSIONS.all {
            ContextCompat.checkSelfPermission(
                requireContext(),
                it
            ) == PackageManager.PERMISSION_GRANTED
        }
    }

    private fun requestCameraPermissions() {
        if (!allPermissionsGranted()) {
            requestPermissions(
                REQUIRED_PERMISSIONS,
                REQUEST_CODE_PERMISSIONS
            )
        }
    }

    companion object {
        private const val TAG = "conexion"
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10
    }

    override fun onDestroyView() {
        super.onDestroyView()
        cameraExecutor.shutdown()
    }
}
