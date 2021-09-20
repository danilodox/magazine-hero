package com.br.magazinehero.ui.comics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.br.magazinehero.R
import androidx.navigation.fragment.findNavController
import com.br.magazinehero.data.model.Comic
import com.br.magazinehero.databinding.FragmentComicsBinding
import com.br.magazinehero.util.isVisible
import org.koin.androidx.viewmodel.ext.android.viewModel

class ComicsFragment : Fragment(R.layout.fragment_comics) {
    private val mViewModel : ComicViewModel by viewModel()
    private lateinit var binding : FragmentComicsBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentComicsBinding.inflate(inflater, container, false)

        initUiElements()
        initObservers()
        return binding.root
    }


    private fun initUiElements() {
        initRecyclerObserver()
    }

    private fun initObservers() {
        showLoading()
        showError()
    }

    private fun initRecyclerObserver() {

        mViewModel.mComicsLiveData.observe(viewLifecycleOwner, {

            it.data?.results.let {comics ->
                with(binding.recyclerListComic){
                    layoutManager = LinearLayoutManager(context)
                    setHasFixedSize(true)

                    adapter = ComicAdapter(comics){ results ->



                        val action = ComicsFragmentDirections.actionComicFragmentToComicDetailsFragment(results.id!!)
                        findNavController().navigate(action)

                    }

                }
            }

        })
        mViewModel.getComics()
    }

    private fun showLoading(){
        mViewModel.loadingLiveData.observe(viewLifecycleOwner, {
            binding.progressLoadingEvent.root.isVisible(it)
        })
    }

    private fun showError(){
        mViewModel.errorComicsLiveData.observe(viewLifecycleOwner, {
            binding.errorNetworkEvent.root.isVisible(it)
        })
    }


}