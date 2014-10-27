package cl.jazocar.jprocess.ibatis.callable;

import org.apache.ibatis.session.SqlSession;

public class UpdateCallableDao<T> implements GenericCallableDao<T> 
{

	@Override
	public Integer genericCaller(SqlSession session, String function, Object parameter) throws Exception 
	{
		return session.update(function, parameter);
	}

}
