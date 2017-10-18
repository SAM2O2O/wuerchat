package com.wuerchat.site.business.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.wuerchat.common.command.Command;
import com.wuerchat.common.command.CommandResponse;

public abstract class AbstractApiBusiness implements IApiService{

	public abstract CommandResponse execute(Command command);

	public CommandResponse executeMethodByReflect(Command command) {
		try {
			String methodName = command.getMethod();
			System.out.println("method===="+methodName);
			
			Method m = this.getClass().getDeclaredMethod(methodName, command.getClass());
			
			CommandResponse result = (CommandResponse) m.invoke(this, command);
			return result;
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
		return null;
	}
}
