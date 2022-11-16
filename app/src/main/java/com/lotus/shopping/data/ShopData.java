package com.lotus.shopping.data;

public class ShopData {
    String item;
    String count,money;
    String check;
    String create_at;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public ShopData(String item, String count, String money, String check, String create_at) {
        this.item = item;
        this.count = count;
        this.money = money;
        this.check = check;
        this.create_at = create_at;
    }

    public String getCreate_at() {
        return create_at;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }

    public String getItem() {
        return item;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }


    public void setItem(String item) {
        this.item = item;
    }


}
