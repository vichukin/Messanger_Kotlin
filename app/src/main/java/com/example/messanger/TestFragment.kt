package com.example.messanger

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.io.BufferedReader
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.FileReader
import java.io.FileWriter
import java.io.IOException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TestFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TestFragment : Fragment() {
    var etText: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_test, container, false);
        etText = view.findViewById<EditText>(R.id.fileText)
        val sendBtn = view.findViewById<Button>(R.id.readFile)
        val writeBtn = view.findViewById<Button>(R.id.writeFile)
        sendBtn.setOnClickListener{
            etText!!.setText(readFile())
        }
        writeBtn.setOnClickListener{
            writeText()
            testWriteFile()
        }
        return view;
    }
    fun writeText() {
        if (!etText?.getText().toString().isEmpty()) {

            val file = File(requireActivity().filesDir, "text")
            if (!file.exists()) {
                file.mkdir()
            }
            //etText.getText().toString().getBytes()
            try {
                val gpxfile = File(file, "file.txt")
                val writer = FileWriter(gpxfile)
                writer.append(etText?.getText().toString())
                writer.flush()
                writer.close()
                //output.setText(readFile());
                Toast.makeText(activity, "Saved your text", Toast.LENGTH_LONG).show()
            } catch (e: java.lang.Exception) {
            }
        }
    }

    private fun readFile(): String? {
        val fileEvents = File(requireActivity().filesDir.toString() + "/text/file.txt")
        val text = java.lang.StringBuilder()
        try {
            val br = BufferedReader(FileReader(fileEvents))
            var line: String?
            while (br.readLine().also { line = it } != null) {
                text.append(line)
                text.append('\n')
            }
            br.close()
        } catch (e: IOException) {
        }
        return text.toString()
    }


    fun testWriteFile() {
        val internalStorageDir = requireActivity().filesDir
        val alice = File(internalStorageDir, "alice001.csv")

        // Create file output stream
        var fos: FileOutputStream? = null
        try {
            fos = FileOutputStream(alice)
            val s = "Alice,25,1"
            fos.write(s.toByteArray())
            // Close the file output stream
            fos.close()
        } catch (ex: FileNotFoundException) {
            ex.printStackTrace()
        } catch (ioException: IOException) {
            ioException.printStackTrace()
        }
        // Write a line to the file
    }
}