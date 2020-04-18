package com.holybell.demo.genmapper.dao;

import com.holybell.demo.genmapper.annotation.Select;

public interface CardDao {

    @Select("select * from card")
    public void showCard();
}
