package com.franco.convidados.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.franco.convidados.databinding.FragmentAbsentBinding
import com.franco.convidados.service.constants.GuestConstants
import com.franco.convidados.view.adapter.GuestAdapter
import com.franco.convidados.view.listener.GuestListener
import com.franco.convidados.viewmodel.GuestsViewModel

class AbsentFragment : Fragment() {

    private lateinit var mViewModel: GuestsViewModel
    private val mAdapter: GuestAdapter = GuestAdapter()
    private lateinit var mListener: GuestListener
    private var _binding: FragmentAbsentBinding? = null


    private val binding get() = _binding!!


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, s: Bundle?): View {
        mViewModel = ViewModelProvider(this)[GuestsViewModel::class.java]
        _binding = FragmentAbsentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recycler = binding.recyclerAbsents

        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = mAdapter

        mListener = object : GuestListener {
            override fun onClick(id: Int) {
                val intent = Intent(context, GuestFormActivity::class.java)

                val bundle = Bundle()
                bundle.putInt(GuestConstants.GUESTID, id)
                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                mViewModel.delete(id)
                mViewModel.load(GuestConstants.FILTER.ABSENT)
            }
        }
        mAdapter.attchListener(mListener)
        observer()
        return root
    }

    override fun onResume() {
        super.onResume()
        mViewModel.load(GuestConstants.FILTER.ABSENT)
    }

    private fun observer() {
        mViewModel.guestList.observe(viewLifecycleOwner) {
            mAdapter.updateGuests(it)

        }
    }
}