package com.develou.datepicker_en_android

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import co.com.citasmedicasinimuto.R
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId

@RequiresApi(Build.VERSION_CODES.O)
class DatePickerFragment : DialogFragment() {

    private var observer: DatePickerDialog.OnDateSetListener? = null

    private var day: Int
    private var month: Int
    private var year: Int

    init {
        LocalDate.now().let { now ->
            year = now.year
            month = now.monthValue
            day = now.dayOfMonth
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { args ->
            year = args.getInt(YEAR_ARG)
            month = args.getInt(MONTH_ARG)
            day = args.getInt(DAY_ARG)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = DatePickerDialog(
            requireActivity(),
            R.style.Theme_CitasMedicasInimuto,
            observer,
            year,
            month,
            day
        )

        // Descomenta para añadir límites al selector de fechas
        /*val today = LocalDateTime.now()
        val sevenDaysAgo = today.minusDays(7)
        dialog.datePicker.minDate= sevenDaysAgo.toMs()
        dialog.datePicker.maxDate = today.toMs()*/

        return dialog
    }

    private fun LocalDateTime.toMs(): Long {
        return atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        // [Acciones si deseas sobrescribir al presionar "CANCELAR"]
    }

    companion object {
        const val YEAR_ARG = "args.year"
        const val MONTH_ARG = "args.month"
        const val DAY_ARG = "args.day"

        fun newInstance(
            year: Int, month: Int, day: Int,
            observer: DatePickerDialog.OnDateSetListener
        ): DatePickerFragment {
            val datePicker = DatePickerFragment()
            datePicker.arguments = Bundle().apply {
                val adjustMonth = month - 1

                putInt(YEAR_ARG, year)
                putInt(MONTH_ARG, adjustMonth)
                putInt(DAY_ARG, day)
            }
            datePicker.observer = observer
            return datePicker
        }
    }
}