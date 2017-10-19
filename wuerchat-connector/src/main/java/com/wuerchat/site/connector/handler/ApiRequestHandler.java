package com.wuerchat.site.connector.handler;

import java.util.HashMap;

import com.google.protobuf.ByteString;
import com.wuerchat.common.command.Command;
import com.wuerchat.common.command.CommandResponse;
import com.wuerchat.site.business.service.BusinessService;
import com.wuerchat.site.connector.client.ChannelSession;
import com.wuerchat.site.connector.codec.protocol.RedisCommand;
import com.zaly.proto.core.CoreProto;

import io.netty.channel.Channel;

/**
 * 使用TCP处理API请求,TCP代处理HTTP请求
 * 
 * @author Sam
 * @since 2017.10.19
 *
 * @param <Command>
 */
public class ApiRequestHandler extends AbstractCommonHandler<Command> {

	public boolean handle(Command cmd) {
		System.out.println("ApiBusinessHandler!!!");

		ChannelSession channelSession = cmd.getField("channel_session", ChannelSession.class);

		if (channelSession == null) {
			System.out.println("ApiBusinessHandler error.....");
		}

		process(channelSession.getChannel(), cmd);

		closeChannel(channelSession.getChannel());
		return true;
	}

	private void process(Channel channel, Command command) {

		System.out.println("execute api request from client");
		// invoke interface of business
		CommandResponse rs = (CommandResponse) new BusinessService().process(command);

		CoreProto.TransportPackageData data = CoreProto.TransportPackageData.newBuilder()
				.putAllHeader(new HashMap<Integer, String>()).setData(ByteString.copyFrom(rs.getParams())).build();

		System.out.println("rs=" + rs.toString());
		channel.writeAndFlush(new RedisCommand().add(1).add(rs.getAction()).add(data.toByteArray()));

	}

}
