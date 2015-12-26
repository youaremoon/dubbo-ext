/**
 * Copyright: Copyright (c) 2015 
 * 
 * @author youaremoon
 * @date 2015年12月25日
 * @version V1.0
 */
package com.yam.dubbo.serialize.protobuf;

import java.io.IOException;

import com.google.protobuf.MessageLite;

/**
 * 
 * @Description: TODO
 * @author youaremoon
 * @date 2015年12月25日 下午10:00:38
 *
 */
public class ProtobufSerializeFactory implements SpecialSerializeFactory {
	
	public static final ProtobufSerializeFactory INSTANCE = new ProtobufSerializeFactory();

	@Override
	public boolean supportDeserialize(int type) {
		if (type == ProtobufUtil.TYPE_PROTOBUF) {
			return true;
		}
		
		return false;
	}

	@Override
	public Object parse(Hessian2WithSpecialObjectInput input) throws IOException {
		String className = input.readUTF();
		byte[] data = input.readBytes();
		
		return ProtobufUtil.parseFrom(className, data);
	}

	@Override
	public boolean trySerializeObject(Hessian2WithSpecialObjectOutput output, Object obj) throws IOException {
		int type = ProtobufUtil.TYPE_NORMAL;
		boolean isMessageLiteBuilder = false;
		if (obj instanceof MessageLite.Builder) {
			isMessageLiteBuilder = true;
			type = ProtobufUtil.TYPE_PROTOBUF;
		} else if (obj instanceof MessageLite) {
			type = ProtobufUtil.TYPE_PROTOBUF;
		}
		
		if (type == ProtobufUtil.TYPE_PROTOBUF) {
			// 如果是protobuf则按照protobuf写入数据
			output.writeInt(ProtobufUtil.TYPE_PROTOBUF);	//标记位
			output.writeUTF(obj.getClass().getName());	//class name
			if (isMessageLiteBuilder) {
				output.writeBytes(((MessageLite.Builder)obj).build().toByteArray());	//数据
			} else {
				output.writeBytes(((MessageLite)obj).toByteArray());	//数据
			}
			
			return true;
		}
		
		return false;
	}
}

