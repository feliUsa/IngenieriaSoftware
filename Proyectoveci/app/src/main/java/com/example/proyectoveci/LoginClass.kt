package com.example.proyectoveci

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.Date
import kotlin.properties.Delegates

class LoginClass : AppCompatActivity() {
    //Companion Object es para variables dentro de cualquier otro archivo
    companion object{
        //Creo estas variables para luego hacer uso de estas
        lateinit var useremail: String
        lateinit var providerSession: String
    }
    //Aca guardamos email y contraseña y ademas decimos que no va a ser de tipo null
    private var email by Delegates.notNull<String>()
    private var password by Delegates.notNull<String>()
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var lyTerms: LinearLayout

    private lateinit var mAuth: FirebaseAuth


    private var RESULT_CODE_GOOGLE_SIGN_IN=100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        lyTerms = findViewById(R.id.lyTerms)
        lyTerms.visibility = View.INVISIBLE
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)

        //Instancia para poder operar
        try {
            mAuth = FirebaseAuth.getInstance()
        } catch (e: Exception) {
            Log.e("TAG", "Error initializing FirebaseAuth: ${e.message}")
        }

        manageButtonLogin()
        etEmail.doOnTextChanged { text, start, before, count -> manageButtonLogin() }
        etPassword.doOnTextChanged { text, start, before, count -> manageButtonLogin() }
    }

    public override fun onStart() {
        super.onStart()
        var currencyUser = FirebaseAuth.getInstance().currentUser
        if(currencyUser != null) goHome(currencyUser.email.toString(),currencyUser.providerId)

    }

    override fun onBackPressed() {
        val startMain =  Intent(Intent.ACTION_MAIN)
        startMain.addCategory(Intent.CATEGORY_HOME)
        startMain.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(startMain)
    }
    private fun manageButtonLogin(){
        var tvLogin = findViewById<TextView>(R.id.LoginButton)
        email = etEmail.text.toString()
        password = etPassword.text.toString()

        if (TextUtils.isEmpty(password) || ValidateEmail.isEmail(email) == false){

            tvLogin.background= ContextCompat.getDrawable(this, R.drawable.boton_inicio_sesion_invalido)
            tvLogin.isEnabled = false
        }
        else{
            tvLogin.background= ContextCompat.getDrawable(this, R.drawable.botonlogin)
            tvLogin.isEnabled = true
        }
    }


    fun login(view: View) {
        loginUser()
    }
    private fun loginUser() {
        email = etEmail.text.toString()
        password = etPassword.text.toString()

        if (::mAuth.isInitialized) { // Check if mAuth is initialized
            mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        goHome(email, "email")
                    } else {
                        if (lyTerms.visibility == View.INVISIBLE) lyTerms.visibility = View.VISIBLE
                        else {
                            val cbAcept = findViewById<CheckBox>(R.id.cbAccept)
                            if (cbAcept.isChecked) register()
                        }
                    }
                }
        } else {
            Log.e("TAG", "FirebaseAuth is not initialized.")
        }
    }
    //Con esta funcion hacemos que vaya al menu principal
    private fun goHome(email: String, provider:String){
        try {
            useremail = email
            providerSession =  provider
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } catch (e: Exception) {
            Log.e("TAG", "Error starting MainActivity: ${e.message}")
        }
    }

    private fun register() {
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)

        if (::mAuth.isInitialized) { // Check if mAuth is initialized
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        val dateRegister = SimpleDateFormat("dd/MM/yyyy").format(Date())

                        val dbRegister = FirebaseFirestore.getInstance()
                        dbRegister.collection("users").document(email).set(hashMapOf(
                            "user" to email,
                            "dateRegister" to dateRegister
                        ))
                        goHome(email, "email")
                    } else {
                        Toast.makeText(this, "Error algo ha ido mal ＞︿＜", Toast.LENGTH_SHORT).show()
                    }
                }
        } else {
            Log.e("TAG", "FirebaseAuth is not initialized.")
        }
    }

    fun goTerms(v: View){
        val intent = Intent(this, TermsActivity::class.java)
        startActivity(intent)
    }
    fun forgotPassword(v:View){
        resetPassword()
    }

    fun resetPassword(){
        var e = etEmail.text.toString()
        if(!TextUtils.isEmpty(e)){
            mAuth.sendPasswordResetEmail(e)
                .addOnCompleteListener{task ->
                    if(task.isSuccessful) Toast.makeText(this, "Email Enviado Veci a $e", Toast.LENGTH_SHORT).show()
                    else Toast.makeText(this, "Veci, no se encontro usuario con ese correo", Toast.LENGTH_SHORT).show()
                }
        }
        else Toast.makeText(this, "Veci, danos un email ", Toast.LENGTH_SHORT).show()
    }

    fun callSignInGoogle(view: View){
        signGoogle()
    }

    private fun signGoogle(){
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        var googleSignInClient = GoogleSignIn.getClient(this,gso)
        googleSignInClient.signOut()

        startActivityForResult(googleSignInClient.signInIntent,RESULT_CODE_GOOGLE_SIGN_IN)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RESULT_CODE_GOOGLE_SIGN_IN){
            val task= GoogleSignIn.getSignedInAccountFromIntent(data)
            try{
                val account = task.getResult(ApiException::class.java)
                if(account!=null){
                    email = account.email!!
                    val credential = GoogleAuthProvider.getCredential(account.idToken,null)
                    mAuth.signInWithCredential(credential).addOnCompleteListener{
                        if(it.isSuccessful)goHome(email,"Google")
                        else Toast.makeText(this,"Error conexion con google", Toast.LENGTH_SHORT)
                    }                }
            }catch (e:ApiException){
                Toast.makeText(this,"Error conexion con google", Toast.LENGTH_SHORT)
            }
        }
    }
}