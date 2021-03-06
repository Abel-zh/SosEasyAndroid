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
package ea.compoment.db.entity;

import ea.base.BaseEntity;

/**
 * @Description: 对应sqlite_master表的实体类
 * @author: ethanchiu@126.com
 * @date: 2013-7-2
 */
public class DBMasterEntity extends BaseEntity
{
	private static final long serialVersionUID = 4511697615195446516L;
	private String type;
	private String name;
	private String tbl_name;
	private String sql;
	private int rootpage;

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getTbl_name()
	{
		return tbl_name;
	}

	public void setTbl_name(String tbl_name)
	{
		this.tbl_name = tbl_name;
	}

	public String getSql()
	{
		return sql;
	}

	public void setSql(String sql)
	{
		this.sql = sql;
	}

	public int getRootpage()
	{
		return rootpage;
	}

	public void setRootpage(int rootpage)
	{
		this.rootpage = rootpage;
	}

}
