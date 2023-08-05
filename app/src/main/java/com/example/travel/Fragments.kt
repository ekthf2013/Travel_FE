package com.example.travel

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

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
    }
}
class SignupFragment : Fragment(R.layout.sign_up_page){

}
class MainFragment : Fragment(R.layout.main_page) {

}
class RouteFragment : Fragment(R.layout.recom_route_page){

}
class MypageFragment : Fragment(R.layout.mypage){

}