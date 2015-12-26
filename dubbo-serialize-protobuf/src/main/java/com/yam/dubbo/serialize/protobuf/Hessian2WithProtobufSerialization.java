/**
 * Copyright: Copyright (c) 2015 
 * 
 * @author youaremoon
 * @date 2015年12月25日
 * @version V1.0
 */
package com.yam.dubbo.serialize.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.Adaptive;
import com.alibaba.dubbo.common.serialize.ObjectInput;
import com.alibaba.dubbo.common.serialize.ObjectOutput;
import com.alibaba.dubbo.common.serialize.Serialization;

/**
 * 
 * @Description: TODO
 * @author youaremoon
 * @date 2015年12月25日 下午9:59:54
 *
 */
public class Hessian2WithProtobufSerialization implements Serialization {

	@Override
	public byte getContentTypeId() {
		return 17;
	}

	@Override
	public String getContentType() {
		return "x-application/hessian2-spec";
	}

	@Override
	@Adaptive
	public ObjectOutput serialize(URL url, OutputStream output) throws IOException {
		return new Hessian2WithSpecialObjectOutput(output, ProtobufSerializeFactory.INSTANCE);
	}

	@Override
	@Adaptive
	public ObjectInput deserialize(URL url, InputStream input) throws IOException {
		return new Hessian2WithSpecialObjectInput(input, ProtobufSerializeFactory.INSTANCE);
	}
}

