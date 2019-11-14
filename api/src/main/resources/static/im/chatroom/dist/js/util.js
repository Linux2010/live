var util ={
    readCookie:function(name) { 
        var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
        if(arr=document.cookie.match(reg)){
            return unescape(arr[2]); 
        }else{
            return null;
        }       
    },
    setCookie:function(name,value) { 
        var days = 1; 
        var exp = new Date(); 
        exp.setTime(exp.getTime() + days*24*60*60*1000); 
        document.cookie = name + "="+ escape (value) + ";path=/;expires=" + exp.toGMTString(); 
    },
    delCookie:function(name) { 
        var cval=this.readCookie(name); 
        if(cval!=null){
            document.cookie= name + "=;path=/;expires="+(new Date(0)).toGMTString();
        }
    },  
    getAvatar:function(url) {
        var re=/^((http|https|ftp):\/\/)?(\w(\:\w)?@)?([0-9a-z_-]+\.)*?([a-z0-9-]+\.[a-z]{2,6}(\.[a-z]{2})?(\:[0-9]{2,6})?)((\/[^?#<>\/\\*":]*)+(\?[^#]*)?(#.*)?)?$/i;
        if(re.test(url)){
            return url+"?imageView&thumbnail=80x80&quality=85";
        }else{
            return "../images/default-icon.png"
        } 
    },
	buildEmoji:function(text) {
		var re = /\[([^\]\[]*)\]/g;
		var matches = text.match(re) || [];
		for (var j = 0, len = matches.length; j < len; ++j) {
			if(emoji[matches[j]]){
				text = text.replace(matches[j], '<img class="emoji" src="images/emoji/' + emoji[matches[j]].file + '" />');
			}		
		}
		return text;
	},
	buildMsg:function(data){
        var text;
        switch (data.type){
            case 'text':
                text = this.buildEmoji(this.escape(data.text));
                break;
            case 'custom':
                if (!data.content) {
                    text = '[未知消息类型]';
                }else{
                    var content = JSON.parse(data.content);
                    if(content.type===0) {
                        var imgUrl = "";
                        if(content.data.present===0) {
                            imgUrl = $("#basePath").val()+"im/chatroom/img/present_flower.png";
                            text = '送给主播'+content.data.count+'个<img src="'+imgUrl+'">';
                        }else if(content.data.present===1) {
                            imgUrl = $("#basePath").val()+"im/chatroom/img/present_chocolate.png";
                            text = '送给主播'+content.data.count+'个<img src="'+imgUrl+'">';
                        }else if(content.data.present===2) {
                            imgUrl = $("#basePath").val()+"im/chatroom/img/present_bear.png";
                            text = '送给主播'+content.data.count+'个<img src="'+imgUrl+'">';
                        }else if(content.data.present===3) {
                            imgUrl = $("#basePath").val()+"im/chatroom/img/present_ice_cream.png";
                            text = '送给主播'+content.data.count+'个<img src="'+imgUrl+'">';
                        }
                    }
                }
                break;
            default:
                text = '[未知消息类型]';
                break;
        }
        return text;
	},
    buildSysMsg:function(data){
        var text;
        var nameVal = "...";
        var warnInfo = "...";
        var infoVal = "...";
        if(data != null && data.attach != null){
            if(data.attach.fromNick != null){
                nameVal = data.attach.fromNick;
            }
            if(data.attach.toNick != null && data.attach.toNick[0] != null){
                warnInfo = data.attach.toNick[0];
            }
            if(data.attach.to != null && data.attach.to[0] != null){
                infoVal = data.attach.to[0];
            }
        }
        switch (data.attach.type){
            case 'memberEnter':
                text = "欢迎"+nameVal+"进入直播间";
                break;
            case 'memberExit':
                text = nameVal+"离开了直播间";
                break;
            case 'blackMember':
                text = (warnInfo||infoVal)+"被管理员拉入黑名单";
                break;
            case 'unblackMember':
                text = (warnInfo||infoVal)+"被管理员解除拉黑";
                break;
            case 'gagMember':
                text = (warnInfo||infoVal)+"被管理员禁言";
                break;
            case 'ungagMember':
                text = (warnInfo||infoVal)+"被管理员解除禁言";
                break;
            case 'addManager':
                text = (warnInfo||infoVal)+"被任命管理员身份";
                break;
            case 'removeManager':
                text = (warnInfo||infoVal)+"被解除管理员身份";
                break;
            case 'addCommon':
                text = "addCommon";
                break;
            case 'removeCommon':
                text = "removeCommon";
                break;
            case 'kickMember':
                text = warnInfo+"被管理员踢出房间";
                break;
            // case 'xxx':
            // 直播公告已更新
            // break;
            default:
                text = "通知消息";
                break;
        }
        return text;    
    },
    // 成员类型解析
    parseMemberType:function(data){
        var type;
        switch(data.type){
            case "guest":
                type = -2;
                break;
            case "common":
                type = 0;
                break;
            case "owner":
                type = 1;
                break;
            case "manager":
                type = 2;
                break;
            case "restricted":
                type = -1;
                break;
            default:
                type = -2;
                break;
        }
        return type;
    },
    // 成员类型解析
    parseMemberTypeToString:function(num){
        var type;
        switch(num){
            case -2:
                type ="guest";
                break;
            case 0:
                type ="common";
                break;
            case 1:
                type ="owner";
                break;
            case 2:
                type ="manager";
                break;
            case -1:
                type ="restricted"
                break;
            default:
                type = "guest";
            break;
        }
        return type;
    },
    escape:(function(){
        var _reg = /<br\/?>$/,
            _map = {
                r:/\<|\>|\&|\r|\n|\s|\'|\"/g,
                '<':'&lt;','>':'&gt;','&':'&amp;',' ':'&nbsp;',
                '"':'&quot;',"'":'&#39;','\n':'<br/>','\r':''
            };
        var _$encode = function(_map,_content){
            _content = ''+_content;
            if (!_map||!_content){
                return _content||'';
            }
            return _content.replace(_map.r,function($1){
                var _result = _map[!_map.i?$1.toLowerCase():$1];
                return _result!=null?_result:$1;
            });
        };
        return function(_content){
            _content = _$encode(_map,_content);
            return _content.replace(_reg,'<br/><br/>');
        };
    })(),
    /**
    * 获取id
    * @param {String} id字段
    * @return {long}  id
    */
    getIdTag:function(id){
        var tip =id||"id",
            _hash=document.location.search,
            _id=0;
        if(_hash.length>0){
            _hash=_hash.substring(1);
            var _akv=_hash.split("&");
            for(var i=0,j=_akv.length;i<j;++i){
                var _kv=_akv[i].split("=");
                if(_kv.length==2&&_kv[0]==tip){
                    _id=_kv[1];
                }
            }
        }
        return _id;
    }
};
