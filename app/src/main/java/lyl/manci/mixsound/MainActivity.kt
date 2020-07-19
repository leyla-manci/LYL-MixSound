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


const val MIX_SOUND_INITIAL_VALUE = "LYLTESTINITIALVALUE"

class MainActivity : AppCompatActivity() {
    val ttsInstance = MixSound.getInstance(this)
    val mainContext: Context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MixSound.recordSound = MIX_SOUND_INITIAL_VALUE
        val micImg = findViewById<ImageView>(R.id.imageMicId)
        val slowImg = findViewById<ImageView>(R.id.imageSlowId)
        val fastImg = findViewById<ImageView>(R.id.imageFastId)
        val chipmunkImg = findViewById<ImageView>(R.id.imageChipmunkId)
        val dartVaderImg = findViewById<ImageView>(R.id.ImageDarthVaderId)

        micImg.setOnClickListener {
            if (MixSound.recordSound != MIX_SOUND_INITIAL_VALUE) {
                /*if it is not the first time that user save record and
                * if there is playing record at back side it should be stop
                * aim to support the user experience*/
                ttsInstance.textToSpeech.stop()
            }
            ttsInstance.recordSound()

            "imgMic clicked !! " extShowToast mainContext
        }
        slowImg.setOnClickListener {
            /*   ttsInstance.textToSpeech.stop()
               ttsInstance.changeSound(SoundType.Slow)

               "slowImg clicked !! " extShowToast this*/

            "slowImg clicked !! ".extPreparePlay(mainContext, SoundType.Slow, ttsInstance)
        }
        fastImg.setOnClickListener {
            /* ttsInstance.textToSpeech.stop()
             ttsInstance.changeSound(SoundType.Fast)

             "fastImg clicked !! " extShowToast this*/
            "fastImg clicked !! ".extPreparePlay(mainContext, SoundType.Fast, ttsInstance)
        }
        chipmunkImg.setOnClickListener {
            /*ttsInstance.textToSpeech.stop().toString() extShowToast this
            ttsInstance.changeSound(SoundType.Chipmunk)

            "chipMunkImg clicked !! " extShowToast this*/
            "chipMunkImg clicked !! ".extPreparePlay(mainContext, SoundType.Chipmunk, ttsInstance)
        }
        dartVaderImg.setOnClickListener {
            /* ttsInstance.changeSound(SoundType.DarthVader)

             "dartVaderImg clicked !! " extShowToast this*/
            "dartVaderImg clicked !! ".extPreparePlay(
                mainContext,
                SoundType.DarthVader,
                ttsInstance
            )
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

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        /*get the stored recordSound value from bundle so user can play the previous text*/
        MixSound.recordSound = savedInstanceState.getString("savedMixsoundRecord").toString()

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        /*save the recordsaound to be able to play it when user comes back*/
        outState.putString("savedMixsoundRecord", MixSound.recordSound)
    }

    override fun onStop() {

        ttsInstance.textToSpeech.stop()
        /* shutdown is should be called inscope of onDestroyed event
         otherwise restoreinstansestate can set the previous savedrecord but can not play it
         because of tts instance will be destroyed with shutdown */
        //  ttsInstance.textToSpeech.shutdown()
        super.onStop()

    }


    override fun onDestroy() {
        ttsInstance.textToSpeech.shutdown()
        super.onDestroy()
    }


}


fun String.extPreparePlay(context: Context, soundType: SoundType, insTts: MixSound) {
    /*if it is not the first time that user save record and
               * if there is playing record at back side it should be stop
               * aim to support the user experience*/

    if (MixSound.recordSound == MIX_SOUND_INITIAL_VALUE) {
        "there is no record to play !!" extShowToast context
        return
    }
    insTts.textToSpeech.stop()
    insTts.changeSound(soundType)
    this extShowToast context
}

infix fun String.extShowToast(context: Context) {
    Toast.makeText(context, this, Toast.LENGTH_SHORT).show()
}