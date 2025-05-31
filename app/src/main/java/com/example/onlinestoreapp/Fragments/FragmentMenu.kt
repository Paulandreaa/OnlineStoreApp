package com.example.onlinestoreapp.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.onlinestoreapp.R
import com.example.onlinestoreapp.activities.Loginactivity
import com.example.onlinestoreapp.activities.MapaActivity
import com.example.onlinestoreapp.activities.Registeractivitys
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FragmentMenu  : DialogFragment() {
private lateinit var btnubicacion: Button
    private lateinit var btncerrarsesion: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.frag_menu, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnubicacion = view.findViewById(R.id.btnUbicacion)
        btnubicacion.setOnClickListener {
            val intent = Intent(requireContext(), MapaActivity::class.java)
            startActivity(intent)
            dismiss()
        }
        btncerrarsesion = view.findViewById(R.id.btnCerrarSesion)
        btncerrarsesion.setOnClickListener {
            val intent = Intent(requireContext(),Loginactivity::class.java)
            startActivity(intent)
            dismiss()
        }
    }
    override fun onStart() {
        super.onStart()

        // Personaliza el tamaño y la posición
        dialog?.window?.apply {
            setLayout((resources.displayMetrics.widthPixels * 0.7).toInt(), ViewGroup.LayoutParams.MATCH_PARENT)
            setGravity(Gravity.START)
            setBackgroundDrawableResource(android.R.color.transparent)
        }
    }
    override fun getTheme(): Int = R.style.RightDialogTheme

}