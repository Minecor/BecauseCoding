package top.mineor.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by mineor on 2017/1/18.
 */
public class MyInvocationHandler implements InvocationHandler{
    private Object target;
    public MyInvocationHandler(Object target){
        this.target = target;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
        System.out.println("method:"+method.getName()+"is invoked!");
        return method.invoke(target,args);
    }
}

