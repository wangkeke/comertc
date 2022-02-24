/**
 * Copyright 禅境科技股份有限公司
 * @author wk
 * @email kkwang@zenking.cc
 * @Date 2022-2-17
 *     All rights reserved.
 */
package cc.zenking.cloud.comertc.service;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cc.zenking.cloud.comertc.vo.PublishCondition;
import cc.zenking.cloud.comertc.vo.PublishVo;
import cc.zenking.cloud.comertc.vo.SubscribeCondition;
import cc.zenking.cloud.comertc.vo.SubscribeVo;
import cc.zenking.cloud.comertc.vo.UnpublishCondition;
import io.openvidu.java.client.Connection;
import io.openvidu.java.client.ConnectionProperties;
import io.openvidu.java.client.ConnectionType;
import io.openvidu.java.client.MediaMode;
import io.openvidu.java.client.OpenVidu;
import io.openvidu.java.client.OpenViduRole;
import io.openvidu.java.client.RecordingMode;
import io.openvidu.java.client.Session;
import io.openvidu.java.client.SessionProperties;
import io.openvidu.java.client.SessionProperties.Builder;

/**
 * openVidu通信
 * @author wk
 * @email kkwang@zenking.cc
 * @Date 2022-2-17
 * @Desc 1619cc84100b1106c7ead472e79b7220
 */
@Service
public class OpenViduService {
	
	private OpenVidu openVidu;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Value("${openvidu.hostname}")
	private String hostname;
	
	@Value("${openvidu.secret}")
	private String secret;
	
	@PostConstruct
	public void init() {
		this.openVidu = new OpenVidu(this.hostname, this.secret);
	}
	
	/**
	 * 发布视频
	 * @param condition 视频参数
	 * @return 发布结果
	 */
	public PublishVo publish(PublishCondition condition) {
		try {			
			SessionProperties sessionProperties = new Builder().allowTranscoding(true)
					.customSessionId(condition.getSessionId())
					.mediaMode(MediaMode.ROUTED)
					.recordingMode(RecordingMode.MANUAL)
					.build();
			Session session = openVidu.createSession(sessionProperties);
			io.openvidu.java.client.ConnectionProperties.Builder builder = new ConnectionProperties.Builder()
					.role(condition.getRole())
					.type(ConnectionType.WEBRTC)
					.record(condition.getIsRecord())
					.data(userDataJson(condition.getUser()));
			if(StringUtils.hasText(condition.getRtspUrl())) {
				builder.role(OpenViduRole.PUBLISHER)
					.type(ConnectionType.IPCAM)
					.adaptativeBitrate(true)
					.rtspUri(condition.getRtspUrl())
					.record(false)
					.data(null);
			}
		 	Connection connection = session.createConnection(builder.build());
		 	PublishVo vo = new PublishVo();
			vo.setConnectionId(connection.getConnectionId());
			vo.setToken(connection.getToken());
			return vo;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 订阅视频流
	 * @param roomCode 直播间编码
	 * @return 订阅结果
	 */
	public SubscribeVo subscribe(SubscribeCondition condition) {
		try {			
			SessionProperties sessionProperties = new Builder().allowTranscoding(true)
					.customSessionId(condition.getSessionId()).mediaMode(MediaMode.ROUTED)
					.recordingMode(RecordingMode.MANUAL).build();
			Session session = openVidu.createSession(sessionProperties);
			ConnectionProperties connectionProperties = new ConnectionProperties.Builder()
					.role(OpenViduRole.SUBSCRIBER)
					.type(ConnectionType.WEBRTC)
					.data(userDataJson(condition.getUser()))
					.build();
			Connection connection = session.createConnection(connectionProperties);
			SubscribeVo vo = new SubscribeVo();
			vo.setConnectionId(connection.getConnectionId());
			vo.setToken(connection.getToken());
			return vo;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 退出视频发布
	 * @param condition
	 */
	public void unpublish(UnpublishCondition condition) {
		try {			
			SessionProperties sessionProperties = new Builder().allowTranscoding(true)
					.customSessionId(condition.getSessionId())
					.mediaMode(MediaMode.ROUTED)
					.recordingMode(RecordingMode.MANUAL)
					.build();
			Session session = openVidu.createSession(sessionProperties);
			session.forceDisconnect(condition.getConnectionId());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 关闭视频会话
	 * @param sessionId 会话ID
	 */
	public void closeSession(@NotNull String sessionId) {
		try {			
			SessionProperties sessionProperties = new Builder().allowTranscoding(true)
					.customSessionId(sessionId)
					.mediaMode(MediaMode.ROUTED)
					.recordingMode(RecordingMode.MANUAL)
					.build();
			Session session = openVidu.createSession(sessionProperties);
			session.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 包装用户基本信息数据
	 * @return
	 */
	private String userDataJson(Object userData) {
		if(userData==null) {
			return null;
		}
		try {			
			return objectMapper.writeValueAsString(userData);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
	
}
