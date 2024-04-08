window.addEventListener("load", () => {
    const main = document.querySelector("main");
    const form = main.querySelector("form");
    const username = form.querySelector(".username input");
    const email = form.querySelector(".email input");
    const password = form.querySelector(".password input[type='password']");
    const confirmPassword = form.querySelector(".confirm-password input[type='password']");
    const confirmPasswordIcon = form.querySelector(".confirm-password input[type='password'] ~ .icon");
    const invalidPassword = form.querySelector(".invalid-confirm-password");

    /* ============== 아이디 중복 에러 안내 후 input 이벤트 발생시 에러 스타일 제거 ============== */
    username.addEventListener("input", () => {
        const isDuplicateUsername = form.querySelector(".duplicate-username");
        const invalidIcon = form.querySelector("input[type='text'] ~ .invalid-icon");
        if(!isDuplicateUsername)
            return;

        isDuplicateUsername.classList.add("d:none");
        invalidIcon.classList.add("d:none");
        username.classList.remove("border-color:accent-2");
    });

    /* ============== 이메일 중복 에러 안내 후 input 이벤트 발생시 에러 스타일 제거 ============== */
    email.addEventListener("input", () => {
        const isDuplicateEmail = form.querySelector(".duplicate-email");
        const invalidIcon = form.querySelector("input[type='email'] ~ .invalid-icon");
        if(!isDuplicateEmail)
            return;

        isDuplicateEmail.classList.add("d:none");
        invalidIcon.classList.add("d:none");
        email.classList.remove("border-color:accent-2");
    });

    /* ============== 패스워드 확인 ============== */
    password.addEventListener("input", passwordCheckHandler);
    confirmPassword.addEventListener("input", passwordCheckHandler);

    function passwordCheckHandler(){
        const newPassword = password.value;
        const newConfirmPassword = confirmPassword.value;

        // 비밀번호 확인의 값이 없을 때
        if(!newConfirmPassword) {
            // 유효성 안내 메세지 제거
            invalidPassword.classList.remove("d:inline-block");
            invalidPassword.classList.add("d:none");
            confirmPasswordIcon.classList.add("d:none");

            // 텍스트 박스 클래스 추가
            confirmPassword.classList.remove("n-textbox-status:warning");
            confirmPassword.classList.remove("n-textbox-status:success");
            return;
        }

        // 비밀번호와 비밀번호 확인이 맞을 때 유효성 성공 클래스를 적용합니다
        if(newPassword === newConfirmPassword){
            // 유효성 안내 메세지 제거
            invalidPassword.classList.remove("d:inline-block");
            invalidPassword.classList.add("d:none");

            // 텍스트 박스 클래스 추가
            confirmPassword.classList.remove("n-textbox-status:warning");
            confirmPassword.classList.add("n-textbox-status:success");

            // 유효성 성공 안내 아이콘 추가
            confirmPasswordIcon.classList.remove("icon:warning_circle", "icon-color:accent-2");
            confirmPasswordIcon.classList.add("icon:check_circle", "icon-color:sub-2");
            return;
        }

        // 유효성 안내 메세지 제거
        invalidPassword.classList.remove("d:none");
        invalidPassword.classList.add("d:inline-block");

        // 텍스트 박스 클래스 추가
        confirmPassword.classList.remove("n-textbox-status:success");
        confirmPassword.classList.add("n-textbox-status:warning");

        // 유효성 실패 안내 아이콘 추가
        confirmPasswordIcon.classList.remove("icon:check_circle", "icon-color:sub-2");
        confirmPasswordIcon.classList.add("icon:warning_circle", "icon-color:accent-2");
    }

    /* ============== 약관 동의 ============== */
    const termsList = form.querySelectorAll("input[type='checkbox']");

    termsList.forEach((checkbox) => {
        checkbox.addEventListener("click", termsCheckHandler);
    });

    function termsCheckHandler(e) {
        // 전체동의 체크박스인지 확인합니다.
        const isMasterCheckbox = termsList[0] === e.target;
        if (isMasterCheckbox) {
            termsList.forEach((checkbox) => checkbox.checked = e.target.checked);
        } else {
            // 모두 체크되어 있으면 첫 번째 체크박스도 체크합니다.
            termsList[0].checked = Array.from(termsList).slice(1).every(checkbox => checkbox.checked);
        }
    }

    /* ============== 마케팅 동의 모달 ============== */
    const modalBackDrop = main.querySelector(".backdrop");
    const marketingBtn = form.querySelector(".checkbox button");

    marketingBtn.addEventListener("click", ()=>{
        const modal = modalBackDrop.querySelector(".n-modal");
        if(modal){
            modal.classList.remove("in-active");
            modalBackDrop.classList.remove("fade-out");

            modal.classList.add("active");
            modalBackDrop.classList.add("fade-in");
        }

        modalBackDrop.classList.remove("d:none");
        modalBackDrop.classList.add("d:inline-block");
    })

    if(modalBackDrop){
        const modal = modalBackDrop.querySelector(".n-modal");
        const closeBtn = modalBackDrop.querySelector(".close-btn");

        closeBtn.addEventListener("click", () => {
            modal.classList.remove("active");
            modalBackDrop.classList.remove("fade-in");

            modal.classList.add("in-active");
            modalBackDrop.classList.add("fade-out");

            modalBackDrop.classList.remove("d:inline-block");
            modalBackDrop.classList.add("d:none");
        });
    }
});
