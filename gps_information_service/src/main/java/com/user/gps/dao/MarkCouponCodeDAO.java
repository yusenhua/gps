package com.user.gps.dao;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.user.gps.model.MarkCouponCode;
import com.user.gps.mybatis.BaseDAO;
import com.user.gps.util.CouponUtil;

@Repository
public class MarkCouponCodeDAO extends BaseDAO {

    /**
     * Max insert num
     */
	private static final int MAX_QUERY = 1000;

    public MarkCouponCodeDAO() {
        super("MARK_COUPON_CODE", "MARK_COUPON_CODE",32);  // total table num: 32
    }

    /**
     * Save mark coupon code
     * @param markCouponCode mark coupon code object
     * @return affected rows
     */
    public int saveMarkCouponCode(MarkCouponCode markCouponCode) {
        if (null == markCouponCode.getCouponId() || 0 == markCouponCode.getCouponId()) {
            return 0;
        }  	
        		
        markCouponCode.setCouponCodeId(CouponUtil.getCouponKeyId(markCouponCode.getCouponId()));
        Map<String, Object> params = new HashMap<>();
        params.put("table_name",    super.getTableNameOf(markCouponCode.getCouponId()));    // by couponId(优惠券活动ID)
        params.put("couponCodeId",  markCouponCode.getCouponCodeId());
        params.put("couponId",      markCouponCode.getCouponId());
        params.put("couponCode",    markCouponCode.getCouponCode());
        params.put("used",          markCouponCode.getUsed());
        params.put("beginTime",     markCouponCode.getBeginTime());
        params.put("endTime",       markCouponCode.getEndTime());
        params.put("taskFileId",    markCouponCode.getTaskFileId());

        return super.insert("insert", params);
    }

    /**
     * Delete record by primary key
     * @param couponId couponId
     * @param couponCodeId couponCodeId
     * @return affected rows
     */
    public int deleteByPrimaryKey(Long couponId, Long couponCodeId) {
        Map<String, Object> params = new HashMap<>();
        params.put("table_name", super.getTableNameOf(couponId));
        params.put("couponCodeId", couponCodeId);
    	return super.delete("deleteByPrimaryKey", params);
    }

    /**
     * Update by primary key
     * @param markCouponCode mark coupon code id
     * @return affected rows
     */
    public int updateByPrimaryKey(MarkCouponCode markCouponCode) {
        Map<String, Object> params = new HashMap<>();
        params.put("table_name", super.getTableNameOf(markCouponCode.getCouponId()));
        params.put("couponCodeId",  markCouponCode.getCouponCodeId());
        params.put("couponId",      markCouponCode.getCouponId());
        params.put("couponCode",    markCouponCode.getCouponCode());
        params.put("used",          markCouponCode.getUsed());
        params.put("beginTime",     markCouponCode.getBeginTime());
        params.put("endTime",       markCouponCode.getEndTime());
        params.put("taskFileId",    markCouponCode.getTaskFileId());

        return super.update("updateByPrimaryKey", params);
    }

    /**
     * Find by couponId and couponCodeId
     * @param couponId couponId
     * @param couponCodeId coupon code id
     * @return markCouponCode
     */
    public MarkCouponCode selectByPrimaryKey(Long couponId, Long couponCodeId) {
        Map<String, Object> params = new HashMap<>();
        params.put("table_name", super.getTableNameOf(couponId));
        params.put("couponCodeId", couponCodeId);

        return super.get("queryByPrimaryKey", params);
    }
    
    /**
     * Find by couponCodeId
     * @param couponCodeId coupon code id
     * @return markCouponCode
     */
    public MarkCouponCode selectByPrimaryKey(Long couponCodeId) {
        Map<String, Object> params = new HashMap<>();
        params.put("couponCodeId", couponCodeId);
        List<MarkCouponCode> result  = this.queryAllTableMap(this.getTotalTableNum(), 8,
				this.getNamespaceName(), params, 0, "queryByPrimaryKeyUnion");
        if (result != null && !result.isEmpty()) {
        	return result.get(0);
        }
        return null;
    }

