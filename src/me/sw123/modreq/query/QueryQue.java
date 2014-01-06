package me.sw123.modreq.query;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class QueryQue {
	
	private ArrayList<IQuery> que;
	
	public QueryQue(){
		que = new ArrayList<IQuery>();
	}
	public final void executeNext(Connection conn) throws SQLException{
		IQuery q = que.get(0);
		if(q instanceof Cancellable){
			Cancellable c = (Cancellable) q;
			if(!c.isCancelled()){
				q.execute(conn);
				q.onComplete();
				q.runPost();
			}
		}else{
			q.execute(conn);
			q.onComplete();
			q.runPost();
		}
		que.remove(0);
	}
	public final boolean hasNext(){
		return que.size() > 0 ? true : false;
	}
	public final void addQueryToQue(IQuery q){
		que.add(q);
	}
	public final void runQueryNow(Connection conn, IQuery q) throws SQLException{
		q.execute(conn);
		q.onComplete();
	}
	public final void removeFirst(){
		if(que.size() > 0){
			que.remove(0);
		}
	}
}
