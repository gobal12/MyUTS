package com.example.myuts

import android.app.AlertDialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class HomeActivity : AppCompatActivity() {

    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        listView = findViewById(R.id.listView)

        val items = arrayOf("Tenda", "Flysheet", "Sleeping Bag", "Tas Carrier", "Matras", "Nasting", "Kompor",)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = items[position]
            // Tampilkan dialog pemilihan rentang waktu atau tanggal ketika item dipilih
            showDateRangePicker(selectedItem)
        }
    }

    private fun showDateRangePicker(itemName: String) {
        val builder = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.dialog_date_range, null)
        builder.setView(dialogView)
        builder.setTitle("Durasi Peminjaman untuk $itemName")

        val startDatePicker = dialogView.findViewById<DatePicker>(R.id.startDatePicker)
        val endDatePicker = dialogView.findViewById<DatePicker>(R.id.endDatePicker)

        builder.setPositiveButton("OK") { dialog, _ ->
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val startDate = Calendar.getInstance()
            startDate.set(startDatePicker.year, startDatePicker.month, startDatePicker.dayOfMonth)
            val endDate = Calendar.getInstance()
            endDate.set(endDatePicker.year, endDatePicker.month, endDatePicker.dayOfMonth)

            val startDateFormatted = sdf.format(startDate.time)
            val endDateFormatted = sdf.format(endDate.time)

            // Tampilkan popup sukses dengan detail peminjaman
            showSuccessPopup("Durasi Peminjaman untuk $itemName: $startDateFormatted - $endDateFormatted")
            dialog.dismiss()
        }

        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.cancel()
        }
        builder.show()
    }

    private fun showSuccessPopup(inputText: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Sukses")
        builder.setMessage("Data anda: $inputText")
        builder.setPositiveButton("OK", null)
        builder.show()
    }
}
