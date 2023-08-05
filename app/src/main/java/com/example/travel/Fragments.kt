package com.example.travel

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class StartFragment : Fragment(R.layout.start_page){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //로그인 버튼 눌렀을 때 메인 화면으로 전환
        view.findViewById<Button>(R.id.sign_in_btn)?.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_mainFragment)
        }
        //회원가입 버튼 눌렀을 때 회원가입 화면으로 전환
        view.findViewById<Button>(R.id.sign_up_btn)?.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_signupFragment)
        }
        val bottomNavView = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNavView.visibility = View.GONE
    }
}
class SignupFragment : Fragment(R.layout.sign_up_page){
    //가입버튼을 눌렀을때 다시 로그인 페이지로 전환
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<Button>(R.id.sign_up_page_btn)?.setOnClickListener {
            findNavController().navigate(R.id.action_signupFragment_to_startFragment)
        }
    }

}
class MainFragment : Fragment(R.layout.main_page) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 메인 페이지에서 BottomNavigationView 설정
        val bottomNavView = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNavView.visibility = View.VISIBLE


    }
}
class RouteFragment : Fragment(R.layout.recom_route_page){

}
class MypageFragment : Fragment(R.layout.mypage){

}