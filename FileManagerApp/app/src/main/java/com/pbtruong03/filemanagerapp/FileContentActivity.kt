package com.pbtruong03.filemanagerapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.File

class FileContentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_content)

        val path = intent.getStringExtra("path") ?: return
        val file = File(path)

        if (file.exists() && file.isFile) {
            val content = file.readText()
            val textView: TextView = findViewById(R.id.textViewContent)
            textView.text = content
        }
    }
}