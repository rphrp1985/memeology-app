package com.rishi.memology

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.activity_memepage.*
import android.content.Intent
import android.net.Uri

import android.provider.MediaStore.Images
import android.graphics.Bitmap

import android.content.ContentValues
import android.widget.ImageView
import java.io.OutputStream
import java.lang.Exception


class memepage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memepage)

        loadmeme()
    }
    var currurl:String = "";
    fun loadmeme(){
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = "https://meme-api.herokuapp.com/gimme"

//        currurl=url
        progressbar.visibility= View.VISIBLE
// Request a string response from the provided URL.
        val jrobjreq = JsonObjectRequest (
            Request.Method.GET, url,null,
            { response ->
                val url= response.getString("url")

                currurl=url
                Glide.with(this).load(url).listener(object: RequestListener<Drawable> {

                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressbar.visibility= View.GONE
                        return  false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressbar.visibility= View.GONE
                        return  false
                    }


                }).into(memesImgView)
                    // Display the first 500 characters of the response string.
                },
            Response.ErrorListener {
                Toast.makeText(this,"Something went wrong!", Toast.LENGTH_LONG).show()
            }
        )



// Add the request to the RequestQueue.
        queue.add(jrobjreq)

    }




    fun sharememe(view: android.view.View) {


        val intent= Intent(Intent.ACTION_SEND)

        intent.putExtra(Intent.EXTRA_TEXT, "Hey checkout this meme\n$currurl ")
        intent.type="text/plain"
        val chooser= Intent.createChooser(intent,"Share meme using..")
        startActivity(chooser)

    }
    fun nextmeme(view: android.view.View) {
        loadmeme()
    }
}