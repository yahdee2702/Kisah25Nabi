package com.yahdi.kisah25nabi.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.yahdi.kisah25nabi.R
import com.yahdi.kisah25nabi.data.KisahResponse
import com.yahdi.kisah25nabi.databinding.ActivityDetailBinding
import com.yahdi.kisah25nabi.utils.fromHttpToHttpsString

class DetailActivity : AppCompatActivity() {
    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding as ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val data = intent.getParcelableExtra<KisahResponse>(EXTRA_DATA)
        val nama = "Kisah " + data?.name
        supportActionBar?.title = nama

        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        data?.let {
            binding.apply {
                detailDesk.text = data.description
                detailNama.text = data.name
                detailTempat.text = data.tmp
                val usia = "Usia: " + data.usia + " tahun"
                detailUsia.text = usia

                Glide.with(this@DetailActivity)
                    .load(fromHttpToHttpsString(data.imageUrl))
                    .into(detailImage)
            }
        }
    }

    companion object {
        const val EXTRA_DATA = "data"
    }
}