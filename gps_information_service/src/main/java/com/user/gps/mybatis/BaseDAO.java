package com.user.gps.mybatis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.util.CollectionUtils;

import com.user.gps.util.Constant;

/**
 * MyBatis的Dao基类
 * 
 * @author yusenhua
 */

public class BaseDAO {

	private static final Log LOG = LogFactory.getLog(BaseDAO.class);

	@Autowired
	private SqlSession sqlSession;
	@Autowired
	@Qualifier("endNumberDivisibleTableGenerator")
	protected DivisibleTable divisibleTable;

	private int maxRows = 5000;
	private int maxRowsForReport = 50000;
	private String namespaceName;
	private String baseTable;
	private int totalTableNum;

    public BaseDAO() { }

	public BaseDAO(String namespaceName) {
		super();
		this.namespaceName = namespaceName;
	}

	public BaseDAO(String namespaceName, String baseTable, int totalTableNum) {
	    this(namespaceName);
        this.baseTable = baseTable;
        this.totalTableNum = totalTableNum;
    }

	/**
	 * Get destination table name by id
	 * @param id id
	 * @return destination table name
	 */
	protected String getTableName(String baseTable,Long id,Integer totalTableNum) {
        if (null != baseTable && 0 != totalTableNum) {
            return divisibleTable.generateTableName(baseTable, id, totalTableNum);
        }
		return null;
	}
	
	/**
	 * Get destination table name by id
	 * @param id id
	 * @return destination table name
	 */
	protected String getTableNameOf(Long id) {
        if (null != baseTable && 0 != totalTableNum) {
            return divisibleTable.generateTableName(baseTable, id, totalTableNum);
        }
		return null;
	}

	private String createStatementName(String id) {
		return this.namespaceName + "." + id;
	}

	protected int insert(String key, Object object) {
		if (object != null) {
			return sqlSession.insert(createStatementName(key), object);
		}
		return 0;
	}
	
	protected int insertFromOtherTable(String key, Map<String, Object> paramMap) {
		if (paramMap != null) {
			return sqlSession.insert(createStatementName(key),paramMap);
		}
		return 0;
	}

	protected int update(String key, Object object) {
		if (object != null) {
			return sqlSession.update(createStatementName(key), object);
		}
		return 0;
	}

	protected int delete(String key, Serializable id) {
		if (id != null) {
			return sqlSession.delete(createStatementName(key), id);
		}
		return 0;
	}

	protected int delete(String key, Object object) {
		if (object != null) {
			return sqlSession.delete(createStatementName(key), object);
		}
		return 0;
	}

	protected int deleteAll(String key, Map<String, Object> paramMap) {
		if (paramMap != null) {
			return sqlSession.delete(createStatementName(key),paramMap);
		}
		return 0;
	}
	protected <T> T get(String key, Object params) { // update by yongchun
		List<T> list = this.getList(key, params);
		if(CollectionUtils.isEmpty(list)){
			return null;
		}
		
		if (list.size() == 1) {
			return list.get(0);
		} 
		
		if (list.size() > 1) {
			throw new TooManyResultsException("Expected one result (or null) to be returned by selectOne(), but found: "+ list.size());
		}
		
		return null;
	}
	
//	@SuppressWarnings({ "unchecked" })
//	protected <T> T get(String key, Object params) {
//		if (params != null) {
//			return (T) sqlSession.selectOne(createStatementName(key), params);
//		} else {
//			return null;
//		}
//	}
	
