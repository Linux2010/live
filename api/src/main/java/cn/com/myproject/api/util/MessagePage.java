package cn.com.myproject.api.util;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 分页类
 * @author LeiJia
 * @param <T>
 */

public class MessagePage<T> implements Serializable {
    /**
     * 请求结果，0为失败，1为成功
     */
    @ApiModelProperty(value="请求结果，0为失败，1为成功",example = "1")
    private int result;


    /**
     * 请求结果信息
     */
    @ApiModelProperty(value = "请求结果信息",example = "成功")
    private String message;


    /**
     * 请求的返回数据对象，也将被转为json格式
     */
    @ApiModelProperty("请求的返回数据对象，也将被转为json格式")
    private T data;
    @ApiModelProperty(value="显示页码条目数，即页码数量顶多是10个",example = "10")
    private final static int PAGEITEMCOUNT = 10;  //显示页码条目数，即页码数量顶多是10个
    @ApiModelProperty(value="总记录数")
    private int totalRecord;        //总记录数
    @ApiModelProperty(value="页面显示的数目")
    private int pageSize = 5;       //页面显示的数目
    @ApiModelProperty(value="总页码数")
    private Integer totalPage;          //总页码数
    @ApiModelProperty(value="当前页码")
    private int currentPage = 1;    //当前页码
    @ApiModelProperty(value="前一页")
    private int previousPage;       //前一页
    @ApiModelProperty(value="后一页")
    private int nextPage;           //后一页
    @ApiModelProperty(value="条目数")
    private int[] pageBar;          //条目数
    @ApiModelProperty(value="开始页")
    private int startIndex;         //开始页
    @ApiModelProperty(value="结束页")
    private int endIndex;           //结束页


    public MessagePage() {

    }

    public MessagePage(int result,String message) {
        this.result = result;
        this.message = message;
    }
    public int getStartIndex() {
        this.startIndex = (this.currentPage-1)*this.pageSize;
        return startIndex;
    }
    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }
    public int getEndIndex() {   //从数据库中获取的结束索引，供页面使用
        int end = this.getStartIndex() + this.getPageSize();  //不包含最后一条记录-1
        if(end>this.getTotalRecord()){
            end = this.getStartIndex() + (this.getTotalRecord()%this.getPageSize());
        }
        this.endIndex = end;
        return this.endIndex;
    }
    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }
    public int getTotalRecord() {
        return totalRecord;
    }
    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public Integer getTotalPage() {             //得到总页码数
        if(this.totalRecord%this.pageSize==0){
            this.totalPage = this.totalRecord/this.pageSize;
        }else{
            this.totalPage = this.totalRecord/this.pageSize+1;
        }

        return totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    public int getPreviousPage() {
        if(this.currentPage-1<1){      //如果上一页小于1，则说明当前页码已经是第一页了
            this.previousPage = 1;
        }else{
            this.previousPage = this.currentPage-1;
        }
        return previousPage;
    }

    public int getNextPage() {
        if(this.currentPage+1>=this.totalPage){   //如果下一页大于总数页，则说明当前页码已经是最后一页了
            this.nextPage = this.totalPage;
        }else{
            this.nextPage = this.currentPage +1;
        }
        return nextPage;
    }

    public int[] getPageBar() {
        int startPage;      //记住页码的起始位置
        int endPage;        //记住页码的结束位置，因为页码条目是既定的，由startpage，endpage两个变量通过循环就可以得全部页码
        int pageBar[] = null;
        if(this.getTotalPage() <= PAGEITEMCOUNT){    //当总页码不足或等于既定页面大小时
            pageBar = new int[this.totalPage];
            startPage = 1;
            endPage = this.totalPage;
        }else{                  //当总页码大于既定页面大小时
            pageBar = new int[PAGEITEMCOUNT];
            startPage = this.currentPage - (PAGEITEMCOUNT/2-1);    //为了保证当前页在中间
            endPage = this.currentPage + PAGEITEMCOUNT/2;

            if(startPage<1){
                startPage = 1;
                endPage = PAGEITEMCOUNT;
            }

            if(endPage>this.totalPage){
                endPage = this.totalPage;
                startPage = this.totalPage - (PAGEITEMCOUNT-1);
            }
        }

        int index = 0;
        for(int i=startPage;i<=endPage;i++){
            pageBar[index++] = i;
        }

        this.pageBar = pageBar;
        return this.pageBar;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static int getPAGEITEMCOUNT() {
        return PAGEITEMCOUNT;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public void setPreviousPage(int previousPage) {
        this.previousPage = previousPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public void setPageBar(int[] pageBar) {
        this.pageBar = pageBar;
    }
}