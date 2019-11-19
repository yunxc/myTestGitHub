package com.yun.RSA;

import com.alibaba.fastjson.JSONObject;
import com.yun.util.HttpClientUtil;
import com.yun.util.HttpRes;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.cookie.Cookie;

import javax.crypto.Cipher;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Key;
import java.security.KeyFactory;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ThirdPartyController{
	
    // Cas登录页面地址
     //public static final String casLoginUrl = "http://127.0.0.1:8080/cas" + "/login";
    public static final String casLoginUrl = "http://172.16.3.18:8082/cas" + "/login";

    // 当前工程对外提供的服务地址
    public static final String shiroServerUrlPrefix = "http://172.16.9.60:9967/aiap";
    // casFilter UrlPattern
    public static final String casFilterUrlPattern = "/shiro-cas";
    
    // 登录地址
    public static final String loginUrl = casLoginUrl + "?service=" + shiroServerUrlPrefix + casFilterUrlPattern;
    
    // jump url
    public static String jumpUrl = "http://172.16.3.73:92/#/authority";
    
    // jump ip
    public static String jumpIp = "172.16.3.73";
    
	// 私钥
	private static final String PRIKEY = "MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAr7YN/ZjoazOxCTWc+J4dxh6Pr7QYMT6ZTO4mSvNibnAlEJrzGa9yENlFoMI5f76ZSiKpVwPfTQPkdikKuXDOpwIDAQABAkEAmpj1ylEE/X1noJC5Yc3MqIovJgqw1nMbcqJ+sqddzYgM7Nv2r+qfA6fAbPUohCfsDU+++RApenEHfu5l2zYWYQIhANgTCE1WfTD4h8W4aCjuM2JjJMD98O2q7rZoET+CbheZAiEA0C24tNKGbRvN9ftYt6dpYh8VXZQCjNviipY0ZkbBAD8CIH8/Ph1leNaVFkgvYE/yb5pabrOlGPSuPGR5hubS5gjJAiAP8JBF+CCm1gwtG3OtjDn7c1nVcnHHi0aLSfj1I9G/TQIgYKefqXPsdBYZId1mcsUSAnoaRn858//L/+hEUtsrGVU=";
	
	public static final String ALGORITHM = "RSA";
	
	public static final String salt = "firesoon@qwezxloj8973d!";

	private static BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();

	private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(50, 100, 1, TimeUnit.SECONDS, workQueue);

	private static int n = 0;
	public static void main(String[] args) throws Exception {
		String a = decryptByPri("TEGFYV4lsuTRo6SIlPVmc8WAGWZkzRjpKTcx7tdPBeEkNqi5f4PqtJ07sxEh5ZEUDcve2Vk5pbvld9UxPDKg2Rkf9dErMvX98HZWpjwS0q9S6xaKFNrxKMAGzEA+bc2kxZ6DyIq4GckFWXR4KmXtcGMDAYi7Pnik4DMs+mfG0XNT9fzUbIXYz/zBpvTZnSQpmdfqsw3VhS60ZIQl3C/LdwjbqmQM5sy1IfDWoZ42KN1PTMjdNCuk1wayO+17RD1/");
		System.out.println(a);
		String ss = "{\"organizationId\":\"3001\",\"doctorName\":\"姜航\",\"patientId\":\"12346\",\"doctorCode\":\"1010\",\"timestamp\":1564036696099}";
        JSONObject json = JSONObject.parseObject(ss);

            for(int i = 0; i< 1; i++){
				threadPoolExecutor.submit(
                        () -> login(json)
                );
               /*new Thread(new Runnable() {
                    @Override
                    public void run() {
                        login(json);
                    }
                }).start();*/
				//login(json);
            }
        System.out.println("getActiveCount" + threadPoolExecutor.getActiveCount());
            while (true){
                System.out.println("getActiveCount:" + threadPoolExecutor.getActiveCount());
                System.out.println("workQueue.size:" + workQueue.size());
                Thread.sleep(2000);
            }
	}

	/**
	 * 模拟登陆
	 * @param json
	 */
	private static void login(JSONObject json) {
		try {
			// 访问登录页面，获取需要的几个隐藏参数
			Map<String, String> headMap = new HashMap<>();
			headMap.put("Accept-Language", "zh-CN,zh");
			HttpRes res = HttpClientUtil.sendGet(loginUrl, headMap);
			String html = res.getHttpEntity();
			String lt = rex(html, "<input type=\"hidden\" name=\"lt\" value=\"(.*?)\"");
			String execution = rex(html, "<input type=\"hidden\" name=\"execution\" value=\"(.*?)\"");
			String _eventId = "submit";
			String submitBtn = "登录";
			String errorUrl = "";
			String jsessionid = rex(html, "jsessionid=(.*?)\"");
			// 拼接参数，执行登录
			Map<String, String> params = new HashMap<>();
			params.put("lt", lt);
			params.put("execution", execution);
			params.put("_eventId", _eventId);
			params.put("submitBtn", submitBtn);
			params.put("errorUrl", errorUrl);
			//拼接username，医生姓名base64编码，否则中文会乱码
			String username = String.format("ThirdParty-%s-%s-%s-huoshu", json.get("organizationId"), json.get("doctorCode"), 
					Base64.getEncoder().encodeToString(json.get("doctorName").toString().getBytes()));
			params.put("username", username);
			params.put("password", getPassword());
			Map<String, String> headers = new HashMap<>();
			headers.put("Cookie", "JSESSIONID=" + jsessionid);
			headers.put("Accept-Language", "zh-CN,zh");
			//超时
			HttpRes ticketRes = HttpClientUtil.sendPost(loginUrl, headers, params);
			
			//重定向
			String ticketUrl = ticketRes.getHeaders().get("Location");
			String cookie = ticketRes.getHeaders().get("Set-Cookie");
			Map<String, String> head = new HashMap<>();
			head.put("Cookie", cookie);
            HttpRes result = null;
			try {
			    result = HttpClientUtil.sendGet(ticketUrl);
            } catch (Exception e){
                System.out.println("1111111111111111111111111111111111");
			    e.printStackTrace();
            }

            System.out.println(n++ +"==========" + result);
			List<Cookie> Cookies = result.getCookies();
			for (Cookie co : Cookies) {
				if("JSESSIONID".equals(co.getName())) {
					javax.servlet.http.Cookie c = new javax.servlet.http.Cookie("JSESSIONID", co.getValue());
					c.setDomain(jumpIp);
				    c.setPath("/aiap");
				    c.setMaxAge(-1);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Ascii码
	 * @param psnid
	 * @return
	 */
	private static String Md5(String psnid) {
        return DigestUtils.md5Hex(psnid + salt);
	}
	
	/**
	 * 获取超级密码
	 * @return
	 */
	private static String getPassword() {
		Calendar calendar = Calendar.getInstance();
		String password = String.format("%shuo_sh%su-@ad%smin!-_$#*%%&&sikida#@!",calendar.get(Calendar.MONTH) , calendar.get(Calendar.YEAR), calendar.get(Calendar.DATE));
		return password;
	}
	
	private static String rex(String line, String pattern) {

		Pattern r = Pattern.compile(pattern);

		Matcher m = r.matcher(line);
		if (m.find()) {
			return m.group(1);
		} else {
			throw new RuntimeException("获取登录页面参数失败");
		}
	}

	/**
	 * 	页面写错误信息
	 * @param response
	 * @param code
	 * @throws IOException
	 */
	private void sendError(HttpServletResponse response, int code, String msg) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<h5>"+msg+"</h5>");
		out.flush();
		out.close();
	}
	
	/**
	 * 	公钥解密
	 * @param encryptData
	 * @return
	 * @throws Exception
	 */
	public static String decryptByPri(String encryptData) throws Exception {
		// 私钥有经过base64编码，先解码
		byte[] priByte = Base64.getDecoder().decode(PRIKEY);
		// 还原私钥
		PKCS8EncodedKeySpec pks = new PKCS8EncodedKeySpec(priByte);
		KeyFactory factory = KeyFactory.getInstance(ALGORITHM);
		Key priKey = factory.generatePrivate(pks);
		// 对数据解密
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, priKey);
		// 加密的数据有经过base64编码，所以先解码
		byte[] encryptByte = Base64.getDecoder().decode(encryptData);

		int inputLen = encryptByte.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;

		for (int i = 0; inputLen - offSet > 0; offSet = i * 64) {
			byte[] cache;
			if (inputLen - offSet > 64) {
				cache = cipher.doFinal(encryptByte, offSet, 64);
			} else {
				cache = cipher.doFinal(encryptByte, offSet, inputLen - offSet);
			}

			out.write(cache, 0, cache.length);
			++i;
		}

		String decryptedData = out.toString("UTF-8");
		out.close();
		return decryptedData;
	}

}
