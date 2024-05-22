// 체크박스 전체 체크하기
function toggleCheckboxes(source) {
    const checkboxes = document.querySelectorAll('input[type="checkbox"]');
    checkboxes.forEach(checkbox => {
        if (checkbox !== source) {
            checkbox.checked = source.checked;
        }
    });
}

// wishlist 전체 삭제 버튼 기능 구현을 위한 함수
function submitClearWishlistForm() {

    const form = document.getElementById('wishlistForm');
    // 모든 상품 ID를 숨은 입력 필드로 폼에 추가
    document.querySelectorAll('input[name="productIds"]').forEach(input => {
        const hiddenInput = document.createElement('input');
        hiddenInput.type = 'hidden';
        hiddenInput.name = 'productIds';
        hiddenInput.value = input.value;
        form.appendChild(hiddenInput);
    });
    // 폼을 제출합니다.
    form.submit();
}
