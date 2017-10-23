package com.user.gps.util;
/**
 * 
 * @author wufahang
 *
 */
public abstract class BaseShard {
	
	/**
     * get shard table count
     * @return
     */
    public abstract long getShardTableId();
    
    /**
     * get base shard name
     * @return
     */
    public abstract String getBaseShardTableName(Long id,int num);

}
