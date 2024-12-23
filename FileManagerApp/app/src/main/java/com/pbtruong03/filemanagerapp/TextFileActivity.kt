package com.pbtruong03.filemanagerapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pbtruong03.filemanagerapp.databinding.ActivityTextFileBinding
import java.io.File

class TextFileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTextFileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTextFileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val filePath = intent.getStringExtra("FILE_PATH")
        filePath?.let{
            val file = File(it)
            try{
                binding.textFileContent.text = file.readText()
            }catch (e:Exception){
                val message: String = "Unable to read file! ${e.localizedMessage}"
                binding.textFileContent.text = message
            }

        }
    }
}