package com.example.fruits.presention.fragment

import android.graphics.drawable.ClipDrawable.HORIZONTAL
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.util.Resource
import com.example.fruits.R
import com.example.fruits.data.model.Result
import com.example.fruits.databinding.ActivityMainBinding
import com.example.fruits.databinding.FragmentHomeBinding
import com.example.fruits.presention.activity.MainActivity
import com.example.fruits.presention.adapter.FruitsHomeAdapter
import com.example.fruits.presention.viewmodel.HomeViewModel


class HomeFragment : Fragment() {

    lateinit var homeBinding: FragmentHomeBinding
    lateinit var homeViewModel: HomeViewModel
    lateinit var fruitsHomeAdapter: FruitsHomeAdapter


    private var current_page = 1
    private var isScroling = false
    private var isLoading = false
    private var isLastPage = false
    private var resultData: List<Result> = emptyList()
    private var isShow = true

    var vegetables: Boolean = false
    var fruits: Boolean = false
    var dry_fruits: Boolean = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeBinding = FragmentHomeBinding.bind(view)

        homeViewModel = (activity as MainActivity).homeViewModel
        fruitsHomeAdapter = (activity as MainActivity).fruitsHomeAdapter
        homeViewModel.getConnect()

        homeViewModel.connectData.observe(viewLifecycleOwner, Observer {
            if (it == "ok") {
                Log.i("PP", "RUNNING")
                showProgressBar()
                showOrganicFruitsImages()
                showsMixedFruitPackImages()
                showStoneFruitsImages()
                showMelonsFruitsImages()
            } else {
                Log.i("IN", "NO RUNNING")
                hideProgressBar()
            }
        })


        initOrganicFruitsRecyclerView()
        initMixedFruitPackRecyclerView()
        initStoneFruitsRecyclerView()
        initMelonsFruitsRecyclerView()


