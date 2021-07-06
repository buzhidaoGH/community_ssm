$(function () {
  function clear_active(str){
    $('.nav .navigation ul li').each(function(i,n){
      $(n).removeClass("navigation_active");
    });
  }

  //默认加载首页
  $(".content .loading").load("pages/base");

  //首页加载
  $("#base_index").click(function () {//加载base.jsp
    $(".content .loading").load("pages/base");
    clear_active();
    $("#base_index").addClass("navigation_active");
  });

  //社团列表
  $("#group_list").click(function () {//加载group_list.jsp
    $(".content .loading").load("pages/group_list");
    clear_active();
    $("#group_list").addClass("navigation_active");
  });

  //社团活动
  $("#group_active").click(function () {//加载group_active.jsp
    $(".content .loading").load("pages/group_active");
    clear_active();
    $("#group_active").addClass("navigation_active");
  });

  //新闻列表
  $("#news_list").click(function () {//加载news_list.jsp
    $(".content .loading").load("pages/news_list");
    clear_active();
    $("#news_list").addClass("navigation_active");
  });

  $("#personal_center").click(function () {//加载personal_center.jsp
    $(".content .loading").load("pages/personal_center");
    clear_active();
    $("#personal_center").addClass("navigation_active");
  });

  //个人留言
  $("#message_list").click(function () {//加载message_list.jsp
    $(".content .loading").load("pages/message_list");
    clear_active();
    $("#message_list").addClass("navigation_active");
  });

  //系统简介
  $("#introduction").click(function () {//加载introduction.html
    $(".content .loading").load("pages/pages/introduction.html");
    clear_active();
    $("#introduction").addClass("navigation_active");
  });
});