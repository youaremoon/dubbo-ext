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
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.dubbo.common.serialize.support.hessian.Hessian2ObjectInput;

/**
 * 
 * @Description: TODO
 * @author youaremoon
 * @date 2015年12月25日 下午10:00:05
 *
 */
public class Hessian2WithSpecialObjectInput extends Hessian2ObjectInput {
	
	private List<SpecialSerializeFactory> factoryList;

	public Hessian2WithSpecialObjectInput(InputStream is) {
		super(is);
	}
	
	public Hessian2WithSpecialObjectInput(InputStream is, SpecialSerializeFactory... factories) {
		super(is);
		
		if (null != factories) {
			factoryList = new ArrayList<SpecialSerializeFactory>();
			for (SpecialSerializeFactory factory : factories) {
				factoryList.add(factory);
			}
		}
	}
	
	public void addSerializeFactory(SpecialSerializeFactory factory) {
		if (null == factoryList) {
			factoryList = new ArrayList<SpecialSerializeFactory>();
		}
		
		factoryList.add(factory);
	}

	@Override
	public Object readObject() throws IOException {
		// 根据标记位判断Object类型
		int type = super.readInt();
		if (null != factoryList) {
			for (SpecialSerializeFactory factory : factoryList) {
				if (factory.supportDeserialize(type)) {
					return factory.parse(this);
				}
			}
		}
		
		return super.readObject();
	}

	@SuppressWarnings("unchecked")
	public <T> T readObject(Class<T> cls) throws IOException, ClassNotFoundException {
		return (T)readObject();
	}

	@SuppressWarnings("unchecked")
	public <T> T readObject(Class<T> cls, Type type) throws IOException, ClassNotFoundException {
		return (T)readObject();
	}
}
