window.addEventListener("load", function (node, child) {

    var imgInput = document.querySelector(".img-input");
    var imgBox = document.querySelector(".img-box");

    imgInput.oninput = function (e) {
        for (var key in imgInput.files[0])
            console.log(key, ":", imgInput.files[0][key])

        var file = imgInput.files[0]

        if (file.type.indexOf("image/") !== 0) { //타입 제약
            alert("이미지만 업로드 할 수 있습니다.")
            return;
        }
        if (file.size > 100 * 100 * 1024) {
            alert(file.size)
            return;
        }

        var reader = new FileReader();
        reader.onload = function (e) {
            var img = document.createElement("img");
            img.src = e.target.result;
            imgBox.innerHTML = "";
            imgBox.append(img)
            // imgBox.classList.add("fade-in");

            setTimeout(() => {
                img.classList.add("slide-in")
            }, 10);
        };

        reader.readAsDataURL(file);

    };
});