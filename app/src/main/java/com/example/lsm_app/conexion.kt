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
import android.media.MediaPlayer
import android.widget.TextView
import com.google.mediapipe.tasks.components.containers.NormalizedLandmark
import com.example.lsm_app.Draw
import com.google.mediapipe.tasks.vision.gesturerecognizer.GestureRecognizerResult

class conexion : Fragment() {

    private lateinit var gestureRecognizerHelper: GestureRecognizerHelper
    private lateinit var previewView: PreviewView
    private lateinit var overlayView: Draw
    private val cameraExecutor = Executors.newSingleThreadExecutor()
    private var Media:MediaPlayer?=null
    private var Time = 0
    private var punta: Float? = null
    private var puntay: Float? = null
    private var puntax: Float? = null
    private var puntayi: Float? = null
    private var puntaym: Float? = null
    private var puntaxm: Float? = null
    private var movimiento =  mutableListOf(0f, 0f, 0f)
    private var movimientoy =  mutableListOf(0f, 0f, 0f)
    private var movimientox =  mutableListOf(0f, 0f, 0f)
    private var movimientomes =  mutableListOf(0f, 0f)
    private var score:String? = null
    private var PalabraAnterior = ""
    private var contador:Int = 0
    private var Oracion=mutableListOf<String>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_conexion, container, false)

        overlayView = view.findViewById(R.id.overlay)
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
                    val puntos = gestureRecognizerHelper.recognizeLiveStream(imageProxy)

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

    fun Results(resultBundle: GestureRecognizerHelper.ResultBundle) {
        activity?.runOnUiThread {
            val gestureCategories = resultBundle.results.firstOrNull()?.gestures()

            // Inicializar Landmark ------------------------------------------------------------------------------------------------------------
            // Obtenemos los valores de los landmarks
            val landmarkslist = resultBundle.results.firstOrNull()?.landmarks()
            val respuesta: GestureRecognizerResult? = resultBundle.results.firstOrNull()


            if (respuesta != null) {

                // Obtener el ancho y alto de la vista previa
                val imageWidth = previewView.width
                val imageHeight = previewView.height

                // Llamar a setResults en overlayView
                overlayView.setResults(respuesta, imageHeight, imageWidth, RunningMode.LIVE_STREAM)

            }
            //......................

            var TimeStop: Int = 20
            var pulgar:NormalizedLandmark? = null
            var menique:NormalizedLandmark? = null
            var indice:NormalizedLandmark? = null
            var muneca:NormalizedLandmark? = null
            // Fin Landmark  ----------------------------------------------------------------------------------------------------------------
            if(landmarkslist != null && landmarkslist.isNotEmpty()){
                pulgar = landmarkslist[0][4]
                punta = pulgar.z()
                puntay = pulgar.y()
                puntax = pulgar.x()

                menique = landmarkslist[0][18]
                puntayi = menique.x()

                indice = landmarkslist[0][8]
                puntaym = indice.x()

                muneca = landmarkslist[0][0]
                puntaxm = muneca.x()
            }else{

                punta = null
                puntay = null
                puntax = null
                puntaym = null
            }




            if (gestureCategories != null && gestureCategories.isNotEmpty()) {

                val gesture = gestureCategories.first()
                val string =gesture.toString()

                //Se sacaron los datos de las respuestas
                val regex = Regex("\"(.*?)\"")
                val regex2 = Regex("score=([0-9\\.]+)")
                val match = regex.find(string)
                val match2 = regex2.find(string)

                if (match2 != null) {
                    score = match2.groupValues[1]

                    val scoreString = score
                    val sc = scoreString?.toFloatOrNull() ?: 0f

                    if(sc >= 0.65f){
                        Log.d(TAG, "Detected gesture: $score")
                        if (match != null) {
                            val gesto = match.groupValues[1]
                            Log.d(TAG, "Detected gesture: $gesto")

                            var audio = resources.getIdentifier(gesto,"raw",requireContext().packageName)
                            val texto = view?.findViewById<TextView>(R.id.sena)

                            texto?.text = "Tiempo:  " + Time  +" ......."+ gesto

                            if (audio != 0) {

                                if (PalabraAnterior == gesto) {
                                    Time = Time + 1

                                    val p = punta ?: 0f
                                    val py = puntay ?: 0f
                                    val px = puntax ?: 0f
                                    val pym = puntaym ?: 0f
                                    val pxm = puntaxm ?: 0f
                                    //Logica de movimiento ayer
                                    if(punta != null && Time <= 7 && gesto == "a"){
                                        movimiento[0] = (Math.round(p * 100) / 100f)
                                        movimientox[0] = (Math.round(px * 100) / 100f)
                                        movimientoy[0] = (Math.round(py * 100) / 100f)

                                    }else if (punta != null && Time >= 7 && Time <= 14 && gesto == "a"){
                                        movimiento[1] = (Math.round(p * 100) / 100f)
                                        movimientox[1] = (Math.round(px * 100) / 100f)
                                        movimientoy[1] = (Math.round(py * 100) / 100f)

                                        }else if(punta != null && gesto == "a"){
                                        movimiento[2] = (Math.round(p * 100) / 100f)
                                        movimientox[2] = (Math.round(px * 100) / 100f)
                                        if ((movimiento[1]-movimiento[0])==(movimiento[1]-movimiento[2]) && movimiento[1] != movimiento[2] ){

                                            if(movimientoy[0]<movimientoy[1]){
                                                audio = R.raw.ayer
                                                texto?.text = "ayer"
                                                Media = MediaPlayer.create(requireContext(), audio)
                                                Media?.start()
                                                if(contador==3){
                                                    Oracion.add("ayer")
                                                }
                                            }else if(movimientoy[0]>movimientoy[1]){
                                                audio = R.raw.abril
                                                texto?.text = "abril"
                                                Media = MediaPlayer.create(requireContext(), audio)
                                                Media?.start()
                                            }

                                            Log.i(TAG,"puntay"+movimientoy.toString())
                                            Log.e(TAG,"meniquez"+movimiento.toString())

                                            for (i in movimiento.indices) {
                                                movimiento[i] = 0f
                                            }
                                            for (i in movimientoy.indices) {
                                                movimientoy[i] = 0f
                                            }
                                            Time = 0
                                        }else if((movimientox[1]-movimientox[0])==(movimientox[1]-movimientox[2]) && movimientox[1] != movimientox[2]){

                                            audio = R.raw.agosto
                                            texto?.text = "Agosto"
                                            Media = MediaPlayer.create(requireContext(), audio)
                                            Media?.start()

                                            Log.e(TAG,"menique"+movimientox.toString())

                                            for (i in movimientox.indices) {
                                                movimientox[i] = 0f
                                            }

                                            Time = 0
                                        }
                                    }

                                    if(punta != null && Time <= 7 && gesto == "b"){
                                        movimiento[0] = (Math.round(p * 100) / 100f)

                                    }else if (punta != null && Time >= 7 && Time <= 14 && gesto == "b"){
                                        movimiento[1] = (Math.round(p * 100) / 100f)

                                    }else if(punta != null && gesto == "b"){
                                        movimiento[2] = (Math.round(p * 100) / 100f)

                                        if((movimiento[1]-movimiento[0])==(movimiento[1]-movimiento[2])){
                                            texto?.text = "manzana"
                                            audio = R.raw.manzana
                                            Media = MediaPlayer.create(requireContext(), audio)
                                            Media?.start()

                                            if(contador==3){
                                                Oracion.add("manzana")
                                            }

                                            for (i in movimientox.indices) {
                                                movimientox[i] = 0f
                                            }

                                            Time = 0
                                        }
                                    }

                                    if(gesto == "l" && indice != null && Time <= 7){
                                        movimientox[0] = (Math.round(pym * 100) / 100f)
                                    }else if(gesto == "l" && indice != null && Time >= 7 && Time <= 14){
                                        movimientox[1] = (Math.round(pym * 100) / 100f)
                                    }else if(gesto == "l" && indice != null && Time >= 14){
                                        movimientox[2] = (Math.round(pym * 100) / 100f)
                                        Log.e(TAG,"muneca: "+movimientox.toString())
                                        if((movimientox[1]-movimientox[0])==(movimientox[1]-movimientox[2]) && movimientox[1]!= movimientox[2] ) {
                                            audio = R.raw.lunes
                                            texto?.text = "Lunes"
                                            Media = MediaPlayer.create(requireContext(), audio)
                                            Media?.start()

                                            for (i in movimientox.indices) {
                                                movimientox[i] = 0f
                                            }

                                            Time = 0
                                        }
                                    }

                                    if(gesto == "w" && indice != null && Time <= 7){
                                        movimientox[0] = (Math.round(pym * 100) / 100f)
                                    }else if(gesto == "w" && indice != null && Time >= 7 && Time <= 14){
                                        movimientox[1] = (Math.round(pym * 100) / 100f)
                                    }else if(gesto == "w" && indice != null && Time >= 14){
                                        movimientox[2] = (Math.round(pym * 100) / 100f)
                                        Log.e(TAG,"muneca: "+movimientox.toString())
                                        if((movimientox[1]-movimientox[0])==(movimientox[1]-movimientox[2]) && movimientox[1]!= movimientox[2]) {
                                            audio = R.raw.miercoles
                                            texto?.text = "miercoles"
                                            Media = MediaPlayer.create(requireContext(), audio)
                                            Media?.start()

                                            for (i in movimientox.indices) {
                                                movimientox[i] = 0f
                                            }

                                            Time = 0
                                        }
                                    }

                                    if(gesto == "i" && indice != null && Time <= 7){
                                        movimientox[0] = (Math.round(pym * 100) / 100f)
                                    }else if(gesto == "i" && indice != null && Time >= 7 && Time <= 14){
                                        movimientox[1] = (Math.round(pym * 100) / 100f)
                                    }else if(gesto == "i" && indice != null && Time >= 14){
                                        movimientox[2] = (Math.round(pym * 100) / 100f)
                                        Log.e(TAG,"muneca: "+movimientox.toString())
                                        if((movimientox[1]-movimientox[0])==(movimientox[1]-movimientox[2]) && movimientox[1]!= movimientox[2]) {
                                            audio = R.raw.jueves
                                            texto?.text = "Jueves"
                                            Media = MediaPlayer.create(requireContext(), audio)
                                            Media?.start()

                                            for (i in movimientox.indices) {
                                                movimientox[i] = 0f
                                            }

                                            Time = 0
                                        }
                                    }

                                    if(gesto == "v" && indice != null && Time <= 7){
                                        movimientox[0] = (Math.round(pym * 100) / 100f)
                                    }else if(gesto == "v" && indice != null && Time >= 7 && Time <= 14){
                                        movimientox[1] = (Math.round(pym * 100) / 100f)
                                    }else if(gesto == "v" && indice != null && Time >= 14){
                                        movimientox[2] = (Math.round(pym * 100) / 100f)
                                        Log.e(TAG,"muneca: "+movimientox.toString())
                                        if((movimientox[1]-movimientox[0])==(movimientox[1]-movimientox[2]) && movimientox[1]!= movimientox[2]) {
                                            audio = R.raw.viernes
                                            texto?.text = "viernes"
                                            Media = MediaPlayer.create(requireContext(), audio)
                                            Media?.start()

                                            for (i in movimientox.indices) {
                                                movimientox[i] = 0f
                                            }

                                            Time = 0
                                        }
                                    }

                                    if(gesto == "d" && indice != null && Time <= 7){
                                        movimientox[0] = (Math.round(pym * 100) / 100f)
                                    }else if(gesto == "d" && indice != null && Time >= 7 && Time <= 14){
                                        movimientox[1] = (Math.round(pym * 100) / 100f)
                                    }else if(gesto == "d" && indice != null && Time >= 14){
                                        movimientox[2] = (Math.round(pym * 100) / 100f)
                                        Log.e(TAG,"muneca: "+movimientox.toString())
                                        if((movimientox[1]-movimientox[0])==(movimientox[1]-movimientox[2]) && movimientox[1]!= movimientox[2]) {
                                            audio = R.raw.domingo
                                            texto?.text = "domingo"
                                            Media = MediaPlayer.create(requireContext(), audio)
                                            Media?.start()

                                            for (i in movimientox.indices) {
                                                movimientox[i] = 0f
                                            }

                                            Time = 0
                                        }
                                    }

                                    if(gesto == "s" && indice != null && Time <= 7){
                                        movimientox[0] = (Math.round(pym * 100) / 100f)
                                    }else if(gesto == "s" && indice != null && Time >= 7 && Time <= 14){
                                        movimientox[1] = (Math.round(pym * 100) / 100f)
                                    }else if(gesto == "s" && indice != null && Time >= 14){
                                        movimientox[2] = (Math.round(pym * 100) / 100f)
                                        Log.e(TAG,"muneca: "+movimientox.toString())
                                        if((movimientox[1]-movimientox[0])==(movimientox[1]-movimientox[2]) && movimientox[1]!= movimientox[2]) {
                                            audio = R.raw.sabado
                                            texto?.text = "sabado"
                                            Media = MediaPlayer.create(requireContext(), audio)
                                            Media?.start()

                                            for (i in movimientox.indices) {
                                                movimientox[i] = 0f
                                            }

                                            Time = 0
                                        }
                                    }

                                    //Adicion 1 ------------------------------------------------------------------------------------
                                    if(gesto == "g" && indice != null && Time <= 7){
                                        movimientox[0] = (Math.round(pym * 100) / 100f)
                                    }else if(gesto == "g" && indice != null && Time >= 7 && Time <= 14){
                                        movimientox[1] = (Math.round(pym * 100) / 100f)
                                    }else if(gesto == "g" && indice != null && Time >= 14){
                                        movimientox[2] = (Math.round(pym * 100) / 100f)
                                        Log.e(TAG,"muneca: "+movimientox.toString())
                                        if((movimientox[1]-movimientox[0])==(movimientox[1]-movimientox[2]) && movimientox[1]!= movimientox[2] ) {
                                            audio = R.raw.gustar
                                            texto?.text = "Gustar"
                                            Media = MediaPlayer.create(requireContext(), audio)
                                            Media?.start()

                                            if(contador==3){
                                                Oracion.add("gustar")
                                            }

                                            for (i in movimientox.indices) {
                                                movimientox[i] = 0f
                                            }

                                            Time = 0
                                        }
                                    }
                                    //Adicion 1 -------------------------------------------------------------------------------------

                                    if(gesto == "e"){

                                        val pulgar = (Math.round(px * 100) / 100f)
                                        val puntom = (Math.round(pxm * 100) / 100f)

                                        Log.e(TAG,"" + pulgar + " :::" +puntom)
                                        if(puntom > pulgar){
                                            movimientomes[0]=pulgar
                                            movimientomes[1]=puntom
                                        }else if (puntom<pulgar && movimientomes[0]!=0.00f && movimientomes[1]!=0.00f ){
                                            if(movimientomes[0] <= pulgar){
                                                audio = R.raw.enero
                                                texto?.text = "enero"
                                                Media = MediaPlayer.create(requireContext(), audio)
                                                Media?.start()

                                                for (i in movimientomes.indices) {
                                                    movimientomes[i] = 0f
                                                }

                                                Time=0
                                            }
                                        }

                                        Log.w(TAG,movimientomes.toString())
                                    }

                                    if(gesto == "i"){

                                        val pulgar = (Math.round(px * 100) / 100f)
                                        val puntom = (Math.round(pxm * 100) / 100f)

                                        Log.e(TAG,"" + pulgar + " :::" +puntom)
                                        if(puntom > pulgar){
                                            movimientomes[0]=pulgar
                                            movimientomes[1]=puntom
                                        }else if (puntom<pulgar && movimientomes[0]!=0.00f && movimientomes[1]!=0.00f ){
                                            if(movimientomes[0] <= pulgar){
                                                audio = R.raw.junio
                                                texto?.text = "junio"
                                                Media = MediaPlayer.create(requireContext(), audio)
                                                Media?.start()

                                                for (i in movimientomes.indices) {
                                                    movimientomes[i] = 0f
                                                }

                                                Time=0
                                            }
                                        }

                                        Log.w(TAG,movimientomes.toString())
                                    }

                                    if(gesto == "s"){

                                        val pulgar = (Math.round(px * 100) / 100f)
                                        val puntom = (Math.round(pxm * 100) / 100f)

                                        Log.e(TAG,"" + pulgar + " :::" +puntom)
                                        if(puntom > pulgar){
                                            movimientomes[0]=pulgar
                                            movimientomes[1]=puntom
                                        }else if (puntom<pulgar && movimientomes[0]!=0.00f && movimientomes[1]!=0.00f ){
                                            if(movimientomes[0] <= pulgar){
                                                audio = R.raw.septiembre
                                                texto?.text = "septiembre"
                                                Media = MediaPlayer.create(requireContext(), audio)
                                                Media?.start()

                                                for (i in movimientomes.indices) {
                                                    movimientomes[i] = 0f
                                                }

                                                Time=0
                                            }
                                        }



                                        Log.w(TAG,movimientomes.toString())
                                    }

                                    if(gesto == "o"){

                                        val pulgar = (Math.round(px * 100) / 100f)
                                        val puntom = (Math.round(pxm * 100) / 100f)

                                        Log.e(TAG,"" + pulgar + " :::" +puntom)
                                        if(puntom > pulgar){
                                            movimientomes[0]=pulgar
                                            movimientomes[1]=puntom
                                        }else if (puntom<pulgar && movimientomes[0]!=0.00f && movimientomes[1]!=0.00f ){
                                            if(movimientomes[0] <= pulgar){
                                                audio = R.raw.octubre
                                                texto?.text = "octubre"
                                                Media = MediaPlayer.create(requireContext(), audio)
                                                Media?.start()

                                                for (i in movimientomes.indices) {
                                                    movimientomes[i] = 0f
                                                }

                                                Time=0
                                            }
                                        }

                                        Log.w(TAG,movimientomes.toString())
                                    }

                                    if(gesto == "u"){

                                        val pulgar = (Math.round(px * 100) / 100f)
                                        val puntom = (Math.round(pxm * 100) / 100f)

                                        Log.e(TAG,"" + pulgar + " :::" +puntom)
                                        if(puntom > pulgar){
                                            movimientomes[0]=pulgar
                                            movimientomes[1]=puntom
                                        }else if (puntom<pulgar && movimientomes[0]!=0.00f && movimientomes[1]!=0.00f ){
                                            if(movimientomes[0] <= pulgar){
                                                audio = R.raw.noviembre
                                                texto?.text = "noviembre"
                                                Media = MediaPlayer.create(requireContext(), audio)
                                                Media?.start()

                                                for (i in movimientomes.indices) {
                                                    movimientomes[i] = 0f
                                                }

                                                Time=0
                                            }
                                        }

                                        Log.w(TAG,movimientomes.toString())
                                    }
                                } else {
                                    Time = 0
                                }

                                if (Time > TimeStop) {
                                    Time = 0
                                }

                                Log.d(TAG, "Tiempo: $Time")

                                // Tiempos -----------------------------------------------------------------------------
                                if (gesto == "a" && PalabraAnterior == "l") {
                                    audio = R.raw.manana
                                    texto?.text = "Mañana"
                                    Media = MediaPlayer.create(requireContext(), audio)
                                    Media?.start()
                                    Time = 0

                                    if(contador==3){
                                        Oracion.add("manana")
                                    }
                                }

                                if (gesto == "b" && PalabraAnterior == "f") {
                                    audio = R.raw.febrero
                                    texto?.text = "febrero"
                                    Media = MediaPlayer.create(requireContext(), audio)
                                    Media?.start()
                                    Time = 0
                                }

                                if (gesto == "b" && PalabraAnterior == "w") {
                                    audio = R.raw.mayo
                                    texto?.text = "mayo"
                                    Media = MediaPlayer.create(requireContext(), audio)
                                    Media?.start()
                                    Time = 0
                                }

                                if (gesto == "hola" && PalabraAnterior == "l") {
                                    audio = R.raw.hola
                                    texto?.text = "hola"
                                    Media = MediaPlayer.create(requireContext(), audio)
                                    Media?.start()
                                    Time = 0
                                }

                                if (gesto == "nombre" && PalabraAnterior == "l") {
                                    audio = R.raw.nombre
                                    texto?.text = "nombre"
                                    Media = MediaPlayer.create(requireContext(), audio)
                                    Media?.start()
                                    Time = 0
                                }

                                if (Time >TimeStop-1) {
                                    if (Time == TimeStop) {
                                        Media = MediaPlayer.create(requireContext(), audio)
                                        Media?.start()

                                        if(gesto == "i"){
                                            contador = contador + 1
                                        }
                                        Time = 0
                                    }
                                }

                                PalabraAnterior = gesto

                                if(contador == 3){
                                    texto?.text = Oracion.toString()
                                }else if(contador == 4){

                                    if(Oracion[0] == "manana" && Oracion[1] == "gustar"){
                                            audio = R.raw.manana
                                            Media = MediaPlayer.create(requireContext(), audio)
                                            Media?.start()
                                        Media?.setOnCompletionListener {
                                            audio = R.raw.gustara
                                            Media = MediaPlayer.create(requireContext(), audio)
                                            Media?.start()

                                            Media?.setOnCompletionListener {
                                                if(Oracion[2] == "manzana" ){
                                                    audio = R.raw.la
                                                    Media = MediaPlayer.create(requireContext(), audio)
                                                    Media?.start()
                                                    Media?.setOnCompletionListener {
                                                        audio = R.raw.manzana
                                                        Media = MediaPlayer.create(requireContext(), audio)
                                                        Media?.start()
                                                    }
                                                }
                                            }
                                        }

                                    }

                                    if(Oracion[0] == "gustar" && Oracion[1] == "manzana"){
                                        audio = R.raw.gusta
                                        Media = MediaPlayer.create(requireContext(), audio)
                                        Media?.start()

                                        Media?.setOnCompletionListener {
                                            audio = R.raw.la
                                            Media = MediaPlayer.create(requireContext(), audio)
                                            Media?.start()

                                            Media?.setOnCompletionListener {
                                                audio = R.raw.manzana
                                                Media = MediaPlayer.create(requireContext(), audio)
                                                Media?.start()
                                            }
                                        }

                                    }

                                    if(Oracion[0] == "ayer" && Oracion[2] == "gustar"){
                                        audio = R.raw.ayer
                                        Media = MediaPlayer.create(requireContext(), audio)
                                        Media?.start()
                                        audio = R.raw.gusto
                                        Media = MediaPlayer.create(requireContext(), audio)
                                        Media?.start()
                                        if(Oracion[0] == "manzana" ){
                                            audio = R.raw.la
                                            Media = MediaPlayer.create(requireContext(), audio)
                                            Media?.start()
                                            audio = R.raw.manzana
                                            Media = MediaPlayer.create(requireContext(), audio)
                                            Media?.start()
                                        }
                                    }

                                    contador = 0
                                    Oracion.clear()
                                }

                            } else {
                                Log.d (TAG,"No se encontro el audio")
                            }

                        } else {
                            println ("No se encontró texto entre comillas")
                        }
                    }

                } else {
                    println("No se encontró texto entre comillas")
                }

            } else {

                for (i in movimiento.indices) {
                    movimiento[i] = 0f
                }
                for (i in movimientoy.indices) {

                    movimientoy[i] = 0f
                }

                score = null
                Time = 0
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
