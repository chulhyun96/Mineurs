console.log('haha');
window.onload = function () {
    xhr.onload = function () {
        var list = JSON.parse(this.responseText);
        bind(list)
    };
    xhr.open("put", "admin/products");
    xhr.send();
}

function goInsert() {

    const form = document.querySelector("#frm");

    const formData = new FormData(form);

    const formDataString = new URLSearchParams(formData).toString();

    const xhr = new XMLHttpRequest();

    xhr.open("PUT", "/admin/products");
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send(formDataString);

    xhr.onload = function () {
        if (xhr.status === 200) {

            location.reload()
        } else {
            alert("error");
        }
    };
}