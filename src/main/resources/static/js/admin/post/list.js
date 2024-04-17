window.addEventListener('load', function () {
    //HTML 요소 변수 매핑 영역
    let preview = document.querySelector(".preview");
    const previewId = preview.getAttribute("data-id");
    const closeButton = document.querySelector('.close-button');
    modal = document.querySelector('.modal');
    //관리자 미리보기 클릭 이벤트 데이터요청영역
    preview.onclick = function () {
        $.ajax({
            url : '/api/post/'+previewId,
            type : 'get',
            dataType : 'json',
            success : postView,
            error: function () {
                alert('Error occurred:' + errorThrown);
            }
        })
    };


    //미리보기 열린 후 닫기 버튼 이벤트 처리 영역
    closeButton.addEventListener('click', function () {
        setTimeout(() => {
            modal.classList.add('hidden');
        }, 130);
    });

});

//미리보기 클릭후에 데이터 받아서 모달에 데이터 뿌려주는 영역
function postView(post) {
    modalContent = document.querySelector('.modal-content');
    modal.classList.remove('hidden');
    modalContent.innerHTML = `
        <label>
            제목
            <input type="text" class="n-textbox n-textbox-type:outline n-textbox-label:top" value="${post.title}"/>
        </label>
        <label>
            작성자
            <input type="text" class="n-textbox n-textbox-type:outline n-textbox-label:top" value="${post.writer}"/>
        </label>
        <label>
            답변상태 
            <input type="text" class="n-textbox n-textbox-type:outline n-textbox-label:top" value="${post.status}"/>
            <button class="my:3 n-btn">답변하기</button>
        </label>
        <label>
            원글
            <textarea type="text" class="n-textbox n-textbox-type:outline n-textbox-label:top h:5">
                ${post.content}
            </textarea>
        </label>
    `;

}