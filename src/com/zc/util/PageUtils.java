package com.zc.util;

public class PageUtils {
	private int currentPage;
	private int pageSize;
	private int totalRecords;
	private int totalPages;
	
	private int start;
	private int end;
	public PageUtils() {
		super();
	}
	public PageUtils(int currentPage, int pageSize) {
		this.currentPage=currentPage;
		this.pageSize=pageSize;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}
	public int getTotalPages() {
		return totalRecords%pageSize==0?totalRecords/pageSize:totalRecords/pageSize+1;
	}
	/*public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}*/
	public int getStart() {
		return (currentPage-1)*pageSize+1;
	}
	/*public void setStart(int start) {
		this.start = start;
	}*/
	public int getEnd() {
		return currentPage*pageSize;
	}
	/*public void setEnd(int end) {
		this.end = end;
	}*/
}
