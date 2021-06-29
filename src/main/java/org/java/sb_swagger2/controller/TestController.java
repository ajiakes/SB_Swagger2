package org.java.sb_swagger2.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.java.sb_swagger2.model.User;
import org.springframework.web.bind.annotation.*;

/**
 * @author gxd
 */
@Api(description = "测试Controller（接口方法组）",//@Api的description参数用于描述接口组，但不知道为什么这个参数被标注了@Deprecated注解弃用了，并没有替代的属性
        tags = {"测试Controller"}//@Api注解中的tags会覆盖掉原本默认的以类名下划线命名法命名的接口组名称
        ,position = 0)//position用于设置接口组在页面中的显示位置
@RestController
@RequestMapping("test")
public class TestController {

    @ApiOperation("该方法请求注解使用了@RequestMapper，所以该方法会拥有所有的请求方式接口显示")
    @RequestMapping("TestMethod")

    public String TestMethod(){
        return "success！";
    }

    //@ApiOperation中的tags属性可以为接口创建一个新的接口组，名称为属性的值
    // 一个组可以有多个接口，一个接口也可以属于多个组，tags的参数一个字符串数组，可传入多值
    // 被类上的@Api修饰后，再被@ApiOperation的接口会同时属于两个用户组
    @ApiOperation(value = "该方法请求注解使用了@GetMapper，所以该方法会拥有get请求方式接口显示，" +
            "同时该方法参数使用了@ApiParam注解，在页面参数下方会显示参数的含义",tags = {"新创建的接口组","test-controller"},
            notes = "此处为notes属性，用于进一步描述接口的信息，一般用于描述接口方法必要的重要信息",
            hidden = false,//hidden属性用于控制接口在页面中的显隐性，默认值为false，即显示，除非你想隐藏接口，否则无须配置该属性
            position =0 //position属性用户调整接口方法在接口组中的显示顺序
    )//@ApiOperation注解还有其他属性，但其余在正常使用中不常见，这里不再赘述
    @GetMapping("GetMethod/{id}")
    public String GetMethod(
            @ApiParam(
                    value = "测试参数"//参数输入框上方的文字,用于标注参数名称
                    ,defaultValue = "默认值参数"//swagger测试页面中的参数默认值
//                    ,required =true//是否是必传，默认是false，即为不必传，如果是必传，在页面上不传会有错误提示
//                    readOnly = false,//是否是只读
//                    allowEmptyValue = true,//是否能为空值，页面会有验证
//                    allowableValues = "sdfsf",//配置该项后，页面的参数输入框会变为下拉框，值只能从下拉框中选择
//                    allowMultiple = false//是否能为多媒体文件
            )
            @PathVariable("id") String arg){
        System.out.println(arg);
        return arg;
    }

    @ApiOperation("该方法请求注解使用了@RequestMapper，但通过该注解的method属性指定了方法为Post方法，但该方法参数未使用@ApiParam注解做修饰，所以没有注释")
    @RequestMapping(value = "PostMethod",method = RequestMethod.POST)
    @ResponseBody
    public User PostMethod(@RequestBody User user){
        System.out.println(user);
        return user;
    }
}
