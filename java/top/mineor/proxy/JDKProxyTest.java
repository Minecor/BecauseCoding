package top.mineor.proxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

/**
 * Created by mineor on 2017/1/18.
 */
public class JDKProxyTest {
    public static void main(String[]args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //这里有两种写法，我们采用略微复杂的一种写法，这样更有助于大家理解。
        Class<?> proxyClass= Proxy.getProxyClass(HelloWorldImpl.class.getClassLoader(),HelloWorld.class);
        final InvocationHandler ih = new MyInvocationHandler(new HelloWorldImpl());
        final Constructor<?> cons = proxyClass.getConstructor(InvocationHandler.class);
        HelloWorld helloWorld= (HelloWorld)cons.newInstance(ih);
        helloWorld.sayHello();


        //下面是更简单的一种写法，本质上和上面是一样的

       /* HelloWorld helloWorld=(HelloWorld)Proxy.
                 newProxyInstance(JDKProxyTest.class.getClassLoader(),
                        new Class<?>[]{HelloWorld.class},
                        new MyInvocationHandler(new HelloWorldImpl()));
        helloWorld.sayHello();*/

    }
}
