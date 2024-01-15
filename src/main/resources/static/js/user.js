let index = { // index 변수 선언
    init: function () { // init 메서드 작성
        $("#btn-save").on("click", () => { // btn-save라는 id 태그가 달렸을 때, 클릭하면
            this.save(); // save 메서드가 동작
        });
        $("#btn-update").on("click", () => {
            this.update();
        });
    },

    save: function () { // save 메서드 작성
        let data = { // username, password, email의 값을 data라는 하나의 객체로 만들어서 이 값을 백엔드 api로 날린다.
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        }
        if (checkUsername && validatePassword() && validateEmail()) { // 모든 유효성 검사값이 true가 되면
            $.ajax({
                type: "POST",
                url: "/auth/joinProc", // 백엔드로 요청을 보내 회원가입 로직이 실행됨.
                data: JSON.stringify(data),
                contentType: "application/json; charset=utf-8",
            }).done(function (resp) {
                if (resp.status == 500) { // 내부적으로 에러 발생
                    alert("Failed to Sign Up");
                } else {
                    alert("Success Sign Up");
                    location.href = "/auth/loginForm"; // 성공적으로 회원가입하면 로그인창으로 리다이렉션
                }
            }).fail(function (error) {
                alert(JSON.stringify(error))
            });
        } else { // 유효성 검사가 제대로 되지 않은 경우
            alert("Please Check ID and Password or Email")
        }
    },

    update: function () { // update 메서드 작성
        let data = {
            id: $("#id").val(),
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        }
        if (validatePassword()&&validateEmail()) { // password, email 유효성 검사르 통해 password, email 수정 가능
            $.ajax({ // password, email 값에 변동이 있으면 백엔드 api 호출
                type: "PUT",
                url: "/user",
                data: JSON.stringify(data),
                contentType: "application/json; charset=utf-8",
            }).done(function (resp) {
                alert("Success Update User Info");
                location.href = "/"; // 업데이트 성공하면 메인 페이지로 리다이렉션
            }).fail(function (error) {
                alert(JSON.stringify(error))
            });
        } else {
            alert("Please Check Password and Email")
        }
    }
}

let checkUsername;
function validateUsername() {
    const $resultUsername = $('#resultUsername');
    const usernameValue = $('#username').val();
    $resultUsername.text('');
    let checkLength = usernameValue.length >= 4 ? true : false;
    let checkDuplication = false;
    $.ajax({
        type: "GET",
        url: "/auth/username/"+usernameValue,
        async: false,
        contentType: "application/json;  charset=utf-8",
    }).done(function (resp) {
        checkDuplication = resp.data;
    });

    checkUsername = checkDuplication && checkLength;
    if (checkUsername) {
        $resultUsername.text('Valid Username');
        $resultUsername.css('color', 'green');
    } else {
        $resultUsername.text('Invalid Username');
        $resultUsername.css('color', 'red');
    }
}

const validateInputPassword = (password) => {
    return password.match(
        /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z8-9])(?!.*\s).{8,15}$/
    );
};

const validatePassword = () => {
    const $resultPassword = $('#resultPassword');
    const password = $('#password').val();
    $resultPassword.text('');

    if (validateInputPassword(password)) {
        $resultPassword.text('Valid Password');
        $resultPassword.css('color', 'green');
        return true;
    } else {
        $resultPassword.text('Invalid Password: One Upper Character, and One Special Character, and Length is 8 to 15');
        $resultPassword.css('color', 'red');
    }
    return false;
}

const validateInputEmail = (email) => {
    return email.match( // email@email.~ 형식인지 확인
        /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
        );
};

const validateEmail = () => {
    const $resultEmail = $('#resultEmail');
    const email = $('#email').val();
    $resultEmail.text('');

    if (validateInputEmail(email)) {
        $resultEmail.text('Valid Email Address');
        $resultEmail.css('color', 'green');
        return true;
    } else {
        $resultEmail.text('Invalid Email : Format(test@test.com)');
        $resultEmail.css('color', 'red');
    }
    return false;
}

index.init();

$('#username').focusout(validateUsername)
$('#password').on('input', validatePassword);
$('#email').on('input', validateEmail);