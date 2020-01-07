package util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;

public class TransactionInvocationHandler implements InvocationHandler {

	private Object target;
	public TransactionInvocationHandler(Object target) {
		this.target=target;
	}
	//该方法为代理实现类的业务方法
	//zs:target
	//业务方法：method
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		Connection conn=null;
		Object obj=null;
		try {
			conn=DBUtil.getConnection();
			conn.setAutoCommit(false);
			
			
			//调用业务方法
			obj=method.invoke(target, args);
			
			
			conn.commit();
			
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		}
		finally {
			DBUtil.DBClose(conn, null, null);
		}
		
		
		return obj;
	}
	
	//取得代理实现类的对象
	public Object getProxy() {
		return  Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
	}

}
