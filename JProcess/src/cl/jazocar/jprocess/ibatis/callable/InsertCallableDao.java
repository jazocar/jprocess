package cl.jazocar.jprocess.ibatis.callable;

import org.apache.ibatis.session.SqlSession;

public class InsertCallableDao<T> implements GenericCallableDao<T> 
{

	@Override
	public Integer genericCaller(SqlSession session, String function,
			Object parameter) throws Exception 
	{
		return session.insert(function, parameter);
	}

	
}
