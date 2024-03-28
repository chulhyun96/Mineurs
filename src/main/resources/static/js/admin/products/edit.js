function goInsert() {

    const form = document.querySelector("#frm"); // detail.html 의 요소(의 id:frm) 선택.

    const product = {                                                    //product객체의 각 요소의 값들을 똑같은 이름의 변수에 담기완료.
        id: form.querySelector(".id").value,                          //현재 적은 방법은 json으로 파싱해서 서버로 데이터 보내려고 하는거고,
        name: form.querySelector(".name").value,
        sellingPrice: form.querySelector(".sellingPrice").value,
        supplyingPrice: form.querySelector(".supplyingPrice").value,
    };

    const xhr = new XMLHttpRequest();                       //비동기통신을 위해 XMLHttpRequest 객체 생성 및 xhr로 참조.

    xhr.open("PUT", "/admin/products/edit");                    //서버로 비동기연결 요청메서드(PUT) , url 정보 적어서 보냄. 참고로 html 에선 get , post만 사용가능해서 자바스크립트로 데이터요청보내게됨.
    xhr.setRequestHeader("Content-Type", "application/json");   //서버로 보낼 데이터 타입 명시해야댐 .
    xhr.send(JSON.stringify(product));

    xhr.onload = function () {
        if (xhr.responseText === "success") {
            window.location.href = "/admin/products";
        }
    };
}
    /*
    그 외 방법

1.  const form = document.querySelector("#frm");
    const formData = new FormData(form); / 콘솔로 찍어볼려면 console.log(formData.get('매핑된 key');

    const xhr = new XMLHttpRequest();
    xhr.open("PUT", "/admin/products/edit");
    xhr.setRequestHeader("Content-Type", "multipart/form-dat");
    xhr.send(formData);

    const formDataString = new URLSearchParams(formData).toString(); /

2.  const form = document.querySelector("#frm");
    const formData = new FormData(form); / 콘솔로 찍어볼려면 console.log(formData.get('매핑된 key');

    const xhr = new XMLHttpRequest();
    xhr.open("PUT", "/admin/products/edit");
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send(formDataString);

        multipart/form-data는 파일 업로드와 같은 바이너리 데이터를 전송할 때 주로 사용되며,
        application/x-www-form-urlencoded는 단순한 텍스트 필드만 포함하는 양식을 보낼 때 주로 사용 된다는데 그냥 Json으로 보내도될듯?

        주의사항 : 서버에서는 대신 제이슨으로 파싱해서 데이타 보낸게 아니기 때문에 @ModelAttribute 로 받거나 , @RequestParam으로 받아야댐.
        @ModelAttribute Product product 로 받으려면 클라이언트에서 전송하는 필드의 이름과 서버에서 사용하는 엔티티 객체의 필드 이름이 같아야 함
    */
    /*
    개념 :
    -> 클라이언트가 html 문서 내 form 태그 내 input태그를 통해 데이터 문자열을 입력 > 문자열을 자바스크립트 객체에 담고 > XMLHttpRequest 객체를 통해 서버로 보냄 > 서버에서 인자로 받아 해당되는 서비스 구현

    참고 :
    -> 클라이언트에서 서버로 데이터를 파싱할 때 XMLHttpRequest 객체의 send 메소드는 DOMString, Blob, File, FormData 등의 argument만을 지원
    1. application/x-www-form-urlencoded 형식
        최초 자바스크립트 객체에 담아
        `

        클라이언트단에서 참고 할 내용
         JSON.stringify() = 자바스크립트 객체를 제이슨 데이터형식으로 바꿔줌.
         항상 따라다니는 코드가
         xhr.setRequestHeader("Content-Type", "application/json"); 인데
         서버에게 클라이언트가 보내주는 데이터 형식이 JSON이라는 것을 알려줌.

         XMLHttpRequest 객체의 send() = DOMString, Blob, File, FormData 형식의 인자를 지원함.

         const formDataString = new URLSearchParams(formData).toString();
         ->폼 태그안의 파라미터로 보낼 데이터들을 문자열로 변환해서 변수에 담는 코드
         ->대신에  xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded")써줘야 댐 . 복잡함 옛날 꺼 인듯.그냥 Jason으로 파싱 하시오.

         FormData()
         ->자바스크립트의 내장객체이고,사용자가 입력한 데이터를 서버로 전송하기 위한 키-값 쌍(key-value pairs)을 생성
          주요 메서드 append, delete, get, getAll, has, set 있고,
          예졔
            var formData = new FormData();

            // 위치정보를 추가
            formData.append('latitude', '50.01');
            formData.append('longitude', '10.47');
            // 위치정보를 조회
            console.log(formData.get('latitude'));  // 50.01


         예제 -> const formData = new FormData();
                formData.append('id', form.querySelector(".id").value);
                formData.append('name', form.querySelector(".name").value);
                formData.append('sellingPrice', form.querySelector(".sellingPrice").value);
                formData.append('supplyingPrice', form.querySelector(".supplyingPrice").value);

                xhr.open("PUT", "/admin/products/edit");
                xhr.send(formData);
                서버측에서는 @RequestParam 또는 클라이언트단과 서버단 보내는 객체의 필드,받는 서버에 엔티티 필드명이 같다면
                @ModelAttribute Product product 알아서 바인딩해줌.

        서버단에서 참고 할 내용
        @RequestParam = 클라이언트에서 데이터를 json으로 파싱하지 않고 보낼 때 사용.

        @RequestBody = Spring MVC에서 클라이언트의 HTTP 요청 본문을 Java 객체에 바인딩(변환 또는 매핑)하는 데 사용.
         즉, 특정 요청이 들어왔을때 본문에 있는 JSON이나 XML 형태의 데이터를 자바 객체로 변환해주는 역할.
         없으면 자동으로 바인딩 하지않음.따라서, 주로 JSON이나 XML 같은 데이터 형태를 받을 때 @RequestBody를 사용해야함.
₩
        @@ResponseBody
        서버(스프링)에서 클라이언트에게 응답을 할 때 Java 객체를 클라이언트에게 JSON, XML으로 변환되어 전달하는 역할.
        예를들어 new Product(1, "Product Name", 23.00); 이런 생성자로 만든 객체를
        {
        "id": 1,
        "name": "Product Name",
        "price": 23.00
        } 이런식으로 변환해서 응답보냄.
     */