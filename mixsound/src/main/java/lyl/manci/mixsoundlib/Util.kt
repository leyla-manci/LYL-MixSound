package lyl.manci.mixsoundlib

import android.content.Context
import android.os.Build
import android.speech.tts.TextToSpeech
import android.widget.Toast


/**       Code with ❤  ´• ل •`   ❤
▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬
▬     Created by Leyla Akmancı                ▬
▬     ▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬    ▬
▬     leyla.manci@gmail.com                           ▬
▬     ▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬     ▬
▬     17/07/2020 - 03:44        ▬
▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬
 */

infix fun String.toast(context: Context?) = Toast.makeText(context, this, Toast.LENGTH_SHORT).show()

@Suppress("DEPRECATION")
fun TextToSpeech.speak() = when {
    Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> {
        this.speak(MixSound.recordSound, TextToSpeech.QUEUE_FLUSH, null, null)
    }
    else -> this.speak(MixSound.recordSound, TextToSpeech.QUEUE_FLUSH, null)
}