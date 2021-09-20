package com.br.magazinehero.ui.comicdetails;


import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.br.magazinehero.R
import com.br.magazinehero.data.model.Comic
import com.br.magazinehero.databinding.FragmentComicDetailsBinding
import com.br.magazinehero.databinding.ViewCheckInBottomSheetBinding
import com.br.magazinehero.util.isVisible
import com.br.magazinehero.util.loadImage
import com.br.moviefy.ui.moviedetails.ComicDetailsViewModel
import kotlinx.android.synthetic.main.view_check_in_bottom_sheet.*
import okhttp3.internal.trimSubstring
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.viewModel

class ComicDetailsFragment : Fragment(R.layout.fragment_comic_details){

    private val mViewModel : ComicDetailsViewModel by viewModel()
    private lateinit var binding : FragmentComicDetailsBinding
    private val args : ComicDetailsFragmentArgs by navArgs()
    private var isComicRare : Boolean = false



    private val searchTextWatcher = object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            var desconto = 0
            if(editText_cupom.text.toString().toUpperCase().equals("COMUM") && isComicRare == false){
                desconto = 10
            }else if (editText_cupom.text.toString().toUpperCase().equals("RARO")){
                desconto = 25
            }
            var comicUni = (textView_price_uni.text.toString().toFloat() * desconto) / 100
            comicUni =  textView_price_uni.text.toString().toFloat() - comicUni

            textView_total.text = (textView_quant.text.toString().toFloat() * comicUni).toString().trimSubstring(0,5)
            //textView_total.text = (textView_quant.text.toString().toFloat() * textView_price_uni.text.toString().toFloat()).toString()

        }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        binding = FragmentComicDetailsBinding.inflate(inflater, container, false)



        initObservers()
        initUiElements()
        onClickShare()

        return binding.root
    }

    private fun initObservers() {
        showLoading()
        showError()
    }

    private fun showLoading(){
        with(mViewModel) {
            loadingDetailsLiveData.observe(viewLifecycleOwner, {
                binding.progressLoadingEvent.root.isVisible(it)

            })
        }
    }

    private fun showError(){
        with(mViewModel) {
            errorComicDetailsLiveData.observe(viewLifecycleOwner, {
                binding.errorNetworkEvent.root.isVisible(it)
            })
        }
    }

    private fun initUiElements() {

        with(mViewModel) {
            mComicsDetailsLiveData.observe(viewLifecycleOwner,  {


                it?.let {comics ->
                    fillBindingElements(comics.data.results[0])
                }
            })
            getComic(args.id)
        }

    }


    private fun fillBindingElements(comic : Comic){

        binding.comicDetailsPoster.loadImage(comic.thumbnail.path, comic.thumbnail.extension)
        binding.comicDetailsTitle.text = comic.title
        binding.comicDetailsDescription.text = comic.description
        binding.comicDetailsPrice.text = "$" + comic.prices[0].price.toString()


        //if(comic.isRare == "Rare") isComicRare = true
        initButton()

        textView_price_uni.text = comic.prices[0].price.toString()

        textView_quant.addTextChangedListener(searchTextWatcher)
        textView_quant.addTextChangedListener()




    }

    private fun initButton(){

        btn_confirm_check.setOnClickListener {
            Toast.makeText(context, "Compra realizada com sucesso!", Toast.LENGTH_LONG).show()
            activity?.onBackPressed()
        }
    }



    private fun onClickShare(){
        binding.buttonShare.setOnClickListener{

            val shareText = mViewModel.getShareText()
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, shareText)
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }
    }




}