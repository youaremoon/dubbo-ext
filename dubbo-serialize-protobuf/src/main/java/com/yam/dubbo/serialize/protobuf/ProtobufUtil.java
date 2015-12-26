/**
 * Copyright: Copyright (c) 2015 
 * 
 * @author youaremoon
 * @date 2015年12月25日
 * @version V1.0
 */
package com.yam.dubbo.serialize.protobuf;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.google.protobuf.MessageLite;


/**
 * 
 * @Description: TODO
 * @author youaremoon
 * @date 2015年12月25日 下午10:00:46
 *
 */
public class ProtobufUtil {
	/**
	 * 普通的Object
	 */
	public static final int TYPE_NORMAL = 0;

	/**
	 * protobuf类型的对象
	 */
	public static final int TYPE_PROTOBUF = 1;
	
	private static final String BUILDER_CLASS_SUFFIX = "$Builder";
	
	private static final Map<String, MessageLite.Builder> BUILDER_CACHE = new ConcurrentHashMap<String, MessageLite.Builder>();
	
	/**
	 * 根据protobuf类型解析
	 * @param 
	 * @return
	 */
	public static Object parseFrom(String className, byte[] data) throws IOException {
		try {
			boolean isBuilderClass = className.endsWith(BUILDER_CLASS_SUFFIX);
			if (isBuilderClass) {
				className = className.substring(0, className.length() - BUILDER_CLASS_SUFFIX.length());
			}
			
	        MessageLite.Builder builder = getPbBuilder(className);
	        builder.mergeFrom(data);
	        
	        if (isBuilderClass) {
	        	return builder;
	        } else {
	        	return builder.buildPartial();
	        }
		} catch (Exception ex) {
			if (ex instanceof IOException) {
				throw (IOException)ex;
			} else {
				throw new IOException(ex);
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static MessageLite.Builder getPbBuilder(String className) throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		MessageLite.Builder builder = BUILDER_CACHE.get(className);
		if (null == builder) {
			Class messageClass = Class.forName(className);
			
			Method newBuilder = messageClass.getMethod("newBuilder");
			builder = (MessageLite.Builder)newBuilder.invoke(null);
	        
	        BUILDER_CACHE.put(className, builder);
		}
		
        return builder.clone();
	}
}

