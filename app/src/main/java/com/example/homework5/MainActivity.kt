package com.example.homework5

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import android.content.Intent
import android.widget.Toast
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private val REQUEST_CODE_SPEECH_INPUT = 100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_1.setOnClickListener{
            speak();
        }
    }
    private fun speak(){
        val myIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        myIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        myIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())

        try {
            startActivityForResult(myIntent, REQUEST_CODE_SPEECH_INPUT)
        }
        catch (e:Exception)
        {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode)
        {
            REQUEST_CODE_SPEECH_INPUT ->{
                if (resultCode == Activity.RESULT_OK && null != data)
                {
                    val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    text_v.text = result[0]
                }
            }
        }
    }
}