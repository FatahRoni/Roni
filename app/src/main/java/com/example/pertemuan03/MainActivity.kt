package com.example.pertemuan03

import android.content.Intent
import android.content.Intent.getIntent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import com.example.pertemuan03.databinding.ActivityAktivasiAkunBinding
import com.example.pertemuan03.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    // menggunakan username dengan variabel pada fungsi toast
    private val usernametext: String get() = binding.edtLogin.text.toString()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // inflate layout ke binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // masih belum memahami konsep doontextchanged
//        binding.edtLogin.doOnTextChanged { text, start, before, count ->
//            Toast.makeText(
//                this,"Text: $text, Start: $start before: $before count: $count", Toast.LENGTH_SHORT
//            ).show()
//        }   // do on text

//        binding.btnMasuk.setOnClickListener {
//            Toast.makeText(this,"Username : ${usernametext} password : ${binding.edtPassword.text}", Toast.LENGTH_SHORT).show()
//        }

        binding.edtLogin.doOnTextChanged { _, _, _,_  ->
        validasiBtnMasuk()
        }

        binding.edtPassword.doOnTextChanged { _, _, _,_  ->
            validasiBtnMasuk()
        }

        binding.txtLupaPass.setOnClickListener {
            // cara pertama intent untuk menyambungkan antar activity (layout)
//            startActivity(Intent (this@MainActivity,AktivasiAkun::class.java) )
            // cara kedua
//            val intent = Intent(this,AktivasiAkun::class.java)
//            startActivity(intent)
            // tugas ambil data
            aktivasiAkun.launch(AktivasiAkun.getIntent(this))
        }

        if (intent.getStringExtra("IdUser")?.isNotBlank() == true) {
            binding.kotakInfoAkun.isVisible = true
            binding.rekeningUser.text = intent.getStringExtra("rekening")
            binding.idUser.text = intent.getStringExtra("IdUser")
        }
    }

    private fun validasiBtnMasuk (){
        binding.btnMasuk.isEnabled = binding.edtLogin.text.toString().isNotBlank()
                && binding.edtPassword.text.toString().isNotBlank() && passwordBenar
    }

    private val passwordBenar: Boolean get () {
        val kataPassword = binding.edtPassword.text.toString()
        val PasswordBenar = kataPassword.contains("[a-z]".toRegex())
                && kataPassword.contains("[A-Z]".toRegex())
                && kataPassword.contains("[0-9]".toRegex())
                && kataPassword.length >= 8
        binding.txtNotesPassword.isInvisible = PasswordBenar

        return PasswordBenar
    }

    private val aktivasiAkun = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == RESULT_OK) {
            val intent = it.data ?: return@registerForActivityResult
            val username = intent.getStringExtra("user_data")
//            val accountNumber = intent.getStringExtra("account_number")

            binding.edtLogin.setText(username)
        }
    }

}