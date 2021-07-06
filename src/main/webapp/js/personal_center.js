$(function () {
  $(".personal_center_context > .my_active  table tr td > a").click(function () {
    //通过ajax改变用户和活动的签到状态
    var anumber = $(this).attr("anumber");
    var myself = $(this).get();
    $.ajax({
      type:"get",//请求类型
      contentType: "text/html; charset=UTF-8",//json接收
      url:"/change/signing",//请求的url
      async: true,//默认是异步
      data:{anumber:anumber},//请求参数
      dataType:"text",//ajax接口（请求url）返回的数据类型
      success:function(data){//data：返回数据（json对象）
        //然后更新html(jsp)对应内容
        alert("签到成功");
        $(myself).removeAttr("style");
        $(myself).text("已签到");
      },
      error:function(data){
        //然后更新html(jsp)对应内容
        console.log("失败");
      }
    });
  });

  $(".personal_center_context > .my_club  table tr td > a").click(function () {
    //通过ajax改变用户和活动的签到状态
    var cnumber = $(this).attr("cnumber");
    $.ajax({
      type:"get",//请求类型
      contentType: "text/html; charset=UTF-8",//json接收
      url:"/change/quitCommunity",//请求的url
      async: true,//默认是异步
      data:{cnumber:cnumber},//请求参数
      dataType:"text",//ajax接口（请求url）返回的数据类型
      success:function(data){//data：返回数据（json对象）
        //然后更新html(jsp)对应内容
        alert("退团成功");
        //加载personal_center.jsp
        $(".content .loading").load("pages/personal_center");
        clear_active();
        $("#personal_center").addClass("navigation_active");
      },
      error:function(data){
        //然后更新html(jsp)对应内容
        console.log("失败");
      }
    });
  });

});