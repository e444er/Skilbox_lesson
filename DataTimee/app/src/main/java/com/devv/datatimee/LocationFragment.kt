package com.devv.datatimee


import android.Manifest
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.devv.datatimee.databinding.LocationFragmentBinding
import com.google.android.gms.location.LocationServices
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class LocationFragment : Fragment(R.layout.location_fragment) {

    private lateinit var binding: LocationFragmentBinding
    private var rationaleDialog: AlertDialog? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = LocationFragmentBinding.bind(view)
        binding.button.setOnClickListener {
            showCurPermissionCheck()
            timeData()
            dataPicker()
            imageGlide()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun dataPicker() {
        val curDateTime = LocalDateTime.now()
        binding.datapicker.setOnClickListener {
            DatePickerDialog(
                requireContext(),
                { _, year, month, dayOfMonth ->
                    TimePickerDialog(
                        requireContext(),
                        { _, hourOfDay, minute ->
                            val data =
                                LocalDateTime.of(year, month + 1, dayOfMonth, hourOfDay, minute)
                            binding.textTime.text = data.toString()
                            Toast.makeText(requireContext(), "$data", Toast.LENGTH_SHORT).show()
                        },
                        curDateTime.hour,
                        curDateTime.minute,
                        true
                    )
                        .show()
                },
                curDateTime.year,
                curDateTime.month.value - 1,
                curDateTime.dayOfMonth
            )
                .show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun timeData() {
        // val curDateTime = LocalDateTime.now()
        val formattor = DateTimeFormatter.ofPattern("HH:mm dd/MM/yy")
            .withZone(ZoneId.systemDefault())

        binding.textTime.text = formattor.toString()
    }

    private fun showCurPermissionCheck() {
        val isLocationPerGranted = ActivityCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        if (isLocationPerGranted) {
            showLocationInfo()
        } else {
            val needRationale = ActivityCompat.shouldShowRequestPermissionRationale(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            if (needRationale) {
                showDialog()
            } else {
                requestLocationPermission()
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
            showLocationInfo()
        } else {
            val needRationale = ActivityCompat.shouldShowRequestPermissionRationale(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            if (needRationale) {
                showDialog()
            }
        }
    }

    private fun showLocationInfo() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            Toast.makeText(requireContext(), "check", Toast.LENGTH_LONG).show()
            //
            return
        }
        LocationServices.getFusedLocationProviderClient(requireContext())
            .lastLocation
            .addOnSuccessListener {
                it?.let {
                    binding.textView.text = """
                        Lat = ${it.latitude}
                        Lng = ${it.longitude}
                        Alt = ${it.altitude}
                        Speed = ${it.speed}
                        Accuracy = ${it.accuracy}
                    """.trimIndent()
                } ?: Toast.makeText(requireContext(), "локации нет", Toast.LENGTH_SHORT).show()

            }
            .addOnCanceledListener {
                Toast.makeText(requireContext(), "Запрос локации был отменен", Toast.LENGTH_SHORT)
                    .show()
            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), "Запрос локации был НЕУДАЧНО", Toast.LENGTH_SHORT)
                    .show()
            }
    }

    private fun showDialog() {
        rationaleDialog = AlertDialog.Builder(requireContext())
            .setMessage("Разрешения для отображения информации по локации")
            .setPositiveButton("OK") { _, _ -> requestLocationPermission() }
            .setNegativeButton("Отмена", null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        rationaleDialog?.dismiss()
        rationaleDialog = null
    }

    private fun requestLocationPermission() {
        requestPermissions(
            arrayOf(
                Manifest
                    .permission.ACCESS_FINE_LOCATION
            ), PERCODE
        )
    }

    private fun imageGlide() {
        Glide
            .with(this)
            .load("https://cdn.pixabay.com/photo/2015/02/01/22/37/hourglass-620397__480.jpg")
            .centerCrop()
            .into(binding.imageView);
    }

    companion object {
        private const val PERCODE = 3232
    }
}