document.addEventListener("DOMContentLoaded", function () {
    var priorityElements = document.querySelectorAll(".priority");

    priorityElements.forEach(function (element) {
        var priority = element.textContent.trim();
        element.style.backgroundColor = getColorForPriority(priority);
    });

    function getColorForPriority(priority) {
        switch (priority) {
            case "LOW":
                return "#65B741"; // Green for LOW priority
            case "MEDIUM":
                return "#F8E559"; // Yellow for MEDIUM priority
            case "HIGH":
                return "#FF004D"; // Red for HIGH priority
            default:
                return "#f4f4f4"; // Default color
        }
    }
});

function toggleOptionsMenu(event) {
    var optionsMenu = event.currentTarget.querySelector('.options-menu');
    optionsMenu.style.display = optionsMenu.style.display === 'block' ? 'none' : 'block';
}
// task.js
function toggleTaskStatus(form, event) {
    // Prevent the form from submitting
    event.preventDefault();

    // Get the button element inside the form
    var button = form.querySelector('.toggle-button');

    // Toggle the button text
    button.innerText = button.innerText === 'Mark as Completed' ? 'Mark as In Progress' : 'Mark as Completed';

    // Submit the form asynchronously using AJAX
    fetch(form.action, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams(new FormData(form))
    }).then(response => {
        // Handle the response as needed
        if (response.ok) {
            // Assuming a successful response, you can remove the task card from the DOM
            const taskCard = form.closest('.task-card');
            taskCard.remove();
        } else {
            console.error('Error:', response.statusText);
        }
    }).catch(error => {
        console.error('Error:', error);
    });

    return false; // Prevent the default form submission
}