jQuery(function($) {
	// 移除所有的active 和 menu-open 属性
	$('.nav-sidebar li').each(function(){  
		$(this).removeClass('menu-open');  
	});
	$('.nav-sidebar li a').each(function(){  
		$(this).removeClass('active');  
	});
	
	// 根据path加载 active 和 menu-open 属性
	$('.nav-sidebar li a').each(function(){  
		if($($(this))[0].href==String(window.location)) {
			$(this).addClass("active");
			$(this).parent().parent().parent().addClass("active menu-open");
		}
	});
});

var DEFAULT_CURRENT_PAGE = 1;
var DEFAULT_PAGE_SIZE = 5;

function initRoles(rolesDiv, roleName) {
	$("#" + rolesDiv + "").empty();
	$.ajax({
		url : "/api/roles",
		type : "get",
		contentType: "application/json",
		success : function (rs) {
			$("#" + rolesDiv + "").append("<label class='middle'>");
			$.each(rs, function(i, value) {
				$("#" + rolesDiv + "").append("<input name='"+ roleName + "' value='" + 
						value.roleId +"' type='checkbox'>" + value.roleName + "&nbsp;&nbsp;");
			});
			$("#" + rolesDiv + "").append("</label>");
		},
		error : function (data) {
			layer.alert(data.responseText, {icon: 0});
		}
	});
}