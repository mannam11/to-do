document.getElementById('login').addEventListener('submit',function(event){

     let username = document.getElementById('username');
     let password = document.getElementById('password');

     if(username.value.trim().length === 0){
          username.style.border='1px solid red';
          username.style.boxShadow = '0 0 3px red';
          event.preventDefault();
     }

     if(password.value.trim().length === 0){
          password.style.border='1px solid red';
          password.style.boxShadow = '0 0 3px red';
          event.preventDefault();
     }

});