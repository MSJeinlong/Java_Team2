package dao;

import java.util.List;

//2:����һ��Dao�ӿ�
public interface UserDao {
	public void add(User u);;
	public void update(User u);
	public void delete(int id);
	public User getById(int id);
	public List<User> find();
}
