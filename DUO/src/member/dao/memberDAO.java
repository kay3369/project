package member.dao;

import org.apache.ibatis.session.SqlSession;

import member.dto.memberDTO;
import sqlMap.MybatisManager;

public class memberDAO {
	
	// 회원가입
	public void addMember(memberDTO dto) {
		SqlSession session = MybatisManager.getInstance().openSession();
		session.insert("member.addMember",dto);
		session.commit();
		session.close();
	}
}
