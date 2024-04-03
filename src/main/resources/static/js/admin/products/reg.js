


window.addEventListener("load", function (node, child) {

    var imgInput = document.querySelector(".img-input");
    var imgBox = document.querySelector(".img-box");
    var detailImageLabel = document.querySelector(".detail-img-label");
    var detailImageBox = document.querySelector(".detail-img");
    var mainImgLabel = document.querySelector(".main-img-label");

    // 메인이미지 드래그 앤 드랍
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

            setTimeout(() => {
                img.classList.add("slide-in")
            }, 10);
        };

        reader.readAsDataURL(file);

    };
    /*메인드래그 존*/
    mainImgLabel.ondragover = function (e) {
        e.stopPropagation();
        e.preventDefault();
        console.log("over")
        var valid = e.dataTransfer &&
            e.dataTransfer.types &&
            e.dataTransfer.types.indexOf("Files") >=0;

        if(!valid)
            mainImgLabel.classList.add("invalid");
        /*var files = e.dataTransfer.files;*/
    };


    /*서브이미지 드래그 존*/
    detailImageLabel.ondragover = function (e) {
        e.stopPropagation();
        e.preventDefault();
        console.log("over")
        var valid = e.dataTransfer &&
            e.dataTransfer.types &&
            e.dataTransfer.types.indexOf("Files") >=0;

        if (!valid)
            detailImageLabel.classList.add("invalid");

        /*var files = e.dataTransfer.files;*/
    };

    detailImageLabel.ondrop = function (e) {
        e.preventDefault();
        e.stopPropagation();
        detailImage.classList.remove("valid");
        detailImage.classList.remove("invalid");

        var files = e.dataTransfer.files;
        var file = files[0];
        // fileInput.files = e.dataTransfer.files;
        // 파일인풋태그에 드래그앤드랍한 파일들을 담는다.

        // new InputFileList(imgInput).add(file);

        if (file.type.indexOf("image/") !== 0) {
            alert("이미지파일만 업로드 할 수 있습니다.")
            return;
        }
        if(file.size > 100*100*1024){
            alert(file.size)
            return;
        }
        var reader = new FileReader();
        reader.onload = function (e) {
            var img = document.createElement("img");
            img.src = e.target.result;
            detailImageBox.append(img);
            setTimeout( ()=> {
                // img.classList.add("slide-in");
                //10밀리세컨
            }, 10);
            // imgBox.classList.add("fade-in");
            files = dataTransfer.files;
        };
        reader.readAsDataURL(file);
    };
});