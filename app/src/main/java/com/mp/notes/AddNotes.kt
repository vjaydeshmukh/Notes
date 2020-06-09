package com.mp.notes

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.contentValuesOf
import kotlinx.android.synthetic.main.activity_notes_add.*

class AddNotes : AppCompatActivity() {

    var edit = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes_add)

        var bundle = intent.extras

        edit = bundle!!.getBoolean("edit")
        if(edit) {
            noteName.setText(bundle!!.getString("Name").toString())
            noteDesc.setText(bundle!!.getString("Description").toString())
        }
    }

    fun endActivity(view: View){

        var dbManager = DatabaseHelper(this)
        var values = ContentValues()
        values.put("Name",noteName.text.toString())
        values.put("Description", noteDesc.text.toString())

        if(edit){
            dbManager.update(noteName.text.toString(),noteDesc.text.toString())
            finish()
        }
        else {
            val ID = dbManager.insert(noteName.text.toString(), noteDesc.text.toString())
            if (ID > 0) {
                Toast.makeText(this, "Added successfully to db", Toast.LENGTH_LONG).show()
                finish()
            } else {
                Toast.makeText(this, "Cannot add to DB", Toast.LENGTH_LONG).show()
            }
        }

        //finish()
    }
}
