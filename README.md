＃记录学习SpringbootDemo的过程  
[我的CSDN](https://blog.csdn.net/sinat_38701901)   
=
刚接触shiro，通过学习一些博主文章，整合了资源，完成了一个基于springboot的shirDEMO,  
-
****
 基本功能包括登录、注册、退出，对url权限的认证，<br>
数据库分为五个表包括  
*用户表：sys_users  
*角色表：sys_roles  
*用户角色表：sys_users_roles  
*权限表：sys_permissions  
*角色权限表：sys_roles_permissions  

API接口：  
-
*登录接口：localhost:8089/login  
*注册接口：localhost:8089/register  
*查询列表接口：localhost:8089/user/show  

权限管理：  
-
权限配置在shiroConfig配置文件中↓
用户在访问user接口下时，shiro会获取用户的权限信息，与perms进行匹配，用户权限不在perms数组中时，会返回拦截信息  
对URL权限配置

```
	filterChainDefinitionMap.put("/user/**", "authc,perms[user:list,user:create]");
```

 对于没有权限的请求，进行返回JSON信息  
	```
   Map<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("authc", new PermissionsAuthorizationFilter());
	```
	
	
继续完善中...  
-
	
