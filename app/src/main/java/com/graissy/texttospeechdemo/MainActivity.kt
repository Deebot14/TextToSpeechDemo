package com.graissy.texttospeechdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Toast
import com.graissy.texttospeechdemo.databinding.ActivityMainBinding
import org.w3c.dom.Text
import java.util.Locale

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private var binding: ActivityMainBinding? = null

    private var tts: TextToSpeech? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        tts = TextToSpeech(this, this)

        binding?.btnSpeak?.setOnClickListener{ view ->
            if(binding?.etEnteredText?.text!!.isEmpty()){
                Toast.makeText(
                    this@MainActivity,
                    "Please enter a text to speak",
                    Toast.LENGTH_SHORT
                ).show()
            }else{
                speakOut(binding?.etEnteredText?.text.toString())
            }
        }
    }

    // "onInit": we set the language we want to turn into speech
    override fun onInit(status: Int) {
        if(status == TextToSpeech.SUCCESS){
            val result = tts!!.setLanguage(Locale.US)

            // If the Language is missing or not supported
            if(result == TextToSpeech.LANG_MISSING_DATA ||
                result == TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("TTS", "The Language specified is not supported")
            }
        }else{
            // when "TextToSpeech" is not "SUCCESS"
            Log.e("TTS", "Initialization failed")
        }
    }


    private fun speakOut(text: String){
        // "QUEUE_FLUSH": we flush(delete) the current statement.
        // So if you press the button to turn into speech multiple times
        // it will cut the speech and start from beginning

        // With "QUEUE_ADD" if we press the button 5 times it will speak out 5 times.
        // So in other words it keeps adding the speeches together
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    override fun onDestroy() {
        super.onDestroy()

        if(tts != null){
            tts?.stop()
            tts?.shutdown()
        }

        binding = null
    }
}