	/**
	 * 重载一个无参数的get方法，供vst_search使用
	 * @author wenzhengtao
	 * @param key
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	protected <T> T get(String key) {
		return (T) sqlSession.selectOne(createStatementName(key));
	}

	protected <T> List<T> getList(String key) {
		return sqlSession.selectList(createStatementName(key));
	}

	protected <T> List<T> getList(String key, Object params) {
		if (params != null) {
			return sqlSession.selectList(createStatementName(key), params);
		} else {
			return null;
		}
	}

	protected <T> List<T> queryForList(String statementName) throws DataAccessException {
		return queryForList(statementName, null);
	}

	protected <T> List<T> queryForList(final String statementName, final Object parameterObject) throws DataAccessException {
		if (parameterObject != null) {
			List<T> result = sqlSession.selectList(createStatementName(statementName), parameterObject, new RowBounds(0, this.maxRows));
			if ((result != null) && (result.size() == this.maxRows)) {
				LOG.warn("SQL Exception: result size is greater than the max rows, " + this.namespaceName + "." + statementName);
			}
			return result;
		} else {
			return null;
		}
	}

	protected <T> List<T> queryForList(String statementName, int skipResults, int maxResults) throws DataAccessException {

		if ((maxResults - skipResults) >= this.maxRows) {
			maxResults = skipResults + this.maxRows;
			LOG.warn("SQL Exception: result size is greater than the max rows, " + createStatementName(statementName));
		}

		return queryForList(statementName, null, skipResults, maxResults);
	}

	protected <T> List<T> queryForList(final String statementName, final Object parameterObject, final int skipResults, final int maxResults) throws DataAccessException {

		int tempMaxResults = maxResults;
		if ((maxResults - skipResults) >= this.maxRows) {
			tempMaxResults = skipResults + this.maxRows;
			LOG.warn("SQL Exception: result size is greater than the max rows, " + createStatementName(statementName));
		}
		return sqlSession.selectList(createStatementName(statementName), parameterObject, new RowBounds(skipResults, tempMaxResults));
	}

	// 数据量比较大的报表导出请用这个接口
	protected <T> List<T> queryForListForReport(String statementName) throws DataAccessException {
		return queryForListForReport(statementName, null);
	}

	// 数据量比较大的报表导出请用这个接口
	protected <T> List<T> queryForListForReport(final String statementName, final Object parameterObject) throws DataAccessException {

		List<T> result = sqlSession.selectList(createStatementName(statementName), parameterObject, new RowBounds(0, this.maxRowsForReport));

		if ((result != null) && (result.size() == this.maxRowsForReport)) {
			LOG.warn("SQL Exception: result size is greater than the max rows, " + statementName);
		}
		return result;
	}

	// 数据量比较大的报表导出请用这个接口
	protected <T> List<T> queryForList(final String statementName, final Object parameterObject, final boolean isForReportExport) throws DataAccessException {

		int maxRowsTemp = this.maxRows;
		if (isForReportExport) {
			maxRowsTemp = this.maxRowsForReport;
		}

		List<T> result = sqlSession.selectList(createStatementName(statementName), parameterObject, new RowBounds(0, maxRowsTemp));
		if ((result != null) && (result.size() == maxRowsTemp)) {
			LOG.warn("SQL Exception: result size is greater than the max rows, " + statementName);
		}
		return result;
	}

	/*
	 * XXX 搜索重载部分，允许参数传入null
	 */
	protected <T> List<T> getListFree(String key, Object params) {
		return sqlSession.selectList(createStatementName(key), params);
	}

	protected int updateFree(String key, Object object) {
		return sqlSession.update(createStatementName(key), object);
	}

