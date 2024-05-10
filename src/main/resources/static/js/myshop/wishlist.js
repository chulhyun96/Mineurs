function toggleCheckboxes(source) {
    const checkboxes = document.querySelectorAll('input[type="checkbox"]');
    checkboxes.forEach(checkbox => {
        if (checkbox !== source) {
            checkbox.checked = source.checked;
        }
    });
}

// // 모달을 열기 위해 document.getElementById('ID').showModal() 메서드 사용
// document.querySelector('.btn').addEventListener('click', function() {
//     document.getElementById('my_modal_1').showModal();
//   });
  
//   // 폼 내부 버튼이 클릭되면 모달을 닫습니다.
//   document.querySelector('.modal-action form button').addEventListener('click', function() {
//     document.getElementById('my_modal_1').close();
//   });