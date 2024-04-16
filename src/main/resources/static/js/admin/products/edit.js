window.addEventListener("load", (e) => {
    const form = document.querySelector("#form"); // detail.html 의 요소(의 id:frm) 선택.

    const currentUrl = window.location.href;
    const parts = currentUrl.split('/'); // URL을 '/'로 분할
    const lastPart = parts[parts.length - 1]; // 마지막 부분 추출
    const productId = parseInt(lastPart); // 문자열을 숫자로 변환

    const editButton = form.querySelector(".edit")

    console.log("ㅇㅇ")
    editButton.onclick = (e) => {
        e.preventDefault();
        console.log("실행되나");
        edit(form, productId);
    };

});

async function edit(form, productId) {
    const formData = new FormData(form);


    const response = await fetch('/admin/products/' + productId, {
        method: "PUT",
        body: formData,
    });

    if (response.ok) {
        console.log(await response.text());
        await window.location.replace("/admin/products")
    } else {
        console.error("File upload failed.");
    }
}