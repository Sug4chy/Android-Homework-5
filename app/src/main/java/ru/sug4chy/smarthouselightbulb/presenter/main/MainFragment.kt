package ru.sug4chy.smarthouselightbulb.presenter.main

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.SeekBar
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.sug4chy.smarthouselightbulb.R
import ru.sug4chy.smarthouselightbulb.data.model.Color
import ru.sug4chy.smarthouselightbulb.data.model.LightbulbState
import ru.sug4chy.smarthouselightbulb.databinding.FragmentMainBinding
import ru.sug4chy.smarthouselightbulb.di.extensions.appComponent
import ru.sug4chy.smarthouselightbulb.di.viewModel.ViewModelFactory
import ru.sug4chy.smarthouselightbulb.presenter.UiState
import javax.inject.Inject

class MainFragment : Fragment(R.layout.fragment_main) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val binding: FragmentMainBinding by viewBinding()
    private val viewModel: MainViewModel by viewModels { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpStateSection()
        setUpBrightnessSection()
        setUpColorSection()
    }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    private fun setUpStateSection() {
        binding.stateSwitch.setOnCheckedChangeListener { _, isChecked ->
            this@MainFragment.stateSwitchCheckedChanged(
                isChecked = isChecked
            )
        }

        viewModel.currentState.observe(viewLifecycleOwner, ::currentStateReceived)
        viewModel.getCurrentState()
    }

    private fun stateSwitchCheckedChanged(isChecked: Boolean) {
        when (isChecked) {
            true -> viewModel.turnOn()
            false -> viewModel.turnOff()
        }
    }

    private fun currentStateReceived(state: UiState<LightbulbState>) =
        when (state) {
            is UiState.Success -> {
                binding.stateSection.visibility = View.VISIBLE
                binding.stateTxv.text = state.value.toString()
                binding.stateTxv.visibility = View.VISIBLE
                binding.stateSwitch.isChecked = state.value == LightbulbState.ON
                binding.stateSwitch.visibility = View.VISIBLE
                binding.stateErrTxv.visibility = View.GONE
            }

            is UiState.Failure -> {
                binding.stateSection.visibility = View.VISIBLE
                binding.stateTxv.text = LightbulbState.OFF.toString()
                binding.stateTxv.visibility = View.VISIBLE
                binding.stateSwitch.isChecked = false
                binding.stateSwitch.visibility = View.VISIBLE
                binding.stateErrTxv.text = state.message
                binding.stateErrTxv.visibility = View.VISIBLE
            }

            UiState.Loading -> {
                binding.stateSection.visibility = View.GONE
            }
        }

    private fun setUpBrightnessSection() {
        binding.brightnessSeekBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    binding.brightnessTxv.text = String.format(
                        getString(R.string.current_brightness), progress
                    )
                }

                override fun onStartTrackingTouch(seekBar: SeekBar) =
                    Unit

                override fun onStopTrackingTouch(seekBar: SeekBar) =
                    viewModel.setBrightnessLevel(seekBar.progress)
            }
        )

        viewModel.currentBrightness.observe(viewLifecycleOwner, ::currentBrightnessChanged)
    }

    private fun currentBrightnessChanged(level: UiState<Int>) =
        when (level) {
            is UiState.Success -> {
                binding.brightnessSection.visibility = View.VISIBLE
                binding.brightnessTxv.text = String.format(
                    getString(R.string.current_brightness), level.value
                )
                binding.brightnessTxv.visibility = View.VISIBLE
                binding.brightnessSeekBar.progress = level.value
                binding.brightnessSeekBar.visibility = View.VISIBLE
                binding.brightnessErrTxv.visibility = View.GONE
            }

            is UiState.Failure -> {
                binding.brightnessSection.visibility = View.VISIBLE
                binding.brightnessTxv.text = String.format(
                    getString(R.string.current_brightness), 0
                )
                binding.brightnessTxv.visibility = View.VISIBLE
                binding.brightnessSeekBar.progress = 0
                binding.brightnessSeekBar.visibility = View.VISIBLE
                binding.brightnessErrTxv.text = level.message
                binding.brightnessErrTxv.visibility = View.VISIBLE
            }

            UiState.Loading -> {
                binding.brightnessSection.visibility = View.GONE
            }
        }

    private fun setUpColorSection() {
        binding.colorsSpinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) = viewModel.setColor(
                with(viewModel.colors.value) {
                    this as UiState.Success
                    this.value[position]
                }
            )

            override fun onNothingSelected(parent: AdapterView<*>?) =
                Unit
        }

        viewModel.colors.observe(viewLifecycleOwner, ::colorsGot)
        viewModel.currentColor.observe(viewLifecycleOwner, ::currentColorChanged)

        viewModel.getAllColors()
    }

    private fun colorsGot(colors: UiState<List<String>?>) =
        when (colors) {
            is UiState.Success -> {
                binding.colorsSpinner.adapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_spinner_item,
                    colors.value ?: emptyList()
                ).apply { setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) }
                binding.colorsSpinner.visibility = View.VISIBLE
                binding.colorErrTxv.visibility = View.GONE
            }

            is UiState.Failure -> {
                binding.colorsSpinner.adapter = null
                binding.colorsSpinner.visibility = View.VISIBLE
                binding.colorErrTxv.text = colors.message
                binding.colorErrTxv.visibility = View.VISIBLE
            }

            UiState.Loading -> Unit
        }

    private fun currentColorChanged(color: UiState<Color>) =
        when (color) {
            is UiState.Success -> {
                binding.colorSection.visibility = View.VISIBLE
                binding.colorsSpinner.setSelection(
                    with(viewModel.colors.value) {
                        this as UiState.Success
                        this.value.indexOfFirst { it == color.value.color }
                    },
                    true
                )
                binding.colorsSpinner.visibility = View.VISIBLE
                binding.colorErrTxv.visibility = View.GONE

                binding.colorView.setBackgroundColor(
                    ContextCompat.getColor(requireContext(), getColorIdByName(color.value.color))
                )
            }

            is UiState.Failure -> {
                binding.colorSection.visibility = View.VISIBLE
                binding.colorsSpinner.visibility = View.VISIBLE
                binding.colorErrTxv.text = color.message
                binding.colorErrTxv.visibility = View.VISIBLE
            }

            UiState.Loading -> {
                binding.colorSection.visibility = View.GONE
            }
        }

    @ColorRes
    private fun getColorIdByName(colorName: String): Int {
        val resClass = R.color::class.java
        return resClass.getField(colorName).getInt(null)
    }
}