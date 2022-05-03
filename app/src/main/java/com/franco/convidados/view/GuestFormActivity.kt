package com.franco.convidados.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.franco.convidados.viewmodel.GuestFormViewModel
import com.franco.convidados.R
import com.franco.convidados.databinding.ActivityGuestFormBinding
import com.franco.convidados.service.constants.GuestConstants

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityGuestFormBinding
    private lateinit var mViewModel: GuestFormViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mViewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        loadData()
        setListener()
        observe()
    }

    override fun onClick(v: View) {
        val id = v.id
        if (id == R.id.button_save) {
            val name = binding.editName.text.toString()
            val presence = binding.radioPresence.isChecked
            mViewModel.save(name, presence)
        }
    }

    private fun loadData() {
        val bundle = intent.extras
        if (bundle != null) {
            val id = bundle.getInt(GuestConstants.GUESTID)
            mViewModel.load(id)
        }
    }

    private fun observe() {
        mViewModel.saveGuest.observe(this, Observer {
           if (it) {
               Toast.makeText(this,"Sucesso",Toast.LENGTH_SHORT).show()
           }else {
               Toast.makeText(this,"Falha",Toast.LENGTH_SHORT).show()
           }
            finish()
        })
        mViewModel.guest.observe(this, Observer {
            binding.editName.setText(it.name)
            if (it.presence) {
                binding.radioPresence.isChecked = true
            }else {
                binding.radioAbsent.isChecked = true
            }
        })
    }

    private fun setListener() {
        binding.buttonSave.setOnClickListener(this@GuestFormActivity)

    }
}