	public String getNamespaceName() {
		return namespaceName;
	}

	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}

	public int getTotalTableNum() {
		return totalTableNum;
	}

	public void setTotalTableNum(int totalTableNum) {
		this.totalTableNum = totalTableNum;
	}
	
	protected <T> List<T> queryAllTableMap(int totalTable,int unionTableNum,String tableName,Map<String,Object> map,int resultType,String sqlId){
		List<T> result = new ArrayList<T>() ; 
		int num = totalTable/unionTableNum;
        for(int i=0;i<num;i++){
            for(int j=0;j<unionTableNum;j++){
                  map.put("table_name_"+j, tableName+"_"+(unionTableNum*i+j));
            }
            List<T> list = this.queryForList(sqlId, map);
            if(resultType == 0){
                if(list != null && !list.isEmpty() && list.size()!=0){
                    return list;
                }
            }else{
                if(list != null && !list.isEmpty() && list.size()!=0){
                     result.addAll(list);
                }
            }
        }
        return result;
    }
	
	 @SuppressWarnings("unchecked")
	protected int updateByInIds(Map<String, Object> params,String key,String sqlId) {
	    	int result = 0;
	    	List<Long>  ids = (List<Long>) params.get(key);
	    
	    	if(ids != null && ids.size()>0){
	    		int countList = ids.size();
	    		if(countList > Constant.IN_MAX){
	    			int c = countList/ Constant.IN_MAX;
	    			int y = countList% Constant.IN_MAX;
	    			for(int i=0;i<c;i++){
	    				List<Long> tempIds = ids.subList(i*100, i*100+100);
	    				params.put(key, tempIds);
	    				result  =result + this.update(sqlId, params);
	    				
	    			}
	    			if(y !=0){
	    				List<Long> tempIds = ids.subList(c*100, c*100+y);
	    				params.put(key, tempIds);
	    				result  =result + this.update(sqlId, params);
	    			}
	    		}
	    	}else{
	    		result = this.update(sqlId, params);
	    	}
	        return result;
	    }
	 
	 
	    @SuppressWarnings("unchecked")
		protected <T> List<T> queryListByInIds(Map<String, Object> params,String key,String sqlId) {
		    	List<T> result = new ArrayList<T>();
		    	List<T>  ids = (List<T>) params.get(key);
		    
		    	if(ids != null && ids.size()>0){
		    		int countList = ids.size();
		    		if(countList > Constant.IN_MAX){
		    			int c = countList/ Constant.IN_MAX;
		    			int y = countList% Constant.IN_MAX;
		    			for(int i=0;i<c;i++){
		    				List<T> tempIds = ids.subList(i*100, i*100+100);
		    				params.put(key, tempIds);
		    				List<T> mcList = this.queryForList(sqlId, params);
		    				if(mcList != null && mcList.size() >0){
		    					result.addAll(mcList);
		    				}
		    			}
		    			if(y !=0){
		    				List<T> tempIds = ids.subList(c*100, c*100+y);
		    				params.put(key, tempIds);
		    				List<T> mcList = this.queryForList(sqlId, params);
		    				if(mcList != null && mcList.size() >0){
		    					result.addAll(mcList);
		    				}
		    			}
		    		}else{
		    			result = this.queryForList(sqlId, params);
		    		}
		    	}else{
		    		result = this.queryForList(sqlId, params);
		    	}
		        return result;
		    }
	    
	    
	    @SuppressWarnings("unchecked")
		protected Long countListByInIds(Map<String, Object> params,String key,String sqlId) {
	    		Long  result = 0L;
		    	List<Long>  ids = (List<Long>) params.get(key);
		    
		    	if(ids != null && ids.size()>0){
		    		int countList = ids.size();
		    		if(countList > Constant.IN_MAX){
		    			int c = countList/ Constant.IN_MAX;
		    			int y = countList% Constant.IN_MAX;
		    			for(int i=0;i<c;i++){
		    				List<Long> tempIds = ids.subList(i*100, i*100+100);
		    				params.put(key, tempIds);
		    				result = result+(Long)this.get(sqlId, params);
		    			
		    			}
		    			if(y !=0){
		    				List<Long> tempIds = ids.subList(c*100, c*100+y);
		    				params.put(key, tempIds);
		    				result = result+(Long)this.get(sqlId, params);
		    			}
		    		}else{
		    			result = result+(Long)this.get(sqlId, params);
		    		}
		    	}else{
		    		result = result+(Long)this.get(sqlId, params);
		    	}
		        return result;
		    }
	    
	    
	    
	    protected List<Long> countTableNameMap(int totalTable,int unionTableNum,String tableName,Map<String,Object> map,int resultType,String sqlId){
			List<Long> result = new ArrayList<Long>() ; 
			int num = totalTable/unionTableNum;
	        for(int i=0;i<num;i++){
	            for(int j=0;j<unionTableNum;j++){
	                  map.put("table_name_"+j, tableName+"_"+(unionTableNum*i+j));
	            }
	            List<Long> list = this.queryForList(sqlId, map);
	            if(resultType == 0){
	                if(list != null && !list.isEmpty() && list.size()!=0){
	                    return list;
	                }
	            }else{
	                if(list != null && !list.isEmpty() && list.size()!=0){
	                     result.addAll(list);
	                }
	            }
	        }
	        return result;
	    }
	    
	    
	  

}