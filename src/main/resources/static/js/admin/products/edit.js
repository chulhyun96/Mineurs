function edit() {

    const form = document.querySelector("#form"); // detail.html 의 요소(의 id:frm) 선택.

    const product = {                                                    //product객체의 각 요소의 값들을 똑같은 이름의 변수에 담기완료.
        id: form.querySelector(".id").value,    //현재 적은 방법은 json으로 파싱해서 서버로 데이터 보내려고 하는거고,
        name: form.querySelector(".name").value,
        sellingPrice: form.querySelector(".sellingPrice").value,
        supplyingPrice: form.querySelector(".supplyingPrice").value,
        description: form.querySelector(".description").value,
        img: form.querySelector(".img").value
    };

    const xhr = new XMLHttpRequest();                       //비동기통신을 위해 XMLHttpRequest 객체 생성 및 xhr로 참조.

    xhr.open("PUT", "/api/products");                    //서버로 비동기연결 요청메서드(PUT) , url 정보 적어서 보냄. 참고로 html 에선 get , post만 사용가능해서 자바스크립트로 데이터요청보내게됨.
    xhr.setRequestHeader("Content-Type", "application/json");   //서버로 보낼 데이터 타입 명시해야댐 .
    xhr.send(JSON.stringify(product));

    xhr.onload = function () {
        if (xhr.responseText === "success") {
            window.location.href = "/admin/products";
        }
    };
}
