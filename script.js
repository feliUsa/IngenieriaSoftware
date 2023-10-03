const $btnsignin= document.querySelector(".sign-in-btn"),
      $btnsignup= document.querySelector(".sign-up-btn"),
      $signin= document.querySelector(".sign-in"),
      $signup= document.querySelector(".sign-up");

document.addEventListener("click", e=>{
    if(e.target === $btnsignin || e.target ===$btnsignup){
        $signin.classList.toggle("active");
        $signup.classList.toggle("active")
    }
})




   