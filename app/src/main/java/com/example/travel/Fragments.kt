package com.example.travel

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
//import com.google.android.gms.maps.GoogleMap
//import com.google.android.gms.maps.OnMapReadyCallback
//import com.google.android.gms.maps.SupportMapFragment
//import com.google.android.gms.maps.model.CameraPosition
//import com.google.android.gms.maps.model.LatLng
//import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

private lateinit var auth: FirebaseAuth
class StartFragment : Fragment(R.layout.start_page) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Initialize Firebase Auth
        auth = Firebase.auth
        val name = view.findViewById<EditText>(R.id.sign_in_email)
        val signInButton = view.findViewById<Button>(R.id.sign_in_btn)

        //로그인 버튼 눌렀을 때 메인 화면으로 전환
        signInButton?.setOnClickListener {
            Login()
            val userInput = name.text.toString() // 사용자가 입력한 값을 여기서 가져옴
            showToast(requireActivity(),"$userInput 님 환영합니다.")
        }

        //회원가입 버튼 눌렀을 때 회원가입 화면으로 전환
        view.findViewById<Button>(R.id.sign_up_btn)?.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_signupFragment)
        }
        val bottomNavView = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNavView.visibility = View.GONE
    }
    private fun Login() {
        val email = view?.findViewById<EditText>(R.id.sign_in_email)?.getText().toString()
        val password = view?.findViewById<EditText>(R.id.sign_in_password)?.getText().toString()
        if (email.length > 0 && password.length > 0) {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        findNavController().navigate(R.id.action_startFragment_to_mainFragment)
                    }
                }
        }
        }
    }
    private fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

class SignupFragment : Fragment(R.layout.sign_up_page) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        auth = Firebase.auth
        val checkBox = view.findViewById<CheckBox>(R.id.sign_up_checkBox)
        val signUpButton = view.findViewById<Button>(R.id.sign_up_page_btn)

        // 초기값 설정
        var isChecked = false

        // 체크박스 상태 변경 리스너
        checkBox.setOnCheckedChangeListener { buttonView, isCheckedNow ->
            isChecked = isCheckedNow
            updateButtonListener(signUpButton, isChecked)
        }

        // 초기 버튼 리스너 설정
        updateButtonListener(signUpButton, isChecked)
    }

    private fun updateButtonListener(button: Button, isChecked: Boolean) {
        button.setOnClickListener {
            if (isChecked) {
                signUp()
                showToast(requireContext(), "가입에 성공하였습니다.")
                findNavController().navigate(R.id.action_signupFragment_to_startFragment)
            } else {
                showToast(it.context, "정보제공 동의해주세요.")
            }
        }
    }
    private fun signUp(){
        val email = view?.findViewById<EditText>(R.id.sign_up_email)?.getText().toString()
        val password = view?.findViewById<EditText>(R.id.sign_up_password)?.getText().toString()
        val passwordCheck = view?.findViewById<EditText>(R.id.sign_up_password_check)?.getText().toString()
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                }
            }
    }
    private fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}


class MainFragment : Fragment(R.layout.main_page) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<ImageButton>(R.id.main_page_btn)?.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_choiceFragment)
        }

        // 메인 페이지에서 BottomNavigationView 설정
        val bottomNavView = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNavView.visibility = View.VISIBLE


    }
}
class RouteFragment : Fragment(R.layout.recom_route_page){

}
class MypageFragment : Fragment(R.layout.mypage){

}

class ChoiceFragment : Fragment(R.layout.choice_page){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<Button>(R.id.choice_page_OKbtn)?.setOnClickListener {
            findNavController().navigate(R.id.action_choiceFragment_to_mapFragment)
        }
        val bottomNavView = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNavView.visibility = View.GONE
    }
}
/*
class MapFragment : AppCompatActivity(), OnMapReadyCallback {
    lateinit var binding: ActivityMapBinding
    var googleMap: GoogleMap? = null
    //지도 뷰 객체 얻기
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)
        (supportFragmentManager.findFragmentById(R.id.mapView) as
                SupportMapFragment?)!!.getMapAsync(this)
    }
    override fun onMapReady(p0: GoogleMap) {
        googleMap = p0
    }
    //지도의 중심 이동
    val latLng = LatLng(37.566610, 126.978403)
    val position = CameraPosition.Builder()
        .target(latLng)
        .zoom(16f)
        .build()
    googleMap?.moveCamera(CameraUpdateFactory.newCameraPosition(position))
    //마커 표시
    val markerOptions = MarkerOptions()
    markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker))
    markerOptions.position(latLng)
    markerOptions.title("서울시청")
    markerOptions.snippet("Tel:01-120")

    googleMap?.addMarker(markerOptions)
}
 */