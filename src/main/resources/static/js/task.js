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