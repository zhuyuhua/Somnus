/*
 * Copyright (c) 2010-2015. Somnus Framework
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.somnus.common.wrapper;

import java.io.Serializable;

/**
 * 封装页码
 * 
 * @author zhuyuhua
 * @version 0.0.1
 */
public class Pagination<T> implements Serializable {

	private static final long serialVersionUID = -2978759702157110862L;

	/** 总页数 */
	private int totalPageCount;

	/** 当前页码 */
	private int pageNo;

	/** 每页总数 */
	private int pageSize;

	/** 总数据条目数 */
	private long totalItemCount;

	public Pagination() {
		this.pageSize = 10;
		this.pageNo = 0;
		this.totalPageCount = 1;
	}

	public static <T> Pagination<T> getInstance(int pageNo, int limit) {
		if (pageNo < 0) {
			pageNo = 0;
		}
		if (limit <= 0) {
			limit = 40;
		}
		Pagination<T> pagination = new Pagination<T>();
		pagination.setPageNo(pageNo);
		pagination.setPageSize(limit);
		return pagination;
	}

	public Pagination(Integer pageNo) {
		this();
		if (pageNo != null) {
			this.setPageNo(pageNo);
		}
	}

	public Pagination(Integer pageNo, Integer pageSize) {
		this(pageNo);
		if (pageSize != null) {
			this.setPageSize(pageSize);
		}
	}

	public void setTotalItemCount(Long itemCount) {
		if (itemCount != null) {
			this.totalItemCount = itemCount;
			this.totalPageCount = (int) ((itemCount + pageSize - 1) / pageSize);
			this.totalPageCount = totalPageCount <= 0 ? 1 : totalPageCount;
		}
	}

	public int getStart() {
		return pageSize * pageNo;
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(Integer totalPageCount) {
		if (totalPageCount != null) {
			this.totalPageCount = totalPageCount < 1 ? 1 : totalPageCount;
		}
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		if (pageSize != null) {
			this.pageSize = pageSize;
		}
	}

	public long getTotalItemCount() {
		return totalItemCount;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 计算总页数
	 */
	public void build() {
		if (this.pageSize < 0) {
			this.pageSize = 10;
		}
		if (this.totalItemCount < 0) {
			this.totalItemCount = 0;
		}
		this.totalPageCount = (int) ((totalItemCount % pageSize > 0) ? totalItemCount / pageSize + 1
				: totalItemCount / pageSize);
	}
}
