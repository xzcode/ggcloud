package com.xzcode.ggcloud.router.common.ping;

/**
 * 心跳信息
 * 
 * @author zai
 * 2019-12-27 15:26:12
 */
public class RouterPingPongInfo {
	
	/**
	 * 心跳失败次数
	 */
	private int lostTimes = 0;
	
	/**
	 * 最大心跳失败允许次数
	 */
	private int maxLoseTimes = 3;
	
	
	public RouterPingPongInfo() {}
	
	
	public boolean isHeartBeatLost() {
		if (this.lostTimes >= maxLoseTimes) {
			return true;
		}
		return false;
	}
	
	
	/**
	 * 增加丢失次数
	 * 
	 * 
	 * @author zai
	 * 2017-09-19
	 */
	public void heartBeatLostTimesIncrease() {
		if (lostTimes < maxLoseTimes) {
			this.lostTimes++;
		}
	}
	
	/**
	 * 减少心跳丢失次数
	 * 
	 * 
	 * @author zai
	 * 2017-09-19
	 */
	public void heartBeatLostTimesDecrease() {
		if (lostTimes > 0) {
			this.lostTimes--;
		}
	}
	
	/**
	 * 重置心跳丢失次数
	 * 
	 * 
	 * @author zai
	 * 2017-09-19
	 */
	public void heartBeatLostTimesReset() {
		this.lostTimes = 0;
	}



	public int getHeartBeatLostTimes() {
		return lostTimes;
	}



	public int getMaxHeartBeatLoseTimes() {
		return maxLoseTimes;
	}


	@Override
	public String toString() {
		return "HeartBeatModel [lostTimes=" + lostTimes + ", maxLoseTimes=" + maxLoseTimes + "]";
	}
	
	


}
