let index = { // index 변수 선언
    init: function () { // init 메서드 작성
        $("#btn-save").on("click", () => { // btn-save라는 id 태그가 달렸을 때, 클릭하면
            this.save(); // save 메서드가 동작
        });
        $("#btn-update").on("click", () => {
            this.update();
        });
        $("#btn-reply-save").on("click", () => {
            this.saveReply();
        });
    },

    save: function () { // save 메서드 작성
        let data = {
            title: $("#title").val(),
            category: $("#category").val(),
            content: $("#content").val()
        }
        $.ajax({
            type: "POST",
            url: "/api/board",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
        }).done(function (resp) {
            alert("Success Save Post");
            location.href = "/";
        }).fail(function (error) {
            alert("Failed Save Post");
        })
    },

    update: function () {
        let id = $("#id").val();
        let data = {
            title: $("#title").val(),
            category: $("#category").val(),
            content: $("#content").val(),
        }
        $.ajax({ // password, email 값에 변동이 있으면 백엔드 api 호출
            type: "PUT",
            url: "/api/board/" + id,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
        }).done(function (resp) {
            alert("Success Update Post");
            location.href = "/"; // 업데이트 성공하면 메인 페이지로 리다이렉션
        }).fail(function (error) {
            alert("Failed Update Post");
        })
    },

    deleteById : function (boardId) {
        $.ajax({
            type : "DELETE",
            url: `/api/board/${boardId}`,
            contentType: "application/json; charset=utf-8",
        }).done(function (resp) {
            alert("Success Delete Post");
            location.href = "/";
        }).fail(function (error) {
            alert("Failed Delete Post");
        })
    },

    saveReply: function () {
        let data = {
            userId: $("#userId").val(),
            boardId: $("#boardId").val(),
            content: $("#reply-content").val()
        }
        $.ajax({
            type : "POST",
            url: `/api/board/${data.boardId}/reply`,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
        }).done(function (resp) {
            alert("Success Save Reply");
            location.href = `/board/${data.boardId}`;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    deleteReply: function (boardId, replyId) {
        console.log(`Delete button clicked for boardId: ${boardId}, replyId: ${replyId}`);
        $.ajax({
            type : "DELETE",
            url: `/api/board/reply/${replyId}`,
            contentType: "application/json; charset=utf-8",
        }).done(function (resp) {
            alert("Success Remove Reply");
            location.href = `/board/${boardId}`;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
}

index.init();