    /**
     * 根据传入的优惠券号码查找所对应的优惠券批次的标识列表
     * @param couponCode 优惠券号码
     * @return 优惠券批次的标识列表
     * 查找所有还有此类优惠券号码的优惠券批次的标识号。此方法大量运用于通过优惠券号找优惠券批次及渠道。
     */
    @Deprecated
	public List<Long> findCouponIdByCouponCodeAndUserId(String couponCode, String userId) {
        Map<String, Object> params = new HashMap<>();
        params.put("table_name", super.getTableNameOf(25L));
        return super.queryForList("selectCouponIdByCouponCode", params);
    }
	
	/**
     * 根据传入的优惠券号码查找所对应的优惠券批次的标识列表
     * @param couponCode 优惠券号码
     * @return 优惠券批次的标识列表
     * 查找所有还有此类优惠券号码的优惠券批次的标识号。此方法大量运用于通过优惠券号找优惠券批次及渠道。
     */
	public List<Long> selectCouponIdByCouponCode(String couponCode) {
        Map<String, Object> params = new HashMap<>();
        int unionCount = 8 ;
        params.put("couponCode",couponCode);
        return  countTableNameMap(this.getTotalTableNum(), unionCount, this.getNamespaceName(), params,0);
    }
	
	/**
     * Find by couponId and couponCode
     * @param couponCode couponCode
     * @param couponId couponId
     * @return markCouponCode
     */
	public MarkCouponCode selectByCouponCodeAndCouponId(String couponCode, Long couponId) {
	    if (null == couponId || 0 == couponId) {
	        return null;
        }
        Map<String, Object> params = new HashMap<>();
        params.put("table_name", this.getTableNameOf(couponId));
        params.put("couponCode", couponCode);
        return get("selectByCouponCode", params);
    }

    /**
     * Find code by param, couponId is needed for locate table
     * @param paramMap couponId | isValid, whether to valid data | couponCode, code | used, whether used | _startRow, offset | _endRow, limit num
     * @return result list
     */
	public List<MarkCouponCode> selectByParam(Map<String, Object> paramMap) {
	    if (paramMap == null || !paramMap.containsKey("couponId")) {
            return null;
        }
	    Long couponId = Long.valueOf(paramMap.get("couponId")==null?"0":paramMap.get("couponId").toString());
       // Map<String, Object> params = new HashMap<>();
	    paramMap.put("table_name", super.getTableNameOf(couponId));
     

        return super.queryForList("selectByParam", paramMap);
    }

    /**
     * Count number by param
     * @param paramMap couponId | isValid, whether to valid data | couponCode, code | used, whether used
     * @return total result num
     */
	public int selectRowCount(Map<String, Object> paramMap) {
        if (paramMap == null || !paramMap.containsKey("couponId")) {
            return 0;
        }
        Map<String, Object> params = new HashMap<>();
        Long couponId = Long.valueOf(paramMap.get("couponId")==null?"0":paramMap.get("couponId").toString());
        params.put("table_name", super.getTableNameOf(couponId));
        if (paramMap.containsKey("isValid")) {
            params.put("isValid", paramMap.get("isValid"));
        }
        if (paramMap.containsKey("couponCode")) {
            params.put("couponCode", paramMap.get("couponCode"));
        }
        if (paramMap.containsKey("used")) {
            params.put("used", paramMap.get("used"));
        }
        
        return super.get("selectCountByParam", params);
    }

