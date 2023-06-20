package service;

import dao.AccountDao;
import domain.Account;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class AccountService {
    private InputStream inputStream;
    private SqlSession sqlSession;
    private AccountDao accountDao;

    public AccountService() throws IOException{
        inputStream= Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(inputStream);
        sqlSession=factory.openSession();
        accountDao=sqlSession.getMapper(AccountDao.class);
    }
    private void destroy(){
        try {
            sqlSession.commit();
            sqlSession.close();
            inputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public List<Account> findAll(){
        return accountDao.findAll();
    }
    public int deleteByPrimaryKey(String id){
        return accountDao.deleteByPrimaryKey(id);
    }
    public int insert(Account record){
        return accountDao.insert(record);
    }
    public Account selectByPrimaryKey(String id){
        return accountDao.selectByPrimaryKey(id);
    }
    public int updateByPrimaryKey(Account record){
        return accountDao.updateByPrimaryKey(record);
    }

    public void transfer(String remitterId,String remitId,int money){
        Account remitter=accountDao.selectByPrimaryKey(remitterId);
        Account remit=accountDao.selectByPrimaryKey(remitId);

        remitter.setMoney(remitter.getMoney()-money);
        remit.setMoney(remit.getMoney()+money);

        accountDao.updateByPrimaryKey(remitter);
        accountDao.updateByPrimaryKey(remit);
    }
}
