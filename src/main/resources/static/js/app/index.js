let main = {
    init : function (){
        let _this = this;
        $('#btn-save').on('click',function (){
            _this.save();
        });
    },
    save : function (){
        let data = {
            title : $('#title').val(),
            author : $('#author').val(),
            content : $('#content').val()
        };

        $.ajax({
            type : 'post',
            url : '/api/v1/posts',
            dataType:'json',
            contentType:'application/json; charset=utf-8',
            data:JSON.stringify(data)
        }).done(function (){
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    }
};
main.init();
alert("e등록 성공!");