package lyl.manci.mixsound

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import lyl.manci.mixsoundlib.MixSound
import lyl.manci.mixsoundlib.SoundType


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val micImg = findViewById<ImageView>(R.id.imageMicId)
        val slowImg = findViewById<ImageView>(R.id.imageSlowId)
        val fastImg = findViewById<ImageView>(R.id.imageFastId)
        val chipmunkImg = findViewById<ImageView>(R.id.imageChipmunkId)
        val dartVaderImg = findViewById<ImageView>(R.id.ImageDarthVaderId)
        micImg.setOnClickListener {
            MixSound.getInstance(this).recordSound()

            "imgMic clicked !! " extShowToast this
        }
        slowImg.setOnClickListener {
            MixSound.getInstance(this).changeSound(SoundType.Slow)

            "slowImg clicked !! " extShowToast this
        }
        fastImg.setOnClickListener {
            MixSound.getInstance(this).changeSound(SoundType.Fast)

            "fastImg clicked !! " extShowToast this
        }
        chipmunkImg.setOnClickListener {
            MixSound.getInstance(this).changeSound(SoundType.Chipmunk)

            "chipMunkImg clicked !! " extShowToast this
        }
        dartVaderImg.setOnClickListener {
            MixSound.getInstance(this).changeSound(SoundType.DarthVader)

            "dartVaderImg clicked !! " extShowToast this
        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            MixSound.CODE_SPEECH_RECOGNIZER -> {
                data?.let {
                    val result = it.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    MixSound.recordSound = result.first()
                    /*                  MixSound.recordSound = """

                                         What is going on


                                      """.trimIndent()*/

                }
            }
        }
    }

    override fun onStop() {

        MixSound.getInstance(this).textToSpeech.stop()
        MixSound.getInstance(this).textToSpeech.shutdown()

        super.onStop()
    }
}


infix fun String.extShowToast(context: Context) {
    Toast.makeText(context, this, Toast.LENGTH_SHORT).show()
}