        if (!vegetables && !fruits && !dry_fruits) {
            homeBinding.Fruits.setBackgroundResource(R.drawable.ic_rectangel_filter)
            homeBinding.Fruits.setTextColor(resources.getColor(R.color.white))

        }
//        homeBinding.Vegetables.setOnClickListener {
//            vegetables = true
//            fruits = false
//            dry_fruits = false
//            homeBinding.Vegetables.setBackgroundResource(R.drawable.ic_rectangel_filter)
//            homeBinding.Vegetables.setTextColor(resources.getColor(R.color.white))
//
//        }

//        homeBinding.Fruits.setOnClickListener {
//            vegetables = false
//            fruits = true
//            dry_fruits = false
//            homeBinding.Fruits.setBackgroundResource(R.drawable.ic_rectangel_filter)
//            homeBinding.Fruits.setTextColor(resources.getColor(R.color.white))
//        }
//        homeBinding.DryFruits.setOnClickListener {
//            vegetables = false
//            fruits = false
//            dry_fruits = true
//            homeBinding.DryFruits.setBackgroundResource(R.drawable.ic_rectangel_filter)
//            homeBinding.DryFruits.setTextColor(resources.getColor(R.color.white))
//        }
    }

    private fun hideProgressBar() {
        homeBinding.HomeProgressBar.visibility = View.GONE
    }

    private fun showOrganicFruitsImages() {
        homeViewModel.getImageFruits("Organic Fruits",current_page)
        homeViewModel.fruitsImageMutable.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        resultData = resultData + it.results
                        fruitsHomeAdapter.differ.submitList(resultData)
                        var pages = 0
                        if (it.totalPages % 10 == 0) {
                            pages = it.totalPages / 10
                            Log.i("P", pages.toString())
                        } else {
                            pages = it.totalPages / 10 + 1
                            Log.i("P2", pages.toString())
                        }
                        isLastPage = current_page == pages



                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let {
                        Log.i("T",it.toString())
                        Toast.makeText(activity, "An error occurred : $it", Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }

        })
    }

    private fun showsMixedFruitPackImages() {
        homeViewModel.getImageFruits("Mixed Fruit Pack",current_page)
        homeViewModel.fruitsImageMutable.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        resultData = resultData + it.results
                        fruitsHomeAdapter.differ.submitList(resultData)
                        var pages = 0
                        if (it.totalPages % 10 == 0) {
                            pages = it.totalPages / 10
                            Log.i("P", pages.toString())
                        } else {
                            pages = it.totalPages / 10 + 1
                            Log.i("P2", pages.toString())
                        }
                        isLastPage = current_page == pages



                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let {
                        Log.i("T",it.toString())
                        Toast.makeText(activity, "An error occurred : $it", Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }

        })
    }
    private fun showStoneFruitsImages() {
        homeViewModel.getImageFruits("Stone Fruits",current_page)
        homeViewModel.fruitsImageMutable.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        resultData = resultData + it.results
                        fruitsHomeAdapter.differ.submitList(resultData)
                        var pages = 0
                        if (it.totalPages % 10 == 0) {
                            pages = it.totalPages / 10
                            Log.i("P", pages.toString())
                        } else {
                            pages = it.totalPages / 10 + 1
                            Log.i("P2", pages.toString())
                        }
                        isLastPage = current_page == pages



                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let {
                        Log.i("T",it.toString())
                        Toast.makeText(activity, "An error occurred : $it", Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }

        })
    }
    private fun showMelonsFruitsImages() {
        homeViewModel.getImageFruits("Melons",current_page)
        homeViewModel.fruitsImageMutable.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        resultData = resultData + it.results
                        fruitsHomeAdapter.differ.submitList(resultData)
                        var pages = 0
                        if (it.totalPages % 10 == 0) {
                            pages = it.totalPages / 10
                            Log.i("P", pages.toString())
                        } else {
                            pages = it.totalPages / 10 + 1
                            Log.i("P2", pages.toString())
                        }
                        isLastPage = current_page == pages



                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let {
                        Log.i("T",it.toString())
                        Toast.makeText(activity, "An error occurred : $it", Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }

        })
    }

    private fun showProgressBar() {
        homeBinding.HomeProgressBar.visibility = View.VISIBLE
    }

    private fun initOrganicFruitsRecyclerView() {
        homeBinding.FruitsRecycle.apply {
            adapter = fruitsHomeAdapter
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            addOnScrollListener(onOrganicFruitsScrollinsnerLisner)
        }

    }
    private fun initMixedFruitPackRecyclerView() {
        homeBinding.MixedFruitPackRecycl.apply {
            adapter = fruitsHomeAdapter
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            addOnScrollListener(onMixedFruitPackScrollinsnerLisner)
        }

    }
    private fun initStoneFruitsRecyclerView() {
        homeBinding.StoneFruitsRecyl.apply {
            adapter = fruitsHomeAdapter
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            addOnScrollListener(onStoneFruitsScrollinsnerLisner)
        }

    }
    private fun initMelonsFruitsRecyclerView() {
        homeBinding.MelonsRecycl.apply {
            adapter = fruitsHomeAdapter
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            addOnScrollListener(onMelonFruitsScrollinsnerLisner)
        }

    }

    private val onOrganicFruitsScrollinsnerLisner = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScroling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            var layoutManger = homeBinding.FruitsRecycle.layoutManager as LinearLayoutManager
            // using layoutManger manger instance we are  going to 3 properties of the current recyleView
            // 1 ..  Size of the current list
            // 2 ..  visible ItemCount
            // 3..  starting Position of visible Item
            val sizeOfListItem = layoutManger.itemCount
            val visibleItemcount = layoutManger.childCount
            val startingPostionof_visible_Item =
                layoutManger.findFirstCompletelyVisibleItemPosition()

            val hasReachedToEnd =
                startingPostionof_visible_Item + visibleItemcount >= sizeOfListItem
            val shouldPaginate = !isLoading && !isLastPage && hasReachedToEnd && isScroling
            if (shouldPaginate) {
                current_page++
                homeViewModel.getImageFruits("Organic Fruits",current_page)
                isScroling = false
            }
            Log.i("TAGG", sizeOfListItem.toString())
            Log.i("PAGE", current_page.toString())


        }
    }
    private val onMixedFruitPackScrollinsnerLisner = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScroling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            var layoutManger = homeBinding.FruitsRecycle.layoutManager as LinearLayoutManager
            // using layoutManger manger instance we are  going to 3 properties of the current recyleView
            // 1 ..  Size of the current list
            // 2 ..  visible ItemCount
            // 3..  starting Position of visible Item
            val sizeOfListItem = layoutManger.itemCount
            val visibleItemcount = layoutManger.childCount
            val startingPostionof_visible_Item =
                layoutManger.findFirstCompletelyVisibleItemPosition()

            val hasReachedToEnd =
                startingPostionof_visible_Item + visibleItemcount >= sizeOfListItem
            val shouldPaginate = !isLoading && !isLastPage && hasReachedToEnd && isScroling
            if (shouldPaginate) {
                current_page++
                homeViewModel.getImageFruits("Mixed Fruit Pack",current_page)
                isScroling = false
            }
            Log.i("TAGG", sizeOfListItem.toString())
            Log.i("PAGE", current_page.toString())


        }
    }
    private val onStoneFruitsScrollinsnerLisner = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScroling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            var layoutManger = homeBinding.FruitsRecycle.layoutManager as LinearLayoutManager
            // using layoutManger manger instance we are  going to 3 properties of the current recyleView
            // 1 ..  Size of the current list
            // 2 ..  visible ItemCount
            // 3..  starting Position of visible Item
            val sizeOfListItem = layoutManger.itemCount
            val visibleItemcount = layoutManger.childCount
            val startingPostionof_visible_Item =
                layoutManger.findFirstCompletelyVisibleItemPosition()

            val hasReachedToEnd =
                startingPostionof_visible_Item + visibleItemcount >= sizeOfListItem
            val shouldPaginate = !isLoading && !isLastPage && hasReachedToEnd && isScroling
            if (shouldPaginate) {
                current_page++
                homeViewModel.getImageFruits("Stone Fruits",current_page)
                isScroling = false
            }
            Log.i("TAGG", sizeOfListItem.toString())
            Log.i("PAGE", current_page.toString())


        }
    }
    private val onMelonFruitsScrollinsnerLisner = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScroling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            var layoutManger = homeBinding.FruitsRecycle.layoutManager as LinearLayoutManager
            // using layoutManger manger instance we are  going to 3 properties of the current recyleView
            // 1 ..  Size of the current list
            // 2 ..  visible ItemCount
            // 3..  starting Position of visible Item
            val sizeOfListItem = layoutManger.itemCount
            val visibleItemcount = layoutManger.childCount
            val startingPostionof_visible_Item =
                layoutManger.findFirstCompletelyVisibleItemPosition()

            val hasReachedToEnd =
                startingPostionof_visible_Item + visibleItemcount >= sizeOfListItem
            val shouldPaginate = !isLoading && !isLastPage && hasReachedToEnd && isScroling
            if (shouldPaginate) {
                current_page++
                homeViewModel.getImageFruits("Melons",current_page)
                isScroling = false
            }
            Log.i("TAGG", sizeOfListItem.toString())
            Log.i("PAGE", current_page.toString())


        }
    }

}