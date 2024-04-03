/* 인풋파일 컬렉션 리스트 구현*/
function InputFileList(input) {
    this.input = input;
}
InputFileList.prototype = {
    add: function (file) {
        var dt = new DataTransfer();
        var files = this.input.files;

        for(var temp of files)
            dt.items.add(temp);

        dt.items.files = dt.files;
    }
};

window.addEventListener("load", function (node, child) {

    var imgInput = document.querySelector(".img-input");
    var imgBox = document.querySelector(".img-box");
    var detailImageLabel = document.querySelector(".detail-img-label");
    var detailImageBox = document.querySelector(".detail-img");
    var mainImgLabel = document.querySelector(".main-img-label");


    // 메인이미지 드래그
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
    /*대표사진 존*/
    mainImgLabel.ondragover = function (e) {
        e.stopPropagation();
        e.preventDefault();
        console.log("over")
        var valid = e.dataTransfer &&
            e.dataTransfer.types &&
            e.dataTransfer.types.indexOf("Files") >= 0;

        if (!valid)
            mainImgLabel.classList.add("invalid");
        /*var files = e.dataTransfer.files;*/
    };
    /*대표사진 드래그 리브*/
    mainImgLabel.ondragleave = function (e) {
        mainImgLabel.classList.remove("invalid");
    };
    /*대표사진 드래그 드랍*/
    mainImgLabel.ondrop = function(e) {
        e.stopPropagation();
        e.preventDefault();

        var files = e.dataTransfer.files;
        var file = files[0];

        // new InputFileList(imgInput).addList(files);
        new InputFileList(imgInput).add(file);

        if (file.type.indexOf("image/") !== 0) {
            alert("이미지만 업로드 할 수 있습니다");
            return;
        }

        if (file.size > 1024*1024 * 1024) {
            alert("파일의 크기가 너무 큽니다");
            return;
        }
        var reader = new FileReader();
        reader.onload = function (e) {
            var img = document.createElement("img");
            img.src = e.target.result;
            imgBox.innerHTML = "";
            imgBox.append(img);
            setTimeout( ()=> {
                img.classList.add("slide-in");
                //10밀리세컨
            }, 10);
            // imgBox.classList.add("fade-in");
            files = dataTransfer.files;
        };
        reader.readAsDataURL(file);
    }

    /*서브이미지 드래그 존*/
    detailImageLabel.ondragover = function (e) {
        e.stopPropagation();
        e.preventDefault();
        console.log("over")
        var valid = e.dataTransfer &&
            e.dataTransfer.types &&
            e.dataTransfer.types.indexOf("Files") >= 0;

        if (!valid)
            detailImageLabel.classList.add("invalid");

        /*var files = e.dataTransfer.files;*/
    };
    /*서브이미지 드래그 드랍*/
    detailImageLabel.ondrop = function (e) {
        e.preventDefault();
        e.stopPropagation();
        detailImageLabel.classList.remove("valid");
        detailImageLabel.classList.remove("invalid");

        var files = e.dataTransfer.files;
        var file = files[0];


        if (file.type.indexOf("image/") !== 0) {
            alert("이미지파일만 업로드 할 수 있습니다.")
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
            detailImageBox.append(img);
            setTimeout(() => {
                // img.classList.add("slide-in");
                //10밀리세컨
            }, 10);
            // imgBox.classList.add("fade-in");
            files = dataTransfer.files;
        };
        reader.readAsDataURL(file);
    };
    /*서브이미지 드래그 리브*/
    detailImageLabel.ondragleave = function (e) {
        detailImageLabel.classList.remove("invalid");
    };
});