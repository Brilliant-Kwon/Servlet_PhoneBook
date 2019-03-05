package PhoneBook.dao;

import PhoneBook.vo.PhoneBookVo;

import java.util.List;

public interface PhoneBookDao {
    public List<PhoneBookVo> getList();

    public boolean insert(PhoneBookVo vo);

    public boolean delete(Long no);

    public List<PhoneBookVo> search(String str);
}
