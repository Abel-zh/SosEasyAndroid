/*
 * Copyright (C) 2013  ethanchiu@126.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ea.compoment.resoperate.util;

import java.lang.reflect.Field;
import java.util.Date;

import ea.framework.ioc.annotation.commen.Transparent;
import ea.framework.ioc.annotation.view.InjectField;

/**
 * @Description: 反射的一些工具
 * @author: ethanchiu@126.com
 * @date: 2013-6-21
 */
public class ReflectUtils
{
	/**
	 * 检测实体属性是否已经被标注为 不被识别
	 * 
	 * @param field
	 *            字段
	 * @return
	 */
	public static boolean isTransient(Field field)
	{
		return field.getAnnotation(Transparent.class) != null;
	}

	/**
	 * 是否为基本的数据类型
	 * 
	 * @param field
	 * @return
	 */
	public static boolean isBaseDateType(Field field)
	{
		Class<?> clazz = field.getType();
		return clazz.equals(String.class) || clazz.equals(Integer.class)
				|| clazz.equals(Byte.class) || clazz.equals(Long.class)
				|| clazz.equals(Double.class) || clazz.equals(Float.class)
				|| clazz.equals(Character.class) || clazz.equals(Short.class)
				|| clazz.equals(Boolean.class) || clazz.equals(Date.class)
				|| clazz.equals(java.util.Date.class)
				|| clazz.equals(java.sql.Date.class) || clazz.isPrimitive();
	}

	/**
	 * 获得配置名
	 * 
	 * @param field
	 * @return
	 */
	public static String getFieldName(Field field)
	{
		InjectField column = field.getAnnotation(InjectField.class);
		if (column != null && column.name().trim().length() != 0)
		{
			return column.name();
		}
		return field.getName();
	}
	
}
