// HTML 코드에서 Cart 버튼을 선택합니다.
const cartButton = document.querySelector('button[name="userAction"][value="2"]');
const wishButton = document.querySelector('button[name="userAction"][value="3"]');

// 버튼에 클릭 이벤트 리스너를 추가합니다.
cartButton.addEventListener('click', function() {
    // 버튼이 클릭되면 "장바구니 담기 완료"라는 메시지 창을 출력합니다.
    alert('장바구니 담기 완료');
});
wishButton.addEventListener('click', function() {
    // 버튼이 클릭되면 "장바구니 담기 완료"라는 메시지 창을 출력합니다.
    alert('위시리스트 담기 완료');
});