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
package ea.compoment.netstate;

import ea.util.check.NetWorkUtil.NetType;

/**
 * @Description: 检测网络改变的观察者
 * @author: ethanchiu@126.com
 * @date: 2013-7-1
 */
public class NetChangeObserver
{
	/**
	 * 网络连接连接时调用
	 */
	public void onConnect(NetType type)
	{
	}

	/**
	 * 当前没有网络连接
	 */
	public void onDisConnect()
	{
	}
}
