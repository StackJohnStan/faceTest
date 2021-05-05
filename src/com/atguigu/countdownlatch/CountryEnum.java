package com.atguigu.countdownlatch;

/**
 * @author Stack
 * @date 2021-03-28 19:51
 */
public enum CountryEnum {
    ONE(1,"齐"),TWO(2,"楚"),THREE(3,"燕"),FOUR(4,"韩"),FIVE(5,"赵"),SIX(6,"魏");

    private Integer retCode;

    private String retMessage;

    public Integer getRetCode() {
        return retCode;
    }

    public String getRetMessage() {
        return retMessage;
    }

    public void setRetCode(Integer retCode) {
        this.retCode = retCode;
    }

    public void setRetMessage(String retMessage) {
        this.retMessage = retMessage;
    }

    CountryEnum(Integer retCode, String retMessage) {
        this.retCode = retCode;
        this.retMessage = retMessage;
    }

    public static  CountryEnum foreach_Enum(int index){
        for (CountryEnum countryEnum : CountryEnum.values()) {
            Integer retCode = countryEnum.getRetCode();
            if(retCode.equals(index)){
                return  countryEnum;
            }
        }
        return null;
    }
}
