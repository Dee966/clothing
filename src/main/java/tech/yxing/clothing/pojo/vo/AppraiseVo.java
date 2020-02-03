package tech.yxing.clothing.pojo.vo;

public class AppraiseVo {
    private String appraise;

    public AppraiseVo(){}

    public AppraiseVo(String appraise) {
        this.appraise = appraise;
    }

    public String getAppraise() {
        return appraise;
    }

    public void setAppraise(String appraise) {
        this.appraise = appraise;
    }

    @Override
    public String toString() {
        return "AppraiseVo{" +
                "appraise='" + appraise + '\'' +
                '}';
    }
}
