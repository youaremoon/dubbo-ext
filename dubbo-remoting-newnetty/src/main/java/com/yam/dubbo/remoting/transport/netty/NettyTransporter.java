/**
 * Copyright: Copyright (c) 2015 
 * 
 * @author youaremoon
 * @date 2015年12月25日
 * @version V1.0
 */
package com.yam.dubbo.remoting.transport.netty;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.remoting.ChannelHandler;
import com.alibaba.dubbo.remoting.Client;
import com.alibaba.dubbo.remoting.RemotingException;
import com.alibaba.dubbo.remoting.Server;
import com.alibaba.dubbo.remoting.Transporter;

/**
 * @Description: 新版本(4.0以上)的netty集成
 * @author youaremoon
 * @date 2015年12月25日 下午9:55:17
 *
 */
public class NettyTransporter implements Transporter {

	public static final String NAME = "newnetty";

	public Server bind(URL url, ChannelHandler listener) throws RemotingException {
		return new NettyServer(url, listener);
	}

	public Client connect(URL url, ChannelHandler listener) throws RemotingException {
		return new NettyClient(url, listener);
	}
}
