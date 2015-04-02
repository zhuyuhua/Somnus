##中英文对照
      | feature          | "功能"       |
      | background       | "背景"       |
      | scenario         | "场景"       |
      | scenario_outline | "场景大纲"     |
      | examples         | "例子"       |
      | given            | "* ", "假如" |
      | when             | "* ", "当"  |
      | then             | "* ", "那么" |
      | and              | "* ", "而且" |
      | but              | "* ", "但是" |
      | given (code)     | "假如"       |
      | when (code)      | "当"        |
      | then (code)      | "那么"       |
      | and (code)       | "而且"       |
      | but (code)       | "但是"       |

      
##中文范例
	
	# language: zh-CN
	功能: 用户登录
	  	 为了能够浏览网站只对在线会员可见的那些内容
	 	 作为一名访客
	  	 我希望能够登录
	
	  场景: 用户登录功能
	    	假如 没有<somebody@somedomain.com>这个用户
	    	当 我以<somebody@somedomain.com/password>这个身份登录
	    	那么 我应该看到<用户名或密码错误>的提示信息
	    	而且 我应该尚未登录