let textarea = document.getElementById("description");
    let maxWords = 50; // Change this to your desired word limit

    textarea.addEventListener("input", function() {
        let text = this.value;
        let words = text.trim().split(/\s+/);

        if (words.length > maxWords) {
            this.value = words.slice(0, maxWords).join(" ");
        }
    });

document.getElementById('create_task').addEventListener('submit', function(event){

      let title = document.getElementById('title');

      if(title.value.trim().length === 0){
         title.style.border = '1px solid red';
         title.style.boxShadow = '0 0 3px red';
         event.preventDefault();
      }
});