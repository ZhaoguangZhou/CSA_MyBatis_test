package dao;

import domain.Account;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface AccountDao {
    @Select("select * from account")
    List<Account> findAll();
    @Delete("delete from account where id=#{id}")
    int deleteByPrimaryKey(String id);
    @Insert("insert into account values(#{id},#{name},#{money},#{createTime},#{updateTime})")
    int insert(Account record);
    @Select("select * from account where id=#{id}")
    Account selectByPrimaryKey(String id);
    @Update("update account set " +
            "name=#{name},money=#{money},createTime=#{createTime},updateTime=#{updateTime} " +
            "where id=#{id}")
    int updateByPrimaryKey(Account record);
}
