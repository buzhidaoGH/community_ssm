$(function () {

  function clear_group(str){
    $('.group_list .group_list_left li').each(function(i,n){
      $(n).removeClass("group_active");
    });
  }

  $(".group_list .group_list_left li").click(function() {
    //this通过自定义属性设置样式
    clear_group();
    $(this).addClass("group_active");

    //this通过自定义属性取出社团编号
    var cnumber = $(this).attr("cnumber");
    //alert(data);
    // console.log(cnumber);

    //可以通过ajax来请求json数据
    $.ajax({
      type:"get",//请求类型
      contentType: "application/json; charset=UTF-8",//json接收
      url:"/community/simple?t="+new Date().getTime(),//请求的url
      async: true,//默认是异步
      data:{cnumber:cnumber},//请求参数
      dataType:"json",//ajax接口（请求url）返回的数据类型
      success:function(data){//data：返回数据（json对象）
        //然后更新html(jsp)对应内容
        // console.log(data);
        $(".content .group_list .group_list_right .club_details > h2").text(data.cname);
        $(".content .group_list .group_list_right .club_details > span").eq(1).text("成立时间："+data.creationtimeStr);
        $(".content .group_list .group_list_right .club_details > a").eq(0).attr("href","/show/communityPage?cnumber="+data.cnumber);
        $(".content .group_list .group_list_right .club_details > a").eq(1).attr("href","grouppictures/poster_"+data.imgpath);
        $(".content .group_list .group_list_right .club_details > a").eq(1).html('<img src="grouppictures/poster_'+data.imgpath+'" height="300px">');
        $(".content .group_list .group_list_right .club_details > p").html("&emsp;&emsp;"+data.introduction);
      },
      error:function(data){
        //然后更新html(jsp)对应内容
        console.log("失败");
      }
    });

    $.ajax({
      type:"get",//请求类型
      contentType: "application/json; charset=UTF-8",//json接收
      url:"/student/findPresidentByCnumber?t="+new Date().getTime(),//请求的url
      async: true,//默认是异步
      data:{cnumber:cnumber},//请求参数
      dataType:"json",//ajax接口（请求url）返回的数据类型
      success:function(data){//data：返回数据（json对象）
        //然后更新html(jsp)对应内容
        // console.log(data);
        $(".content .group_list .group_list_right .club_details > span").eq(0).text("社长："+data);
      },
      error:function(data){
        //然后更新html(jsp)对应内容
        console.log("失败");
      }
    });
  });
});