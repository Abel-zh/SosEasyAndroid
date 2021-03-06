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
package ea.compoment.cache;

import ea.base.BaseEntity;

public class CacheEntity<T> extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	private T t;
	private AsyncEntity asyncEntity;

	public T getT()
	{
		return t;
	}

	public void setT(T t)
	{
		this.t = t;
	}

	public AsyncEntity getAsyncEntity()
	{
		return asyncEntity;
	}

	public void setAsyncEntity(AsyncEntity asyncEntity)
	{
		this.asyncEntity = asyncEntity;
	}

}
