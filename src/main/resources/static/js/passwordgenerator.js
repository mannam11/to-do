const url = 'https://api.api-ninjas.com/v1/passwordgenerator?length=10';
let passwordInput = document.querySelector("#password");
let btn = document.querySelector("#btn");

const generatePassword = async () => {
    try {
        let response = await fetch(url, {
            method: 'GET',
            headers: {
                'X-Api-Key': 'your api-key'
            }
        });

        let data = await response.json();
        passwordInput.value = data.random_password; // Set password field value
    } catch (error) {
        console.error('Error:', error);
    }
}
btn.addEventListener("click", generatePassword);
