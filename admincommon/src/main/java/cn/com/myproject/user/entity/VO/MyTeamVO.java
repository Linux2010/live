package cn.com.myproject.user.entity.VO;

import cn.com.myproject.admincon.entity.PO.ProfitShareRelation;
import cn.com.myproject.user.entity.PO.User;

import java.util.List;

/**
 * Created by JYP on 2017/9/6 0006.
 */
public class MyTeamVO {
    private User personal_user;//个人信息
    private ProfitShareRelation superior_user;//上级
    private List<ProfitShareRelation> second_user;//二级
    private Integer thrid_user_count;//三级人数

    public User getPersonal_user() {
        return personal_user;
    }

    public void setPersonal_user(User personal_user) {
        this.personal_user = personal_user;
    }

    public ProfitShareRelation getSuperior_user() {
        return superior_user;
    }

    public void setSuperior_user(ProfitShareRelation superior_user) {
        this.superior_user = superior_user;
    }

    public List<ProfitShareRelation> getSecond_user() {
        return second_user;
    }

    public void setSecond_user(List<ProfitShareRelation> second_user) {
        this.second_user = second_user;
    }

    public Integer getThrid_user_count() {
        return thrid_user_count;
    }

    public void setThrid_user_count(Integer thrid_user_count) {
        this.thrid_user_count = thrid_user_count;
    }
}
