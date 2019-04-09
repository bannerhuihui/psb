$(function () {
    $("#index_login").click(function(){
        $.ajax({
            url:"/login/login.do",
            type:"POST",
            data:{"username":$("#username").val(),"password":$("#password").val()},
            success:function (data) {
                if (data.success != null ){
                    alert(data.success);
                } else{
                    alert(data.error);
                }
            },
            error:function () {
                alert("连个ajax都不会写！呵呵呵！");
            }
        });
    });

    $("#index_register").click(function(){
        alert($("#ver").val());
        /*$.ajax({
            url:"/login/register.do",
            type:"POST",
            data:{"username":$("#username").val(),"password":$("#password").val()},
            success:function (data) {
                if (data.success != null ){
                    alert(data.success);
                } else{
                    alert(data.error);
                }
            },
            error:function () {
                alert("连个ajax都不会写！呵呵呵！");
            }
        });*/
    });
});
