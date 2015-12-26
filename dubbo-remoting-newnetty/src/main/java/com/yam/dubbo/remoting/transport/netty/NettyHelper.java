/**
 * Copyright: Copyright (c) 2015 
 * 
 * @author youaremoon
 * @date 2015年12月25日
 * @version V1.0
 */
package com.yam.dubbo.remoting.transport.netty;

import io.netty.buffer.ByteBuf;

import com.alibaba.dubbo.remoting.buffer.ChannelBuffer;

/**
 * 
 * @Description: TODO
 * @author youaremoon
 * @date 2015年12月25日 下午9:57:25
 *
 */
final class NettyHelper {

	/**
	 * 从ByteBuf获取byte[]数据
	 * @param
	 * @return
	 */
	public static byte[] getBytesFromByteBuf(ByteBuf bb) {
		byte[] data = null;

		int readableBytes = bb.readableBytes();
		if(bb.hasArray() && bb.arrayOffset() == 0 && readableBytes == bb.capacity()) {
			data = bb.array();
		} else {
			data = new byte[readableBytes];
			bb.getBytes(0, data, 0, readableBytes);
		}

		return data;
	}

	public static byte[] getBytesFromChannelBuffer(ChannelBuffer cb) {
		byte[] data;
		
		int readableBytes = cb.readableBytes();
		if (cb.hasArray() && cb.arrayOffset() == 0 && readableBytes == cb.capacity()) {
			data = cb.array();
		} else {
			data = new byte[readableBytes];
			cb.getBytes(0, data, 0, readableBytes);
		}
		
		return data;
	}
}
