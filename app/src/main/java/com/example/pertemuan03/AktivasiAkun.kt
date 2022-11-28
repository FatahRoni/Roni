package com.example.pertemuan03

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pertemuan03.databinding.ActivityAktivasiAkunBinding
import com.example.pertemuan03.databinding.ActivityMainBinding

class AktivasiAkun : AppCompatActivity() {
    companion object {
        private const val USERNAME_DATA = "username"

        fun getIntent (context: Context): Intent {
            return Intent(context, AktivasiAkun::class.java).apply {
            }
        }
    }
    private lateinit var binding: ActivityAktivasiAkunBinding
    private val username: String get() = binding.idUserr.text.toString()
    private val rekening: String get() = binding.rekening.text.toString()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAktivasiAkunBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.selanjutnya.setOnClickListener {
            // tanpa mengembalikan data
//            startActivity(
//                Intent(this@AktivasiAkun,MainActivity::class.java)
//                    .putExtra("IdUser",username).putExtra("rekening",rekening)
//            )
            // mengembalikan data
            setResult(RESULT_OK,Intent().apply {
                putExtra("user_data",binding.idUserr.text.toString())
            })
            finish()
        }
    }
}