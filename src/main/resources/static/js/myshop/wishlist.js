function toggleCheckboxes(source) {
    const checkboxes = document.querySelectorAll('input[type="checkbox"]');
    checkboxes.forEach(checkbox => {
        if (checkbox !== source) {
            checkbox.checked = source.checked;
        }
    });
}