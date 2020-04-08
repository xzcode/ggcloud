package com.xzcode.ggcloud.session.group.common.message.req;

import xzcode.ggserver.core.common.message.model.IMessage;

/**
 * 数据传输请求
 *
 * @author zai 2020-04-08 10:31:48
 */
public class DataTransferReq implements IMessage {

	public static final String ACTION = "GG.SESSION.GROUP.DATA.TRANSFER.REQ";

	@Override
	public String getActionId() {
		return ACTION;
	}

	// 会话组id
	private String sessionGroupId;

	/* 消息标识 */
	private byte[] action;

	/* 消息体 */
	private byte[] message;

	public DataTransferReq() {

	}

	public DataTransferReq(String sessionGroupId) {
		super();
		this.sessionGroupId = sessionGroupId;
	}

	public void setSessionGroupId(String sessionGroupId) {
		this.sessionGroupId = sessionGroupId;
	}

	public String getSessionGroupId() {
		return sessionGroupId;
	}

	public byte[] getAction() {
		return action;
	}

	public void setAction(byte[] action) {
		this.action = action;
	}

	public byte[] getMessage() {
		return message;
	}

	public void setMessage(byte[] message) {
		this.message = message;
	}

}
