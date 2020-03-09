package tech.yxing.clothing.pojo.vo;

public class PagesVo {
    private Integer page;
    private final Integer pageSize = 3;

    public PagesVo(){}

    public PagesVo(Integer page) {
        this.page = page;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }
}
