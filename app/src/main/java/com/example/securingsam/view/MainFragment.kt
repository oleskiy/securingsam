package com.example.securingsam.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.securingsam.R
import kotlinx.android.synthetic.main.main_fragment.view.*

class MainFragment : Fragment(){
override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
): View? {
    activity!!.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
    return inflater.inflate(R.layout.main_fragment, container, false)
}

override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val navController = Navigation.findNavController(view)
    var mvpBtn = view.mvp_btn
    var mvvmBtn = view.mvvm_btn

    mvpBtn.setOnClickListener { view-> navController.navigate(R.id.action_mainFragment_to_MVPFragment)}
    mvvmBtn.setOnClickListener { view-> navController.navigate(R.id.action_mainFragment_to_MVVMFragment)}

}
}