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
package ea.exception;

/**
 * @Description: 所有异常的基类
 * @author: ethanchiu@126.com
 * @date: 2013-6-24
 */
public class BaseException extends Exception
{
	private static final long serialVersionUID = 1L;

	public BaseException()
	{
		super();
	}

	public BaseException(String detailMessage)
	{
		super(detailMessage);
	}
}