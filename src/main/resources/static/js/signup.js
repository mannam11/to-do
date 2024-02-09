document.getElementById('signup').addEventListener('submit',function(event){

     let email = document.getElementById('email');
     let password = document.getElementById('password');

     if(email.value.trim().length === 0){
          email.style.border='1px solid red';
          email.style.boxShadow = '0 0 3px red';
          event.preventDefault();
     }

     if(password.value.trim().length === 0){
          password.style.border='1px solid red';
          password.style.boxShadow = '0 0 3px red';
          event.preventDefault();
     }

});