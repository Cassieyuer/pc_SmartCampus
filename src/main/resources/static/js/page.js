function Page(totalPages, size, numberOfElements, page) {
	this.page = parseInt(page);//第几页
    this.totalPages = parseInt(totalPages);//共多少页
    this.size = parseInt(size);//每页数据的数量
    this.numberOfElements = parseInt(numberOfElements);//当前页数据数量
    this._page = parseInt(page/10);//分页按钮第几批    
}
Page.prototype = {
	    constructor: Page,//原型字面量方式会将对象的constructor变为Object，此外强制指回Person
	    createPage: function (){//画分页界面
	    	$(".page:last").append("<font color='#337ab7'>当前第&nbsp;" + (this.page+1) +"&nbsp;页,共&nbsp;"+this.totalPages+"&nbsp;页</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
	    	$(".page:last").append("<a href='javascript:void(0)'>上一页</a>");
	    	var beg = this._page*this.size;
            var end = Math.min((this._page+1)*this.size, this.totalPages);
	    	for(var i= beg; i < end; i++){
	    		$(".page:last").append("<a href='javascript:void(0)'>"+(i+1)+"</a>");
	    	}
	    	$(".page:last").append("<a href='javascript:void(0)'>下一页</a>");
	    },
	    getNewPageValue: function (view, oldValue){//得到第几页
	    	if(view.innerText == '上一页'){
	    		var value = Math.max(parseInt(oldValue)-1, 1);
	    	} else if(view.innerText == '下一页'){
	    		var value = Math.min(parseInt(oldValue)+1, this.totalPages);
	    	} else {
	    		var value = view.innerText;
	    	}
	    	return parseInt(value)-1;
	    },
	   //构建新参数
		getNewParams: function(view,oldParams){
			var paramList = oldParams.split('&');//分割参数
			var newParams = "";//新参数
			var hasPage = false;//参数中是否含有page
			for(var i=0; i < paramList.length; i++){
				var key = paramList[i].split('=')[0];
				var value = paramList[i].split('=')[1];
				if(key == 'page'){
					hasPage = true;
					value = this.getNewPageValue(view, parseInt(value)+1);
				}
				newParams = newParams +key + '=' +value+'&';
			}
			if(!hasPage){
				var value = this.getNewPageValue(view,1);
				newParams = newParams + 'page=' + value+'&';//如果参数中没有页分页信息则加上
				hasPage = true;
			}
			return newParams.substring(0,newParams.length-1);
		}
	}