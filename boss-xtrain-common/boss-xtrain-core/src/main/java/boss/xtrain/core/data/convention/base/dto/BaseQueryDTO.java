package boss.xtrain.core.data.convention.base.dto;

/**
 * @description:
 * @author: WenZhi Luo
 * @create: 2020-07-01
 * @since
 */
public abstract class BaseQueryDTO extends BaseDTO {
    /**
     * 分页页码
     * */
    private Integer pageIndex;
    /**
     * 分页页面大小
     * */
    private Integer pageSize;
    /**
     * 排序列
     * **/
    private String orderByColumn;
    /**
     * 升序或者降序 "desc"或者"asc".
     * */
    private String isAsc;

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderByColumn() {
        return orderByColumn;
    }

    public void setOrderByColumn(String orderByColumn) {
        this.orderByColumn = orderByColumn;
    }

    public String getIsAsc() {
        return isAsc;
    }

    public void setIsAsc(String isAsc) {
        this.isAsc = isAsc;
    }

    public BaseQueryDTO() {
    }
}
