package com.jin.guess

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.content_materia.*


class MateriaActivity : AppCompatActivity() {
    val secreNumber = SecreNumber()
    val TAG = MateriaActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_materia)
        setSupportActionBar(findViewById(R.id.toolbar))
        Log.d(TAG, "secret: " + secreNumber.secret)

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            AlertDialog.Builder(this)
                .setTitle("Replay game")
                .setMessage("Are you sure?")
                .setPositiveButton(getString(R.string.ok), {dialog, which ->
                    secreNumber.reset()
                    counter.setText(secreNumber.count.toString())
                    number.setText("")
                })
                .setNeutralButton("Cancel", null)
                .show()
        }
        counter.setText(secreNumber.count.toString())
    }
    fun check(view : View) {
        val n = number.text.toString().toInt()
        println("number: $n")
        Log.d(TAG, "number:" + n)
        val diff = secreNumber.validate(n)
        var message = getString(R.string.yes_you_got_it)
        when {
            diff < 0 -> message = getString(R.string.bigger)
            diff > 0 -> message = getString(R.string.smaller)
            diff == 0 && secreNumber.count <3 -> message = getString(R.string.excellent) +
                    "\t" + secreNumber.secret.toString() +"\t" + "!"

        }
/*        if (diff < 0 ) {
            message = getString(R.string.bigger)
        } else if (diff > 0) {
            message = getString(R.string.smaller)
        }*/
        counter.setText(secreNumber.count.toString())
//        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.dialog_title))
            .setMessage(message)
            .setPositiveButton(getString(R.string.ok), null)
            .show()
    }
}