package com.rhj.emo.controller;


import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import cn.hutool.json.JSONObject;
import com.rhj.emo.entity.User;
import com.rhj.emo.service.UserService;
import com.rhj.emo.util.NetworkUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("user/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("getAllUser")
    public List<User> getAllUsers(){
        List<User> list = userService.getAllUser();
        return list;

    }

    @PostMapping ("doLogin")
    public SaResult doLogin(@RequestBody String body,HttpSession session,HttpServletRequest request) {
        JSONObject user = new JSONObject(body);
        String userid = user.getStr("username", "");
        String password = user.getStr("password", "");
        String code = user.getStr("code","");

        if (!code.equals(session.getAttribute("validateCode"+NetworkUtil.getRequestIp(request)))){
            return SaResult.error("验证码错误");
        }


        if (userid.length()==0||password.length()==0){
            return SaResult.error("用户输入信息缺少，请重新输入！");
        }

        if (!userService.isExistUser(userid)){
            return SaResult.error(StrUtil.format("用户{}不存在！"));
        }

        Digester md5 = new Digester(DigestAlgorithm.MD5);
        String pwd_encode =  md5.digestHex(password);

        User userTrue = userService.getUserById(userid);
        if (!pwd_encode.equals(userTrue.getPassword())){
            return SaResult.error(StrUtil.format("用户{}密码输入错误，请重试！",userid));
        }
        //登录成功
        StpUtil.login(userid);
        return SaResult.ok("登录成功！");



    }



/**
 * 获取验证码图片
 * @return
 */
    @RequestMapping("/getVerifyCode")
    public void  getAuthCodeImg(HttpSession session, HttpServletRequest request,HttpServletResponse response, Integer count) {
        String requestIp = NetworkUtil.getRequestIp(request);
        //定义图形验证码的长和宽  码值个数  干扰圈数
        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(90, 40, 4, 10);

        if (null != count && count > 0) {
            //重新生成验证码
            circleCaptcha.createCode();
        }
        BufferedImage codeImg = circleCaptcha.getImage();
        //String codeImg = lineCaptcha.getImageBase64();
        String authCode = circleCaptcha.getCode();

        if (session.getAttribute("validateCode"+requestIp) != null) {
            session.removeAttribute("validateCode"+requestIp);
        }
        session.setAttribute("validateCode"+requestIp, authCode);
        System.out.println("validateCode"+requestIp+",验证码："+ authCode);
        ServletOutputStream sos;
        try {
            sos = response.getOutputStream();
            ImageIO.write(codeImg, "jpeg", sos);
            sos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // 查询登录状态，浏览器访问： http://localhost:8081/user/isLogin
    @RequestMapping("isLogin")
    public SaResult isLogin() {
        return SaResult.ok("是否登录：" + StpUtil.isLogin());
    }

    // 查询 Token 信息  ---- http://localhost:8081/acc/tokenInfo
    @RequestMapping("tokenInfo")
    public SaResult tokenInfo() {
        return SaResult.data(StpUtil.getTokenInfo());
    }

    // 测试注销  ---- http://localhost:8081/acc/logout
    @RequestMapping("logout")
    public SaResult logout() {
        StpUtil.logout();
        return SaResult.ok();
    }




}
