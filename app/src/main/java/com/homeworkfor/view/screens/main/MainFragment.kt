package com.homeworkfor.view.screens.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import coil.transform.CircleCropTransformation
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.homeworkfor.MainConstance
import com.homeworkfor.R
import com.homeworkfor.data.model.NasaResponseModel
import com.homeworkfor.databinding.FragmentMainBinding
import com.homeworkfor.extension.getDaysAgo
import com.homeworkfor.extension.showWebWiki
import com.homeworkfor.extension.snackBar
import com.homeworkfor.view.AppState
import com.homeworkfor.view.screens.main.viewmodel.MainViewModel
import com.homeworkfor.view.screens.main.viewmodel.MainViewModelFactory
import java.text.SimpleDateFormat

class MainFragment : Fragment() {

    private var _bind: FragmentMainBinding? = null

    private val binding get() = _bind!!

    private lateinit var viewMainModel: MainViewModel

    private var isSearchVisible: Boolean = false

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bind = FragmentMainBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialViewModel()

        binding.apply {
            layoutEdSearch.setEndIconOnClickListener {
                if (edSearch.text!!.isNotEmpty()) {
                    val textRequest = "${MainConstance.BASE_URL_WIKI}${edSearch.text.toString()}"
                    showWebWiki(textRequest)
                }
            }
            setBottomSheetBehavior(binding.bottomSheetLayout.bottomSheetContainer)
            groupCheepLayout.setOnCheckedStateChangeListener { group, checkedIds ->
                checkedIds.forEach {
                    updateViewModel(it)
                }
            }
        }
    }

    private fun updateViewModel(it: Int) {
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        when (it) {
            2131230837 -> {
                viewMainModel.getPicturesOtherDay(sdf.format(getDaysAgo(0)))
            }
            2131230838 -> {
                viewMainModel.getPicturesOtherDay(sdf.format(getDaysAgo(1)))
            }
            2131230839 -> {
                viewMainModel.getPicturesOtherDay(sdf.format(getDaysAgo(2)))
            }
        }
    }

    private fun setBottomSheetBehavior(bottomSheet: ConstraintLayout) {
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    private fun initialViewModel() {
        viewMainModel = ViewModelProvider(this, MainViewModelFactory())[MainViewModel::class.java]
        viewMainModel.getPictureOfTheDay()
        viewMainModel.requestViewModelData.observe(viewLifecycleOwner) {
            getStateData(it)
        }
    }

    private fun getStateData(state: AppState<NasaResponseModel>) = with(binding) {
        when (state) {
            is AppState.Error -> {
                snackBar(state.message.toString())
            }
            is AppState.Loading -> {
                includeProgress.root.visibility = View.VISIBLE
            }
            is AppState.Success -> {
                includeProgress.root.visibility = View.GONE
                imageView.load(state.data?.url) {
                    crossfade(true)
                    transformations(CircleCropTransformation())
                }
                bottomSheetLayout.apply {
                    textTitle.text = state.data?.title
                    textDescription.text = state.data?.explanation
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _bind = null
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.show_search_view -> {
                isSearchVisible = !isSearchVisible
                if (isSearchVisible) {
                    binding.searchViewLayout.visibility = View.VISIBLE
                } else {
                    binding.searchViewLayout.visibility = View.GONE
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}