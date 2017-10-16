package com.wuerchat.common.chain;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.wuerchat.common.command.Command;

/**
 * 按照方法名称执行handler中方法
 * 
 * @author Sam
 * @since 2017.10.16
 * @param <T>
 */
public class MethodHandlerChain<T> implements IHandler<T> {

	public boolean handle(T t) {

		try {
			Command cmd = (Command) t;
			String methodName = cmd.getMethod();

			Method m = this.getClass().getDeclaredMethod(methodName, cmd.getClass());
			Object result = m.invoke(this, t);

			if (result != null && result instanceof Boolean) {
				return (Boolean) result;
			}
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

}
