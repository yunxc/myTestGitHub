package server1;

import javax.jws.WebService;

/**
 * @author yunlong.zhang
 * @date 2019/4/18 12:13
 */
@WebService
public interface HelloWorld {
    public String sayHello(String name);
}