    /**
     * Insert coupon code by batch with a same couponId
     * @param couponCodeList coupon code list
     * @return affected rows
     */
	public int insertMarkCouponCodeBatch(List<MarkCouponCode> couponCodeList) {
		if (null == couponCodeList || 0 == couponCodeList.size()) {
            return 0;
        }
        // verify couponId
        if (null == couponCodeList.get(0)) {
		    return 0;
        }
        long couponId = couponCodeList.get(0).getCouponId();
		for (MarkCouponCode markCouponCode : couponCodeList) {
            if (couponId != markCouponCode.getCouponId()) {
                return 0;
            }
        }

        Map<String, Object> params = new HashMap<>();
        params.put("table_name", super.getTableNameOf(couponCodeList.get(0).getCouponId()));
        params.put("couponId", couponCodeList.get(0).getCouponId());

        int totalAffectedRows = 0;
		List<MarkCouponCode> subMarkCouponCodeList = new ArrayList<>();

		for (int i=0; i<couponCodeList.size(); i++) {
            subMarkCouponCodeList.add(couponCodeList.get(i));
            if ( 0 == (subMarkCouponCodeList.size() % MAX_QUERY) || i == (couponCodeList.size() - 1) ) {
                params.put("markCouponCodeList", subMarkCouponCodeList);
                int affectedRows = super.insert("insertBatchWithSameCouponId", params);
                if (affectedRows == subMarkCouponCodeList.size()) {
                    totalAffectedRows += affectedRows;
                    subMarkCouponCodeList.clear();
                } else {    // resolve later
                    return totalAffectedRows;
                }
            }
        }
        return totalAffectedRows;
	}
   
   /**
    * 根据用户id和优惠活动id查询优惠券
    * @param param 查询条件
    * @return 优惠码列表
    */
    // TODO 关联表
	public List<MarkCouponCode> queryByUserAndCoupon(Map<String, Object> param) {
        Map<String, Object> params = new HashMap<>();
        Long userId = Long.valueOf(param.get("userId")==null ? "0":param.get("userId").toString());
        Long couponId = Long.valueOf(param.get("couponId")==null? "0":param.get("couponId").toString());
        params.put("table_name", super.getTableNameOf(couponId));
        params.put("table_user_name", super.getTableName("MARK_COUPON_RELATE_USER", userId, 32));
        params.put("userId", userId);
        params.put("couponId", couponId);

        return super.queryForList("queryByUserAndCoupon", params);
    }

    /**
     * Update gotTime of code to now
     * @param markCouponCode markCouponCode
     * @return affected rows
     */
	public int updateGotTime(MarkCouponCode markCouponCode) {
        Map<String, Object> params = new HashMap<>();
        params.put("table_name", super.getTableNameOf(markCouponCode.getCouponId()));
        params.put("couponCodeId", markCouponCode.getCouponCodeId());
        return super.update("updateGotTime", params);
	}

    /**
     * Find markCouponCode which markCoupon is valid and markCouponCode is not getted by user
     * @param couponId markCouponId
     * @return markCouponCodeList
     */
	public List<MarkCouponCode> selectValidListByCouponId(Long couponId) {
        Map<String, Object> params = new HashMap<>();
        params.put("table_name", super.getTableNameOf(couponId));
        params.put("couponId", couponId);
        return super.queryForList("selectValidMarkCouponCodeListByCouponId", params);
	}

	public List<MarkCouponCode> selectByCouponCodeIds(List<Long> couponCodeIds) {
		
		List<MarkCouponCode> codes = new ArrayList<MarkCouponCode>();
        if(couponCodeIds == null || couponCodeIds.size() == 0) {
			return new ArrayList<>();
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		for(Long couponCodeId:couponCodeIds){
			map.put("table_name", super.getTableNameOf(couponCodeId));
			map.put("couponCodeId", couponCodeId);
			MarkCouponCode code = super.get("queryByPrimaryKey", map);
			if(code !=null){
				codes.add(code);
			}
		}		
		return codes;
	}

    /**
     * Update markCouponCodes with same couponId
     * @param paramMap couponId | used, 'false' for not used, 'true' for used | couponCodes, collection of markCouponCode
     * @return affectedRow
     */
	@SuppressWarnings("unchecked")
	public int updateMarkCouponCodeUsed(Map<String, Object> paramMap) {
      
        // TODO couponCodes, to find couponId for verification? performance 将来优化
		Integer result = 0;
        Map<String, Object> params = new HashMap<>();
        
        if (paramMap.containsKey("used")) {
            params.put("used", paramMap.get("used"));
        }
        if (paramMap.containsKey("couponCodes")) {
            params.put("couponCodes", paramMap.get("couponCodes"));
        }
        List<String> couponCodes = (List<String>) paramMap.get("couponCodes");
        if(couponCodes !=null && couponCodes.size()>0){
        	for(String couponCode:couponCodes){
        		for(int i=0;i<32;i++){
        			params.put("table_name",this.getNamespaceName()+"_"+i);
        			 params.put("couponCode", couponCode);
        			 int res = super.update("updateMarkCouponCodeUsed", params);
        			 if(res==1){
        				 result = 1;
        				 continue ;
        			 }
        		}
        	}
        }
        
        return result;
	}

	public List<MarkCouponCode> getMarkCouponCodeList(List<String> codeList) {
		  List<MarkCouponCode> result = new ArrayList<MarkCouponCode>();
		if(codeList == null || codeList.size() == 0){
			return result;
		}
		Map<String, Object> params = new HashMap<>();
		
		for(String code:codeList){
			params.put("couponCode", code);
			List<MarkCouponCode> couponCodees = this.selectdByCouponCodeAndUsedUnion(params);
			if(couponCodees != null && couponCodees.size() >0 ){
				result.add(couponCodees.get(0));
			}
		}	
		
		return result ;
	}

	public List<MarkCouponCode> getOutOfDateMarkCouponCodeList(Long couponId) {
        Map<String, Object> params = new HashMap<>();
        params.put("table_name", super.getTableNameOf(couponId));
        params.put("couponId", couponId);
        return super.queryForList("getOutOfDateMarkCouponCodeList", params);
	}
	
	public List<MarkCouponCode> getOutOfDateMarkCouponCodeListUnion(String validType) {
        Map<String, Object> params = new HashMap<>();
        params.put("validType", validType);
      //  params.put("couponId", couponId);
       // List<MarkCouponCode> result = TableNameMap(32, 4, params, params, 1);
        return this.queryAllTableMap(this.getTotalTableNum(), 4, this.getNamespaceName(), params, 1, "getOutOfDateMarkCouponCodeListUnion");
      //  return TableNameMap(32, 4, this.getNamespaceName(), params, 1); //super.queryForList("getOutOfDateMarkCouponCodeListUnion", params);
	}
    
	public int  insertHistoryCodeTable(List<MarkCouponCode> couponCodeList,Long couponId) {
		if (null == couponCodeList || 0 == couponCodeList.size()) {
            return 0;
        }       
		Map<String, Object> params = new HashMap<>();
        params.put("table_name", super.getTableName("MARK_COUPON_CODE_HISTORY", couponId, 32));

        int totalAffectedRows = 0;
		List<MarkCouponCode> subMarkCouponCodeList = new ArrayList<>();
        for (int i=0; i<couponCodeList.size(); i++) {
            subMarkCouponCodeList.add(couponCodeList.get(i));
            if ( 0 == (i % 1000) || i == (couponCodeList.size() - 1) ) {
                params.put("markCouponCodeList", subMarkCouponCodeList);
                int affectedRows = super.insert("insertIntoHistoryTable", params);
                if (affectedRows == i) {
                    totalAffectedRows += affectedRows;
                    subMarkCouponCodeList.clear();
                } else {    // resolve later
                    return totalAffectedRows;
                }
            }
        }
        return totalAffectedRows;
	}
	
	/**
	 * union 所有表
	 * @param totalTable
	 * @param unionTableNum
	 * @param tableName
	 * @param map
	 * @param resultType  0  有值就返回  1 取出所有返回
	 */
	private List<Long> countTableNameMap(int totalTable,int unionTableNum,String tableName,Map<String,Object> map,int resultType){
		List<Long> result = new ArrayList<Long>() ; 
		int num = totalTable/unionTableNum;
	        for(int i=0;i<num;i++){
	        	for(int j=0;j<unionTableNum;j++){
	        		  map.put("table_name_"+j, tableName+"_"+(unionTableNum*i+j));
	        	}
	        	
	        		List<Long> list = super.queryForList("selectCouponIdByCouponCodeAndUsedUnion", map);
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
	
	/**
	 * 分表查询  根据优惠活动ID 和其他条件查询优惠券码
	 * @param params
	 * @return
	 */
	public List<MarkCouponCode> selectCouponIdByCouponIdAndOther(Map<String,Object> params){
		Long couponId = Long.valueOf(params.get("couponId")==null?"0":params.get("couponId").toString());
		if(couponId != null && couponId > 0) {
			params.put("table_name", this.getTableNameOf(couponId));
		}
		return super.queryForList("selectCouponIdByCouponIdAndOther", params);
	}
	
	public List<MarkCouponCode> selectdByCouponCodeAndUsedUnion(Map<String, Object> params){
	        int unionCount = 8 ;
	       return  this.queryAllTableMap(this.getTotalTableNum(), unionCount, this.getNamespaceName(), params, 0, "selectdByCouponCodeAndUsedUnion");
	      // return this.getTableNameMap(this.getTotalTableNum(), unionCount, this.getNamespaceName(), params,1);
	}
	

	
	public MarkCouponCode  selectRelateUserCouponCode(Long couponId,Long couponCodeId,String used) {
        Map<String, Object> params = new HashMap<>();
        params.put("table_name", super.getTableNameOf(couponId));
        params.put("couponId", couponId);
        params.put("couponCodeId", couponCodeId);
        params.put("used", used);
        return super.get("selectRelateUserCouponCode", params);
	}
	
	public List<Long> selectCouponCodeIdByBeforDayOutDate(Long couponId){
	    Map<String, Object> params = new HashMap<>();
        params.put("table_name", super.getTableNameOf(couponId));
        params.put("couponId", couponId);		
		return super.queryForList("selectCouponCodeIdBeforDayOutDate", params);
	}
	
	public List<MarkCouponCode> selectOutofDateRelateUserCouponCode(Map<Integer,List<Long>> map){
		List<MarkCouponCode> result = new ArrayList<MarkCouponCode>();
		if (map.size() ==0){
			return result;
		}
		Map<String,Object> params = new  HashMap<String,Object>();
		for(Map.Entry<Integer, List<Long>> ids:map.entrySet()){
			params.put("table_name", this.getNamespaceName()+"_"+ids.getKey());
			List<Long> couponCodeIds = ids.getValue();
			if(couponCodeIds != null && couponCodeIds.size() >0){
				List<MarkCouponCode> codes  = null ;
				if(couponCodeIds.size()==1){
					params.put("couponCodeId", couponCodeIds.get(0));
					codes =  super.getListFree("selectOutDateRelateUserCouponCodeId",params);
				}else{
					params.put("couponCodeIds", couponCodeIds);
					codes =  super.queryListByInIds(params, "couponCodeIds", "selectOutDateRelateUserCouponCodeIds");
				}
				if(codes !=null && codes.size()>0){
					result.addAll(codes);
				}
			}
			
		
		}
		return result;
	}
	
	
	public List<MarkCouponCode> selectOutofDateRelateUserCouponCodemu(Map<Integer,List<Long>> map){
		List<MarkCouponCode> result = new ArrayList<MarkCouponCode>();
		if (map.size() ==0){
			return result;
		}
		Map<String,Object> params = new  HashMap<String,Object>();
		for(Map.Entry<Integer, List<Long>> ids:map.entrySet()){
			params.put("table_name", this.getTableNameOf(ids.getKey().longValue()));
			params.put("couponCodeIds", ids.getValue());
			List<MarkCouponCode> codes =  super.queryListByInIds(params, "couponCodeIds", "selectOutofDateRelateUserCouponCodemu");
			if(codes !=null && codes.size()>0){
				result.addAll(codes);
			}
		}
		return result;
	}
	
	public List<MarkCouponCode> selectOutofDateRelateUserCouponCodem(Map<Integer,List<Long>> map){
		List<MarkCouponCode> result = new ArrayList<MarkCouponCode>();
		if (map.size() ==0){
			return result;
		}
		Map<String,Object> params = new  HashMap<String,Object>();
		for(Map.Entry<Integer, List<Long>> ids:map.entrySet()){
			params.put("table_name", this.getNamespaceName()+"_"+ids.getKey());
			params.put("couponCodeIds", ids.getValue());
			List<MarkCouponCode> codes =  super.queryListByInIds(params, "couponCodeIds", "selectMarkCouponCodeByCouponIds");
			if(codes !=null && codes.size()>0){
				result.addAll(codes);
			}
		}
		
		return result;
	}
	
	public List<MarkCouponCode>  selectRelateUserCouponCodeIds(Map<Integer,List<Long>> map) {
		List<MarkCouponCode> result = new ArrayList<MarkCouponCode>();
		if (map.size() ==0){
			return result;
		}
		Map<String,Object> params = new  HashMap<String,Object>();
		for(Map.Entry<Integer, List<Long>> ids:map.entrySet()){
			params.put("table_name", this.getTableNameOf(ids.getKey().longValue()));
			params.put("couponCodeIds", ids.getValue());
			List<MarkCouponCode> codes =  super.queryListByInIds(params, "couponCodeIds", "selectRelateUserCouponCodeIds");
			if(codes !=null && codes.size()>0){
				result.addAll(codes);
			}
		}
		return result ;		
	}
	
	public List<Long> getOutOfDateMarkCouponCodeIdsList(Long couponId) {
        Map<String, Object> params = new HashMap<>();
        params.put("table_name", super.getTableNameOf(couponId));
        params.put("couponId", couponId);
        params.put("used", false);
        return super.queryForList("getOutOfDateMarkCouponCodeIdList", params);
	}
	
	public Integer selectCouponCodeCountByCouponIdAndCouponCode(Long couponId,String couponCode){
		Map<String, Object> params = new HashMap<>();
		params.put("table_name", super.getTableNameOf(couponId));
		params.put("couponId", couponId);
		params.put("couponCode", couponCode);
		return super.get("reuse_query_sql", params);
	}
	
	
	  
    public MarkCouponCode selectdByCouponCodeAndUsedUnion(String code){
    	Map<String,Object> params = new HashMap<String,Object>();
    	params.put("couponCode", code);
    	
    	List<MarkCouponCode> result = super.queryAllTableMap(this.getTotalTableNum(),8, this.getNamespaceName(), params, 0, "selectdByCouponCodeAndUsedUnion");
        if(result!=null && result.size()>0){
        	return result.get(0);
        }
        return null ;
    }
	
    public List<MarkCouponCode> queryMarkCouponCodeList(Map<String,Object> param) {
        Long couponId = (Long) param.get("couponId");
        if(couponId == null || couponId == 0){
        	return null ;
        }
        param.put("table_name", super.getTableNameOf(couponId));    
        return super.queryForList("queryMarkCouponCodeList", param);
    }
    
    public List<MarkCouponCode>  selectCouponCodesByCouponCodeIdsMap(Map<Integer,List<Long>> map,String used) {
    	List<MarkCouponCode> result = new ArrayList<MarkCouponCode>();
        Map<String, Object> params = new HashMap<>();
        for(Map.Entry<Integer,List<Long>> entry:map.entrySet()){
        	  params.clear();
        	  List<Long> couponCodeIds = entry.getValue();
        	  params.put("table_name",this.getNamespaceName()+"_"+entry.getKey());          
        	  params.put("used", used);
        	  if(couponCodeIds != null && couponCodeIds.size() ==1){
        		  params.put("couponCodeId", couponCodeIds.get(0));
        		  MarkCouponCode temp =  super.get("selectRelateUserCouponCode", params);
        		  if(temp != null){
        			  result.add(temp);
        		  }
        	  }else if (couponCodeIds != null && couponCodeIds.size() > 1){
        		  params.put("couponCodeIds", couponCodeIds);
        		  List<MarkCouponCode> tempes =  super.getList("selectRelateUserCouponCodeIds", params);
        		  if(tempes != null && tempes.size() > 0){
        			  result.addAll(tempes);
        		  }
        	  }
        	 
            
        }
      
        return result;
	}
    
    
	public List<MarkCouponCode> queryMarkCouponCodeListByCouponCodeIds(Map<Integer, List<Long>> map, String used) {

		List<MarkCouponCode> result = new ArrayList<MarkCouponCode>();
		Map<String, Object> params = new HashMap<>();
		for (Map.Entry<Integer, List<Long>> entry : map.entrySet()) {
			params.clear();
			List<Long> couponCodeIds = entry.getValue();
			params.put("table_name", this.getNamespaceName() + "_" + entry.getKey());
			params.put("used", used);
			if (couponCodeIds != null && couponCodeIds.size() == 1) {
				params.put("couponCodeId", couponCodeIds.get(0));
				MarkCouponCode temp = super.get("queryMarkCouponCodeList", params);
				if (temp != null) {
					result.add(temp);
				}
			} else if (couponCodeIds != null && couponCodeIds.size() > 1) {
				params.put("couponCodeIdes", couponCodeIds);
				List<MarkCouponCode> tempes = super.getList("queryMarkCouponCodeListByCouponCodeIds", params);
				if (tempes != null && tempes.size() > 0) {
					result.addAll(tempes);
				}
			}

		}

		return result;

	}
	
	
	
	public List<MarkCouponCode>  selectRelateUserCouponCodeByCouponCodeIds(Map<Integer,List<Long>> tablecouponCodeIds,String used) {
     
        List<MarkCouponCode> result = new ArrayList<MarkCouponCode>();
		Map<String, Object> params = new HashMap<>();        
    	for (Map.Entry<Integer, List<Long>> entry : tablecouponCodeIds.entrySet()) {
			params.clear();
			List<Long> couponCodeIds = entry.getValue();
			params.put("table_name", this.getNamespaceName() + "_" + entry.getKey());
			params.put("used", used);
			if (couponCodeIds != null && couponCodeIds.size() == 1) {
				params.put("couponCodeId", couponCodeIds.get(0));
				MarkCouponCode temp = super.get("selectRelateUserCouponCodeMcc", params);
				if (temp != null) {
					result.add(temp);
				}
			} else if (couponCodeIds != null && couponCodeIds.size() > 1) {
				params.put("couponCodeIds", couponCodeIds);
				List<MarkCouponCode> tempes = super.getList("selectRelateUserCouponCodeByCouponCodeIdsMcc", params);
				if (tempes != null && tempes.size() > 0) {
					result.addAll(tempes);
				}
			}

		}
    	return result ;
	}
	
	public List<MarkCouponCode> getOutOfDateMarkCouponCodeListByValidType(Map<String,Object> params){
		Integer tableNum=(Integer) params.get("tableNum");
		params.put("table_name", this.getNamespaceName() + "_" + tableNum);
		return super.getList("getOutOfDateMarkCouponCodeListFixed", params);
	}
	
	public Integer deleteOutOfDateMarkCouponCodeByIds(List<Long> codeIds ,Integer tableNum){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("table_name", this.getNamespaceName() + "_" + tableNum);
		params.put("couponCodeIds", codeIds);
		List<Integer> result = super.queryListByInIds(params, "couponCodeIds", "deleteOutMarkCouponCodeByCouponCodeIds");
		if(result ==null || result.size()==0){
			return 0 ;
		}
		return result.get(0);
		
	}
	
	
	public int  insertHistoryCodeTableNew(List<MarkCouponCode> couponCodeList,Integer tableNumb) {
		if (null == couponCodeList || 0 == couponCodeList.size()) {
            return 0;
        }       
		Map<String, Object> params = new HashMap<>();
        params.put("table_name", "MARK_COUPON_CODE_HISTORY_"+tableNumb);

        int totalAffectedRows = 0;
		List<MarkCouponCode> subMarkCouponCodeList = new ArrayList<>();
        for (int i=0; i<couponCodeList.size(); i++) {
            subMarkCouponCodeList.add(couponCodeList.get(i));
            if (  0 == (subMarkCouponCodeList.size() % MAX_QUERY) || i == (couponCodeList.size() - 1) ) {
                params.put("markCouponCodeList", subMarkCouponCodeList);
                int affectedRows = super.insert("insertIntoHistoryTable", params);
                if (affectedRows == i) {
                    totalAffectedRows += affectedRows;
                    subMarkCouponCodeList.clear();
                } else {    // resolve later
                    return totalAffectedRows;
                }
            }
        }
        return totalAffectedRows;
	}

	public List<MarkCouponCode> selectcouponCodeIdsBycouponIdAndupdateTime(Map<String, Object> map) {
		map.put("table_name",    super.getTableNameOf((long)map.get("couponId")));
		return super.queryForList("selectcouponCodeIdsBycouponIdAndupdateTime", map);
	}
	
}